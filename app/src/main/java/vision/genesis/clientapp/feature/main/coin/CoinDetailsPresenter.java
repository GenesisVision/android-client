package vision.genesis.clientapp.feature.main.coin;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.AssetInfo;
import io.swagger.client.model.CoinsAsset;
import io.swagger.client.model.CoinsAssetItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.CoinsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2021.
 */

@InjectViewState
public class CoinDetailsPresenter extends MvpPresenter<CoinDetailsView>
{
	@Inject
	public CoinsManager coinsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription getInfoSubscription;

	private Subscription getPortfolioSubscription;

	private Subscription getCoinInfoSubscription;

	private String symbol;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getInfo();
	}

	@Override
	public void onDestroy() {
		if (getInfoSubscription != null) {
			getInfoSubscription.unsubscribe();
		}
		if (getPortfolioSubscription != null) {
			getPortfolioSubscription.unsubscribe();
		}
		if (getCoinInfoSubscription != null) {
			getCoinInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void setData(String symbol) {
		this.symbol = symbol;
		getInfo();
	}

	public void onResume() {
		getPortfolio();
	}

	private void getInfo() {
		if (symbol != null && !symbol.isEmpty() && settingsManager != null) {
			if (getInfoSubscription != null) {
				getInfoSubscription.unsubscribe();
			}
			getInfoSubscription = settingsManager.getAssetInfo(symbol)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetInfoSuccess,
							this::handleGetInfoError);
		}
	}

	private void handleGetInfoSuccess(AssetInfo response) {
		getInfoSubscription.unsubscribe();

		getViewState().setAssetInfo(response);

		getPortfolio();
	}

	private void handleGetInfoError(Throwable throwable) {
		getInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getPortfolio() {
		if (symbol != null && coinsManager != null) {
			if (getPortfolioSubscription != null) {
				getPortfolioSubscription.unsubscribe();
			}
			getPortfolioSubscription = coinsManager.getPortfolio(0, 1000)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetPortfolioSuccess,
							this::handleGetPortfolioError);
		}
	}

	private void handleGetPortfolioSuccess(CoinsAssetItemsViewModel response) {
		getPortfolioSubscription.unsubscribe();
		getViewState().showProgress(false);

		CoinsAsset coin = null;

		for (CoinsAsset item : response.getItems()) {
			if (item.getAsset().equals(symbol)) {
				coin = item;
				break;
			}
		}
		getViewState().setInvestment(coin);

		if (coin == null) {
			getCoinInfo();
		}
	}

	private void handleGetPortfolioError(Throwable throwable) {
		getPortfolioSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getCoinInfo() {
		if (symbol != null && coinsManager != null) {
			if (getCoinInfoSubscription != null) {
				getCoinInfoSubscription.unsubscribe();
			}
			getCoinInfoSubscription = coinsManager.getCoin(symbol)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetCoinInfoSuccess,
							this::handleGetCoinInfoError);
		}
	}

	private void handleGetCoinInfoSuccess(CoinsAssetItemsViewModel response) {
		getCoinInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().setCoinInfo(response.getItems().get(0));
	}

	private void handleGetCoinInfoError(Throwable throwable) {
		getCoinInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
