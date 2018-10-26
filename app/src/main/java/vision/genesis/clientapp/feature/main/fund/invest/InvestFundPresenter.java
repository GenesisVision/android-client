package vision.genesis.clientapp.feature.main.fund.invest;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.FundInvestInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.fund.invest.confirm.ConfirmFundInvestBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
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
public class InvestFundPresenter extends MvpPresenter<InvestFundView> implements ConfirmFundInvestBottomSheetFragment.OnConfirmFundInvestListener
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription baseCurrencySubscription;

	private Subscription investInfoSubscription;

	private FundRequest fundRequest;

	private CurrencyEnum baseCurrency;

	private Double amount;

	private FundInvestInfo investInfo;

	private Double availableInWallet;

	private Double entryFee;

	private Double gvCommission;

	private Double investmentAmount;

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
		if (investInfoSubscription != null)
			investInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	void setFundRequest(FundRequest fundRequest) {
		this.fundRequest = fundRequest;
		getInvestInfo();
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
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, CurrencyEnum.GVT.getValue()));
				return;
			}

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
		return String.format(Locale.getDefault(), "= %s %s",
				StringFormatUtil.formatCurrencyAmount(amount * investInfo.getRate(), baseCurrency.getValue()),
				baseCurrency.getValue());
	}

	private String getAmountToInvestString() {
		return String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(amount, CurrencyEnum.GVT.getValue()));
	}

	private String getEntryFeeString() {
		return String.format(Locale.getDefault(), "%s%% (%s GVT)",
				StringFormatUtil.formatAmount(investInfo.getEntryFee(), 0, 5),
				StringFormatUtil.formatCurrencyAmount(entryFee, CurrencyEnum.GVT.getValue()));
	}

	private String getGvCommissionString() {
		return String.format(Locale.getDefault(), "%s%% (%s GVT)",
				StringFormatUtil.formatAmount(investInfo.getGvCommission(), 0, 5),
				StringFormatUtil.formatCurrencyAmount(gvCommission, CurrencyEnum.GVT.getValue()));
	}

	private String getInvestmentAmountString() {
		return String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(investmentAmount, CurrencyEnum.GVT.getValue()));
	}

	private String getFeesAndCommissionsString() {
		return String.format(Locale.getDefault(), "%s%% (%s GVT)",
				StringFormatUtil.formatAmount(investInfo.getEntryFee() + investInfo.getGvCommission(), 0, 5),
				StringFormatUtil.formatCurrencyAmount(entryFee + gvCommission, CurrencyEnum.GVT.getValue()));
	}

	void onMaxClicked() {
		if (investInfo != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableInWallet, CurrencyEnum.GVT.getValue()));
		}
	}

	void onContinueClicked() {
		fundRequest.setAmount(amount);
		fundRequest.setAmountTopText(getAmountToInvestString());
		fundRequest.setInfoMiddleText(getFeesAndCommissionsString());
		fundRequest.setAmountBottomText(getInvestmentAmountString());
		getViewState().showConfirmDialog(fundRequest);
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getInvestInfo();
	}

	private void getInvestInfo() {
		if (fundsManager != null && fundRequest != null && baseCurrency != null) {
			investInfoSubscription = fundsManager.getInvestInfo(fundRequest.getFundId(), baseCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleInvestInfoResponse,
							this::handleInvestInfoError);
		}
	}

	private void handleInvestInfoResponse(FundInvestInfo response) {
		investInfoSubscription.unsubscribe();

		investInfo = response;

		availableInWallet = investInfo.getAvailableInWallet();

		getViewState().showProgress(false);
		getViewState().setAvailableInWallet(availableInWallet);
		getViewState().setMinInvestmentAmount(investInfo.getMinInvestmentAmount());
		onAmountChanged("0");
	}

	private void handleInvestInfoError(Throwable throwable) {
		investInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));

		getViewState().finishActivity();
	}

	@Override
	public void onInvestSucceeded() {
		getViewState().finishActivity();
	}
}
