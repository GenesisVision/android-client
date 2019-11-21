package vision.genesis.clientapp.managers;


import java.util.UUID;

import io.swagger.client.api.BrokersApi;
import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.BrokersProgramInfo;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class BrokersManager
{
	private BrokersApi brokersApi;

	public BrokersManager(BrokersApi brokersApi) {
		this.brokersApi = brokersApi;
	}

	public Observable<BrokersInfo> getBrokers() {
		return brokersApi.getBrokers();
	}

	public Observable<BrokersInfo> getExternalBrokers() {
		return brokersApi.getBrokersExternal();
	}

	public Observable<BrokersProgramInfo> getBrokersForProgram(UUID programId) {
		return brokersApi.getBrokersForProgram(programId);
	}
}