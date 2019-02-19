package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.MultiWalletTransaction;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletPagerAdapter;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class TransactionsFragment extends BaseFragment implements TransactionsView, WalletPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_WALLET_ID = "extra_wallet_id";

	public static TransactionsFragment with(@Nullable UUID walletId) {
		TransactionsFragment transactionsFragment = new TransactionsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_WALLET_ID, walletId);
		transactionsFragment.setArguments(arguments);
		return transactionsFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_transactions)
	public View groupNoTransactions;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.filters)
	public RelativeLayout filtersView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int filtersMarginBottom;

	@InjectPresenter
	public TransactionsPresenter transactionsPresenter;

	private TransactionsListAdapter transactionsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

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

		if (getArguments() != null) {
			transactionsPresenter.setWalletId((UUID) getArguments().getSerializable(EXTRA_WALLET_ID));

			initRecyclerView();
		}
		else {
			Timber.e("Passed empty arguments to TransactionsFragment");
			onBackPressed();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		transactionsPresenter.onShow();
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
					transactionsPresenter.onLastListItemVisible();
				}
			}
		});
	}


	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
//			filtersView.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setTransactions(List<MultiWalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
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
	public void addTransactions(List<MultiWalletTransaction> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
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

	public void onSwipeRefresh() {
		if (transactionsPresenter != null)
			transactionsPresenter.onSwipeRefresh();
	}

	public void onOffsetChanged(int verticalOffset) {
		if (filtersView != null)
			filtersView.setY(root.getHeight() - verticalOffset - filtersView.getHeight() - filtersMarginBottom);
	}
}