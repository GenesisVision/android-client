package vision.genesis.clientapp.feature.main.wallet.deposit;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.android.QRCode;

import javax.inject.Inject;

import io.swagger.client.model.WalletAddressViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.WalletManager;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

@InjectViewState
public class DepositWalletPresenter extends MvpPresenter<DepositWalletView>
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription getWalletAddressSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getWalletAddress();
	}

	@Override
	public void onDestroy() {
		if (getWalletAddressSubscription != null)
			getWalletAddressSubscription.unsubscribe();

		super.onDestroy();
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	private void getWalletAddress() {
		getViewState().showProgress(true);
		getWalletAddressSubscription = walletManager.getWalletAddress()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetWalletAddressSuccess,
						this::handleGetWalletAddressError);
	}

	private void handleGetWalletAddressSuccess(WalletAddressViewModel response) {
		getViewState().showProgress(false);
		String walletAddress = response.getAddress();

		getViewState().setWalletAddress(walletAddress);
		getViewState().setWalletQrCode(QRCode.from(walletAddress)
				.withColor(0xFF000000, ContextCompat.getColor(context, R.color.colorGrayBackground))
				.withSize(1000, 1000)
				.withErrorCorrection(ErrorCorrectionLevel.H)
				.bitmap());
	}

	private void handleGetWalletAddressError(Throwable throwable) {
		getViewState().finishActivity();
	}
}
