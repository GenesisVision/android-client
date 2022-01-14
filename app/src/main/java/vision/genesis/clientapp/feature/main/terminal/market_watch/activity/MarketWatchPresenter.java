package vision.genesis.clientapp.feature.main.terminal.market_watch.activity;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.ExchangeAsset;
import io.swagger.client.model.TradingAccountPermission;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/01/2022.
 */

@InjectViewState
public class MarketWatchPresenter extends MvpPresenter<MarketWatchView>
{
	@Inject
	public Context context;

	@Inject
	public TerminalManager terminalManager;

	private Subscription getAccountsSubscription;

	private UUID assetId;

	private TradingAccountPermission permission = TradingAccountPermission.SPOT;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getAccounts();
	}

	@Override
	public void onDestroy() {
		if (getAccountsSubscription != null) {
			getAccountsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID assetId, ArrayList<String> permissions) {
		this.assetId = assetId;

		if (permissions != null) {
			for (String permission : permissions) {
				if (permission.equals(TradingAccountPermission.SPOT.getValue())) {
					this.permission = TradingAccountPermission.SPOT;
				}
				else if (permission.equals(TradingAccountPermission.FUTURES.getValue())) {
					this.permission = TradingAccountPermission.FUTURES;
				}
			}
		}

		getAccounts();
	}

	private void getAccounts() {
		if (terminalManager != null && assetId != null && permission != null) {
			getAccountsSubscription = terminalManager.getAccountsFor(BrokerTradeServerType.BINANCE, permission)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetAccountsSuccess,
							this::handleGetAccountsError);
		}
	}

	private void handleGetAccountsSuccess(List<ExchangeAsset> accounts) {
		getAccountsSubscription.unsubscribe();

		for (ExchangeAsset account : accounts) {
			if (account.getId().equals(assetId)) {
				terminalManager.setSelectedAccount(account);
				break;
			}
		}

		getViewState().showProgress(false);
	}

	private void handleGetAccountsError(Throwable throwable) {
		getAccountsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}