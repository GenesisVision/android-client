package vision.genesis.clientapp.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
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

	@BindView(R.id.button_backspace)
	public View buttonBackspace;

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
	}

	private void initView() {
		inflate(getContext(), R.layout.view_pin_keyboard, this);

		unbinder = ButterKnife.bind(this);

		setFonts();
		setBackspaceLongClickListener();
	}

	private void setFonts() {
		buttonOne.setTypeface(TypefaceUtil.light());
		buttonTwo.setTypeface(TypefaceUtil.light());
		buttonThree.setTypeface(TypefaceUtil.light());
		buttonFour.setTypeface(TypefaceUtil.light());
		buttonFive.setTypeface(TypefaceUtil.light());
		buttonSix.setTypeface(TypefaceUtil.light());
		buttonSeven.setTypeface(TypefaceUtil.light());
		buttonEight.setTypeface(TypefaceUtil.light());
		buttonNine.setTypeface(TypefaceUtil.light());
		buttonZero.setTypeface(TypefaceUtil.light());
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
		if (listener != null)
			listener.onNumber(number);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		listener = null;
	}
}