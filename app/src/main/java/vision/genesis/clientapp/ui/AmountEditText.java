package vision.genesis.clientapp.ui;

import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.regex.Pattern;

import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class AmountEditText extends android.support.v7.widget.AppCompatEditText
{
	public interface AmountChangeListener
	{
		void onAmountChanged(double amount);
	}

	private static final int GVT_MAX_FRACTION = StringFormatUtil.getCurrencyMaxFraction(CurrencyEnum.GVT.toString());

	private AmountChangeListener listener;

	private double amount = 0;

	private String previousAmountText = "";

	public AmountEditText(Context context) {
		super(context);
		initView();
	}

	public AmountEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AmountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void setListener(AmountChangeListener listener) {
		this.listener = listener;
		this.listener.onAmountChanged(amount);
	}

	private void initView() {
		this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
		}

		setAmountTextListener();
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
		if (amountText.contains(".")) {
			String[] parts = amountText.split(Pattern.quote("."));
			if (parts.length > 1) {
				String decimalPart = parts[1];
				if (decimalPart != null && decimalPart.length() > GVT_MAX_FRACTION) {
					this.setText(amountText.substring(0, amountText.length() - 1));
					this.setSelection(this.getText().length());
					return;
				}
			}
		}
		if ((amountText.equals("0") && !previousAmountText.equals("0."))
				|| amountText.equals(".")) {
			this.setText("0.");
			this.setSelection(2);
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

		if (listener != null)
			listener.onAmountChanged(amount);
	}
}
