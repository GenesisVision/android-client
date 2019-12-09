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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DashboardPortfolio
 */


public class DashboardPortfolio implements Parcelable
{
	public static final Parcelable.Creator<DashboardPortfolio> CREATOR = new Parcelable.Creator<DashboardPortfolio>()
	{
		public DashboardPortfolio createFromParcel(Parcel in) {
			return new DashboardPortfolio(in);
		}

		public DashboardPortfolio[] newArray(int size) {
			return new DashboardPortfolio[size];
		}
	};

	@SerializedName("distribution")
	private List<MoneyLocation> distribution = null;

	public DashboardPortfolio() {
	}

	DashboardPortfolio(Parcel in) {
		distribution = (List<MoneyLocation>) in.readValue(MoneyLocation.class.getClassLoader());
	}

	public DashboardPortfolio distribution(List<MoneyLocation> distribution) {
		this.distribution = distribution;
		return this;
	}

	public DashboardPortfolio addDistributionItem(MoneyLocation distributionItem) {
		if (this.distribution == null) {
			this.distribution = new ArrayList<MoneyLocation>();
		}
		this.distribution.add(distributionItem);
		return this;
	}

	/**
	 * Get distribution
	 *
	 * @return distribution
	 **/
	@Schema(description = "")
	public List<MoneyLocation> getDistribution() {
		return distribution;
	}

	public void setDistribution(List<MoneyLocation> distribution) {
		this.distribution = distribution;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardPortfolio dashboardPortfolio = (DashboardPortfolio) o;
		return Objects.equals(this.distribution, dashboardPortfolio.distribution);
	}

	@Override
	public int hashCode() {
		return Objects.hash(distribution);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardPortfolio {\n");

		sb.append("    distribution: ").append(toIndentedString(distribution)).append("\n");
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
		out.writeValue(distribution);
	}

	public int describeContents() {
		return 0;
	}
}
