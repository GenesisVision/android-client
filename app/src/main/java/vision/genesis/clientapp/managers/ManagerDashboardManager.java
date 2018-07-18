package vision.genesis.clientapp.managers;

import io.swagger.client.api.ManagerApi;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2018.
 */

public class ManagerDashboardManager
{
	private ManagerApi managerApi;

	public ManagerDashboardManager(ManagerApi managerApi) {
		this.managerApi = managerApi;
	}
}