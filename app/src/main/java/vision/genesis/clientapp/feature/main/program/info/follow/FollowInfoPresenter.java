package vision.genesis.clientapp.feature.main.program.info.follow;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.ProgramFollowDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.User;

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

	private Subscription signalsInfoSubscription;

	private UUID followId;

	private Boolean userLoggedOn;

	private ProgramFollowDetailsFull details;

	private AttachToSignalProviderInfo signalsInfo;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

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

		if (signalsInfoSubscription != null) {
			signalsInfoSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setDetails(ProgramFollowDetailsFull followDetails) {
		this.followId = followDetails.getId();
		this.details = followDetails;
	}

	void onShow() {
		getFollowDetails();
	}

	void onShowSubscriptionSettingsClicked(boolean isEdit) {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (details != null) {
			SubscriptionSettingsModel model = new SubscriptionSettingsModel();

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
			getViewState().showSubscriptionSettings(model, isEdit);
//			}
		}
	}

	void onUnfollowTradesClicked() {
		getViewState().showUnfollowTradesActivity(followId, details.getPublicInfo().getTitle());
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
	}

	private void userLoggedOff() {
		userLoggedOn = false;
		getViewState().showSubscriptionButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}
}
