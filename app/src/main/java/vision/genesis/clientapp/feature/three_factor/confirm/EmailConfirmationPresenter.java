package vision.genesis.clientapp.feature.three_factor.confirm;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.ThreeFactorAuthenticatorConfirm;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.events.OnEmailVerificationSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/01/2020.
 */

@InjectViewState
public class EmailConfirmationPresenter extends MvpPresenter<EmailConfirmationView>
{
	@Inject
	public AuthManager authManager;

	private Subscription emailConfirmationSubscription;

	private String code = "";

	private String email;

	private String tempToken;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (emailConfirmationSubscription != null) {
			emailConfirmationSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(String email, String tempToken) {
		this.email = email;
		this.tempToken = tempToken;
	}

	void onConfirmClicked() {
		if (authManager != null && email != null && tempToken != null
				&& code != null && code.length() == Constants.THREE_FACTOR_CODE_LENGTH) {
			getViewState().showProgress(true);

			ThreeFactorAuthenticatorConfirm request = new ThreeFactorAuthenticatorConfirm();
			request.setEmail(email);
			request.setToken(tempToken);
			request.setCode(code);

			emailConfirmationSubscription = authManager.confirmThreeStepAuth(request)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::onConfirmSuccess,
							this::onConfirmError);
		}
	}

	private void onConfirmSuccess(String token) {
		emailConfirmationSubscription.unsubscribe();

		EventBus.getDefault().post(new OnEmailVerificationSuccessEvent(token));
		getViewState().finishActivity();
	}

	private void onConfirmError(Throwable throwable) {
		emailConfirmationSubscription.unsubscribe();
		getViewState().showProgress(false);

		onLongBackspace();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	public void onNumber(String number) {
		if (code.length() < Constants.THREE_FACTOR_CODE_LENGTH) {
			code += number;
			getViewState().setCode(code);
		}
		if (code.length() == Constants.THREE_FACTOR_CODE_LENGTH) {
			getViewState().setKeyboardKeysEnabled(false);
			onConfirmClicked();
		}
	}

	public void onBackspace() {
		if (code.length() > 0) {
			code = code.substring(0, code.length() - 1);
			getViewState().setCode(code);
			getViewState().setKeyboardKeysEnabled(true);
		}
	}

	public void onLongBackspace() {
		code = "";
		getViewState().setCode(code);
		getViewState().setKeyboardKeysEnabled(true);
	}
}
