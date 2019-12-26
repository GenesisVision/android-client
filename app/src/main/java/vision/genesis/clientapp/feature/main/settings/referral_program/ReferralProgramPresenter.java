package vision.genesis.clientapp.feature.main.settings.referral_program;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.model.events.SetReferralFriendsCountEvent;
import vision.genesis.clientapp.model.events.SetReferralHistoryCountEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

@InjectViewState
public class ReferralProgramPresenter extends MvpPresenter<ReferralProgramView>
{
	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	@Subscribe
	public void onEventMainThread(SetReferralFriendsCountEvent event) {
		getViewState().setReferralFriendsCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetReferralHistoryCountEvent event) {
		getViewState().setHistoryCount(event.getCount());
	}
}
