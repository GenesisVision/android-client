package vision.genesis.clientapp.feature.two_factor.setup.first;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public interface SetupTfaFirstStepView extends MvpView
{
	void setKey(String sharedKey);
}
