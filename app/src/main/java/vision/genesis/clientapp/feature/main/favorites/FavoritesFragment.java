package vision.genesis.clientapp.feature.main.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProgramDetailsList;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.programs_list.ProgramsListAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

public class FavoritesFragment extends BaseFragment implements FavoritesView
{
	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.programs_recycler_view)
	public RecyclerView programsRecyclerView;

//	@BindView(R.id.tournament_recycler_view)
//	public RecyclerView tournamentRecyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindView(R.id.group_should_sign_in)
	public ViewGroup shouldSignInGroup;

//	@BindView(R.id.text_programs)
//	public TextView programsText;
//
//	@BindView(R.id.text_tournament)
//	public TextView tournamentText;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	FavoritesPresenter favoritesPresenter;

	private ProgramsListAdapter programsAdapter;

//	private ProgramsListAdapter tournamentAdapter;

	private Unbinder unbinder;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		favoritesPresenter.onTryAgainClicked();
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_favorites, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRefreshLayout();
		initRecyclerViews();
	}

	@Override
	public void onDestroyView() {
		if (programsRecyclerView != null) {
			programsRecyclerView.setAdapter(null);
		}

//		if (tournamentRecyclerView != null)
//			tournamentRecyclerView.setAdapter(null);

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> favoritesPresenter.onSwipeRefresh());
	}

	private void initRecyclerViews() {
		RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

		programsRecyclerView.setRecycledViewPool(viewPool);
		programsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		programsAdapter = new ProgramsListAdapter();
		programsAdapter.setHasStableIds(true);
		programsRecyclerView.setAdapter(programsAdapter);
//		programsRecyclerView.setHasFixedSize(true);

//		tournamentRecyclerView.setRecycledViewPool(viewPool);
//		tournamentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//		tournamentAdapter = new ProgramsListAdapter();
//		tournamentAdapter.setHasStableIds(true);
//		tournamentRecyclerView.setAdapter(tournamentAdapter);
//		tournamentRecyclerView.setNestedScrollingEnabled(false);
//		tournamentRecyclerView.setHasFixedSize(true);
	}

	@Override
	public void setInvestmentPrograms(List<ProgramDetailsList> programs) {
//		programsText.setVisibility(!programs.isEmpty() ? View.VISIBLE : View.GONE);
		programsAdapter.setInvestmentPrograms(programs);
	}

//	@Override
//	public void setTournamentPrograms(List<InvestmentProgramExtended> programs) {
//		tournamentText.setVisibility(!programs.isEmpty() ? View.VISIBLE : View.GONE);
//		tournamentAdapter.setInvestmentPrograms(programs);
//	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, refreshLayout);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showEmptyList(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void removeProgram(UUID programId) {
		programsAdapter.removeProgram(programId);
//		tournamentAdapter.removeProgram(programId);
	}

	@Override
	public void showUserLoggedOff(boolean show) {
		shouldSignInGroup.setVisibility(!show ? View.GONE : View.VISIBLE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void setFavoritesCount(String count) {

	}
}
