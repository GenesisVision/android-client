package vision.genesis.clientapp.feature.main.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
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
import butterknife.Unbinder;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsPagerAdapter;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletFragment extends BaseFragment implements WalletView
{
	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_balance)
	public ViewGroup balanceGroup;

	@BindView(R.id.label_balance)
	public TextView balanceLabel;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_secondary)
	public TextView balanceSecondary;

	@BindView(R.id.label_available)
	public TextView availableLabel;

	@BindView(R.id.available)
	public TextView available;

	@BindView(R.id.available_secondary)
	public TextView availableSecondary;

	@BindView(R.id.label_invested)
	public TextView investedLabel;

	@BindView(R.id.invested)
	public TextView invested;

	@BindView(R.id.invested_secondary)
	public TextView investedSecondary;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;

	@BindView(R.id.balance_progress)
	public ProgressBar balanceProgress;

	@BindView(R.id.view_pager_wallet)
	public ViewPager viewPager;

	@InjectPresenter
	WalletPresenter walletPresenter;

	private TransactionsPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	private Unbinder unbinder;

	@OnClick(R.id.group_balance)
	public void onBalanceGroupClicked() {
		walletPresenter.onBalanceGroupClicked();
	}

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

		unbinder = ButterKnife.bind(this, view);

		initViewPager();
		setFonts();

		withdrawButton.setEmpty();
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

		if (viewPager != null)
			viewPager.addOnPageChangeListener(walletPresenter);

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.bold());

		balanceLabel.setTypeface(TypefaceUtil.bold());
		balance.setTypeface(TypefaceUtil.light());
		balanceSecondary.setTypeface(TypefaceUtil.regular());

		availableLabel.setTypeface(TypefaceUtil.bold());
		available.setTypeface(TypefaceUtil.light());
		availableSecondary.setTypeface(TypefaceUtil.regular());

		investedLabel.setTypeface(TypefaceUtil.bold());
		invested.setTypeface(TypefaceUtil.light());
		investedSecondary.setTypeface(TypefaceUtil.regular());
	}

	private void initViewPager() {
		pagerAdapter = new TransactionsPagerAdapter(getActivity().getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

		viewPager.addOnPageChangeListener(walletPresenter);
		viewPager.setCurrentItem(0);
	}

	@Override
	public void setBalance(double balance) {
		this.balance.setText(String.format(Locale.getDefault(), "%s %s", StringFormatUtil.formatAmount(balance, 2,
				StringFormatUtil.getCurrencyMaxFraction(WalletTransaction.CurrencyEnum.GVT.toString())), getString(R.string.gvt)));
	}

	@Override
	public void setFiatBalance(double balance) {
		balanceSecondary.setText(String.format(Locale.getDefault(), "$ %s",
				StringFormatUtil.formatAmount(balance, 2,
						StringFormatUtil.getCurrencyMaxFraction(WalletTransaction.CurrencyEnum.USD.toString()))));
	}

	@Override
	public void setTransactionsFilterType(TransactionsFilter.TypeEnum type) {
		pagerAdapter.setTransactionsFilterType(type);
	}

	@Override
	public void showBalanceProgress() {
		balanceProgress.setVisibility(View.VISIBLE);
		balanceGroup.setVisibility(View.INVISIBLE);
	}

	@Override
	public void hideBalanceProgress() {
		balanceProgress.setVisibility(View.GONE);
		balanceGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, appBarLayout);
	}

	@Override
	public void showPage(int position) {
		if (currentFragment != null && currentFragment instanceof TransactionsPagerAdapter.OnPageVisibilityChanged)
			((TransactionsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof TransactionsPagerAdapter.OnPageVisibilityChanged) {
			((TransactionsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onShow() {
		walletPresenter.onResume();
		if (currentFragment != null && currentFragment instanceof TransactionsPagerAdapter.OnPageVisibilityChanged)
			((TransactionsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerShow();
	}
}