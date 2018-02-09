package vision.genesis.clientapp.feature.tournament;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.swagger.client.model.ParticipantViewModel;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.Screens;
import vision.genesis.clientapp.feature.tournament.leaderboard.LeaderboardFragment;
import vision.genesis.clientapp.feature.tournament.participants.ParticipantsFragment;
import vision.genesis.clientapp.feature.tournament.participants.details.ParticipantDetailsFragment;
import vision.genesis.clientapp.ui.common.BackButtonListener;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

public class TournamentActivity extends MvpAppCompatActivity implements TournamentView

{
	public static void startFrom(Context context) {
		Intent tournamentActivityIntent = new Intent(context, TournamentActivity.class);
		tournamentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tournamentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(tournamentActivityIntent);
	}

	@Inject
	public NavigatorHolder navigatorHolder;

	@Inject
	Router router;

	@InjectPresenter
	TournamentPresenter tournamentPresenter;

	private ParticipantsFragment participantsFragment;

	private ParticipantDetailsFragment participantDetailsFragment;

	private LeaderboardFragment leaderboardFragment;

	private Navigator navigator = new SupportAppNavigator(this, R.id.content)
	{
		@Override
		protected Intent createActivityIntent(String screenKey, Object data) {
			return null;
		}

		@Override
		protected Fragment createFragment(String screenKey, Object data) {
			switch (screenKey) {
				case Screens.TOUR_PARTICIPANTS:
					if (participantsFragment == null)
						participantsFragment = new ParticipantsFragment();
					return participantsFragment;
				case Screens.TOUR_PARTICIPANT_DETAILS:
					participantDetailsFragment = ParticipantDetailsFragment.with(((ParticipantViewModel) data).getId());
					return participantDetailsFragment;
				case Screens.TOUR_LEADERBOARD:
					if (leaderboardFragment == null)
						leaderboardFragment = new LeaderboardFragment();
					return leaderboardFragment;
			}
			return null;
		}

		@Override
		protected void setupFragmentTransactionAnimation(
				Command command,
				Fragment currentFragment,
				Fragment nextFragment,
				FragmentTransaction fragmentTransaction) {
			fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tournament);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);

		if (savedInstanceState == null) {
			navigator.applyCommand(new Replace(Screens.TOUR_PARTICIPANTS, 1));
		}
	}

	@Override
	public void onResumeFragments() {
		super.onResumeFragments();
		navigatorHolder.setNavigator(navigator);
	}

	@Override
	public void onPause() {
		navigatorHolder.removeNavigator();
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
		if (fragment != null
				&& fragment instanceof BackButtonListener
				&& ((BackButtonListener) fragment).onBackPressed()) {
			return;
		}
		else {
			super.onBackPressed();
		}
	}
}