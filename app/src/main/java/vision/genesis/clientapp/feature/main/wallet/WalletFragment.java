package vision.genesis.clientapp.feature.main.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsPagerAdapter;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletFragment extends BaseFragment implements WalletView, ViewPager.OnPageChangeListener
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.group_balance)
	public ViewGroup balanceGroup;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_currency)
	public TextView balanceCurrency;

	@BindView(R.id.balance_fiat)
	public TextView balanceFiat;

	@BindView(R.id.balance_progress)
	public ProgressBar balanceProgress;

	@BindView(R.id.view_pager_wallet)
	public ViewPager viewPager;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@InjectPresenter
	WalletPresenter walletPresenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TransactionsPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	@OnClick(R.id.button_withdraw)
	public void onWithdrawButtonClicked() {
		walletPresenter.onWithdrawButtonClicked();
	}

	@OnClick(R.id.button_deposit)
	public void onDepositButtonClicked() {
		walletPresenter.onDepositButtonClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wallet, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
		initTabs();
		initViewPager();
		setFonts();
	}

	@Override
	public void onResume() {
		super.onResume();

		walletPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (pagerAdapter != null)
			pagerAdapter.destroy();

		if (tabSelectedListener != null)
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);

		if (tabLayoutOnPageChangeListener != null)
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);

		viewPager.addOnPageChangeListener(this);

		super.onDestroyView();
	}

	private void setFonts() {
		balance.setTypeface(TypefaceUtil.light(getContext()));
		balanceCurrency.setTypeface(TypefaceUtil.bold(getContext()));
		balanceFiat.setTypeface(TypefaceUtil.light(getContext()));
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.wallet));
	}

	private void initTabs() {
		tabLayout.addTab(tabLayout.newTab().setText(getContext().getResources().getString(R.string.all)), true);
		tabLayout.addTab(tabLayout.newTab().setText(getContext().getResources().getString(R.string.internal)));
		tabLayout.addTab(tabLayout.newTab().setText(getContext().getResources().getString(R.string.external)));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		};

		tabLayout.addOnTabSelectedListener(tabSelectedListener);
	}

	private void initViewPager() {
		pagerAdapter = new TransactionsPagerAdapter(getActivity().getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void setBalance(double balance) {
		this.balance.setText(StringFormatUtil.formatAmount(balance));
	}

	@Override
	public void setFiatBalance(double balance) {
		balanceFiat.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(balance, 2, 2)));
	}

	@Override
	public void showBalanceProgress() {
		balanceProgress.setVisibility(View.VISIBLE);
		balanceGroup.setVisibility(View.GONE);
	}

	@Override
	public void hideBalanceProgress() {
		balanceProgress.setVisibility(View.GONE);
		balanceGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof TransactionsPagerAdapter.OnPageVisibilityChanged)
			((TransactionsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof TransactionsPagerAdapter.OnPageVisibilityChanged) {
			((TransactionsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onShow() {
		if (currentFragment != null && currentFragment instanceof TransactionsPagerAdapter.OnPageVisibilityChanged)
			((TransactionsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerShow();
	}
}