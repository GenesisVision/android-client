package vision.genesis.clientapp.utils.hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */
public class HashGeneratorUtil
{
	public static String generateMD5(String message) throws HashGenerationException {
		return hashString(message, "MD5");
	}

	public static String generateSHA1(String message) throws HashGenerationException {
		return hashString(message, "SHA-1");
	}

	public static String generateSHA256(String message) throws HashGenerationException {
		return hashString(message, "SHA-256");
	}

	private static String hashString(String message, String algorithm)
			throws HashGenerationException {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new HashGenerationException(
					"Could not generate hash from String", ex);
		}
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte arrayByte : arrayBytes) {
			stringBuilder.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
					.substring(1));
		}
		return stringBuilder.toString();
	}
}
