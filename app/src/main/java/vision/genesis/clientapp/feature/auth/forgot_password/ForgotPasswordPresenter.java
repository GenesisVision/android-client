package vision.genesis.clientapp.feature.auth.forgot_password;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.CaptchaCheckResult;
import io.swagger.client.model.CaptchaDetails;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.utils.CaptchaUtils;
import vision.genesis.clientapp.utils.ValidatorUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/2/18.
 */

@InjectViewState
public class ForgotPasswordPresenter extends MvpPresenter<ForgotPasswordView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription forgotPasswordSubscription;

	private String email;

	private CaptchaCheckResult captchaCheckResult;

	private Subscription riskControlSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (riskControlSubscription != null) {
			riskControlSubscription.unsubscribe();
		}
		if (forgotPasswordSubscription != null) {
			forgotPasswordSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onEmailChanged(String email) {
		getViewState().setButtonEnabled(ValidatorUtil.isEmailValid(email));
	}

	void onResetPasswordClicked(String email) {
		this.email = email.trim();
		if (ValidatorUtil.isEmailValid(this.email)) {
			checkRiskControl(this.email);
		}
		else {
			getViewState().setEmailError(context.getString(R.string.enter_valid_email));
		}
	}

	private void checkRiskControl(String route) {
		riskControlSubscription = authManager.checkRiskControl(route)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleRiskControlSuccess,
						this::handleRiskControlError);
	}

	private void handleRiskControlSuccess(CaptchaDetails captchaDetails) {
		riskControlSubscription.unsubscribe();

		switch (captchaDetails.getCaptchaType()) {
			case NONE:
				sendForgotPassword();
				break;
			case POW:
				performPowCaptcha(captchaDetails);
				break;
			case GEETEST:
				getViewState().showProgressBar(false);
				break;
		}
	}

	private void handleRiskControlError(Throwable throwable) {
		riskControlSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void performPowCaptcha(CaptchaDetails captchaDetails) {
		Single.fromCallable(() -> CaptchaUtils.findPowPrefix(captchaDetails))
				.subscribeOn(Schedulers.computation())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(captchaCheckResult -> {
					this.captchaCheckResult = captchaCheckResult;
					sendForgotPassword();
				});
	}

	private void sendForgotPassword() {
		getViewState().showProgressBar(true);
		forgotPasswordSubscription = authManager.sendForgotPassword(email, captchaCheckResult)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleForgotPasswordResponse,
						this::handleForgotPasswordError);
	}

	private void handleForgotPasswordResponse(Void response) {
		forgotPasswordSubscription.unsubscribe();

		getViewState().showMessageDialog(R.drawable.image_ok,
				context.getString(R.string.email_sent),
				context.getString(R.string.forgot_password_we_sent_email),
				false,
				() -> getViewState().finishActivity());
	}

	private void handleForgotPasswordError(Throwable throwable) {
		forgotPasswordSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null && response.errors != null && !response.errors.isEmpty()) {
				Error error = response.errors.get(0);
				getViewState().showSnackbarMessage(error.message);
			}
		}
	}
}
