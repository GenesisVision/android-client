package vision.genesis.clientapp.feature.main.filters_sorting;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/05/2018.
 */
public class FilterSwitchView extends RelativeLayout
{
	interface OnCheckChangeListener
	{
		void onChecked(boolean checked);
	}

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.switch_box)
	public SwitchCompat switchBox;

	private OnCheckChangeListener listener;

	private boolean checked = false;

	public FilterSwitchView(Context context) {
		super(context);
		initView();
	}

	public FilterSwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FilterSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_filter_switch, this);

		ButterKnife.bind(this);

		this.setOnClickListener(v -> setChecked(!checked));
	}

	public String getName() {
		return name.getText().toString();
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
		this.switchBox.setChecked(checked);
		if (listener != null)
			listener.onChecked(checked);
	}

	public void setListener(OnCheckChangeListener listener) {
		this.listener = listener;
		listener.onChecked(checked);
	}
}
