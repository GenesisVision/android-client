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
 * FundAssetPercent
 */


public class FundAssetPercent implements Parcelable
{
	public static final Parcelable.Creator<FundAssetPercent> CREATOR = new Parcelable.Creator<FundAssetPercent>()
	{
		public FundAssetPercent createFromParcel(Parcel in) {
			return new FundAssetPercent(in);
		}

		public FundAssetPercent[] newArray(int size) {
			return new FundAssetPercent[size];
		}
	};

	@SerializedName("asset")
	private String asset = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("percent")
	private Double percent = null;

	@SerializedName("icon")
	private String icon = null;

	public FundAssetPercent() {
	}

	FundAssetPercent(Parcel in) {
		asset = (String) in.readValue(null);
		name = (String) in.readValue(null);
		percent = (Double) in.readValue(null);
		icon = (String) in.readValue(null);
	}

	public FundAssetPercent asset(String asset) {
		this.asset = asset;
		return this;
	}

	/**
	 * Get asset
	 *
	 * @return asset
	 **/
	@Schema(description = "")
	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public FundAssetPercent name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	@Schema(description = "")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FundAssetPercent percent(Double percent) {
		this.percent = percent;
		return this;
	}

	/**
	 * Get percent
	 *
	 * @return percent
	 **/
	@Schema(description = "")
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public FundAssetPercent icon(String icon) {
		this.icon = icon;
		return this;
	}

	/**
	 * Get icon
	 *
	 * @return icon
	 **/
	@Schema(description = "")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundAssetPercent fundAssetPercent = (FundAssetPercent) o;
		return Objects.equals(this.asset, fundAssetPercent.asset) &&
				Objects.equals(this.name, fundAssetPercent.name) &&
				Objects.equals(this.percent, fundAssetPercent.percent) &&
				Objects.equals(this.icon, fundAssetPercent.icon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(asset, name, percent, icon);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundAssetPercent {\n");

		sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
		sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
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
		out.writeValue(asset);
		out.writeValue(name);
		out.writeValue(percent);
		out.writeValue(icon);
	}

	public int describeContents() {
		return 0;
	}
}
