package vision.genesis.clientapp.feature.auth.login;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.CaptchaCheckResult;
import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.ErrorCodes;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnCheckTfaConfirmClickedEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaErrorEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaSuccessEvent;
import vision.genesis.clientapp.model.events.OnEmailVerificationSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.utils.CaptchaUtils;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription riskControlSubscription;

	private Subscription loginSubscription;

	private String email;

	private String password;

	private boolean tfaEnabled;

	private String tfaCode;

	private boolean useRecoveryCode;

	private CaptchaCheckResult captchaCheckResult;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (riskControlSubscription != null) {
			riskControlSubscription.unsubscribe();
		}
		if (loginSubscription != null) {
			loginSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onSignUpClicked() {
		getViewState().showRegistrationActivity();
	}

	void onSignInClicked(String email, String password) {
		this.email = email.trim();
		this.password = password;
		if (this.email.isEmpty()) {
			getViewState().showSnackbarMessage(context.getString(R.string.error_enter_email));
		}
		else if (this.password.isEmpty()) {
			getViewState().showSnackbarMessage(context.getString(R.string.error_enter_password));
		}
		else {
			checkRiskControl(email);
		}
	}

	private void checkRiskControl(String route) {
		getViewState().showProgress();
		riskControlSubscription = authManager.checkRiskControl(route)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleRiskControlSuccess,
						this::handleRiskControlError);
	}

	private void handleRiskControlSuccess(CaptchaDetails captchaDetails) {
		riskControlSubscription.unsubscribe();
		captchaCheckResult = null;

		switch (captchaDetails.getCaptchaType()) {
			case NONE:
				login();
				break;
			case POW:
				performPowCaptcha(captchaDetails);
				break;
			case GEETEST:
				getViewState().hideProgress();
				break;
		}
	}

	private void handleRiskControlError(Throwable throwable) {
		riskControlSubscription.unsubscribe();
		getViewState().hideProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void performPowCaptcha(CaptchaDetails captchaDetails) {
		Single.fromCallable(() -> CaptchaUtils.findPowPrefix(captchaDetails))
				.subscribeOn(Schedulers.computation())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(captchaCheckResult -> {
					this.captchaCheckResult = captchaCheckResult;
					login();
				});
	}

	private void login() {
		getViewState().clearErrors();
		getViewState().showProgress();
		loginSubscription = authManager.login(email, password, tfaCode, useRecoveryCode, captchaCheckResult)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onLoginResponse,
						this::onLoginError);
	}

	private void onLoginResponse(String response) {
		loginSubscription.unsubscribe();

		getViewState().finishActivity(false);
		if (tfaEnabled) {
			EventBus.getDefault().post(new OnCheckTfaSuccessEvent());
		}
	}

	private void onLoginError(Throwable throwable) {
		loginSubscription.unsubscribe();
		getViewState().hideProgress();

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (tfaEnabled) {
				EventBus.getDefault().post(new OnCheckTfaErrorEvent(context.getResources().getString(R.string.network_error)));
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
			getViewState().hideProgress();
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				if (response.code.equals(ErrorCodes.REQUIRESTWOFACTOR.toString())) {
					String action = String.format(Locale.getDefault(), "%s %s", context.getString(R.string.sign_in_as), email);
					getViewState().startCheckTfaActivity(action);
				}
				else if (response.code.equals(ErrorCodes.REQUIRESEMAILCONFIRMATION.toString())) {
					if (tfaEnabled) {
						EventBus.getDefault().post(new OnCheckTfaSuccessEvent());
					}
					authManager.saveTempToken(response.tempToken);
					getViewState().startEmailConfirmationActivity(email, response.tempToken);
				}
				else {
					for (Error error : response.errors) {
						if (error.property == null || error.property.isEmpty()) {
							if (tfaEnabled) {
								EventBus.getDefault().post(new OnCheckTfaErrorEvent(error.message));
							}
							else {
								getViewState().showSnackbarMessage(error.message);
							}
						}
						else {
							switch (error.property.toLowerCase()) {
								case "email":
									getViewState().setEmailError(error.message);
									break;
								case "password":
									getViewState().setPasswordError(error.message);
									break;
								default:
									getViewState().showSnackbarMessage(error.message);
									break;
							}
						}
					}
				}
			}
			getViewState().hideProgress();
			tfaEnabled = false;
		}
	}

	@Subscribe
	public void onEventMainThread(OnCheckTfaConfirmClickedEvent event) {
		tfaEnabled = true;
		this.tfaCode = event.getCode();
		this.useRecoveryCode = event.isUseRecoveryCode();
		login();
	}

	@Subscribe
	public void onEventMainThread(OnEmailVerificationSuccessEvent event) {
		authManager.handleGetTokenResponse(event.getToken());
		onLoginResponse(event.getToken());
	}
}
