package vision.genesis.clientapp.feature.main.trading_account.add_demo_funds;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/01/2020.
 */

@InjectViewState
public class AddDemoFundsPresenter extends MvpPresenter<AddDemoFundsView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription addFundsSubscription;

	private ProgramRequest request;

	private Double amount;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (addFundsSubscription != null) {
			addFundsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setRequest(ProgramRequest request) {
		this.request = request;
	}

	void onAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
			//TODO: fix newAmount == "000.000000"
		} catch (NumberFormatException e) {
			amount = 0.0;
		}
		getViewState().setAddFundsButtonEnabled(amount > 0);
	}

	void onAddFundsClicked() {
		if (assetsManager != null && request != null && amount > 0) {
			if (addFundsSubscription != null) {
				addFundsSubscription.unsubscribe();
			}
			getViewState().showProgress(true);
			addFundsSubscription = assetsManager.addDemoFunds(request.getProgramId(), amount)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleAddFundsSuccess,
							this::handleAddFundsError);
		}
	}

	private void handleAddFundsSuccess(Void response) {
		addFundsSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleAddFundsError(Throwable throwable) {
		addFundsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
