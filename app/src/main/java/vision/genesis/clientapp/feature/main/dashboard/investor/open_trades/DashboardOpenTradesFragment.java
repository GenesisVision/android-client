package vision.genesis.clientapp.feature.main.dashboard.investor.open_trades;

import android.os.Build;
import android.os.Bundle;
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
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class DashboardOpenTradesFragment extends BaseFragment implements DashboardOpenTradesView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static DashboardOpenTradesFragment with() {
		DashboardOpenTradesFragment dashboardOpenTradesFragment = new DashboardOpenTradesFragment();
		Bundle arguments = new Bundle(1);
		dashboardOpenTradesFragment.setArguments(arguments);
		return dashboardOpenTradesFragment;
	}

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public DashboardOpenTradesPresenter dashboardOpenTradesPresenter;

	private DashboardOpenTradesAdapter dashboardOpenTradesAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard_copytrading, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		initRecyclerView();
	}

	@Override
	public void onResume() {
		super.onResume();

		dashboardOpenTradesPresenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardOpenTradesAdapter = new DashboardOpenTradesAdapter();
		dashboardOpenTradesAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardOpenTradesAdapter);
	}

	@Override
	public void setOpenTrades(List<OrderSignalModel> trades) {
		dashboardOpenTradesAdapter.setTrades(trades);

		showEmpty(trades.size() == 0);
	}

	@Override
	public void showEmpty(boolean show) {
		if (emptyGroup != null) {
			emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
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
		if (dashboardOpenTradesPresenter != null)
			dashboardOpenTradesPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}