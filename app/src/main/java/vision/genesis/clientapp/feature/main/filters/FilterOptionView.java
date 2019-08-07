package vision.genesis.clientapp.feature.main.filters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.filter.FilterOption;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/08/2019.
 */
public class FilterOptionView extends RelativeLayout
{
	interface ClickListener
	{
		void onClick(FilterOption filter);
	}

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.value)
	public TextView value;

	private FilterOption filter;

	private ClickListener listener;

	public FilterOptionView(Context context) {
		super(context);
		initView();
	}

	public FilterOptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FilterOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_filter_option, this);

		ButterKnife.bind(this);

		setOnClickListener(view -> {
			if (listener != null)
				listener.onClick(filter);
		});
	}

	private void setName(String name) {
		this.name.setText(name);
	}

	private void setValue(String value) {
		this.value.setText(value);
		this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				filter.getSelectedPosition() == 0
						? R.attr.colorTextSecondary
						: R.attr.colorAccent));
	}

	public void setFilterOption(FilterOption filterOption) {
		this.filter = filterOption;
		setName(filter.getName());
		setValue(filter.getSelectedValue());
	}

	private void setRangeFilterValue(TextView filterValueView, Integer minValue, Integer maxValue) {
		if (minValue == null && maxValue == null) {
			filterValueView.setAlpha(0.4f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));

			filterValueView.setText(getContext().getString(R.string.all));
		}
		else {
			filterValueView.setAlpha(1f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));

//			if (minValue == null)
//				minValue = defaultLevelMinLevel;
//			if (maxValue == null)
//				maxValue = defaultLevelMaxLevel;

			if (maxValue - minValue == 1)
				filterValueView.setText(String.format(Locale.getDefault(), "%d, %d", minValue, maxValue));
			else if (minValue.equals(maxValue))
				filterValueView.setText(String.format(Locale.getDefault(), "%d", minValue));
			else
				filterValueView.setText(String.format(Locale.getDefault(), "%d-%d", minValue, maxValue));
		}
	}

	private void setSingleFilterValue(TextView filterValueView, String value) {
		if (value == null) {
			filterValueView.setAlpha(0.4f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));

			filterValueView.setText(getContext().getString(R.string.all));
		}
		else {
			filterValueView.setAlpha(1f);
			filterValueView.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));

			filterValueView.setText(value);
		}
	}

	public void setClickListener(ClickListener listener) {
		this.listener = listener;
	}
}
