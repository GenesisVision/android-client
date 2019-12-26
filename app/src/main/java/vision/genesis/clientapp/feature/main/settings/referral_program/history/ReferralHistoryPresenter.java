package vision.genesis.clientapp.feature.main.settings.referral_program.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ItemsViewModelRewardDetails;
import io.swagger.client.model.RewardDetails;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.managers.PartnershipManager;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.SetReferralHistoryCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

@InjectViewState
public class ReferralHistoryPresenter extends MvpPresenter<ReferralHistoryView> implements DateRangeBottomSheetFragment.OnDateRangeChangedListener
{
	private static int TAKE = 20;

	@Inject
	public PartnershipManager partnershipManager;

	private Subscription getEventsSubscription;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private List<RewardDetails> events = new ArrayList<>();

	private int skip;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getViewState().setDateRange(dateRange);

		getEvents(true);
	}

	@Override
	public void onDestroy() {
		if (getEventsSubscription != null) {
			getEventsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getEvents(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getEvents(false);
	}

	private void getEvents(boolean forceUpdate) {
		if (dateRange != null && partnershipManager != null) {
			if (forceUpdate) {
				skip = 0;
			}
			getEventsSubscription = partnershipManager.getRewardsHistory(dateRange, skip, TAKE)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetEventsSuccess,
							this::handleGetEventsError);
		}
	}

	private void handleGetEventsSuccess(ItemsViewModelRewardDetails response) {
		getEventsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			events.clear();
		}

		EventBus.getDefault().post(new SetReferralHistoryCountEvent(response.getTotal()));

		List<RewardDetails> newEvents = response.getItems();

		events.addAll(newEvents);

		if (skip == 0) {
			getViewState().setEvents(newEvents);
		}
		else {
			getViewState().addEvents(newEvents);
		}

		skip += TAKE;
	}

	private void handleGetEventsError(Throwable throwable) {
		getEventsSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onDateRangeChanged(DateRange dateRange) {
		this.dateRange = dateRange;
		getViewState().setDateRange(dateRange);
		getViewState().showProgress(true);
		getEvents(true);
	}
}
