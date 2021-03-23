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
 * DashboardTradingAssetSignalDetails
 */


public class DashboardTradingAssetSignalDetails implements Parcelable
{
	@SerializedName("subscribersCount")
	private Integer subscribersCount = null;

	public DashboardTradingAssetSignalDetails() {
	}

	public DashboardTradingAssetSignalDetails subscribersCount(Integer subscribersCount) {
		this.subscribersCount = subscribersCount;
		return this;
	}

	/**
	 * Get subscribersCount
	 *
	 * @return subscribersCount
	 **/
	@Schema(description = "")
	public Integer getSubscribersCount() {
		return subscribersCount;
	}

	public void setSubscribersCount(Integer subscribersCount) {
		this.subscribersCount = subscribersCount;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardTradingAssetSignalDetails dashboardTradingAssetSignalDetails = (DashboardTradingAssetSignalDetails) o;
		return Objects.equals(this.subscribersCount, dashboardTradingAssetSignalDetails.subscribersCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subscribersCount);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardTradingAssetSignalDetails {\n");

		sb.append("    subscribersCount: ").append(toIndentedString(subscribersCount)).append("\n");
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
		out.writeValue(subscribersCount);
	}

	public static final Parcelable.Creator<DashboardTradingAssetSignalDetails> CREATOR = new Parcelable.Creator<DashboardTradingAssetSignalDetails>()
	{
		public DashboardTradingAssetSignalDetails createFromParcel(Parcel in) {
			return new DashboardTradingAssetSignalDetails(in);
		}

		public DashboardTradingAssetSignalDetails[] newArray(int size) {
			return new DashboardTradingAssetSignalDetails[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	DashboardTradingAssetSignalDetails(Parcel in) {
		subscribersCount = (Integer) in.readValue(null);
	}
}
