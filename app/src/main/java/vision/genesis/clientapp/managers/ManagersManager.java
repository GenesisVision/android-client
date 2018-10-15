package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.ManagersApi;
import io.swagger.client.model.ManagerProfileDetails;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class ManagersManager
{
	private ManagersApi managerApi;

	public ManagersManager(ManagersApi managerApi) {
		this.managerApi = managerApi;
	}

	public Observable<ManagerProfileDetails> getManagerDetails(UUID managerId) {
		return managerApi.v10ManagersByIdDetailsGet(managerId.toString());
	}
}