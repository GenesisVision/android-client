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

	@SerializedName("canChangeReinvest")
	private Boolean canChangeReinvest = null;

	@SerializedName("share")
  private Double share = null;

  @SerializedName("value")
  private Double value = null;

  @SerializedName("profit")
  private Double profit = null;

  @SerializedName("profitPercent")
  private Double profitPercent = null;

  @SerializedName("invested")
  private Double invested = null;

  @SerializedName("successFeePersonal")
  private Double successFeePersonal = null;

	@SerializedName("managementFeePersonal")
	private Double managementFeePersonal = null;

	@SerializedName("status")
	private AssetInvestmentStatus status = null;

	@SerializedName("isAutoJoin")
	private Boolean isAutoJoin = null;

	public PersonalInvestingProgramDetailsList() {
	}

	PersonalInvestingProgramDetailsList(Parcel in) {
		isOwnAsset = (Boolean) in.readValue(null);
		isFavorite = (Boolean) in.readValue(null);
		isReinvest = (Boolean) in.readValue(null);
		isAutoJoin = (Boolean) in.readValue(null);
		canInvest = (Boolean) in.readValue(null);
		canWithdraw = (Boolean) in.readValue(null);
		canChangeReinvest = (Boolean) in.readValue(null);
		share = (Double) in.readValue(null);
		value = (Double) in.readValue(null);
		profit = (Double) in.readValue(null);
		profitPercent = (Double) in.readValue(null);
		invested = (Double) in.readValue(null);
		successFeePersonal = (Double) in.readValue(null);
		managementFeePersonal = (Double) in.readValue(null);
		status = (AssetInvestmentStatus) in.readValue(AssetInvestmentStatus.class.getClassLoader());
	}

	public PersonalInvestingProgramDetailsList isOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
		return this;
	}

	public void setIsOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
	}

	public PersonalInvestingProgramDetailsList isFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
		return this;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public PersonalInvestingProgramDetailsList isReinvest(Boolean isReinvest) {
		this.isReinvest = isReinvest;
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

	public void setIsReinvest(Boolean isReinvest) {
		this.isReinvest = isReinvest;
	}

	public PersonalInvestingProgramDetailsList isAutoJoin(Boolean isAutoJoin) {
		this.isAutoJoin = isAutoJoin;
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

	public void setIsAutoJoin(Boolean isAutoJoin) {
		this.isAutoJoin = isAutoJoin;
	}

	public PersonalInvestingProgramDetailsList canInvest(Boolean canInvest) {
		this.canInvest = canInvest;
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

	public void setCanInvest(Boolean canInvest) {
		this.canInvest = canInvest;
	}

	public PersonalInvestingProgramDetailsList canWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
		return this;
	}

	/**
	 * Get isAutoJoin
	 *
	 * @return isAutoJoin
	 **/
	@Schema(description = "")
	public Boolean isIsAutoJoin() {
		return isAutoJoin;
	}

	public void setCanWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	public PersonalInvestingProgramDetailsList canChangeReinvest(Boolean canChangeReinvest) {
		this.canChangeReinvest = canChangeReinvest;
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

	public void setCanChangeReinvest(Boolean canChangeReinvest) {
		this.canChangeReinvest = canChangeReinvest;
	}

	public PersonalInvestingProgramDetailsList share(Double share) {
		this.share = share;
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

	public PersonalInvestingProgramDetailsList value(Double value) {
		this.value = value;
		return this;
	}

	/**
	 * Get canChangeReinvest
	 *
	 * @return canChangeReinvest
	 **/
	@Schema(description = "")
	public Boolean isCanChangeReinvest() {
		return canChangeReinvest;
	}

	public PersonalInvestingProgramDetailsList profit(Double profit) {
		this.profit = profit;
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

	public PersonalInvestingProgramDetailsList profitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
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

	public PersonalInvestingProgramDetailsList invested(Double invested) {
		this.invested = invested;
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

	public PersonalInvestingProgramDetailsList successFeePersonal(Double successFeePersonal) {
		this.successFeePersonal = successFeePersonal;
		return this;
	}

	/**
	 * Get profitPercent
	 *
	 * @return profitPercent
	 **/
	@Schema(description = "")
	public Double getProfitPercent() {
		return profitPercent;
	}

	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}

	public PersonalInvestingProgramDetailsList managementFeePersonal(Double managementFeePersonal) {
		this.managementFeePersonal = managementFeePersonal;
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
	 * Get successFeePersonal
	 *
	 * @return successFeePersonal
	 **/
	@Schema(description = "")
	public Double getSuccessFeePersonal() {
		return successFeePersonal;
	}

	public void setSuccessFeePersonal(Double successFeePersonal) {
		this.successFeePersonal = successFeePersonal;
	}

	/**
	 * Get managementFeePersonal
	 *
	 * @return managementFeePersonal
	 **/
	@Schema(description = "")
	public Double getManagementFeePersonal() {
		return managementFeePersonal;
	}

	public void setManagementFeePersonal(Double managementFeePersonal) {
		this.managementFeePersonal = managementFeePersonal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isOwnAsset, isFavorite, isReinvest, isAutoJoin, canInvest, canWithdraw, canChangeReinvest, share, value, profit, profitPercent, invested, successFeePersonal, managementFeePersonal, status);
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
				Objects.equals(this.isAutoJoin, personalInvestingProgramDetailsList.isAutoJoin) &&
				Objects.equals(this.canInvest, personalInvestingProgramDetailsList.canInvest) &&
				Objects.equals(this.canWithdraw, personalInvestingProgramDetailsList.canWithdraw) &&
				Objects.equals(this.canChangeReinvest, personalInvestingProgramDetailsList.canChangeReinvest) &&
				Objects.equals(this.share, personalInvestingProgramDetailsList.share) &&
				Objects.equals(this.value, personalInvestingProgramDetailsList.value) &&
				Objects.equals(this.profit, personalInvestingProgramDetailsList.profit) &&
				Objects.equals(this.profitPercent, personalInvestingProgramDetailsList.profitPercent) &&
				Objects.equals(this.invested, personalInvestingProgramDetailsList.invested) &&
				Objects.equals(this.successFeePersonal, personalInvestingProgramDetailsList.successFeePersonal) &&
				Objects.equals(this.managementFeePersonal, personalInvestingProgramDetailsList.managementFeePersonal) &&
				Objects.equals(this.status, personalInvestingProgramDetailsList.status);
	}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalInvestingProgramDetailsList {\n");

    sb.append("    isOwnAsset: ").append(toIndentedString(isOwnAsset)).append("\n");
    sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
    sb.append("    isReinvest: ").append(toIndentedString(isReinvest)).append("\n");
    sb.append("    isAutoJoin: ").append(toIndentedString(isAutoJoin)).append("\n");
    sb.append("    canInvest: ").append(toIndentedString(canInvest)).append("\n");
    sb.append("    canWithdraw: ").append(toIndentedString(canWithdraw)).append("\n");
    sb.append("    canChangeReinvest: ").append(toIndentedString(canChangeReinvest)).append("\n");
	  sb.append("    share: ").append(toIndentedString(share)).append("\n");
	  sb.append("    value: ").append(toIndentedString(value)).append("\n");
	  sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
	  sb.append("    profitPercent: ").append(toIndentedString(profitPercent)).append("\n");
	  sb.append("    invested: ").append(toIndentedString(invested)).append("\n");
	  sb.append("    successFeePersonal: ").append(toIndentedString(successFeePersonal)).append("\n");
	  sb.append("    managementFeePersonal: ").append(toIndentedString(managementFeePersonal)).append("\n");
	  sb.append("    status: ").append(toIndentedString(status)).append("\n");
	  sb.append("}");
	  return sb.toString();
  }

	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(isOwnAsset);
		out.writeValue(isFavorite);
		out.writeValue(isReinvest);
		out.writeValue(isAutoJoin);
		out.writeValue(canInvest);
		out.writeValue(canWithdraw);
		out.writeValue(canChangeReinvest);
		out.writeValue(share);
		out.writeValue(value);
		out.writeValue(profit);
		out.writeValue(profitPercent);
		out.writeValue(invested);
		out.writeValue(successFeePersonal);
		out.writeValue(managementFeePersonal);
		out.writeValue(status);
	}

	public int describeContents() {
		return 0;
	}
}
