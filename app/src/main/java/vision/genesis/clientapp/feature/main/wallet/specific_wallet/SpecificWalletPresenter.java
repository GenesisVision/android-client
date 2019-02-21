package vision.genesis.clientapp.feature.main.wallet.specific_wallet;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/02/2019.
 */

@InjectViewState
public class SpecificWalletPresenter extends MvpPresenter<SpecificWalletView>
{
	@Inject
	public SettingsManager settingsManager;

	@Inject
	public WalletManager walletManager;

	private UUID walletId;

	private Subscription baseCurrencySubscription;

	private Subscription walletSubscription;

	private CurrencyEnum baseCurrency;

	private WalletData walletData;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null)
			baseCurrencySubscription.unsubscribe();
		if (walletSubscription != null)
			walletSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		subscribeToWallet();
	}

	void setWalletId(UUID walletId) {
		this.walletId = walletId;
		subscribeToWallet();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().showProgress(true);
		subscribeToWallet();
	}

	private void subscribeToWallet() {
		if (walletManager != null && walletId != null && baseCurrency != null) {
			if (walletSubscription != null)
				walletSubscription.unsubscribe();
			walletSubscription = walletManager.getWallets(baseCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleWalletUpdateSuccess(response, walletId),
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletMultiSummary response, UUID walletId) {
		for (WalletData walletData : response.getWallets()) {
			if (walletData.getId().equals(walletId)) {
				this.walletData = walletData;
				getViewState().showProgress(false);
				getViewState().setRefreshing(false);
				getViewState().setWalletData(walletData);
				break;
			}
		}
	}

	private void handleWalletUpdateError(Throwable error) {

	}
}
