/*
 * Core API v1.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PersonalFundDetailsFull
 */

public class PersonalFundDetailsFull
{
	@SerializedName("withdrawPercent")
	private Double withdrawPercent = null;

	@SerializedName("canReallocate")
	private Boolean canReallocate = null;

	@SerializedName("possibleReallocationTime")
	private DateTime possibleReallocationTime = null;

	@SerializedName("isFavorite")
	private Boolean isFavorite = null;

	@SerializedName("isInvested")
	private Boolean isInvested = null;

	@SerializedName("isOwnProgram")
	private Boolean isOwnProgram = null;

	@SerializedName("canCloseProgram")
	private Boolean canCloseProgram = null;

	@SerializedName("isFinishing")
	private Boolean isFinishing = null;

	@SerializedName("canInvest")
	private Boolean canInvest = null;

	@SerializedName("canWithdraw")
	private Boolean canWithdraw = null;

	@SerializedName("canClosePeriod")
	private Boolean canClosePeriod = null;

	@SerializedName("hasNotifications")
	private Boolean hasNotifications = null;

	@SerializedName("value")
	private Double value = null;

	@SerializedName("profit")
	private Double profit = null;

	@SerializedName("invested")
	private Double invested = null;

	@SerializedName("pendingInput")
	private Double pendingInput = null;

	@SerializedName("pendingOutput")
	private Double pendingOutput = null;

	@SerializedName("status")
	private StatusEnum status = null;

	@SerializedName("isFollowSignals")
	private Boolean isFollowSignals = null;

	@SerializedName("canMakeSignalProvider")
	private Boolean canMakeSignalProvider = null;

	public PersonalFundDetailsFull withdrawPercent(Double withdrawPercent) {
		this.withdrawPercent = withdrawPercent;
		return this;
	}

	/**
	 * Get withdrawPercent
	 *
	 * @return withdrawPercent
	 **/
	@ApiModelProperty(value = "")
	public Double getWithdrawPercent() {
		return withdrawPercent;
	}

	public void setWithdrawPercent(Double withdrawPercent) {
		this.withdrawPercent = withdrawPercent;
	}

	public PersonalFundDetailsFull canReallocate(Boolean canReallocate) {
		this.canReallocate = canReallocate;
		return this;
	}

	/**
	 * Get canReallocate
	 *
	 * @return canReallocate
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanReallocate() {
		return canReallocate;
	}

	public void setCanReallocate(Boolean canReallocate) {
		this.canReallocate = canReallocate;
	}

	public PersonalFundDetailsFull possibleReallocationTime(DateTime possibleReallocationTime) {
		this.possibleReallocationTime = possibleReallocationTime;
		return this;
	}

	/**
	 * Get possibleReallocationTime
	 *
	 * @return possibleReallocationTime
	 **/
	@ApiModelProperty(value = "")
	public DateTime getPossibleReallocationTime() {
		return possibleReallocationTime;
	}

	public void setPossibleReallocationTime(DateTime possibleReallocationTime) {
		this.possibleReallocationTime = possibleReallocationTime;
	}

	public PersonalFundDetailsFull isFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
		return this;
	}

	/**
	 * Get isFavorite
	 *
	 * @return isFavorite
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public PersonalFundDetailsFull isInvested(Boolean isInvested) {
		this.isInvested = isInvested;
		return this;
	}

	/**
	 * Get isInvested
	 *
	 * @return isInvested
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsInvested() {
		return isInvested;
	}

	public void setIsInvested(Boolean isInvested) {
		this.isInvested = isInvested;
	}

	public PersonalFundDetailsFull isOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
		return this;
	}

	/**
	 * Get isOwnProgram
	 *
	 * @return isOwnProgram
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsOwnProgram() {
		return isOwnProgram;
	}

	public void setIsOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
	}

	public PersonalFundDetailsFull canCloseProgram(Boolean canCloseProgram) {
		this.canCloseProgram = canCloseProgram;
		return this;
	}

	/**
	 * Get canCloseProgram
	 *
	 * @return canCloseProgram
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanCloseProgram() {
		return canCloseProgram;
	}

	public void setCanCloseProgram(Boolean canCloseProgram) {
		this.canCloseProgram = canCloseProgram;
	}

	public PersonalFundDetailsFull isFinishing(Boolean isFinishing) {
		this.isFinishing = isFinishing;
		return this;
	}

	/**
	 * Get isFinishing
	 *
	 * @return isFinishing
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsFinishing() {
		return isFinishing;
	}

	public void setIsFinishing(Boolean isFinishing) {
		this.isFinishing = isFinishing;
	}

	public PersonalFundDetailsFull canInvest(Boolean canInvest) {
		this.canInvest = canInvest;
		return this;
	}

	/**
	 * Get canInvest
	 *
	 * @return canInvest
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanInvest() {
		return canInvest;
	}

	public void setCanInvest(Boolean canInvest) {
		this.canInvest = canInvest;
	}

	public PersonalFundDetailsFull canWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
		return this;
	}

	/**
	 * Get canWithdraw
	 *
	 * @return canWithdraw
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanWithdraw() {
		return canWithdraw;
	}

	public void setCanWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	public PersonalFundDetailsFull canClosePeriod(Boolean canClosePeriod) {
		this.canClosePeriod = canClosePeriod;
		return this;
	}

	/**
	 * Get canClosePeriod
	 *
	 * @return canClosePeriod
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanClosePeriod() {
		return canClosePeriod;
	}

	public void setCanClosePeriod(Boolean canClosePeriod) {
		this.canClosePeriod = canClosePeriod;
	}

	public PersonalFundDetailsFull hasNotifications(Boolean hasNotifications) {
		this.hasNotifications = hasNotifications;
		return this;
	}

	/**
	 * Get hasNotifications
	 *
	 * @return hasNotifications
	 **/
	@ApiModelProperty(value = "")
	public Boolean isHasNotifications() {
		return hasNotifications;
	}

	public void setHasNotifications(Boolean hasNotifications) {
		this.hasNotifications = hasNotifications;
	}

	public PersonalFundDetailsFull value(Double value) {
		this.value = value;
		return this;
	}

	/**
	 * Get value
	 *
	 * @return value
	 **/
	@ApiModelProperty(value = "")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public PersonalFundDetailsFull profit(Double profit) {
		this.profit = profit;
		return this;
	}

	/**
	 * Get profit
	 *
	 * @return profit
	 **/
	@ApiModelProperty(value = "")
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public PersonalFundDetailsFull invested(Double invested) {
		this.invested = invested;
		return this;
	}

	/**
	 * Get invested
	 *
	 * @return invested
	 **/
	@ApiModelProperty(value = "")
	public Double getInvested() {
		return invested;
	}

	public void setInvested(Double invested) {
		this.invested = invested;
	}

	public PersonalFundDetailsFull pendingInput(Double pendingInput) {
		this.pendingInput = pendingInput;
		return this;
	}

	/**
	 * Get pendingInput
	 *
	 * @return pendingInput
	 **/
	@ApiModelProperty(value = "")
	public Double getPendingInput() {
		return pendingInput;
	}

	public void setPendingInput(Double pendingInput) {
		this.pendingInput = pendingInput;
	}

	public PersonalFundDetailsFull pendingOutput(Double pendingOutput) {
		this.pendingOutput = pendingOutput;
		return this;
	}

	/**
	 * Get pendingOutput
	 *
	 * @return pendingOutput
	 **/
	@ApiModelProperty(value = "")
	public Double getPendingOutput() {
		return pendingOutput;
	}

	public void setPendingOutput(Double pendingOutput) {
		this.pendingOutput = pendingOutput;
	}

	public PersonalFundDetailsFull status(StatusEnum status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@ApiModelProperty(value = "")
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public PersonalFundDetailsFull isFollowSignals(Boolean isFollowSignals) {
		this.isFollowSignals = isFollowSignals;
		return this;
	}

	/**
	 * Get isFollowSignals
	 *
	 * @return isFollowSignals
	 **/
	@ApiModelProperty(value = "")
	public Boolean isIsFollowSignals() {
		return isFollowSignals;
	}

	public void setIsFollowSignals(Boolean isFollowSignals) {
		this.isFollowSignals = isFollowSignals;
	}

	public PersonalFundDetailsFull canMakeSignalProvider(Boolean canMakeSignalProvider) {
		this.canMakeSignalProvider = canMakeSignalProvider;
		return this;
	}

	/**
	 * Get canMakeSignalProvider
	 *
	 * @return canMakeSignalProvider
	 **/
	@ApiModelProperty(value = "")
	public Boolean isCanMakeSignalProvider() {
		return canMakeSignalProvider;
	}

	public void setCanMakeSignalProvider(Boolean canMakeSignalProvider) {
		this.canMakeSignalProvider = canMakeSignalProvider;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonalFundDetailsFull personalFundDetailsFull = (PersonalFundDetailsFull) o;
		return Objects.equals(this.withdrawPercent, personalFundDetailsFull.withdrawPercent) &&
				Objects.equals(this.canReallocate, personalFundDetailsFull.canReallocate) &&
				Objects.equals(this.possibleReallocationTime, personalFundDetailsFull.possibleReallocationTime) &&
				Objects.equals(this.isFavorite, personalFundDetailsFull.isFavorite) &&
				Objects.equals(this.isInvested, personalFundDetailsFull.isInvested) &&
				Objects.equals(this.isOwnProgram, personalFundDetailsFull.isOwnProgram) &&
				Objects.equals(this.canCloseProgram, personalFundDetailsFull.canCloseProgram) &&
				Objects.equals(this.isFinishing, personalFundDetailsFull.isFinishing) &&
				Objects.equals(this.canInvest, personalFundDetailsFull.canInvest) &&
				Objects.equals(this.canWithdraw, personalFundDetailsFull.canWithdraw) &&
				Objects.equals(this.canClosePeriod, personalFundDetailsFull.canClosePeriod) &&
				Objects.equals(this.hasNotifications, personalFundDetailsFull.hasNotifications) &&
				Objects.equals(this.value, personalFundDetailsFull.value) &&
				Objects.equals(this.profit, personalFundDetailsFull.profit) &&
				Objects.equals(this.invested, personalFundDetailsFull.invested) &&
				Objects.equals(this.pendingInput, personalFundDetailsFull.pendingInput) &&
				Objects.equals(this.pendingOutput, personalFundDetailsFull.pendingOutput) &&
				Objects.equals(this.status, personalFundDetailsFull.status) &&
				Objects.equals(this.isFollowSignals, personalFundDetailsFull.isFollowSignals) &&
				Objects.equals(this.canMakeSignalProvider, personalFundDetailsFull.canMakeSignalProvider);
	}

	@Override
	public int hashCode() {
		return Objects.hash(withdrawPercent, canReallocate, possibleReallocationTime, isFavorite, isInvested, isOwnProgram, canCloseProgram, isFinishing, canInvest, canWithdraw, canClosePeriod, hasNotifications, value, profit, invested, pendingInput, pendingOutput, status, isFollowSignals, canMakeSignalProvider);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonalFundDetailsFull {\n");

		sb.append("    withdrawPercent: ").append(toIndentedString(withdrawPercent)).append("\n");
		sb.append("    canReallocate: ").append(toIndentedString(canReallocate)).append("\n");
		sb.append("    possibleReallocationTime: ").append(toIndentedString(possibleReallocationTime)).append("\n");
		sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
		sb.append("    isInvested: ").append(toIndentedString(isInvested)).append("\n");
		sb.append("    isOwnProgram: ").append(toIndentedString(isOwnProgram)).append("\n");
		sb.append("    canCloseProgram: ").append(toIndentedString(canCloseProgram)).append("\n");
		sb.append("    isFinishing: ").append(toIndentedString(isFinishing)).append("\n");
		sb.append("    canInvest: ").append(toIndentedString(canInvest)).append("\n");
		sb.append("    canWithdraw: ").append(toIndentedString(canWithdraw)).append("\n");
		sb.append("    canClosePeriod: ").append(toIndentedString(canClosePeriod)).append("\n");
		sb.append("    hasNotifications: ").append(toIndentedString(hasNotifications)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
		sb.append("    invested: ").append(toIndentedString(invested)).append("\n");
		sb.append("    pendingInput: ").append(toIndentedString(pendingInput)).append("\n");
		sb.append("    pendingOutput: ").append(toIndentedString(pendingOutput)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    isFollowSignals: ").append(toIndentedString(isFollowSignals)).append("\n");
		sb.append("    canMakeSignalProvider: ").append(toIndentedString(canMakeSignalProvider)).append("\n");
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

	/**
	 * Gets or Sets status
	 */
	@JsonAdapter(StatusEnum.Adapter.class)
	public enum StatusEnum
	{
		PENDING("Pending"),

		ACTIVE("Active"),

		INVESTING("Investing"),

		WITHDRAWING("Withdrawing"),

		ENDED("Ended");

		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<StatusEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public StatusEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return StatusEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

