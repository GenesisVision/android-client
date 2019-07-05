package vision.genesis.clientapp.feature.main.copytrading.trades_history;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderSignalModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.copytrading.commissions.CommissionsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class CopytradingTradesHistoryFragment extends BaseFragment implements CopytradingTradesHistoryView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static final String LOCATION_COPYTRADING_ACCOUNT = "location_copytrading_account";

	public static final String LOCATION_DASHBOARD = "location_dashboard";

	private static final String EXTRA_LOCATION = "extra_location";

	private static final String EXTRA_ACCOUNT_CURRENCY = "extra_account_currency";

	public static CopytradingTradesHistoryFragment with(@NonNull String location, @Nullable String accountCurrency) {
		CopytradingTradesHistoryFragment copytradingTradesHistoryFragment = new CopytradingTradesHistoryFragment();
		Bundle arguments = new Bundle(2);
		arguments.putString(EXTRA_LOCATION, location);
		arguments.putString(EXTRA_ACCOUNT_CURRENCY, accountCurrency);
		copytradingTradesHistoryFragment.setArguments(arguments);
		return copytradingTradesHistoryFragment;
	}

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public CopytradingTradesHistoryPresenter copytradingTradesHistoryPresenter;

	private CopytradingTradesHistoryAdapter copytradingTradesHistoryAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard_trades_history, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		if (getArguments() != null) {
			String location = getArguments().getString(EXTRA_LOCATION);
			String accountCurrency = getArguments().getString(EXTRA_ACCOUNT_CURRENCY);
			copytradingTradesHistoryPresenter.setData(location, accountCurrency);

			initRecyclerView();
		}
		else {
			Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
			onBackPressed();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		copytradingTradesHistoryPresenter.onHide();
	}

	@Override
	public void onResume() {
		super.onResume();
		copytradingTradesHistoryPresenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		copytradingTradesHistoryAdapter = new CopytradingTradesHistoryAdapter();
		copytradingTradesHistoryAdapter.setHasStableIds(true);
		recyclerView.setAdapter(copytradingTradesHistoryAdapter);

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					copytradingTradesHistoryPresenter.onLastListItemVisible();
				}
			}
		});
	}

	@Override
	public void setTrades(List<OrderSignalModel> trades) {
		copytradingTradesHistoryAdapter.setTrades(trades);

		showEmpty(trades.size() == 0);
	}

	@Override
	public void addTrades(List<OrderSignalModel> newTrades) {
		copytradingTradesHistoryAdapter.addTrades(newTrades);
	}

	@Override
	public void showEmpty(boolean show) {
		if (emptyGroup != null) {
			emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void showCommissions(OrderSignalModel trade) {
		if (getActivity() != null) {
			CommissionsBottomSheetFragment fragment = new CommissionsBottomSheetFragment();
			fragment.setData(trade);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void showProgressBar(boolean show) {
		if (progressBar != null) {
			progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}


	@Override
	public void pagerShow() {
		if (copytradingTradesHistoryPresenter != null)
			copytradingTradesHistoryPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (copytradingTradesHistoryPresenter != null)
			copytradingTradesHistoryPresenter.onSwipeRefresh();
	}
}