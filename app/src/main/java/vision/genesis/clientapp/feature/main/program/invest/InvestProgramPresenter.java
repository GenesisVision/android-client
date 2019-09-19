package vision.genesis.clientapp.feature.main.program.invest;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.ProgramInvestInfo;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletMultiSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.invest.confirm.ConfirmProgramInvestBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

@InjectViewState
public class InvestProgramPresenter extends MvpPresenter<InvestProgramView> implements
		ConfirmProgramInvestBottomSheetFragment.OnConfirmProgramInvestListener,
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

	private Subscription investInfoSubscription;

	private ProgramRequest programRequest;

	private Double amount;

	private Double amountBase;

	private ProgramInvestInfo investInfo;

	private Double availableToInvest;

	private Double entryFee;

	private Double gvCommission;

	private Double investmentAmount;

	private WalletData selectedWalletFrom;

	private Double rate = 0.0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToWallets();
		getInvestInfo();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}
		if (investInfoSubscription != null) {
			investInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setProgramRequest(ProgramRequest programRequest) {
		this.programRequest = programRequest;
		subscribeToWallets();
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
			if (amount > availableToInvest) {
				getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableToInvest, selectedWalletFrom.getCurrency().getValue()));
				return;
			}

			amountBase = amount / rate;

			entryFee = amountBase * (investInfo.getEntryFee() / 100);
			gvCommission = amount * (investInfo.getGvCommission() / 100);
			investmentAmount = amountBase - entryFee - gvCommission / rate;

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setEntryFee(getEntryFeeString());
			getViewState().setGvCommission(getGvCommissionString());
			getViewState().setInvestmentAmount(getInvestmentAmountString());
			getViewState().setContinueButtonEnabled(amount >= investInfo.getProgramCurrencyMinInvestment() * rate
					&& amount <= availableToInvest);
		}
	}

	private String getAmountBaseString() {
		return String.format(Locale.getDefault(), "≈ %s",
				StringFormatUtil.getValueString(amountBase, programRequest.getProgramCurrency()));
	}

	private String getAmountToInvestString() {
		String approxAmount = String.format(Locale.getDefault(), "(≈%s)",
				StringFormatUtil.getValueString(amountBase, programRequest.getProgramCurrency()));
		String result = StringFormatUtil.getValueString(amount, selectedWalletFrom.getCurrency().getValue());
		if (!selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency())) {
			result = result.concat(" " + approxAmount);
		}
		return result;
	}

	private String getEntryFeeString() {
		return String.format(Locale.getDefault(), "%s%% (%s%s)",
				StringFormatUtil.formatAmount(investInfo.getEntryFee(), 0, 2),
				selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency()) ? "" : StringFormatUtil.getApproxSymbolIfNeeded(entryFee),
				StringFormatUtil.getValueString(entryFee, programRequest.getProgramCurrency()));
	}

	private String getGvCommissionString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(investInfo.getGvCommission(), 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getInvestmentAmountString() {
		return String.format(Locale.getDefault(), "%s%s",
				selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency()) ? "" : StringFormatUtil.getApproxSymbolIfNeeded(investmentAmount),
				StringFormatUtil.getValueString(investmentAmount, programRequest.getProgramCurrency()));
	}

	private String getFeesAndCommissionsString() {
		return String.format(Locale.getDefault(), "%s%% (%s%s)\n%s%% (%s)",
				StringFormatUtil.formatAmount(investInfo.getEntryFee(), 0, 5),
				selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency()) ? "" : StringFormatUtil.getApproxSymbolIfNeeded(entryFee),
				StringFormatUtil.getValueString(entryFee, programRequest.getProgramCurrency()),
				StringFormatUtil.formatAmount(investInfo.getGvCommission(), 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	void onMaxClicked() {
		if (investInfo != null) {
			getViewState().setAmount(StringFormatUtil.formatCurrencyAmount(availableToInvest, CurrencyEnum.GVT.getValue()));
		}
	}

	void onContinueClicked() {
		programRequest.setAmount(amount);
		programRequest.setWalletCurrency(selectedWalletFrom.getCurrency().getValue());
		programRequest.setAmountTopText(getAmountToInvestString());
		programRequest.setInfoMiddleText(getFeesAndCommissionsString());
		programRequest.setAmountBottomText(getInvestmentAmountString());
		programRequest.setPeriodEndsText(String.format(Locale.getDefault(),
				context.getString(R.string.template_program_invest_accrual),
				selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency()) ? "" :
						String.format(Locale.getDefault(),
								context.getString(R.string.template_program_invest_convert),
								selectedWalletFrom.getCurrency().getValue(), programRequest.getProgramCurrency()),
				programRequest.getProgramCurrency()));
		getViewState().showConfirmDialog(programRequest);
	}

	private void subscribeToWallets() {
		if (walletManager != null && programRequest != null) {
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
		if (programRequest != null && selectedWalletFrom != null && rateManager != null) {
			getViewState().showAmountProgress(true);
			getViewState().setContinueButtonEnabled(false);
			rateManager.getRate(programRequest.getProgramCurrency(), selectedWalletFrom.getCurrency().getValue())
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
		if (programsManager != null && programRequest != null && selectedWalletFrom != null) {
			investInfoSubscription = programsManager.getInvestInfo(programRequest.getProgramId(), selectedWalletFrom.getCurrency().getValue())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleInvestInfoResponse,
							this::handleInvestInfoError);
		}
	}

	private void handleInvestInfoResponse(ProgramInvestInfo response) {
		investInfoSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showAmountProgress(false);

		investInfo = response;

//		double maxAmount = investInfo.getAvailableInWallet() / (1 + investInfo.getEntryFee() / 100);
//		availableToInvest = investInfo.getAvailableToInvest() < maxAmount
//				? investInfo.getAvailableToInvest()
//				: maxAmount;
		getViewState().setAvailableToInvest(StringFormatUtil.getValueString(
				investInfo.getAvailableToInvestBase(),
				programRequest.getProgramCurrency()));

		availableToInvest = investInfo.getAvailableToInvestBase() < selectedWalletFrom.getAvailable() / rate
				? investInfo.getAvailableToInvestBase() * rate
				: selectedWalletFrom.getAvailable();

		getViewState().setMinInvestmentAmount(investInfo.getProgramCurrencyMinInvestment() * rate);
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
		getViewState().setWalletFrom(selectedWalletFrom);
		updateRate();
	}

	@Override
	public void onInvestSucceeded() {
		getViewState().finishActivity();
	}
}
