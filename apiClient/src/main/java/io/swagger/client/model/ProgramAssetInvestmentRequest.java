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
 * ProgramAssetInvestmentRequest
 */


public class ProgramAssetInvestmentRequest implements Parcelable
{
	public static final Parcelable.Creator<ProgramAssetInvestmentRequest> CREATOR = new Parcelable.Creator<ProgramAssetInvestmentRequest>()
	{
		public ProgramAssetInvestmentRequest createFromParcel(Parcel in) {
			return new ProgramAssetInvestmentRequest(in);
		}

		public ProgramAssetInvestmentRequest[] newArray(int size) {
			return new ProgramAssetInvestmentRequest[size];
		}
	};

	@SerializedName("isWithdrawAll")
	private Boolean isWithdrawAll = null;

	@SerializedName("successFee")
	private Double successFee = null;

	@SerializedName("entryFee")
	private Double entryFee = null;

	public ProgramAssetInvestmentRequest() {
	}

	ProgramAssetInvestmentRequest(Parcel in) {
		isWithdrawAll = (Boolean) in.readValue(null);
		successFee = (Double) in.readValue(null);
		entryFee = (Double) in.readValue(null);
	}

	public ProgramAssetInvestmentRequest isWithdrawAll(Boolean isWithdrawAll) {
		this.isWithdrawAll = isWithdrawAll;
		return this;
	}

	/**
	 * Get isWithdrawAll
	 *
	 * @return isWithdrawAll
	 **/
	@Schema(description = "")
	public Boolean isIsWithdrawAll() {
		return isWithdrawAll;
	}

	public void setIsWithdrawAll(Boolean isWithdrawAll) {
		this.isWithdrawAll = isWithdrawAll;
	}

	public ProgramAssetInvestmentRequest successFee(Double successFee) {
		this.successFee = successFee;
		return this;
	}

	/**
	 * Get successFee
	 *
	 * @return successFee
	 **/
	@Schema(description = "")
	public Double getSuccessFee() {
		return successFee;
	}

	public void setSuccessFee(Double successFee) {
		this.successFee = successFee;
	}

	public ProgramAssetInvestmentRequest entryFee(Double entryFee) {
		this.entryFee = entryFee;
		return this;
	}

	/**
	 * Get entryFee
	 *
	 * @return entryFee
	 **/
	@Schema(description = "")
	public Double getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(Double entryFee) {
		this.entryFee = entryFee;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProgramAssetInvestmentRequest programAssetInvestmentRequest = (ProgramAssetInvestmentRequest) o;
		return Objects.equals(this.isWithdrawAll, programAssetInvestmentRequest.isWithdrawAll) &&
				Objects.equals(this.successFee, programAssetInvestmentRequest.successFee) &&
				Objects.equals(this.entryFee, programAssetInvestmentRequest.entryFee);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isWithdrawAll, successFee, entryFee);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramAssetInvestmentRequest {\n");

		sb.append("    isWithdrawAll: ").append(toIndentedString(isWithdrawAll)).append("\n");
		sb.append("    successFee: ").append(toIndentedString(successFee)).append("\n");
		sb.append("    entryFee: ").append(toIndentedString(entryFee)).append("\n");
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
		out.writeValue(isWithdrawAll);
		out.writeValue(successFee);
		out.writeValue(entryFee);
	}

	public int describeContents() {
		return 0;
	}
}
