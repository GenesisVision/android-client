package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.wallet.TransactionsListAdapter;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 3/5/18.
 */

public class TransactionsFragment extends BaseFragment implements TransactionsView, TransactionsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_TYPE = "extra_type";

	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	public static TransactionsFragment with(@Nullable UUID programId) {
		TransactionsFragment transactionsFragment = new TransactionsFragment();
		Bundle arguments = new Bundle(2);
//		arguments.putString(EXTRA_TYPE, type.toString());
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
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

	private UUID programId;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_transactions, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		programId = (UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID);
//		transactionsPresenter.setFilter(getArguments().getString(EXTRA_TYPE), programId);

		initRefreshLayout();
		initRecyclerView();

	}

	@Override
	public void onResume() {
		super.onResume();

		transactionsPresenter.onShow();
	}

	@Override
	public void onDestroyView() {
		if (recyclerView != null)
			recyclerView.setAdapter(null);

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		whoopsLabel.setTypeface(TypefaceUtil.bold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> transactionsPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		transactionsListAdapter = new TransactionsListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
				AppCompatResources.getDrawable(getContext(), R.drawable.list_item_divider),
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

//	public void setTransactionsFilterType(TransactionsFilter.TypeEnum type) {
//		transactionsPresenter.setFilter(type.toString(), null);
//	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

//	@Override
//	public void setTransactions(List<WalletTransaction> transactions) {
//		transactionsListAdapter.setTransactions(transactions);
//
//		groupNoEvents.setVisibility(transactions.size() == 0
//				? View.VISIBLE
//				: View.GONE);
//	}
//
//	@Override
//	public void addTransactions(List<WalletTransaction> transactions) {
//		transactionsListAdapter.addTransactions(transactions);
//	}

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