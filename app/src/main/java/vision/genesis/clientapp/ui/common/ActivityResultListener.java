package vision.genesis.clientapp.ui.common;

import android.content.Intent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/07/2018.
 */
public interface ActivityResultListener
{
	void onResultPassedFromActivity(int requestCode, int resultCode, Intent data);
}
