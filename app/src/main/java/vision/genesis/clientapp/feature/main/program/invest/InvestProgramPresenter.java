package vision.genesis.clientapp.feature.main.program.invest;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramBuyToken;
import io.swagger.client.model.WalletsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.ShowMessageActivityEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

@InjectViewState
public class InvestProgramPresenter extends MvpPresenter<InvestProgramView>
{
	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	private ProgramRequest investRequest;

	private Subscription investSubscription;

	private Subscription buyTokensModelSubscription;

	private Subscription rateSubscription;

	private double balance = 0;

	private double rate = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getBuyTokensModel();
	}

	@Override
	public void onDestroy() {
		if (investSubscription != null)
			investSubscription.unsubscribe();

		if (buyTokensModelSubscription != null)
			buyTokensModelSubscription.unsubscribe();

		super.onDestroy();
	}

	void setInvestRequest(ProgramRequest request) {
		investRequest = request;
		getBuyTokensModel();
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void onAmountChanged(double newAmount) {
		investRequest.amount = newAmount;
		getViewState().setProgramCurrencyAmount(rate * newAmount);
		getViewState().setInvestButtonEnabled(investRequest.amount > 0 && investRequest.amount <= balance);
		if (balance != 0)
			getViewState().setKeyboardKeysEnabled(investRequest.amount < balance);
		getViewState().showAmountHint(false);
	}

	void onAmountCleared() {
		getViewState().showAmountHint(true);
	}

	void onAvailableClicked() {
		getViewState().setAmount(balance);
	}

	void onInvestClicked() {
		sendInvestRequest();
	}

	private void getBuyTokensModel() {
		if (investManager != null && investRequest != null) {
			getViewState().showAvailableProgress(true);
			buyTokensModelSubscription = investManager.getBuyTokensModel(investRequest.programId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleBuyTokensModelResponse,
							this::handleBuyTokensModelError);
		}
	}

	private void handleBuyTokensModelResponse(InvestmentProgramBuyToken response) {
		getViewState().showAvailableProgress(false);
		rate = response.getGvtRate();
		double availableToInvest = investRequest.available;
		if (response.getGvtWalletAmount() < availableToInvest)
			availableToInvest = response.getGvtWalletAmount();
		setBalance(availableToInvest);
		updateFiatBalance();
	}

	private void handleBuyTokensModelError(Throwable error) {
		getViewState().showAvailableProgress(false);
	}

	private void setBalance(Double balance) {
		this.balance = balance;
		getViewState().setAvailable(balance);
		getViewState().setProgramCurrencyBalance(rate * balance);
	}

	private void sendInvestRequest() {
		getViewState().showProgress(true);
		investSubscription = investManager.invest(investRequest)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleInvestSuccess,
						this::handleInvestError);
	}

	private void handleInvestSuccess(WalletsViewModel model) {
		investSubscription.unsubscribe();
		walletManager.getBalance();

		EventBus.getDefault().post(new ShowMessageActivityEvent(
				String.format(Locale.getDefault(), context.getString(R.string.message_program_invest_success), String.valueOf(investRequest.amount)),
				R.drawable.ic_email_confirmed_icon,
				false));
		getViewState().finishActivity();
	}

	private void handleInvestError(Throwable throwable) {
		investSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showToastMessage(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.message != null) {
						getViewState().showToastMessage(error.message);
						break;
					}
				}
			}
		}
	}

	private void updateFiatBalance() {
		getViewState().setProgramCurrencyBalance(rate * balance);
		getViewState().setProgramCurrencyAmount(rate * investRequest.amount);
	}
}
