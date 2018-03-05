package vision.genesis.clientapp.feature.main.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletFragment extends BaseFragment implements WalletView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_balance)
	public ViewGroup balanceGroup;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_fiat)
	public TextView balanceFiat;

	@BindView(R.id.group_no_transactions)
	public View groupNoTransactions;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.balance_progress)
	public ProgressBar balanceProgress;

	@InjectPresenter
	WalletPresenter walletPresenter;

	private TransactionsListAdapter transactionsListAdapter;

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

		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> walletPresenter.onSwipeRefresh());
		initRecyclerView();
	}

	@Override
	public void onResume() {
		super.onResume();

		walletPresenter.onResume();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.wallet));
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		transactionsListAdapter = new TransactionsListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
		dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(transactionsListAdapter);
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

	@Override
	public void setBalance(double balance) {
		this.balance.setText(StringFormatUtil.formatAmount(balance));
	}

	@Override
	public void setFiatBalance(double balance) {
		balanceFiat.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(balance)));
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
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setTransactions(List<WalletTransaction> transactions) {
		transactionsListAdapter.setTransactions(transactions);

		groupNoTransactions.setVisibility(transactions.size() == 0
				? View.VISIBLE
				: View.GONE);
	}

	@Override
	public void addTransactions(List<WalletTransaction> transactions) {
		transactionsListAdapter.addTransactions(transactions);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}
}
