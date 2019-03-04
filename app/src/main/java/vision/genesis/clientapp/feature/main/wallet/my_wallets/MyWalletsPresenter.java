package vision.genesis.clientapp.feature.main.wallet.my_wallets;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

@InjectViewState
public class MyWalletsPresenter extends MvpPresenter<MyWalletsView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public WalletManager walletManager;

	private Subscription baseCurrencySubscription;

	private Subscription walletsSubscription;

	private CurrencyEnum baseCurrency;

	private List<WalletData> wallets = new ArrayList<>();

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
		if (walletsSubscription != null)
			walletsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
		getWallets();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getWallets();
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
		getWallets();
	}

	private void getWallets() {
		if (walletManager != null && baseCurrency != null) {
			if (walletsSubscription != null)
				walletsSubscription.unsubscribe();
			walletsSubscription = walletManager.getWallets(baseCurrency.getValue(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetWalletsResponse,
							this::handleGetWalletsError);
		}
	}

	private void handleGetWalletsResponse(WalletMultiSummary model) {
		walletsSubscription.unsubscribe();
		getViewState().showProgress(false);

		wallets.clear();
		wallets.addAll(model.getWallets());

		getViewState().setWallets(wallets);
	}

	private void handleGetWalletsError(Throwable error) {
		walletsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
