package vision.genesis.clientapp.feature.main.fund;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.FundDetailsFull;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.notifications.fund.FundNotificationsSettingsActivity;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

public class FundDetailsActivity extends BaseSwipeBackActivity implements FundDetailsView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, FundDetailsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), FundDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.toolbar_fund_logo)
	public ProgramLogoView toolbarFundLogo;

	@BindView(R.id.toolbar_fund_name)
	public TextView toolbarFundName;

	@BindView(R.id.group_toolbar_buttons)
	public ViewGroup toolbarButtons;

	@BindView(R.id.button_notifications)
	public ImageView notificationsButton;

	@BindView(R.id.button_favorite)
	public ImageView favoriteButton;

	@BindView(R.id.fund_logo)
	public SimpleDraweeView fundLogo;

	@BindView(R.id.fund_name)
	public TextView fundName;

	@BindView(R.id.app_bar_layout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.collapsing_toolbar_screen)
	public View collapsingToolbarScreen;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_fund_details)
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
	FundDetailsPresenter fundDetailsPresenter;

	private FundDetailsFull fundDetails;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab infoTab;

	private TabLayout.Tab structureTab;

	private TabLayout.Tab profitTab;

	private TabLayout.Tab balanceTab;

	private TabLayout.Tab eventsTab;

	private FundDetailsPagerAdapter pagerAdapter;

	private FundDetailsModel model;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		fundDetailsPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_notifications)
	public void onNotificationsClicked() {
		FundNotificationsSettingsActivity.startWith(this, model.getFundId(), model.getFundName());
	}

	@OnClick(R.id.button_favorite)
	public void onFavoriteClicked() {
		if (fundDetails != null && fundDetails.getPersonalFundDetails() != null) {
			fundDetails.getPersonalFundDetails().setIsFavorite(!fundDetails.getPersonalFundDetails().isIsFavorite());
			setFavoriteButtonImage(fundDetails.getPersonalFundDetails().isIsFavorite());
			fundDetailsPresenter.onFavoriteButtonClicked(fundDetails.getPersonalFundDetails().isIsFavorite());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fund_details);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);

			initRefreshLayout();
			initTabs();
			initViewPager(model.getFundId());
			updateHeader();

			fundDetailsPresenter.setFundId(model.getFundId());

			setAnimations();
		}
		else {
			Timber.e("Passed empty model to FundDetailsActivity");
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
			fundDetailsPresenter.onSwipeRefresh();
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
			toolbarFundLogo.setAlpha(1 - toolbarAlphaPercent);
			toolbarFundName.setAlpha(1 - toolbarAlphaPercent);

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
		toolbarFundName.setTypeface(TypefaceUtil.semibold());
		fundName.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader() {
		GenericDraweeHierarchy hierarchy = fundLogo.getHierarchy();
		hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(model.getFundColor())));
		fundLogo.setHierarchy(hierarchy);
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(ImageUtils.getImageUri(model.getLogo())))
				.setResizeOptions(new ResizeOptions(300, 300))
				.build();
		PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
				.setOldController(fundLogo.getController())
				.setImageRequest(request)
				.build();
		fundLogo.setController(controller);

		toolbarFundLogo.setImage(model.getLogo(), model.getFundColor(), 50, 50);
		toolbarFundLogo.hideLevel();

		fundName.setText(model.getFundName());
		toolbarFundName.setText(model.getFundName());

		setNotificationsButtonImage(model.isHasNotifications());
		setFavoriteButtonImage(model.isFavorite());
	}

	private void initTabs() {
		infoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("info");
		structureTab = tabLayout.newTab().setCustomView(getTabView(R.string.structure)).setTag("structure");
		profitTab = tabLayout.newTab().setCustomView(getTabView(R.string.profit)).setTag("profit");
		balanceTab = tabLayout.newTab().setCustomView(getTabView(R.string.balance)).setTag("balance");
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
		addPage(structureTab, false);
		addPage(profitTab, false);
		addPage(balanceTab, false);
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
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
	}

	private void initViewPager(UUID fundId) {
		pagerAdapter = new FundDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, fundId);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(5);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		fundDetailsPresenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setFund(FundDetailsFull fundDetails) {
		this.fundDetails = fundDetails;

		if (fundDetails.getPersonalFundDetails() != null)
			addPage(eventsTab, false);

		model.update(fundDetails);
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
//		if (currentFragment != null && currentFragment instanceof FundDetailsPagerAdapter.OnPageVisibilityChanged)
//			((FundDetailsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
//		currentFragment = pagerAdapter.getItem(position);
//		if (pagerAdapter.getItem(position) instanceof FundDetailsPagerAdapter.OnPageVisibilityChanged) {
//			((FundDetailsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
//		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

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
		showSnackbar(message, toolbarFundLogo);
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
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setEventsCount(Integer eventsCount) {
		((DetailsTabView) eventsTab.getCustomView()).setCount(eventsCount);
	}
}
