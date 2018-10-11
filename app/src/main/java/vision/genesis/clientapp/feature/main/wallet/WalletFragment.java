package vision.genesis.clientapp.feature.main.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.wallet.deposit.DepositWalletActivity;
import vision.genesis.clientapp.feature.main.wallet.withdraw.WithdrawWalletActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletFragment extends BaseFragment implements WalletView
{
	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.withdraw)
	public TextView withdraw;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.add_funds)
	public TextView addFunds;

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

	@BindView(R.id.label_transactions)
	public TextView transactionsLabel;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_transactions)
	public ViewGroup groupNoTransactions;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	WalletPresenter walletPresenter;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	private TransactionsListAdapter transactionsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	@OnClick(R.id.group_currency)
	public void onCurrencyClicked() {
		if (getActivity() != null) {
			SelectCurrencyFragment fragment = SelectCurrencyFragment.with(baseCurrency.getValue());
			fragment.setListener(walletPresenter);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(walletPresenter);
		}
	}

	@OnClick(R.id.withdraw)
	public void onWithdrawButtonClicked() {
		if (getActivity() != null) {
			WithdrawWalletActivity.startWith(getActivity());
		}
	}

	@OnClick(R.id.add_funds)
	public void onAddFundsButtonClicked() {
		if (getActivity() != null) {
			DepositWalletActivity.startWith(getActivity());
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

		initRecyclerView();
		initRefreshLayout();
		setOffsetListener();
	}

	@Override
	public void onResume() {
		super.onResume();

		walletPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		withdraw.setTypeface(TypefaceUtil.semibold());
		addFunds.setTypeface(TypefaceUtil.semibold());
		currency.setTypeface(TypefaceUtil.semibold());

		balanceLabel.setTypeface(TypefaceUtil.semibold());
		balance.setTypeface(TypefaceUtil.semibold());

		available.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());

		transactionsLabel.setTypeface(TypefaceUtil.semibold());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		transactionsListAdapter = new TransactionsListAdapter();
		sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getContext(), R.layout.list_item_trades_date_section, R.id.text, transactionsListAdapter);
		recyclerView.setAdapter(sectionedAdapter);

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					walletPresenter.onLastListItemVisible();
				}
			}
		});
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> walletPresenter.onSwipeRefresh());
	}

	private void setOffsetListener() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> refreshLayout.setEnabled(verticalOffset == 0));
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		currency.setText(baseCurrency.getValue());
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void setBalance(WalletSummary data) {
		this.balance.setText(StringFormatUtil.getGvtValueString(data.getTotalBalanceGVT()));
		this.balanceBase.setText(StringFormatUtil.getBaseValueString(data.getTotalBalanceCurrency(), baseCurrency.getValue()));

		this.available.setText(StringFormatUtil.getGvtValueString(data.getAvailableGVT()));
		this.availableBase.setText(StringFormatUtil.getBaseValueString(data.getAvailableCurrency(), baseCurrency.getValue()));

		this.invested.setText(StringFormatUtil.getGvtValueString(data.getInvestedGVT()));
		this.investedBase.setText(StringFormatUtil.getBaseValueString(data.getInvestedCurrency(), baseCurrency.getValue()));

		availableShare.setProgress((int) (data.getAvailableGVT() * 100 / data.getTotalBalanceGVT()));
		investedShare.setProgress((int) (data.getInvestedGVT() * 100 / data.getTotalBalanceGVT()));
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			refreshLayout.setVisibility(View.VISIBLE);
			dateRangeView.setVisibility(View.VISIBLE);
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
	public void setTransactions(List<WalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		if (transactions.isEmpty()) {
			groupNoTransactions.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		sectionedAdapter.setSections(sections);
		transactionsListAdapter.setTransactions(transactions);
		groupNoTransactions.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addTransactions(List<WalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
		transactionsListAdapter.addTransactions(transactions);
	}

}