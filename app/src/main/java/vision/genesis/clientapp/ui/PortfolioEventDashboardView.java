package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/08/2018.
 */
public class PortfolioEventDashboardView extends RelativeLayout
{
	@BindView(R.id.subject)
	public SimpleDraweeView subject;

	@BindView(R.id.action)
	public SimpleDraweeView action;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.text)
	public TextView text;

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
		inflate(getContext(), R.layout.list_item_portfolio_event, this);

		unbinder = ButterKnife.bind(this);

		value.setTypeface(TypefaceUtil.semibold());
	}

	public void setEvent(DashboardPortfolioEvent event) {
		subject.getHierarchy().setPlaceholderImage(ContextCompat.getDrawable(getContext(), R.drawable.logo));
		action.getHierarchy().setPlaceholderImage(ContextCompat.getDrawable(getContext(), R.drawable.icon_event_reinvest));

		this.date.setText(DateTimeUtil.formatEventDateTime(event.getDate()).toLowerCase());

		String valueString = event.getValue() > 0 ? "+" : "";
		valueString = valueString.concat(StringFormatUtil.formatAmount(event.getValue(), 2, 4));
		valueString = valueString.concat(" GVT");
		this.value.setText(valueString);
		this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(), event.getValue() >= 0 ? R.attr.colorGreen : R.attr.colorRed));

//		this.text.setText(event.getDescription());
	}
}
