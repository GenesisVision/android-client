package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.transfer_copytrading_account.TransferCopytradingAccountActivity;
import vision.genesis.clientapp.model.CopytradingAccountModel;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

public class CopytradingAccountDetailsActivity extends BaseSwipeBackActivity implements CopytradingAccountDetailsView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, CopytradingAccountModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), CopytradingAccountDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.account_icon)
	public SimpleDraweeView accountIcon;

	@BindView(R.id.account_name)
	public TextView accountName;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_copytrading_account_details)
	public ViewPager viewPager;

	@BindView(R.id.label_balance)
	public TextView balanceLabel;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.available_share)
	public ProgressBar availableShare;

	@BindView(R.id.available_percent)
	public TextView availablePercent;

	@BindView(R.id.available)
	public TextView available;

	@BindView(R.id.invested_share)
	public ProgressBar investedShare;

	@BindView(R.id.invested_percent)
	public TextView investedPercent;

	@BindView(R.id.invested)
	public TextView invested;

	@BindView(R.id.pending_share)
	public ProgressBar pendingShare;

	@BindView(R.id.pending_percent)
	public TextView pendingPercent;

	@BindView(R.id.pending)
	public TextView pending;

	@BindView(R.id.label_withdraw)
	public TextView withdrawLabel;

	@BindView(R.id.withdraw)
	public ViewGroup withdraw;

	@BindView(R.id.add_funds)
	public ViewGroup addFunds;

	@BindView(R.id.label_add_funds)
	public TextView addFundsLabel;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	CopytradingAccountDetailsPresenter copytradingAccountDetailsPresenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab openTradesTab;

	private TabLayout.Tab tradesHistoryTab;

	private TabLayout.Tab tradingLogTab;

	private CopytradingAccountDetailsPagerAdapter pagerAdapter;

	private CopytradingAccountModel model;

	private int verticalOffset;

	private boolean isPagerDragging = false;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.withdraw)
	public void onWithdrawButtonClicked() {
		TransferCopytradingAccountActivity.startWith(this, model, TransferCopytradingAccountActivity.OPERATION_WITHDRAW);
	}

	@OnClick(R.id.add_funds)
	public void onAddFundsButtonClicked() {
		TransferCopytradingAccountActivity.startWith(this, model, TransferCopytradingAccountActivity.OPERATION_DEPOSIT);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_copytrading_account_details);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				updateHeader(model);
				setAccountData(model);

				setFonts();

				initRefreshLayout();
				initButtons();
				setOffsetListener();
				initTabs();
				initViewPager(model.getCurrency());
				return;
			}
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateHeader(CopytradingAccountModel model) {
		accountIcon.setImageURI(ImageUtils.getImageUri(model.getLogo()));
		accountName.setText(model.getTitle());
	}

	@Override
	public void onResume() {
		super.onResume();

		if (pagerAdapter != null) {
			pagerAdapter.sendUpdate();
		}
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void onDestroy() {
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
		accountName.setTypeface(TypefaceUtil.semibold());

		balanceLabel.setTypeface(TypefaceUtil.semibold());
		balance.setTypeface(TypefaceUtil.semibold());

		available.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());
		pending.setTypeface(TypefaceUtil.semibold());

		availablePercent.setTypeface(TypefaceUtil.semibold());
		investedPercent.setTypeface(TypefaceUtil.semibold());
		pendingPercent.setTypeface(TypefaceUtil.semibold());

		withdrawLabel.setTypeface(TypefaceUtil.semibold());
		addFundsLabel.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
			refreshLayout.setRefreshing(false);
		});
	}

	private void initButtons() {
	}

	private void setOffsetListener() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			this.verticalOffset = verticalOffset;
			updateRefreshLayoutEnabled();
			pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight());
		});
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(verticalOffset == 0 && !isPagerDragging);
	}

	private void initTabs() {
		openTradesTab = tabLayout.newTab().setCustomView(getTabView(R.string.open_trades)).setTag("open_trades");
		tradesHistoryTab = tabLayout.newTab().setCustomView(getTabView(R.string.trades_history)).setTag("trades_history");
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

		addPage(openTradesTab, true);
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

	private void initViewPager(String accountCurrency) {
		pagerAdapter = new CopytradingAccountDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, accountCurrency);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void setAccountData(CopytradingAccountModel data) {
		String currency = data.getCurrency();

		this.balance.setText(StringFormatUtil.getValueString(data.getBalance(), data.getCurrency()));

		Integer availablePercent = (int) Math.round(data.getAvailable() * 100 / data.getBalance());
		this.availableShare.setProgress(availablePercent);
		this.availablePercent.setText(String.format(Locale.getDefault(), "%d%%", availablePercent));
		this.available.setText(StringFormatUtil.getValueString(data.getAvailable(), currency));

//		Integer investedPercent = (int) Math.round(data.getInvested() * 100 / data.getSignalsCount());
//		this.investedShare.setProgress(investedPercent);
//		this.investedPercent.setText(String.format(Locale.getDefault(), "%d%%", investedPercent));
//		this.invested.setText(StringFormatUtil.getValueString(data.getInvested(), currency));
////		this.investedBase.setText(StringFormatUtil.getValueString(data.getGrandTotal().getInvestedCcy(), baseCurrency.getValue()));
//
//		Integer pendingPercent = (int) Math.round(data.getPending() * 100 / data.getSignalsCount());
//		this.pendingShare.setProgress(pendingPercent);
//		this.pendingPercent.setText(String.format(Locale.getDefault(), "%d%%", pendingPercent));
//		this.pending.setText(StringFormatUtil.getValueString(data.getPending(), currency));
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
	public void setOpenTradesCount(Integer openTradesCount) {
		((DetailsTabView) openTradesTab.getCustomView()).setCount(openTradesCount);
	}

	@Override
	public void setTradesHistoryCount(Integer tradesHistoryCount) {
		((DetailsTabView) tradesHistoryTab.getCustomView()).setCount(tradesHistoryCount);
	}

	@Override
	public void setTradingLogCount(Integer eventsCount) {
		((DetailsTabView) tradingLogTab.getCustomView()).setCount(eventsCount);
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
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