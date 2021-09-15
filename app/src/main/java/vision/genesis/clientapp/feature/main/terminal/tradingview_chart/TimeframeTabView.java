package vision.genesis.clientapp.feature.main.terminal.tradingview_chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;

public class TimeframeTabView extends RelativeLayout
{
	@BindView(R.id.view_selected)
	public View selectedView;

	@BindView(R.id.text)
	public TextView text;

	private Unbinder unbinder;

	private int index = -1;

	public TimeframeTabView(Context context) {
		super(context);
		initView();
	}

	public TimeframeTabView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TimeframeTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		View customView = inflate(getContext(), R.layout.view_timeframe_tab, this);
//		View targetViewToApplyMargin = (View) customView.getParent();
//		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) targetViewToApplyMargin.getLayoutParams();
//
//		layoutParams.rightMargin = 20;
//		targetViewToApplyMargin.setLayoutParams(layoutParams);

		unbinder = ButterKnife.bind(this);
	}

	public void setData(int index, String text) {
		this.index = index;
		this.text.setText(text);
	}

	public void setSelectedState(boolean selected) {
		text.setTextColor(ThemeUtil.getColorByAttrId(getContext(), selected ? R.attr.colorAccent : R.attr.colorTextSecondary));
		selectedView.setVisibility(selected ? View.VISIBLE : View.GONE);
	}
}
