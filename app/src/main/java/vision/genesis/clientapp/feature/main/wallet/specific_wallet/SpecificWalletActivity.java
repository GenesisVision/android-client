package vision.genesis.clientapp.feature.main.wallet.specific_wallet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import io.swagger.client.model.InternalTransferRequestType;
import io.swagger.client.model.WalletData;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletActivity;
import vision.genesis.clientapp.feature.main.wallet.transfer_funds.TransferFundsActivity;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletActivity;
import vision.genesis.clientapp.model.TransferFundsModel;
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

public class SpecificWalletActivity extends BaseSwipeBackActivity implements SpecificWalletView, ViewPager.OnPageChangeListener
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

	@BindView(R.id.buy_with_card)
	public ViewGroup buyWithCard;

	@BindView(R.id.label_transfer)
	public TextView transferLabel;

	@BindView(R.id.label_add_funds)
	public TextView addFundsLabel;

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

	private boolean isPagerDragging;

	private int verticalOffset;

	private WalletData wallet;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.transfer)
	public void onTransferButtonClicked() {
		if (this.wallet != null) {
			TransferFundsModel transferFundsModel = TransferFundsModel.createFrom(this.wallet);
			transferFundsModel.setAssetType(InternalTransferRequestType.WALLET);
			transferFundsModel.setTransferDirection(TransferFundsModel.TransferDirection.WITHDRAW);
			TransferFundsActivity.startWith(this, transferFundsModel);
		}
	}

	@OnClick(R.id.buy_with_card)
	public void onBuyWithCardClicked() {
		if (wallet != null && wallet.getDepositUrlCoindirect() != null && !wallet.getDepositUrlCoindirect().isEmpty()) {
//			BuyWithCardActivity.startWith(this, wallet.getDepositUrlCoindirect());

			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(wallet.getDepositUrlCoindirect()));
			startActivity(browserIntent);
		}
	}

	@OnClick(R.id.withdraw)
	public void onWithdrawButtonClicked() {
		WithdrawWalletActivity.startWith(this, model);
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
			Timber.e("Passed empty model to SpecificWalletActivity");
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
		walletName.setTypeface(TypefaceUtil.semibold());

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
		transferLabel.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			specificWalletPresenter.onSwipeRefresh();
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
		});
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
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION) {
			return;
		}

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null) {
			pagerAdapter.notifyDataSetChanged();
		}
	}

	private void initViewPager(String walletCurrency) {
		pagerAdapter = new SpecificWalletPagerAdapter(getSupportFragmentManager(), tabLayout, walletCurrency);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(2);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void setWalletData(WalletData data) {
		this.wallet = data;

		String currency = data.getCurrency().getValue();

		this.balance.setText(StringFormatUtil.getValueString(data.getTotal(), data.getCurrency().getValue()));
//		this.balanceBase.setText(StringFormatUtil.getValueString(data.getTotalCcy(), data.getCurrencyCcy().getValue()));

		int availablePercent = (int) Math.round(data.getAvailable() * 100 / data.getTotal());
		this.availableShare.setProgress(availablePercent);
		this.availablePercent.setText(String.format(Locale.getDefault(), "%d%%", availablePercent));
		this.available.setText(StringFormatUtil.getValueString(data.getAvailable(), currency));
//		this.availableBase.setText(StringFormatUtil.getValueString(data.getGrandTotal().getAvailableCcy(), baseCurrency.getValue()));

		int investedPercent = (int) Math.round(data.getInvested() * 100 / data.getTotal());
		this.investedShare.setProgress(investedPercent);
		this.investedPercent.setText(String.format(Locale.getDefault(), "%d%%", investedPercent));
		this.invested.setText(StringFormatUtil.getValueString(data.getInvested(), currency));
//		this.investedBase.setText(StringFormatUtil.getValueString(data.getGrandTotal().getInvestedCcy(), baseCurrency.getValue()));

		int pendingPercent = (int) Math.round(data.getPending() * 100 / data.getTotal());
		this.pendingShare.setProgress(pendingPercent);
		this.pendingPercent.setText(String.format(Locale.getDefault(), "%d%%", pendingPercent));
		this.pending.setText(StringFormatUtil.getValueString(data.getPending(), currency));

		this.addFunds.setVisibility(data.isIsDepositEnabled() ? View.VISIBLE : View.GONE);
		this.withdraw.setVisibility(data.isIsWithdrawalEnabled() ? View.VISIBLE : View.GONE);

		this.buyWithCard.setVisibility(
				data.getDepositUrlCoindirect() != null && !data.getDepositUrlCoindirect().isEmpty()
						? View.VISIBLE
						: View.GONE);
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