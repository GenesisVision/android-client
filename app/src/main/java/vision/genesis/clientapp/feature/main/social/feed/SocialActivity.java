package vision.genesis.clientapp.feature.main.social.feed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import androidx.appcompat.widget.SwitchCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.social.post.PostsListFragment;
import vision.genesis.clientapp.feature.main.social.trending.TrendingBottomSheetView;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialActivity extends BaseSwipeBackActivity implements SocialView, ViewPager.OnPageChangeListener
{
	private static final String EXTRA_SHOW_PAGE = "extra_show_page";

	public static void startWith(Activity activity, String showPage) {
		Intent intent = new Intent(activity.getApplicationContext(), SocialActivity.class);
		intent.putExtra(EXTRA_SHOW_PAGE, showPage);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.coordinator_layout)
	public CoordinatorLayout coordinatorLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.switch_show_events)
	public SwitchCompat showEventsSwitch;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_feed)
	public ViewPager viewPager;

	@BindView(R.id.group_filtered_posts)
	public ViewGroup filteredPostsGroup;

	@BindView(R.id.view_trending)
	public TrendingBottomSheetView trendingView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SocialPresenter presenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private SocialPagerAdapter pagerAdapter;

	private String showPage;

	private PostsListFragment postsListFragment;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_close_filtered_posts)
	public void onCloseFilteredPostsClicked() {
		if (trendingView != null) {
			trendingView.clearTags();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_social);

		ButterKnife.bind(this);

		if (savedInstanceState == null) {
			postsListFragment = PostsListFragment.with(null);
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.content, postsListFragment)
					.disallowAddToBackStack()
					.commit();
		}

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			showPage = getIntent().getExtras().getString(EXTRA_SHOW_PAGE);
		}

		initListener();
		initTabs();

		trendingView.setListener(presenter);
	}

	private void initListener() {
		showEventsSwitch.setOnCheckedChangeListener((view, checked) ->
				presenter.onShowEventsCheckChanged(checked));
	}

	@Override
	public void onDestroy() {
		if (pagerAdapter != null) {
			pagerAdapter.destroy();
		}
		if (trendingView != null) {
			trendingView.onDestroy();
		}

		if (tabSelectedListener != null && tabLayout != null) {
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);
		}

		if (tabLayoutOnPageChangeListener != null && viewPager != null) {
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
		}

		if (viewPager != null) {
			viewPager.clearOnPageChangeListeners();
		}

		super.onDestroy();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(trendingView);
		if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
			bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
		}
		else if (filteredPostsGroup.getVisibility() == View.VISIBLE) {
			onCloseFilteredPostsClicked();
		}
		else {
			finishActivity();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		presenter.onResume();
		if (pagerAdapter != null) {
			pagerAdapter.sendUpdate();
		}
	}

	private void initTabs() {
		TabLayout.Tab liveTab = tabLayout.newTab().setCustomView(getTabView(R.string.live)).setTag("live");
		TabLayout.Tab hotTab = tabLayout.newTab().setCustomView(getTabView(R.string.hot)).setTag("hot");
		TabLayout.Tab feedTab = tabLayout.newTab().setCustomView(getTabView(R.string.feed)).setTag("feed");

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(DetailsTabView.class)) {
					((DetailsTabView) tab.getCustomView()).setSelectedState(true);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(DetailsTabView.class)) {
					((DetailsTabView) tab.getCustomView()).setSelectedState(false);
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(DetailsTabView.class)) {
					((DetailsTabView) tab.getCustomView()).setSelectedState(true);
				}
			}
		};

		tabLayout.addOnTabSelectedListener(tabSelectedListener);

		addPage(liveTab, isTabSelected(liveTab));
		addPage(hotTab, isTabSelected(hotTab));
		addPage(feedTab, isTabSelected(feedTab));
	}

	private boolean isTabSelected(TabLayout.Tab tab) {
		if (tab.getTag().toString().equals("live")) {
			if (showPage == null) {
				return true;
			}
			return showPage.equals(SocialLiveView.TYPE_LIVE);
		}
		else if (tab.getTag().toString().equals("hot")) {
			if (showPage == null) {
				return false;
			}
			return showPage.equals(SocialLiveView.TYPE_HOT);
		}
		else if (tab.getTag().toString().equals("feed")) {
			if (showPage == null) {
				return false;
			}
			return showPage.equals(SocialLiveView.TYPE_FEED);
		}
		return false;
	}

	private View getTabView(int textResId) {
		DetailsTabView view = new DetailsTabView(this);
		view.setData(textResId);
		return view;
	}

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION) {
			return;
		}

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null) {
			pagerAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void initViewPager(boolean showEvents) {
		pagerAdapter = new SocialPagerAdapter(getSupportFragmentManager(), tabLayout, showEvents);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);

		viewPager.setCurrentItem(getCurrentItem());
	}

	private int getCurrentItem() {
		if (showPage == null) {
			return 0;
		}
		if (showPage.equals(SocialLiveView.TYPE_LIVE)) {
			return 0;
		}
		if (showPage.equals(SocialLiveView.TYPE_HOT)) {
			return 1;
		}
		if (showPage.equals(SocialLiveView.TYPE_FEED)) {
			return 2;
		}
		return 0;
	}

	@Override
	public void setShowEventsChecked(boolean checked) {
		showEventsSwitch.setChecked(checked);
		if (pagerAdapter != null) {
			pagerAdapter.setShowEvents(checked);
		}
	}

	@Override
	public void showTrendingBottomSheet() {
		BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(trendingView);
		bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
	}

	@Override
	public void showFilteredPosts(boolean show) {
		ValueAnimator alphaAnim = ValueAnimator.ofFloat(filteredPostsGroup.getAlpha(), show ? 1f : 0f);
		alphaAnim.addUpdateListener(animation -> {
			if (filteredPostsGroup != null) {
				filteredPostsGroup.setAlpha((float) alphaAnim.getAnimatedValue());
			}
		});
		alphaAnim.setDuration(300);
		alphaAnim.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				if (show) {
					filteredPostsGroup.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				if (!show) {
					filteredPostsGroup.setVisibility(View.GONE);
				}
			}
		});
		alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnim.start();

		if (!show) {
			postsListFragment.clearPostsList();
		}
	}

	@Override
	public void setFilter(PostsFilter filter) {
		if (postsListFragment != null) {
			postsListFragment.setFilter(filter);
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			coordinatorLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, appBarLayout);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}
}