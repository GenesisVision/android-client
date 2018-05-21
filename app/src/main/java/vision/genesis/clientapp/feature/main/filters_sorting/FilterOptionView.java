package vision.genesis.clientapp.feature.main.filters_sorting;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/05/2018.
 */
public class FilterOptionView extends RelativeLayout
{
	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.value)
	public TextView value;

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
	}

	public String getName() {
		return name.getText().toString();
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setValue(String value) {
		this.value.setText(value);
	}
}
