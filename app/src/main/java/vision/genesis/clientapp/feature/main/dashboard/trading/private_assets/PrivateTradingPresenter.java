package vision.genesis.clientapp.feature.main.dashboard.trading.private_assets;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.DashboardTradingAssetItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

@InjectViewState
public class PrivateTradingPresenter extends MvpPresenter<PrivateTradingView>
{
	private static final int TAKE = 100;

	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription privateAccountsSubscription;

	private Subscription privateFundsSubscription;

	private CurrencyEnum baseCurrency;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);


	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (privateAccountsSubscription != null) {
			privateAccountsSubscription.unsubscribe();
		}
		if (privateFundsSubscription != null) {
			privateFundsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateAll();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setRefreshing(true);
		updateAll();
	}

	private void updateAll() {
		if (dashboardManager != null && baseCurrency != null) {
			if (privateAccountsSubscription != null) {
				privateAccountsSubscription.unsubscribe();
			}

			getViewState().setRefreshing(true);
			privateAccountsSubscription = dashboardManager.getPrivateAccounts(dateRange, baseCurrency.getValue(), settingsManager.getSavedTradingPrivateStatus(), 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPrivateAccountsResponse,
							this::handleGetPrivateAccountsError);
		}
	}

	private void handleGetPrivateAccountsResponse(DashboardTradingAssetItemsViewModel response) {
		privateAccountsSubscription.unsubscribe();

		getViewState().setPrivateAccounts(response.getItems(), baseCurrency.getValue());

		getPrivateFunds();
	}

	private void handleGetPrivateAccountsError(Throwable throwable) {
		privateAccountsSubscription.unsubscribe();
		privateFundsSubscription.unsubscribe();
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPrivateFunds() {
		if (dashboardManager != null && baseCurrency != null) {
			if (privateFundsSubscription != null) {
				privateFundsSubscription.unsubscribe();
			}

			privateFundsSubscription = dashboardManager.getPrivateFunds(dateRange, baseCurrency.getValue(), settingsManager.getSavedTradingPrivateStatus(), 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPrivateFundsResponse,
							this::handleGetPrivateAccountsError);
		}
	}

	private void handleGetPrivateFundsResponse(DashboardTradingAssetItemsViewModel response) {
		privateFundsSubscription.unsubscribe();
		getViewState().setRefreshing(false);

		getViewState().setPrivateFunds(response.getItems(), baseCurrency.getValue());
	}
}
