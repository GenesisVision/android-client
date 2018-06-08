package vision.genesis.clientapp.feature.two_factor.setup.second;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

public interface SetupTfaSecondStepView extends MvpView
{
	void setKey(String sharedKey);

	void setQrCode(Bitmap bitmap);
}
