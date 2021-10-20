package vision.genesis.clientapp.feature.main.coin;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.AssetInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2021.
 */

@InjectViewState
public class CoinDetailsPresenter extends MvpPresenter<CoinDetailsView>
{
	@Inject
	public SettingsManager settingsManager;

	@Inject
	public TerminalManager terminalManager;

	private Subscription serverInfoSubscription;

	private Subscription getInfoSubscription;

	private String symbol;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getServerInfo();
		getInfo();
	}

	@Override
	public void onDestroy() {
		if (serverInfoSubscription != null) {
			serverInfoSubscription.unsubscribe();
		}
		if (getInfoSubscription != null) {
			getInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void setData(String symbol) {
		this.symbol = symbol;
		getInfo();
	}

//	private void getBaseAssetName() {
//		if (symbol != null && terminalManager != null) {
//			Pair<String, String> baseQuoteAssets = terminalManager.getBaseQuoteAssets(symbol);
//			if (baseQuoteAssets != null) {
//				baseAssetName = baseQuoteAssets.first;
//				getInfo();
//			}
//		}
//	}

	private void getServerInfo() {
		if (terminalManager != null) {
			if (serverInfoSubscription != null) {
				serverInfoSubscription.unsubscribe();
			}
			serverInfoSubscription = terminalManager.getBinanceServerInfo()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe((response -> {
							}),
							error -> {
							});
		}
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
		getViewState().showProgress(false);

		getViewState().setAssetInfo(response);
	}

	private void handleGetInfoError(Throwable throwable) {
		getInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
