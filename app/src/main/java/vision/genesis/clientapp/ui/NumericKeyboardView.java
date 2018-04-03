package vision.genesis.clientapp.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class NumericKeyboardView extends RelativeLayout
{
	interface InputListener
	{
		void onNumber(String number);

		void onDecimal();

		void onBackspace();

		void onLongBackspace();
	}

	private static final float ANIM_SCALE_FROM = 1f;

	private static final float ANIM_SCALE_TO = 0.95f;

	private static final int ANIM_DURATION = 300;

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

	@BindView(R.id.button_decimal)
	public Button buttonDecimal;

	@BindView(R.id.button_backspace)
	public View buttonBackspace;

	@BindView(R.id.image_backspace)
	public ImageView backspaceImage;

	private Unbinder unbinder;

	private InputListener listener;

	private Handler longClickHandler = new Handler();

	private Runnable longClickRunnable = this::onLongBackspaceButton;

	public NumericKeyboardView(Context context) {
		super(context);
		initView();
	}

	public NumericKeyboardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public NumericKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
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

	@OnClick(R.id.button_decimal)
	public void onButtonDecimal() {
		if (listener != null)
			listener.onDecimal();
	}

	@OnClick(R.id.button_backspace)
	public void onBackspaceButton() {
		if (listener != null)
			listener.onBackspace();
	}

	private boolean onLongBackspaceButton() {
		if (listener != null)
			listener.onLongBackspace();
		return false;
	}

	public void setListener(InputListener listener) {
		this.listener = listener;
	}

	public void disableAllKeysExceptBackspace(boolean disable) {
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
		buttonDecimal.setEnabled(!disable);

		int enabledColor = ContextCompat.getColor(getContext(), R.color.colorFontDark);
		int disabledColor = ContextCompat.getColor(getContext(), R.color.colorFontLight);

		buttonOne.setTextColor(disable ? disabledColor : enabledColor);
		buttonTwo.setTextColor(disable ? disabledColor : enabledColor);
		buttonThree.setTextColor(disable ? disabledColor : enabledColor);
		buttonFour.setTextColor(disable ? disabledColor : enabledColor);
		buttonFive.setTextColor(disable ? disabledColor : enabledColor);
		buttonSix.setTextColor(disable ? disabledColor : enabledColor);
		buttonSeven.setTextColor(disable ? disabledColor : enabledColor);
		buttonEight.setTextColor(disable ? disabledColor : enabledColor);
		buttonNine.setTextColor(disable ? disabledColor : enabledColor);
		buttonZero.setTextColor(disable ? disabledColor : enabledColor);
		buttonDecimal.setTextColor(disable ? disabledColor : enabledColor);
	}

	private void initView() {
		inflate(getContext(), R.layout.view_numeric_keyboard, this);

		unbinder = ButterKnife.bind(this);

		setNumbersAnimations();
		setBackspaceAnimations();
	}

	private void setNumbersAnimations() {
		OnTouchListener onTouchListener = (view, event) -> {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				playDownAnimation(view);
				return true;
			}
			else if (event.getAction() == MotionEvent.ACTION_UP) {
				playUpAnimation(view);
				view.performClick();
				return true;
			}
			return false;
		};

		buttonOne.setOnTouchListener(onTouchListener);
		buttonTwo.setOnTouchListener(onTouchListener);
		buttonThree.setOnTouchListener(onTouchListener);
		buttonFour.setOnTouchListener(onTouchListener);
		buttonFive.setOnTouchListener(onTouchListener);
		buttonSix.setOnTouchListener(onTouchListener);
		buttonSeven.setOnTouchListener(onTouchListener);
		buttonEight.setOnTouchListener(onTouchListener);
		buttonNine.setOnTouchListener(onTouchListener);
		buttonZero.setOnTouchListener(onTouchListener);
		buttonDecimal.setOnTouchListener(onTouchListener);
	}

	private void setBackspaceAnimations() {
		OnTouchListener onBackspaceTouchListener = (view, event) -> {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				longClickHandler.postDelayed(longClickRunnable, LONG_CLICK_DURATION);
				playDownAnimation(view);
				return true;
			}
			else if (event.getAction() == MotionEvent.ACTION_UP) {
				longClickHandler.removeCallbacks(longClickRunnable);
				playUpAnimation(view);
				view.performClick();
				return true;
			}
			return false;
		};

		buttonBackspace.setOnTouchListener(onBackspaceTouchListener);
	}

	private void playDownAnimation(View view) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(
				ANIM_SCALE_FROM, ANIM_SCALE_TO,
				ANIM_SCALE_FROM, ANIM_SCALE_TO,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(ANIM_DURATION);
		scaleAnimation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				view.setScaleX(ANIM_SCALE_FROM);
				view.setScaleY(ANIM_SCALE_FROM);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				view.setScaleX(ANIM_SCALE_TO);
				view.setScaleY(ANIM_SCALE_TO);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});

//		ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
//				ContextCompat.getColor(getContext(), R.color.colorFontDark),
//				ContextCompat.getColor(getContext(), R.color.colorPrimary));
//		colorAnimation.setDuration(ANIM_DURATION);
//		colorAnimation.addUpdateListener(animator -> {
//			if (view instanceof TextView)
//				((TextView) view).setTextColor((int) animator.getAnimatedValue());
//			else
//				backspaceImage.setColorFilter((int) animator.getAnimatedValue());
//		});

		view.startAnimation(scaleAnimation);
//		colorAnimation.start();
	}

	private void playUpAnimation(View view) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(
				ANIM_SCALE_TO, ANIM_SCALE_FROM,
				ANIM_SCALE_TO, ANIM_SCALE_FROM,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(ANIM_DURATION);
		scaleAnimation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				view.setScaleX(ANIM_SCALE_TO);
				view.setScaleY(ANIM_SCALE_TO);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				view.setScaleX(ANIM_SCALE_FROM);
				view.setScaleY(ANIM_SCALE_FROM);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});

//		ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
//				ContextCompat.getColor(getContext(), R.color.colorPrimary),
//				ContextCompat.getColor(getContext(), R.color.colorFontDark));
//		colorAnimation.setDuration(ANIM_DURATION);
//		colorAnimation.addUpdateListener(animator -> {
//			if (view instanceof TextView)
//				((TextView) view).setTextColor((int) animator.getAnimatedValue());
//			else
//				backspaceImage.setColorFilter((int) animator.getAnimatedValue());
//		});

		view.startAnimation(scaleAnimation);
//		colorAnimation.start();
	}

	private void numberClicked(String number) {
		if (listener != null)
			listener.onNumber(number);
	}

	public void onDestroy() {
		if (unbinder != null)
			unbinder.unbind();

		listener = null;
	}
}