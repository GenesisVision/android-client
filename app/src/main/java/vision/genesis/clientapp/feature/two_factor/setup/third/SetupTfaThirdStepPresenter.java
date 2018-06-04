package vision.genesis.clientapp.feature.two_factor.setup.third;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.model.events.SetupTfaConfirmButtonClickedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

@InjectViewState
public class SetupTfaThirdStepPresenter extends MvpPresenter<SetupTfaThirdStepView>
{
	private String password;

	private String code;

	public void onPasswordChanged(String password) {
		this.password = password;
		checkConfirmButtonAvailability();
	}

	public void onCodeChanged(String code) {
		this.code = code;
		checkConfirmButtonAvailability();
	}

	private void checkConfirmButtonAvailability() {
		getViewState().setConfirmButtonAvailability(!password.isEmpty() && !code.isEmpty());
	}

	public void onConfirmClicked() {
		EventBus.getDefault().post(new SetupTfaConfirmButtonClickedEvent(password, code));
	}
}