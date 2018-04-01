package vision.genesis.clientapp.feature.main.program.trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TradesViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

@InjectViewState
public class TradesPresenter extends MvpPresenter<TradesView>
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public InvestManager investManager;

	private Subscription tradesSubscription;

	private int skip = 0;

	private TradesFilter filter = new TradesFilter();

	private UUID programId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		filter.setSkip(0);
		filter.setTake(TAKE);
		filter.setSorting(TradesFilter.SortingEnum.BYDATEDESC);

		if (programId != null)
			getTrades(true);
	}

	@Override
	public void onDestroy() {
		if (tradesSubscription != null)
			tradesSubscription.unsubscribe();

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
		filter.setInvestmentProgramId(programId);
		if (investManager != null)
			getTrades(true);
	}

	void onSwipeRefresh() {
		getTrades(true);
	}

	void onLastListItemVisible() {
		getTrades(false);
	}

	private void getTrades(boolean forceUpdate) {
		if (forceUpdate) {
			getViewState().setRefreshing(true);
			skip = 0;
			filter.setSkip(skip);
		}

		if (tradesSubscription != null)
			tradesSubscription.unsubscribe();
		tradesSubscription = investManager.getProgramTrades(filter)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetTradesResponse,
						this::handleGetTradesError);
	}

	private void handleGetTradesResponse(TradesViewModel model) {
		tradesSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		List<OrderModel> trades = model.getTrades();

		if (skip == 0)
			getViewState().setTrades(trades);
		else
			getViewState().addTrades(trades);

		skip += TAKE;
		filter.setTake(TAKE);
		filter.setSkip(skip);
	}

	private void handleGetTradesError(Throwable error) {
		tradesSubscription.unsubscribe();

		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}
}
