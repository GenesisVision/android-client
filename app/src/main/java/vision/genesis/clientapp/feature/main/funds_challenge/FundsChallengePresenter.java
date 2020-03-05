package vision.genesis.clientapp.feature.main.funds_challenge;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.FundDetailsListItem;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/03/2020.
 */

@InjectViewState
public class FundsChallengePresenter extends MvpPresenter<FundsChallengeView>
{
	@Inject
	public FundsManager fundsManager;

	private Subscription getWinnerSubscription;

	private FacetModel facetModel;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getWinner();
	}

	@Override
	public void onDestroy() {
		if (getWinnerSubscription != null) {
			getWinnerSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(FacetModel model) {
		this.facetModel = model;
		getWinner();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getWinner();
	}

	private void getWinner() {
		if (fundsManager != null && facetModel != null) {
			getViewState().showProgress(true);
			getWinnerSubscription = fundsManager.getChallengeWinner()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetWinnerSuccess,
							this::handleGetWinnerError);
		}
	}

	private void handleGetWinnerSuccess(FundDetailsListItem winner) {
		getWinnerSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		getViewState().setWinner(winner);
	}

	private void handleGetWinnerError(Throwable throwable) {
		getWinnerSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
