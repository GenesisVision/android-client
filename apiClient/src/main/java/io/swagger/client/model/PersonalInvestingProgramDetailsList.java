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
 * PersonalInvestingProgramDetailsList
 */


public class PersonalInvestingProgramDetailsList implements Parcelable
{
	public static final Parcelable.Creator<PersonalInvestingProgramDetailsList> CREATOR = new Parcelable.Creator<PersonalInvestingProgramDetailsList>()
	{
		public PersonalInvestingProgramDetailsList createFromParcel(Parcel in) {
			return new PersonalInvestingProgramDetailsList(in);
		}

		public PersonalInvestingProgramDetailsList[] newArray(int size) {
			return new PersonalInvestingProgramDetailsList[size];
		}
	};

	@SerializedName("isOwnAsset")
	private Boolean isOwnAsset = null;

	@SerializedName("isFavorite")
	private Boolean isFavorite = null;

	@SerializedName("isReinvest")
	private Boolean isReinvest = null;

	@SerializedName("canInvest")
	private Boolean canInvest = null;

	@SerializedName("canWithdraw")
	private Boolean canWithdraw = null;

	@SerializedName("share")
	private Double share = null;

	@SerializedName("value")
	private Double value = null;

	@SerializedName("profit")
	private Double profit = null;

	@SerializedName("invested")
	private Double invested = null;

	@SerializedName("status")
	private AssetInvestmentStatus status = null;

	public PersonalInvestingProgramDetailsList() {
	}

	PersonalInvestingProgramDetailsList(Parcel in) {
		isOwnAsset = (Boolean) in.readValue(null);
		isFavorite = (Boolean) in.readValue(null);
		isReinvest = (Boolean) in.readValue(null);
		canInvest = (Boolean) in.readValue(null);
		canWithdraw = (Boolean) in.readValue(null);
		share = (Double) in.readValue(null);
		value = (Double) in.readValue(null);
		profit = (Double) in.readValue(null);
		invested = (Double) in.readValue(null);
		status = (AssetInvestmentStatus) in.readValue(AssetInvestmentStatus.class.getClassLoader());
	}

	public PersonalInvestingProgramDetailsList isOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
		return this;
	}

	/**
	 * Get isOwnAsset
	 *
	 * @return isOwnAsset
	 **/
	@Schema(description = "")
	public Boolean isIsOwnAsset() {
		return isOwnAsset;
	}

	public void setIsOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
	}

	public PersonalInvestingProgramDetailsList isFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
		return this;
	}

	/**
	 * Get isFavorite
	 *
	 * @return isFavorite
	 **/
	@Schema(description = "")
	public Boolean isIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public PersonalInvestingProgramDetailsList isReinvest(Boolean isReinvest) {
		this.isReinvest = isReinvest;
		return this;
	}

	/**
	 * Get isReinvest
	 *
	 * @return isReinvest
	 **/
	@Schema(description = "")
	public Boolean isIsReinvest() {
		return isReinvest;
	}

	public void setIsReinvest(Boolean isReinvest) {
		this.isReinvest = isReinvest;
	}

	public PersonalInvestingProgramDetailsList canInvest(Boolean canInvest) {
		this.canInvest = canInvest;
		return this;
	}

	/**
	 * Get canInvest
	 *
	 * @return canInvest
	 **/
	@Schema(description = "")
	public Boolean isCanInvest() {
		return canInvest;
	}

	public void setCanInvest(Boolean canInvest) {
		this.canInvest = canInvest;
	}

	public PersonalInvestingProgramDetailsList canWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
		return this;
	}

	/**
	 * Get canWithdraw
	 *
	 * @return canWithdraw
	 **/
	@Schema(description = "")
	public Boolean isCanWithdraw() {
		return canWithdraw;
	}

	public void setCanWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	public PersonalInvestingProgramDetailsList share(Double share) {
		this.share = share;
		return this;
	}

	/**
	 * Get share
	 *
	 * @return share
	 **/
	@Schema(description = "")
	public Double getShare() {
		return share;
	}

	public void setShare(Double share) {
		this.share = share;
	}

	public PersonalInvestingProgramDetailsList value(Double value) {
		this.value = value;
		return this;
	}

	/**
	 * Get value
	 *
	 * @return value
	 **/
	@Schema(description = "")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public PersonalInvestingProgramDetailsList profit(Double profit) {
		this.profit = profit;
		return this;
	}

	/**
	 * Get profit
	 *
	 * @return profit
	 **/
	@Schema(description = "")
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public PersonalInvestingProgramDetailsList invested(Double invested) {
		this.invested = invested;
		return this;
	}

	/**
	 * Get invested
	 *
	 * @return invested
	 **/
	@Schema(description = "")
	public Double getInvested() {
		return invested;
	}

	public void setInvested(Double invested) {
		this.invested = invested;
	}

	public PersonalInvestingProgramDetailsList status(AssetInvestmentStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@Schema(description = "")
	public AssetInvestmentStatus getStatus() {
		return status;
	}

	public void setStatus(AssetInvestmentStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonalInvestingProgramDetailsList personalInvestingProgramDetailsList = (PersonalInvestingProgramDetailsList) o;
		return Objects.equals(this.isOwnAsset, personalInvestingProgramDetailsList.isOwnAsset) &&
				Objects.equals(this.isFavorite, personalInvestingProgramDetailsList.isFavorite) &&
				Objects.equals(this.isReinvest, personalInvestingProgramDetailsList.isReinvest) &&
				Objects.equals(this.canInvest, personalInvestingProgramDetailsList.canInvest) &&
				Objects.equals(this.canWithdraw, personalInvestingProgramDetailsList.canWithdraw) &&
				Objects.equals(this.share, personalInvestingProgramDetailsList.share) &&
				Objects.equals(this.value, personalInvestingProgramDetailsList.value) &&
				Objects.equals(this.profit, personalInvestingProgramDetailsList.profit) &&
				Objects.equals(this.invested, personalInvestingProgramDetailsList.invested) &&
				Objects.equals(this.status, personalInvestingProgramDetailsList.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isOwnAsset, isFavorite, isReinvest, canInvest, canWithdraw, share, value, profit, invested, status);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonalInvestingProgramDetailsList {\n");

		sb.append("    isOwnAsset: ").append(toIndentedString(isOwnAsset)).append("\n");
		sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
		sb.append("    isReinvest: ").append(toIndentedString(isReinvest)).append("\n");
		sb.append("    canInvest: ").append(toIndentedString(canInvest)).append("\n");
		sb.append("    canWithdraw: ").append(toIndentedString(canWithdraw)).append("\n");
		sb.append("    share: ").append(toIndentedString(share)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
		sb.append("    invested: ").append(toIndentedString(invested)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
		out.writeValue(isOwnAsset);
		out.writeValue(isFavorite);
		out.writeValue(isReinvest);
		out.writeValue(canInvest);
		out.writeValue(canWithdraw);
		out.writeValue(share);
		out.writeValue(value);
		out.writeValue(profit);
		out.writeValue(invested);
		out.writeValue(status);
	}

	public int describeContents() {
		return 0;
	}
}
