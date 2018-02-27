package vision.genesis.clientapp.feature.main.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
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
import io.swagger.client.model.InvestmentProgramDashboard;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class DashboardFragment extends BaseFragment implements DashboardView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@InjectPresenter
	DashboardPresenter dashboardPresenter;

	private DashboardAdapter dashboardAdapter;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		dashboardPresenter.onTryAgainClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
		initRefreshLayout();
		initRecyclerView();
	}

	@Override
	public void onResume() {
		super.onResume();
		dashboardPresenter.onResume();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.dashboard));
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> dashboardPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardAdapter = new DashboardAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
		dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(dashboardAdapter);
	}

	@Override
	public void setInvestorPrograms(List<InvestmentProgramDashboard> programs) {
		dashboardAdapter.setInvestorPrograms(programs);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showEmptyList() {

	}
}