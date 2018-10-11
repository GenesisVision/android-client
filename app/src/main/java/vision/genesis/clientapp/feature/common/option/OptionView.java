package vision.genesis.clientapp.feature.common.option;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/10/2018.
 */
public class OptionView extends RelativeLayout
{
	@BindView(R.id.option)
	public TextView option;

	@BindView(R.id.check)
	public ImageView check;

	public OptionView(Context context) {
		super(context);
		initView();
	}

	public OptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public OptionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_option, this);

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
//		option.setTypeface(TypefaceUtil.semibold());
	}

	public void setData(String option) {
		this.option.setText(option);
	}

	public void setSelected(boolean selected) {
		check.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
		option.setTextColor(selected
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
	}
}
