package vision.genesis.clientapp.feature.two_factor.setup.first;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

@InjectViewState
public class SetupTfaFirstStepPresenter extends MvpPresenter<SetupTfaFirstStepView>
{
	private String sharedKey;

	public void onSetKey(String sharedKey) {
		this.sharedKey = sharedKey;
		getViewState().setKey(sharedKey);
	}
}