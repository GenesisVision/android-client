package vision.genesis.clientapp.feature.main.dashboard.investor.trades_history;

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
import butterknife.OnClick;
import io.swagger.client.model.SignalDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class DashboardTradesHistoryFragment extends BaseFragment implements DashboardTradesHistoryView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static DashboardTradesHistoryFragment with() {
		DashboardTradesHistoryFragment dashboardTradesHistoryFragment = new DashboardTradesHistoryFragment();
		Bundle arguments = new Bundle(1);
		dashboardTradesHistoryFragment.setArguments(arguments);
		return dashboardTradesHistoryFragment;
	}

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public DashboardTradesHistoryPresenter dashboardTradesHistoryPresenter;

	private DashboardTradesHistoryAdapter dashboardTradesHistoryAdapter;

	@OnClick(R.id.button_browse_programs)
	public void onStartInvestingClicked() {
		dashboardTradesHistoryPresenter.onStartInvestingClicked();
	}

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

		dashboardTradesHistoryPresenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardTradesHistoryAdapter = new DashboardTradesHistoryAdapter();
		dashboardTradesHistoryAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardTradesHistoryAdapter);
	}

	@Override
	public void setSignals(List<SignalDetails> signals) {
		dashboardTradesHistoryAdapter.setSignals(signals);

		showEmpty(signals.size() == 0);
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
		if (dashboardTradesHistoryPresenter != null)
			dashboardTradesHistoryPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}