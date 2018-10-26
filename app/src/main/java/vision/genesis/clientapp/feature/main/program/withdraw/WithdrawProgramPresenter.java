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

	private ProgramRequest withdrawalRequest;

	private Subscription baseCurrencySubscription;

	private Subscription withdrawInfoSubscription;

	private ProgramRequest programRequest;

	private CurrencyEnum baseCurrency;

	private Double amount;

	private ProgramWithdrawInfo withdrawInfo;

	private Double availableToWithdraw;

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
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableToWithdraw, CurrencyEnum.GVT.getValue()));
				return;
			}

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setRemainingInvestment(getRemainingInvestmentString());
			getViewState().setContinueButtonEnabled(amount > 0
					&& amount <= availableToWithdraw);
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "= %s %s",
				StringFormatUtil.formatCurrencyAmount(amount * withdrawInfo.getRate(), baseCurrency.getValue()),
				baseCurrency.getValue());
	}

	private String getAmountToWithdrawString() {
		return String.format(Locale.getDefault(), "%s GVT",
				StringFormatUtil.formatCurrencyAmount(amount, CurrencyEnum.GVT.getValue()));
//		return StringFormatUtil.getBaseValueString(amount, withdrawInfo.getCurrency());
	}

	private String getRemainingInvestmentString() {
		return String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(availableToWithdraw - amount, CurrencyEnum.GVT.getValue()));
	}

	private String getPayoutDateString() {
		return DateTimeUtil.formatEventDateTime(withdrawInfo.getPeriodEnds());
	}

	void onMaxClicked() {
		if (withdrawInfo != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableToWithdraw, CurrencyEnum.GVT.getValue()));
		}
	}

	void onContinueClicked() {
		programRequest.setAmount(amount);
		programRequest.setAmountTopText(getAmountToWithdrawString());
		programRequest.setInfoMiddleText(getPayoutDateString());
		programRequest.setAmountBottomText(getRemainingInvestmentString());
		programRequest.setPeriodEndsText(String.format(Locale.getDefault(),
				context.getString(R.string.request_info_template),
				DateTimeUtil.formatShortDateTime(withdrawInfo.getPeriodEnds())));
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
			withdrawInfoSubscription = programsManager.getWithdrawInfo(programRequest.getProgramId(), baseCurrency)
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
		getViewState().setAvailableToWithdraw(availableToWithdraw);
		getViewState().setPayoutDate(getPayoutDateString());
		onAmountChanged("0");
	}

	private void handleWithdrawInfoError(Throwable throwable) {
		withdrawInfoSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	public void setProgramRequest(ProgramRequest programRequest) {
		this.programRequest = programRequest;
		getWithdrawInfo();
	}

	@Override
	public void onWithdrawSucceeded() {
		getViewState().finishActivity();
	}
}
