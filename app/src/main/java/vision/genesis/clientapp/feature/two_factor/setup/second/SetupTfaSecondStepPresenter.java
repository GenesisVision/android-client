package vision.genesis.clientapp.feature.two_factor.setup.second;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.android.QRCode;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */

@InjectViewState
public class SetupTfaSecondStepPresenter extends MvpPresenter<SetupTfaSecondStepView>
{
	void onSetKey(Context context, String sharedKey, String authenticatorUri) {
		getViewState().setKey(sharedKey);
		getViewState().setQrCode(QRCode.from(authenticatorUri)
				.withColor(ThemeUtil.getColorByAttrId(context, R.attr.colorBackground),
						ThemeUtil.getColorByAttrId(context, R.attr.colorTextPrimary))
				.withSize(1000, 1000)
				.withErrorCorrection(ErrorCorrectionLevel.H)
				.bitmap());
	}
}