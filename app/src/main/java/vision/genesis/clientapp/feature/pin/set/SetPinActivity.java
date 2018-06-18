package vision.genesis.clientapp.feature.pin.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PinCodeView;
import vision.genesis.clientapp.ui.PinKeyboardView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */

public class SetPinActivity extends MvpAppCompatActivity implements SetPinView
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), SetPinActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.view_pin_code)
	public PinCodeView pinCodeView;

	@BindView(R.id.view_repeat_pin_code)
	public PinCodeView repeatPinCodeView;

	@BindView(R.id.group_repeat_pin)
	public ViewGroup repeatPinGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.keyboard)
	public PinKeyboardView keyboard;

	@InjectPresenter
	SetPinPresenter setPinPresenter;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_set_pin);

		ButterKnife.bind(this);

		initKeyboardListener();
		setFonts();
	}

	@Override
	protected void onDestroy() {
		keyboard.onDestroy();

		super.onDestroy();
	}

	private void initKeyboardListener() {
		keyboard.setListener(new PinKeyboardView.InputListener()
		{
			@Override
			public void onNumber(String number) {
				setPinPresenter.onNumber(number);
			}

			@Override
			public void onBackspace() {
				setPinPresenter.onBackspace();
			}

			@Override
			public void onLongBackspace() {
				setPinPresenter.onLongBackspace();
			}
		});
	}

	private void setFonts() {
	}

	@Override
	public void setPin(int symbolsEntered) {
		pinCodeView.selectDots(symbolsEntered);
	}

	@Override
	public void setPinError(boolean error) {
		pinCodeView.setError(error);
	}

	@Override
	public void setRepeatPin(int symbolsEntered) {
		repeatPinCodeView.selectDots(symbolsEntered);
	}

	@Override
	public void setRepeatPinError(boolean error) {
		repeatPinCodeView.setError(error);
		if (error) {
			Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake_horizontal);
			repeatPinCodeView.startAnimation(animShake);
		}
		else {
			repeatPinCodeView.clearAnimation();
		}
	}

	@Override
	public void showRepeatPin(boolean show) {
		repeatPinGroup.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void setKeyboardKeysEnabled(boolean enabled) {
		keyboard.disableKeyboard(!enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		keyboard.disableKeyboard(show);
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}
}