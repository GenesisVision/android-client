package vision.genesis.clientapp.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/04/2018.
 */
public class ValidatorUtil
{
	public static boolean isEmailValid(String email) {
		return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}

	public static boolean isEthAddressValid(String address) {
		return Pattern.matches("^0x[a-fA-F0-9]{40}$", address);
	}
}
