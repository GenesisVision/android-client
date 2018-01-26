package vision.genesis.clientapp.feature.main.traders;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
import vision.genesis.clientapp.model.events.OnTraderItemListClicked;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class TradersPresenter extends MvpPresenter<TradersView>
{
	@Inject
	public Context context;

	@Inject
	public Router router;

	@Inject
	public InvestManager investManager;

	private Subscription getTradersSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setRefreshing(true);
		getTradersList(false);
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

	private void getTradersList(boolean forceUpdate) {
		InvestmentsFilter filter = new InvestmentsFilter();
		getTradersSubscription = investManager.getTradersList(filter, forceUpdate)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTradersList,
						this::handleGetTradersListError);
	}

	private void handleGetTradersList(InvestmentProgramsViewModel model) {
		getViewState().setRefreshing(false);
		getViewState().setInvestmentPrograms(model.getInvestments());
	}

	private void handleGetTradersListError(Throwable error) {
		getViewState().setRefreshing(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(OnTraderItemListClicked event) {
		router.navigateTo(Screens.TRADER_DETAILS, event.investmentProgram);
	}
}
