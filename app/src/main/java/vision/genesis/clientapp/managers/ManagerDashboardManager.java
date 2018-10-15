package vision.genesis.clientapp.managers;

import io.swagger.client.api.ManagersApi;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class ManagerDashboardManager
{
	private ManagersApi managerApi;

	public ManagerDashboardManager(ManagersApi managerApi) {
		this.managerApi = managerApi;
	}

}