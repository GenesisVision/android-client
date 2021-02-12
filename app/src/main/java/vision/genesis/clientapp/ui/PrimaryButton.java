package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.content.res.AppCompatResources;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/03/2018.
 */

public class PrimaryButton extends androidx.appcompat.widget.AppCompatButton
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
		setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.button_primary_white));
		setTextColorByAttrId(R.attr.colorAccent);
	}

	public void setEmpty() {
		setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.button_primary_empty));
	}

	public void setTextColorByAttrId(int colorAttrId) {
		setTextColor(ThemeUtil.getColorByAttrId(getContext(), colorAttrId));
	}

	private void initView() {
		setTransformationMethod(null);
		setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.button_primary));
		setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
		setTypeface(TypefaceUtil.semibold());
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
	}

	public void setGreen() {
		setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.button_primary_green));
	}

	public void setRed() {
		setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.button_primary_red));
	}
}
