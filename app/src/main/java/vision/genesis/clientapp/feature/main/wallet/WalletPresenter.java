package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView> implements SelectCurrencyFragment.OnCurrencyChangedListener
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public WalletManager walletManager;

	private Subscription baseCurrencySubscription;

	private Subscription balanceSubscription;

	private CurrencyEnum baseCurrency;

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
		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
		updateBalance();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateBalance();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setBaseCurrency(baseCurrency);
		getViewState().showProgress(true);
		updateBalance();
	}

	private void updateBalance() {
		if (baseCurrency != null) {
			if (balanceSubscription != null)
				balanceSubscription.unsubscribe();
			balanceSubscription = walletManager.getWallets(baseCurrency.getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleBalanceUpdateResponse,
							this::handleBalanceUpdateError);
		}
	}

	private void handleBalanceUpdateResponse(WalletMultiSummary response) {
		balanceSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setBalance(response);
	}

	private void handleBalanceUpdateError(Throwable throwable) {
		balanceSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onCurrencyChanged(CurrencyEnum currency) {
		settingsManager.saveBaseCurrency(currency);
	}
}
