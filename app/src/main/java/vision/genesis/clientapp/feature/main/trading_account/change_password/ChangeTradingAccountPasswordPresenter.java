package vision.genesis.clientapp.feature.main.trading_account.change_password;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.TradingAccountPwdUpdate;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ChangeTradingAccountPasswordPresenter extends MvpPresenter<ChangeTradingAccountPasswordView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription sendCodeSubscription;

	private UUID accountId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (sendCodeSubscription != null) {
			sendCodeSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID accountId) {
		this.accountId = accountId;
	}

	void onChangePasswordClicked(String newPassword, String repeatPassword) {
		boolean hasErrors = false;
		getViewState().clearErrors();

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

		if (!hasErrors) {
			sendChangePassword(newPassword);
		}
	}

	private void sendChangePassword(String newPassword) {
		if (assetsManager != null && accountId != null) {
			getViewState().showProgressBar(true);

			TradingAccountPwdUpdate request = new TradingAccountPwdUpdate();
			request.setPassword(newPassword);
			sendCodeSubscription = assetsManager.changeTradingAccountPassword(accountId, request)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleChangePasswordResponse(),
							this::handleChangePasswordError);
		}
	}

	private void handleChangePasswordResponse() {
		sendCodeSubscription.unsubscribe();
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
