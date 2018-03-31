package vision.genesis.clientapp.feature.main.program.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
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
public class WithdrawProgramPresenter extends MvpPresenter<WithdrawProgramView>
{
	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private ProgramRequest withdrawalRequest;

	private Subscription withdrawSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (withdrawSubscription != null)
			withdrawSubscription.unsubscribe();

		super.onDestroy();
	}

	void setWithdrawalRequest(ProgramRequest request) {
		withdrawalRequest = request;
		getViewState().setAvailable(withdrawalRequest.available);
		getViewState().setFiatBalance(withdrawalRequest.available * withdrawalRequest.tokenPrice);
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void onAmountChanged(double newAmount) {
		withdrawalRequest.amount = newAmount;
		getViewState().setFiatAmount(newAmount * withdrawalRequest.tokenPrice);
		getViewState().setWithdrawButtonEnabled(withdrawalRequest.amount > 0 && withdrawalRequest.amount <= withdrawalRequest.available);
		if (withdrawalRequest.available != 0)
			getViewState().setKeyboardKeysEnabled(withdrawalRequest.amount < withdrawalRequest.available);
		getViewState().showAmountHint(false);
	}

	void onAmountCleared() {
		getViewState().showAmountHint(true);
	}

	void onAvailableClicked() {
		getViewState().setAmount(withdrawalRequest.available);
	}

	void onWithdrawClicked() {
		sendWithdrawRequest();
	}

	private void sendWithdrawRequest() {
		getViewState().showProgress(true);
		withdrawSubscription = investManager.withdraw(withdrawalRequest)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleWithdrawSuccess,
						this::handleWithdrawError);
	}

	private void handleWithdrawSuccess(Void response) {
		withdrawSubscription.unsubscribe();

		EventBus.getDefault().post(new ShowMessageActivityEvent(context.getString(R.string.message_program_withdraw_success), R.drawable.ic_email_confirmed_icon, false));
		getViewState().finishActivity();
	}

	private void handleWithdrawError(Throwable throwable) {
		withdrawSubscription.unsubscribe();
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
}
