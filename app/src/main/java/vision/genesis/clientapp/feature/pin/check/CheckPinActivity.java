package vision.genesis.clientapp.feature.pin.check;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.pin.fingerprint.VerifyFingerprintActivity;
import vision.genesis.clientapp.ui.PinCodeView;
import vision.genesis.clientapp.ui.PinKeyboardView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/06/2018.
 */

public class CheckPinActivity extends MvpAppCompatActivity implements CheckPinView
{
	public static final int LOCK_SCREEN_REQUEST_CODE = 1234;

	private static final String EXTRA_FINGERPRINT_ENABLED = "extra_fingerprint_enabled";

	public static void startForResult(Activity activity, int requestCode, boolean allowFingerprint) {
		Intent intent = new Intent(activity.getApplicationContext(), CheckPinActivity.class);
		intent.putExtra(EXTRA_FINGERPRINT_ENABLED, allowFingerprint);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@BindView(R.id.view_pin_code)
	public PinCodeView pinCodeView;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.timer)
	public TextView timer;

	@BindView(R.id.keyboard)
	public PinKeyboardView keyboard;

	@BindView(R.id.group_pin)
	public ViewGroup pinGroup;

	@BindView(R.id.background)
	public View background;

	@InjectPresenter
	CheckPinPresenter checkPinPresenter;

	private boolean firstStart = true;

	private boolean fingerprintEnabled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_check_pin);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			fingerprintEnabled = getIntent().getExtras().getBoolean(EXTRA_FINGERPRINT_ENABLED);
			initStartText();
		}

		initKeyboardListener();
	}

	@Override
	protected void onStart() {
		super.onStart();
		checkPinPresenter.onStart();
		if (firstStart) {
			startAnimations();
			firstStart = false;
		}
	}

	private void startAnimations() {
		Animation keyboardAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_appear);
		keyboard.startAnimation(keyboardAnimation);

		Animation pinAnimation = AnimationUtils.loadAnimation(this, R.anim.pin_appear);
		pinGroup.startAnimation(pinAnimation);
	}

	@Override
	protected void onStop() {
		super.onStop();

		checkPinPresenter.onStop();
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
				checkPinPresenter.onNumber(number);
			}

			@Override
			public void onBackspace() {
				checkPinPresenter.onBackspace();
			}

			@Override
			public void onLongBackspace() {
				checkPinPresenter.onLongBackspace();
			}
		});
	}

	@Override
	public void initStartText() {
		text.setText(getString(fingerprintEnabled
				? R.string.enter_pin_code_or_use_fingerprint
				: R.string.enter_pin_code));
		text.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary));
		keyboard.showFingerprint(fingerprintEnabled);
		checkPinPresenter.setFingerprintEnabled(fingerprintEnabled);
	}

	@Override
	public void setPin(int symbolsEntered) {
		pinCodeView.selectDots(symbolsEntered);
	}

	@Override
	public void setPinError(boolean error) {
		pinCodeView.setError(error);
		if (error) {
			Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake_horizontal);
			pinCodeView.startAnimation(animShake);
		}
		else {
			pinCodeView.clearAnimation();
		}
	}

	@Override
	public void setKeyboardKeysEnabled(boolean enabled) {
		keyboard.disableKeyboard(!enabled);
	}

	@Override
	public void setErrorMessage(String message) {
		text.setText(message);
		text.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorRed));
	}

	@Override
	public void showTimer(boolean show) {
		this.timer.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setTimer(String timerString) {
		this.timer.setText(timerString);
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showVerifyFingerprintActivity() {
		VerifyFingerprintActivity.startWith(this, VerifyFingerprintActivity.ENABLE_FINGERPRINT_REQUEST_CODE);
	}

	@Override
	public void shakeFingerprint() {
		keyboard.shakeFingerprint();
	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}

	@Override
	public void finishAnimations() {
		Animation keyboardAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_disappear);
		keyboardAnimation.setFillAfter(true);
		keyboard.startAnimation(keyboardAnimation);

		Animation pinAnimation = AnimationUtils.loadAnimation(this, R.anim.pin_disappear);
		pinAnimation.setFillAfter(true);
		pinGroup.startAnimation(pinAnimation);

		Animation backgroundAnimation = AnimationUtils.loadAnimation(this, R.anim.check_pin_disappear);
		backgroundAnimation.setFillAfter(true);
		background.startAnimation(backgroundAnimation);
	}

	@Override
	public void finishActivity(int resultCode) {
		setResult(resultCode);
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}

	@Override
	public void disableFingerprint(boolean changeText) {
		keyboard.showFingerprintError();
		if (changeText) {
			text.setText(getString(R.string.enter_pin_code));
		}
	}
}