package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import androidx.appcompat.content.res.AppCompatResources;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.PortfolioEvent;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.utils.ImageUtils;
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

	private PortfolioEvent event;

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
		date.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(view -> {
			if (event != null) {
				if (event.getAssetType().equals(DashboardPortfolioEvent.AssetTypeEnum.PROGRAM)) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(event.getAssetId(),
							event.getLogoUrl(),
							event.getAssetColor(),
							0,
							0.0,
							event.getAssetName(),
							"",
							event.getProgramCurrency(),
							false,
							false);
					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				}
				else if (event.getAssetType().equals(DashboardPortfolioEvent.AssetTypeEnum.FUND)) {
					FundDetailsModel fundDetailsModel = new FundDetailsModel(event.getAssetId(),
							event.getLogoUrl(),
							event.getAssetColor(),
							event.getAssetName(),
							"",
							false,
							false);
					EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
				}
			}
		});
	}

	public void setEvent(PortfolioEvent event) {
		this.event = event;

		if (event.getLogoUrl() == null || event.getLogoUrl().isEmpty()) {
			GenericDraweeHierarchy hierarchy = subject.getHierarchy();
			hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(event.getAssetColor())));
			subject.setHierarchy(hierarchy);
			subject.setImageURI("");
		}
		else {
			subject.setImageURI(ImageUtils.getImageUri(event.getLogoUrl()));
		}

		action.getHierarchy().setPlaceholderImage(AppCompatResources.getDrawable(getContext(), event.getActionResId()));

		this.value.setText(event.getValue());
		this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(), event.getValueColorResId()));

		this.text.setText(event.getText());

		this.date.setText(event.getDateTime());
	}
}
