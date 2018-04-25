package vision.genesis.clientapp.feature.main.program;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramDetails;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.tooltip.TooltipActivity;
import vision.genesis.clientapp.model.ProgramInfoModel;
import vision.genesis.clientapp.model.TooltipModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramInfoActivity extends BaseSwipeBackActivity implements ProgramInfoView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, ProgramInfoModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramInfoActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.avatar)
	public AvatarView avatar;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.button_favorite)
	public ImageView favoriteButton;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_program_info)
	public ViewPager viewPager;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.progress_bar_no_internet)
	public ProgressBar noInternetProgressBar;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@InjectPresenter
	ProgramInfoPresenter programInfoPresenter;

	private InvestmentProgramDetails programDetails;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab detailsTab;

	private TabLayout.Tab descriptionTab;

	private TabLayout.Tab tradesTab;

	private TabLayout.Tab historyTab;

	private ProgramInfoPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	private ProgramInfoModel model;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		programInfoPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_favorite)
	public void onFavoriteClicked() {
		if (programDetails != null) {
			programDetails.isFavorite(!programDetails.isIsFavorite());
			setFavoriteButtonImage(programDetails.isIsFavorite());
			programInfoPresenter.onFavoriteButtonClicked(programDetails.isIsFavorite());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_info);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			initTabs();
			initViewPager(model.getProgramId());
			setFonts();

			avatar.hideLevel();
			updateHeader();

			programInfoPresenter.setProgramId(model.getProgramId());
		}
		else {
			Timber.e("Passed empty program to ProgramDetailsActivity");
			onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		if (pagerAdapter != null)
			pagerAdapter.destroy();

		if (tabSelectedListener != null)
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);

		if (tabLayoutOnPageChangeListener != null)
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);

		if (viewPager != null)
			viewPager.clearOnPageChangeListeners();

		super.onDestroy();
	}

	private void setFonts() {
		programName.setTypeface(TypefaceUtil.bold());
	}

	private void updateHeader() {
		avatar.setImage(model.getAvatar(), 50, 50);

		programName.setText(model.getProgramName());
		managerName.setText(String.format(Locale.getDefault(), "%s %s", getResources().getString(R.string.by), model.getManagerName()));

		setFavoriteButtonImage(model.isFavorite());
	}

	private void initTabs() {
		detailsTab = tabLayout.newTab().setText(getString(R.string.details)).setTag("details");
		descriptionTab = tabLayout.newTab().setText(getString(R.string.strategy)).setTag("description");
		tradesTab = tabLayout.newTab().setText(getString(R.string.trades)).setTag("trades");
		historyTab = tabLayout.newTab().setText(getString(R.string.history)).setTag("history");

		addPage(detailsTab, true);
		addPage(descriptionTab, false);

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

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION)
			return;

		tabLayout.addTab(tab, selected);
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
	}

	private void initViewPager(UUID programId) {
		pagerAdapter = new ProgramInfoPagerAdapter(getSupportFragmentManager(), tabLayout, programId);
		viewPager.setAdapter(pagerAdapter);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		programInfoPresenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setProgram(InvestmentProgramDetails programDetails) {
		this.programDetails = programDetails;

		if (programDetails.getTradesCount() > 0)
			addPage(tradesTab, false);

		if (programDetails.isIsHistoryEnable())
			addPage(historyTab, false);

		model.update(programDetails);
		updateHeader();

		tabLayout.setVisibility(View.VISIBLE);
		viewPager.setVisibility(View.VISIBLE);

		pagerAdapter.sendUpdate();
	}

	private void setFavoriteButtonImage(boolean isFavorite) {
		favoriteButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), isFavorite
				? R.drawable.ic_star_black_24dp
				: R.drawable.ic_star_border_black_24dp));

		favoriteButton.setColorFilter(ContextCompat.getColor(getApplicationContext(), isFavorite
						? R.color.gold
						: R.color.grey400),
				PorterDuff.Mode.SRC_IN);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof ProgramInfoPagerAdapter.OnPageVisibilityChanged)
			((ProgramInfoPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof ProgramInfoPagerAdapter.OnPageVisibilityChanged) {
			((ProgramInfoPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	public void onChartTouch() {
		viewPager.requestDisallowInterceptTouchEvent(true);
	}

	public void onChartTouchEnd() {
		viewPager.requestDisallowInterceptTouchEvent(false);

	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showTooltipActivity(TooltipModel tooltipModel) {
		TooltipActivity.startWith(this, tooltipModel);
	}

	@Override
	public void showFavoriteButton(boolean show) {
		favoriteButton.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, avatar);
	}

	@Override
	public void showNoInternetProgress(boolean show) {
		noInternetProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showTrades() {
		tradesTab.select();
	}
}
