package vision.genesis.clientapp.feature.main.trading_account;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.PrivateTradingAccountFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.events.SetTradingAccountDetailsOpenPositionsCountEvent;
import vision.genesis.clientapp.model.events.SetTradingAccountDetailsTradesCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class TradingAccountPresenter extends MvpPresenter<TradingAccountDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public TradingAccountManager tradingAccountManager;

	private Subscription accountDetailsSubscription;

	private TradingAccountDetailsModel model;

	private PrivateTradingAccountFull accountDetails;

	private FollowDetailsFull followDetails;

	private boolean isActive = false;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (accountDetailsSubscription != null) {
			accountDetailsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	void onResume() {
		isActive = true;
		getAccountDetails();
	}

	void onPause() {
		isActive = false;
	}

	void setData(TradingAccountDetailsModel model) {
		this.model = model;
		getAccountDetails();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getAccountDetails();
	}

	void onTryAgainClicked() {
		getViewState().showNoInternetProgress(true);
		getAccountDetails();
	}

	private void getAccountDetails() {
		if (model != null && tradingAccountManager != null) {
			accountDetailsSubscription = tradingAccountManager.getAccountDetails(model.getAccountId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleAccountDetailsSuccess,
							this::handleAccountDetailsError);
		}
	}

	private void handleAccountDetailsSuccess(PrivateTradingAccountFull accountDetails) {
		accountDetailsSubscription.unsubscribe();
		hideProgress();

		this.accountDetails = accountDetails;
		getViewState().setDetails(accountDetails);
	}

	private void handleAccountDetailsError(Throwable throwable) {
		accountDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (accountDetails == null) {
				getViewState().showNoInternet(true);
				getViewState().showNoInternetProgress(false);
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
		}
		else {
			ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
		}
	}

	private void hideProgress() {
		getViewState().showNoInternet(false);
		getViewState().showNoInternetProgress(false);
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);
	}

	@Subscribe
	public void onEventMainThread(SetTradingAccountDetailsOpenPositionsCountEvent event) {
		getViewState().setOpenPositionsCount(event.getOpenPositionsCount());
	}

	@Subscribe
	public void onEventMainThread(SetTradingAccountDetailsTradesCountEvent event) {
		getViewState().setTradesCount(event.getTradesCount());
	}
}
