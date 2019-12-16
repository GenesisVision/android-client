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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PrivateTradingAccountFull
 */


public class PrivateTradingAccountFull implements Parcelable
{
	public static final Parcelable.Creator<PrivateTradingAccountFull> CREATOR = new Parcelable.Creator<PrivateTradingAccountFull>()
	{
		public PrivateTradingAccountFull createFromParcel(Parcel in) {
			return new PrivateTradingAccountFull(in);
		}

		public PrivateTradingAccountFull[] newArray(int size) {
			return new PrivateTradingAccountFull[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("publicInfo")
	private PrivateTradingAccountFullPublicDetails publicInfo = null;

	@SerializedName("tradingAccountInfo")
	private PrivateTradingAccountFullTradingAccountDetails tradingAccountInfo = null;

	@SerializedName("brokerDetails")
	private BrokerDetails brokerDetails = null;

	@SerializedName("ownerActions")
	private PrivateTradingAccountOwnerActions ownerActions = null;

	public PrivateTradingAccountFull() {
	}

	PrivateTradingAccountFull(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		publicInfo = (PrivateTradingAccountFullPublicDetails) in.readValue(PrivateTradingAccountFullPublicDetails.class.getClassLoader());
		tradingAccountInfo = (PrivateTradingAccountFullTradingAccountDetails) in.readValue(PrivateTradingAccountFullTradingAccountDetails.class.getClassLoader());
		brokerDetails = (BrokerDetails) in.readValue(BrokerDetails.class.getClassLoader());
		ownerActions = (PrivateTradingAccountOwnerActions) in.readValue(PrivateTradingAccountOwnerActions.class.getClassLoader());
	}

	public PrivateTradingAccountFull id(UUID id) {
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

	public PrivateTradingAccountFull publicInfo(PrivateTradingAccountFullPublicDetails publicInfo) {
		this.publicInfo = publicInfo;
		return this;
	}

	/**
	 * Get publicInfo
	 *
	 * @return publicInfo
	 **/
	@Schema(description = "")
	public PrivateTradingAccountFullPublicDetails getPublicInfo() {
		return publicInfo;
	}

	public void setPublicInfo(PrivateTradingAccountFullPublicDetails publicInfo) {
		this.publicInfo = publicInfo;
	}

	public PrivateTradingAccountFull tradingAccountInfo(PrivateTradingAccountFullTradingAccountDetails tradingAccountInfo) {
		this.tradingAccountInfo = tradingAccountInfo;
		return this;
	}

	/**
	 * Get tradingAccountInfo
	 *
	 * @return tradingAccountInfo
	 **/
	@Schema(description = "")
	public PrivateTradingAccountFullTradingAccountDetails getTradingAccountInfo() {
		return tradingAccountInfo;
	}

	public void setTradingAccountInfo(PrivateTradingAccountFullTradingAccountDetails tradingAccountInfo) {
		this.tradingAccountInfo = tradingAccountInfo;
	}

	public PrivateTradingAccountFull brokerDetails(BrokerDetails brokerDetails) {
		this.brokerDetails = brokerDetails;
		return this;
	}

	/**
	 * Get brokerDetails
	 *
	 * @return brokerDetails
	 **/
	@Schema(description = "")
	public BrokerDetails getBrokerDetails() {
		return brokerDetails;
	}

	public void setBrokerDetails(BrokerDetails brokerDetails) {
		this.brokerDetails = brokerDetails;
	}

	public PrivateTradingAccountFull ownerActions(PrivateTradingAccountOwnerActions ownerActions) {
		this.ownerActions = ownerActions;
		return this;
	}

	/**
	 * Get ownerActions
	 *
	 * @return ownerActions
	 **/
	@Schema(description = "")
	public PrivateTradingAccountOwnerActions getOwnerActions() {
		return ownerActions;
	}

	public void setOwnerActions(PrivateTradingAccountOwnerActions ownerActions) {
		this.ownerActions = ownerActions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PrivateTradingAccountFull privateTradingAccountFull = (PrivateTradingAccountFull) o;
		return Objects.equals(this.id, privateTradingAccountFull.id) &&
				Objects.equals(this.publicInfo, privateTradingAccountFull.publicInfo) &&
				Objects.equals(this.tradingAccountInfo, privateTradingAccountFull.tradingAccountInfo) &&
				Objects.equals(this.brokerDetails, privateTradingAccountFull.brokerDetails) &&
				Objects.equals(this.ownerActions, privateTradingAccountFull.ownerActions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, publicInfo, tradingAccountInfo, brokerDetails, ownerActions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PrivateTradingAccountFull {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    publicInfo: ").append(toIndentedString(publicInfo)).append("\n");
		sb.append("    tradingAccountInfo: ").append(toIndentedString(tradingAccountInfo)).append("\n");
		sb.append("    brokerDetails: ").append(toIndentedString(brokerDetails)).append("\n");
		sb.append("    ownerActions: ").append(toIndentedString(ownerActions)).append("\n");
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
		out.writeValue(id);
		out.writeValue(publicInfo);
		out.writeValue(tradingAccountInfo);
		out.writeValue(brokerDetails);
		out.writeValue(ownerActions);
	}

	public int describeContents() {
		return 0;
	}
}
