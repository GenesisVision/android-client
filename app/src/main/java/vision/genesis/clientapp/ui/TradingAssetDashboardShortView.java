package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.DashboardTradingAsset;
import io.swagger.client.model.DashboardTradingAssetStatus;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

public class TradingAssetDashboardShortView extends RelativeLayout
{
	@BindView(R.id.logo)
	public ProgramLogoView logo;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.status)
	public TextView status;

	@BindView(R.id.status_progress)
	public ProgressBar statusProgress;

	@BindView(R.id.group_value)
	public ViewGroup valueGroup;

	@BindView(R.id.group_status)
	public ViewGroup statusGroup;

	@BindView(R.id.delimiter)
	public View delimiter;

	private Unbinder unbinder;

	private DashboardTradingAsset asset;

	private String baseCurrency;

	public TradingAssetDashboardShortView(Context context) {
		super(context);
		initView();
	}

	public TradingAssetDashboardShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TradingAssetDashboardShortView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_trading_asset_dashboard_short, this);

		unbinder = ButterKnife.bind(this);

		name.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(v -> {
			if (asset != null) {
				switch (asset.getAssetType()) {
					case NONE:
						break;
					case PROGRAM:
						ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(asset.getId(),
								asset.getPublicInfo().getLogo(),
								asset.getPublicInfo().getColor(),
								0,
								0.0,
								asset.getPublicInfo().getTitle(),
								"",
								asset.getAccountInfo().getCurrency().getValue(),
								false,
								false);
						EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
						break;
					case FUND:
						FundDetailsModel fundDetailsModel = new FundDetailsModel(asset.getId(),
								asset.getPublicInfo().getLogo(),
								asset.getPublicInfo().getColor(),
								asset.getPublicInfo().getTitle(),
								"",
								false,
								false);
						EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
						break;
					case FOLLOW:
						break;
				}
			}
		});
	}

	public void setData(DashboardTradingAsset asset, String baseCurrency) {
		this.asset = asset;
		this.baseCurrency = baseCurrency;

		if (asset.getAssetType().equals(AssetType.NONE)) {
			this.logo.setImage(asset.getBroker().getLogo(), "", 50, 50);
			this.logo.hideLevel();
			this.name.setText(asset.getAccountInfo().getLogin());
			this.value.setText(StringFormatUtil.getValueString(asset.getAccountInfo().getBalance(), asset.getAccountInfo().getCurrency().getValue()));

			valueGroup.setVisibility(ViewGroup.VISIBLE);
			statusGroup.setVisibility(ViewGroup.GONE);
		}
		else {
			this.logo.setImage(asset.getPublicInfo().getLogo(), asset.getPublicInfo().getColor(), 50, 50);
			this.logo.hideLevel();
			this.name.setText(asset.getPublicInfo().getTitle());

			double value = Math.random() * 100;
			double change = asset.getStatistic().getProfit();

			if (asset.getAccountInfo().getStatus().equals(DashboardTradingAssetStatus.ACTIVE)) {
				if (asset.getAccountInfo().getBalance() != null) {
					this.value.setText(StringFormatUtil.getValueString(asset.getAccountInfo().getBalance(), asset.getAccountInfo().getCurrency().getValue()));
					updateChangeText(value, change);
				}
				valueGroup.setVisibility(ViewGroup.VISIBLE);
				statusGroup.setVisibility(ViewGroup.GONE);
			}
			else {
				if ((asset.getAccountInfo().getStatus().equals(DashboardTradingAssetStatus.PENDING))) {
					this.status.setText(getContext().getString(R.string.pending));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
					this.statusProgress.setVisibility(View.VISIBLE);
				}
				else if ((asset.getAccountInfo().getStatus().equals(DashboardTradingAssetStatus.DISABLED))) {
					this.status.setText(getContext().getString(R.string.disabled));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
					this.statusProgress.setVisibility(View.GONE);
				}
				statusGroup.setVisibility(ViewGroup.VISIBLE);
				valueGroup.setVisibility(ViewGroup.GONE);
			}
		}
	}

	private void updateChangeText(double value, double profit) {
		double profitPercent = Math.abs(profit / (value - profit) * 100);
		String sign = profit > 0 ? "+" : "";
		this.change.setText(String.format(Locale.getDefault(), "%s%s (%s%%)",
				sign,
				StringFormatUtil.getValueString(profit, baseCurrency),
				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profit > 0
						? R.attr.colorGreen
						: profit < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}
}
