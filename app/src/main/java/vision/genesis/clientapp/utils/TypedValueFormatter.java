package vision.genesis.clientapp.utils;

import android.util.TypedValue;

import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/07/2019.
 */
public class TypedValueFormatter
{
	public static int toDp(int value) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
				GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
	}
}
