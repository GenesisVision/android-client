package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.TransactionViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.wallet.WalletPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class TransactionsFragment extends BaseFragment implements TransactionsView, WalletPagerAdapter.OnPageVisibilityChanged
{
	public static final String LOCATION_WALLET = "location_wallet";

	public static final String LOCATION_SPECIFIC_WALLET = "location_specific_wallet";

	private static final String EXTRA_LOCATION = "extra_location";

	private static final String EXTRA_WALLET_CURRENCY = "extra_wallet_id";

	public static TransactionsFragment with(@NonNull String location, @Nullable String walletCurrency) {
		TransactionsFragment transactionsFragment = new TransactionsFragment();
		Bundle arguments = new Bundle(2);
		arguments.putString(EXTRA_LOCATION, location);
		arguments.putString(EXTRA_WALLET_CURRENCY, walletCurrency);
		transactionsFragment.setArguments(arguments);
		return transactionsFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_transactions)
	public View groupNoTransactions;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public TransactionsPresenter presenter;

	private TransactionsListAdapter transactionsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private ArrayList<String> typeOptions;

	private Integer selectedTypePosition = -1;

	@OnClick(R.id.group_type)
	public void onTypeClicked() {
		if (getActivity() != null && typeOptions != null && typeOptions.size() > 0) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.select_type), typeOptions, selectedTypePosition);
			fragment.setListener((position, text) -> presenter.onTypeOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(presenter);
		}
	}

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
			String location = getArguments().getString(EXTRA_LOCATION);
			String walletCurrency = getArguments().getString(EXTRA_WALLET_CURRENCY);
			presenter.setData(location, walletCurrency);

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

		presenter.onShow();
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
					presenter.onLastListItemVisible();
				}
			}
		});
	}

	@Override
	public void setTypeOptions(ArrayList<String> typeOptions) {
		this.typeOptions = typeOptions;
	}

	@Override
	public void setType(String type, Integer position) {
		this.type.setText(type);
		this.selectedTypePosition = position;
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			dateRangeView.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void setTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
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
	public void addTransactions(List<TransactionViewModel> transactions, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
		transactionsListAdapter.addTransactions(transactions);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (presenter != null) {
			presenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (presenter != null) {
			presenter.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null) {
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
		}
	}
}