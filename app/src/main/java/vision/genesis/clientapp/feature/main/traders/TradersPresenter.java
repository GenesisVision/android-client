package vision.genesis.clientapp.feature.main.traders;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.model.events.OnTraderItemListClicked;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class TradersPresenter extends MvpPresenter<TradersView>
{
	private static int TAKE = 2;

	@Inject
	public Context context;

	@Inject
	public Router router;

	@Inject
	public InvestManager investManager;

	private Subscription getTradersSubscription;

	private List<InvestmentProgram> investmentProgramsList = new ArrayList<>();

	private int skip = 0;

	private InvestmentsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		filter = new InvestmentsFilter();
		filter.setSkip(0);
		filter.setTake(TAKE);

		getViewState().setRefreshing(true);
		getTradersList(true);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (getTradersSubscription != null)
			getTradersSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
	}

	void onFilterClicked() {
		router.navigateTo(Screens.TRADERS_FILTERS);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getTradersList(true);
	}

	void onLastListItemVisible() {
		getTradersList(false);
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

	@Subscribe
	public void onEventMainThread(OnTraderItemListClicked event) {
		router.navigateTo(Screens.TRADER_DETAILS, event.investmentProgram);
	}

	void onTryAgainClicked() {
		getViewState().showProgressBar(true);
		getTradersList(true);
	}
}
