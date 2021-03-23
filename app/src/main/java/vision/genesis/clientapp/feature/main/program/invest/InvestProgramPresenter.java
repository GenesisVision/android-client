package vision.genesis.clientapp.feature.main.program.invest;

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
import io.swagger.client.model.ProgramMinInvestAmount;
import io.swagger.client.model.ProgramWithdrawInfo;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.invest.confirm.ConfirmProgramInvestBottomSheetFragment;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.SettingsManager;
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
	public SettingsManager settingsManager;

	@Inject
	public RateManager rateManager;

	@Inject
	public ProgramsManager programsManager;

	private Subscription walletsSubscription;

	private Subscription withdrawInfoSubscription;

	private Subscription platformInfoSubscription;

	private ProgramRequest programRequest;

	private Double amount;

	private Double amountBase;

	private Double availableToInvest;

	private Double gvCommission = 0.0;

	private Double investmentAmount;

	private WalletData selectedWalletFrom;

	private Double rate = 0.0;

	private PlatformInfo info;

	private Double minInvestAmount = 0.0;

	private Double gvCommissionPercent = 0.0;

	private Map<String, Double> minInvestInfo;


	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getPlatformInfo();
		getWithdrawInfo();
	}

	@Override
	public void onDestroy() {
		if (walletsSubscription != null) {
			walletsSubscription.unsubscribe();
		}
		if (withdrawInfoSubscription != null) {
			withdrawInfoSubscription.unsubscribe();
		}
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setProgramRequest(ProgramRequest programRequest) {
		this.programRequest = programRequest;
		getPlatformInfo();
		getWithdrawInfo();
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
			//TODO: fix newAmount == "000.000000"
		} catch (NumberFormatException e) {
			amount = 0.0;
		}
		if (info != null && programRequest != null && availableToInvest != null) {
			if (amount > availableToInvest) {
				getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableToInvest));
				return;
			}

			amountBase = amount / rate;

			if (!programRequest.isOwner()) {
				gvCommission = amount * (gvCommissionPercent / 100);
			}
			investmentAmount = amountBase - gvCommission / rate;

			getViewState().setAmountBase(getAmountBaseString());
			getViewState().setManagementFee(getManagementFeeString());
			getViewState().setGvCommission(getGvCommissionString());
			getViewState().setInvestmentAmount(getInvestmentAmountString());
			getViewState().setContinueButtonEnabled(amount >= minInvestAmount
					&& amount > 0
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

	private String getManagementFeeString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(programRequest.getManagementFee(), 0, 2),
				context.getString(R.string.annual));
	}

	private String getGvCommissionString() {
		return String.format(Locale.getDefault(), "%s%% (%s)",
				StringFormatUtil.formatAmount(gvCommissionPercent, 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	private String getInvestmentAmountString() {
		return String.format(Locale.getDefault(), "%s%s",
				selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency()) ? "" : StringFormatUtil.getApproxSymbolIfNeeded(investmentAmount),
				StringFormatUtil.getValueString(investmentAmount, programRequest.getProgramCurrency()));
	}

	private String getFeesAndCommissionsString() {
		return String.format(Locale.getDefault(), "%s%% (%s)\n%s%% (%s)",
				StringFormatUtil.formatAmount(programRequest.getManagementFee(), 0, 2),
				context.getString(R.string.annual),
				StringFormatUtil.formatAmount(gvCommissionPercent, 0, 5),
				StringFormatUtil.getValueString(gvCommission, selectedWalletFrom.getCurrency().getValue()));
	}

	void onMaxClicked() {
		if (info != null) {
			getViewState().setAmount(StringFormatUtil.formatAmountWithoutGrouping(availableToInvest));
		}
	}

	void onContinueClicked() {
		programRequest.setAmount(amount);
		programRequest.setWalletCurrency(selectedWalletFrom.getCurrency().getValue());
		programRequest.setWalletId(selectedWalletFrom.getId());
		programRequest.setAmountTopText(getAmountToInvestString());
		programRequest.setInfoMiddleText(getFeesAndCommissionsString());
		programRequest.setAmountBottomText(getInvestmentAmountString());
		programRequest.setPeriodEndsText(getPeriodEndsText());
		getViewState().showConfirmDialog(programRequest);
	}

	private String getPeriodEndsText() {
		int templateStringResId;
		if (programRequest.isExchangeProgram()) {
			templateStringResId = R.string.template_program_exchange_invest_accrual;
		}
		else {
			templateStringResId = R.string.template_program_invest_accrual;
		}
		return String.format(Locale.getDefault(),
				context.getString(templateStringResId),
				selectedWalletFrom.getCurrency().getValue().equals(programRequest.getProgramCurrency()) ? "" :
						String.format(Locale.getDefault(),
								context.getString(R.string.template_program_invest_convert),
								selectedWalletFrom.getCurrency().getValue(), programRequest.getProgramCurrency()),
				programRequest.getProgramCurrency());

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

	private void getWithdrawInfo() {
		if (programsManager != null && programRequest != null && programRequest.isExchangeProgram() && !programRequest.getIsProcessingRealTime()) {
			withdrawInfoSubscription = programsManager.getWithdrawInfo(programRequest.getProgramId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawInfoResponse,
							this::handleWithdrawInfoError);
		}
	}

	private void handleWithdrawInfoResponse(ProgramWithdrawInfo response) {
		withdrawInfoSubscription.unsubscribe();

		getViewState().setRequestInfo(response.getPeriodEnds());
	}

	private void handleWithdrawInfoError(Throwable throwable) {
		withdrawInfoSubscription.unsubscribe();

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
		getViewState().showAmountProgress(false);

		this.rate = rate;

		if (programRequest.isOwner()) {
			availableToInvest = selectedWalletFrom.getAvailable();
		}
		else {
			availableToInvest = programRequest.getAvailableInvestment() < selectedWalletFrom.getAvailable() / rate
					? programRequest.getAvailableInvestment() * rate
					: selectedWalletFrom.getAvailable();
		}

		getViewState().setAvailableToInvest(StringFormatUtil.getValueString(
				programRequest.getAvailableInvestment(),
				programRequest.getProgramCurrency()));

		getViewState().setAmount("");
	}

	private void handleGetRateError(Throwable throwable) {
		getViewState().showAmountProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPlatformInfo() {
		if (settingsManager != null && programRequest != null) {
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
		getViewState().showAmountProgress(false);

		info = response;
		gvCommissionPercent = info.getCommonInfo().getPlatformCommission().getInvestment();

		minInvestInfo = new HashMap<>();

		for (ProgramMinInvestAmount model : info.getAssetInfo().getProgramInfo().getMinInvestAmounts()) {
			if (model.getServerType().equals(programRequest.getBrokerType())) {
				for (AmountWithCurrency amountWithCurrency : model.getMinInvestAmountIntoProgram()) {
					minInvestInfo.put(amountWithCurrency.getCurrency().getValue(), amountWithCurrency.getAmount());
				}
			}
		}

		updateMinInvest();

		subscribeToWallets();
	}

	private void handlePlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();
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
		updateMinInvest();
	}

	private void updateMinInvest() {
		if (selectedWalletFrom != null && minInvestInfo != null) {
			minInvestAmount = minInvestInfo.get(selectedWalletFrom.getCurrency().getValue());
			getViewState().setMinInvestmentAmount(minInvestAmount);
		}
	}

	@Override
	public void onInvestSucceeded() {
		getViewState().finishActivity();
	}
}
