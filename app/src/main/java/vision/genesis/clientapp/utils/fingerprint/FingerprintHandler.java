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

public class FingerprintHandler
{
	public interface FingerprintAuthListener
	{
		void onAuthenticationSucceeded();

		void onAuthenticationHelp();

		void onAuthenticationError(String message);

		void onAuthenticationFailed();
	}

	private final FingerprintAuthListener listener;

	private FingerprintManager.AuthenticationCallback authenticateListener = new FingerprintManager.AuthenticationCallback()
	{
		@Override
		public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
			update("Fingerprint Authentication succeeded.");
			listener.onAuthenticationSucceeded();
		}

		@Override
		public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
			update("Fingerprint Authentication help\n" + helpString);
			listener.onAuthenticationHelp();
			Toast.makeText(GenesisVisionApplication.INSTANCE, helpString, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onAuthenticationError(int errMsgId, CharSequence errString) {
			update("Fingerprint Authentication error\n" + errString);
			if (errMsgId != FingerprintManager.FINGERPRINT_ERROR_CANCELED) {
//			Toast.makeText(GenesisVisionApplication.INSTANCE, errString, Toast.LENGTH_LONG).show();
				listener.onAuthenticationError(errString.toString());
			}
		}

		@Override
		public void onAuthenticationFailed() {
			update("Fingerprint Authentication failed.");
			listener.onAuthenticationFailed();
		}
	};

	public FingerprintHandler(FingerprintAuthListener listener) {
		this.listener = listener;
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public CancellationSignal startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
		if (ActivityCompat.checkSelfPermission(GenesisVisionApplication.INSTANCE, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
			return null;
		}
		CancellationSignal cancellationSignal = new CancellationSignal();
		manager.authenticate(cryptoObject, cancellationSignal, 0, authenticateListener, null);
		return cancellationSignal;
	}


	public void update(String message) {
		Timber.d(message);
	}
}