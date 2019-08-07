package vision.genesis.clientapp.feature.main.dashboard.investor.copytrading;

import android.app.Activity;
import android.content.Intent;
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

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.SignalDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;
import vision.genesis.clientapp.feature.main.filters.FiltersActivity;
import vision.genesis.clientapp.model.filter.DashboardFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.ui.FiltersView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class DashboardCopytradingFragment extends BaseFragment implements DashboardCopytradingView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static DashboardCopytradingFragment with() {
		DashboardCopytradingFragment dashboardCopytradingFragment = new DashboardCopytradingFragment();
		Bundle arguments = new Bundle(1);
		dashboardCopytradingFragment.setArguments(arguments);
		return dashboardCopytradingFragment;
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
	public DashboardCopytradingPresenter dashboardCopytradingPresenter;

	@BindDimen(R.dimen.filters_margin_bottom)
	public int filtersMarginBottom;

	@BindDimen(R.dimen.filters_margin_top)
	public int filtersMarginTop;

	private DashboardCopytradingAdapter dashboardCopytradingAdapter;

	@OnClick(R.id.button_browse_programs)
	public void onStartInvestingClicked() {
		dashboardCopytradingPresenter.onStartInvestingClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		dashboardCopytradingPresenter.onFiltersClicked();
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

		dashboardCopytradingPresenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		dashboardCopytradingAdapter = new DashboardCopytradingAdapter();
		dashboardCopytradingAdapter.setHasStableIds(true);
		recyclerView.setAdapter(dashboardCopytradingAdapter);
	}

	@Override
	public void setSignals(List<SignalDetails> signals) {
		dashboardCopytradingAdapter.setSignals(signals);

		showEmpty(signals.size() == 0);
	}

	@Override
	public void showFiltersActivity(DashboardFilter filter) {
		FiltersActivity.startFromFragment(this, filter.getUserFilter(UserFilter.TYPE_DASHBOARD_SIGNALS_FILTER));
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
		if (dashboardCopytradingPresenter != null)
			dashboardCopytradingPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onOffsetChanged(int verticalOffset) {
		if (filters != null) {
			float newY = root.getHeight() - verticalOffset - filters.getHeight() - filtersMarginBottom;
			if (newY < filtersMarginTop)
				newY = filtersMarginTop;
			filters.setY(newY);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == UserFilter.TYPE_DASHBOARD_SIGNALS_FILTER && resultCode == Activity.RESULT_OK) {
			UserFilter userFilter = data.getParcelableExtra("filter");
			if (userFilter != null)
				dashboardCopytradingPresenter.onFilterUpdated(userFilter);
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}