package vision.genesis.clientapp.feature.main.external.attach.api_key;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.model.events.OnExternalApiKeyConfirmEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */

@InjectViewState
public class ExternalApiKeyPresenter extends MvpPresenter<ExternalApiKeyView>
{
	private String apiKey = "";

	private String apiSecret = "";

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
	}

	void onApiKeyChanged(String apiKey) {
		this.apiKey = apiKey.trim();

		updateConfirmButtonAvailability();
	}

	void onApiSecretChanged(String apiSecret) {
		this.apiSecret = apiSecret.trim();

		updateConfirmButtonAvailability();
	}


	void onConfirmClicked() {
		EventBus.getDefault().post(new OnExternalApiKeyConfirmEvent(apiKey, apiSecret));
	}

	private void updateConfirmButtonAvailability() {
		getViewState().setConfirmButtonEnabled(!apiKey.isEmpty() && !apiSecret.isEmpty());
	}

}
