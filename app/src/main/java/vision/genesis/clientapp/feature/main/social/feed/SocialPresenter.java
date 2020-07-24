package vision.genesis.clientapp.feature.main.social.feed;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class SocialPresenter extends MvpPresenter<SocialView>
{
	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	private boolean showEvents = true;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getShowEvents();
	}

	private void getShowEvents() {
		showEvents = settingsManager.getShowEvents();
		getViewState().initViewPager(showEvents);
		getViewState().setShowEventsChecked(showEvents);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	void onResume() {
	}

	void onShowEventsCheckChanged(boolean checked) {
		showEvents = checked;
		settingsManager.saveShowEvents(checked);
		getViewState().setShowEventsChecked(showEvents);
	}
}
