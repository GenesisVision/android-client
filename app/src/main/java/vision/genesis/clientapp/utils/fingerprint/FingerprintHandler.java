package vision.genesis.clientapp.utils.fingerprint;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/06/2018.
 */

@RequiresApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback
{
	public interface FingerprintAuthListener
	{
		void onAuthenticationSucceeded();

		void onAuthenticationHelp();

		void onAuthenticationError(String message);

		void onAuthenticationFailed();
	}

	private final FingerprintAuthListener listener;

	public FingerprintHandler(FingerprintAuthListener listener) {
		this.listener = listener;
	}

	public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
		if (ActivityCompat.checkSelfPermission(GenesisVisionApplication.INSTANCE, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		manager.authenticate(cryptoObject, new CancellationSignal(), 0, this, null);
	}

	@Override
	public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
		this.update("Fingerprint Authentication succeeded.");
		listener.onAuthenticationSucceeded();
	}

	@Override
	public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
		this.update("Fingerprint Authentication help\n" + helpString);
		listener.onAuthenticationHelp();
		Toast.makeText(GenesisVisionApplication.INSTANCE, helpString, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onAuthenticationError(int errMsgId, CharSequence errString) {
		this.update("Fingerprint Authentication error\n" + errString);
		if (errMsgId != FingerprintManager.FINGERPRINT_ERROR_CANCELED) {
//			Toast.makeText(GenesisVisionApplication.INSTANCE, errString, Toast.LENGTH_LONG).show();
			listener.onAuthenticationError(errString.toString());
		}
	}

	@Override
	public void onAuthenticationFailed() {
		this.update("Fingerprint Authentication failed.");
		listener.onAuthenticationFailed();
	}

	public void update(String message) {
		Timber.d(message);
	}
}