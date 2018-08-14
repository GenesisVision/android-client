package vision.genesis.clientapp.feature.main.portfolio_events.fragment;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.DashboardPortfolioEvent;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.InvestorDashboardManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

@InjectViewState
public class PortfolioEventsListPresenter extends MvpPresenter<PortfolioEventsListView>
{
	@Inject
	public Context context;

	@Inject
	public InvestorDashboardManager investorDashboardManager;

	private UUID productId;

	private boolean idSet = false;

	private Subscription eventsSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setRefreshing(true);
		getEvents();
	}

	@Override
	public void onDestroy() {
		if (eventsSubscription != null)
			eventsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onShow() {
		getEvents();
	}

	void onSwipeRefresh() {
		getEvents();
	}

	void setProductId(UUID productId) {
		this.productId = productId;
		idSet = true;
		getViewState().setRefreshing(true);
		getEvents();
	}

	private void getEvents() {
		if (idSet) {
			if (eventsSubscription != null)
				eventsSubscription.unsubscribe();
			eventsSubscription = investorDashboardManager.getPortfolioEvents()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetEventsResponse,
							this::handleGetEventsError);
		}
	}

	private void handleGetEventsResponse(List<DashboardPortfolioEvent> events) {
//		getViewState().setRefreshing(false);

		getViewState().setEvents(events);

	}

	private void handleGetEventsError(Throwable error) {
//		getViewState().setRefreshing(false);


	}
}
