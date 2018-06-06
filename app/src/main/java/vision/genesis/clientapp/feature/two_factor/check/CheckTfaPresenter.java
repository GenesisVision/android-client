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
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

@InjectViewState
public class CheckTfaPresenter extends MvpPresenter<CheckTfaView>
{
	private String code = "";

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

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void onConfirmClicked() {
		getViewState().showProgress(true);
		EventBus.getDefault().post(new OnCheckTfaConfirmClickedEvent(code));
	}

	public void onNumber(String number) {
		if (code.length() < Constants.TWO_FACTOR_CODE_LENGTH) {
			code += number;
			getViewState().setCode(code);
		}
		if (code.length() == Constants.TWO_FACTOR_CODE_LENGTH) {
			getViewState().setKeyboardKeysEnabled(false);
			getViewState().setConfirmButtonEnabled(true);
			onConfirmClicked();
		}
	}

	public void onBackspace() {
		if (code.length() > 0) {
			code = code.substring(0, code.length() - 1);
			getViewState().setCode(code);
			getViewState().setKeyboardKeysEnabled(true);
			getViewState().setConfirmButtonEnabled(false);
		}
	}

	public void onLongBackspace() {
		code = "";
		getViewState().setCode(code);
		getViewState().setKeyboardKeysEnabled(true);
		getViewState().setConfirmButtonEnabled(false);
	}

	@Subscribe
	public void onEventMainThread(OnCheckTfaSuccessEvent event) {
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(OnCheckTfaErrorEvent event) {
		getViewState().showProgress(false);
		getViewState().showToastMessage(event.getError());
	}
}
