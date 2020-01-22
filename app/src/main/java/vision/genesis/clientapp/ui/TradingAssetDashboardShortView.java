package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

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
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowTradingAccountDetailsEvent;
import vision.genesis.clientapp.utils.ImageUtils;
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

	@BindView(R.id.broker_logo)
	public SimpleDraweeView brokerLogo;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.demo_tag)
	public TagView demoTag;

	@BindView(R.id.type)
	public TextView type;

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
			if (asset != null && asset.getAccountInfo() != null &&
					asset.getAccountInfo().getStatus() != DashboardTradingAssetStatus.PENDING) {
				switch (asset.getAssetType()) {
					case NONE:
						TradingAccountDetailsModel tradingAccountDetailsModel = new TradingAccountDetailsModel(
								asset.getId(),
								asset.getAccountInfo() != null ? asset.getAccountInfo().getTitle() : null,
								asset.getBroker() != null ? asset.getBroker().getLogo() : null,
								asset.getAccountInfo() != null ? asset.getAccountInfo().getType() : null
						);
						tradingAccountDetailsModel.setIsDemo(asset.getActions().isCanMakeDemoDeposit());
						EventBus.getDefault().post(new ShowTradingAccountDetailsEvent(tradingAccountDetailsModel));
						break;
					case PROGRAM:
						ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(
								asset.getId(),
								asset.getPublicInfo().getLogo(),
								asset.getPublicInfo().getColor(),
								0,
								0.0,
								asset.getPublicInfo().getTitle(),
								"",
								asset.getAccountInfo().getCurrency().getValue(),
								false,
								false,
								AssetType.PROGRAM);
						EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
						break;
					case FUND:
						FundDetailsModel fundDetailsModel = new FundDetailsModel(
								asset.getId(),
								asset.getPublicInfo().getLogo(),
								asset.getPublicInfo().getColor(),
								asset.getPublicInfo().getTitle(),
								"",
								false,
								false);
						EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
						break;
					case FOLLOW:
						ProgramDetailsModel followDetailsModel = new ProgramDetailsModel(
								asset.getId(),
								asset.getPublicInfo().getLogo(),
								asset.getPublicInfo().getColor(),
								0,
								0.0,
								asset.getPublicInfo().getTitle(),
								"",
								asset.getAccountInfo().getCurrency() != null
										? asset.getAccountInfo().getCurrency().getValue()
										: null,
								false,
								false,
								AssetType.FOLLOW);
						EventBus.getDefault().post(new ShowProgramDetailsEvent(followDetailsModel));
						break;
				}
			}
		});
	}

	public void setData(DashboardTradingAsset asset, String baseCurrency) {
		this.asset = asset;
		this.baseCurrency = baseCurrency;

		double value = asset.getAccountInfo().getBalance() != null
				? asset.getAccountInfo().getBalance()
				: 0;
		double profitPercent = asset.getStatistic().getProfit();

		setType();

		if (asset.getAssetType().equals(AssetType.NONE)) {
			this.logo.setVisibility(View.GONE);
			this.brokerLogo.setVisibility(View.VISIBLE);
			this.brokerLogo.setImageURI(ImageUtils.getImageUri(asset.getBroker().getLogo()));

			this.name.setText(asset.getAccountInfo().getTitle());
			if (asset.getAccountInfo().getBalance() != null) {
				if (asset.getAccountInfo().getCurrency() != null) {
					this.value.setText(StringFormatUtil.getValueString(asset.getAccountInfo().getBalance(), asset.getAccountInfo().getCurrency().getValue()));
				}
				else {
					this.value.setText(StringFormatUtil.formatAmount(asset.getAccountInfo().getBalance()));
				}
			}

			if ((asset.getAccountInfo().getStatus().equals(DashboardTradingAssetStatus.PENDING))) {
				this.status.setText(getContext().getString(R.string.pending));
				this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
				this.statusProgress.setVisibility(View.VISIBLE);

				statusGroup.setVisibility(ViewGroup.VISIBLE);
				valueGroup.setVisibility(ViewGroup.GONE);
			}
			else {
				valueGroup.setVisibility(ViewGroup.VISIBLE);
				statusGroup.setVisibility(ViewGroup.GONE);
			}

			this.change.setVisibility(ViewGroup.GONE);

			if (asset.getActions().isCanMakeDemoDeposit()) {
				demoTag.setVisibility(View.VISIBLE);
				demoTag.setDemo();
			}
		}
		else {
			this.brokerLogo.setVisibility(View.GONE);
			this.logo.setVisibility(View.VISIBLE);
			this.logo.setImage(asset.getPublicInfo().getLogo(), asset.getPublicInfo().getColor(), 50, 50);
			this.logo.hideLevel();

			this.name.setText(asset.getPublicInfo().getTitle());

			if (asset.getAccountInfo().getStatus().equals(DashboardTradingAssetStatus.ACTIVE)) {
				if (asset.getAccountInfo().getBalance() != null) {
					if (asset.getAccountInfo().getCurrency() != null) {
						this.value.setText(StringFormatUtil.getValueString(value, asset.getAccountInfo().getCurrency().getValue()));
					}
					else {
						this.value.setText(StringFormatUtil.formatAmount(asset.getAccountInfo().getBalance()));
					}
					updateChangeText(value, profitPercent);
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

	private void setType() {
		int typeStringResId = 0;
		switch (asset.getAssetTypeExt()) {
			case NONE:
				this.type.setVisibility(View.GONE);
				break;
			case PROGRAM:
				typeStringResId = R.string.program;
				break;
			case SIGNALPROGRAM:
				typeStringResId = R.string.signal_program;
				break;
			case FUND:
				typeStringResId = R.string.fund;
				break;
			case SIGNALTRADINGACCOUNT:
				typeStringResId = R.string.signal_trading_account;
				break;
			case EXTERNALSIGNALTRADINGACCOUNT:
				typeStringResId = R.string.external_trading_account;
				break;
		}

		if (typeStringResId != 0) {
			this.type.setText(getContext().getString(typeStringResId));
		}
	}

	private void updateChangeText(double value, double profitPercent) {
//		double profitValue = value * profitPercent / 100 / (1 - profitPercent);
		String sign = profitPercent > 0 ? "+" : "";
//		this.change.setText(String.format(Locale.getDefault(), "%s%s (%s%%)",
//				sign,
//				StringFormatUtil.getValueString(profitValue, baseCurrency),
//				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setText(String.format(Locale.getDefault(), "%s%s%%",
				sign,
				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profitPercent > 0
						? R.attr.colorGreen
						: profitPercent < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}
}
