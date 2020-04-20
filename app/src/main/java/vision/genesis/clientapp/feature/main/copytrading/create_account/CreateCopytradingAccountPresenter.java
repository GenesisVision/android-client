package vision.genesis.clientapp.feature.main.copytrading.create_account;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.events.OnSubscribedToProgramEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/06/2019.
 */

@InjectViewState
public class CreateCopytradingAccountPresenter extends MvpPresenter<CreateCopytradingAccountView> implements
		SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	@Inject
	public ProgramsManager programsManager;

	private Subscription walletsSubscription;

	private SubscriptionSettingsModel model = new SubscriptionSettingsModel();

	private Double amount;

	private Double amountBase;

	private WalletData selectedWalletFrom;

	private Double rate = 0.0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToWallets();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setModel(SubscriptionSettingsModel model) {
		this.model = model;
		subscribeToWallets();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
			//TODO: fix newAmount == "000.000000"
		} catch (NumberFormatException e) {
			amount = 0.0;
		}
		if (selectedWalletFrom != null && rate != 0) {
			if (amount > selectedWalletFrom.getAvailable()) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(selectedWalletFrom.getAvailable()));
				return;
			}

			amountBase = amount / rate;

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setNextButtonEnabled(amount >= model.getMinDeposit() * rate
					&& amount <= selectedWalletFrom.getAvailable());
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "≈ %s",
				StringFormatUtil.getValueString(amountBase, model.getMinDepositCurrency()));
	}

	void onAmountToDepositLabelClicked() {
		if (model != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(model.getMinDeposit() * rate));
		}
	}

	void onMaxClicked() {
		if (selectedWalletFrom != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(selectedWalletFrom.getAvailable()));
		}
	}

	void onNextClicked() {
		model.setInitialDepositAmount(amount);
		model.setInitialDepositCurrency(selectedWalletFrom.getCurrency().getValue());
		getViewState().showSubscriptionSettings(model);
	}

	private void subscribeToWallets() {
		if (walletManager != null && model != null) {
			if (walletsSubscription != null) {
				walletsSubscription.unsubscribe();
			}
			walletsSubscription = walletManager.getWallets(CurrencyEnum.GVT.getValue(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletSummary response) {
		List<WalletData> wallets = response.getWallets();

		getViewState().setWalletsFrom(wallets);

		int selectedWalletPosition = 0;
		int index = 0;
		for (WalletData wallet : wallets) {
			if (wallet.getCurrency().getValue().equals(model.getMinDepositCurrency())) {
				selectedWalletPosition = index;
				break;
			}
			index++;
		}
		onWalletSelected(wallets.get(selectedWalletPosition));
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void updateRate() {
		if (selectedWalletFrom != null && rateManager != null) {
			getViewState().showAmountProgress(true);
			getViewState().setNextButtonEnabled(false);
			rateManager.getRate(model.getMinDepositCurrency(), selectedWalletFrom.getCurrency().getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetRateSuccess,
							this::handleGetRateError);
		}
	}

	private void handleGetRateSuccess(Double rate) {
		getViewState().showProgress(false);
		getViewState().showAmountProgress(false);

		this.rate = rate;

		getViewState().setMinDepositAmount(model.getMinDeposit() * rate, selectedWalletFrom.getCurrency().getValue());
		getViewState().setAmount("");
	}

	private void handleGetRateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWalletFrom = wallet;
		getViewState().setWalletFrom(selectedWalletFrom);
		updateRate();
	}

	@Subscribe
	public void onEventMainThread(OnSubscribedToProgramEvent event) {
		getViewState().finishActivity();
	}
}
