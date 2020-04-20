package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class AmountTextView extends androidx.appcompat.widget.AppCompatTextView
{
	public interface AmountChangeListener
	{
		void onAmountChanged(double amount);

		void onAmountCleared();
	}

	private AmountChangeListener listener;

	private double amount = 0;

	private String previousAmountText = "";

	private int maxDecimalDigits = 0;

	public AmountTextView(Context context) {
		super(context);
		initView();
	}

	public AmountTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AmountTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void setKeyboard(NumericKeyboardView keyboard) {
		keyboard.setListener(new NumericKeyboardView.InputListener()
		{
			@Override
			public void onNumber(String number) {
				append(number);
			}

			@Override
			public void onDecimal() {
				append(".");
			}

			@Override
			public void onBackspace() {
				String text = getText().toString();
				if (text.length() > 0) {
					setText(text.subSequence(0, text.length() - 1));
				}
			}

			@Override
			public void onLongBackspace() {
				setText("");
			}
		});
	}

	public void setAmountChangeListener(AmountChangeListener listener) {
		this.listener = listener;
		this.listener.onAmountChanged(amount);
		if (getText().toString().isEmpty()) {
			this.listener.onAmountCleared();
		}
	}

	public void setMaxDecimalDigits(int maxDigits) {
		maxDecimalDigits = maxDigits;
	}

	private void initView() {
		setAmountTextListener();
		setFonts();
	}

	private void setFonts() {
		this.setTypeface(TypefaceUtil.light());
	}

	private void setAmountTextListener() {
		RxTextView.textChanges(this)
				.subscribe(charSequence -> {
					onAmountChanged(charSequence.toString());
					previousAmountText = this.getText().toString();
				});
	}

	private void onAmountChanged(String amountText) {
		if (amountText.equals(previousAmountText)) {
			return;
		}

		if (amountText.endsWith(".") && amountText.substring(0, amountText.length() - 1).contains(".")) {
			this.setText(amountText.substring(0, amountText.length() - 1));
			return;
		}

		if (amountText.contains(".")) {
			String[] parts = amountText.split(Pattern.quote("."));
			if (parts.length > 1) {
				String decimalPart = parts[1];
				if (decimalPart != null && decimalPart.length() > maxDecimalDigits) {
					this.setText(amountText.substring(0, amountText.length() - 1));
					return;
				}
			}
		}
		if ((amountText.equals("0") && !previousAmountText.equals("0."))
				|| amountText.equals(".")) {
			this.setText("0.");
			return;
		}
		else if ((amountText.equals("0") && previousAmountText.equals("0."))) {
			this.setText("");
			return;
		}

		try {
			amount = Double.parseDouble(amountText);
		} catch (NumberFormatException e) {
			amount = 0;
		}

		if (listener != null) {
			listener.onAmountChanged(amount);
		}

		if (getText().toString().isEmpty()) {
			if (listener != null) {
				listener.onAmountCleared();
			}
		}
	}

	public void onDestroy() {
		listener = null;
	}
}
