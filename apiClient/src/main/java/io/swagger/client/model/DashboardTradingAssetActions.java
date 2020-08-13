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
 * DashboardTradingAssetActions
 */


public class DashboardTradingAssetActions implements Parcelable
{
	public static final Parcelable.Creator<DashboardTradingAssetActions> CREATOR = new Parcelable.Creator<DashboardTradingAssetActions>()
	{
		public DashboardTradingAssetActions createFromParcel(Parcel in) {
			return new DashboardTradingAssetActions(in);
		}

		public DashboardTradingAssetActions[] newArray(int size) {
			return new DashboardTradingAssetActions[size];
		}
	};

	@SerializedName("canAddRequestInvest")
	private Boolean canAddRequestInvest = null;

	@SerializedName("canAddRequestWithdraw")
	private Boolean canAddRequestWithdraw = null;

	@SerializedName("canTransferMoney")
	private Boolean canTransferMoney = null;

	@SerializedName("canMakeDemoDeposit")
	private Boolean canMakeDemoDeposit = null;

	@SerializedName("canChangePassword")
	private Boolean canChangePassword = null;

	@SerializedName("canClose")
	private Boolean canClose = null;

	@SerializedName("hasTerminal")
	private Boolean hasTerminal = null;

	@SerializedName("canMakeProgramFromPrivateTradingAccount")
	private Boolean canMakeProgramFromPrivateTradingAccount = null;

	@SerializedName("canMakeExchangeProgramFromPrivateTradingAccount")
	private Boolean canMakeExchangeProgramFromPrivateTradingAccount = null;

	@SerializedName("canMakeSignalProviderFromPrivateTradingAccount")
	private Boolean canMakeSignalProviderFromPrivateTradingAccount = null;

	@SerializedName("canMakeSignalProviderFromPrivateExternalTradingAccount")
	private Boolean canMakeSignalProviderFromPrivateExternalTradingAccount = null;

	@SerializedName("canMakeProgramFromSignalProvider")
	private Boolean canMakeProgramFromSignalProvider = null;

	@SerializedName("canMakeSignalProviderFromProgram")
	private Boolean canMakeSignalProviderFromProgram = null;

	@SerializedName("canEditSignalProviderSettings")
	private Boolean canEditSignalProviderSettings = null;

	@SerializedName("isEnoughMoneyToCreateProgram")
	private Boolean isEnoughMoneyToCreateProgram = null;

	@SerializedName("canConfirm2FA")
	private Boolean canConfirm2FA = null;

	public DashboardTradingAssetActions() {
	}

	DashboardTradingAssetActions(Parcel in) {
		canAddRequestInvest = (Boolean) in.readValue(null);
		canAddRequestWithdraw = (Boolean) in.readValue(null);
		canTransferMoney = (Boolean) in.readValue(null);
		canMakeDemoDeposit = (Boolean) in.readValue(null);
		canChangePassword = (Boolean) in.readValue(null);
		canClose = (Boolean) in.readValue(null);
		hasTerminal = (Boolean) in.readValue(null);
		canMakeProgramFromPrivateTradingAccount = (Boolean) in.readValue(null);
		canMakeExchangeProgramFromPrivateTradingAccount = (Boolean) in.readValue(null);
		canMakeSignalProviderFromPrivateTradingAccount = (Boolean) in.readValue(null);
		canMakeSignalProviderFromPrivateExternalTradingAccount = (Boolean) in.readValue(null);
		canMakeProgramFromSignalProvider = (Boolean) in.readValue(null);
		canMakeSignalProviderFromProgram = (Boolean) in.readValue(null);
		canEditSignalProviderSettings = (Boolean) in.readValue(null);
		isEnoughMoneyToCreateProgram = (Boolean) in.readValue(null);
		canConfirm2FA = (Boolean) in.readValue(null);
	}

	public DashboardTradingAssetActions canAddRequestInvest(Boolean canAddRequestInvest) {
		this.canAddRequestInvest = canAddRequestInvest;
		return this;
	}

	/**
	 * Get canAddRequestInvest
	 *
	 * @return canAddRequestInvest
	 **/
	@Schema(description = "")
	public Boolean isCanAddRequestInvest() {
		return canAddRequestInvest;
	}

	public void setCanAddRequestInvest(Boolean canAddRequestInvest) {
		this.canAddRequestInvest = canAddRequestInvest;
	}

	public DashboardTradingAssetActions canAddRequestWithdraw(Boolean canAddRequestWithdraw) {
		this.canAddRequestWithdraw = canAddRequestWithdraw;
		return this;
	}

	/**
	 * Get canAddRequestWithdraw
	 *
	 * @return canAddRequestWithdraw
	 **/
	@Schema(description = "")
	public Boolean isCanAddRequestWithdraw() {
		return canAddRequestWithdraw;
	}

	public void setCanAddRequestWithdraw(Boolean canAddRequestWithdraw) {
		this.canAddRequestWithdraw = canAddRequestWithdraw;
	}

	public DashboardTradingAssetActions canTransferMoney(Boolean canTransferMoney) {
		this.canTransferMoney = canTransferMoney;
		return this;
	}

	/**
	 * Get canTransferMoney
	 *
	 * @return canTransferMoney
	 **/
	@Schema(description = "")
	public Boolean isCanTransferMoney() {
		return canTransferMoney;
	}

	public void setCanTransferMoney(Boolean canTransferMoney) {
		this.canTransferMoney = canTransferMoney;
	}

	public DashboardTradingAssetActions canMakeDemoDeposit(Boolean canMakeDemoDeposit) {
		this.canMakeDemoDeposit = canMakeDemoDeposit;
		return this;
	}

	/**
	 * Get canMakeDemoDeposit
	 *
	 * @return canMakeDemoDeposit
	 **/
	@Schema(description = "")
	public Boolean isCanMakeDemoDeposit() {
		return canMakeDemoDeposit;
	}

	public void setCanMakeDemoDeposit(Boolean canMakeDemoDeposit) {
		this.canMakeDemoDeposit = canMakeDemoDeposit;
	}

	public DashboardTradingAssetActions canChangePassword(Boolean canChangePassword) {
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

	public DashboardTradingAssetActions canClose(Boolean canClose) {
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

	public DashboardTradingAssetActions hasTerminal(Boolean hasTerminal) {
		this.hasTerminal = hasTerminal;
		return this;
	}

	/**
	 * Get hasTerminal
	 *
	 * @return hasTerminal
	 **/
	@Schema(description = "")
	public Boolean isHasTerminal() {
		return hasTerminal;
	}

	public void setHasTerminal(Boolean hasTerminal) {
		this.hasTerminal = hasTerminal;
	}

	public DashboardTradingAssetActions canMakeProgramFromPrivateTradingAccount(Boolean canMakeProgramFromPrivateTradingAccount) {
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

	public DashboardTradingAssetActions canMakeExchangeProgramFromPrivateTradingAccount(Boolean canMakeExchangeProgramFromPrivateTradingAccount) {
		this.canMakeExchangeProgramFromPrivateTradingAccount = canMakeExchangeProgramFromPrivateTradingAccount;
		return this;
	}

	/**
	 * Get canMakeExchangeProgramFromPrivateTradingAccount
	 *
	 * @return canMakeExchangeProgramFromPrivateTradingAccount
	 **/
	@Schema(description = "")
	public Boolean isCanMakeExchangeProgramFromPrivateTradingAccount() {
		return canMakeExchangeProgramFromPrivateTradingAccount;
	}

	public void setCanMakeExchangeProgramFromPrivateTradingAccount(Boolean canMakeExchangeProgramFromPrivateTradingAccount) {
		this.canMakeExchangeProgramFromPrivateTradingAccount = canMakeExchangeProgramFromPrivateTradingAccount;
	}

	public DashboardTradingAssetActions canMakeSignalProviderFromPrivateTradingAccount(Boolean canMakeSignalProviderFromPrivateTradingAccount) {
		this.canMakeSignalProviderFromPrivateTradingAccount = canMakeSignalProviderFromPrivateTradingAccount;
		return this;
	}

	/**
	 * Get canMakeSignalProviderFromPrivateTradingAccount
	 *
	 * @return canMakeSignalProviderFromPrivateTradingAccount
	 **/
	@Schema(description = "")
	public Boolean isCanMakeSignalProviderFromPrivateTradingAccount() {
		return canMakeSignalProviderFromPrivateTradingAccount;
	}

	public void setCanMakeSignalProviderFromPrivateTradingAccount(Boolean canMakeSignalProviderFromPrivateTradingAccount) {
		this.canMakeSignalProviderFromPrivateTradingAccount = canMakeSignalProviderFromPrivateTradingAccount;
	}

	public DashboardTradingAssetActions canMakeSignalProviderFromPrivateExternalTradingAccount(Boolean canMakeSignalProviderFromPrivateExternalTradingAccount) {
		this.canMakeSignalProviderFromPrivateExternalTradingAccount = canMakeSignalProviderFromPrivateExternalTradingAccount;
		return this;
	}

	/**
	 * Get canMakeSignalProviderFromPrivateExternalTradingAccount
	 *
	 * @return canMakeSignalProviderFromPrivateExternalTradingAccount
	 **/
	@Schema(description = "")
	public Boolean isCanMakeSignalProviderFromPrivateExternalTradingAccount() {
		return canMakeSignalProviderFromPrivateExternalTradingAccount;
	}

	public void setCanMakeSignalProviderFromPrivateExternalTradingAccount(Boolean canMakeSignalProviderFromPrivateExternalTradingAccount) {
		this.canMakeSignalProviderFromPrivateExternalTradingAccount = canMakeSignalProviderFromPrivateExternalTradingAccount;
	}

	public DashboardTradingAssetActions canMakeProgramFromSignalProvider(Boolean canMakeProgramFromSignalProvider) {
		this.canMakeProgramFromSignalProvider = canMakeProgramFromSignalProvider;
		return this;
	}

	/**
	 * Get canMakeProgramFromSignalProvider
	 *
	 * @return canMakeProgramFromSignalProvider
	 **/
	@Schema(description = "")
	public Boolean isCanMakeProgramFromSignalProvider() {
		return canMakeProgramFromSignalProvider;
	}

	public void setCanMakeProgramFromSignalProvider(Boolean canMakeProgramFromSignalProvider) {
		this.canMakeProgramFromSignalProvider = canMakeProgramFromSignalProvider;
	}

	public DashboardTradingAssetActions canMakeSignalProviderFromProgram(Boolean canMakeSignalProviderFromProgram) {
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

	public DashboardTradingAssetActions canEditSignalProviderSettings(Boolean canEditSignalProviderSettings) {
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

	public DashboardTradingAssetActions isEnoughMoneyToCreateProgram(Boolean isEnoughMoneyToCreateProgram) {
		this.isEnoughMoneyToCreateProgram = isEnoughMoneyToCreateProgram;
		return this;
	}

	/**
	 * Get isEnoughMoneyToCreateProgram
	 *
	 * @return isEnoughMoneyToCreateProgram
	 **/
	@Schema(description = "")
	public Boolean isIsEnoughMoneyToCreateProgram() {
		return isEnoughMoneyToCreateProgram;
	}

	public void setIsEnoughMoneyToCreateProgram(Boolean isEnoughMoneyToCreateProgram) {
		this.isEnoughMoneyToCreateProgram = isEnoughMoneyToCreateProgram;
	}

	public DashboardTradingAssetActions canConfirm2FA(Boolean canConfirm2FA) {
		this.canConfirm2FA = canConfirm2FA;
		return this;
	}

	/**
	 * Get canConfirm2FA
	 *
	 * @return canConfirm2FA
	 **/
	@Schema(description = "")
	public Boolean isCanConfirm2FA() {
		return canConfirm2FA;
	}

	public void setCanConfirm2FA(Boolean canConfirm2FA) {
		this.canConfirm2FA = canConfirm2FA;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardTradingAssetActions dashboardTradingAssetActions = (DashboardTradingAssetActions) o;
		return Objects.equals(this.canAddRequestInvest, dashboardTradingAssetActions.canAddRequestInvest) &&
				Objects.equals(this.canAddRequestWithdraw, dashboardTradingAssetActions.canAddRequestWithdraw) &&
				Objects.equals(this.canTransferMoney, dashboardTradingAssetActions.canTransferMoney) &&
				Objects.equals(this.canMakeDemoDeposit, dashboardTradingAssetActions.canMakeDemoDeposit) &&
				Objects.equals(this.canChangePassword, dashboardTradingAssetActions.canChangePassword) &&
				Objects.equals(this.canClose, dashboardTradingAssetActions.canClose) &&
				Objects.equals(this.hasTerminal, dashboardTradingAssetActions.hasTerminal) &&
				Objects.equals(this.canMakeProgramFromPrivateTradingAccount, dashboardTradingAssetActions.canMakeProgramFromPrivateTradingAccount) &&
				Objects.equals(this.canMakeExchangeProgramFromPrivateTradingAccount, dashboardTradingAssetActions.canMakeExchangeProgramFromPrivateTradingAccount) &&
				Objects.equals(this.canMakeSignalProviderFromPrivateTradingAccount, dashboardTradingAssetActions.canMakeSignalProviderFromPrivateTradingAccount) &&
				Objects.equals(this.canMakeSignalProviderFromPrivateExternalTradingAccount, dashboardTradingAssetActions.canMakeSignalProviderFromPrivateExternalTradingAccount) &&
				Objects.equals(this.canMakeProgramFromSignalProvider, dashboardTradingAssetActions.canMakeProgramFromSignalProvider) &&
				Objects.equals(this.canMakeSignalProviderFromProgram, dashboardTradingAssetActions.canMakeSignalProviderFromProgram) &&
				Objects.equals(this.canEditSignalProviderSettings, dashboardTradingAssetActions.canEditSignalProviderSettings) &&
				Objects.equals(this.isEnoughMoneyToCreateProgram, dashboardTradingAssetActions.isEnoughMoneyToCreateProgram) &&
				Objects.equals(this.canConfirm2FA, dashboardTradingAssetActions.canConfirm2FA);
	}

	@Override
	public int hashCode() {
		return Objects.hash(canAddRequestInvest, canAddRequestWithdraw, canTransferMoney, canMakeDemoDeposit, canChangePassword, canClose, hasTerminal, canMakeProgramFromPrivateTradingAccount, canMakeExchangeProgramFromPrivateTradingAccount, canMakeSignalProviderFromPrivateTradingAccount, canMakeSignalProviderFromPrivateExternalTradingAccount, canMakeProgramFromSignalProvider, canMakeSignalProviderFromProgram, canEditSignalProviderSettings, isEnoughMoneyToCreateProgram, canConfirm2FA);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardTradingAssetActions {\n");

		sb.append("    canAddRequestInvest: ").append(toIndentedString(canAddRequestInvest)).append("\n");
		sb.append("    canAddRequestWithdraw: ").append(toIndentedString(canAddRequestWithdraw)).append("\n");
		sb.append("    canTransferMoney: ").append(toIndentedString(canTransferMoney)).append("\n");
		sb.append("    canMakeDemoDeposit: ").append(toIndentedString(canMakeDemoDeposit)).append("\n");
		sb.append("    canChangePassword: ").append(toIndentedString(canChangePassword)).append("\n");
		sb.append("    canClose: ").append(toIndentedString(canClose)).append("\n");
		sb.append("    hasTerminal: ").append(toIndentedString(hasTerminal)).append("\n");
		sb.append("    canMakeProgramFromPrivateTradingAccount: ").append(toIndentedString(canMakeProgramFromPrivateTradingAccount)).append("\n");
		sb.append("    canMakeExchangeProgramFromPrivateTradingAccount: ").append(toIndentedString(canMakeExchangeProgramFromPrivateTradingAccount)).append("\n");
		sb.append("    canMakeSignalProviderFromPrivateTradingAccount: ").append(toIndentedString(canMakeSignalProviderFromPrivateTradingAccount)).append("\n");
		sb.append("    canMakeSignalProviderFromPrivateExternalTradingAccount: ").append(toIndentedString(canMakeSignalProviderFromPrivateExternalTradingAccount)).append("\n");
		sb.append("    canMakeProgramFromSignalProvider: ").append(toIndentedString(canMakeProgramFromSignalProvider)).append("\n");
		sb.append("    canMakeSignalProviderFromProgram: ").append(toIndentedString(canMakeSignalProviderFromProgram)).append("\n");
		sb.append("    canEditSignalProviderSettings: ").append(toIndentedString(canEditSignalProviderSettings)).append("\n");
		sb.append("    isEnoughMoneyToCreateProgram: ").append(toIndentedString(isEnoughMoneyToCreateProgram)).append("\n");
		sb.append("    canConfirm2FA: ").append(toIndentedString(canConfirm2FA)).append("\n");
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
		out.writeValue(canAddRequestInvest);
		out.writeValue(canAddRequestWithdraw);
		out.writeValue(canTransferMoney);
		out.writeValue(canMakeDemoDeposit);
		out.writeValue(canChangePassword);
		out.writeValue(canClose);
		out.writeValue(hasTerminal);
		out.writeValue(canMakeProgramFromPrivateTradingAccount);
		out.writeValue(canMakeExchangeProgramFromPrivateTradingAccount);
		out.writeValue(canMakeSignalProviderFromPrivateTradingAccount);
		out.writeValue(canMakeSignalProviderFromPrivateExternalTradingAccount);
		out.writeValue(canMakeProgramFromSignalProvider);
		out.writeValue(canMakeSignalProviderFromProgram);
		out.writeValue(canEditSignalProviderSettings);
		out.writeValue(isEnoughMoneyToCreateProgram);
		out.writeValue(canConfirm2FA);
	}

	public int describeContents() {
		return 0;
	}
}
