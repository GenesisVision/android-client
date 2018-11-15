package vision.genesis.clientapp.feature.main.fund.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.FundWithdrawInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.fund.withdraw.confirm.ConfirmFundWithdrawBottomSheetFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

@InjectViewState
public class WithdrawFundPresenter extends MvpPresenter<WithdrawFundView> implements ConfirmFundWithdrawBottomSheetFragment.OnConfirmFundWithdrawListener
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription baseCurrencySubscription;

	private Subscription withdrawInfoSubscription;

	private FundRequest fundRequest;

	private CurrencyEnum baseCurrency;

	private Double amount;

	private FundWithdrawInfo withdrawInfo;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
		getWithdrawInfo();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null)
			baseCurrencySubscription.unsubscribe();
		if (withdrawInfoSubscription != null)
			withdrawInfoSubscription.unsubscribe();

		super.onDestroy();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		if (withdrawInfo != null) {
			if (amount > 100) {
				getViewState().setAmount("100");
				return;
			}

			getViewState().setEstimatedAmount(getEstimatedAmountString());
			getViewState().setExitFee(getExitFeeString());
			getViewState().setContinueButtonEnabled(amount > 0
					&& amount <= 100);
		}
	}

	private Double getEstimatedAmount() {
		return withdrawInfo.getAvailableToWithdraw() * amount / 100;
	}

	private Double getEstimatedExitFee() {
		return getEstimatedAmount() * withdrawInfo.getExitFee() / 100;
	}

	private Double getEstimatedYouWillGet() {
		return getEstimatedAmount() - getEstimatedExitFee();
	}

	private String getEstimatedAmountString() {
		return String.format(Locale.getDefault(), "≈ %s GVT",
				StringFormatUtil.formatCurrencyAmount(getEstimatedAmount(), CurrencyEnum.GVT.getValue()));
	}

	private String getAmountToWithdrawString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(amount, 0, 4), getEstimatedAmountString());
	}

	private String getExitFeeString() {
		return String.format(Locale.getDefault(), "%s%% (≈ %s GVT)",
				StringFormatUtil.formatAmount(withdrawInfo.getExitFee(), 0, 4),
				StringFormatUtil.formatCurrencyAmount(getEstimatedExitFee(), CurrencyEnum.GVT.getValue()));
	}

	private String getEstimatedYouWillGetString() {
		return String.format(Locale.getDefault(), "≈ %s GVT",
				StringFormatUtil.formatCurrencyAmount(getEstimatedYouWillGet(), CurrencyEnum.GVT.getValue()));
	}

	void onMaxClicked() {
		getViewState().setAmount("100");
	}

	void onContinueClicked() {
		fundRequest.setAmount(amount);
		fundRequest.setAmountTopText(getAmountToWithdrawString());
		fundRequest.setInfoMiddleText(getExitFeeString());
		fundRequest.setAmountBottomText(getEstimatedYouWillGetString());
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
		getWithdrawInfo();
	}

	private void getWithdrawInfo() {
		if (fundsManager != null && fundRequest != null && baseCurrency != null) {
			withdrawInfoSubscription = fundsManager.getWithdrawInfo(fundRequest.getFundId(), baseCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawInfoResponse,
							this::handleWithdrawInfoError);
		}
	}

	private void handleWithdrawInfoResponse(FundWithdrawInfo response) {
		withdrawInfoSubscription.unsubscribe();

		withdrawInfo = response;

		getViewState().showProgress(false);
		getViewState().setAvailableToWithdraw(withdrawInfo.getAvailableToWithdraw());
		getViewState().setExitFee(getExitFeeString());
		onAmountChanged("0");
	}

	private void handleWithdrawInfoError(Throwable throwable) {
		withdrawInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	public void setFundRequest(FundRequest fundRequest) {
		this.fundRequest = fundRequest;
		getWithdrawInfo();
	}

	@Override
	public void onWithdrawSucceeded() {
		getViewState().finishActivity();
	}
}
