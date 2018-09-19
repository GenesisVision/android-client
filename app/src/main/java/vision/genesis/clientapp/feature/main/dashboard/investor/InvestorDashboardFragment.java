package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.programs.DashboardPagerAdapter;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.PortfolioAssetData;
import vision.genesis.clientapp.model.events.HideBottomNavigationEvent;
import vision.genesis.clientapp.model.events.ShowBottomNavigationEvent;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.ui.common.CustomBottomSheetBehavior;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class InvestorDashboardFragment extends BaseFragment implements InvestorDashboardView, ViewPager.OnPageChangeListener
{
//	@BindView(R.id.refresh_layout)
//	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.header)
	public ViewGroup header;

	@BindView(R.id.coordinator_layout)
	public CoordinatorLayout coordinatorLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.notifications_dot)
	public View notificationsDot;

	@BindView(R.id.text_currency)
	public TextView currencyText;

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

	@BindView(R.id.assets_bottomsheet)
	public ViewGroup assetsBottomSheet;

	@BindView(R.id.bottomsheet_title)
	public TextView bottomSheetTitle;

	@BindView(R.id.assets_recycler_view)
	public RecyclerView assetsRecyclerView;

	@InjectPresenter
	InvestorDashboardPresenter investorDashboardPresenter;

	//TODO: assetsBottomSheet recyclerView scroll
	private RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener()
	{
		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
			setScrollable(assetsBottomSheet, rv);
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {

		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

		}
	};

	private TabLayout.OnTabSelectedListener headerTabSelectedListener;

	private TabLayout.OnTabSelectedListener assetsTabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutChartOnPageChangeListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutAssetsOnPageChangeListener;

	private DashboardPagerAdapter pagerAdapter;

	private DashboardHeaderPagerAdapter dashboardHeaderPagerAdapter;

	private Fragment currentFragment;

	private DateRange dateRange = new DateRange();

	private CurrencyEnum baseCurrency;

	private Unbinder unbinder;

	private ViewPager.OnPageChangeListener headerViewPagerPageChangeListener = new ViewPager.OnPageChangeListener()
	{
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			dashboardHeaderPagerAdapter.chartViewModeTurnOff();
		}

		@Override
		public void onPageSelected(int position) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	};

	private AppBarLayout.OnOffsetChangedListener appBarLayoutOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener()
	{
		Integer previousOffset;

		@Override
		public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
			if (previousOffset != null && verticalOffset < -100 && verticalOffset < previousOffset) {
				dashboardHeaderPagerAdapter.chartViewModeTurnOff();
			}
			previousOffset = verticalOffset;
		}
	};

	private PortfolioAssetsAdapter portfolioAssetsAdapter;

	@OnClick(R.id.group_notifications)
	public void onNotificationsClicked() {

	}

	@OnClick(R.id.group_currency)
	public void onCurrencyClicked() {
		if (getActivity() != null) {
			SelectCurrencyFragment fragment = SelectCurrencyFragment.with(baseCurrency.getValue());
			fragment.setListener(investorDashboardPresenter);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

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
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), ThemeUtil.getCurrentThemeResource());
		LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
		return localInflater.inflate(R.layout.fragment_investor_dashboard, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

//		initRefreshLayout();
		initHeaderTabs();
		initHeaderViewPager();
		initAssetsTabs();
		initAssetsViewPager();
		initAssetsBottomSheet();
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

	@Override
	public boolean onBackPressed() {
		CustomBottomSheetBehavior assetsBottomsheetBehavior = CustomBottomSheetBehavior.from(assetsBottomSheet);
		if (assetsBottomsheetBehavior.getState() == CustomBottomSheetBehavior.STATE_EXPANDED) {
			assetsBottomsheetBehavior.setState(CustomBottomSheetBehavior.STATE_COLLAPSED);
			return true;
		}
		if (assetsBottomsheetBehavior.getState() != CustomBottomSheetBehavior.STATE_HIDDEN) {
			assetsBottomsheetBehavior.setState(CustomBottomSheetBehavior.STATE_HIDDEN);
			return true;
		}
		return false;
	}

	private void setFonts() {
		currencyText.setTypeface(TypefaceUtil.semibold());
		bottomSheetTitle.setTypeface(TypefaceUtil.semibold());
	}

//	private void showTooltip(View view, int tooltipTextResId) {
//		int[] viewLocation = new int[2];
//		view.getLocationInWindow(viewLocation);
//		float viewX = viewLocation[0];
//		float viewY = viewLocation[1];
//
//		TooltipModel tooltipModel = new TooltipModel(
//				viewX + view.getWidth() / 2,
//				viewY,
//				viewY + view.getHeight(),
//				getString(tooltipTextResId));
//
//		if (getActivity() != null)
//			TooltipActivity.startWith(getActivity(), tooltipModel);
//	}

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
		pagerAdapter = new DashboardPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
		viewPagerAssets.setAdapter(pagerAdapter);

		tabLayoutAssetsOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayoutAssets);
		viewPagerAssets.addOnPageChangeListener(tabLayoutAssetsOnPageChangeListener);
		viewPagerAssets.addOnPageChangeListener(this);
	}

	private void initAssetsBottomSheet() {
		appBarLayout.addOnOffsetChangedListener(appBarLayoutOffsetChangedListener);
		CustomBottomSheetBehavior.from(assetsBottomSheet).setState(CustomBottomSheetBehavior.STATE_HIDDEN);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		assetsRecyclerView.setLayoutManager(layoutManager);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
				ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider), 43, 0);
		assetsRecyclerView.addItemDecoration(dividerItemDecoration);
		portfolioAssetsAdapter = new PortfolioAssetsAdapter();
		portfolioAssetsAdapter.setHasStableIds(true);
		assetsRecyclerView.setAdapter(portfolioAssetsAdapter);
		assetsRecyclerView.setNestedScrollingEnabled(false);
//		assetsRecyclerView.addOnItemTouchListener(onItemTouchListener);
	}

	private void setScrollable(View bottomSheet, RecyclerView recyclerView) {
		ViewGroup.LayoutParams params = bottomSheet.getLayoutParams();
		if (params instanceof CoordinatorLayout.LayoutParams) {
			CoordinatorLayout.LayoutParams coordinatorLayoutParams = (CoordinatorLayout.LayoutParams) params;
			CoordinatorLayout.Behavior behavior = coordinatorLayoutParams.getBehavior();
			if (behavior != null && behavior instanceof CustomBottomSheetBehavior)
				((CustomBottomSheetBehavior) behavior).setNestedScrollingChildRef(recyclerView);
		}
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
	public void setHaveNewNotifications(boolean have) {
		notificationsDot.setVisibility(have ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		currencyText.setText(baseCurrency.getValue());
	}

	@Override
	public void setChartViewMode(Boolean viewMode, Float chartBottomY) {
		if (viewMode) {
			appBarLayout.setExpanded(true, true);
			headerViewPager.addOnPageChangeListener(headerViewPagerPageChangeListener);

			showAssetsBottomSheet(chartBottomY + header.getHeight() + tabLayoutChart.getHeight());
		}
		else {
			headerViewPager.removeOnPageChangeListener(headerViewPagerPageChangeListener);
//			appBarLayout.removeOnOffsetChangedListener(appBarLayoutOffsetChangedListener);

			hideAssetsBottomSheet();
		}
	}

	public void showAssetsBottomSheet(float height) {
		CustomBottomSheetBehavior assetsBottomsheetBehavior = CustomBottomSheetBehavior.from(assetsBottomSheet);
		assetsBottomsheetBehavior.setPeekHeight((int) (root.getHeight() - height
				- TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics())));
		assetsBottomsheetBehavior.setState(CustomBottomSheetBehavior.STATE_COLLAPSED);
		assetsBottomsheetBehavior.setBottomSheetCallback(new CustomBottomSheetBehavior.BottomSheetCallback()
		{
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				if (newState == CustomBottomSheetBehavior.STATE_HIDDEN) {
					dashboardHeaderPagerAdapter.chartViewModeTurnOff();
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {
			}
		});
		EventBus.getDefault().post(new HideBottomNavigationEvent());
	}

	public void hideAssetsBottomSheet() {
		CustomBottomSheetBehavior.from(assetsBottomSheet).setState(CustomBottomSheetBehavior.STATE_HIDDEN);
		EventBus.getDefault().post(new ShowBottomNavigationEvent(true));
	}

	@Override
	public void setPortfolioAssets(List<PortfolioAssetData> portfolioAssets) {
		portfolioAssetsAdapter.setAssets(portfolioAssets);
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