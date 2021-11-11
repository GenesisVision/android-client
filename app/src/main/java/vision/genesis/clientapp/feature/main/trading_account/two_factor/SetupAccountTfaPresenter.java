package vision.genesis.clientapp.feature.main.trading_account.two_factor;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.TwoFactorAuthenticator;
import io.swagger.client.model.TwoFactorCodeModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2021.
 */

@InjectViewState
public class SetupAccountTfaPresenter extends MvpPresenter<SetupAccountTfaView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription getTfaKeySubscription;

	private Subscription confirmTfaKeySubscription;

	private UUID id;

	private String code = "";

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getTfaKey();
	}

	@Override
	public void onDestroy() {
		if (getTfaKeySubscription != null) {
			getTfaKeySubscription.unsubscribe();
		}
		if (confirmTfaKeySubscription != null) {
			confirmTfaKeySubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID id) {
		this.id = id;
		getTfaKey();
	}

	void onActivateClicked() {
		confirmTfa(code);
	}

	void onCodeChanged(String code) {
		this.code = code;
		checkActivateButtonAvailability();
	}

	private void getTfaKey() {
		if (id != null && assetsManager != null) {
			if (getTfaKeySubscription != null) {
				getTfaKeySubscription.unsubscribe();
			}
			getViewState().showProgress(true);
			getTfaKeySubscription = assetsManager.getProgram2fa(id)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetTfaKeySuccess,
							this::handleGetTfaKeyError);
		}
	}

	private void handleGetTfaKeySuccess(TwoFactorAuthenticator response) {
		getTfaKeySubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setKey(response.getSharedKey(), response.getAuthenticatorUri());
	}

	private void handleGetTfaKeyError(Throwable throwable) {
		getTfaKeySubscription.unsubscribe();
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbar(message));
	}

	private void confirmTfa(String code) {
		if (id != null && assetsManager != null) {
			getViewState().showButtonProgress(true);
			if (confirmTfaKeySubscription != null) {
				confirmTfaKeySubscription.unsubscribe();
			}

			TwoFactorCodeModel model = new TwoFactorCodeModel();
			model.setTwoFactorCode(code);
			confirmTfaKeySubscription = assetsManager.confirmProgram2fa(id, model)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleConfirmTfaKeySuccess,
							this::handleConfirmTfaKeyError);
		}
	}

	private void handleConfirmTfaKeySuccess(Void response) {
		confirmTfaKeySubscription.unsubscribe();

		getViewState().finishActivity();
	}

	private void handleConfirmTfaKeyError(Throwable throwable) {
		getTfaKeySubscription.unsubscribe();
		getViewState().showButtonProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbar(message));
	}

	private void checkActivateButtonAvailability() {
		getViewState().setActivateButtonAvailability(code.length() == Constants.TWO_FACTOR_CODE_LENGTH);
	}
}