package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/03/2018.
 */

public class PrimaryButton extends android.support.v7.widget.AppCompatButton
{
	public PrimaryButton(Context context) {
		super(context);
		initView();
	}

	public PrimaryButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PrimaryButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void setWhite() {
		setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_primary_white));
		setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
	}

	public void setEmpty() {
		setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_primary_empty));
	}

	private void initView() {
		setTransformationMethod(null);
		setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_primary));
		setTextColor(ContextCompat.getColor(getContext(), R.color.white));
		setTypeface(TypefaceUtil.semibold());
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	}
}
