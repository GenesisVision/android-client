package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.AssetDetails;
import io.swagger.client.model.InvestmentEventItemViewModel;
import io.swagger.client.model.InvestmentEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
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

	@BindView(R.id.value_label)
	public TextView valueLabel;

	@BindView(R.id.text)
	public TextView text;

	private Unbinder unbinder;

	private InvestmentEventViewModel event;

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
		inflate(getContext(), R.layout.dashboard_portfolio_event, this);

		unbinder = ButterKnife.bind(this);

		value.setTypeface(TypefaceUtil.semibold());
//		date.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(v -> {
			if (event != null) {
				EventBus.getDefault().post(new ShowEventDetailsEvent(event));
			}
		});
	}

//	public void setEvent(InvestmentEventViewModel event) {
//		this.event = event;
//
//		if (event.getLogoUrl() == null || event.getLogoUrl().isEmpty()) {
//			GenericDraweeHierarchy hierarchy = subject.getHierarchy();
//			hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(event.getAssetColor())));
//			subject.setHierarchy(hierarchy);
//			subject.setImageURI("");
//		}
//		else {
//			subject.setImageURI(ImageUtils.getImageUri(event.getLogoUrl()));
//		}
//
//		action.getHierarchy().setPlaceholderImage(AppCompatResources.getDrawable(getContext(), event.getActionResId()));
//
//		this.value.setText(event.getValue());
//		this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(), event.getValueColorResId()));
//
//		this.text.setText(event.getText());
//
//		this.date.setText(event.getDateTime());
//	}

	public void setEvent(InvestmentEventViewModel event) {
		this.event = event;

		AssetDetails details = event.getAssetDetails();
		if (details != null) {
			if (details.getLogo() == null || details.getLogo().isEmpty()) {
				GenericDraweeHierarchy hierarchy = subject.getHierarchy();
				hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(details.getColor())));
				subject.setHierarchy(hierarchy);
				subject.setImageURI("");
			}
			else {
				subject.setImageURI(ImageUtils.getImageUri(details.getLogo()));
			}
		}

		action.setImageURI(ImageUtils.getImageUri(event.getIcon()));
		text.setText(event.getTitle());
		date.setText(DateTimeUtil.formatEventDateTime(event.getDate()));

		value.setVisibility(View.VISIBLE);
		valueLabel.setVisibility(View.VISIBLE);
		if (event.getExtendedInfo() != null && event.getExtendedInfo().size() > 0) {
			InvestmentEventItemViewModel info = event.getExtendedInfo().get(0);
			value.setText(StringFormatUtil.getValueString(info.getAmount(), info.getCurrency().getValue()));
			valueLabel.setText(info.getTitle());
		}
		else if (event.getAmount() != null) {
			value.setText(StringFormatUtil.getValueString(event.getAmount(), event.getCurrency().getValue()));
			valueLabel.setVisibility(View.GONE);
		}
		else {
			value.setVisibility(View.GONE);
			valueLabel.setVisibility(View.GONE);
		}

		int valueColorAttrId = R.attr.colorTextPrimary;
		switch (event.getChangeState()) {
			case INCREASED:
				valueColorAttrId = R.attr.colorGreen;
				break;
			case DECREASED:
				valueColorAttrId = R.attr.colorRed;
				break;
			case NOTCHANGED:
				break;
		}
		this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(), valueColorAttrId));
	}
}
