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
 * TradingAccountOwnerActions
 */


public class TradingAccountOwnerActions implements Parcelable
{
	public static final Parcelable.Creator<TradingAccountOwnerActions> CREATOR = new Parcelable.Creator<TradingAccountOwnerActions>()
	{
		public TradingAccountOwnerActions createFromParcel(Parcel in) {
			return new TradingAccountOwnerActions(in);
		}

		public TradingAccountOwnerActions[] newArray(int size) {
			return new TradingAccountOwnerActions[size];
		}
	};

	@SerializedName("canClose")
	private Boolean canClose = null;

	@SerializedName("canClosePeriod")
	private Boolean canClosePeriod = null;

	@SerializedName("canChangePassword")
	private Boolean canChangePassword = null;

	@SerializedName("canMakeProgramFromPrivateTradingAccount")
	private Boolean canMakeProgramFromPrivateTradingAccount = null;

	@SerializedName("canMakePublicAccountFromPrivateTradingAccount")
	private Boolean canMakePublicAccountFromPrivateTradingAccount = null;

	@SerializedName("canMakeProgramFromPublicTradingAccount")
	private Boolean canMakeProgramFromPublicTradingAccount = null;

	@SerializedName("canMakeSignalProviderFromProgram")
	private Boolean canMakeSignalProviderFromProgram = null;

	@SerializedName("canEditSignalProviderSettings")
	private Boolean canEditSignalProviderSettings = null;

	public TradingAccountOwnerActions() {
	}

	TradingAccountOwnerActions(Parcel in) {
		canClose = (Boolean) in.readValue(null);
		canClosePeriod = (Boolean) in.readValue(null);
		canChangePassword = (Boolean) in.readValue(null);
		canMakeProgramFromPrivateTradingAccount = (Boolean) in.readValue(null);
		canMakePublicAccountFromPrivateTradingAccount = (Boolean) in.readValue(null);
		canMakeProgramFromPublicTradingAccount = (Boolean) in.readValue(null);
		canMakeSignalProviderFromProgram = (Boolean) in.readValue(null);
		canEditSignalProviderSettings = (Boolean) in.readValue(null);
	}

	public TradingAccountOwnerActions canClose(Boolean canClose) {
		this.canClose = canClose;
		return this;
	}

	/**
	 * Get canClose
	 *
	 * @return canClose
	 **/
	@Schema(description = "")
	public Boolean isCanClose() {
		return canClose;
	}

	public void setCanClose(Boolean canClose) {
		this.canClose = canClose;
	}

	public TradingAccountOwnerActions canClosePeriod(Boolean canClosePeriod) {
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

	public TradingAccountOwnerActions canChangePassword(Boolean canChangePassword) {
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

	public TradingAccountOwnerActions canMakeProgramFromPrivateTradingAccount(Boolean canMakeProgramFromPrivateTradingAccount) {
		this.canMakeProgramFromPrivateTradingAccount = canMakeProgramFromPrivateTradingAccount;
		return this;
	}

	/**
	 * Get canMakeProgramFromPrivateTradingAccount
	 *
	 * @return canMakeProgramFromPrivateTradingAccount
	 **/
	@Schema(description = "")
	public Boolean isCanMakeProgramFromPrivateTradingAccount() {
		return canMakeProgramFromPrivateTradingAccount;
	}

	public void setCanMakeProgramFromPrivateTradingAccount(Boolean canMakeProgramFromPrivateTradingAccount) {
		this.canMakeProgramFromPrivateTradingAccount = canMakeProgramFromPrivateTradingAccount;
	}

	public TradingAccountOwnerActions canMakePublicAccountFromPrivateTradingAccount(Boolean canMakePublicAccountFromPrivateTradingAccount) {
		this.canMakePublicAccountFromPrivateTradingAccount = canMakePublicAccountFromPrivateTradingAccount;
		return this;
	}

	/**
	 * Get canMakePublicAccountFromPrivateTradingAccount
	 *
	 * @return canMakePublicAccountFromPrivateTradingAccount
	 **/
	@Schema(description = "")
	public Boolean isCanMakePublicAccountFromPrivateTradingAccount() {
		return canMakePublicAccountFromPrivateTradingAccount;
	}

	public void setCanMakePublicAccountFromPrivateTradingAccount(Boolean canMakePublicAccountFromPrivateTradingAccount) {
		this.canMakePublicAccountFromPrivateTradingAccount = canMakePublicAccountFromPrivateTradingAccount;
	}

	public TradingAccountOwnerActions canMakeProgramFromPublicTradingAccount(Boolean canMakeProgramFromPublicTradingAccount) {
		this.canMakeProgramFromPublicTradingAccount = canMakeProgramFromPublicTradingAccount;
		return this;
	}

	/**
	 * Get canMakeProgramFromPublicTradingAccount
	 *
	 * @return canMakeProgramFromPublicTradingAccount
	 **/
	@Schema(description = "")
	public Boolean isCanMakeProgramFromPublicTradingAccount() {
		return canMakeProgramFromPublicTradingAccount;
	}

	public void setCanMakeProgramFromPublicTradingAccount(Boolean canMakeProgramFromPublicTradingAccount) {
		this.canMakeProgramFromPublicTradingAccount = canMakeProgramFromPublicTradingAccount;
	}

	public TradingAccountOwnerActions canMakeSignalProviderFromProgram(Boolean canMakeSignalProviderFromProgram) {
		this.canMakeSignalProviderFromProgram = canMakeSignalProviderFromProgram;
		return this;
	}

	/**
	 * Get canMakeSignalProviderFromProgram
	 *
	 * @return canMakeSignalProviderFromProgram
	 **/
	@Schema(description = "")
	public Boolean isCanMakeSignalProviderFromProgram() {
		return canMakeSignalProviderFromProgram;
	}

	public void setCanMakeSignalProviderFromProgram(Boolean canMakeSignalProviderFromProgram) {
		this.canMakeSignalProviderFromProgram = canMakeSignalProviderFromProgram;
	}

	public TradingAccountOwnerActions canEditSignalProviderSettings(Boolean canEditSignalProviderSettings) {
		this.canEditSignalProviderSettings = canEditSignalProviderSettings;
		return this;
	}

	/**
	 * Get canEditSignalProviderSettings
	 *
	 * @return canEditSignalProviderSettings
	 **/
	@Schema(description = "")
	public Boolean isCanEditSignalProviderSettings() {
		return canEditSignalProviderSettings;
	}

	public void setCanEditSignalProviderSettings(Boolean canEditSignalProviderSettings) {
		this.canEditSignalProviderSettings = canEditSignalProviderSettings;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TradingAccountOwnerActions tradingAccountOwnerActions = (TradingAccountOwnerActions) o;
		return Objects.equals(this.canClose, tradingAccountOwnerActions.canClose) &&
				Objects.equals(this.canClosePeriod, tradingAccountOwnerActions.canClosePeriod) &&
				Objects.equals(this.canChangePassword, tradingAccountOwnerActions.canChangePassword) &&
				Objects.equals(this.canMakeProgramFromPrivateTradingAccount, tradingAccountOwnerActions.canMakeProgramFromPrivateTradingAccount) &&
				Objects.equals(this.canMakePublicAccountFromPrivateTradingAccount, tradingAccountOwnerActions.canMakePublicAccountFromPrivateTradingAccount) &&
				Objects.equals(this.canMakeProgramFromPublicTradingAccount, tradingAccountOwnerActions.canMakeProgramFromPublicTradingAccount) &&
				Objects.equals(this.canMakeSignalProviderFromProgram, tradingAccountOwnerActions.canMakeSignalProviderFromProgram) &&
				Objects.equals(this.canEditSignalProviderSettings, tradingAccountOwnerActions.canEditSignalProviderSettings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(canClose, canClosePeriod, canChangePassword, canMakeProgramFromPrivateTradingAccount, canMakePublicAccountFromPrivateTradingAccount, canMakeProgramFromPublicTradingAccount, canMakeSignalProviderFromProgram, canEditSignalProviderSettings);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TradingAccountOwnerActions {\n");

		sb.append("    canClose: ").append(toIndentedString(canClose)).append("\n");
		sb.append("    canClosePeriod: ").append(toIndentedString(canClosePeriod)).append("\n");
		sb.append("    canChangePassword: ").append(toIndentedString(canChangePassword)).append("\n");
		sb.append("    canMakeProgramFromPrivateTradingAccount: ").append(toIndentedString(canMakeProgramFromPrivateTradingAccount)).append("\n");
		sb.append("    canMakePublicAccountFromPrivateTradingAccount: ").append(toIndentedString(canMakePublicAccountFromPrivateTradingAccount)).append("\n");
		sb.append("    canMakeProgramFromPublicTradingAccount: ").append(toIndentedString(canMakeProgramFromPublicTradingAccount)).append("\n");
		sb.append("    canMakeSignalProviderFromProgram: ").append(toIndentedString(canMakeSignalProviderFromProgram)).append("\n");
		sb.append("    canEditSignalProviderSettings: ").append(toIndentedString(canEditSignalProviderSettings)).append("\n");
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
		out.writeValue(canClose);
		out.writeValue(canClosePeriod);
		out.writeValue(canChangePassword);
		out.writeValue(canMakeProgramFromPrivateTradingAccount);
		out.writeValue(canMakePublicAccountFromPrivateTradingAccount);
		out.writeValue(canMakeProgramFromPublicTradingAccount);
		out.writeValue(canMakeSignalProviderFromProgram);
		out.writeValue(canEditSignalProviderSettings);
	}

	public int describeContents() {
		return 0;
	}
}
