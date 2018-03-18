package vision.genesis.clientapp.feature.tournament.leaderboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/9/18.
 */

public class LeaderboardFragment extends BaseFragment implements LeaderboardView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_cannot_load)
	public ViewGroup cannotLoadGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	LeaderboardPresenter leaderboardPresenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_leaderboard, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.leaderboard));
		toolbar.addLeftButton(R.drawable.back_arrow, () -> leaderboardPresenter.onBackClicked());
	}

	@Override
	public void showLoading(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showCannotLoad(boolean show) {
		cannotLoadGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public boolean onBackPressed() {
		leaderboardPresenter.onBackClicked();
		return true;
	}
}