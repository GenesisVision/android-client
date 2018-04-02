package vision.genesis.clientapp.utils;

import android.text.TextUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/04/2018.
 */
public class ValidatorUtil
{
	public static boolean isEmailValid(String email) {
		return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
}
