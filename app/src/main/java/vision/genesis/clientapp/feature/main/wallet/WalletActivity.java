package vision.genesis.clientapp.feature.main.wallet;

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
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletSummary;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.fees_and_discounts.FeesAndDiscountsActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletActivity extends BaseSwipeBackActivity implements WalletView, ViewPager.OnPageChangeListener
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), WalletActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.group_header_balance)
	public ViewGroup headerBalanceGroup;

	@BindView(R.id.header_balance)
	public TextView headerBalance;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_wallet)
	public ViewPager viewPager;

	@BindView(R.id.label_gm_discount)
	public TextView gmDiscountLabel;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.toolbar_height)
	public int toolbarHeight;


	@InjectPresenter
	WalletPresenter presenter;

	private CurrencyEnum baseCurrency;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab transactionsTab;

	private TabLayout.Tab depositsWithdrawalsTab;

	private WalletPagerAdapter pagerAdapter;

	private int verticalOffset;

	private boolean isPagerDragging;

	private float titleInitialY = 0;

	private float headerBalanceGroupInitialY = 0;

	private long headerBalanceGroupAnimationDuration = 300;

	private boolean showAnimInProcess = false;

	private boolean hideAnimInProcess = false;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.tooltip_using_gvt)
	public void onTooltipUsingGvtClicked() {
		FeesAndDiscountsActivity.startFrom(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wallet);

		ButterKnife.bind(this);

		initRefreshLayout();
		setOffsetListener();
		initTabs();
		initViewPager();
	}

	@Override
	public void onResume() {
		super.onResume();

		presenter.onResume();
		if (pagerAdapter != null) {
			pagerAdapter.sendUpdate();
		}
	}

	@Override
	public void onDestroy() {
		if (pagerAdapter != null) {
			pagerAdapter.destroy();
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
		finishActivity();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(Objects.requireNonNull(this), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			presenter.onSwipeRefresh();
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
		});
	}

	private void setOffsetListener() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			this.verticalOffset = verticalOffset;
			if (verticalOffset < -200 && !showAnimInProcess) {
				showBalanceInHeader();
			}
			else if (verticalOffset > -150 && !hideAnimInProcess) {
				hideBalanceInHeader();
			}
			updateRefreshLayoutEnabled();
			pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight());
		});
	}

	private void showBalanceInHeader() {
		showAnimInProcess = true;
		hideAnimInProcess = false;

		ValueAnimator balanceYAnim = ValueAnimator.ofFloat(headerBalanceGroup.getY(), headerBalanceGroupInitialY);
		ValueAnimator titleYAnim = ValueAnimator.ofFloat(title.getY(), titleInitialY - toolbarHeight);
		ValueAnimator alphaAnim = ValueAnimator.ofFloat(headerBalanceGroup.getAlpha(), 1f);

		balanceYAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setY((float) balanceYAnim.getAnimatedValue());
			}
		});
		titleYAnim.addUpdateListener(animation -> {
			if (title != null) {
				title.setY((float) titleYAnim.getAnimatedValue());
			}
		});
		alphaAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue());
			}
		});

		balanceYAnim.setDuration(headerBalanceGroupAnimationDuration);
		titleYAnim.setDuration(headerBalanceGroupAnimationDuration);
		alphaAnim.setDuration(headerBalanceGroupAnimationDuration);

		alphaAnim.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				showAnimInProcess = false;
			}
		});

		balanceYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		titleYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());

		balanceYAnim.start();
		titleYAnim.start();
		alphaAnim.start();
	}

	private void hideBalanceInHeader() {
		hideAnimInProcess = true;
		showAnimInProcess = false;

		if (titleInitialY == 0) {
			titleInitialY = title.getY();
		}
		if (headerBalanceGroupInitialY == 0) {
			headerBalanceGroupInitialY = headerBalanceGroup.getY();
		}

		ValueAnimator balanceYAnim = ValueAnimator.ofFloat(headerBalanceGroup.getY(), headerBalanceGroupInitialY + toolbarHeight);
		ValueAnimator titleYAnim = ValueAnimator.ofFloat(title.getY(), titleInitialY);
		ValueAnimator alphaAnim = ValueAnimator.ofFloat(headerBalanceGroup.getAlpha(), 0f);

		balanceYAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setY((float) balanceYAnim.getAnimatedValue());
			}
		});
		titleYAnim.addUpdateListener(animation -> {
			if (title != null) {
				title.setY((float) titleYAnim.getAnimatedValue());
			}
		});
		alphaAnim.addUpdateListener(animation -> {
			if (headerBalanceGroup != null) {
				headerBalanceGroup.setAlpha((float) alphaAnim.getAnimatedValue());
			}
		});

		balanceYAnim.setDuration(headerBalanceGroupAnimationDuration);
		titleYAnim.setDuration(headerBalanceGroupAnimationDuration);
		alphaAnim.setDuration(headerBalanceGroupAnimationDuration);

		alphaAnim.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				hideAnimInProcess = false;
			}
		});

		balanceYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		titleYAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		alphaAnim.setInterpolator(new AccelerateDecelerateInterpolator());

		balanceYAnim.start();
		titleYAnim.start();
		alphaAnim.start();
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(verticalOffset == 0 && !isPagerDragging);
	}

	private void initTabs() {
		TabLayout.Tab myWalletsTab = tabLayout.newTab().setCustomView(getTabView(R.string.my_wallets)).setTag("my_wallets");
		transactionsTab = tabLayout.newTab().setCustomView(getTabView(R.string.transactions)).setTag("transactions");
		depositsWithdrawalsTab = tabLayout.newTab().setCustomView(getTabView(R.string.deposits_withdrawals)).setTag("deposits_withdrawals");

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

		addPage(myWalletsTab, true);
		addPage(transactionsTab, false);
		addPage(depositsWithdrawalsTab, false);
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
		pagerAdapter = new WalletPagerAdapter(getSupportFragmentManager(), tabLayout);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setBalance(WalletSummary data) {
		String currency = data.getGrandTotal().getCurrency().getValue();

		this.balance.setText(StringFormatUtil.getValueString(data.getGrandTotal().getAvailable(), currency));
		this.headerBalance.setText(StringFormatUtil.getValueString(data.getGrandTotal().getAvailable(), currency));

		this.gmDiscountLabel.setText(String.format(Locale.getDefault(), getString(R.string.template_your_gm_discount),
				StringFormatUtil.formatAmount(data.getGenesisMarketsDiscountPercent(), 0, 8)));
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			refreshLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, appBarLayout);
	}

	@Override
	public void setTransactionsCount(Integer transactionsCount) {
		((DetailsTabView) transactionsTab.getCustomView()).setCount(transactionsCount);
	}

	@Override
	public void setDepositsWithdrawalsCount(Integer depositsWithdrawalsCount) {
		((DetailsTabView) depositsWithdrawalsTab.getCustomView()).setCount(depositsWithdrawalsCount);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {
		isPagerDragging = state == ViewPager.SCROLL_STATE_DRAGGING;
		updateRefreshLayoutEnabled();
	}
}