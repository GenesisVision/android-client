package vision.genesis.clientapp.feature.main.trading_account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PrivateTradingAccountFull;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.copytrading.unfollow_trades.UnfollowTradesActivity;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class TradingAccountDetailsActivity extends BaseSwipeBackActivity implements TradingAccountDetailsView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, TradingAccountDetailsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), TradingAccountDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.toolbar_broker_logo)
	public SimpleDraweeView toolbarBrokerLogo;

	@BindView(R.id.toolbar_account_name)
	public TextView toolbarAccountName;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_trading_account_details)
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
	TradingAccountDetailsPresenter presenter;

	private PrivateTradingAccountFull accountDetails;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab infoTab;

	private TabLayout.Tab openPositionsTab;

	private TabLayout.Tab profitTab;

	private TabLayout.Tab equityTab;

	private TabLayout.Tab tradesTab;

	private TradingAccountDetailsPagerAdapter pagerAdapter;

	private TradingAccountDetailsModel model;

	private boolean isPagerDragging;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		presenter.onTryAgainClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_trading_account_details);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);

			initRefreshLayout();
			initTabs();
			updateHeader();

			presenter.setData(model);
		}
		else {
			Timber.e("Passed empty data to %s", getClass().getSimpleName());
			onBackPressed();
		}
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

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(!isPagerDragging);
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
		toolbarAccountName.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader() {
		toolbarBrokerLogo.setImageURI(ImageUtils.getImageUri(model.getBrokerLogo()));
		toolbarAccountName.setText(model.getAccountName());
	}

	private void initTabs() {
		infoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("info");
		profitTab = tabLayout.newTab().setCustomView(getTabView(R.string.profit)).setTag("profit");
		equityTab = tabLayout.newTab().setCustomView(getTabView(R.string.equity)).setTag("equity");
		openPositionsTab = tabLayout.newTab().setCustomView(getTabView(R.string.open_positions)).setTag("open_positions");
		tradesTab = tabLayout.newTab().setCustomView(getTabView(R.string.trades)).setTag("trades");

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

	private void initViewPager(PrivateTradingAccountFull accountDetails) {
		pagerAdapter = new TradingAccountDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, accountDetails);
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
	public void setDetails(PrivateTradingAccountFull accountDetails) {
		this.accountDetails = accountDetails;

		if (pagerAdapter == null) {
			addPage(infoTab, true);
			addPage(profitTab, false);
			addPage(equityTab, false);
			addPage(openPositionsTab, false);
			addPage(tradesTab, false);

			initViewPager(this.accountDetails);
		}

		tabLayout.setVisibility(View.VISIBLE);
		viewPager.setVisibility(View.VISIBLE);

		pagerAdapter.sendUpdate();
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
	public void showNoInternet(boolean show) {
//		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbarAccountName);
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
	public void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName) {
		UnfollowTradesActivity.startWith(this, followId, tradingAccountId, followName);
	}
}
