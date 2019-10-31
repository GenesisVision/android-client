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
 * DashboardAssets
 */


public class DashboardAssets implements Parcelable
{
	public static final Parcelable.Creator<DashboardAssets> CREATOR = new Parcelable.Creator<DashboardAssets>()
	{
		public DashboardAssets createFromParcel(Parcel in) {
			return new DashboardAssets(in);
		}

		public DashboardAssets[] newArray(int size) {
			return new DashboardAssets[size];
		}
	};

	@SerializedName("assets")
	private List<DashboardAsset> assets = null;

	@SerializedName("othersPercent")
	private Double othersPercent = null;

	public DashboardAssets() {
	}

	DashboardAssets(Parcel in) {
		assets = (List<DashboardAsset>) in.readValue(DashboardAsset.class.getClassLoader());
		othersPercent = (Double) in.readValue(null);
	}

	public DashboardAssets assets(List<DashboardAsset> assets) {
		this.assets = assets;
		return this;
	}

	public DashboardAssets addAssetsItem(DashboardAsset assetsItem) {
		if (this.assets == null) {
			this.assets = new ArrayList<DashboardAsset>();
		}
		this.assets.add(assetsItem);
		return this;
	}

	/**
	 * Get assets
	 *
	 * @return assets
	 **/
	@Schema(description = "")
	public List<DashboardAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<DashboardAsset> assets) {
		this.assets = assets;
	}

	public DashboardAssets othersPercent(Double othersPercent) {
		this.othersPercent = othersPercent;
		return this;
	}

	/**
	 * Get othersPercent
	 *
	 * @return othersPercent
	 **/
	@Schema(description = "")
	public Double getOthersPercent() {
		return othersPercent;
	}

	public void setOthersPercent(Double othersPercent) {
		this.othersPercent = othersPercent;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardAssets dashboardAssets = (DashboardAssets) o;
		return Objects.equals(this.assets, dashboardAssets.assets) &&
				Objects.equals(this.othersPercent, dashboardAssets.othersPercent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assets, othersPercent);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardAssets {\n");

		sb.append("    assets: ").append(toIndentedString(assets)).append("\n");
		sb.append("    othersPercent: ").append(toIndentedString(othersPercent)).append("\n");
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
		out.writeValue(assets);
		out.writeValue(othersPercent);
	}

	public int describeContents() {
		return 0;
	}
}
