package vision.genesis.clientapp.ui;

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
 * Created by Vitaly on 14/06/2018.
 */

public class SettingsSwitchButton extends RelativeLayout
{
	public interface OnCheckChangeListener
	{
		void onChecked(boolean checked);
	}

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.switch_view)
	public SwitchCompat switchView;

	private OnCheckChangeListener listener;

	private boolean checked = false;

	public SettingsSwitchButton(Context context) {
		super(context);
		initView();
	}

	public SettingsSwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SettingsSwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_settings_switch_button, this);

		ButterKnife.bind(this);

		this.setOnClickListener(v -> setChecked(!checked));
	}

	public void setText(String text) {
		this.text.setText(text);
	}

	public void setSelected(boolean selected) {
		switchView.setChecked(selected);
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
		this.switchView.setChecked(checked);
		if (listener != null) {
			listener.onChecked(checked);
		}
	}

	public void setSwitchListener(OnCheckChangeListener listener) {
		this.listener = listener;
		listener.onChecked(checked);
	}

	public void setColor(int color) {
		text.setTextColor(color);
	}
}
