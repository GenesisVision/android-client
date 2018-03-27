package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class TransactionsFragment extends BaseFragment implements TransactionsView, TransactionsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_TYPE = "extra_type";

	public static TransactionsFragment with(TransactionsFilter.TypeEnum type) {
		TransactionsFragment transactionsFragment = new TransactionsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putString(EXTRA_TYPE, type.toString());
		transactionsFragment.setArguments(arguments);
		return transactionsFragment;
	}

	@BindView(R.id.group_no_transactions)
	public View groupNoTransactions;

	@BindView(R.id.label_whoops)
	public TextView whoopsLabel;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public TransactionsPresenter transactionsPresenter;

	private TransactionsListAdapter transactionsListAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_transactions, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		setFonts();

		transactionsPresenter.setType(getArguments().getString(EXTRA_TYPE));

		initRefreshLayout();
		initRecyclerView();

	}

	@Override
	public void onResume() {
		super.onResume();

		transactionsPresenter.onShow();
	}

	private void setFonts() {
		whoopsLabel.setTypeface(TypefaceUtil.bold(getContext()));
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> transactionsPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		transactionsListAdapter = new TransactionsListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
				ContextCompat.getDrawable(getContext(), R.drawable.divider_dot_horizontal),
				20, 20);
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
					transactionsPresenter.onLastListItemVisible();
				}
			}
		});
	}

	public void setTransactionsFilterType(TransactionsFilter.TypeEnum type) {
		transactionsPresenter.setType(type.toString());
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
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (transactionsPresenter != null)
			transactionsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}