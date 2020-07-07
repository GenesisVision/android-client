package vision.genesis.clientapp.feature.main.program;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.Tag;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.events.details.EventDetailsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.notifications.follow.FollowNotificationsSettingsActivity;
import vision.genesis.clientapp.feature.main.notifications.program.ProgramNotificationsSettingsActivity;
import vision.genesis.clientapp.feature.main.program.level.ProgramLevelBottomSheetDialog;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.TagView;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramDetailsActivity extends BaseSwipeBackActivity implements ProgramDetailsView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, ProgramDetailsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.toolbar_program_logo)
	public ProgramLogoView toolbarProgramLogo;

	@BindView(R.id.toolbar_program_name)
	public TextView toolbarProgramName;

	@BindView(R.id.group_toolbar_buttons)
	public ViewGroup toolbarButtons;

	@BindView(R.id.button_notifications)
	public ImageView notificationsButton;

	@BindView(R.id.button_favorite)
	public ImageView favoriteButton;

	@BindView(R.id.program_logo)
	public SimpleDraweeView programLogo;

	@BindView(R.id.group_level)
	public ViewGroup levelGroup;

	@BindView(R.id.level_progress_bar)
	public ProgressBar levelProgress;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.level_color)
	public ImageView levelColor;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.group_tags)
	public LinearLayout tagsGroup;

	@BindView(R.id.app_bar_layout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.collapsing_toolbar_screen)
	public View collapsingToolbarScreen;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_program_details)
	public ViewPager viewPager;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.progress_bar_no_internet)
	public ProgressBar noInternetProgressBar;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@InjectPresenter
	ProgramDetailsPresenter presenter;

	private ProgramFollowDetailsFull details;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab programInfoTab;

	private TabLayout.Tab followInfoTab;

	private TabLayout.Tab ownerInfoTab;

	private TabLayout.Tab openPositionsTab;

	private TabLayout.Tab profitTab;

	private TabLayout.Tab equityTab;

	private TabLayout.Tab tradesTab;

	private TabLayout.Tab periodHistoryTab;

	private TabLayout.Tab eventsTab;

	private ProgramDetailsPagerAdapter pagerAdapter;

	private ProgramDetailsModel model;

	private boolean isPagerDragging;

	private int verticalOffset = 0;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		presenter.onTryAgainClicked();
	}

	@OnClick(R.id.level)
	public void onLevelClicked() {
		if (details != null && details.getProgramDetails() != null) {
			ProgramLevelBottomSheetDialog dialog = new ProgramLevelBottomSheetDialog();
			dialog.show(getSupportFragmentManager(), dialog.getTag());
			dialog.setData(details.getProgramDetails().getLevel(),
					false,
					details.getTradingAccountInfo().getCurrency().getValue(),
					details.getProgramDetails().getTotalAvailableInvestment());
		}
	}

	@OnClick(R.id.button_notifications)
	public void onNotificationsClicked() {
		presenter.onNotificationsClicked();
	}

	@OnClick(R.id.button_favorite)
	public void onFavoriteClicked() {
		if (details != null && details.getProgramDetails() != null) {
			details.getProgramDetails().getPersonalDetails().setIsFavorite(!details.getProgramDetails().getPersonalDetails().isIsFavorite());
			setFavoriteButtonImage(details.getProgramDetails().getPersonalDetails().isIsFavorite());
			presenter.onFavoriteButtonClicked(details.getProgramDetails().getPersonalDetails().isIsFavorite());
		}
		else if (details != null && details.getFollowDetails() != null) {
			details.getFollowDetails().getPersonalDetails().setIsFavorite(!details.getFollowDetails().getPersonalDetails().isIsFavorite());
			setFavoriteButtonImage(details.getFollowDetails().getPersonalDetails().isIsFavorite());
			presenter.onFavoriteButtonClicked(details.getFollowDetails().getPersonalDetails().isIsFavorite());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_details);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				initRefreshLayout();
				initTabs();
				updateHeader();

				presenter.setData(model);

				setAnimations();
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (presenter != null) {
			presenter.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (presenter != null) {
			presenter.onPause();
		}
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			presenter.onSwipeRefresh();
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
		});
	}

	private void setAnimations() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			this.verticalOffset = verticalOffset;
			double toolbarStartOffset = Math.abs((float) appBarLayout.getTotalScrollRange() * 0.15);
			float alphaPercent = ((float) Math.abs(verticalOffset) / ((float) appBarLayout.getTotalScrollRange() - 100));
			collapsingToolbarScreen.setAlpha(alphaPercent);

			float toolbarAlphaPercent = 1;
			if (Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() - toolbarStartOffset) {
				toolbarAlphaPercent = (float) ((appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset)) / toolbarStartOffset);
			}
			toolbarProgramLogo.setAlpha(1 - toolbarAlphaPercent);
			toolbarProgramName.setAlpha(1 - toolbarAlphaPercent);

			updateRefreshLayoutEnabled();

			if (pagerAdapter != null) {
				pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight() - toolbar.getHeight());
			}
		});
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(verticalOffset == 0 && !isPagerDragging);
	}

	@Override
	protected void onDestroy() {
		if (pagerAdapter != null) {
			pagerAdapter.destroy();
		}

		if (tabSelectedListener != null) {
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);
		}

		if (tabLayoutOnPageChangeListener != null) {
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
		}

		if (viewPager != null) {
			viewPager.clearOnPageChangeListeners();
		}

		super.onDestroy();
	}

	private void setFonts() {
		toolbarProgramName.setTypeface(TypefaceUtil.semibold());
		programName.setTypeface(TypefaceUtil.semibold());
		level.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader() {
		GenericDraweeHierarchy hierarchy = programLogo.getHierarchy();
		hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(model.getProgramColor())));
		programLogo.setHierarchy(hierarchy);
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(ImageUtils.getImageUri(model.getAvatar())))
				.setResizeOptions(new ResizeOptions(300, 300))
				.build();
		PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
				.setOldController(programLogo.getController())
				.setImageRequest(request)
				.build();
		programLogo.setController(controller);

		toolbarProgramLogo.setImage(model.getAvatar(), model.getProgramColor(), 50, 50);
		toolbarProgramLogo.hideLevel();

		int level = model.getLevel();
		if (level > 0) {
			this.levelGroup.setVisibility(View.VISIBLE);
			this.level.setText(String.valueOf(level));
			int[] levelColors = getResources().getIntArray(R.array.levelColors);
			if (level > levelColors.length) {
				level = levelColors.length;
			}
			levelColor.setColorFilter(levelColors[level - 1]);
			levelProgress.setProgress(model.getLevelProgress().intValue());
		}
		else {
			this.levelGroup.setVisibility(View.GONE);
		}

		programName.setText(model.getProgramName());
		toolbarProgramName.setText(model.getProgramName());

		setTags();

		setNotificationsButtonImage(model.isHasNotifications());
		setFavoriteButtonImage(model.isFavorite());
	}

	private void setTags() {
		if (details != null && tagsGroup != null) {
			tagsGroup.removeAllViews();
			for (Tag tag : details.getTags()) {
				addTag(createTagView(tag), tagsGroup);
			}
		}
	}

	private TagView createTagView(Tag tag) {
		TagView view = new TagView(this);
		view.setTag(tag);
		return view;
	}

	private void addTag(TagView tagView, LinearLayout tagsGroup) {
		tagsGroup.addView(tagView);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tagView.getLayoutParams();
		lp.setMargins(0,
				0,
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()),
				0);
		tagView.setLayoutParams(lp);
	}

	private void initTabs() {
		programInfoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("program_info");
		followInfoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("follow_info");
		ownerInfoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("owner_info");
		profitTab = tabLayout.newTab().setCustomView(getTabView(R.string.profit)).setTag("profit");
		equityTab = tabLayout.newTab().setCustomView(getTabView(R.string.equity)).setTag("equity");
		openPositionsTab = tabLayout.newTab().setCustomView(getTabView(R.string.open_positions)).setTag("open_positions");
		tradesTab = tabLayout.newTab().setCustomView(getTabView(R.string.trades)).setTag("trades");
		periodHistoryTab = tabLayout.newTab().setCustomView(getTabView(R.string.period_history)).setTag("period_history");
		eventsTab = tabLayout.newTab().setCustomView(getTabView(R.string.my_history)).setTag("events");

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

	private void initViewPager(ProgramFollowDetailsFull details) {
		pagerAdapter = new ProgramDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, details);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(5);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgram(ProgramFollowDetailsFull details) {
		this.details = details;

		if (pagerAdapter == null) {
			addPage(programInfoTab, true);
			addPage(profitTab, false);
			addPage(equityTab, false);
			addPage(openPositionsTab, false);
			addPage(tradesTab, false);
			addPage(periodHistoryTab, false);
		}

		finishInit();
	}

	@Override
	public void showFollow(ProgramFollowDetailsFull details) {
		this.details = details;

		if (pagerAdapter == null) {
			addPage(followInfoTab, true);
			addPage(profitTab, false);
			addPage(equityTab, false);
			addPage(openPositionsTab, false);
			addPage(tradesTab, false);
		}

		finishInit();
	}

	@Override
	public void showOwner(ProgramFollowDetailsFull details) {
		this.details = details;

		addPage(ownerInfoTab, true);
		addPage(profitTab, false);
		addPage(equityTab, false);
		addPage(openPositionsTab, false);
		addPage(tradesTab, false);
		if (this.details != null && details.getProgramDetails() != null) {
			addPage(periodHistoryTab, false);
		}

		finishInit();
	}

	private void finishInit() {
		if (details != null && (details.getPublicInfo().isIsOwnAsset() ||
				(details.getProgramDetails() != null
						&& details.getProgramDetails().getPersonalDetails() != null
						&& details.getProgramDetails().getPersonalDetails().isIsInvested()))) {
			addPage(eventsTab, false);
		}
		if (pagerAdapter == null) {
			initViewPager(details);
		}
		else {
			pagerAdapter.updateOwnerInfo(details);
		}

		if (details != null) {
			model.update(details);
		}

		updateHeader();

		tabsGroup.setVisibility(View.VISIBLE);
		viewPager.setVisibility(View.VISIBLE);

		pagerAdapter.sendUpdate();
	}

	private void setNotificationsButtonImage(boolean hasNotifications) {
		notificationsButton.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), hasNotifications
				? R.drawable.icon_notifications_fill
				: R.drawable.icon_notifications));
	}

	private void setFavoriteButtonImage(boolean isFavorite) {
		favoriteButton.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), isFavorite
				? R.drawable.icon_favorite_fill
				: R.drawable.icon_favorite));
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
//		if (currentFragment != null && currentFragment instanceof ProgramDetailsPagerAdapter.OnPageVisibilityChanged)
//			((ProgramDetailsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
//		currentFragment = pagerAdapter.getItem(position);
//		if (pagerAdapter.getItem(position) instanceof ProgramDetailsPagerAdapter.OnPageVisibilityChanged) {
//			((ProgramDetailsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
//		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		isPagerDragging = state == ViewPager.SCROLL_STATE_DRAGGING;
		updateRefreshLayoutEnabled();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showToolbarButtons(boolean show) {
		toolbarButtons.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showNoInternet(boolean show) {
//		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbarProgramLogo);
	}

	@Override
	public void showNoInternetProgress(boolean show) {
		noInternetProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

//	@Override
//	public void setRefreshing(boolean refreshing) {
//		if (refreshLayout != null)
//			refreshLayout.setRefreshing(refreshing);
//	}

	@Override
	public void showTrades() {
		tradesTab.select();
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setOpenPositionsCount(Integer openPositionsCount) {
		((DetailsTabView) openPositionsTab.getCustomView()).setCount(openPositionsCount);
	}

	@Override
	public void setTradesCount(Integer tradesCount) {
		((DetailsTabView) tradesTab.getCustomView()).setCount(tradesCount);
	}

	@Override
	public void setPeriodHistoryCount(Integer periodHistoryCount) {
		((DetailsTabView) periodHistoryTab.getCustomView()).setCount(periodHistoryCount);
	}

	@Override
	public void setEventsCount(Integer eventsCount) {
		((DetailsTabView) eventsTab.getCustomView()).setCount(eventsCount);
	}

	@Override
	public void showEventDetails(InvestmentEventViewModel event) {
		EventDetailsBottomSheetFragment fragment = new EventDetailsBottomSheetFragment();
		fragment.setData(event);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@Override
	public void showProgramNotificationsSettings(UUID programId, String programName) {
		ProgramNotificationsSettingsActivity.startWith(this, programId, programName);
	}

	@Override
	public void showFollowNotificationsSettings(UUID followId, String followName) {
		FollowNotificationsSettingsActivity.startWith(this, followId, followName);
	}
}
