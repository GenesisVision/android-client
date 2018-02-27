package vision.genesis.clientapp.utils;

import java.util.regex.Pattern;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

public class EthAddressValidator
{
	public static boolean isValid(String address) {
		return Pattern.matches("^0x[a-fA-F0-9]{40}$", address);
	}
}