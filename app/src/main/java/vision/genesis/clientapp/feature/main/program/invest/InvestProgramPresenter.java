package vision.genesis.clientapp.feature.main.program.invest;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.WalletViewModel;
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

	private Subscription balanceSubscription;

	private Subscription rateSubscription;

	private double balance = 0;

	private double rate = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getBalance();
	}

	@Override
	public void onDestroy() {
		if (investSubscription != null)
			investSubscription.unsubscribe();

		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();

		super.onDestroy();
	}

	void setInvestRequest(ProgramRequest request) {
		investRequest = request;
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

	private void getBalance() {
		getViewState().showAvailableProgress(true);
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleBalanceUpdateResponse,
						this::handleBalanceUpdateError);

		updateRate();
	}

	private void handleBalanceUpdateResponse(Double balance) {
		getViewState().showAvailableProgress(false);
		setBalance(balance);
	}

	private void handleBalanceUpdateError(Throwable error) {
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

		EventBus.getDefault().post(new ShowMessageActivityEvent(context.getString(R.string.message_program_invest_success), R.drawable.ic_email_confirmed_icon));
//		EventBus.getDefault().post(new NewInvestmentSuccessEvent());
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

	private void updateRate() {
		rateSubscription = rateManager.getRate(WalletViewModel.CurrencyEnum.GVT.toString(), investRequest.programCurrency)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::getRateSuccessHandler,
						this::getRateErrorHandler);
	}

	private void getRateSuccessHandler(Double rate) {
		rateSubscription.unsubscribe();

		this.rate = rate;
		updateFiatBalance();
	}

	private void updateFiatBalance() {
		getViewState().setProgramCurrencyBalance(rate * balance);
		getViewState().setProgramCurrencyAmount(rate * investRequest.amount);
	}

	private void getRateErrorHandler(Throwable error) {
		rateSubscription.unsubscribe();
	}
}
