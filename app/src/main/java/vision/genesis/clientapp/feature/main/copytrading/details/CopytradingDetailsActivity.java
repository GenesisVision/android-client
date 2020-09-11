package vision.genesis.clientapp.feature.main.copytrading.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/12/2019.
 */

public class CopytradingDetailsActivity extends BaseSwipeBackActivity implements CopytradingDetailsView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, TradingAccountDetailsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), CopytradingDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.toolbar_account_name)
	public TextView toolbarAccountName;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_copytrading_details)
	public ViewPager viewPager;

	@InjectPresenter
	CopytradingDetailsPresenter presenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab subscriptionsTab;

//	private TabLayout.Tab openTradesTab;

	private TabLayout.Tab tradesHistoryTab;

	private TabLayout.Tab tradingLogTab;

	private CopytradingDetailsPagerAdapter pagerAdapter;

	private TradingAccountDetailsModel model;

	private boolean isPagerDragging;

	private Fragment currentFragment;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_copytrading_details);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				initRefreshLayout();
				initTabs();
				initViewPager(model);
				updateHeader();

				presenter.setData(model);

				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			new Handler().postDelayed(() -> refreshLayout.setRefreshing(false), 500);
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
		});
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(!isPagerDragging);
	}

	@Override
	public void onResume() {
		super.onResume();

		if (pagerAdapter != null) {
			pagerAdapter.sendUpdate();
		}
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
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader() {
		toolbarAccountName.setText(model.getAccountName());
	}

	private void initTabs() {
		subscriptionsTab = tabLayout.newTab().setCustomView(getTabView(R.string.subscriptions)).setTag("subscriptions");
//		openTradesTab = tabLayout.newTab().setCustomView(getTabView(R.string.open_trades)).setTag("open_trades");
		tradesHistoryTab = tabLayout.newTab().setCustomView(getTabView(R.string.trades)).setTag("trades_history");
		tradingLogTab = tabLayout.newTab().setCustomView(getTabView(R.string.trading_log)).setTag("trading_log");

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

		addPage(subscriptionsTab, true);
//		addPage(openTradesTab, false);
		addPage(tradesHistoryTab, false);
		addPage(tradingLogTab, false);
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

	private void initViewPager(TradingAccountDetailsModel accountId) {
		pagerAdapter = new CopytradingDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, model);
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
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof CopytradingDetailsPagerAdapter.OnPageVisibilityChanged) {
			((CopytradingDetailsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		}
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof CopytradingDetailsPagerAdapter.OnPageVisibilityChanged) {
			((CopytradingDetailsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
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
	public void setOpenTradesCount(Integer openPositionsCount) {
//		((DetailsTabView) openTradesTab.getCustomView()).setCount(openPositionsCount);
	}

	@Override
	public void setTradesCount(Integer tradesCount) {
		((DetailsTabView) tradesHistoryTab.getCustomView()).setCount(tradesCount);
	}

	@Override
	public void setTradingLogCount(Integer tradingLogCount) {
		((DetailsTabView) tradingLogTab.getCustomView()).setCount(tradingLogCount);
	}
}
