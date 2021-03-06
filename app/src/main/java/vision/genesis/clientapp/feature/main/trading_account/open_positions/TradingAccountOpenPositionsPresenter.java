package vision.genesis.clientapp.feature.main.trading_account.open_positions;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TradingAccountManager;
import vision.genesis.clientapp.model.events.OnOpenPositionClickedEvent;
import vision.genesis.clientapp.model.events.SetTradingAccountDetailsOpenPositionsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

@InjectViewState
public class TradingAccountOpenPositionsPresenter extends MvpPresenter<TradingAccountOpenPositionsView>
{
	@Inject
	public Context context;

	@Inject
	public TradingAccountManager tradingAccountManager;

	private Subscription positionsSubscription;

	private UUID accountId;

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		if (accountId != null) {
			getOpenPositions();
		}
	}

	@Override
	public void onDestroy() {
		if (positionsSubscription != null) {
			positionsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setAccountId(UUID accountId) {
		this.accountId = accountId;
		if (tradingAccountManager != null) {
			getOpenPositions();
		}
	}

	void onShow() {
		getOpenPositions();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getOpenPositions();
	}

	private void getOpenPositions() {
		if (accountId != null) {

			if (positionsSubscription != null) {
				positionsSubscription.unsubscribe();
			}
			positionsSubscription = tradingAccountManager.getAccountOpenPositions(accountId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetOpenPositionsResponse,
							this::handleGetOpenPositionsError);
		}
	}

	private void handleGetOpenPositionsResponse(TradesViewModel model) {
		positionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		sections.clear();

		EventBus.getDefault().post(new SetTradingAccountDetailsOpenPositionsCountEvent(model.getTotal()));

		int index = 0;
		for (OrderModel newTrade : model.getItems()) {
			String dateString = DateTimeUtil.formatShortDate(newTrade.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString)) {
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			}
			index++;
		}

		getViewState().setOpenPositions(model, sections);
	}

	private void handleGetOpenPositionsError(Throwable error) {
		positionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(error)) {
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(OnOpenPositionClickedEvent event) {
		getViewState().showOpenPositionDetails(event.getOpenPosition(), event.getModel());
	}
}
