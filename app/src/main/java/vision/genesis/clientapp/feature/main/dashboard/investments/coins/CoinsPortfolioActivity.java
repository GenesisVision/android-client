package vision.genesis.clientapp.feature.main.dashboard.investments.coins;

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
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

public class CoinsPortfolioActivity extends BaseSwipeBackActivity implements CoinsPortfolioView, ViewPager.OnPageChangeListener
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), CoinsPortfolioActivity.class);
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

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_coins_portfolio)
	public ViewPager viewPager;

	@InjectPresenter
	CoinsPortfolioPresenter presenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab portfolioTab;

	private TabLayout.Tab historyTab;

	private CoinsPortfolioPagerAdapter pagerAdapter;

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

		setContentView(R.layout.activity_coins_portfolio);

		ButterKnife.bind(this);

		initRefreshLayout();
		initTabs();
		initViewPager();
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

	private void initTabs() {
		portfolioTab = tabLayout.newTab().setCustomView(getTabView(R.string.portfolio)).setTag("portfolio");
		historyTab = tabLayout.newTab().setCustomView(getTabView(R.string.history)).setTag("history");

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

		addPage(portfolioTab, true);
		addPage(historyTab, false);
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

	private void initViewPager() {
		pagerAdapter = new CoinsPortfolioPagerAdapter(getSupportFragmentManager(), tabLayout);
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
		if (currentFragment != null && currentFragment instanceof CoinsPortfolioPagerAdapter.OnPageVisibilityChanged) {
			((CoinsPortfolioPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		}
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof CoinsPortfolioPagerAdapter.OnPageVisibilityChanged) {
			((CoinsPortfolioPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
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
	public void setAssetsCount(Integer count) {
		((DetailsTabView) portfolioTab.getCustomView()).setCount(count);
	}

	@Override
	public void setHistoryCount(Integer count) {
		((DetailsTabView) historyTab.getCustomView()).setCount(count);
	}
}
