package vision.genesis.clientapp.navigation;

import java.util.HashMap;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

/**
 * GenesisVision
 * Created by Vitaly on 1/30/18.
 */

public class LocalCiceroneHolder
{
	private HashMap<String, Cicerone<Router>> containers;

	public LocalCiceroneHolder() {
		containers = new HashMap<>();
	}

	public Cicerone<Router> getCicerone(String containerTag) {
		if (!containers.containsKey(containerTag)) {
			containers.put(containerTag, Cicerone.create());
		}
		return containers.get(containerTag);
	}
}
