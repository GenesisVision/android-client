package vision.genesis.clientapp.ui;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
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

	private static final float ANIM_SCALE_TO = 1.2f;

	private static final int ANIM_DURATION = 300;

	@BindView(R.id.button_one)
	public View buttonOne;

	@BindView(R.id.button_two)
	public View buttonTwo;

	@BindView(R.id.button_three)
	public View buttonThree;

	@BindView(R.id.button_four)
	public View buttonFour;

	@BindView(R.id.button_five)
	public View buttonFive;

	@BindView(R.id.button_six)
	public View buttonSix;

	@BindView(R.id.button_seven)
	public View buttonSeven;

	@BindView(R.id.button_eight)
	public View buttonEight;

	@BindView(R.id.button_nine)
	public View buttonNine;

	@BindView(R.id.button_zero)
	public View buttonZero;

	@BindView(R.id.button_decimal)
	public View buttonDecimal;

	@BindView(R.id.button_backspace)
	public View buttonBackspace;

	@BindView(R.id.image_backspace)
	public ImageView backspaceImage;

	private InputListener listener;

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

	@OnLongClick(R.id.button_backspace)
	public boolean onLongBackspaceButton() {
		if (listener != null)
			listener.onLongBackspace();
		return false;
	}

	public void setListener(InputListener listener) {
		this.listener = listener;
	}

	private void initView() {
		inflate(getContext(), R.layout.view_numeric_keyboard, this);

		ButterKnife.bind(this);

		setAnimations();
	}

	private void setAnimations() {
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
		buttonBackspace.setOnTouchListener(onTouchListener);
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

		ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
				ContextCompat.getColor(getContext(), R.color.colorFontMedium),
				ContextCompat.getColor(getContext(), R.color.colorPrimary));
		colorAnimation.setDuration(ANIM_DURATION);
		colorAnimation.addUpdateListener(animator -> {
			if (view instanceof TextView)
				((TextView) view).setTextColor((int) animator.getAnimatedValue());
			else
				backspaceImage.setColorFilter((int) animator.getAnimatedValue());
		});

		view.startAnimation(scaleAnimation);
		colorAnimation.start();
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

		ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
				ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.colorFontMedium));
		colorAnimation.setDuration(ANIM_DURATION);
		colorAnimation.addUpdateListener(animator -> {
			if (view instanceof TextView)
				((TextView) view).setTextColor((int) animator.getAnimatedValue());
			else
				backspaceImage.setColorFilter((int) animator.getAnimatedValue());
		});

		view.startAnimation(scaleAnimation);
		colorAnimation.start();
	}

	private void numberClicked(String number) {
		if (listener != null)
			listener.onNumber(number);
	}
}
