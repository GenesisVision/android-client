package vision.genesis.clientapp.managers;


import io.swagger.client.api.AssetsApi;
import io.swagger.client.model.NewFundRequest;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/10/2019.
 */

public class AssetsManager
{
	private AssetsApi assetsApi;

	public AssetsManager(AssetsApi assetsApi) {
		this.assetsApi = assetsApi;
	}

	public Observable<Void> createFund(NewFundRequest request) {
		return assetsApi.createFund(AuthManager.token.getValue(), request);
	}
}