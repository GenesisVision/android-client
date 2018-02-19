package vision.genesis.clientapp.feature.main.traders;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.model.events.ShowFiltersEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class TradersPresenter extends MvpPresenter<TradersView>
{
	private static int TAKE = 5;

	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription filterSubscription;

	private Subscription getTradersSubscription;

	private List<InvestmentProgram> investmentProgramsList = new ArrayList<>();

	private int skip = 0;

	private InvestmentsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

//		EventBus.getDefault().register(this);

		subscribeToFilter();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (filterSubscription != null)
			filterSubscription.unsubscribe();

		if (getTradersSubscription != null)
			getTradersSubscription.unsubscribe();

//		EventBus.getDefault().unregister(this);
	}

	void onFilterClicked() {
		EventBus.getDefault().post(new ShowFiltersEvent());
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getTradersList(true);
	}

	void onLastListItemVisible() {
		getTradersList(false);
	}

	private void subscribeToFilter() {
		filterSubscription = investManager.filterSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::filterUpdatedHandler,
						error -> {
						});
	}

	private void filterUpdatedHandler(InvestmentsFilter investmentsFilter) {
		filter = investmentsFilter;
		filter.setSkip(0);
		filter.setTake(TAKE);
		getViewState().setRefreshing(true);
		getTradersList(true);
	}

	private void getTradersList(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
			filter.setSkip(skip);
		}

		getTradersSubscription = investManager.getTradersList(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTradersList,
						this::handleGetTradersListError);
	}

	private void handleGetTradersList(InvestmentProgramsViewModel model) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);

		getTradersSubscription.unsubscribe();

		List<InvestmentProgram> programs = investManager.parseInvestmentProgramsModel(model);

		if (programs.size() == 0) {
			if (skip == 0)
				getViewState().showEmptyList();
			return;
		}

		if (skip == 0) {
			investmentProgramsList.clear();
			getViewState().setInvestmentPrograms(programs);
		}
		else {
			getViewState().addInvestmentPrograms(programs);
		}
		investmentProgramsList.addAll(programs);
		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetTradersListError(Throwable error) {
		getTradersSubscription.unsubscribe();

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (investmentProgramsList.size() == 0)
				getViewState().showNoInternet(true);
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getTradersList(true);
	}
}
