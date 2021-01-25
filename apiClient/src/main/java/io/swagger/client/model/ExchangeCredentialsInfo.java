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

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ExchangeCredentialsInfo
 */


public class ExchangeCredentialsInfo implements Parcelable
{
	public static final Parcelable.Creator<ExchangeCredentialsInfo> CREATOR = new Parcelable.Creator<ExchangeCredentialsInfo>()
	{
		public ExchangeCredentialsInfo createFromParcel(Parcel in) {
			return new ExchangeCredentialsInfo(in);
		}

		public ExchangeCredentialsInfo[] newArray(int size) {
			return new ExchangeCredentialsInfo[size];
		}
	};

	@SerializedName("apiKey")
	private String apiKey = null;

	@SerializedName("apiSecret")
	private String apiSecret = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("isEnabled")
	private Boolean isEnabled = null;

	@SerializedName("isIpRestrict")
	private Boolean isIpRestrict = null;

	@SerializedName("isTradingEnabled")
	private Boolean isTradingEnabled = null;

	@SerializedName("dateCreate")
	private DateTime dateCreate = null;

	@SerializedName("dateRemove")
	private DateTime dateRemove = null;

	@SerializedName("allowedIps")
	private List<ExchangeCredentialsIpInfo> allowedIps = null;

	public ExchangeCredentialsInfo() {
	}

	ExchangeCredentialsInfo(Parcel in) {
		apiKey = (String) in.readValue(null);
		apiSecret = (String) in.readValue(null);
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		title = (String) in.readValue(null);
		isEnabled = (Boolean) in.readValue(null);
		isIpRestrict = (Boolean) in.readValue(null);
		isTradingEnabled = (Boolean) in.readValue(null);
		dateCreate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		dateRemove = (DateTime) in.readValue(DateTime.class.getClassLoader());
		allowedIps = (List<ExchangeCredentialsIpInfo>) in.readValue(ExchangeCredentialsIpInfo.class.getClassLoader());
	}

	public ExchangeCredentialsInfo apiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}

	/**
	 * Get apiKey
	 *
	 * @return apiKey
	 **/
	@Schema(description = "")
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public ExchangeCredentialsInfo apiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
		return this;
	}

	/**
	 * Get apiSecret
	 *
	 * @return apiSecret
	 **/
	@Schema(description = "")
	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public ExchangeCredentialsInfo id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ExchangeCredentialsInfo title(String title) {
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

	public ExchangeCredentialsInfo isEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

	/**
	 * Get isEnabled
	 *
	 * @return isEnabled
	 **/
	@Schema(description = "")
	public Boolean isIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public ExchangeCredentialsInfo isIpRestrict(Boolean isIpRestrict) {
		this.isIpRestrict = isIpRestrict;
		return this;
	}

	/**
	 * Get isIpRestrict
	 *
	 * @return isIpRestrict
	 **/
	@Schema(description = "")
	public Boolean isIsIpRestrict() {
		return isIpRestrict;
	}

	public void setIsIpRestrict(Boolean isIpRestrict) {
		this.isIpRestrict = isIpRestrict;
	}

	public ExchangeCredentialsInfo isTradingEnabled(Boolean isTradingEnabled) {
		this.isTradingEnabled = isTradingEnabled;
		return this;
	}

	/**
	 * Get isTradingEnabled
	 *
	 * @return isTradingEnabled
	 **/
	@Schema(description = "")
	public Boolean isIsTradingEnabled() {
		return isTradingEnabled;
	}

	public void setIsTradingEnabled(Boolean isTradingEnabled) {
		this.isTradingEnabled = isTradingEnabled;
	}

	public ExchangeCredentialsInfo dateCreate(DateTime dateCreate) {
		this.dateCreate = dateCreate;
		return this;
	}

	/**
	 * Get dateCreate
	 *
	 * @return dateCreate
	 **/
	@Schema(description = "")
	public DateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(DateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public ExchangeCredentialsInfo dateRemove(DateTime dateRemove) {
		this.dateRemove = dateRemove;
		return this;
	}

	/**
	 * Get dateRemove
	 *
	 * @return dateRemove
	 **/
	@Schema(description = "")
	public DateTime getDateRemove() {
		return dateRemove;
	}

	public void setDateRemove(DateTime dateRemove) {
		this.dateRemove = dateRemove;
	}

	public ExchangeCredentialsInfo allowedIps(List<ExchangeCredentialsIpInfo> allowedIps) {
		this.allowedIps = allowedIps;
		return this;
	}

	public ExchangeCredentialsInfo addAllowedIpsItem(ExchangeCredentialsIpInfo allowedIpsItem) {
		if (this.allowedIps == null) {
			this.allowedIps = new ArrayList<ExchangeCredentialsIpInfo>();
		}
		this.allowedIps.add(allowedIpsItem);
		return this;
	}

	/**
	 * Get allowedIps
	 *
	 * @return allowedIps
	 **/
	@Schema(description = "")
	public List<ExchangeCredentialsIpInfo> getAllowedIps() {
		return allowedIps;
	}

	public void setAllowedIps(List<ExchangeCredentialsIpInfo> allowedIps) {
		this.allowedIps = allowedIps;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExchangeCredentialsInfo exchangeCredentialsInfo = (ExchangeCredentialsInfo) o;
		return Objects.equals(this.apiKey, exchangeCredentialsInfo.apiKey) &&
				Objects.equals(this.apiSecret, exchangeCredentialsInfo.apiSecret) &&
				Objects.equals(this.id, exchangeCredentialsInfo.id) &&
				Objects.equals(this.title, exchangeCredentialsInfo.title) &&
				Objects.equals(this.isEnabled, exchangeCredentialsInfo.isEnabled) &&
				Objects.equals(this.isIpRestrict, exchangeCredentialsInfo.isIpRestrict) &&
				Objects.equals(this.isTradingEnabled, exchangeCredentialsInfo.isTradingEnabled) &&
				Objects.equals(this.dateCreate, exchangeCredentialsInfo.dateCreate) &&
				Objects.equals(this.dateRemove, exchangeCredentialsInfo.dateRemove) &&
				Objects.equals(this.allowedIps, exchangeCredentialsInfo.allowedIps);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiKey, apiSecret, id, title, isEnabled, isIpRestrict, isTradingEnabled, dateCreate, dateRemove, allowedIps);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExchangeCredentialsInfo {\n");

		sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
		sb.append("    apiSecret: ").append(toIndentedString(apiSecret)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    isEnabled: ").append(toIndentedString(isEnabled)).append("\n");
		sb.append("    isIpRestrict: ").append(toIndentedString(isIpRestrict)).append("\n");
		sb.append("    isTradingEnabled: ").append(toIndentedString(isTradingEnabled)).append("\n");
		sb.append("    dateCreate: ").append(toIndentedString(dateCreate)).append("\n");
		sb.append("    dateRemove: ").append(toIndentedString(dateRemove)).append("\n");
		sb.append("    allowedIps: ").append(toIndentedString(allowedIps)).append("\n");
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
		out.writeValue(apiKey);
		out.writeValue(apiSecret);
		out.writeValue(id);
		out.writeValue(title);
		out.writeValue(isEnabled);
		out.writeValue(isIpRestrict);
		out.writeValue(isTradingEnabled);
		out.writeValue(dateCreate);
		out.writeValue(dateRemove);
		out.writeValue(allowedIps);
	}

	public int describeContents() {
		return 0;
	}
}