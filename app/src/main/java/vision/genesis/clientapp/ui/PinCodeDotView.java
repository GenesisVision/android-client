package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.content.res.AppCompatResources;
import butterknife.BindView;
import butterknife.ButterKnife;
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
				? AppCompatResources.getDrawable(getContext(), R.drawable.pin_code_dot_selected)
				: AppCompatResources.getDrawable(getContext(), R.drawable.pin_code_dot_unselected));
		dot.setAlpha(selected ? 1f : 0.1f);
	}

	public void setError(boolean error) {
		dot.setAlpha(error ? 1f : 0.1f);
		dot.setImageDrawable(error
				? AppCompatResources.getDrawable(getContext(), R.drawable.pin_code_dot_error)
				: AppCompatResources.getDrawable(getContext(), R.drawable.pin_code_dot_unselected));
	}
}
