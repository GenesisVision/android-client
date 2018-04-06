package vision.genesis.clientapp.feature.main.program.list;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.InvestmentProgram;
import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.FilterSortingOption;
import vision.genesis.clientapp.ui.SpinnerView;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class ProgramsListFragment extends BaseFragment implements ProgramsListView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_search)
	public ViewGroup searchGroup;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;

	@BindView(R.id.programs_count)
	public TextView programsCount;

	@BindView(R.id.programs_count_progress)
	public ProgressBar programsCountProgressBar;

	@BindView(R.id.label_programs_count)
	public TextView programsCountLabel;

	@BindView(R.id.spinner_view)
	public SpinnerView spinner;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.fab)
	public FloatingActionButton fab;

	@InjectPresenter
	ProgramsListPresenter programsListPresenter;

	private boolean fabInAnim = false;

	private int lastVisible = 0;

	private InvestmentProgramsListAdapter investmentProgramsListAdapter;

	private Subscription textChangeSubscription;

	private Unbinder unbinder;

	@OnClick(R.id.button_search_close)
	public void onSearchCloseClicked() {
		programsListPresenter.onSearchCloseClicked();
	}


	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		programsListPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.fab)
	public void onFabClicked() {
		if (lastVisible < 20)
			recyclerView.smoothScrollToPosition(0);
		else
			recyclerView.scrollToPosition(0);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_programs_list, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		initToolbar();
		initSpinner();
		initRefreshLayout();
		initRecyclerView();
	}

	@Override
	public void onStart() {
		super.onStart();
		setSearchTextListener();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (textChangeSubscription != null)
			textChangeSubscription.unsubscribe();
		hideSoftKeyboard();
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

	private void setFonts() {
		programsCount.setTypeface(TypefaceUtil.light());
		programsCountLabel.setTypeface(TypefaceUtil.bold());
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.programs));
		toolbar.addRightSecondButton(R.drawable.ic_search_black_24dp, () -> programsListPresenter.onSearchClicked());
		toolbar.addRightButton(R.drawable.filters_icon, () -> programsListPresenter.onFilterClicked());
	}

	private void initSpinner() {
		ArrayList<FilterSortingOption> sortingOptions = FilterSortingOption.getOptions(GenesisVisionApplication.INSTANCE);
		spinner.setData(sortingOptions);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				programsListPresenter.onSortingSelected(sortingOptions.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> programsListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		investmentProgramsListAdapter = new InvestmentProgramsListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
		dividerItemDecoration.setDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(investmentProgramsListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				checkIfLastItemVisible();
			}
		});
	}

	private void checkIfLastItemVisible() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
		int totalItemCount = layoutManager.getItemCount();
		lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
		if (lastVisible < 0)
			return;

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			programsListPresenter.onLastListItemVisible();
		}
		if (!fabInAnim && fab.getVisibility() != View.VISIBLE && lastVisible > 3)
			showFab();
		else if (!fabInAnim && fab.getVisibility() == View.VISIBLE && lastVisible < 3)
			hideFab();
	}

	private void setSearchTextListener() {
		textChangeSubscription = RxTextView.textChanges(searchEditText)
				.subscribe(text -> programsListPresenter.onSearchTextChanged(text.toString()));
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

		programsCountProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		programsCount.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showEmptyList(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void setProgramsCount(Integer count) {
		programsCount.setText(StringFormatUtil.formatAmount((double) count, 0, 0));
	}

	@Override
	public void showFiltersActive(boolean show) {
		toolbar.showRightButtonDot(show);
	}

	@Override
	public void showSearch(boolean show) {
		searchGroup.setVisibility(show ? View.VISIBLE : View.GONE);

		if (show) {
			showSoftKeyboard();
		}
		else {
			hideSoftKeyboard();
			searchEditText.setText("");
		}
	}

	@Override
	public boolean onBackPressed() {
		return false;
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.requestFocus();
		if (imm != null) {
			imm.showSoftInput(searchEditText, 0);
		}
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
		}
	}
}
