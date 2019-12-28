package vision.genesis.clientapp.feature.main.program.info.follow;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ItemsViewModelSignalSubscription;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.SignalSubscription;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.ShowUnfollowTradesEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

@InjectViewState
public class FollowInfoPresenter extends MvpPresenter<FollowInfoView>
{
	@Inject
	public AuthManager authManager;

	@Inject
	public FollowsManager followsManager;

	private Subscription userSubscription;

	private Subscription followDetailsSubscription;

	private Subscription subscriptionsSubscription;

	private UUID followId;

	private Boolean userLoggedOn;

	private ProgramFollowDetailsFull details;

	private List<SignalSubscription> subscriptions = new ArrayList<>();

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getViewState().showProgress(true);
		getFollowDetails();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (followDetailsSubscription != null) {
			followDetailsSubscription.unsubscribe();
		}
		if (subscriptionsSubscription != null) {
			subscriptionsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setDetails(ProgramFollowDetailsFull followDetails) {
		this.followId = followDetails.getId();
		this.details = followDetails;
	}

	void onShow() {
		getFollowDetails();
		getSubscriptions();
	}

	void onShowSubscriptionSettingsClicked(boolean isEdit) {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (followId != null) {
//			SubscriptionSettingsModel model = new SubscriptionSettingsModel();

//			SignalSubscription signalSubscription = followDetails.getPersonalDetails().getSignalSubscriptions().get(0);
//			try {
//				if (signalSubscription.isHasActiveSubscription()) {
//					if (signalSubscription.getMode() != null) {
//						model.setMode(signalSubscription.getMode().getValue());
//					}
//					if (signalSubscription.getPercent() != null) {
//						model.setPercent(signalSubscription.getPercent());
//					}
//					if (signalSubscription.getOpenTolerancePercent() != null) {
//						model.setOpenTolerancePercent(signalSubscription.getOpenTolerancePercent());
//					}
//					if (signalSubscription.getFixedVolume() != null) {
//						model.setFixedVolume(signalSubscription.getFixedVolume());
//					}
//					if (signalSubscription.getFixedCurrency() != null) {
//						model.setFixedCurrency(signalSubscription.getFixedCurrency().getValue());
//					}
//				}
//			} catch (NullPointerException e) {
//				e.printStackTrace();
//			}
//			model.setProgramName(followDetails.getTitle());
//			model.setProgramId(followDetails.getId());

			//TODO:
//			if (!signalsInfo.isHasSignalAccount()) {
//				getViewState().showCreateCopytradingAccountActivity(model);
//			}
//			else {
//			getViewState().showSubscriptionSettings(model, isEdit);
//			}
			getViewState().showFollowTradesActivity(details);
		}
	}


	private void getFollowDetails() {
		if (followId != null && followsManager != null) {
			if (followDetailsSubscription != null) {
				followDetailsSubscription.unsubscribe();
			}
			followDetailsSubscription = followsManager.getFollowDetails(followId.toString())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleFollowDetailsSuccess,
							this::handleFollowDetailsError);
		}
	}

	private void handleFollowDetailsSuccess(ProgramFollowDetailsFull followDetails) {
		followDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.details = followDetails;

		getViewState().setDetails(followDetails);
	}

	private void handleFollowDetailsError(Throwable throwable) {
		followDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void getSubscriptions() {
		if (followId != null && followsManager != null && userLoggedOn) {
			if (subscriptionsSubscription != null) {
				subscriptionsSubscription.unsubscribe();
			}
			subscriptionsSubscription = followsManager.getMySubscriptionsForFollow(followId, true)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSubscriptionsSuccess,
							this::handleSubscriptionsError);
		}
	}

	private void handleSubscriptionsSuccess(ItemsViewModelSignalSubscription response) {
		subscriptionsSubscription.unsubscribe();

		this.subscriptions = new ArrayList<>();
		for (SignalSubscription item : response.getItems()) {
			if (item.getStatus().toLowerCase().equals("active")) {
				subscriptions.add(item);
			}
		}

		getViewState().setSubscriptions(subscriptions);
	}

	private void handleSubscriptionsError(Throwable throwable) {
		subscriptionsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn();
		}
	}

	private void userLoggedOn() {
		userLoggedOn = true;
		getViewState().showSubscriptionButtons(true);
		getSubscriptions();
	}

	private void userLoggedOff() {
		userLoggedOn = false;
		getViewState().showSubscriptionButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(ShowUnfollowTradesEvent event) {
		getViewState().showUnfollowTradesActivity(event.getFollowId(), event.getTradingAccountId(), event.getFollowName(), event.isExternal());
	}
}
