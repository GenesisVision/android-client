package vision.genesis.clientapp.feature.main.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.programs.DashboardPagerAdapter;
import vision.genesis.clientapp.feature.main.tooltip.TooltipActivity;
import vision.genesis.clientapp.model.InvestmentProgramDashboardExtended;
import vision.genesis.clientapp.model.TooltipModel;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.StringFormatUtil;
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

	@BindView(R.id.group_portfolio_value)
	public ViewGroup portfolioValueGroup;

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

	private Unbinder unbinder;

	@OnClick(R.id.send_feedback)
	public void onSendFeedbackClicked() {
		showFeedbackDialog();
	}

	@OnClick(R.id.tooltip_total_portfolio_value)
	public void onTooltipTotalPortfolioClicked() {
		showTooltip(portfolioValueGroup, R.string.tooltip_total_portfolio_value);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

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

		if (viewPager != null)
			viewPager.clearOnPageChangeListeners();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void showTooltip(View view, int tooltipTextResId) {
		int[] viewLocation = new int[2];
		view.getLocationInWindow(viewLocation);
		float viewX = viewLocation[0];
		float viewY = viewLocation[1];

		TooltipModel tooltipModel = new TooltipModel(
				viewX + view.getWidth() / 2,
				viewY,
				viewY + view.getHeight(),
				getString(tooltipTextResId));

		if (getActivity() != null)
			TooltipActivity.startWith(getActivity(), tooltipModel);
	}

	private void setFonts() {
		portfolioValue.setTypeface(TypefaceUtil.light());
		totalPortfolioValueLabel.setTypeface(TypefaceUtil.bold());
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

	private void showFeedbackDialog() {
		CharSequence options[] = new CharSequence[]{getString(R.string.visit_feedback_website), getString(R.string.send_email)};

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.feedback_dialog_title));
		builder.setItems(options, (dialog, optionId) -> {
			if (optionId == 0)
				openFeedbackSite();
			else
				sendFeedbackEmail();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	private void openFeedbackSite() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.feedback_web_address)));
		startActivity(browserIntent);
	}

	private void sendFeedbackEmail() {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("message/rfc822");  //set the email recipient
		String recipient = getString(R.string.feedback_email_address);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android feedback");
		try {
			emailIntent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), "\n\nversion %s (%d)\n%s %s\n%s %s",
					BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
					Build.MANUFACTURER, Build.MODEL,
					Build.VERSION.RELEASE, Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		startActivity(Intent.createChooser(emailIntent, "Send email using..."));
	}

	@Override
	public void setActivePrograms(List<InvestmentProgramDashboardExtended> programs) {
		pagerAdapter.setActivePrograms(programs);
	}

	@Override
	public void setArchivedPrograms(List<InvestmentProgramDashboardExtended> programs) {
		pagerAdapter.setArchivedPrograms(programs);
	}

	@Override
	public void showNoInternet(boolean show) {
		pagerAdapter.showNoInternet(show);
	}

	@Override
	public void showProgressBar(boolean show) {
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
	public void setTotalPortfolioValue(Double totalPortfolioAmount) {
		portfolioValueProgressBar.setVisibility(View.GONE);
		portfolioValue.setText(String.format(Locale.getDefault(), "$%s", StringFormatUtil.formatAmount(totalPortfolioAmount, 2, 2)));
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