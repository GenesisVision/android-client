package vision.genesis.clientapp.feature.main.notifications;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.NotificationList;
import io.swagger.client.model.NotificationViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

@InjectViewState
public class NotificationsPresenter extends MvpPresenter<NotificationsView>
{
	private static final int TAKE = 20;

	@Inject
	public Context context;

	@Inject
	public NotificationsManager notificationsManager;

	private Subscription notificationsSubscription;

	private int skip = 0;

	private List<NotificationViewModel> notifications = new ArrayList<>();

	private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getNotifications(true);
	}

	@Override
	public void onDestroy() {
		if (notificationsSubscription != null)
			notificationsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getNotifications(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getNotifications(false);
	}


	private void getNotifications(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
		}

		if (notificationsSubscription != null)
			notificationsSubscription.unsubscribe();
		notificationsSubscription = notificationsManager.getNotifications(skip, TAKE)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetNotificationsResponse,
						this::handleGetNotificationsError);
	}

	private void handleGetNotificationsResponse(NotificationList response) {
		notificationsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (skip == 0) {
			notifications.clear();
			sections.clear();
		}

		List<NotificationViewModel> newNotifications = response.getNotifications();

		int index = notifications.size();
		for (NotificationViewModel newNotification : newNotifications) {
			String dateString = DateTimeUtil.formatShortDate(newNotification.getDate());
			String lastSectionDate = sections.isEmpty() ? "" : sections.get(sections.size() - 1).getTitle().toString();
			if (!lastSectionDate.equals(dateString))
				sections.add(new SimpleSectionedRecyclerViewAdapter.Section(index, dateString));
			index++;
		}

		notifications.addAll(newNotifications);

		if (skip == 0) {
			getViewState().setNotifications(newNotifications, sections);
		}
		else {
			getViewState().addNotifications(newNotifications, sections);
		}

		skip += TAKE;
	}

	private void handleGetNotificationsError(Throwable throwable) {
		notificationsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(ShowProgramDetailsEvent event) {
		getViewState().showProgramDetails(event.programDetailsModel);
	}

	@Subscribe
	public void onEventMainThread(ShowFundDetailsEvent event) {
		getViewState().showFundDetails(event.getFundDetailsModel());
	}
}
