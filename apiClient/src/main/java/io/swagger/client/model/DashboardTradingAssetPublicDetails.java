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
 * DashboardTradingAssetPublicDetails
 */


public class DashboardTradingAssetPublicDetails implements Parcelable
{
	public static final Parcelable.Creator<DashboardTradingAssetPublicDetails> CREATOR = new Parcelable.Creator<DashboardTradingAssetPublicDetails>()
	{
		public DashboardTradingAssetPublicDetails createFromParcel(Parcel in) {
			return new DashboardTradingAssetPublicDetails(in);
		}

		public DashboardTradingAssetPublicDetails[] newArray(int size) {
			return new DashboardTradingAssetPublicDetails[size];
		}
	};

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("url")
	private String url = null;

	public DashboardTradingAssetPublicDetails() {
	}

	DashboardTradingAssetPublicDetails(Parcel in) {
		logo = (String) in.readValue(null);
		color = (String) in.readValue(null);
		title = (String) in.readValue(null);
		url = (String) in.readValue(null);
	}

	public DashboardTradingAssetPublicDetails logo(String logo) {
		this.logo = logo;
		return this;
	}

	/**
	 * Get logo
	 *
	 * @return logo
	 **/
	@Schema(description = "")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public DashboardTradingAssetPublicDetails color(String color) {
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

	public DashboardTradingAssetPublicDetails title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DashboardTradingAssetPublicDetails url(String url) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DashboardTradingAssetPublicDetails dashboardTradingAssetPublicDetails = (DashboardTradingAssetPublicDetails) o;
		return Objects.equals(this.logo, dashboardTradingAssetPublicDetails.logo) &&
				Objects.equals(this.color, dashboardTradingAssetPublicDetails.color) &&
				Objects.equals(this.title, dashboardTradingAssetPublicDetails.title) &&
				Objects.equals(this.url, dashboardTradingAssetPublicDetails.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(logo, color, title, url);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DashboardTradingAssetPublicDetails {\n");

		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
		out.writeValue(logo);
		out.writeValue(color);
		out.writeValue(title);
		out.writeValue(url);
	}

	public int describeContents() {
		return 0;
	}
}
