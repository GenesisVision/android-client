package vision.genesis.clientapp.feature.main.dashboard.investor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardChartValue;
import io.swagger.client.model.ProgramRequest;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.notifications.NotificationsActivity;
import vision.genesis.clientapp.feature.main.portfolio_events.PortfolioEventsActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.PortfolioAssetData;
import vision.genesis.clientapp.model.PortfolioEvent;
import vision.genesis.clientapp.model.events.HideBottomNavigationEvent;
import vision.genesis.clientapp.model.events.ShowBottomNavigationEvent;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.ui.common.CustomBottomSheetBehavior;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class InvestorDashboardFragment extends BaseFragment implements InvestorDashboardView, ViewPager.OnPageChangeListener
{
	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

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
	public TabLayout tabLayoutHeader;

	@BindView(R.id.investor_dashboard_header_view_pager)
	public ViewPager headerViewPager;

	@BindView(R.id.label_portfolio_events)
	public TextView portfolioEventsLabel;

	@BindView(R.id.label_show_all)
	public TextView showAllLabel;

	@BindView(R.id.scroll_view_events)
	public HorizontalScrollView eventsScrollView;

	@BindView(R.id.group_events_header)
	public ViewGroup eventsHeaderGroup;

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

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

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

	private DashboardPagerAdapter assetsPagerAdapter;

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

	private Boolean assetsBottomSheetShowing = false;

	private TabLayout.Tab programsTab;

	private TabLayout.Tab fundsTab;

	private TabLayout.Tab copytradingTab;

	@OnClick(R.id.group_notifications)
	public void onNotificationsClicked() {
		if (getActivity() != null)
			NotificationsActivity.startWith(getActivity());
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

	@OnClick(R.id.show_all_events)
	public void onShowAllEventsClicked() {
		if (getActivity() != null)
			PortfolioEventsActivity.startWith(getActivity());
	}

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

		initRefreshLayout();
		initHeaderViewPager();
		initHeaderTabs();
		initAssetsViewPager();
		initAssetsTabs();
		initAssetsBottomSheet();
		setOffsetListener();
	}

	@Override
	public void onResume() {
		super.onResume();
		investorDashboardPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (assetsPagerAdapter != null)
			assetsPagerAdapter.destroy();

		if (headerTabSelectedListener != null)
			tabLayoutHeader.removeOnTabSelectedListener(headerTabSelectedListener);

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
		portfolioEventsLabel.setTypeface(TypefaceUtil.semibold());
		showAllLabel.setTypeface(TypefaceUtil.semibold());
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
//			TooltipActivity.startFrom(getActivity(), tooltipModel);
//	}

	private void initRefreshLayout() {
//		refreshLayout.setBackgroundColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> investorDashboardPresenter.onSwipeRefresh());
	}

	private void initHeaderTabs() {
		headerTabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				headerViewPager.setCurrentItem(tab.getPosition());
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(false);
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}
		};

		tabLayoutHeader.addOnTabSelectedListener(headerTabSelectedListener);

		addTab(tabLayoutHeader, dashboardHeaderPagerAdapter,
				tabLayoutHeader.newTab().setCustomView(getTabView(R.string.portfolio)).setTag("portfolio"), true);
//		addTab(tabLayoutHeader, dashboardHeaderPagerAdapter,
//				tabLayoutHeader.newTab().setCustomView(getTabView(R.string.profit)).setTag("profit"), false);

	}

	private void initHeaderViewPager() {
		dashboardHeaderPagerAdapter = new DashboardHeaderPagerAdapter(getChildFragmentManager(), tabLayoutHeader);
		headerViewPager.setAdapter(dashboardHeaderPagerAdapter);

		tabLayoutChartOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayoutHeader);
		headerViewPager.addOnPageChangeListener(tabLayoutChartOnPageChangeListener);
	}

	private void addTab(TabLayout tabLayout, PagerAdapter pagerAdapter, TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION)
			return;

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
	}

	private View getTabView(int textResId) {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(0, textResId);
		return view;
	}

	private void initAssetsTabs() {
		programsTab = tabLayoutAssets.newTab().setCustomView(getTabView(R.string.programs)).setTag("programs");
		fundsTab = tabLayoutAssets.newTab().setCustomView(getTabView(R.string.funds)).setTag("funds");
		copytradingTab = tabLayoutAssets.newTab().setCustomView(getTabView(R.string.copytrading)).setTag("copytrading");

		assetsTabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPagerAssets.setCurrentItem(tab.getPosition());
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(false);
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}
		};

		tabLayoutAssets.addOnTabSelectedListener(assetsTabSelectedListener);

		addTab(tabLayoutAssets, assetsPagerAdapter, programsTab, true);
		addTab(tabLayoutAssets, assetsPagerAdapter, fundsTab, false);
		addTab(tabLayoutAssets, assetsPagerAdapter, copytradingTab, false);

	}

	private void initAssetsViewPager() {
		assetsPagerAdapter = new DashboardPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), tabLayoutAssets);
		viewPagerAssets.setAdapter(assetsPagerAdapter);
		viewPagerAssets.setOffscreenPageLimit(5);

		tabLayoutAssetsOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayoutAssets);
		viewPagerAssets.addOnPageChangeListener(tabLayoutAssetsOnPageChangeListener);
		viewPagerAssets.addOnPageChangeListener(this);
	}

	private void initAssetsBottomSheet() {
		appBarLayout.addOnOffsetChangedListener(appBarLayoutOffsetChangedListener);
		CustomBottomSheetBehavior.from(assetsBottomSheet).setState(CustomBottomSheetBehavior.STATE_HIDDEN);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		assetsRecyclerView.setLayoutManager(layoutManager);
		int paddingLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 43, getContext().getResources().getDisplayMetrics());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
				AppCompatResources.getDrawable(getContext(), R.drawable.list_item_divider), paddingLeft, 0);
		assetsRecyclerView.addItemDecoration(dividerItemDecoration);
		portfolioAssetsAdapter = new PortfolioAssetsAdapter();
		portfolioAssetsAdapter.setHasStableIds(true);
		assetsRecyclerView.setAdapter(portfolioAssetsAdapter);
		assetsRecyclerView.setNestedScrollingEnabled(false);
//		assetsRecyclerView.addOnItemTouchListener(onItemTouchListener);
	}

	private void setOffsetListener() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> refreshLayout.setEnabled(!assetsBottomSheetShowing && verticalOffset == 0));
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
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			refreshLayout.setVisibility(View.VISIBLE);
			dateRangeView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		if (refreshLayout != null)
			refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPagerAssets);
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

			showAssetsBottomSheet(chartBottomY + header.getHeight() + tabLayoutHeader.getHeight());
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
		refreshLayout.setEnabled(false);
		assetsBottomSheetShowing = true;
	}

	public void hideAssetsBottomSheet() {
		CustomBottomSheetBehavior.from(assetsBottomSheet).setState(CustomBottomSheetBehavior.STATE_HIDDEN);
		EventBus.getDefault().post(new ShowBottomNavigationEvent(true));
		refreshLayout.setEnabled(true);
		assetsBottomSheetShowing = false;
	}

	@Override
	public void setPortfolioAssets(List<PortfolioAssetData> portfolioAssets) {
		portfolioAssetsAdapter.setAssets(portfolioAssets);
	}

	@Override
	public void setInRequests(Double totalValue, Double rate) {
		dashboardHeaderPagerAdapter.setInRequests(totalValue, rate);
	}

	@Override
	public void showInRequests(List<ProgramRequest> requests) {
		if (getActivity() != null) {
			RequestsBottomSheetFragment bottomSheetDialog = new RequestsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setRequests(requests);
		}
	}

	@Override
	public void showProgramRequests(UUID programId) {
		if (getActivity() != null) {
			RequestsBottomSheetFragment bottomSheetDialog = new RequestsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setAssetId(programId);
		}
	}

	@Override
	public void onAssetsListsUpdate() {
		assetsPagerAdapter.onAssetsListsUpdate();
	}

	@Override
	public void setProgramsCount(Integer programsCount) {
		((CustomTabView) programsTab.getCustomView()).setCount(programsCount);
	}

	@Override
	public void setFundsCount(Integer fundsCount) {
		((CustomTabView) fundsTab.getCustomView()).setCount(fundsCount);
	}

	@Override
	public void setSignalsCount(Integer signalsCount) {
		((CustomTabView) copytradingTab.getCustomView()).setCount(signalsCount);
	}

	@Override
	public void setChartData(DashboardChartValue chart) {
		dashboardHeaderPagerAdapter.setPortfolioChart(chart, dateRange);
	}

	@Override
	public void setPortfolioEvents(List<PortfolioEvent> events) {
		eventsGroup.removeAllViews();
		for (PortfolioEvent event : events) {
			PortfolioEventDashboardView eventView = new PortfolioEventDashboardView(getContext());
			eventView.setEvent(event);
			eventsGroup.addView(eventView);
		}
		eventsHeaderGroup.setVisibility(!events.isEmpty() ? View.VISIBLE : View.GONE);
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
		currentFragment = assetsPagerAdapter.getItem(position);
		if (assetsPagerAdapter.getItem(position) instanceof DashboardPagerAdapter.OnPageVisibilityChanged) {
			((DashboardPagerAdapter.OnPageVisibilityChanged) assetsPagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}