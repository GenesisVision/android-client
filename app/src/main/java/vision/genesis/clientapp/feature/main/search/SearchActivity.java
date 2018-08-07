package vision.genesis.clientapp.feature.main.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import io.swagger.client.model.ProgramDetails;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

public class SearchActivity extends MvpAppCompatActivity implements SearchView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), SearchActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@BindView(R.id.version)
	public TextView version;

//	@BindView(R.id.scrollview)
//	public NestedScrollView scrollView;

	@BindView(R.id.background_black)
	public View backgroundBlack;

	@BindView(R.id.background)
	public View background;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;

	@BindView(R.id.text_results_count)
	public TextView resultsCount;

	@BindView(R.id.programs_recycler_view)
	public RecyclerView programsRecyclerView;

//	@BindView(R.id.tournament_recycler_view)
//	public RecyclerView tournamentRecyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

//	@BindView(R.id.text_programs)
//	public TextView programsText;
//
//	@BindView(R.id.text_tournament)
//	public TextView tournamentText;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SearchPresenter searchPresenter;

	private ProgramsListAdapter programsAdapter;

	private int lastVisible = 0;

//	private ProgramsListAdapter tournamentAdapter;

	@OnClick(R.id.background_black)
	public void onBackgroundBlackClicked() {
		if (background.getVisibility() == View.VISIBLE)
			hideSoftKeyboard();
		else
			onBackPressed();
	}

	@OnClick(R.id.icon_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnFocusChange(R.id.edittext_search)
	void onSearchFocusChange(View view, boolean hasFocus) {
		if (hasFocus)
			showBackgroundBlack();
		else
			hideBackgroundBlack();
	}

	@OnEditorAction(R.id.edittext_search)
	protected boolean onSearchEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			searchPresenter.onSearchClicked(searchEditText.getText().toString());
//			searchEditText.clearFocus();
			background.setVisibility(View.VISIBLE);
			hideSoftKeyboard();
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_search);

		ButterKnife.bind(this);

		initRecyclerViews();

		showSoftKeyboard();

		version.setText(String.format(Locale.getDefault(), "%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
	}

	@Override
	protected void onDestroy() {
		if (programsRecyclerView != null)
			programsRecyclerView.setAdapter(null);

//		if (tournamentRecyclerView != null)
//			tournamentRecyclerView.setAdapter(null);

		super.onDestroy();
	}

	private void initRecyclerViews() {
		RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

		programsRecyclerView.setRecycledViewPool(viewPool);
		programsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		programsAdapter = new ProgramsListAdapter();
		programsAdapter.setHasStableIds(true);
		programsRecyclerView.setAdapter(programsAdapter);
		programsRecyclerView.setNestedScrollingEnabled(false);
		programsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				checkIfLastItemVisible();
			}
		});
	}

	private void checkIfLastItemVisible() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(programsRecyclerView.getLayoutManager());
		int totalItemCount = layoutManager.getItemCount();
		lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
		if (lastVisible < 0)
			return;

		boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			searchPresenter.onLastListItemVisible();
		}
	}

	private void showBackgroundBlack() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_in);
		animation.setInterpolator(new LinearInterpolator());
		backgroundBlack.startAnimation(animation);
		backgroundBlack.setVisibility(View.VISIBLE);
	}

	private void hideBackgroundBlack() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.tooltip_fade_out);
		animation.setInterpolator(new LinearInterpolator());
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				backgroundBlack.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		backgroundBlack.startAnimation(animation);
	}

	@Override
	public void setInvestmentPrograms(List<ProgramDetails> programs) {
//		programsText.setVisibility(!programs.isEmpty() ? View.VISIBLE : View.GONE);
		programsAdapter.setInvestmentPrograms(programs);
		programsRecyclerView.scrollToPosition(0);
	}

	@Override
	public void addInvestmentPrograms(List<ProgramDetails> programs) {
		programsAdapter.addInvestmentPrograms(programs);
	}

	@Override
	public void showSnackbarMessage(String message) {
		Snackbar snack = Snackbar.make(background, message, Snackbar.LENGTH_LONG);
		((TextView) snack.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
		snack.show();
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		programsRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (show)
			programsRecyclerView.scrollToPosition(0);
	}

	@Override
	public void showEmptyList(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		programsRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}

	@Override
	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		programsAdapter.changeProgramIsFavorite(programId, isFavorite);
//		tournamentAdapter.changeProgramIsFavorite(programId, isFavorite);
	}

	@Override
	public void setResultsCount(String count) {
		resultsCount.setText(String.format(Locale.getDefault(), "%s: %s", getString(R.string.results), count));
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.requestFocus();
		if (imm != null) {
			imm.showSoftInput(searchEditText, 0);
		}
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
		}
	}
}
