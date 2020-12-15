package vision.genesis.clientapp.feature.main.program.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.ProgramWithdrawInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.program.withdraw.confirm.ConfirmProgramWithdrawBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

@InjectViewState
public class WithdrawProgramPresenter extends MvpPresenter<WithdrawProgramView> implements ConfirmProgramWithdrawBottomSheetFragment.OnConfirmProgramWithdrawListener
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public ProgramsManager programsManager;

	private Subscription baseCurrencySubscription;

	private Subscription withdrawInfoSubscription;

	private ProgramRequest programRequest;

	private CurrencyEnum baseCurrency;

	private Double amount = 0.0;

	private ProgramWithdrawInfo withdrawInfo;

	private Double availableToWithdraw;

	private Boolean isWithdrawAll = false;

	private double amountPercent = 0.0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
		getWithdrawInfo();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (withdrawInfoSubscription != null) {
			withdrawInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setProgramRequest(ProgramRequest programRequest) {
		this.programRequest = programRequest;
		getWithdrawInfo();
	}

	void onWithdrawAllCheckedChanged(boolean checked) {
		isWithdrawAll = checked;
		getViewState().setAmountEnabled(!isWithdrawAll);
		updateRemainingInvestment();
		updateContinueButtonEnabled();
	}

	private void updateContinueButtonEnabled() {
		getViewState().setContinueButtonEnabled((amount > 0 && amount <= availableToWithdraw) || isWithdrawAll);
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
		if (withdrawInfo != null) {
			if (amount > availableToWithdraw) {
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableToWithdraw, programRequest.getProgramCurrency()));
				return;
			}

			updateRemainingInvestment();

//			getViewState().setAmountBase(getAmountBaseString());
			updateContinueButtonEnabled();
		}
	}

	void onPercentAmountChanged(String newAmount) {
		try {
			amountPercent = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amountPercent = 0.0;
		}

		if (withdrawInfo != null) {
			if (amountPercent > 100) {
				getViewState().setAmount("100");
				return;
			}

			updateRemainingInvestment();
			updateContinueButtonEnabled();
		}
	}

	private void updateRemainingInvestment() {
		getViewState().setRemainingInvestment(getRemainingInvestmentString());
	}

	private String getAmountBaseString() {
		//TODO:
//		return String.format(Locale.getDefault(), "= %s %s",
//				StringFormatUtil.formatCurrencyAmount(amount * withdrawInfo.getRate(), baseCurrency.getValue()),
//				baseCurrency.getValue());
		return String.format(Locale.getDefault(), "= %s %s",
				StringFormatUtil.formatCurrencyAmount(amount * 2, baseCurrency.getValue()),
				baseCurrency.getValue());
	}

	private String getAmountToWithdrawString() {
		if (isWithdrawAll) {
			return context.getString(R.string.withdraw_all);
		}
		else if (programRequest.isExchangeProgram()) {
			return getEstimatedAmountString();
		}
		else {
			return String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatCurrencyAmount(amount, programRequest.getProgramCurrency()),
					programRequest.getProgramCurrency());
		}
	}

	private String getEstimatedAmountString() {
		return String.format(Locale.getDefault(), "≈ %s",
				StringFormatUtil.getValueString(getEstimatedAmount(), programRequest.getProgramCurrency()));
	}

	private Double getEstimatedAmount() {
		return availableToWithdraw / 100 * amountPercent;
	}

	private String getRemainingInvestmentString() {
		double remainingInvestment = availableToWithdraw;
		if (programRequest != null) {
			if (programRequest.isExchangeProgram()) {
				remainingInvestment = getEstimatedAmount();
			}
			else {
				remainingInvestment = availableToWithdraw - amount;
			}
		}
		return String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(isWithdrawAll ? 0 : remainingInvestment, programRequest.getProgramCurrency()),
				programRequest.getProgramCurrency());
	}

	private String getPayoutDateString() {
		return DateTimeUtil.formatEventDateTime(withdrawInfo.getPeriodEnds());
	}

	void onAvailableToWithdrawClicked() {
		if (withdrawInfo != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableToWithdraw, programRequest.getProgramCurrency()));
			getViewState().setWithdrawAllChecked(false);
		}
	}

	void onContinueClicked() {
		programRequest.setWithdrawAll(isWithdrawAll);
		programRequest.setAmountTopText(getAmountToWithdrawString());
		programRequest.setInfoMiddleText(getPayoutDateString());
		programRequest.setAmountBottomText(getRemainingInvestmentString());

		if (programRequest.isExchangeProgram()) {
			programRequest.setAmount(amountPercent);
			if (programRequest.getIsProcessingRealTime()) {
				programRequest.setPeriodEndsText(String.format(Locale.getDefault(),
						context.getString(R.string.request_info_exchange_template),
						DateTimeUtil.formatRequestInfoDateTime(withdrawInfo.getPeriodEnds())));
			}
			else {
				programRequest.setAmount(amount);
				programRequest.setPeriodEndsText(context.getString(R.string.program_withdraw_warning));
			}
		}
		getViewState().showConfirmDialog(programRequest);
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
		if (programsManager != null && programRequest != null && baseCurrency != null) {
			withdrawInfoSubscription = programsManager.getWithdrawInfo(programRequest.getProgramId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawInfoResponse,
							this::handleWithdrawInfoError);
		}
	}

	private void handleWithdrawInfoResponse(ProgramWithdrawInfo response) {
		withdrawInfoSubscription.unsubscribe();

		withdrawInfo = response;

		availableToWithdraw = withdrawInfo.getAvailableToWithdraw();

		getViewState().showProgress(false);
		getViewState().setAvailableToWithdraw(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(availableToWithdraw, programRequest.getProgramCurrency()),
				programRequest.getProgramCurrency()));
		getViewState().setCurrency(programRequest.getProgramCurrency());
		updateRemainingInvestment();
		getViewState().setPayoutDate(getPayoutDateString());
		onAmountChanged("0");
	}

	private void handleWithdrawInfoError(Throwable throwable) {
		withdrawInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onWithdrawSucceeded() {
		getViewState().finishActivity();
	}
}
