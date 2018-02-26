package vision.genesis.clientapp.feature.main.wallet.deposit;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

interface DepositWalletView extends MvpView
{
	void setWalletAddress(String address);

	void setWalletQrCode(Bitmap image);

	void showProgress(boolean show);

	void finishActivity();
}
