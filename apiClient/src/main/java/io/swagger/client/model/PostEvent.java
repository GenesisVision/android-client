/*
 * Core API v2.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v2.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PostEvent
 */


public class PostEvent implements Parcelable
{
	public static final Parcelable.Creator<PostEvent> CREATOR = new Parcelable.Creator<PostEvent>()
	{
		public PostEvent createFromParcel(Parcel in) {
			return new PostEvent(in);
		}

		public PostEvent[] newArray(int size) {
			return new PostEvent[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("percent")
	private Double percent = null;

	@SerializedName("changeState")
	private ChangeState changeState = null;

	public PostEvent() {
	}

	PostEvent(Parcel in) {
		title = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		amount = (Double) in.readValue(null);
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		percent = (Double) in.readValue(null);
		changeState = (ChangeState) in.readValue(ChangeState.class.getClassLoader());
	}

	public PostEvent title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PostEvent logoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		return this;
	}

	/**
	 * Get logoUrl
	 *
	 * @return logoUrl
	 **/
	@Schema(description = "")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public PostEvent amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@Schema(description = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PostEvent currency(Currency currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public PostEvent percent(Double percent) {
		this.percent = percent;
		return this;
	}

	/**
	 * Get percent
	 *
	 * @return percent
	 **/
	@Schema(description = "")
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public PostEvent changeState(ChangeState changeState) {
		this.changeState = changeState;
		return this;
	}

	/**
	 * Get changeState
	 *
	 * @return changeState
	 **/
	@Schema(description = "")
	public ChangeState getChangeState() {
		return changeState;
	}

	public void setChangeState(ChangeState changeState) {
		this.changeState = changeState;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostEvent postEvent = (PostEvent) o;
		return Objects.equals(this.title, postEvent.title) &&
				Objects.equals(this.logoUrl, postEvent.logoUrl) &&
				Objects.equals(this.amount, postEvent.amount) &&
				Objects.equals(this.currency, postEvent.currency) &&
				Objects.equals(this.percent, postEvent.percent) &&
				Objects.equals(this.changeState, postEvent.changeState);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, logoUrl, amount, currency, percent, changeState);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostEvent {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
		sb.append("    changeState: ").append(toIndentedString(changeState)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(title);
		out.writeValue(logoUrl);
		out.writeValue(amount);
		out.writeValue(currency);
		out.writeValue(percent);
		out.writeValue(changeState);
	}

	public int describeContents() {
		return 0;
	}
}
