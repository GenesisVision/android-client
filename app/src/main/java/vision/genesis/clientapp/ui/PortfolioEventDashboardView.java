package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */
public class PortfolioEventDashboardView extends RelativeLayout
{
	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.value)
	public TextView value;

	private Unbinder unbinder;

	public PortfolioEventDashboardView(Context context) {
		super(context);
		initView();
	}

	public PortfolioEventDashboardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PortfolioEventDashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_portfolio_event_dashboard, this);

		unbinder = ButterKnife.bind(this);

		type.setTypeface(TypefaceUtil.regular());
		value.setTypeface(TypefaceUtil.regular());
	}

	public void setEvent(DashboardPortfolioEvent event) {
		this.type.setText(event.getDescription());

		String valueString = event.getValue() > 0 ? "+" : "";
		valueString = valueString.concat(event.getValue().toString());
		this.value.setText(valueString);
		this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(), event.getValue() >= 0 ? R.attr.colorGreen : R.attr.colorRed));
	}
}
