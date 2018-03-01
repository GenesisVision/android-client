package vision.genesis.clientapp.feature.main.program.withdraw;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestManager;

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

	private double amount = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setAvailable(availableFunds);
	}

	void onBackClicked() {
		getViewState().finishActivity();
	}

	void onAmountChanged(double newAmount) {
		amount = newAmount;
		getViewState().setWithdrawButtonEnabled(amount > 0 && amount <= availableFunds);
	}

	void onAvailableClicked() {
		getViewState().setAmount(availableFunds);
	}

	void onWithdrawClicked() {

	}
}
