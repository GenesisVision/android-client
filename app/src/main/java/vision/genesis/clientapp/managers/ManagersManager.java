package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.ManagerApi;
import io.swagger.client.model.ManagerProfileDetails;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class ManagersManager
{
	private ManagerApi managerApi;

	public ManagersManager(ManagerApi managerApi) {
		this.managerApi = managerApi;
	}

	public Observable<ManagerProfileDetails> getManagerDetails(UUID managerId) {
		return managerApi.v10ManagerByIdDetailsGet(managerId.toString());
	}
}