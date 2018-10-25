package vision.genesis.clientapp.feature.main.wallet.deposit;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.WalletInfo;
import io.swagger.client.model.WalletsInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

@InjectViewState
public class DepositWalletPresenter extends MvpPresenter<DepositWalletView> implements SelectOptionBottomSheetFragment.OnOptionSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	private Subscription getWalletAddressesSubscription;

	private List<WalletInfo> wallets;

	private WalletInfo selectedWallet;

	private Double amount = 0.0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getWalletAddresses();
	}

	@Override
	public void onDestroy() {
		if (getWalletAddressesSubscription != null)
			getWalletAddressesSubscription.unsubscribe();

		super.onDestroy();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		updateAmountBase();
	}

	private void updateAmountBase() {
		if (selectedWallet != null)
			getViewState().setAmountBase(getAmountBaseString());
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "%s %s %s",
				selectedWallet.getCurrency().getValue().equals(CurrencyEnum.GVT.getValue())
						? context.getString(R.string.you_will_get)
						: context.getString(R.string.approximate_amount),
				StringFormatUtil.formatCurrencyAmount(amount * selectedWallet.getRateToGVT(), selectedWallet.getCurrency().getValue()),
				selectedWallet.getCurrency().getValue());
	}

	private void getWalletAddresses() {
		getViewState().showProgress(true);
		getWalletAddressesSubscription = walletManager.getWalletAddress()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetWalletAddressesSuccess,
						this::handleGetWalletAddressesError);
	}

	private void handleGetWalletAddressesSuccess(WalletsInfo response) {
		getWalletAddressesSubscription.unsubscribe();
		getViewState().showProgress(false);

		wallets = response.getWallets();

		ArrayList<String> walletsOptions = new ArrayList<>();
		for (WalletInfo wallet : wallets) {
			walletsOptions.add(String.format(Locale.getDefault(), "%s (%s)",
					wallet.getDescription(), wallet.getCurrency().getValue()));
		}
		getViewState().setWalletsOptions(walletsOptions);
		onOptionSelected(0, walletsOptions.get(0));
	}

	private void handleGetWalletAddressesError(Throwable throwable) {
		getWalletAddressesSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onOptionSelected(Integer position, String text) {
		this.selectedWallet = wallets.get(position);
		getViewState().setWalletInfo(selectedWallet, text, position);
		updateAmountBase();
	}
}
