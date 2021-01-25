package vision.genesis.clientapp.feature.main.wallet.specific_wallet;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.events.OnCardDepositFinishedEvent;
import vision.genesis.clientapp.model.events.SetSpecificWalletDepositsWithdrawalsCountEvent;
import vision.genesis.clientapp.model.events.SetSpecificWalletTransactionsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

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

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (walletSubscription != null) {
			walletSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		getWalletDetails();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getWalletDetails();
	}

	void setWalletId(UUID walletId) {
		this.walletId = walletId;
		getWalletDetails();
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
		getWalletDetails();
	}

	private void getWalletDetails() {
		if (walletManager != null && walletId != null && baseCurrency != null) {
			if (walletSubscription != null) {
				walletSubscription.unsubscribe();
			}
			walletSubscription = walletManager.getWallets(baseCurrency.getValue(), true)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleWalletUpdateSuccess(response, walletId),
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletSummary response, UUID walletId) {
		walletSubscription.unsubscribe();

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

	private void handleWalletUpdateError(Throwable throwable) {
		walletSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(SetSpecificWalletTransactionsCountEvent event) {
		getViewState().setTransactionsCount(event.getTransactionsCount());
	}

	@Subscribe
	public void onEventMainThread(SetSpecificWalletDepositsWithdrawalsCountEvent event) {
		getViewState().setDepositsWithdrawalsCount(event.getDepositsWithdrawalsCount());
	}

	@Subscribe
	public void onEventMainThread(OnCardDepositFinishedEvent event) {
		getWalletDetails();
	}
}
