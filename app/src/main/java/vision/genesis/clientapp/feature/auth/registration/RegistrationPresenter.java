package vision.genesis.clientapp.feature.auth.registration;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Observable;
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
import vision.genesis.clientapp.utils.Constants;

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

	private Subscription registrationSubscription;

	private boolean privacyPolicyAccepted;

	private boolean termsConditionsAccepted;

	private boolean notUsResidentChecked;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (registrationSubscription != null)
			registrationSubscription.unsubscribe();

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

	void onConfirmNotUsCheckedChanged(boolean checked) {
		notUsResidentChecked = checked;
		updateSignUpButtonEnabled();
	}

	private void updateSignUpButtonEnabled() {
		getViewState().setSignUpButtonEnabled(privacyPolicyAccepted && termsConditionsAccepted && notUsResidentChecked);
	}

	void onSignInClicked() {
		getViewState().showLoginActivity();
	}

	//	void onSignUpClicked(String userName, String email, String password, String confirmPassword) {
	void onSignUpClicked(String email, String password, String confirmPassword) {
		if (privacyPolicyAccepted && termsConditionsAccepted && notUsResidentChecked) {
			getViewState().clearErrors();
			getViewState().showProgress(true);

//		registrationSubscription = getRegisterObservable(userName, email, password, confirmPassword)
			registrationSubscription = getRegisterObservable(null, email, password, confirmPassword)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::onRegisterResponse,
							this::onRegisterError);
		}
	}

	private Observable<Void> getRegisterObservable(String userName, String email, String password, String confirmPassword) {
		return (Constants.IS_INVESTOR)
				? authManager.registerInvestor(email, password, confirmPassword)
				: authManager.registerManager(userName, email, password, confirmPassword);
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