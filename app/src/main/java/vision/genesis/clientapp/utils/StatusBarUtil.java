package vision.genesis.clientapp.utils;

import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2018.
 */
public class StatusBarUtil
{
	public static void setColor(Activity activity, int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, color));
		}
	}
}
