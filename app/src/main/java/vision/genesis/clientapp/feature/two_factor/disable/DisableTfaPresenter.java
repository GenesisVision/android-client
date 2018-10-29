package vision.genesis.clientapp.feature.two_factor.disable;

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
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

@InjectViewState
public class DisableTfaPresenter extends MvpPresenter<DisableTfaView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription disableTfaSubscription;

	private String password;

	private String code;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (disableTfaSubscription != null)
			disableTfaSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onPasswordChanged(String password) {
		this.password = password;
		checkDisableButtonAvailability();
	}

	void onCodeChanged(String code) {
		this.code = code;
		checkDisableButtonAvailability();
	}

	private void checkDisableButtonAvailability() {
		getViewState().setDisableButtonAvailability(!password.isEmpty() && code.length() == Constants.TWO_FACTOR_CODE_LENGTH);
	}

	void onDisableClicked() {
		disableTfa(password, code);
	}

	private void disableTfa(String password, String code) {
		getViewState().showProgress(true);
		if (disableTfaSubscription != null)
			disableTfaSubscription.unsubscribe();
		disableTfaSubscription = authManager.disableTfa(password, code)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleDisableTfaSuccess,
						this::handleDisableTfaError);
	}

	private void handleDisableTfaSuccess(Void response) {
		disableTfaSubscription.unsubscribe();

		authManager.setTwoFactorStatus(false);

		getViewState().showMessageDialog(R.drawable.image_ok,
				context.getString(R.string.tfa_disable_success_title),
				context.getString(R.string.tfa_disable_success),
				true,
				() -> getViewState().finishActivity());
	}

	private void handleDisableTfaError(Throwable throwable) {
		disableTfaSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbar(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null && response.errors != null && response.errors.get(0) != null) {
				getViewState().showSnackbar(response.errors.get(0).message);
			}
		}
	}
}