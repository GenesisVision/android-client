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
 * BrokerDetails
 */


public class BrokerDetails implements Parcelable
{
	public static final Parcelable.Creator<BrokerDetails> CREATOR = new Parcelable.Creator<BrokerDetails>()
	{
		public BrokerDetails createFromParcel(Parcel in) {
			return new BrokerDetails(in);
		}

		public BrokerDetails[] newArray(int size) {
			return new BrokerDetails[size];
		}
	};

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("isKycRequired")
	private Boolean isKycRequired = null;

	@SerializedName("showSwaps")
	private Boolean showSwaps = null;

	@SerializedName("showTickets")
	private Boolean showTickets = null;

	@SerializedName("showCommissionRebate")
	private Boolean showCommissionRebate = null;

	@SerializedName("isKycRequiredSometime")
	private Boolean isKycRequiredSometime = null;

	@SerializedName("showSwapsSometime")
	private Boolean showSwapsSometime = null;

	@SerializedName("showTicketsSometime")
	private Boolean showTicketsSometime = null;

	@SerializedName("showCommissionRebateSometime")
	private Boolean showCommissionRebateSometime = null;

	public BrokerDetails() {
	}

	BrokerDetails(Parcel in) {
		logo = (String) in.readValue(null);
		name = (String) in.readValue(null);
		isKycRequired = (Boolean) in.readValue(null);
		showSwaps = (Boolean) in.readValue(null);
		showTickets = (Boolean) in.readValue(null);
		showCommissionRebate = (Boolean) in.readValue(null);
		isKycRequiredSometime = (Boolean) in.readValue(null);
		showSwapsSometime = (Boolean) in.readValue(null);
		showTicketsSometime = (Boolean) in.readValue(null);
		showCommissionRebateSometime = (Boolean) in.readValue(null);
	}

	public BrokerDetails logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@Schema(description = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public BrokerDetails name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	@Schema(description = "")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BrokerDetails isKycRequired(Boolean isKycRequired) {
		this.isKycRequired = isKycRequired;
		return this;
	}

	/**
	 * Get isKycRequired
	 *
	 * @return isKycRequired
	 **/
	@Schema(description = "")
	public Boolean isIsKycRequired() {
		return isKycRequired;
	}

	public void setIsKycRequired(Boolean isKycRequired) {
		this.isKycRequired = isKycRequired;
	}

	public BrokerDetails showSwaps(Boolean showSwaps) {
		this.showSwaps = showSwaps;
		return this;
	}

	/**
	 * Get showSwaps
	 *
	 * @return showSwaps
	 **/
	@Schema(description = "")
	public Boolean isShowSwaps() {
		return showSwaps;
	}

	public void setShowSwaps(Boolean showSwaps) {
		this.showSwaps = showSwaps;
	}

	public BrokerDetails showTickets(Boolean showTickets) {
		this.showTickets = showTickets;
		return this;
	}

	/**
	 * Get showTickets
	 *
	 * @return showTickets
	 **/
	@Schema(description = "")
	public Boolean isShowTickets() {
		return showTickets;
	}

	public void setShowTickets(Boolean showTickets) {
		this.showTickets = showTickets;
	}

	public BrokerDetails showCommissionRebate(Boolean showCommissionRebate) {
		this.showCommissionRebate = showCommissionRebate;
		return this;
	}

	/**
	 * Get showCommissionRebate
	 *
	 * @return showCommissionRebate
	 **/
	@Schema(description = "")
	public Boolean isShowCommissionRebate() {
		return showCommissionRebate;
	}

	public void setShowCommissionRebate(Boolean showCommissionRebate) {
		this.showCommissionRebate = showCommissionRebate;
	}

	public BrokerDetails isKycRequiredSometime(Boolean isKycRequiredSometime) {
		this.isKycRequiredSometime = isKycRequiredSometime;
		return this;
	}

	/**
	 * Get isKycRequiredSometime
	 *
	 * @return isKycRequiredSometime
	 **/
	@Schema(description = "")
	public Boolean isIsKycRequiredSometime() {
		return isKycRequiredSometime;
	}

	public void setIsKycRequiredSometime(Boolean isKycRequiredSometime) {
		this.isKycRequiredSometime = isKycRequiredSometime;
	}

	public BrokerDetails showSwapsSometime(Boolean showSwapsSometime) {
		this.showSwapsSometime = showSwapsSometime;
		return this;
	}

	/**
	 * Get showSwapsSometime
	 *
	 * @return showSwapsSometime
	 **/
	@Schema(description = "")
	public Boolean isShowSwapsSometime() {
		return showSwapsSometime;
	}

	public void setShowSwapsSometime(Boolean showSwapsSometime) {
		this.showSwapsSometime = showSwapsSometime;
	}

	public BrokerDetails showTicketsSometime(Boolean showTicketsSometime) {
		this.showTicketsSometime = showTicketsSometime;
		return this;
	}

	/**
	 * Get showTicketsSometime
	 *
	 * @return showTicketsSometime
	 **/
	@Schema(description = "")
	public Boolean isShowTicketsSometime() {
		return showTicketsSometime;
	}

	public void setShowTicketsSometime(Boolean showTicketsSometime) {
		this.showTicketsSometime = showTicketsSometime;
	}

	public BrokerDetails showCommissionRebateSometime(Boolean showCommissionRebateSometime) {
		this.showCommissionRebateSometime = showCommissionRebateSometime;
		return this;
	}

	/**
	 * Get showCommissionRebateSometime
	 *
	 * @return showCommissionRebateSometime
	 **/
	@Schema(description = "")
	public Boolean isShowCommissionRebateSometime() {
		return showCommissionRebateSometime;
	}

	public void setShowCommissionRebateSometime(Boolean showCommissionRebateSometime) {
		this.showCommissionRebateSometime = showCommissionRebateSometime;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BrokerDetails brokerDetails = (BrokerDetails) o;
		return Objects.equals(this.logo, brokerDetails.logo) &&
				Objects.equals(this.name, brokerDetails.name) &&
				Objects.equals(this.isKycRequired, brokerDetails.isKycRequired) &&
				Objects.equals(this.showSwaps, brokerDetails.showSwaps) &&
				Objects.equals(this.showTickets, brokerDetails.showTickets) &&
				Objects.equals(this.showCommissionRebate, brokerDetails.showCommissionRebate) &&
				Objects.equals(this.isKycRequiredSometime, brokerDetails.isKycRequiredSometime) &&
				Objects.equals(this.showSwapsSometime, brokerDetails.showSwapsSometime) &&
				Objects.equals(this.showTicketsSometime, brokerDetails.showTicketsSometime) &&
				Objects.equals(this.showCommissionRebateSometime, brokerDetails.showCommissionRebateSometime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(logo, name, isKycRequired, showSwaps, showTickets, showCommissionRebate, isKycRequiredSometime, showSwapsSometime, showTicketsSometime, showCommissionRebateSometime);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BrokerDetails {\n");

		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    isKycRequired: ").append(toIndentedString(isKycRequired)).append("\n");
		sb.append("    showSwaps: ").append(toIndentedString(showSwaps)).append("\n");
		sb.append("    showTickets: ").append(toIndentedString(showTickets)).append("\n");
		sb.append("    showCommissionRebate: ").append(toIndentedString(showCommissionRebate)).append("\n");
		sb.append("    isKycRequiredSometime: ").append(toIndentedString(isKycRequiredSometime)).append("\n");
		sb.append("    showSwapsSometime: ").append(toIndentedString(showSwapsSometime)).append("\n");
		sb.append("    showTicketsSometime: ").append(toIndentedString(showTicketsSometime)).append("\n");
		sb.append("    showCommissionRebateSometime: ").append(toIndentedString(showCommissionRebateSometime)).append("\n");
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
		out.writeValue(logo);
		out.writeValue(name);
		out.writeValue(isKycRequired);
		out.writeValue(showSwaps);
		out.writeValue(showTickets);
		out.writeValue(showCommissionRebate);
		out.writeValue(isKycRequiredSometime);
		out.writeValue(showSwapsSometime);
		out.writeValue(showTicketsSometime);
		out.writeValue(showCommissionRebateSometime);
	}

	public int describeContents() {
		return 0;
	}
}
