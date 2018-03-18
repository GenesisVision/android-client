package vision.genesis.clientapp.feature.auth.login;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

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

	private Subscription loginSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (loginSubscription != null)
			loginSubscription.unsubscribe();
	}

	void onSignUpClicked() {
		getViewState().showRegistrationActivity();
	}

	void onSignInClicked(String email, String password) {
		getViewState().clearErrors();
		getViewState().showProgress();
		loginSubscription = authManager.login(email, password)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onLoginResponse,
						this::onLoginError);
	}

	private void onLoginResponse(String response) {
		loginSubscription.unsubscribe();

		getViewState().finishActivity();
	}

	private void onLoginError(Throwable throwable) {
		loginSubscription.unsubscribe();
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
