package vision.genesis.clientapp.feature.main.wallet.specific_wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletData;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletActivity;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletActivity;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/02/2019.
 */

public class SpecificWalletActivity extends BaseSwipeBackActivity implements SpecificWalletView
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, WalletModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), SpecificWalletActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.wallet_icon)
	public SimpleDraweeView walletIcon;

	@BindView(R.id.wallet_name)
	public TextView walletName;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_specific_wallet)
	public ViewPager viewPager;

//	@BindView(R.id.withdraw)
//	public TextView withdraw;

//	@BindView(R.id.add_funds)
//	public TextView addFunds;

	@BindView(R.id.label_balance)
	public TextView balanceLabel;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_base)
	public TextView balanceBase;

	@BindView(R.id.available_share)
	public ProgressBar availableShare;

	@BindView(R.id.available)
	public TextView available;

	@BindView(R.id.available_base)
	public TextView availableBase;

	@BindView(R.id.invested_share)
	public ProgressBar investedShare;

	@BindView(R.id.invested)
	public TextView invested;

	@BindView(R.id.invested_base)
	public TextView investedBase;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SpecificWalletPresenter specificWalletPresenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab transactionsTab;

	private TabLayout.Tab depositsWithdrawalsTab;

	private SpecificWalletPagerAdapter pagerAdapter;

	private WalletModel model;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.withdraw)
	public void onWithdrawButtonClicked() {
		WithdrawWalletActivity.startWith(this);
	}

	@OnClick(R.id.add_funds)
	public void onAddFundsButtonClicked() {
		DepositWalletActivity.startWith(this, model);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_specific_wallet);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);

			updateHeader(model);

			setFonts();

			initRefreshLayout();
			setOffsetListener();
			initTabs();
			initViewPager(model.getCurrency());

			specificWalletPresenter.setWalletId(model.getId());
		}
		else {
			Timber.e("Passed empty program to SpecificWalletActivity");
			onBackPressed();
		}
	}

	private void updateHeader(WalletModel model) {
		walletIcon.setImageURI(ImageUtils.getImageUri(model.getLogo()));
		walletName.setText(model.getTitle());
	}

	@Override
	public void onResume() {
		super.onResume();

		specificWalletPresenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void onDestroy() {

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
		walletName.setTypeface(TypefaceUtil.semibold());

		balanceLabel.setTypeface(TypefaceUtil.semibold());
		balance.setTypeface(TypefaceUtil.semibold());

		available.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			specificWalletPresenter.onSwipeRefresh();
			if (pagerAdapter != null)
				pagerAdapter.sendSwipeRefresh();
		});
	}

	private void setOffsetListener() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> refreshLayout.setEnabled(verticalOffset == 0));
	}

	private void initTabs() {
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

		addPage(transactionsTab, true);
		addPage(depositsWithdrawalsTab, false);
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

	private void initViewPager(String walletCurrency) {
		pagerAdapter = new SpecificWalletPagerAdapter(getSupportFragmentManager(), tabLayout, walletCurrency);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(2);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
	}

	@Override
	public void setWalletData(WalletData data) {
		this.balance.setText(StringFormatUtil.getValueString(data.getTotal(), data.getCurrency().getValue()));
		this.balanceBase.setText(StringFormatUtil.getValueString(data.getTotalCcy(), data.getCurrencyCcy().getValue()));

		this.available.setText(StringFormatUtil.getValueString(data.getAvailable(), data.getCurrency().getValue()));
		this.availableBase.setText(StringFormatUtil.getValueString(data.getAvailableCcy(), data.getCurrencyCcy().getValue()));

		this.invested.setText(StringFormatUtil.getValueString(data.getInvested(), data.getCurrency().getValue()));
		this.investedBase.setText(StringFormatUtil.getValueString(data.getInvestedCcy(), data.getCurrencyCcy().getValue()));

		availableShare.setProgress((int) (data.getAvailable() * 100 / data.getTotal()));
		investedShare.setProgress((int) (data.getInvested() * 100 / data.getTotal()));
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

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}