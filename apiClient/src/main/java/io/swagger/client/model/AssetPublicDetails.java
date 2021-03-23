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

import org.joda.time.DateTime;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AssetPublicDetails
 */


public class AssetPublicDetails implements Parcelable
{
	@SerializedName("title")
	private String title = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("creationDate")
	private DateTime creationDate = null;

	@SerializedName("status")
	private String status = null;

	@SerializedName("isOwnAsset")
	private Boolean isOwnAsset = null;

	@SerializedName("typeExt")
	private AssetTypeExt typeExt = null;

	@SerializedName("systemUrl")
	private String systemUrl = null;

	public AssetPublicDetails() {
	}

	public AssetPublicDetails title(String title) {
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

	public AssetPublicDetails description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 **/
	@Schema(description = "")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AssetPublicDetails logo(String logo) {
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

	public AssetPublicDetails logoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
		return this;
	}

	/**
	 * Get logoUrl
	 *
	 * @return logoUrl
	 **/
	@Schema(description = "")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public AssetPublicDetails url(String url) {
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

	public AssetPublicDetails color(String color) {
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

	public AssetPublicDetails creationDate(DateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * Get creationDate
	 *
	 * @return creationDate
	 **/
	@Schema(description = "")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public AssetPublicDetails status(String status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/
	@Schema(description = "")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AssetPublicDetails isOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
		return this;
	}

	/**
	 * Get isOwnAsset
	 *
	 * @return isOwnAsset
	 **/
	@Schema(description = "")
	public Boolean isIsOwnAsset() {
		return isOwnAsset;
	}

	public void setIsOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
	}

	public AssetPublicDetails typeExt(AssetTypeExt typeExt) {
		this.typeExt = typeExt;
		return this;
	}

	/**
	 * Get typeExt
	 *
	 * @return typeExt
	 **/
	@Schema(description = "")
	public AssetTypeExt getTypeExt() {
		return typeExt;
	}

	public void setTypeExt(AssetTypeExt typeExt) {
		this.typeExt = typeExt;
	}

	public AssetPublicDetails systemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
		return this;
	}

	/**
	 * Get systemUrl
	 *
	 * @return systemUrl
	 **/
	@Schema(description = "")
	public String getSystemUrl() {
		return systemUrl;
	}

	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AssetPublicDetails assetPublicDetails = (AssetPublicDetails) o;
		return Objects.equals(this.title, assetPublicDetails.title) &&
				Objects.equals(this.description, assetPublicDetails.description) &&
				Objects.equals(this.logo, assetPublicDetails.logo) &&
				Objects.equals(this.logoUrl, assetPublicDetails.logoUrl) &&
				Objects.equals(this.url, assetPublicDetails.url) &&
				Objects.equals(this.color, assetPublicDetails.color) &&
				Objects.equals(this.creationDate, assetPublicDetails.creationDate) &&
				Objects.equals(this.status, assetPublicDetails.status) &&
				Objects.equals(this.isOwnAsset, assetPublicDetails.isOwnAsset) &&
				Objects.equals(this.typeExt, assetPublicDetails.typeExt) &&
				Objects.equals(this.systemUrl, assetPublicDetails.systemUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description, logo, logoUrl, url, color, creationDate, status, isOwnAsset, typeExt, systemUrl);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AssetPublicDetails {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    isOwnAsset: ").append(toIndentedString(isOwnAsset)).append("\n");
		sb.append("    typeExt: ").append(toIndentedString(typeExt)).append("\n");
		sb.append("    systemUrl: ").append(toIndentedString(systemUrl)).append("\n");
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
		out.writeValue(title);
		out.writeValue(description);
		out.writeValue(logo);
		out.writeValue(logoUrl);
		out.writeValue(url);
		out.writeValue(color);
		out.writeValue(creationDate);
		out.writeValue(status);
		out.writeValue(isOwnAsset);
		out.writeValue(typeExt);
		out.writeValue(systemUrl);
	}

	public static final Parcelable.Creator<AssetPublicDetails> CREATOR = new Parcelable.Creator<AssetPublicDetails>()
	{
		public AssetPublicDetails createFromParcel(Parcel in) {
			return new AssetPublicDetails(in);
		}

		public AssetPublicDetails[] newArray(int size) {
			return new AssetPublicDetails[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	AssetPublicDetails(Parcel in) {
		title = (String) in.readValue(null);
		description = (String) in.readValue(null);
		logo = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		url = (String) in.readValue(null);
		color = (String) in.readValue(null);
		creationDate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		status = (String) in.readValue(null);
		isOwnAsset = (Boolean) in.readValue(null);
		typeExt = (AssetTypeExt) in.readValue(AssetTypeExt.class.getClassLoader());
		systemUrl = (String) in.readValue(null);
	}
}
