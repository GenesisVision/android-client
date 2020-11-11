package vision.genesis.clientapp.feature.main.verification;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/11/2020.
 */

interface VerificationInfoView extends MvpView
{
	void setVerifyButtonVisible(boolean visible);

	void setVerifyButtonEnabled(boolean enabled);

	void setVerifyButtonText(String text);
}
