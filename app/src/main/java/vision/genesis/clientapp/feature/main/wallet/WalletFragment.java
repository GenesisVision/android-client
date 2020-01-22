package vision.genesis.clientapp.feature.main.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.WalletSummary;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.fees_and_discounts.FeesAndDiscountsActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletFragment extends BaseFragment implements WalletView, ViewPager.OnPageChangeListener
{
	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_wallet)
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

	@BindView(R.id.switch_using_gvt)
	public SwitchCompat switchUsingGvt;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	WalletPresenter presenter;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab myWalletsTab;

	private TabLayout.Tab transactionsTab;

	private TabLayout.Tab depositsWithdrawalsTab;

	private WalletPagerAdapter pagerAdapter;

	private int verticalOffset;

	private boolean isPagerDragging;

	@OnClick(R.id.tooltip_using_gvt)
	public void onTooltipUsingGvtClicked() {
		if (getActivity() != null) {
			FeesAndDiscountsActivity.startFrom(getActivity());
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wallet, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		initRefreshLayout();
		setOffsetListener();
		setSwitchListener();
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
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

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


		super.onDestroyView();
	}

	private void setFonts() {
		balanceLabel.setTypeface(TypefaceUtil.semibold());
		balance.setTypeface(TypefaceUtil.semibold());

		available.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());
		pending.setTypeface(TypefaceUtil.semibold());

		availablePercent.setTypeface(TypefaceUtil.semibold());
		investedPercent.setTypeface(TypefaceUtil.semibold());
		pendingPercent.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(Objects.requireNonNull(getContext()), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
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
			updateRefreshLayoutEnabled();
			pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight());
		});
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(verticalOffset == 0 && !isPagerDragging);
	}

	private void setSwitchListener() {
		switchUsingGvt.setOnCheckedChangeListener((view, checked) -> {
			presenter.onUsingGvtCheckedChanged(checked);
		});
	}

	private void initTabs() {
		myWalletsTab = tabLayout.newTab().setCustomView(getTabView(R.string.my_wallets)).setTag("my_wallets");
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
		DetailsTabView view = new DetailsTabView(getContext());
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
		if (getActivity() != null) {
			pagerAdapter = new WalletPagerAdapter(getChildFragmentManager(), tabLayout);
			viewPager.setAdapter(pagerAdapter);
			viewPager.setOffscreenPageLimit(3);

			tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
			viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
			viewPager.addOnPageChangeListener(this);
		}
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setBalance(WalletSummary data) {
		String currency = data.getGrandTotal().getCurrency().getValue();

		this.balance.setText(StringFormatUtil.getValueString(data.getGrandTotal().getTotal(), currency));
//		this.balanceBase.setText(StringFormatUtil.getValueString(data.getGrandTotal().getTotalCcy(), baseCurrency.getValue()));

		int availablePercent = (int) Math.round(data.getGrandTotal().getAvailable() * 100 / data.getGrandTotal().getTotal());
		this.availableShare.setProgress(availablePercent);
		this.availablePercent.setText(String.format(Locale.getDefault(), "%d%%", availablePercent));
		this.available.setText(StringFormatUtil.getValueString(data.getGrandTotal().getAvailable(), currency));
//		this.availableBase.setText(StringFormatUtil.getValueString(data.getGrandTotal().getAvailableCcy(), baseCurrency.getValue()));

		int investedPercent = (int) Math.round(data.getGrandTotal().getInvested() * 100 / data.getGrandTotal().getTotal());
		this.investedShare.setProgress(investedPercent);
		this.investedPercent.setText(String.format(Locale.getDefault(), "%d%%", investedPercent));
		this.invested.setText(StringFormatUtil.getValueString(data.getGrandTotal().getInvested(), currency));
//		this.investedBase.setText(StringFormatUtil.getValueString(data.getGrandTotal().getInvestedCcy(), baseCurrency.getValue()));

		int pendingPercent = (int) Math.round(data.getGrandTotal().getTrading() * 100 / data.getGrandTotal().getTotal());
		this.pendingShare.setProgress(pendingPercent);
		this.pendingPercent.setText(String.format(Locale.getDefault(), "%d%%", pendingPercent));
		this.pending.setText(StringFormatUtil.getValueString(data.getGrandTotal().getTrading(), currency));

		this.switchUsingGvt.setChecked(data.isPayFeesWithGvt());
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