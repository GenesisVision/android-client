package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.SignalApi;
import io.swagger.client.model.AttachToSignalProviderInfo;
import rx.Observable;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

public class SignalsManager
{
	private SignalApi signalApi;

	public SignalsManager(SignalApi signalApi) {
		this.signalApi = signalApi;
	}

	public Observable<AttachToSignalProviderInfo> getSignalsInfo(UUID programId) {
		return signalApi.v10SignalAttachByIdInfoGet(programId, AuthManager.token.getValue());
	}

	public Observable<Void> subscribeToProgram(SubscriptionSettingsModel model) {
		return signalApi.v10SignalAttachByIdPost(model.getProgramId(), AuthManager.token.getValue(), model.getApiModel());
	}

	public Observable<Void> updateSubscription(SubscriptionSettingsModel model) {
		return signalApi.v10SignalByIdUpdatePost(model.getProgramId(), AuthManager.token.getValue(), model.getApiModel());
	}
}