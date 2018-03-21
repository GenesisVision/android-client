package vision.genesis.clientapp.feature.main.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgramDashboard;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.programs.DashboardPagerAdapter;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class DashboardFragment extends BaseFragment implements DashboardView, ViewPager.OnPageChangeListener
{
	@BindView(R.id.portfolio_value)
	public TextView portfolioValue;

	@BindView(R.id.portfolio_value_progress)
	public ProgressBar portfolioValueProgressBar;

	@BindView(R.id.label_total_portfolio_value)
	public TextView totalPortfolioValueLabel;

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.view_pager_dashboard)
	public ViewPager viewPager;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@InjectPresenter
	DashboardPresenter dashboardPresenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private DashboardPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		setFonts();

		initToolbar();
		initTabs();
		initViewPager();
	}

	@Override
	public void onResume() {
		super.onResume();
		dashboardPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (pagerAdapter != null)
			pagerAdapter.destroy();

		if (tabSelectedListener != null)
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);

		if (tabLayoutOnPageChangeListener != null)
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);

		viewPager.addOnPageChangeListener(this);

		super.onDestroyView();
	}

	private void setFonts() {
		portfolioValue.setTypeface(TypefaceUtil.light(getContext()));
		totalPortfolioValueLabel.setTypeface(TypefaceUtil.bold(getContext()));
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.dashboard));
	}

	private void initTabs() {
		tabLayout.addTab(tabLayout.newTab().setText(getContext().getResources().getString(R.string.active)), true);
		tabLayout.addTab(tabLayout.newTab().setText(getContext().getResources().getString(R.string.archived)));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		};

		tabLayout.addOnTabSelectedListener(tabSelectedListener);
	}

	private void initViewPager() {
		pagerAdapter = new DashboardPagerAdapter(getActivity().getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void setActivePrograms(List<InvestmentProgramDashboard> programs) {
		pagerAdapter.setActivePrograms(programs);
	}

	@Override
	public void setArchivedPrograms(List<InvestmentProgramDashboard> programs) {
		pagerAdapter.setArchivedPrograms(programs);
	}

	@Override
	public void showNoInternet(boolean show) {
		pagerAdapter.showNoInternet(show);
	}

	@Override
	public void showProgressBar(boolean show) {
		pagerAdapter.showProgressBar(show);
	}

	@Override
	public void showEmpty(boolean show) {
		pagerAdapter.showEmpty(show);
	}

	@Override
	public void setRefreshing(boolean show) {
		pagerAdapter.setRefreshing(show);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void onShow() {
		dashboardPresenter.onResume();
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof DashboardPagerAdapter.OnPageVisibilityChanged)
			((DashboardPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof DashboardPagerAdapter.OnPageVisibilityChanged) {
			((DashboardPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}