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
 * OrderProgramData
 */


public class OrderProgramData implements Parcelable
{
	@SerializedName("title")
	private String title = null;

	@SerializedName("level")
	private Integer level = null;

	@SerializedName("levelProgress")
	private Double levelProgress = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("type")
	private AssetType type = null;

	public OrderProgramData() {
	}

	public OrderProgramData title(String title) {
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

	public OrderProgramData level(Integer level) {
		this.level = level;
		return this;
	}

	/**
	 * Get level
	 *
	 * @return level
	 **/
	@Schema(description = "")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public OrderProgramData levelProgress(Double levelProgress) {
		this.levelProgress = levelProgress;
		return this;
	}

	/**
	 * Get levelProgress
	 *
	 * @return levelProgress
	 **/
	@Schema(description = "")
	public Double getLevelProgress() {
		return levelProgress;
	}

	public void setLevelProgress(Double levelProgress) {
		this.levelProgress = levelProgress;
	}

	public OrderProgramData color(String color) {
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

	public OrderProgramData url(String url) {
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

	public OrderProgramData logoUrl(String logoUrl) {
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

	public OrderProgramData type(AssetType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderProgramData orderProgramData = (OrderProgramData) o;
		return Objects.equals(this.title, orderProgramData.title) &&
				Objects.equals(this.level, orderProgramData.level) &&
				Objects.equals(this.levelProgress, orderProgramData.levelProgress) &&
				Objects.equals(this.color, orderProgramData.color) &&
				Objects.equals(this.url, orderProgramData.url) &&
				Objects.equals(this.logoUrl, orderProgramData.logoUrl) &&
				Objects.equals(this.type, orderProgramData.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, level, levelProgress, color, url, logoUrl, type);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OrderProgramData {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    level: ").append(toIndentedString(level)).append("\n");
		sb.append("    levelProgress: ").append(toIndentedString(levelProgress)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
		out.writeValue(level);
		out.writeValue(levelProgress);
		out.writeValue(color);
		out.writeValue(url);
		out.writeValue(logoUrl);
		out.writeValue(type);
	}

	public static final Parcelable.Creator<OrderProgramData> CREATOR = new Parcelable.Creator<OrderProgramData>()
	{
		public OrderProgramData createFromParcel(Parcel in) {
			return new OrderProgramData(in);
		}

		public OrderProgramData[] newArray(int size) {
			return new OrderProgramData[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	OrderProgramData(Parcel in) {
		title = (String) in.readValue(null);
		level = (Integer) in.readValue(null);
		levelProgress = (Double) in.readValue(null);
		color = (String) in.readValue(null);
		url = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		type = (AssetType) in.readValue(AssetType.class.getClassLoader());
	}
}
