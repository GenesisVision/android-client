package vision.genesis.clientapp.navigation;

import android.support.v4.app.Fragment;

/**
 * GenesisVision
 * Created by Vitaly on 1/30/18.
 */

public interface ScreenResolver
{
	public Fragment getFragment(String screenKey, Object data);
}
