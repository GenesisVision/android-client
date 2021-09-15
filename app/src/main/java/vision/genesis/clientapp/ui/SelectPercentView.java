package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/02/2021.
 */

public class SelectPercentView extends RelativeLayout
{
	public interface OnPercentChangeListener
	{
		void onPercentChange(int percent, boolean fromUser);
	}

	@BindView(R.id.percent_0)
	public TextView percent0;

	@BindView(R.id.percent_25)
	public TextView percent25;

	@BindView(R.id.percent_50)
	public TextView percent50;

	@BindView(R.id.percent_75)
	public TextView percent75;

	@BindView(R.id.percent_100)
	public TextView percent100;

	@BindView(R.id.seek_bar)
	public AppCompatSeekBar seekBar;

	private OnPercentChangeListener listener;


	public SelectPercentView(Context context) {
		super(context);
		initView();
	}

	public SelectPercentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SelectPercentView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_select_percent, this);

		ButterKnife.bind(this);

		this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (listener != null) {
					listener.onPercentChange(progress, fromUser);
				}
				updateView(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	public void setProgress(int progress) {
		if (seekBar != null) {
			seekBar.setProgress(progress);
		}
	}

	public void setListener(OnPercentChangeListener listener) {
		this.listener = listener;
		if (seekBar != null) {
			listener.onPercentChange(seekBar.getProgress(), false);
		}
	}

	private void updateView(int progress) {
		selectText(percent25, progress >= 25);
		selectText(percent50, progress >= 50);
		selectText(percent75, progress >= 75);
		selectText(percent100, progress >= 100);
	}

	private void selectText(TextView textView, boolean select) {
		textView.setTextColor(ThemeUtil.getColorByAttrId(getContext(), select ? R.attr.colorTextPrimary : R.attr.colorTextSecondary));
		textView.setTypeface(select ? TypefaceUtil.medium() : TypefaceUtil.regular());
	}

	@Override
	public void setEnabled(boolean enabled) {
		seekBar.setEnabled(enabled);
		updateView(0);
		super.setEnabled(enabled);
	}
}
