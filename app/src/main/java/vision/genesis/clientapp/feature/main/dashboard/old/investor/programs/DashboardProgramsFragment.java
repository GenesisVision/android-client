package vision.genesis.clientapp.feature.main.dashboard.old.investor.programs;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.DashboardPagerAdapter;
import vision.genesis.clientapp.feature.main.filters.FiltersActivity;
import vision.genesis.clientapp.model.filter.DashboardFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.ui.FiltersView;

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

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.filters)
	public FiltersView filters;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public DashboardProgramsPresenter dashboardProgramsPresenter;

	@BindDimen(R.dimen.filters_margin_bottom)
	public int filtersMarginBottom;

	@BindDimen(R.dimen.filters_margin_top)
	public int filtersMarginTop;

	private DashboardProgramsAdapter dashboardProgramsAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_browse_programs)
	public void onStartInvestingClicked() {
		dashboardProgramsPresenter.onStartInvestingClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		dashboardProgramsPresenter.onFiltersClicked();
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

//	@Override
//	public void onDestroyView() {
//		if (recyclerView != null)
//			recyclerView.setAdapter(null);
//
//		if (unbinder != null) {
//			unbinder.unbind();
//			unbinder = null;
//		}
//
//		super.onDestroyView();
//	}

	@Override
	public void onResume() {
		super.onResume();

		dashboardProgramsPresenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardProgramsAdapter = new DashboardProgramsAdapter();
		dashboardProgramsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardProgramsAdapter);
	}

//	@Override
//	public void setPrograms(List<ProgramDetails> programs) {
//		dashboardProgramsAdapter.setPrograms(programs);
//		recyclerView.scrollToPosition(0);
//
//		showEmpty(programs.size() == 0);
//	}

	@Override
	public void setProgramReinvest(UUID programId, Boolean reinvest) {
		dashboardProgramsAdapter.setProgramReinvest(programId, reinvest);
	}

	@Override
	public void setProgramFavorite(UUID programId, Boolean favorite) {
		dashboardProgramsAdapter.setProgramFavorite(programId, favorite);
	}

	@Override
	public void showFiltersActivity(DashboardFilter filter) {
		FiltersActivity.startFromFragment(this, filter.getUserFilter(UserFilter.TYPE_DASHBOARD_PROGRAMS_FILTER));
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
		if (!show) {
			filters.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void pagerShow() {
		if (dashboardProgramsPresenter != null) {
			dashboardProgramsPresenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onOffsetChanged(int verticalOffset) {
		if (filters != null) {
			float newY = root.getHeight() - verticalOffset - filters.getHeight() - filtersMarginBottom;
			if (newY < filtersMarginTop) {
				newY = filtersMarginTop;
			}
			filters.setY(newY);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == UserFilter.TYPE_DASHBOARD_PROGRAMS_FILTER && resultCode == Activity.RESULT_OK) {
			UserFilter userFilter = data.getParcelableExtra("filter");
			if (userFilter != null) {
				dashboardProgramsPresenter.onFilterUpdated(userFilter);
			}
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}