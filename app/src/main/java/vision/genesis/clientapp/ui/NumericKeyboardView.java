package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

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
	}

	private void numberClicked(String number) {
		if (listener != null)
			listener.onNumber(number);
	}
}
