package vision.genesis.clientapp.feature.main.settings.referral_program.referral_friends;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.ItemsViewModelReferralFriend;
import io.swagger.client.model.ReferralFriend;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.PartnershipManager;
import vision.genesis.clientapp.model.events.SetReferralFriendsCountEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

@InjectViewState
public class ReferralFriendsPresenter extends MvpPresenter<ReferralFriendsView>
{
	private static int TAKE = 20;

	@Inject
	public PartnershipManager partnershipManager;

	private Subscription getReferralsSubscription;

	private List<ReferralFriend> referrals = new ArrayList<>();

	private int skip;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);

		getReferrals(true);
	}

	@Override
	public void onDestroy() {
		if (getReferralsSubscription != null) {
			getReferralsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getReferrals(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getReferrals(false);
	}

	private void getReferrals(boolean forceUpdate) {
		if (partnershipManager != null) {
			if (forceUpdate) {
				skip = 0;
			}
			getReferralsSubscription = partnershipManager.getReferrals(skip, TAKE)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetReferralsSuccess,
							this::handleGetReferralsError);
		}
	}

	private void handleGetReferralsSuccess(ItemsViewModelReferralFriend response) {
		getReferralsSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (skip == 0) {
			referrals.clear();
		}

		EventBus.getDefault().post(new SetReferralFriendsCountEvent(response.getTotal()));

		List<ReferralFriend> newReferrals = response.getItems();

		referrals.addAll(newReferrals);

		if (skip == 0) {
			getViewState().setReferrals(newReferrals);
		}
		else {
			getViewState().addReferrals(newReferrals);
		}

		skip += TAKE;
	}

	private void handleGetReferralsError(Throwable throwable) {
		getReferralsSubscription.unsubscribe();

		getViewState().showProgress(false);
		getViewState().showEmpty(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
