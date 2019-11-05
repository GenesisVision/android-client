package vision.genesis.clientapp.feature.main.wallet.external_transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.TransactionViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletPagerAdapter;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class ExternalTransactionsFragment extends BaseFragment implements ExternalTransactionsView, WalletPagerAdapter.OnPageVisibilityChanged
{
	public static final String LOCATION_WALLET = "location_wallet";

	public static final String LOCATION_SPECIFIC_WALLET = "location_specific_wallet";

	private static final String EXTRA_LOCATION = "extra_location";

	private static final String EXTRA_WALLET_CURRENCY = "extra_wallet_currency";

	public static ExternalTransactionsFragment with(@NonNull String location, @Nullable String walletCurrency) {
		ExternalTransactionsFragment externalTransactionsFragment = new ExternalTransactionsFragment();
		Bundle arguments = new Bundle(2);
		arguments.putSerializable(EXTRA_LOCATION, location);
		arguments.putSerializable(EXTRA_WALLET_CURRENCY, walletCurrency);
		externalTransactionsFragment.setArguments(arguments);
		return externalTransactionsFragment;
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
	public ExternalTransactionsPresenter externalTransactionsPresenter;

	private ExternalTransactionsListAdapter depositsWithdrawalsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_external_transactions, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			String location = getArguments().getString(EXTRA_LOCATION);
			String walletCurrency = getArguments().getString(EXTRA_WALLET_CURRENCY);
			externalTransactionsPresenter.setData(location, walletCurrency);

			initRecyclerView();
		}
		else {
			Timber.e("Passed empty arguments to DepositsWithdrawalsFragment");
			onBackPressed();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		externalTransactionsPresenter.onShow();
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

		depositsWithdrawalsListAdapter = new ExternalTransactionsListAdapter();
		sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getContext(), R.layout.list_item_trades_date_section, R.id.text, depositsWithdrawalsListAdapter);
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
					externalTransactionsPresenter.onLastListItemVisible();
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
	public void setTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		if (transactions.isEmpty()) {
			groupNoTransactions.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		sectionedAdapter.setSections(sections);
		depositsWithdrawalsListAdapter.setTransactions(transactions);
		groupNoTransactions.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
		depositsWithdrawalsListAdapter.addTransactions(transactions);
	}

	@Override
	public void setStatusCanceled(UUID transactionId) {
		depositsWithdrawalsListAdapter.setStatusCanceled(transactionId);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (externalTransactionsPresenter != null) {
			externalTransactionsPresenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (externalTransactionsPresenter != null) {
			externalTransactionsPresenter.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		if (filtersView != null) {
			filtersView.setY(root.getHeight() - verticalOffset - filtersView.getHeight() - filtersMarginBottom);
		}
	}
}