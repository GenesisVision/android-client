package vision.genesis.clientapp.feature.main.program;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProgramDetailsFull;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.ui.AvatarView;
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
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.toolbar_program_logo)
	public AvatarView toolbarProgramLogo;

	@BindView(R.id.toolbar_program_name)
	public TextView toolbarProgramName;

	@BindView(R.id.button_favorite)
	public ImageView favoriteButton;

	@BindView(R.id.program_logo)
	public SimpleDraweeView programLogo;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.program_name)
	public TextView programName;

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
	ProgramDetailsPresenter programDetailsPresenter;

	private ProgramDetailsFull programDetails;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

//	private TabLayout.Tab descriptionTab;

	private TabLayout.Tab infoTab;

	private TabLayout.Tab profitTab;

	private TabLayout.Tab tradesTab;

	private TabLayout.Tab eventsTab;

	private ProgramDetailsPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	private ProgramDetailsModel model;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		programDetailsPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_favorite)
	public void onFavoriteClicked() {
		if (programDetails != null) {
//			programDetails.isFavorite(!programDetails.isIsFavorite());
//			setFavoriteButtonImage(programDetails.isIsFavorite());
//			programInfoPresenter.onFavoriteButtonClicked(programDetails.isIsFavorite());
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

			initRefreshLayout();
			initTabs();
			initViewPager(model.getProgramId());
			updateHeader();

			programDetailsPresenter.setProgramId(model.getProgramId());

			setAnimations();
		}
		else {
			Timber.e("Passed empty program to ProgramDetailsActivity");
			onBackPressed();
		}
	}

	private void initRefreshLayout() {
//		refreshLayout.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			programDetailsPresenter.onSwipeRefresh();
			if (pagerAdapter != null)
				pagerAdapter.sendSwipeRefresh();
		});
	}

	private void setAnimations() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			double toolbarStartOffset = Math.abs((float) appBarLayout.getTotalScrollRange() * 0.15);
			float alphaPercent = ((float) Math.abs(verticalOffset) / ((float) appBarLayout.getTotalScrollRange() - 100));
			collapsingToolbarScreen.setAlpha(alphaPercent);

			float toolbarAlphaPercent = 1;
			if (Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() - toolbarStartOffset) {
				toolbarAlphaPercent = (float) ((appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset)) / toolbarStartOffset);
			}
			toolbarProgramLogo.setAlpha(1 - toolbarAlphaPercent);
			toolbarProgramName.setAlpha(1 - toolbarAlphaPercent);

			refreshLayout.setEnabled(verticalOffset == 0);

			pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight() - toolbar.getHeight());
		});
	}

	@Override
	protected void onDestroy() {
		if (pagerAdapter != null)
			pagerAdapter.destroy();

		if (tabSelectedListener != null)
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);

		if (tabLayoutOnPageChangeListener != null)
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);

		if (viewPager != null)
			viewPager.clearOnPageChangeListeners();

		super.onDestroy();
	}

	private void setFonts() {
		toolbarProgramName.setTypeface(TypefaceUtil.semibold());
		programName.setTypeface(TypefaceUtil.semibold());
		level.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader() {
		programLogo.setImageURI(ImageUtils.getImageUri(model.getAvatar()));
		toolbarProgramLogo.setImage(model.getAvatar(), 50, 50);
		toolbarProgramLogo.hideLevel();

		level.setText(String.valueOf(model.getLevel()));

		programName.setText(model.getProgramName());
		toolbarProgramName.setText(model.getProgramName());

		setFavoriteButtonImage(model.isFavorite());
	}

	private void initTabs() {
		infoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("info");
		profitTab = tabLayout.newTab().setCustomView(getTabView(R.string.profit)).setTag("profit");
		tradesTab = tabLayout.newTab().setCustomView(getTabView(R.string.trades)).setTag("trades");
		eventsTab = tabLayout.newTab().setCustomView(getTabView(R.string.events)).setTag("events");

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

		addPage(infoTab, true);
		addPage(profitTab, false);
		addPage(tradesTab, false);
	}

	private View getTabView(int textResId) {
		DetailsTabView view = new DetailsTabView(this);
		view.setData(textResId);
		return view;
	}

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION)
			return;

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 0, 10);
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
	}

	private void initViewPager(UUID programId) {
		pagerAdapter = new ProgramDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, programId);
		viewPager.setAdapter(pagerAdapter);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		programDetailsPresenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setProgram(ProgramDetailsFull programDetails) {
		this.programDetails = programDetails;

//		if (programDetails.isIsHistoryEnable())
		addPage(eventsTab, false);

		model.update(programDetails);
		updateHeader();

		tabsGroup.setVisibility(View.VISIBLE);
		viewPager.setVisibility(View.VISIBLE);

		pagerAdapter.sendUpdate();
	}

	private void setFavoriteButtonImage(boolean isFavorite) {

		favoriteButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), isFavorite
				? R.drawable.icon_favorite_fill
				: R.drawable.icon_favorite));
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof ProgramDetailsPagerAdapter.OnPageVisibilityChanged)
			((ProgramDetailsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof ProgramDetailsPagerAdapter.OnPageVisibilityChanged) {
			((ProgramDetailsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

//	public void onChartTouch() {
//		viewPager.requestDisallowInterceptTouchEvent(true);
//	}
//
//	public void onChartTouchEnd() {
//		viewPager.requestDisallowInterceptTouchEvent(false);
//
//	}

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
	public void showFavoriteButton(boolean show) {
		favoriteButton.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
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
}
