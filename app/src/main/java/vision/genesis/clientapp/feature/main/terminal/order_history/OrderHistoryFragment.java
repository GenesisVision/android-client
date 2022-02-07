package vision.genesis.clientapp.feature.main.terminal.order_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.TradingPlatformBinanceOrdersMode;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.terminal.order_details.OrderDetailsDialog;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.terminal.binance_api.BinanceOrder;
import vision.genesis.clientapp.ui.DateRangeView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class OrderHistoryFragment extends BaseFragment implements OrderHistoryView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	private static final String EXTRA_SYMBOL = "extra_symbol";

	private static final String EXTRA_MODE = "extra_mode";

	public static OrderHistoryFragment with(UUID accountId, String symbol, TradingPlatformBinanceOrdersMode mode) {
		OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
		Bundle arguments = new Bundle(3);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_SYMBOL, symbol);
		arguments.putSerializable(EXTRA_MODE, mode);
		orderHistoryFragment.setArguments(arguments);
		return orderHistoryFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_orders)
	public View groupNoOrders;

	@BindView(R.id.text_no_orders)
	public TextView textNoOrders;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public OrderHistoryPresenter presenter;

	private OrderHistoryListAdapter adapter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

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
		return inflater.inflate(R.layout.fragment_order_history, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			UUID accountId = (UUID) getArguments().getSerializable(EXTRA_ACCOUNT_ID);
			String symbol = getArguments().getString(EXTRA_SYMBOL);
			TradingPlatformBinanceOrdersMode mode = (TradingPlatformBinanceOrdersMode) getArguments().getSerializable(EXTRA_MODE);
			if (accountId != null && symbol != null) {
				presenter.setData(accountId, symbol, mode);
				updateView(mode);
				return;
			}
		}
		Timber.e("Passed empty accountId to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(TradingPlatformBinanceOrdersMode mode) {
		initRecyclerView(mode);

		this.textNoOrders.setText(getString(mode.equals(TradingPlatformBinanceOrdersMode.ORDERHISTORY)
				? R.string.no_orders_found
				: R.string.no_trades_found));
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initRecyclerView(TradingPlatformBinanceOrdersMode mode) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		adapter = new OrderHistoryListAdapter(mode, this::showOrderDetails);
		recyclerView.setAdapter(adapter);

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
	public void setOrders(List<BinanceOrder> orders) {
		if (orders.isEmpty()) {
			groupNoOrders.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		adapter.setOrders(orders);
		groupNoOrders.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addOrders(List<BinanceOrder> orders) {
		adapter.addOrders(orders);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	public void showOrderDetails(BinanceOrder order) {
		if (getActivity() != null) {
			OrderDetailsDialog dialog = new OrderDetailsDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(order);
		}
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
		if (dateRangeView != null && root.getHeight() != 0) {
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
		}
	}
}