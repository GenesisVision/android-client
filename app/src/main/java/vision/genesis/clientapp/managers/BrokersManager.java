package vision.genesis.clientapp.managers;


import java.util.UUID;

import io.swagger.client.api.BrokersApi;
import io.swagger.client.api.ExchangesApi;
import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.BrokersProgramInfo;
import io.swagger.client.model.ExchangeInfoItemsViewModel;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class BrokersManager
{
	private BrokersApi brokersApi;

	private ExchangesApi exchangesApi;

	public BrokersManager(BrokersApi brokersApi, ExchangesApi exchangesApi) {
		this.brokersApi = brokersApi;
		this.exchangesApi = exchangesApi;
	}

	public Observable<BrokersInfo> getAllBrokers() {
		return brokersApi.getBrokers();
	}

	public Observable<ExchangeInfoItemsViewModel> getExchanges() {
		return exchangesApi.getExchanges();
	}

	public Observable<BrokersInfo> getExternalBrokers() {
		return brokersApi.getBrokersExternal();
	}

	public Observable<BrokersProgramInfo> getBrokersForProgram(UUID programId) {
		return brokersApi.getBrokersForProgram(programId);
	}
}