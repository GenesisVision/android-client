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
 * NewExchangeAccountRequest
 */


public class NewExchangeAccountRequest implements Parcelable
{
	public static final Parcelable.Creator<NewExchangeAccountRequest> CREATOR = new Parcelable.Creator<NewExchangeAccountRequest>()
	{
		public NewExchangeAccountRequest createFromParcel(Parcel in) {
			return new NewExchangeAccountRequest(in);
		}

		public NewExchangeAccountRequest[] newArray(int size) {
			return new NewExchangeAccountRequest[size];
		}
	};

	@SerializedName("depositAmount")
	private Double depositAmount = null;

	@SerializedName("depositWalletId")
	private UUID depositWalletId = null;

	@SerializedName("brokerAccountTypeId")
	private UUID brokerAccountTypeId = null;

	public NewExchangeAccountRequest() {
	}

	NewExchangeAccountRequest(Parcel in) {
		depositAmount = (Double) in.readValue(null);
		depositWalletId = (UUID) in.readValue(UUID.class.getClassLoader());
		brokerAccountTypeId = (UUID) in.readValue(UUID.class.getClassLoader());
	}

	public NewExchangeAccountRequest depositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
		return this;
	}

	/**
	 * Get depositAmount
	 *
	 * @return depositAmount
	 **/
	@Schema(description = "")
	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public NewExchangeAccountRequest depositWalletId(UUID depositWalletId) {
		this.depositWalletId = depositWalletId;
		return this;
	}

	/**
	 * Get depositWalletId
	 *
	 * @return depositWalletId
	 **/
	@Schema(description = "")
	public UUID getDepositWalletId() {
		return depositWalletId;
	}

	public void setDepositWalletId(UUID depositWalletId) {
		this.depositWalletId = depositWalletId;
	}

	public NewExchangeAccountRequest brokerAccountTypeId(UUID brokerAccountTypeId) {
		this.brokerAccountTypeId = brokerAccountTypeId;
		return this;
	}

	/**
	 * Get brokerAccountTypeId
	 *
	 * @return brokerAccountTypeId
	 **/
	@Schema(description = "")
	public UUID getBrokerAccountTypeId() {
		return brokerAccountTypeId;
	}

	public void setBrokerAccountTypeId(UUID brokerAccountTypeId) {
		this.brokerAccountTypeId = brokerAccountTypeId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NewExchangeAccountRequest newExchangeAccountRequest = (NewExchangeAccountRequest) o;
		return Objects.equals(this.depositAmount, newExchangeAccountRequest.depositAmount) &&
				Objects.equals(this.depositWalletId, newExchangeAccountRequest.depositWalletId) &&
				Objects.equals(this.brokerAccountTypeId, newExchangeAccountRequest.brokerAccountTypeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(depositAmount, depositWalletId, brokerAccountTypeId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NewExchangeAccountRequest {\n");

		sb.append("    depositAmount: ").append(toIndentedString(depositAmount)).append("\n");
		sb.append("    depositWalletId: ").append(toIndentedString(depositWalletId)).append("\n");
		sb.append("    brokerAccountTypeId: ").append(toIndentedString(brokerAccountTypeId)).append("\n");
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
		out.writeValue(depositAmount);
		out.writeValue(depositWalletId);
		out.writeValue(brokerAccountTypeId);
	}

	public int describeContents() {
		return 0;
	}
}
