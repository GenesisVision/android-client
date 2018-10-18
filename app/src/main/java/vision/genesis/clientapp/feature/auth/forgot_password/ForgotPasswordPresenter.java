package vision.genesis.clientapp.feature.auth.forgot_password;

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

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (forgotPasswordSubscription != null)
			forgotPasswordSubscription.unsubscribe();

		super.onDestroy();
	}

	void onEmailChanged(String email) {
		getViewState().setButtonEnabled(ValidatorUtil.isEmailValid(email));
	}

	void onResetPasswordClicked(String email) {
		email = email.trim();
		if (ValidatorUtil.isEmailValid(email))
			sendForgotPassword(email);
		else
			getViewState().setEmailError(context.getString(R.string.enter_valid_email));
	}

	private void sendForgotPassword(String email) {
		getViewState().showProgressBar(true);
		forgotPasswordSubscription = authManager.sendForgotPassword(email)
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
