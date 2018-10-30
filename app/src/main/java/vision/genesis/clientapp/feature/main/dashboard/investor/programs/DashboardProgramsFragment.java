package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

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
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProgramDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;

/**
 * GenesisVision
 * Created by Vitaly on 3/13/18.
 */

public class DashboardProgramsFragment extends BaseFragment implements DashboardProgramsView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static DashboardProgramsFragment with() {
		DashboardProgramsFragment dashboardProgramsFragment = new DashboardProgramsFragment();
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
	public DashboardProgramsPresenter dashboardProgramsPresenter;

	private DashboardProgramsAdapter dashboardProgramsAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_browse_programs)
	public void onStartInvestingClicked() {
		dashboardProgramsPresenter.onStartInvestingClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard_programs, container, false);
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
		dashboardProgramsAdapter = new DashboardProgramsAdapter();
		dashboardProgramsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardProgramsAdapter);
	}

	@Override
	public void setPrograms(List<ProgramDetails> programs) {
		dashboardProgramsAdapter.setPrograms(programs);

		showEmpty(programs.size() == 0);
	}

	@Override
	public void setProgramReinvest(UUID programId, Boolean reinvest) {
		dashboardProgramsAdapter.setProgramReinvest(programId, reinvest);
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
		if (dashboardProgramsPresenter != null)
			dashboardProgramsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}