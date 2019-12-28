package vision.genesis.clientapp.feature.auth.registration;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.CaptchaCheckResult;
import io.swagger.client.model.CaptchaDetails;
import rx.Observable;
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

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription riskControlSubscription;

	private Subscription registrationSubscription;

	private boolean privacyPolicyAccepted;

	private boolean termsConditionsAccepted;

	private String email;

	private String password;

	private String confirmPassword;

	private CaptchaCheckResult captchaCheckResult;

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
		if (registrationSubscription != null) {
			registrationSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onAcceptPrivacyPolicyCheckedChanged(boolean checked) {
		privacyPolicyAccepted = checked;
		updateSignUpButtonEnabled();
	}

	void onAcceptTermsConditionsCheckedChanged(boolean checked) {
		termsConditionsAccepted = checked;
		updateSignUpButtonEnabled();
	}

	private void updateSignUpButtonEnabled() {
		getViewState().setSignUpButtonEnabled(privacyPolicyAccepted && termsConditionsAccepted);
	}

	void onSignInClicked() {
		getViewState().showLoginActivity();
	}

	//	void onSignUpClicked(String userName, String email, String password, String confirmPassword) {
	void onSignUpClicked(String email, String password, String confirmPassword) {
		if (privacyPolicyAccepted && termsConditionsAccepted) {
			this.email = email.trim();
			this.password = password;
			this.confirmPassword = confirmPassword;

			checkRiskControl(email);
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
				register();
				break;
			case POW:
				performPowCaptcha(captchaDetails);
				break;
			case GEETEST:
				getViewState().showProgress(false);
				break;
		}
	}

	private void handleRiskControlError(Throwable throwable) {
		riskControlSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void performPowCaptcha(CaptchaDetails captchaDetails) {
		Single.fromCallable(() -> CaptchaUtils.findPowPrefix(captchaDetails))
				.subscribeOn(Schedulers.computation())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(captchaCheckResult -> {
					this.captchaCheckResult = captchaCheckResult;
					register();
				});
	}

	private void register() {
		getViewState().clearErrors();
		getViewState().showProgress(true);

//		registrationSubscription = getRegisterObservable(userName, email, password, confirmPassword)
		registrationSubscription = getRegisterObservable(null, email, password, confirmPassword, captchaCheckResult)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onRegisterResponse,
						this::onRegisterError);
	}

	private Observable<Void> getRegisterObservable(String userName, String email, String password, String confirmPassword, CaptchaCheckResult captchaCheckResult) {
		return authManager.register(email, password, confirmPassword, captchaCheckResult);
	}

	private void onRegisterResponse(Void response) {
		registrationSubscription.unsubscribe();

		getViewState().showMessageDialog(R.drawable.image_ok,
				context.getString(R.string.email_sent),
				context.getString(R.string.email_verification_text),
				true,
				() -> getViewState().finishActivity());
	}

	private void onRegisterError(Throwable throwable) {
		registrationSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.property == null) {
						getViewState().showSnackbarMessage(error.message);
					}
					else {
						switch (error.property.toLowerCase()) {
							case "username":
								getViewState().setUserNameError(error.message);
								break;
							case "email":
								getViewState().setEmailError(error.message);
								break;
							case "password":
								getViewState().setPasswordError(error.message);
								break;
							case "confirmpassword":
								getViewState().setConfirmPasswordError(error.message);
								break;
							default:
								getViewState().showSnackbarMessage(error.message);
								break;
						}
					}
				}
			}
		}
	}
}