package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/03/2018.
 */

public class ShortenedAmount
{
	public String amount = "";

	public String modifier = "";

	@Override
	public String toString() {
		return amount.concat(modifier);
	}
}
