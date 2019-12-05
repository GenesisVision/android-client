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
 * AssetSignalSettings
 */


public class AssetSignalSettings implements Parcelable
{
	public static final Parcelable.Creator<AssetSignalSettings> CREATOR = new Parcelable.Creator<AssetSignalSettings>()
	{
		public AssetSignalSettings createFromParcel(Parcel in) {
			return new AssetSignalSettings(in);
		}

		public AssetSignalSettings[] newArray(int size) {
			return new AssetSignalSettings[size];
		}
	};

	@SerializedName("signalSuccessFee")
	private Double signalSuccessFee = null;

	@SerializedName("signalVolumeFee")
	private Double signalVolumeFee = null;

	@SerializedName("isActive")
	private Boolean isActive = null;

	public AssetSignalSettings() {
	}

	AssetSignalSettings(Parcel in) {
		signalSuccessFee = (Double) in.readValue(null);
		signalVolumeFee = (Double) in.readValue(null);
		isActive = (Boolean) in.readValue(null);
	}

	public AssetSignalSettings signalSuccessFee(Double signalSuccessFee) {
		this.signalSuccessFee = signalSuccessFee;
		return this;
	}

	/**
	 * Get signalSuccessFee
	 *
	 * @return signalSuccessFee
	 **/
	@Schema(description = "")
	public Double getSignalSuccessFee() {
		return signalSuccessFee;
	}

	public void setSignalSuccessFee(Double signalSuccessFee) {
		this.signalSuccessFee = signalSuccessFee;
	}

	public AssetSignalSettings signalVolumeFee(Double signalVolumeFee) {
		this.signalVolumeFee = signalVolumeFee;
		return this;
	}

	/**
	 * Get signalVolumeFee
	 *
	 * @return signalVolumeFee
	 **/
	@Schema(description = "")
	public Double getSignalVolumeFee() {
		return signalVolumeFee;
	}

	public void setSignalVolumeFee(Double signalVolumeFee) {
		this.signalVolumeFee = signalVolumeFee;
	}

	public AssetSignalSettings isActive(Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	/**
	 * Get isActive
	 *
	 * @return isActive
	 **/
	@Schema(description = "")
	public Boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AssetSignalSettings assetSignalSettings = (AssetSignalSettings) o;
		return Objects.equals(this.signalSuccessFee, assetSignalSettings.signalSuccessFee) &&
				Objects.equals(this.signalVolumeFee, assetSignalSettings.signalVolumeFee) &&
				Objects.equals(this.isActive, assetSignalSettings.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(signalSuccessFee, signalVolumeFee, isActive);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AssetSignalSettings {\n");

		sb.append("    signalSuccessFee: ").append(toIndentedString(signalSuccessFee)).append("\n");
		sb.append("    signalVolumeFee: ").append(toIndentedString(signalVolumeFee)).append("\n");
		sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
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
		out.writeValue(signalSuccessFee);
		out.writeValue(signalVolumeFee);
		out.writeValue(isActive);
	}

	public int describeContents() {
		return 0;
	}
}
