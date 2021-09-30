package vision.genesis.clientapp.feature.main.terminal.open_orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceRawOrder;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.terminal.order_details.OrderDetailsDialog;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class OpenOrdersFragment extends BaseFragment implements OpenOrdersView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static OpenOrdersFragment with(UUID accountId) {
		OpenOrdersFragment openOrdersFragment = new OpenOrdersFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		openOrdersFragment.setArguments(arguments);
		return openOrdersFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_orders)
	public View groupNoOrders;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public OpenOrdersPresenter presenter;

	private OpenOrdersListAdapter adapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_open_orders, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();

		if (getArguments() != null) {
			UUID accountId = (UUID) getArguments().getSerializable(EXTRA_ACCOUNT_ID);
			if (accountId != null) {
				presenter.setData(accountId);
				return;
			}
		}
		Timber.e("Passed empty accountId to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		adapter = new OpenOrdersListAdapter(this::showOrderDetails);
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setOrders(List<BinanceRawOrder> orders) {
		adapter.setOrders(orders);
		if (orders.isEmpty()) {
			groupNoOrders.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		groupNoOrders.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	public void showOrderDetails(BinanceRawOrder order) {
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
}