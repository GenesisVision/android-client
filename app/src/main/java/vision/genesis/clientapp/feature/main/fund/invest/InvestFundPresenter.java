package vision.genesis.clientapp.feature.main.fund.invest;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.FundInvestInfo;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
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

	private Subscription investInfoSubscription;

	private FundRequest fundRequest;

	private Double amount;

	private Double amountBase;

	private FundInvestInfo investInfo;

	private Double availableInWallet;

	private Double entryFee;

	private Double gvCommission;

	private Double investmentAmount;

	private WalletData selectedWalletFrom;

	private CurrencyEnum baseCurrency;

	private Double rate = 0.0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
		getInvestInfo();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null)
			baseCurrencySubscription.unsubscribe();
		if (walletsSubscription != null)
			walletsSubscription.unsubscribe();
		if (investInfoSubscription != null)
			investInfoSubscription.unsubscribe();

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
//		double fractionalPart = NumberFormatUtil.roundDouble(amount - ((long) amount.doubleValue()), StringFormatUtil.getCurrencyMaxFraction(CurrencyEnum.GVT.getValue()));
//		if (String.valueOf(fractionalPart).length() > StringFormatUtil.getCurrencyMaxFraction(CurrencyEnum.GVT.getValue()) + 2) {
//			getViewState().setAmount(newAmount.substring(0, newAmount.length() - 1));
//		}
		if (investInfo != null) {
			if (amount > availableInWallet) {
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, selectedWalletFrom.getCurrency().getValue()));
				return;
			}

			amountBase = amount / rate;

			entryFee = amount * (investInfo.getEntryFee() / 100);
			gvCommission = amount * (investInfo.getGvCommission() / 100);
			investmentAmount = amount - entryFee - gvCommission;

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setEntryFee(getEntryFeeString());
			getViewState().setGvCommission(getGvCommissionString());
			getViewState().setInvestmentAmount(getInvestmentAmountString());
			getViewState().setContinueButtonEnabled(amount >= investInfo.getMinInvestmentAmount() && amount <= availableInWallet);
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
				StringFormatUtil.formatAmount(investInfo.getEntryFee(), 0, 2),
				StringFormatUtil.getValueString(entryFee, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getGvCommissionString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(investInfo.getGvCommission(), 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getInvestmentAmountString() {
		return String.format(Locale.getDefault(), "%s",
				StringFormatUtil.getValueString(investmentAmount, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getFeesAndCommissionsString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(investInfo.getEntryFee() + investInfo.getGvCommission(), 0, 5),
				StringFormatUtil.getValueString(entryFee + gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	void onMaxClicked() {
		if (investInfo != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, CurrencyEnum.GVT.getValue()));
		}
	}

	void onContinueClicked() {
		fundRequest.setAmount(amount);
		fundRequest.setWalletCurrency(selectedWalletFrom.getCurrency().getValue());
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
		subscribeToWallets();
	}

	private void subscribeToWallets() {
		if (walletManager != null && fundRequest != null && baseCurrency != null) {
			if (walletsSubscription != null)
				walletsSubscription.unsubscribe();
			walletsSubscription = walletManager.getWallets(baseCurrency.getValue(), false)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWalletUpdateSuccess,
							this::handleWalletUpdateError);
		}
	}

	private void handleWalletUpdateSuccess(WalletMultiSummary response) {
		List<WalletData> wallets = response.getWallets();

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
		this.rate = rate;
		getInvestInfo();
	}

	private void handleGetRateError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getInvestInfo() {
		if (fundsManager != null && fundRequest != null && selectedWalletFrom != null) {
			investInfoSubscription = fundsManager.getInvestInfo(fundRequest.getFundId(), selectedWalletFrom.getCurrency().getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleInvestInfoResponse,
							this::handleInvestInfoError);
		}
	}

	private void handleInvestInfoResponse(FundInvestInfo response) {
		investInfoSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showAmountProgress(false);

		investInfo = response;

		getViewState().setMinInvestmentAmount(investInfo.getMinInvestmentAmount());
		getViewState().setAmount("");
	}

	private void handleInvestInfoError(Throwable throwable) {
		investInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));

		getViewState().finishActivity();
	}

	@Override
	public void onWalletSelected(WalletData wallet) {
		this.selectedWalletFrom = wallet;
		availableInWallet = selectedWalletFrom.getAvailable();
		getViewState().setWalletFrom(selectedWalletFrom, baseCurrency);
		updateRate();
	}

	@Override
	public void onInvestSucceeded() {
		getViewState().finishActivity();
	}
}
