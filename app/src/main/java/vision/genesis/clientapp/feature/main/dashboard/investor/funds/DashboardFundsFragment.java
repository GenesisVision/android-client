package vision.genesis.clientapp.feature.main.dashboard.investor.funds;

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
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.FundDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;
import vision.genesis.clientapp.feature.main.filters.FiltersActivity;
import vision.genesis.clientapp.model.filter.DashboardFilter;
import vision.genesis.clientapp.model.filter.UserFilter;
import vision.genesis.clientapp.ui.FiltersView;

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
	public DashboardFundsPresenter dashboardFundsPresenter;

	@BindDimen(R.dimen.filters_margin_bottom)
	public int filtersMarginBottom;

	@BindDimen(R.dimen.filters_margin_top)
	public int filtersMarginTop;

	private DashboardFundsAdapter dashboardFundsAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_browse_funds)
	public void onBrowseFundsClicked() {
		dashboardFundsPresenter.onBrowseFundsClicked();
	}

	@OnClick(R.id.filters)
	public void onFiltersClicked() {
		dashboardFundsPresenter.onFiltersClicked();
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
	public void setFundFavorite(UUID fundId, Boolean favorite) {
		dashboardFundsAdapter.setFundFavorite(fundId, favorite);
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
	public void showFiltersActivity(DashboardFilter filter) {
		FiltersActivity.startFromFragment(this, filter.getUserFilter(UserFilter.TYPE_DASHBOARD_FUNDS_FILTER));
	}

	@Override
	public void pagerShow() {
		if (dashboardFundsPresenter != null)
			dashboardFundsPresenter.onShow();
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
		if (requestCode == UserFilter.TYPE_DASHBOARD_FUNDS_FILTER && resultCode == Activity.RESULT_OK) {
			UserFilter userFilter = data.getParcelableExtra("filter");
			if (userFilter != null)
				dashboardFundsPresenter.onFilterUpdated(userFilter);
		}
		else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}