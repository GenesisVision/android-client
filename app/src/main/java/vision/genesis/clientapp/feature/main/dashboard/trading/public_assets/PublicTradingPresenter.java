package vision.genesis.clientapp.feature.main.dashboard.trading.public_assets;

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
public class PublicTradingPresenter extends MvpPresenter<PublicTradingView>
{
	private static final int TAKE = 1000;

	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public DashboardManager dashboardManager;

	private Subscription baseCurrencySubscription;

	private Subscription publicSubscription;

	private CurrencyEnum baseCurrency;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (publicSubscription != null) {
			publicSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPublic();
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
		getPublic();
	}

	private void getPublic() {
		if (dashboardManager != null && baseCurrency != null) {
			if (publicSubscription != null) {
				publicSubscription.unsubscribe();
			}

			publicSubscription = dashboardManager.getPublic(dateRange, baseCurrency.getValue(), settingsManager.getSavedTradingPublicStatus(), 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPublicResponse,
							this::handleGetPublicError);
		}
	}

	private void handleGetPublicResponse(DashboardTradingAssetItemsViewModel response) {
		publicSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setPublic(response.getItems());
	}

	private void handleGetPublicError(Throwable throwable) {
		publicSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

}
