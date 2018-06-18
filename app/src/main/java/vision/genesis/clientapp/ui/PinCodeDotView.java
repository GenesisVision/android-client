package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/06/2018.
 */

public class PinCodeDotView extends RelativeLayout
{
	@BindView(R.id.dot)
	public ImageView dot;

	public PinCodeDotView(Context context) {
		super(context);
		initView();
	}

	public PinCodeDotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PinCodeDotView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_pin_code_dot, this);

		ButterKnife.bind(this);
	}

	public void setSelected(boolean selected) {
		dot.setImageDrawable(selected
				? ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.pin_code_dot_selected)
				: ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.pin_code_dot_unselected));
	}

	public void setError(boolean error) {
		dot.setImageDrawable(error
				? ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.pin_code_dot_error)
				: ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.pin_code_dot_unselected));
	}
}
