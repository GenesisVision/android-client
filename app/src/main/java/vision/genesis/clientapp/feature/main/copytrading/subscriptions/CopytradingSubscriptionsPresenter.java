package vision.genesis.clientapp.feature.main.copytrading.subscriptions;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.SignalSubscription;
import io.swagger.client.model.SignalSubscriptionItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.events.ShowCopytradingCommissionsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/12/2019.
 */

@InjectViewState
public class CopytradingSubscriptionsPresenter extends MvpPresenter<CopytradingSubscriptionsView>
{
	private static int TAKE = 20;

	@Inject
	public FollowsManager followsManager;

	private Subscription getSubscriptionsSubscription;

	private UUID accountId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		getSubscriptions();
	}

	@Override
	public void onDestroy() {
		if (getSubscriptionsSubscription != null) {
			getSubscriptionsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID accountId) {
		this.accountId = accountId;

		getSubscriptions();
	}

	void onShow() {
		getSubscriptions();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getSubscriptions();
	}


	private void getSubscriptions() {
		if (followsManager != null && accountId != null) {
			if (getSubscriptionsSubscription != null) {
				getSubscriptionsSubscription.unsubscribe();
			}
			getSubscriptionsSubscription = followsManager.getMastersForMyAccount(accountId, false)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetSubscriptionsSuccess,
							this::handleGetSubscriptionsError);
		}
	}

	private void handleGetSubscriptionsSuccess(SignalSubscriptionItemsViewModel response) {
		getSubscriptionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		List<SignalSubscription> activeList = new ArrayList<>();
		List<SignalSubscription> archiveList = new ArrayList<>();
		for (SignalSubscription item : response.getItems()) {
			if (item.getStatus().toLowerCase().equals("active")) {
				activeList.add(item);
			}
			else {
				archiveList.add(item);
			}
		}
		getViewState().setData(activeList, archiveList);
	}

	private void handleGetSubscriptionsError(Throwable throwable) {
		getSubscriptionsSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(ShowCopytradingCommissionsEvent event) {

	}
}
