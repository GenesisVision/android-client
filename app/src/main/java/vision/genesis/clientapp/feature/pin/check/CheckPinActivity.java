package vision.genesis.clientapp.feature.pin.check;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.pin.fingerprint.VerifyFingerprintActivity;
import vision.genesis.clientapp.ui.PinCodeView;
import vision.genesis.clientapp.ui.PinKeyboardView;
import vision.genesis.clientapp.utils.StatusBarUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/06/2018.
 */

public class CheckPinActivity extends MvpAppCompatActivity implements CheckPinView
{
	public static final int LOCK_SCREEN_REQUEST_CODE = 1234;

	private static final String EXTRA_CAN_CLOSE = "extra_can_close";

	private static final String EXTRA_FINGERPRINT_ENABLED = "extra_fingerprint_enabled";

	public static void startForResult(Fragment fragment, int requestCode, boolean canClose) {
		Intent intent = new Intent(fragment.getContext(), CheckPinActivity.class);
		intent.putExtra(EXTRA_CAN_CLOSE, canClose);
		fragment.startActivityForResult(intent, requestCode);
		if (fragment.getActivity() != null)
			fragment.getActivity().overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	public static void startForResult(Activity activity, int requestCode, boolean canClose, boolean allowFingerprint) {
		Intent intent = new Intent(activity.getApplicationContext(), CheckPinActivity.class);
		intent.putExtra(EXTRA_CAN_CLOSE, canClose);
		intent.putExtra(EXTRA_FINGERPRINT_ENABLED, allowFingerprint);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@BindView(R.id.view_pin_code)
	public PinCodeView pinCodeView;

	@BindView(R.id.text_error_message)
	public TextView errorMessageText;

	@BindView(R.id.keyboard)
	public PinKeyboardView keyboard;

	@BindView(R.id.button_close)
	public View closeButton;

	@BindView(R.id.group_pin)
	public ViewGroup pinGroup;

	@BindView(R.id.group_fingerprint)
	public ViewGroup fingerprintGroup;

	@BindView(R.id.background)
	public View background;

	@BindView(R.id.image_fingerprint)
	public ImageView fingerprintImage;

	@InjectPresenter
	CheckPinPresenter checkPinPresenter;

	private boolean canClose = false;

	private boolean firstStart = true;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity(RESULT_CANCELED);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_check_pin);

		ButterKnife.bind(this);

		StatusBarUtil.setColorResId(this, R.color.colorAccent);

		if (getIntent().getExtras() != null) {
			canClose = getIntent().getExtras().getBoolean(EXTRA_CAN_CLOSE);
			boolean fingerprintEnabled = getIntent().getExtras().getBoolean(EXTRA_FINGERPRINT_ENABLED);
			closeButton.setVisibility(canClose ? View.VISIBLE : View.GONE);
			fingerprintGroup.setVisibility(fingerprintEnabled ? View.VISIBLE : View.GONE);
			checkPinPresenter.setFingerprintEnabled(fingerprintEnabled);
		}

		initKeyboardListener();
		setFonts();
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

		Animation fingerprintAnimation = AnimationUtils.loadAnimation(this, R.anim.fingerprint_appear);
		fingerprintGroup.startAnimation(fingerprintAnimation);
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

	private void setFonts() {
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
		errorMessageText.setText(message);
	}

	@Override
	public void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showVerifyFingerprintActivity() {
		VerifyFingerprintActivity.startWith(this, VerifyFingerprintActivity.ENABLE_FINGERPRINT_REQUEST_CODE);
	}

	@Override
	public void shakeFingerprint() {
		Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake_horizontal);
		fingerprintGroup.startAnimation(animShake);
	}

	@Override
	public void onBackPressed() {
		if (canClose)
			finishActivity(RESULT_CANCELED);
		else
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

		Animation fingerprintAnimation = AnimationUtils.loadAnimation(this, R.anim.fingerprint_disappear);
		fingerprintAnimation.setFillAfter(true);
		fingerprintGroup.startAnimation(fingerprintAnimation);

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
	public void disableFingerprint(String message) {
		fingerprintImage.setColorFilter(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
		errorMessageText.setText(message);
	}
}