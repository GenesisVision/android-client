package vision.genesis.clientapp.feature.auth.registration;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.ShowMessageActivityEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

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

	void onSignInClicked() {
		getViewState().showLoginActivity();
	}

	void onSignUpClicked(String email, String password, String confirmPassword) {
		getViewState().clearErrors();
		getViewState().showProgress();
		registrationSubscription = authManager.registerInvestor(email, password, confirmPassword)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onRegisterResponse,
						this::onRegisterError);
	}

	private void onRegisterResponse(Void response) {
		registrationSubscription.unsubscribe();

		EventBus.getDefault().post(new ShowMessageActivityEvent(context.getString(R.string.email_verification_text), R.drawable.ic_email_white_64dp, true));
		getViewState().finishActivity();
	}

	private void onRegisterError(Throwable throwable) {
		registrationSubscription.unsubscribe();
		getViewState().hideProgress();

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