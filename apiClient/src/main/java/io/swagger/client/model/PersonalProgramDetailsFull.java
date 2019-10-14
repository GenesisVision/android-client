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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * PersonalProgramDetailsFull
 */


public class PersonalProgramDetailsFull implements Parcelable
{
	public static final Parcelable.Creator<PersonalProgramDetailsFull> CREATOR = new Parcelable.Creator<PersonalProgramDetailsFull>()
	{
		public PersonalProgramDetailsFull createFromParcel(Parcel in) {
			return new PersonalProgramDetailsFull(in);
		}

		public PersonalProgramDetailsFull[] newArray(int size) {
			return new PersonalProgramDetailsFull[size];
		}
	};

	@SerializedName("isReinvest")
	private Boolean isReinvest = null;

	@SerializedName("gvtValue")
	private Double gvtValue = null;

	@SerializedName("showTwoFactorButton")
	private Boolean showTwoFactorButton = null;

	@SerializedName("signalSubscription")
	private SignalSubscription signalSubscription = null;

	@SerializedName("login")
	private String login = null;

	@SerializedName("notificationAvailableToInvestId")
	private UUID notificationAvailableToInvestId = null;

	@SerializedName("canMakeSignalProvider")
	private Boolean canMakeSignalProvider = null;

	@SerializedName("canChangePassword")
	private Boolean canChangePassword = null;

	@SerializedName("migration")
	private MigrationRequest migration = null;

	@SerializedName("successFeePersonal")
	private Double successFeePersonal = null;

	@SerializedName("isFavorite")
	private Boolean isFavorite = null;

	@SerializedName("isInvested")
	private Boolean isInvested = null;

	@SerializedName("isOwnProgram")
	private Boolean isOwnProgram = null;

	@SerializedName("canCloseAsset")
	private Boolean canCloseAsset = null;

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

	@SerializedName("pendingOutputIsWithdrawAll")
	private Boolean pendingOutputIsWithdrawAll = null;

	@SerializedName("status")
	private StatusEnum status = null;

	public PersonalProgramDetailsFull() {
	}

	PersonalProgramDetailsFull(Parcel in) {
		isReinvest = (Boolean) in.readValue(null);
		gvtValue = (Double) in.readValue(null);
		showTwoFactorButton = (Boolean) in.readValue(null);
		signalSubscription = (SignalSubscription) in.readValue(SignalSubscription.class.getClassLoader());
		login = (String) in.readValue(null);
		notificationAvailableToInvestId = (UUID) in.readValue(UUID.class.getClassLoader());
		canMakeSignalProvider = (Boolean) in.readValue(null);
		canChangePassword = (Boolean) in.readValue(null);
		migration = (MigrationRequest) in.readValue(MigrationRequest.class.getClassLoader());
		successFeePersonal = (Double) in.readValue(null);
		isFavorite = (Boolean) in.readValue(null);
		isInvested = (Boolean) in.readValue(null);
		isOwnProgram = (Boolean) in.readValue(null);
		canCloseAsset = (Boolean) in.readValue(null);
		isFinishing = (Boolean) in.readValue(null);
		canInvest = (Boolean) in.readValue(null);
		canWithdraw = (Boolean) in.readValue(null);
		canClosePeriod = (Boolean) in.readValue(null);
		hasNotifications = (Boolean) in.readValue(null);
		value = (Double) in.readValue(null);
		profit = (Double) in.readValue(null);
		invested = (Double) in.readValue(null);
		pendingInput = (Double) in.readValue(null);
		pendingOutput = (Double) in.readValue(null);
		pendingOutputIsWithdrawAll = (Boolean) in.readValue(null);
		status = (StatusEnum) in.readValue(null);
	}

	public PersonalProgramDetailsFull isReinvest(Boolean isReinvest) {
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

	public PersonalProgramDetailsFull gvtValue(Double gvtValue) {
		this.gvtValue = gvtValue;
		return this;
	}

	/**
	 * Get gvtValue
	 *
	 * @return gvtValue
	 **/
	@Schema(description = "")
	public Double getGvtValue() {
		return gvtValue;
	}

	public void setGvtValue(Double gvtValue) {
		this.gvtValue = gvtValue;
	}

	public PersonalProgramDetailsFull showTwoFactorButton(Boolean showTwoFactorButton) {
		this.showTwoFactorButton = showTwoFactorButton;
		return this;
	}

	/**
	 * Get showTwoFactorButton
	 *
	 * @return showTwoFactorButton
	 **/
	@Schema(description = "")
	public Boolean isShowTwoFactorButton() {
		return showTwoFactorButton;
	}

	public void setShowTwoFactorButton(Boolean showTwoFactorButton) {
		this.showTwoFactorButton = showTwoFactorButton;
	}

	public PersonalProgramDetailsFull signalSubscription(SignalSubscription signalSubscription) {
		this.signalSubscription = signalSubscription;
		return this;
	}

	/**
	 * Get signalSubscription
	 *
	 * @return signalSubscription
	 **/
	@Schema(description = "")
	public SignalSubscription getSignalSubscription() {
		return signalSubscription;
	}

	public void setSignalSubscription(SignalSubscription signalSubscription) {
		this.signalSubscription = signalSubscription;
	}

	public PersonalProgramDetailsFull login(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Get login
	 *
	 * @return login
	 **/
	@Schema(description = "")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public PersonalProgramDetailsFull notificationAvailableToInvestId(UUID notificationAvailableToInvestId) {
		this.notificationAvailableToInvestId = notificationAvailableToInvestId;
		return this;
	}

	/**
	 * Get notificationAvailableToInvestId
	 *
	 * @return notificationAvailableToInvestId
	 **/
	@Schema(description = "")
	public UUID getNotificationAvailableToInvestId() {
		return notificationAvailableToInvestId;
	}

	public void setNotificationAvailableToInvestId(UUID notificationAvailableToInvestId) {
		this.notificationAvailableToInvestId = notificationAvailableToInvestId;
	}

	public PersonalProgramDetailsFull canMakeSignalProvider(Boolean canMakeSignalProvider) {
		this.canMakeSignalProvider = canMakeSignalProvider;
		return this;
	}

	/**
	 * Get canMakeSignalProvider
	 *
	 * @return canMakeSignalProvider
	 **/
	@Schema(description = "")
	public Boolean isCanMakeSignalProvider() {
		return canMakeSignalProvider;
	}

	public void setCanMakeSignalProvider(Boolean canMakeSignalProvider) {
		this.canMakeSignalProvider = canMakeSignalProvider;
	}

	public PersonalProgramDetailsFull canChangePassword(Boolean canChangePassword) {
		this.canChangePassword = canChangePassword;
		return this;
	}

	/**
	 * Get canChangePassword
	 *
	 * @return canChangePassword
	 **/
	@Schema(description = "")
	public Boolean isCanChangePassword() {
		return canChangePassword;
	}

	public void setCanChangePassword(Boolean canChangePassword) {
		this.canChangePassword = canChangePassword;
	}

	public PersonalProgramDetailsFull migration(MigrationRequest migration) {
		this.migration = migration;
		return this;
	}

	/**
	 * Get migration
	 *
	 * @return migration
	 **/
	@Schema(description = "")
	public MigrationRequest getMigration() {
		return migration;
	}

	public void setMigration(MigrationRequest migration) {
		this.migration = migration;
	}

	public PersonalProgramDetailsFull successFeePersonal(Double successFeePersonal) {
		this.successFeePersonal = successFeePersonal;
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

	public PersonalProgramDetailsFull isFavorite(Boolean isFavorite) {
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

	public PersonalProgramDetailsFull isInvested(Boolean isInvested) {
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

	public PersonalProgramDetailsFull isOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
		return this;
	}

	/**
	 * Get isOwnProgram
	 *
	 * @return isOwnProgram
	 **/
	@Schema(description = "")
	public Boolean isIsOwnProgram() {
		return isOwnProgram;
	}

	public void setIsOwnProgram(Boolean isOwnProgram) {
		this.isOwnProgram = isOwnProgram;
	}

	public PersonalProgramDetailsFull canCloseAsset(Boolean canCloseAsset) {
		this.canCloseAsset = canCloseAsset;
		return this;
	}

	/**
	 * Get canCloseAsset
	 *
	 * @return canCloseAsset
	 **/
	@Schema(description = "")
	public Boolean isCanCloseAsset() {
		return canCloseAsset;
	}

	public void setCanCloseAsset(Boolean canCloseAsset) {
		this.canCloseAsset = canCloseAsset;
	}

	public PersonalProgramDetailsFull isFinishing(Boolean isFinishing) {
		this.isFinishing = isFinishing;
		return this;
	}

	/**
	 * Get isFinishing
	 *
	 * @return isFinishing
	 **/
	@Schema(description = "")
	public Boolean isIsFinishing() {
		return isFinishing;
	}

	public void setIsFinishing(Boolean isFinishing) {
		this.isFinishing = isFinishing;
	}

	public PersonalProgramDetailsFull canInvest(Boolean canInvest) {
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

	public PersonalProgramDetailsFull canWithdraw(Boolean canWithdraw) {
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

	public PersonalProgramDetailsFull canClosePeriod(Boolean canClosePeriod) {
		this.canClosePeriod = canClosePeriod;
		return this;
	}

	/**
	 * Get canClosePeriod
	 *
	 * @return canClosePeriod
	 **/
	@Schema(description = "")
	public Boolean isCanClosePeriod() {
		return canClosePeriod;
	}

	public void setCanClosePeriod(Boolean canClosePeriod) {
		this.canClosePeriod = canClosePeriod;
	}

	public PersonalProgramDetailsFull hasNotifications(Boolean hasNotifications) {
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

	public PersonalProgramDetailsFull value(Double value) {
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

	public PersonalProgramDetailsFull profit(Double profit) {
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

	public PersonalProgramDetailsFull invested(Double invested) {
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

	public PersonalProgramDetailsFull pendingInput(Double pendingInput) {
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

	public PersonalProgramDetailsFull pendingOutput(Double pendingOutput) {
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

	public PersonalProgramDetailsFull pendingOutputIsWithdrawAll(Boolean pendingOutputIsWithdrawAll) {
		this.pendingOutputIsWithdrawAll = pendingOutputIsWithdrawAll;
		return this;
	}

	/**
	 * Get pendingOutputIsWithdrawAll
	 *
	 * @return pendingOutputIsWithdrawAll
	 **/
	@Schema(description = "")
	public Boolean isPendingOutputIsWithdrawAll() {
		return pendingOutputIsWithdrawAll;
	}

	public void setPendingOutputIsWithdrawAll(Boolean pendingOutputIsWithdrawAll) {
		this.pendingOutputIsWithdrawAll = pendingOutputIsWithdrawAll;
	}

	public PersonalProgramDetailsFull status(StatusEnum status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@Schema(description = "")
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
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
		PersonalProgramDetailsFull personalProgramDetailsFull = (PersonalProgramDetailsFull) o;
		return Objects.equals(this.isReinvest, personalProgramDetailsFull.isReinvest) &&
				Objects.equals(this.gvtValue, personalProgramDetailsFull.gvtValue) &&
				Objects.equals(this.showTwoFactorButton, personalProgramDetailsFull.showTwoFactorButton) &&
				Objects.equals(this.signalSubscription, personalProgramDetailsFull.signalSubscription) &&
				Objects.equals(this.login, personalProgramDetailsFull.login) &&
				Objects.equals(this.notificationAvailableToInvestId, personalProgramDetailsFull.notificationAvailableToInvestId) &&
				Objects.equals(this.canMakeSignalProvider, personalProgramDetailsFull.canMakeSignalProvider) &&
				Objects.equals(this.canChangePassword, personalProgramDetailsFull.canChangePassword) &&
				Objects.equals(this.migration, personalProgramDetailsFull.migration) &&
				Objects.equals(this.successFeePersonal, personalProgramDetailsFull.successFeePersonal) &&
				Objects.equals(this.isFavorite, personalProgramDetailsFull.isFavorite) &&
				Objects.equals(this.isInvested, personalProgramDetailsFull.isInvested) &&
				Objects.equals(this.isOwnProgram, personalProgramDetailsFull.isOwnProgram) &&
				Objects.equals(this.canCloseAsset, personalProgramDetailsFull.canCloseAsset) &&
				Objects.equals(this.isFinishing, personalProgramDetailsFull.isFinishing) &&
				Objects.equals(this.canInvest, personalProgramDetailsFull.canInvest) &&
				Objects.equals(this.canWithdraw, personalProgramDetailsFull.canWithdraw) &&
				Objects.equals(this.canClosePeriod, personalProgramDetailsFull.canClosePeriod) &&
				Objects.equals(this.hasNotifications, personalProgramDetailsFull.hasNotifications) &&
				Objects.equals(this.value, personalProgramDetailsFull.value) &&
				Objects.equals(this.profit, personalProgramDetailsFull.profit) &&
				Objects.equals(this.invested, personalProgramDetailsFull.invested) &&
				Objects.equals(this.pendingInput, personalProgramDetailsFull.pendingInput) &&
				Objects.equals(this.pendingOutput, personalProgramDetailsFull.pendingOutput) &&
				Objects.equals(this.pendingOutputIsWithdrawAll, personalProgramDetailsFull.pendingOutputIsWithdrawAll) &&
				Objects.equals(this.status, personalProgramDetailsFull.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isReinvest, gvtValue, showTwoFactorButton, signalSubscription, login, notificationAvailableToInvestId, canMakeSignalProvider, canChangePassword, migration, successFeePersonal, isFavorite, isInvested, isOwnProgram, canCloseAsset, isFinishing, canInvest, canWithdraw, canClosePeriod, hasNotifications, value, profit, invested, pendingInput, pendingOutput, pendingOutputIsWithdrawAll, status);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonalProgramDetailsFull {\n");

		sb.append("    isReinvest: ").append(toIndentedString(isReinvest)).append("\n");
		sb.append("    gvtValue: ").append(toIndentedString(gvtValue)).append("\n");
		sb.append("    showTwoFactorButton: ").append(toIndentedString(showTwoFactorButton)).append("\n");
		sb.append("    signalSubscription: ").append(toIndentedString(signalSubscription)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    notificationAvailableToInvestId: ").append(toIndentedString(notificationAvailableToInvestId)).append("\n");
		sb.append("    canMakeSignalProvider: ").append(toIndentedString(canMakeSignalProvider)).append("\n");
		sb.append("    canChangePassword: ").append(toIndentedString(canChangePassword)).append("\n");
		sb.append("    migration: ").append(toIndentedString(migration)).append("\n");
		sb.append("    successFeePersonal: ").append(toIndentedString(successFeePersonal)).append("\n");
		sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
		sb.append("    isInvested: ").append(toIndentedString(isInvested)).append("\n");
		sb.append("    isOwnProgram: ").append(toIndentedString(isOwnProgram)).append("\n");
		sb.append("    canCloseAsset: ").append(toIndentedString(canCloseAsset)).append("\n");
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
		sb.append("    pendingOutputIsWithdrawAll: ").append(toIndentedString(pendingOutputIsWithdrawAll)).append("\n");
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
		out.writeValue(isReinvest);
		out.writeValue(gvtValue);
		out.writeValue(showTwoFactorButton);
		out.writeValue(signalSubscription);
		out.writeValue(login);
		out.writeValue(notificationAvailableToInvestId);
		out.writeValue(canMakeSignalProvider);
		out.writeValue(canChangePassword);
		out.writeValue(migration);
		out.writeValue(successFeePersonal);
		out.writeValue(isFavorite);
		out.writeValue(isInvested);
		out.writeValue(isOwnProgram);
		out.writeValue(canCloseAsset);
		out.writeValue(isFinishing);
		out.writeValue(canInvest);
		out.writeValue(canWithdraw);
		out.writeValue(canClosePeriod);
		out.writeValue(hasNotifications);
		out.writeValue(value);
		out.writeValue(profit);
		out.writeValue(invested);
		out.writeValue(pendingInput);
		out.writeValue(pendingOutput);
		out.writeValue(pendingOutputIsWithdrawAll);
		out.writeValue(status);
	}

	public int describeContents() {
		return 0;
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
