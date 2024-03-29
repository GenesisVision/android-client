package vision.genesis.clientapp.feature.main.fund.invest;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import io.swagger.client.model.AmountWithCurrency;
import io.swagger.client.model.PlatformInfo;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * /**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class InvestFundPresenter extends MvpPresenter<InvestFundView> implements
		ConfirmFundInvestBottomSheetFragment.OnConfirmFundInvestListener,
		SelectWalletBottomSheetFragment.OnWalletSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription baseCurrencySubscription;

	private Subscription walletsSubscription;

	private Subscription platformInfoSubscription;

	private FundRequest fundRequest;

	private Double amount;

	private Double amountBase;

	private Double availableInWallet;

	private Double entryFee = 0.0;

	private Double gvCommission = 0.0;

	private Double investmentAmount;

	private WalletData selectedWalletFrom;

	private CurrencyEnum baseCurrency;

	private Double rate = 0.0;

	private PlatformInfo info;

	private Double minInvestAmount = 0.0;

	private Double gvCommissionPercent = 0.0;

	private Map<String, Double> minInvestInfo;

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
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setFundRequest(FundRequest fundRequest) {
		this.fundRequest = fundRequest;
		subscribeToBaseCurrency();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
			//TODO: fix newAmount == "000.000000"
		} catch (NumberFormatException e) {
			amount = 0.0;
		}
		if (info != null && fundRequest != null && rate > 0) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
				return;
			}

			amountBase = amount / rate;

			if (!fundRequest.isOwnFund()) {
				gvCommission = amount * (gvCommissionPercent / 100);
				entryFee = (amount - gvCommission) * (fundRequest.getEntryFee() / 100);
			}
			investmentAmount = amount - entryFee - gvCommission;

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setEntryFee(getEntryFeeString());
			getViewState().setGvCommission(getGvCommissionString());
			getViewState().setInvestmentAmount(getInvestmentAmountString());
			getViewState().setContinueButtonEnabled(amount >= minInvestAmount && amount > 0 && amount <= availableInWallet);
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "≈ %s",
				StringFormatUtil.getValueString(amountBase, baseCurrency.getValue()));
	}

	private String getAmountToInvestString() {
		return String.format(Locale.getDefault(), "%s (≈%s)",
				StringFormatUtil.getValueString(amount, selectedWalletFrom.getCurrency().getValue()),
				StringFormatUtil.getValueString(amountBase, baseCurrency.getValue()));
	}

	private String getEntryFeeString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(fundRequest.getEntryFee(), 0, 2),
				StringFormatUtil.getValueString(entryFee, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getGvCommissionString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(gvCommissionPercent, 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getInvestmentAmountString() {
		return String.format(Locale.getDefault(), "%s",
				StringFormatUtil.getValueString(investmentAmount, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getFeesAndCommissionsString() {
		return String.format(Locale.getDefault(), "%s%% (%s)\n%s%% (%s)",
				StringFormatUtil.formatAmount(gvCommissionPercent, 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()),
				StringFormatUtil.formatAmount(fundRequest.getEntryFee(), 0, 5),
				StringFormatUtil.getValueString(entryFee, selectedWalletFrom.getCurrency().getValue()));
	}

	void onMinClicked() {
		if (selectedWalletFrom != null) {
			double minAmount = selectedWalletFrom.getAvailable() > minInvestAmount
					? minInvestAmount
					: selectedWalletFrom.getAvailable();
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(minAmount));
		}
	}

	void onMaxClicked() {
		if (info != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableInWallet));
		}
	}

	void onContinueClicked() {
		fundRequest.setAmount(amount);
		fundRequest.setWalletCurrency(selectedWalletFrom.getCurrency().getValue());
		fundRequest.setWalletId(selectedWalletFrom.getId());
		fundRequest.setAmountTopText(getAmountToInvestString());
		fundRequest.setInfoMiddleText(getFeesAndCommissionsString());
		fundRequest.setAmountBottomText(getInvestmentAmountString());
		getViewState().showConfirmDialog(fundRequest);
	}

	private void subscribeToBaseCurrency() {
		if (settingsManager != null && fundRequest != null) {
			baseCurrencySubscription = settingsManager.getBaseCurrency()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::baseCurrencyChangedHandler);
		}
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getPlatformInfo();
	}

	private void getPlatformInfo() {
		if (settingsManager != null && fundRequest != null) {
			platformInfoSubscription = settingsManager.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handlePlatformInfoResponse,
							this::handlePlatformInfoError);
		}
	}

	private void handlePlatformInfoResponse(PlatformInfo response) {
		platformInfoSubscription.unsubscribe();

		getViewState().showProgress(false);

		info = response;
		gvCommissionPercent = info.getCommonInfo().getPlatformCommission().getInvestment();

		minInvestInfo = new HashMap<>();
		for (AmountWithCurrency amountWithCurrency : info.getAssetInfo().getFundInfo().getMinInvestAmountIntoFund()) {
			minInvestInfo.put(amountWithCurrency.getCurrency().getValue(), amountWithCurrency.getAmount());
		}

		subscribeToWallets();
	}

	private void handlePlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));

		getViewState().finishActivity();
	}

	private void subscribeToWallets() {
		if (walletManager != null && fundRequest != null && baseCurrency != null) {
			if (walletsSubscription != null) {
				walletsSubscription.unsubscribe();
			}
			walletsSubscription = walletManager.getWallets(baseCurrency.getValue(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletSummary response) {
		List<WalletData> wallets = new ArrayList<>();
		for (WalletData wallet : response.getWallets()) {
			if (minInvestInfo.containsKey(wallet.getCurrency().getValue())) {
				wallets.add(wallet);
			}
		}

		getViewState().setWalletsFrom(wallets);
		onWalletSelected(wallets.get(0));
	}

	private void handleWalletUpdateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void updateRate() {
		if (fundRequest != null && selectedWalletFrom != null && rateManager != null && baseCurrency != null) {
			getViewState().showAmountProgress(true);
			getViewState().setContinueButtonEnabled(false);
			rateManager.getRate(baseCurrency.getValue(), selectedWalletFrom.getCurrency().getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetRateSuccess,
							this::handleGetRateError);
		}
	}

	private void handleGetRateSuccess(Double rate) {
		getViewState().showAmountProgress(false);
		this.rate = rate;
	}

	private void handleGetRateError(Throwable throwable) {
		getViewState().showAmountProgress(false);
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void updateMinInvestmentAmount() {
		if (selectedWalletFrom != null && minInvestInfo != null) {
			minInvestAmount = minInvestInfo.get(selectedWalletFrom.getCurrency().getValue());
		}

		getViewState().setMinInvestmentAmount(minInvestAmount);
		getViewState().setAmount("");
	}

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWalletFrom = wallet;
		availableInWallet = selectedWalletFrom.getAvailable();
		getViewState().setWalletFrom(selectedWalletFrom, baseCurrency);
		updateRate();

		updateMinInvestmentAmount();
	}

	@Override
	public void onInvestSucceeded() {
		getViewState().finishActivity();
	}
}
