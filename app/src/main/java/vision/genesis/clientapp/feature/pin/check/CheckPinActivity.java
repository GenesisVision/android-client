package vision.genesis.clientapp.feature.pin.check;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
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
 * Created by Vitaly on 15/06/2018.
 */

public class CheckPinActivity extends MvpAppCompatActivity implements CheckPinView
{
	private static final String EXTRA_CAN_CLOSE = "extra_can_close";

	public static void startForResult(Fragment fragment, int requestCode, boolean canClose) {
		Intent intent = new Intent(fragment.getContext(), CheckPinActivity.class);
		intent.putExtra(EXTRA_CAN_CLOSE, canClose);
		fragment.startActivityForResult(intent, requestCode);
		if (fragment.getActivity() != null)
			fragment.getActivity().overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	public static void startForResult(Activity activity, int requestCode, boolean canClose) {
		Intent intent = new Intent(activity.getApplicationContext(), CheckPinActivity.class);
		intent.putExtra(EXTRA_CAN_CLOSE, canClose);
		activity.startActivityForResult(intent, requestCode);
		activity.overridePendingTransition(R.anim.hold, R.anim.hold);
	}

	@BindView(R.id.view_pin_code)
	public PinCodeView pinCodeView;

	@BindView(R.id.text_error_message)
	public TextView errorMessageText;

	@BindView(R.id.keyboard)
	public PinKeyboardView keyboard;

	@BindView(R.id.button_close)
	public View closeButton;

	@InjectPresenter
	CheckPinPresenter checkPinPresenter;

	private boolean canClose = false;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity(RESULT_CANCELED);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_check_pin);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			canClose = getIntent().getExtras().getBoolean(EXTRA_CAN_CLOSE);
			closeButton.setVisibility(canClose ? View.VISIBLE : View.GONE);
		}

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
	public void onBackPressed() {
		if (canClose)
			finishActivity(RESULT_CANCELED);
	}

	@Override
	public void finishActivity(int resultCode) {
		setResult(resultCode);
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}
}