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

		event.setAssetId(dashboardEvent.getAssetId());

		event.setLogoUrl(dashboardEvent.getLogo());

		Integer actionResId = R.drawable.icon_arrow_green_down;
		String text = "";
		String title = dashboardEvent.getTitle();

		switch (dashboardEvent.getType()) {
			case INVEST:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_invest_template), title);
				actionResId = R.drawable.icon_arrow_green_down;
				break;
			case WITHDRAW:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_withdraw_template), title);
				actionResId = R.drawable.icon_arrow_red_up;
				break;
			case PROFIT:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_period_ended_template), title);
				actionResId = R.drawable.icon_arrow_red_up;
				break;
			case LOSS:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_period_ended_template), title);
				actionResId = R.drawable.icon_arrow_red_up;
				break;
			case REINVEST:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_period_ended_template), title);
				actionResId = R.drawable.icon_reinvest;
				break;
			case CANCELLED:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_request_canceled_template), title);
				actionResId = R.drawable.icon_arrow_red_up;
				break;
			case ENDED:
				text = String.format(Locale.getDefault(), GenesisVisionApplication.INSTANCE.getString(R.string.event_closed_template), title);
				actionResId = R.drawable.icon_arrow_red_up;
				break;
			default:
				break;
		}

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
}
