package vision.genesis.clientapp.feature.splashscreen;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.MainActivity;
import vision.genesis.clientapp.feature.tournament.TournamentActivity;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class SplashScreenActivity extends MvpAppCompatActivity implements SplashScreenView
{
	@InjectPresenter
	SplashScreenPresenter splashScreenPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
	}

	@Override
	public void showMainActivity() {
		MainActivity.startFrom(this);
		finish();
	}

	@Override
	public void showTournamentActivity() {
		TournamentActivity.startFrom(this);
		finish();
	}
}