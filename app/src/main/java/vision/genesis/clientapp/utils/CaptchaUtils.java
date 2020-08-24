package vision.genesis.clientapp.utils;

import io.swagger.client.model.CaptchaCheckResult;
import io.swagger.client.model.CaptchaDetails;
import io.swagger.client.model.PowResult;
import vision.genesis.clientapp.utils.hash.HashGenerationException;
import vision.genesis.clientapp.utils.hash.HashGeneratorUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/04/2019.
 */
public class CaptchaUtils
{
	public static CaptchaCheckResult findPowPrefix(CaptchaDetails captchaDetails) {
		Integer prefix = 0;
		String str;
		String hash;
		String diffString = "";

		for (int i = 0; i < 64; i++) {
			if (i < captchaDetails.getPow().getDifficulty()) {
				diffString = diffString.concat("0");
			}
			else {
				diffString = diffString.concat("f");
			}
		}
		try {
			while (true) {
				str = prefix.toString().concat(captchaDetails.getPow().getNonce()).concat(captchaDetails.getRoute() != null ? captchaDetails.getRoute() : "");
				hash = HashGeneratorUtil.generateSHA256(str);
				if (hash.compareTo(diffString) < 0) {
					break;
				}

				prefix++;
			}

			CaptchaCheckResult captchaCheckResult = new CaptchaCheckResult();
			captchaCheckResult.setId(captchaDetails.getId().toString());
			PowResult powResult = new PowResult();
			powResult.setPrefix(prefix.toString());
			captchaCheckResult.setPow(powResult);
			return captchaCheckResult;
		} catch (HashGenerationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
