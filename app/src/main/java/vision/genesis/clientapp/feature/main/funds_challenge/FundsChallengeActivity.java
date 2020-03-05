package vision.genesis.clientapp.feature.main.funds_challenge;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.FundDetailsListItem;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.funds_list.FundsListAdapter;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/03/2020.
 */

public class FundsChallengeActivity extends BaseSwipeBackActivity implements FundsChallengeView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, AssetFacet facet) {
		Intent intent = new Intent(activity.getApplicationContext(), FundsChallengeActivity.class);
		FacetModel model = new FacetModel(facet.getId(), facet.getTitle(), facet.getTimeframe().toString(), facet.getSorting());
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.coordinator_layout)
	public CoordinatorLayout coordinatorLayout;

	@BindView(R.id.app_bar_layout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.winner_recycler_view)
	public RecyclerView winnerRecyclerView;

	@BindView(R.id.view_pager_funds_challenge)
	public ViewPager viewPager;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	FundsChallengePresenter presenter;

	private FundsListAdapter winnerAdapter;

	private FacetModel facetModel;

	private FundsChallengePagerAdapter pagerAdapter;

	private boolean isPagerDragging;

	private int verticalOffset = 0;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_info)
	public void onInfoClicked() {
		try {
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.funds_challenge_details_url)));
			startActivity(browserIntent);
		} catch (ActivityNotFoundException e) {
			showSnackbarMessage(getString(R.string.error_cannot_open_link));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_funds_challenge);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			facetModel = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));

			title.setText(facetModel.getTitle());

			initRefreshLayout();
			initWinnerRecycleView();
			initViewPager(facetModel);

			presenter.setData(facetModel);

			setOffsetListener();

			return;
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			presenter.onSwipeRefresh();
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
		});
	}

	private void initWinnerRecycleView() {
		winnerRecyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		winnerRecyclerView.setLayoutManager(layoutManager);
		winnerAdapter = new FundsListAdapter();
		winnerAdapter.setHasStableIds(true);
		winnerRecyclerView.setAdapter(winnerAdapter);
	}

	private void initViewPager(FacetModel facetModel) {
		pagerAdapter = new FundsChallengePagerAdapter(getSupportFragmentManager(), facetModel);
		viewPager.setAdapter(pagerAdapter);
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				isPagerDragging = state == ViewPager.SCROLL_STATE_DRAGGING;
				updateRefreshLayoutEnabled();
			}
		});
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(verticalOffset == 0 && !isPagerDragging);
	}

	private void setOffsetListener() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			this.verticalOffset = verticalOffset;
			updateRefreshLayoutEnabled();

			pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - toolbar.getHeight());
		});
	}

	@Override
	public void setWinner(FundDetailsListItem winner) {
		ArrayList<FundDetailsListItem> list = new ArrayList<>();
		list.add(winner);
		winnerAdapter.setFunds(list);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			this.coordinatorLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}