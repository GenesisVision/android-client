package vision.genesis.clientapp.feature.pin.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.PinCodeView;
import vision.genesis.clientapp.ui.PinKeyboardView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/06/2018.
 */

public class SetPinActivity extends BaseSwipeBackActivity implements SetPinView
{
	private static final String EXTRA_ENABLE = "extra_enable";

	public static void startFrom(Activity activity, boolean enable) {
		Intent intent = new Intent(activity.getApplicationContext(), SetPinActivity.class);
		intent.putExtra(EXTRA_ENABLE, enable);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.background)
	public View background;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.view_pin_code)
	public PinCodeView pinCodeView;

	@BindView(R.id.view_repeat_pin_code)
	public PinCodeView repeatPinCodeView;

	@BindView(R.id.group_repeat_pin)
	public ViewGroup repeatPinGroup;

	@BindView(R.id.keyboard)
	public PinKeyboardView keyboard;

	@InjectPresenter
	SetPinPresenter setPinPresenter;

	private boolean firstStart = true;

	private boolean enablePin = true;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_set_pin);

		ButterKnife.bind(this);

		enablePin = getIntent().getExtras().getBoolean(EXTRA_ENABLE, true);
		setPinPresenter.setMode(enablePin);

		initKeyboardListener();
		setFonts();

		setText();
	}

	private void setText() {
		text.setText(getString(enablePin
				? R.string.enter_pin_code_to_enable
				: R.string.enter_pin_code_to_disable));
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (firstStart) {
			startAnimations();
		}
		firstStart = false;
	}

	@Override
	protected void onDestroy() {
		keyboard.onDestroy();

		super.onDestroy();
	}

	private void startAnimations() {
//		Animation keyboardAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_appear);
//		keyboard.startAnimation(keyboardAnimation);
//
//		Animation pinAnimation = AnimationUtils.loadAnimation(this, R.anim.pin_appear);
//		pinGroup.startAnimation(pinAnimation);
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
		title.setTypeface(TypefaceUtil.semibold());
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
	public void setErrorMessage(String message) {
		text.setText(message);
		text.setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorRed));
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
	public void finishAnimations() {
//		Animation keyboardAnimation = AnimationUtils.loadAnimation(this, R.anim.keyboard_disappear);
//		keyboardAnimation.setFillAfter(true);
//		keyboard.startAnimation(keyboardAnimation);
//
//		Animation pinsAnimation = AnimationUtils.loadAnimation(this, R.anim.pin_disappear);
//		pinsAnimation.setFillAfter(true);
//		pinsGroup.startAnimation(pinsAnimation);
//
//		Animation backgroundAnimation = AnimationUtils.loadAnimation(this, R.anim.check_pin_disappear);
//		backgroundAnimation.setFillAfter(true);
//		background.startAnimation(backgroundAnimation);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}