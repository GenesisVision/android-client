package vision.genesis.clientapp.feature.main.traders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.InvestmentProgram;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class TradersFragment extends BaseFragment implements TradersView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.fab)
	public FloatingActionButton fab;

	@InjectPresenter
	TradersPresenter tradersPresenter;

	private boolean fabInAnim = false;

	private InvestmentProgramsListAdapter investmentProgramsListAdapter;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		tradersPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.fab)
	public void onFabClicked() {
		recyclerView.scrollToPosition(0);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_traders, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();

		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> tradersPresenter.onSwipeRefresh());
		initRecyclerView();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.traders));
		toolbar.addRightButton(R.drawable.ic_filter, () -> tradersPresenter.onFilterClicked());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		investmentProgramsListAdapter = new InvestmentProgramsListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
		dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(investmentProgramsListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					tradersPresenter.onLastListItemVisible();
				}
				if (dy != 0) {
					if (!fabInAnim && fab.getVisibility() != View.VISIBLE && lastVisible > 2)
						showFab();
					else if (!fabInAnim && fab.getVisibility() == View.VISIBLE && lastVisible < 2)
						hideFab();
				}
			}
		});
	}

	private void showFab() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_from_bottom);
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				fabInAnim = true;
				fab.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				fabInAnim = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		fab.startAnimation(animation);
	}

	private void hideFab() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_to_bottom);
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				fabInAnim = true;
				fab.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				fabInAnim = false;
				fab.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		fab.startAnimation(animation);
	}

	@Override
	public void setInvestmentPrograms(List<InvestmentProgram> programs) {
		investmentProgramsListAdapter.setInvestmentPrograms(programs);
	}

	@Override
	public void addInvestmentPrograms(List<InvestmentProgram> programs) {
		investmentProgramsListAdapter.addInvestmentPrograms(programs);
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

	@Override
	public boolean onBackPressed() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
		if (layoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
			recyclerView.smoothScrollToPosition(0);
			return true;
		}
		else
			return false;
	}
}
