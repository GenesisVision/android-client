package vision.genesis.clientapp.feature.splashscreen;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.MainActivity;
import vision.genesis.clientapp.feature.pin.check.CheckPinActivity;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class SplashScreenActivity extends MvpAppCompatActivity implements SplashScreenView
{
	@BindView(R.id.group_network_error)
	public ViewGroup networkErrorGroup;

	@BindView(R.id.group_server_error)
	public ViewGroup serverErrorGroup;

	@BindView(R.id.progress_bar_network)
	public ProgressBar networkProgressBar;

	@BindView(R.id.progress_bar_server)
	public ProgressBar serverProgressBar;

	@BindView(R.id.button_try_again_network)
	public View tryAgainNetworkButton;

	@BindView(R.id.button_try_again_server)
	public View tryAgainServerButton;

	@InjectPresenter
	SplashScreenPresenter splashScreenPresenter;

	@OnClick(R.id.button_try_again_network)
	public void onTryAgainNetworkClicked() {
		splashScreenPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_try_again_server)
	public void onTryAgainServerClicked() {
		splashScreenPresenter.onTryAgainClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

		ButterKnife.bind(this);
	}

	@Override
	public void showLockScreen() {
		CheckPinActivity.startForResult(this, CheckPinActivity.LOCK_SCREEN_REQUEST_CODE, false);
	}

	@Override
	public void showMainActivity() {
		MainActivity.startFrom(this);
//		finish();
	}

	@Override
	public void showNetworkError() {
		networkErrorGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void showServerError() {

	}

	@Override
	public void showProgress(boolean show) {
		networkProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		serverProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainNetworkButton.setVisibility(!show ? View.VISIBLE : View.GONE);
		tryAgainServerButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}
}