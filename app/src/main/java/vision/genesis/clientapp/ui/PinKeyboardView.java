package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class PinKeyboardView extends RelativeLayout
{
	public interface InputListener
	{
		void onNumber(String number);

		void onBackspace();

		void onLongBackspace();
	}

	private static final int LONG_CLICK_DURATION = 400;

	@BindView(R.id.button_one)
	public Button buttonOne;

	@BindView(R.id.button_two)
	public Button buttonTwo;

	@BindView(R.id.button_three)
	public Button buttonThree;

	@BindView(R.id.button_four)
	public Button buttonFour;

	@BindView(R.id.button_five)
	public Button buttonFive;

	@BindView(R.id.button_six)
	public Button buttonSix;

	@BindView(R.id.button_seven)
	public Button buttonSeven;

	@BindView(R.id.button_eight)
	public Button buttonEight;

	@BindView(R.id.button_nine)
	public Button buttonNine;

	@BindView(R.id.button_zero)
	public Button buttonZero;

	@BindView(R.id.text_one)
	public TextView textOne;

	@BindView(R.id.text_two)
	public TextView textTwo;

	@BindView(R.id.text_three)
	public TextView textThree;

	@BindView(R.id.text_four)
	public TextView textFour;

	@BindView(R.id.text_five)
	public TextView textFive;

	@BindView(R.id.text_six)
	public TextView textSix;

	@BindView(R.id.text_seven)
	public TextView textSeven;

	@BindView(R.id.text_eight)
	public TextView textEight;

	@BindView(R.id.text_nine)
	public TextView textNine;

	@BindView(R.id.text_zero)
	public TextView textZero;

	@BindView(R.id.button_backspace)
	public View buttonBackspace;

	@BindView(R.id.image_fingerprint)
	public ImageView fingerprint;

	@BindView(R.id.image_backspace)
	public ImageView backspaceImage;

	private Unbinder unbinder;

	private InputListener listener;

	private Handler longClickHandler = new Handler();

	private Runnable longClickRunnable = this::onLongBackspaceButton;

	public PinKeyboardView(Context context) {
		super(context);
		initView();
	}

	public PinKeyboardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PinKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_one)
	public void onButtonOne() {
		numberClicked("1");
	}

	@OnClick(R.id.button_two)
	public void onButtonTwo() {
		numberClicked("2");
	}

	@OnClick(R.id.button_three)
	public void onButtonThree() {
		numberClicked("3");
	}

	@OnClick(R.id.button_four)
	public void onButtonFour() {
		numberClicked("4");
	}

	@OnClick(R.id.button_five)
	public void onButtonFive() {
		numberClicked("5");
	}

	@OnClick(R.id.button_six)
	public void onButtonSix() {
		numberClicked("6");
	}

	@OnClick(R.id.button_seven)
	public void onButtonSeven() {
		numberClicked("7");
	}

	@OnClick(R.id.button_eight)
	public void onButtonEight() {
		numberClicked("8");
	}

	@OnClick(R.id.button_nine)
	public void onButtonNine() {
		numberClicked("9");
	}

	@OnClick(R.id.button_zero)
	public void onButtonZero() {
		numberClicked("0");
	}

	@OnClick(R.id.button_backspace)
	public void onBackspaceButton() {
		if (listener != null) {
			listener.onBackspace();
		}
	}

	private boolean onLongBackspaceButton() {
		if (listener != null) {
			listener.onLongBackspace();
		}
		return false;
	}

	public void setListener(InputListener listener) {
		this.listener = listener;
	}

	public void disableKeyboard(boolean disable) {
		buttonOne.setEnabled(!disable);
		buttonTwo.setEnabled(!disable);
		buttonThree.setEnabled(!disable);
		buttonFour.setEnabled(!disable);
		buttonFive.setEnabled(!disable);
		buttonSix.setEnabled(!disable);
		buttonSeven.setEnabled(!disable);
		buttonEight.setEnabled(!disable);
		buttonNine.setEnabled(!disable);
		buttonZero.setEnabled(!disable);
		buttonBackspace.setEnabled(!disable);

		int enabledColor = ContextCompat.getColor(getContext(), R.color.white);
		int disabledColor = ContextCompat.getColor(getContext(), R.color.white38);

		textOne.setAlpha(disable ? 0.5f : 1f);
		textTwo.setAlpha(disable ? 0.5f : 1f);
		textThree.setAlpha(disable ? 0.5f : 1f);
		textFour.setAlpha(disable ? 0.5f : 1f);
		textFive.setAlpha(disable ? 0.5f : 1f);
		textSix.setAlpha(disable ? 0.5f : 1f);
		textSeven.setAlpha(disable ? 0.5f : 1f);
		textEight.setAlpha(disable ? 0.5f : 1f);
		textNine.setAlpha(disable ? 0.5f : 1f);
		textZero.setAlpha(disable ? 0.5f : 1f);
		backspaceImage.setColorFilter(disable ? disabledColor : enabledColor, PorterDuff.Mode.SRC_IN);
		backspaceImage.setColorFilter(disable ? disabledColor : enabledColor, PorterDuff.Mode.SRC_IN);
	}

	public void showFingerprint(boolean show) {
		fingerprint.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void shakeFingerprint() {
		Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_horizontal);
		fingerprint.startAnimation(animShake);
	}

	public void showFingerprintError() {
		fingerprint.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
	}

	private void initView() {
		inflate(getContext(), R.layout.view_pin_keyboard, this);

		unbinder = ButterKnife.bind(this);

		setFonts();
		setBackspaceLongClickListener();
	}

	private void setFonts() {
		textOne.setTypeface(TypefaceUtil.light());
		textTwo.setTypeface(TypefaceUtil.light());
		textThree.setTypeface(TypefaceUtil.light());
		textFour.setTypeface(TypefaceUtil.light());
		textFive.setTypeface(TypefaceUtil.light());
		textSix.setTypeface(TypefaceUtil.light());
		textSeven.setTypeface(TypefaceUtil.light());
		textEight.setTypeface(TypefaceUtil.light());
		textNine.setTypeface(TypefaceUtil.light());
		textZero.setTypeface(TypefaceUtil.light());
	}

	private void setBackspaceLongClickListener() {
		OnTouchListener onBackspaceTouchListener = (view, event) -> {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				longClickHandler.postDelayed(longClickRunnable, LONG_CLICK_DURATION);
				return true;
			}
			else if (event.getAction() == MotionEvent.ACTION_UP) {
				longClickHandler.removeCallbacks(longClickRunnable);
				view.performClick();
				return true;
			}
			return false;
		};

		buttonBackspace.setOnTouchListener(onBackspaceTouchListener);
	}

	private void numberClicked(String number) {
		if (listener != null) {
			listener.onNumber(number);
		}
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		listener = null;
	}
}