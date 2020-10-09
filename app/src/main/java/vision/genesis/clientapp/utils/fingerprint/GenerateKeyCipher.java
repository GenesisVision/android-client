package vision.genesis.clientapp.utils.fingerprint;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import timber.log.Timber;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/06/2018.
 */

public class GenerateKeyCipher
{
	private String KEY_NAME = "genesis_vision_fp";

	private Cipher cipher;

	private KeyStore keyStore;

	@RequiresApi(Build.VERSION_CODES.M)
	public boolean generateKey() {
		try {
			keyStore = KeyStore.getInstance("AndroidKeyStore");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//			throw new RuntimeException("Failed to get KeyGenerator instance", e);
			e.printStackTrace();
			return false;
		}

		try {
			keyStore.load(null);
			keyGenerator.init(new
					KeyGenParameterSpec.Builder(KEY_NAME,
					KeyProperties.PURPOSE_ENCRYPT |
							KeyProperties.PURPOSE_DECRYPT)
					.setBlockModes(KeyProperties.BLOCK_MODE_CBC)
					.setUserAuthenticationRequired(true)
					.setEncryptionPaddings(
							KeyProperties.ENCRYPTION_PADDING_PKCS7)
					.build());
			keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException |
				InvalidAlgorithmParameterException
				| CertificateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@TargetApi(Build.VERSION_CODES.M)
	public boolean cipherInit() {
		try {
			keyStore = KeyStore.getInstance("AndroidKeyStore");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			Timber.e("Failed to get Cipher");
//			Crashlytics.log(String.format(Locale.getDefault(), "Failed to get Cipher\n%s", e.toString()));
			e.printStackTrace();
			return false;
		}

		try {
			keyStore.load(null);
			SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return true;
		} catch (KeyPermanentlyInvalidatedException e) {
			Timber.e("KeyPermanentlyInvalidatedException");
			e.printStackTrace();
			return false;
		} catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
			Timber.e("Failed to init Cipher");
//			Crashlytics.log(String.format(Locale.getDefault(), "Failed to init Cipher\n%s", e.toString()));
			e.printStackTrace();
			return false;
		}
	}

	public Cipher getCipher() {
		return cipher;
	}
}