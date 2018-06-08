package vision.genesis.clientapp.feature.two_factor.setup.second;

import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.android.QRCode;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

@InjectViewState
public class SetupTfaSecondStepPresenter extends MvpPresenter<SetupTfaSecondStepView>
{
	public void onSetKey(String sharedKey, String authenticatorUri) {
		getViewState().setKey(sharedKey);
		getViewState().setQrCode(QRCode.from(authenticatorUri)
				.withColor(0xFF000000, Color.WHITE)
				.withSize(1000, 1000)
				.withErrorCorrection(ErrorCorrectionLevel.H)
				.bitmap());
	}
}