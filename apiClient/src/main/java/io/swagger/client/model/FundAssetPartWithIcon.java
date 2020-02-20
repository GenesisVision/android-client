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
 * FundAssetPartWithIcon
 */


public class FundAssetPartWithIcon implements Parcelable
{
	public static final Parcelable.Creator<FundAssetPartWithIcon> CREATOR = new Parcelable.Creator<FundAssetPartWithIcon>()
	{
		public FundAssetPartWithIcon createFromParcel(Parcel in) {
			return new FundAssetPartWithIcon(in);
		}

		public FundAssetPartWithIcon[] newArray(int size) {
			return new FundAssetPartWithIcon[size];
		}
	};

	@SerializedName("icon")
	private String icon = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("asset")
	private String asset = null;

	@SerializedName("percent")
	private Double percent = null;

	public FundAssetPartWithIcon() {
	}

	FundAssetPartWithIcon(Parcel in) {
		icon = (String) in.readValue(null);
		color = (String) in.readValue(null);
		url = (String) in.readValue(null);
		name = (String) in.readValue(null);
		asset = (String) in.readValue(null);
		percent = (Double) in.readValue(null);
	}

	public FundAssetPartWithIcon icon(String icon) {
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

	public FundAssetPartWithIcon color(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Get color
	 *
	 * @return color
	 **/
	@Schema(description = "")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public FundAssetPartWithIcon url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * Get url
	 *
	 * @return url
	 **/
	@Schema(description = "")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FundAssetPartWithIcon name(String name) {
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

	public FundAssetPartWithIcon asset(String asset) {
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

	public FundAssetPartWithIcon percent(Double percent) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundAssetPartWithIcon fundAssetPartWithIcon = (FundAssetPartWithIcon) o;
		return Objects.equals(this.icon, fundAssetPartWithIcon.icon) &&
				Objects.equals(this.color, fundAssetPartWithIcon.color) &&
				Objects.equals(this.url, fundAssetPartWithIcon.url) &&
				Objects.equals(this.name, fundAssetPartWithIcon.name) &&
				Objects.equals(this.asset, fundAssetPartWithIcon.asset) &&
				Objects.equals(this.percent, fundAssetPartWithIcon.percent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(icon, color, url, name, asset, percent);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundAssetPartWithIcon {\n");

		sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
		sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
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
		out.writeValue(icon);
		out.writeValue(color);
		out.writeValue(url);
		out.writeValue(name);
		out.writeValue(asset);
		out.writeValue(percent);
	}

	public int describeContents() {
		return 0;
	}
}
