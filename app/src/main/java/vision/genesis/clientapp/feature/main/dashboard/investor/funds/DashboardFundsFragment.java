package vision.genesis.clientapp.feature.main.dashboard.investor.funds;

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
import butterknife.Unbinder;
import io.swagger.client.model.FundDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class DashboardFundsFragment extends BaseFragment implements DashboardFundsView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static DashboardFundsFragment with() {
		DashboardFundsFragment dashboardProgramsFragment = new DashboardFundsFragment();
		Bundle arguments = new Bundle(1);
		dashboardProgramsFragment.setArguments(arguments);
		return dashboardProgramsFragment;
	}

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public DashboardFundsPresenter dashboardFundsPresenter;

	private DashboardFundsAdapter dashboardFundsAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_browse_funds)
	public void onBrowseFundsClicked() {
		dashboardFundsPresenter.onBrowseFundsClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard_funds, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		initRecyclerView();
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

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardFundsAdapter = new DashboardFundsAdapter();
		dashboardFundsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardFundsAdapter);
	}

	@Override
	public void setFunds(List<FundDetails> funds) {
		dashboardFundsAdapter.setFunds(funds);

		showEmpty(funds.size() == 0);
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
	public void pagerShow() {
		if (dashboardFundsPresenter != null)
			dashboardFundsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}