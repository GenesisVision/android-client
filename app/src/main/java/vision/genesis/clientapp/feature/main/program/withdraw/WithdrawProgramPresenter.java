package vision.genesis.clientapp.feature.main.program.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.ProgramWithdrawalRequest;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
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

	private double availableFunds = 1000.01234567;

	private ProgramWithdrawalRequest withdrawalRequest;

	private Subscription withdrawSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setAvailable(availableFunds);
	}

	@Override
	public void onDestroy() {
		if (withdrawSubscription != null)
			withdrawSubscription.unsubscribe();

		super.onDestroy();
	}

	void setWithdrawalRequest(ProgramWithdrawalRequest request) {
		withdrawalRequest = request;
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void onAmountChanged(double newAmount) {
		withdrawalRequest.amount = newAmount;
		getViewState().setWithdrawButtonEnabled(withdrawalRequest.amount > 0 && withdrawalRequest.amount <= availableFunds);
	}

	void onAvailableClicked() {
		getViewState().setAmount(availableFunds);
	}

	void onWithdrawClicked() {
		sendWithdrawRequest();
	}

	private void sendWithdrawRequest() {
		withdrawSubscription = investManager.withdraw(withdrawalRequest)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleWithdrawSuccess,
						this::handleWithdrawError);
	}

	private void handleWithdrawSuccess(Void response) {
		withdrawSubscription.unsubscribe();

//		EventBus.getDefault().post(new NewWithdrawalRequestSuccessEvent());
		getViewState().finishActivity();
	}

	private void handleWithdrawError(Throwable throwable) {
		withdrawSubscription.unsubscribe();

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
