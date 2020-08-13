package vision.genesis.clientapp.feature.main.social.feed;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.social.trending.TrendingBottomSheetView;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.PostsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class SocialPresenter extends MvpPresenter<SocialView> implements TrendingBottomSheetView.Listener
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
		getShowTrendingFirstTime();
	}

	private void getShowTrendingFirstTime() {
		if (settingsManager.getShowTrendingFirstTime()) {
			getViewState().showTrendingBottomSheet();
			settingsManager.saveShowTrendingFirstTime(false);
		}
	}

	void onShowEventsCheckChanged(boolean checked) {
		showEvents = checked;
		settingsManager.saveShowEvents(checked);
		getViewState().setShowEventsChecked(showEvents);
	}

	@Override
	public void onTagsChanged(List<String> hashTags, List<UUID> contentIds) {
		if ((hashTags == null || hashTags.isEmpty())
				&& (contentIds == null || contentIds.isEmpty())) {
			getViewState().showFilteredPosts(false);
		}
		else {
			getViewState().showFilteredPosts(true);

			PostsFilter filter = new PostsFilter();
			filter.setHashTags(hashTags);
			filter.setTagContentIds(contentIds);
			filter.setShowOnlyUserPosts(true);
			getViewState().setFilter(filter);
		}
	}
}
