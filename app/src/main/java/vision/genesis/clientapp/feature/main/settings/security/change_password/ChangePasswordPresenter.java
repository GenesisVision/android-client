package vision.genesis.clientapp.feature.main.settings.security.change_password;

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
 * Created by Vitaly on 4/2/18.
 */

@InjectViewState
public class ChangePasswordPresenter extends MvpPresenter<ChangePasswordView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription sendCodeSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (sendCodeSubscription != null)
			sendCodeSubscription.unsubscribe();

		super.onDestroy();
	}

	void onChangePasswordClicked(String oldPassword, String newPassword, String repeatPassword) {
		boolean hasErrors = false;
		getViewState().clearErrors();

		if (oldPassword.trim().isEmpty()) {
			getViewState().setOldPasswordError(context.getString(R.string.enter_your_current_password));
			hasErrors = true;
		}
		if (newPassword.trim().isEmpty()) {
			getViewState().setNewPasswordError(context.getString(R.string.enter_your_new_password));
			hasErrors = true;
		}
		if (repeatPassword.trim().isEmpty()) {
			getViewState().setRepeatPasswordError(context.getString(R.string.repeat_your_new_password));
			hasErrors = true;
		}
		else if (!newPassword.equals(repeatPassword)) {
			getViewState().setNewPasswordError(context.getString(R.string.passwords_should_match));
			getViewState().setRepeatPasswordError(context.getString(R.string.passwords_should_match));
			hasErrors = true;
		}

		if (!hasErrors)
			sendChangePassword(oldPassword, newPassword, repeatPassword);
	}

	private void sendChangePassword(String oldPassword, String newPassword, String confirmPassword) {
		getViewState().showProgressBar(true);
		sendCodeSubscription = authManager.sendChangePassword(oldPassword, newPassword, confirmPassword)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleChangePasswordResponse,
						this::handleChangePasswordError);
	}

	private void handleChangePasswordResponse(String newToken) {
		sendCodeSubscription.unsubscribe();
		authManager.saveNewToken(newToken);
		getViewState().showMessageDialog(R.drawable.image_ok,
				context.getString(R.string.password_changed),
				"",
				false,
				() -> getViewState().finishActivity());
	}

	private void handleChangePasswordError(Throwable throwable) {
		sendCodeSubscription.unsubscribe();
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
