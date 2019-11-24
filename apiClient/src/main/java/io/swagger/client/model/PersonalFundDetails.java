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

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PersonalFundDetails
 */


public class PersonalFundDetails implements Parcelable
{
	public static final Parcelable.Creator<PersonalFundDetails> CREATOR = new Parcelable.Creator<PersonalFundDetails>()
	{
		public PersonalFundDetails createFromParcel(Parcel in) {
			return new PersonalFundDetails(in);
		}

		public PersonalFundDetails[] newArray(int size) {
			return new PersonalFundDetails[size];
		}
	};

	@SerializedName("isOwnAsset")
	private Boolean isOwnAsset = null;

	@SerializedName("isFavorite")
	private Boolean isFavorite = null;

	@SerializedName("isInvested")
	private Boolean isInvested = null;

	@SerializedName("canInvest")
	private Boolean canInvest = null;

	@SerializedName("canWithdraw")
	private Boolean canWithdraw = null;

	@SerializedName("ownerActions")
	private FundOwnerActions ownerActions = null;

	@SerializedName("hasNotifications")
	private Boolean hasNotifications = null;

	@SerializedName("value")
	private Double value = null;

	@SerializedName("status")
	private AssetInvestmentStatus status = null;

	@SerializedName("pendingInput")
	private Double pendingInput = null;

	@SerializedName("pendingOutput")
	private Double pendingOutput = null;

	@SerializedName("withdrawPercent")
	private Double withdrawPercent = null;

	@SerializedName("availableReallocationPercents")
	private Integer availableReallocationPercents = null;

	@SerializedName("nextReallocationPercents")
	private DateTime nextReallocationPercents = null;

	@SerializedName("exitFeePersonal")
	private Double exitFeePersonal = null;

	public PersonalFundDetails() {
	}

	PersonalFundDetails(Parcel in) {
		isOwnAsset = (Boolean) in.readValue(null);
		isFavorite = (Boolean) in.readValue(null);
		isInvested = (Boolean) in.readValue(null);
		canInvest = (Boolean) in.readValue(null);
		canWithdraw = (Boolean) in.readValue(null);
		ownerActions = (FundOwnerActions) in.readValue(FundOwnerActions.class.getClassLoader());
		hasNotifications = (Boolean) in.readValue(null);
		value = (Double) in.readValue(null);
		status = (AssetInvestmentStatus) in.readValue(AssetInvestmentStatus.class.getClassLoader());
		pendingInput = (Double) in.readValue(null);
		pendingOutput = (Double) in.readValue(null);
		withdrawPercent = (Double) in.readValue(null);
		availableReallocationPercents = (Integer) in.readValue(null);
		nextReallocationPercents = (DateTime) in.readValue(DateTime.class.getClassLoader());
		exitFeePersonal = (Double) in.readValue(null);
	}

	public PersonalFundDetails isOwnAsset(Boolean isOwnAsset) {
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

	public PersonalFundDetails isFavorite(Boolean isFavorite) {
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

	public PersonalFundDetails isInvested(Boolean isInvested) {
		this.isInvested = isInvested;
		return this;
	}

	/**
	 * Get isInvested
	 *
	 * @return isInvested
	 **/
	@Schema(description = "")
	public Boolean isIsInvested() {
		return isInvested;
	}

	public void setIsInvested(Boolean isInvested) {
		this.isInvested = isInvested;
	}

	public PersonalFundDetails canInvest(Boolean canInvest) {
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

	public PersonalFundDetails canWithdraw(Boolean canWithdraw) {
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

	public PersonalFundDetails ownerActions(FundOwnerActions ownerActions) {
		this.ownerActions = ownerActions;
		return this;
	}

	/**
	 * Get ownerActions
	 *
	 * @return ownerActions
	 **/
	@Schema(description = "")
	public FundOwnerActions getOwnerActions() {
		return ownerActions;
	}

	public void setOwnerActions(FundOwnerActions ownerActions) {
		this.ownerActions = ownerActions;
	}

	public PersonalFundDetails hasNotifications(Boolean hasNotifications) {
		this.hasNotifications = hasNotifications;
		return this;
	}

	/**
	 * Get hasNotifications
	 *
	 * @return hasNotifications
	 **/
	@Schema(description = "")
	public Boolean isHasNotifications() {
		return hasNotifications;
	}

	public void setHasNotifications(Boolean hasNotifications) {
		this.hasNotifications = hasNotifications;
	}

	public PersonalFundDetails value(Double value) {
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

	public PersonalFundDetails status(AssetInvestmentStatus status) {
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

	public PersonalFundDetails pendingInput(Double pendingInput) {
		this.pendingInput = pendingInput;
		return this;
	}

	/**
	 * Get pendingInput
	 *
	 * @return pendingInput
	 **/
	@Schema(description = "")
	public Double getPendingInput() {
		return pendingInput;
	}

	public void setPendingInput(Double pendingInput) {
		this.pendingInput = pendingInput;
	}

	public PersonalFundDetails pendingOutput(Double pendingOutput) {
		this.pendingOutput = pendingOutput;
		return this;
	}

	/**
	 * Get pendingOutput
	 *
	 * @return pendingOutput
	 **/
	@Schema(description = "")
	public Double getPendingOutput() {
		return pendingOutput;
	}

	public void setPendingOutput(Double pendingOutput) {
		this.pendingOutput = pendingOutput;
	}

	public PersonalFundDetails withdrawPercent(Double withdrawPercent) {
		this.withdrawPercent = withdrawPercent;
		return this;
	}

	/**
	 * Get withdrawPercent
	 *
	 * @return withdrawPercent
	 **/
	@Schema(description = "")
	public Double getWithdrawPercent() {
		return withdrawPercent;
	}

	public void setWithdrawPercent(Double withdrawPercent) {
		this.withdrawPercent = withdrawPercent;
	}

	public PersonalFundDetails availableReallocationPercents(Integer availableReallocationPercents) {
		this.availableReallocationPercents = availableReallocationPercents;
		return this;
	}

	/**
	 * Get availableReallocationPercents
	 *
	 * @return availableReallocationPercents
	 **/
	@Schema(description = "")
	public Integer getAvailableReallocationPercents() {
		return availableReallocationPercents;
	}

	public void setAvailableReallocationPercents(Integer availableReallocationPercents) {
		this.availableReallocationPercents = availableReallocationPercents;
	}

	public PersonalFundDetails nextReallocationPercents(DateTime nextReallocationPercents) {
		this.nextReallocationPercents = nextReallocationPercents;
		return this;
	}

	/**
	 * Get nextReallocationPercents
	 *
	 * @return nextReallocationPercents
	 **/
	@Schema(description = "")
	public DateTime getNextReallocationPercents() {
		return nextReallocationPercents;
	}

	public void setNextReallocationPercents(DateTime nextReallocationPercents) {
		this.nextReallocationPercents = nextReallocationPercents;
	}

	public PersonalFundDetails exitFeePersonal(Double exitFeePersonal) {
		this.exitFeePersonal = exitFeePersonal;
		return this;
	}

	/**
	 * Get exitFeePersonal
	 *
	 * @return exitFeePersonal
	 **/
	@Schema(description = "")
	public Double getExitFeePersonal() {
		return exitFeePersonal;
	}

	public void setExitFeePersonal(Double exitFeePersonal) {
		this.exitFeePersonal = exitFeePersonal;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonalFundDetails personalFundDetails = (PersonalFundDetails) o;
		return Objects.equals(this.isOwnAsset, personalFundDetails.isOwnAsset) &&
				Objects.equals(this.isFavorite, personalFundDetails.isFavorite) &&
				Objects.equals(this.isInvested, personalFundDetails.isInvested) &&
				Objects.equals(this.canInvest, personalFundDetails.canInvest) &&
				Objects.equals(this.canWithdraw, personalFundDetails.canWithdraw) &&
				Objects.equals(this.ownerActions, personalFundDetails.ownerActions) &&
				Objects.equals(this.hasNotifications, personalFundDetails.hasNotifications) &&
				Objects.equals(this.value, personalFundDetails.value) &&
				Objects.equals(this.status, personalFundDetails.status) &&
				Objects.equals(this.pendingInput, personalFundDetails.pendingInput) &&
				Objects.equals(this.pendingOutput, personalFundDetails.pendingOutput) &&
				Objects.equals(this.withdrawPercent, personalFundDetails.withdrawPercent) &&
				Objects.equals(this.availableReallocationPercents, personalFundDetails.availableReallocationPercents) &&
				Objects.equals(this.nextReallocationPercents, personalFundDetails.nextReallocationPercents) &&
				Objects.equals(this.exitFeePersonal, personalFundDetails.exitFeePersonal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isOwnAsset, isFavorite, isInvested, canInvest, canWithdraw, ownerActions, hasNotifications, value, status, pendingInput, pendingOutput, withdrawPercent, availableReallocationPercents, nextReallocationPercents, exitFeePersonal);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonalFundDetails {\n");

		sb.append("    isOwnAsset: ").append(toIndentedString(isOwnAsset)).append("\n");
		sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
		sb.append("    isInvested: ").append(toIndentedString(isInvested)).append("\n");
		sb.append("    canInvest: ").append(toIndentedString(canInvest)).append("\n");
		sb.append("    canWithdraw: ").append(toIndentedString(canWithdraw)).append("\n");
		sb.append("    ownerActions: ").append(toIndentedString(ownerActions)).append("\n");
		sb.append("    hasNotifications: ").append(toIndentedString(hasNotifications)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    pendingInput: ").append(toIndentedString(pendingInput)).append("\n");
		sb.append("    pendingOutput: ").append(toIndentedString(pendingOutput)).append("\n");
		sb.append("    withdrawPercent: ").append(toIndentedString(withdrawPercent)).append("\n");
		sb.append("    availableReallocationPercents: ").append(toIndentedString(availableReallocationPercents)).append("\n");
		sb.append("    nextReallocationPercents: ").append(toIndentedString(nextReallocationPercents)).append("\n");
		sb.append("    exitFeePersonal: ").append(toIndentedString(exitFeePersonal)).append("\n");
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
		out.writeValue(isInvested);
		out.writeValue(canInvest);
		out.writeValue(canWithdraw);
		out.writeValue(ownerActions);
		out.writeValue(hasNotifications);
		out.writeValue(value);
		out.writeValue(status);
		out.writeValue(pendingInput);
		out.writeValue(pendingOutput);
		out.writeValue(withdrawPercent);
		out.writeValue(availableReallocationPercents);
		out.writeValue(nextReallocationPercents);
		out.writeValue(exitFeePersonal);
	}

	public int describeContents() {
		return 0;
	}
}
