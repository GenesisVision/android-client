package vision.genesis.clientapp.feature.main.program.create.second;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.model.events.SetCreateProgramDataEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/07/2018.
 */

@InjectViewState
public class CreateProgramSecondStepPresenter extends MvpPresenter<CreateProgramSecondStepView>
{
	private String password = "";

	private String confirmPassword = "";

	private CreateProgramData createProgramData;

//	private BrokerTradeServer broker;

	private Integer leverage;

	private boolean confirmAlreadyEntering = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		EventBus.getDefault().register(this);
	}

	private void checkNextButtonAvailability() {
		if (createProgramData == null)
			return;
		boolean passwordOk = !password.isEmpty();
		boolean confirmPasswordOk = !confirmPassword.isEmpty() && confirmPassword.equals(password);
//		getViewState().setNextButtonAvailability(broker != null && leverage != null && passwordOk && confirmPasswordOk);
	}

	void onPasswordChanged(String password) {
		this.password = password;
		checkPasswords();
		checkNextButtonAvailability();
	}

	void onConfirmPasswordChanged(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		if (!confirmAlreadyEntering && !confirmPassword.isEmpty())
			confirmAlreadyEntering = true;
		checkPasswords();
		checkNextButtonAvailability();
	}

	private void checkPasswords() {
		if (confirmAlreadyEntering && !confirmPassword.equals(password))
			getViewState().setConfirmError(GenesisVisionApplication.INSTANCE.getString(R.string.passwords_should_match));
		else
			getViewState().setConfirmError(null);
	}

	void onNextButtonClicked() {
//		EventBus.getDefault().post(new OnCreateProgramSecondStepPassedEvent(broker, leverage, password));
	}

	public void onBrokerSelected(int position) {
//		broker = createProgramData.getBrokers().get(position);
//		getViewState().setLeverages(broker.getLeverages());
		checkNextButtonAvailability();
	}

	public void onLeverageSelected(int position) {
//		leverage = broker.getLeverages().get(position);
		checkNextButtonAvailability();
	}

	@Subscribe
	public void onEventMainThread(SetCreateProgramDataEvent event) {
		this.createProgramData = event.getCreateProgramData();
//		getViewState().setBrokers(createProgramData.getBrokers());
	}
}