package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.programs.DashboardPagerAdapter;
import vision.genesis.clientapp.feature.main.tooltip.TooltipActivity;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.TooltipModel;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class InvestorDashboardFragment extends BaseFragment implements InvestorDashboardView, ViewPager.OnPageChangeListener
{
//	@BindView(R.id.refresh_layout)
//	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.tab_layout_chart)
	public TabLayout tabLayoutChart;

	@BindView(R.id.investor_dashboard_header_view_pager)
	public ViewPager headerViewPager;

	@BindView(R.id.scroll_view_events)
	public HorizontalScrollView eventsScrollView;

	@BindView(R.id.group_events)
	public ViewGroup eventsGroup;

	@BindView(R.id.tab_layout_assets)
	public TabLayout tabLayoutAssets;

	@BindView(R.id.view_pager_dashboard)
	public ViewPager viewPagerAssets;

	@InjectPresenter
	InvestorDashboardPresenter investorDashboardPresenter;

	private TabLayout.OnTabSelectedListener headerTabSelectedListener;

	private TabLayout.OnTabSelectedListener assetsTabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutChartOnPageChangeListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutAssetsOnPageChangeListener;

	private DashboardPagerAdapter pagerAdapter;

	private DashboardHeaderPagerAdapter dashboardHeaderPagerAdapter;

	private Fragment currentFragment;

	private DateRange dateRange = new DateRange();

	private Unbinder unbinder;

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(investorDashboardPresenter);
		}
	}

//	@OnClick(R.id.button_see_all)
//	public void onSeeAllEventClicked() {
//		if (getActivity() != null)
//			PortfolioEventsActivity.startWith(getActivity());
//	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), ThemeUtil.getCurrentThemeResource());
		LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
		return localInflater.inflate(R.layout.fragment_investor_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

//		initRefreshLayout();
		initHeaderTabs();
		initHeaderViewPager();
		initAssetsTabs();
		initAssetsViewPager();
	}

	@Override
	public void onResume() {
		super.onResume();
		investorDashboardPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (pagerAdapter != null)
			pagerAdapter.destroy();

		if (headerTabSelectedListener != null)
			tabLayoutChart.removeOnTabSelectedListener(headerTabSelectedListener);

		if (assetsTabSelectedListener != null)
			tabLayoutAssets.removeOnTabSelectedListener(assetsTabSelectedListener);

		if (tabLayoutChartOnPageChangeListener != null)
			headerViewPager.removeOnPageChangeListener(tabLayoutChartOnPageChangeListener);

		if (tabLayoutAssetsOnPageChangeListener != null)
			viewPagerAssets.removeOnPageChangeListener(tabLayoutAssetsOnPageChangeListener);

		if (headerViewPager != null)
			headerViewPager.clearOnPageChangeListeners();

		if (viewPagerAssets != null)
			viewPagerAssets.clearOnPageChangeListeners();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void showTooltip(View view, int tooltipTextResId) {
		int[] viewLocation = new int[2];
		view.getLocationInWindow(viewLocation);
		float viewX = viewLocation[0];
		float viewY = viewLocation[1];

		TooltipModel tooltipModel = new TooltipModel(
				viewX + view.getWidth() / 2,
				viewY,
				viewY + view.getHeight(),
				getString(tooltipTextResId));

		if (getActivity() != null)
			TooltipActivity.startWith(getActivity(), tooltipModel);
	}

	private void setFonts() {
//		portfolioEventsLabel.setTypeface(TypefaceUtil.semibold());
	}

//	private void initRefreshLayout() {
//		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
//				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
//				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
//		refreshLayout.setOnRefreshListener(() -> investorDashboardPresenter.onSwipeRefresh());
//	}

	private void initHeaderTabs() {
		tabLayoutChart.addTab(tabLayoutChart.newTab().setText(GenesisVisionApplication.INSTANCE.getString(R.string.portfolio)), true);
		tabLayoutChart.addTab(tabLayoutChart.newTab().setText(GenesisVisionApplication.INSTANCE.getString(R.string.profit)));
		tabLayoutChart.setTabGravity(TabLayout.GRAVITY_FILL);

		headerTabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				headerViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		};

		tabLayoutChart.addOnTabSelectedListener(headerTabSelectedListener);
	}

	private void initHeaderViewPager() {
		dashboardHeaderPagerAdapter = new DashboardHeaderPagerAdapter(getChildFragmentManager());
		headerViewPager.setAdapter(dashboardHeaderPagerAdapter);

		tabLayoutChartOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayoutChart);
		headerViewPager.addOnPageChangeListener(tabLayoutChartOnPageChangeListener);
	}

	private void initAssetsTabs() {
		tabLayoutAssets.addTab(tabLayoutAssets.newTab().setText(GenesisVisionApplication.INSTANCE.getString(R.string.programs)), true);
		tabLayoutAssets.addTab(tabLayoutAssets.newTab().setText(GenesisVisionApplication.INSTANCE.getString(R.string.funds)));
		tabLayoutAssets.setTabGravity(TabLayout.GRAVITY_FILL);

		assetsTabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPagerAssets.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		};

		tabLayoutAssets.addOnTabSelectedListener(assetsTabSelectedListener);
	}

	private void initAssetsViewPager() {
		pagerAdapter = new DashboardPagerAdapter(getActivity().getSupportFragmentManager());
		viewPagerAssets.setAdapter(pagerAdapter);

		tabLayoutAssetsOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayoutAssets);
		viewPagerAssets.addOnPageChangeListener(tabLayoutAssetsOnPageChangeListener);
		viewPagerAssets.addOnPageChangeListener(this);
	}

	@Override
	public void showProgressBar(boolean show) {
	}

	@Override
	public void setRefreshing(boolean refreshing) {
//		if (refreshLayout != null)
//			refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPagerAssets);
	}

	@Override
	public void setAssetsCount(Integer programsCount, Integer fundsCount) {

	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void setChartData(DashboardChartValue chart) {
		dashboardHeaderPagerAdapter.setPortfolioChart(chart);
	}

	@Override
	public void setPortfolioEvents(List<DashboardPortfolioEvent> events) {
		eventsGroup.removeAllViews();
		for (DashboardPortfolioEvent event : events) {
			PortfolioEventDashboardView eventView = new PortfolioEventDashboardView(getContext());
			eventView.setEvent(event);
			eventsGroup.addView(eventView);
		}
	}

	@Override
	public void onShow() {
		investorDashboardPresenter.onResume();
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof DashboardPagerAdapter.OnPageVisibilityChanged)
			((DashboardPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof DashboardPagerAdapter.OnPageVisibilityChanged) {
			((DashboardPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}