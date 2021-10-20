package vision.genesis.clientapp.feature.main.dashboard.investments.coins.portfolio;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.CoinsAssetItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.CoinsManager;
import vision.genesis.clientapp.model.events.SetCoinsPortfolioAssetsCountEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

@InjectViewState
public class CoinsAssetsPresenter extends MvpPresenter<CoinsAssetsView>
{
	@Inject
	public Context context;

	@Inject
	public CoinsManager coinsManager;

	private Subscription getCoinsSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setRefreshing(true);
		getCoinsList();
	}

	@Override
	public void onDestroy() {
		if (getCoinsSubscription != null) {
			getCoinsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getCoinsList();
	}

	void onUpdateAll() {
		getViewState().showProgressBar(true);
		getCoinsList();
	}

	private void getCoinsList() {
		if (coinsManager != null) {
			if (getCoinsSubscription != null) {
				getCoinsSubscription.unsubscribe();
			}
			getCoinsSubscription = coinsManager.getPortfolio(0, 100)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetCoinsList,
							this::handleGetCoinsListError);
		}
	}

	private void handleGetCoinsList(CoinsAssetItemsViewModel response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);

		if (getCoinsSubscription != null) {
			getCoinsSubscription.unsubscribe();
		}

		getViewState().setCoins(response.getItems());

		EventBus.getDefault().post(new SetCoinsPortfolioAssetsCountEvent(response.getTotal()));
	}

	private void handleGetCoinsListError(Throwable error) {
		getCoinsSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
	}
}
