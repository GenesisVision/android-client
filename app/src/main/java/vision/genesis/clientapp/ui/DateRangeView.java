package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2018.
 */
public class DateRangeView extends RelativeLayout
{
	@BindView(R.id.text_date_range)
	public TextView dateRange;

	private Unbinder unbinder;

	public DateRangeView(Context context) {
		super(context);
		initView();
	}

	public DateRangeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DateRangeView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_date_range, this);

		unbinder = ButterKnife.bind(this);

		dateRange.setTypeface(TypefaceUtil.semibold());
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange.setText(dateRange.toString());
	}
}
