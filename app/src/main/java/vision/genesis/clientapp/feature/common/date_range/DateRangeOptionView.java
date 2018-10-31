package vision.genesis.clientapp.feature.common.date_range;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2018.
 */
public class DateRangeOptionView extends RelativeLayout
{
	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.background)
	public ViewGroup background;

	public DateRangeOptionView(Context context) {
		super(context);
		initView();
	}

	public DateRangeOptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DateRangeOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_date_range_option, this);

		ButterKnife.bind(this);
	}

	public String getText() {
		return name.getText().toString();
	}

	public void setText(String text) {
		name.setText(text);
	}

	public void setSelected(boolean selected) {
		name.setTextColor(selected
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));

		background.setBackground(AppCompatResources.getDrawable(getContext(),
				selected ? R.drawable.date_range_option_selected : R.drawable.date_range_option));
	}
}
