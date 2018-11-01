package vision.genesis.clientapp.model;

import java.util.Locale;
import java.util.UUID;

import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */
public class PortfolioEvent
{
	public static PortfolioEvent createFrom(DashboardPortfolioEvent dashboardEvent) {
		PortfolioEvent event = new PortfolioEvent();

		event.setAssetType(dashboardEvent.getAssetType());
		event.setAssetId(dashboardEvent.getAssetId());

		event.setLogoUrl(dashboardEvent.getLogo());
		event.setAssetColor(dashboardEvent.getColor());
		event.setAssetName(dashboardEvent.getTitle());
		event.setProgramCurrency(dashboardEvent.getCurrency().getValue());

		Integer actionResId = R.drawable.icon_arrow_green_down;
		String text = "";
		String title = dashboardEvent.getTitle();
		int valueColorAttrId = R.attr.colorTextPrimary;

		switch (dashboardEvent.getType()) {
			case INVEST:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_invest_template), title);
				actionResId = R.drawable.icon_arrow_white_left;
				valueColorAttrId = R.attr.colorTextPrimary;
				break;
			case WITHDRAW:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_withdraw_template), title);
				actionResId = R.drawable.icon_arrow_white_right;
				valueColorAttrId = R.attr.colorTextPrimary;
				break;
			case PROFIT:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_period_ended_template), title);
				actionResId = R.drawable.icon_arrow_green_up;
				valueColorAttrId = R.attr.colorGreen;
				break;
			case LOSS:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_period_ended_template), title);
				actionResId = R.drawable.icon_arrow_red_down;
				valueColorAttrId = R.attr.colorRed;
				break;
			case REINVEST:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_period_ended_template), title);
				actionResId = R.drawable.icon_reinvest;
				valueColorAttrId = R.attr.colorAccent;
				break;
			case CANCELLED:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_request_canceled_template), title);
				actionResId = R.drawable.icon_request_cancelled;
				valueColorAttrId = R.attr.colorRed;
				break;
			case ENDED:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_closed_template), title);
				actionResId = R.drawable.icon_program_closed;
				valueColorAttrId = R.attr.colorTextPrimary;
				break;
			default:
				break;
		}

		event.setValueColorResId(valueColorAttrId);
		event.setActionResId(actionResId);
//		event.setText(text);
		event.setText(dashboardEvent.getDescription());

		event.setTime(DateTimeUtil.formatShortTime(dashboardEvent.getDate()));
		event.setDateTime(DateTimeUtil.formatEventDateTime(dashboardEvent.getDate()));
		event.setValue(StringFormatUtil.getBaseValueString(dashboardEvent.getValue(), dashboardEvent.getCurrency().getValue()));
		event.setValueNegative(dashboardEvent.getValue() < 0);

		return event;
	}

	private UUID assetId;

	private String logoUrl;

	private Integer actionResId;

	private String text;

	private String time;

	private String dateTime;

	private String value;

	private Boolean isValueNegative;

	private String assetColor;

	private String assetName;

	private String programCurrency;

	private DashboardPortfolioEvent.AssetTypeEnum assetType;

	private int valueColorResId;

	public UUID getAssetId() {
		return assetId;
	}

	public void setAssetId(UUID assetId) {
		this.assetId = assetId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Integer getActionResId() {
		return actionResId;
	}

	public void setActionResId(Integer actionResId) {
		this.actionResId = actionResId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getValueNegative() {
		return isValueNegative;
	}

	public void setValueNegative(Boolean valueNegative) {
		isValueNegative = valueNegative;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAssetColor() {
		return assetColor;
	}

	public void setAssetColor(String assetColor) {
		this.assetColor = assetColor;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getProgramCurrency() {
		return programCurrency;
	}

	public void setProgramCurrency(String programCurrency) {
		this.programCurrency = programCurrency;
	}

	public DashboardPortfolioEvent.AssetTypeEnum getAssetType() {
		return assetType;
	}

	public void setAssetType(DashboardPortfolioEvent.AssetTypeEnum assetType) {
		this.assetType = assetType;
	}

	public int getValueColorResId() {
		return valueColorResId;
	}

	public void setValueColorResId(int valueColorResId) {
		this.valueColorResId = valueColorResId;
	}
}
