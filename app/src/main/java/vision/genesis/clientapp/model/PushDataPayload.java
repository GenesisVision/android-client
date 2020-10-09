package vision.genesis.clientapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/09/2020.
 */
public class PushDataPayload
{
	@SerializedName("Id")
	public String id;

	@SerializedName("Text")
	public String text;

	@SerializedName("Date")
	public String date;

	@SerializedName("Type")
	public String type;

	@SerializedName("CampaignId")
	public String campaignId;

	@SerializedName("Currency")
	public String currency;

	@SerializedName("Link")
	public String link;

	@SerializedName("RequestId")
	public String requestId;

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public String getCurrency() {
		return currency;
	}

	public String getLink() {
		return link;
	}

	public String getRequestId() {
		return requestId;
	}
}