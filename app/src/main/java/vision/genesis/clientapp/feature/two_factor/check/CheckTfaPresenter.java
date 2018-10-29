package vision.genesis.clientapp.feature.two_factor.check;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.model.events.OnCheckTfaConfirmClickedEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaErrorEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaSuccessEvent;
import vision.genesis.clientapp.utils.Constants;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

@InjectViewState
public class CheckTfaPresenter extends MvpPresenter<CheckTfaView>
{
	private String code = "";

	private boolean useRecoveryCode = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onConfirmClicked() {
		getViewState().showProgress(true);
		EventBus.getDefault().post(new OnCheckTfaConfirmClickedEvent(code, useRecoveryCode));
	}

	public void onNumber(String number) {
		if (code.length() < Constants.TWO_FACTOR_CODE_LENGTH) {
			code += number;
			getViewState().setCode(code);
		}
		if (code.length() == Constants.TWO_FACTOR_CODE_LENGTH) {
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

	@Subscribe
	public void onEventMainThread(OnCheckTfaSuccessEvent event) {
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(OnCheckTfaErrorEvent event) {
		getViewState().showProgress(false);
		onLongBackspace();
		getViewState().showToastMessage(event.getError());
	}

	public void setUseRecoveryCode(boolean use) {
		useRecoveryCode = use;
	}
}
