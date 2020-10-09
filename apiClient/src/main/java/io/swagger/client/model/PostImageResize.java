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
 * PostImageResize
 */


public class PostImageResize implements Parcelable
{
	public static final Parcelable.Creator<PostImageResize> CREATOR = new Parcelable.Creator<PostImageResize>()
	{
		public PostImageResize createFromParcel(Parcel in) {
			return new PostImageResize(in);
		}

		public PostImageResize[] newArray(int size) {
			return new PostImageResize[size];
		}
	};

	@SerializedName("quality")
	private ImageQuality quality = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("height")
	private Integer height = null;

	@SerializedName("width")
	private Integer width = null;

	public PostImageResize() {
	}

	PostImageResize(Parcel in) {
		quality = (ImageQuality) in.readValue(ImageQuality.class.getClassLoader());
		logoUrl = (String) in.readValue(null);
		height = (Integer) in.readValue(null);
		width = (Integer) in.readValue(null);
	}

	public PostImageResize quality(ImageQuality quality) {
		this.quality = quality;
		return this;
	}

	/**
	 * Get quality
	 *
	 * @return quality
	 **/
	@Schema(description = "")
	public ImageQuality getQuality() {
		return quality;
	}

	public void setQuality(ImageQuality quality) {
		this.quality = quality;
	}

	public PostImageResize logoUrl(String logoUrl) {
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

	public PostImageResize height(Integer height) {
		this.height = height;
		return this;
	}

	/**
	 * Get height
	 *
	 * @return height
	 **/
	@Schema(description = "")
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public PostImageResize width(Integer width) {
		this.width = width;
		return this;
	}

	/**
	 * Get width
	 *
	 * @return width
	 **/
	@Schema(description = "")
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostImageResize postImageResize = (PostImageResize) o;
		return Objects.equals(this.quality, postImageResize.quality) &&
				Objects.equals(this.logoUrl, postImageResize.logoUrl) &&
				Objects.equals(this.height, postImageResize.height) &&
				Objects.equals(this.width, postImageResize.width);
	}

	@Override
	public int hashCode() {
		return Objects.hash(quality, logoUrl, height, width);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostImageResize {\n");

		sb.append("    quality: ").append(toIndentedString(quality)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    height: ").append(toIndentedString(height)).append("\n");
		sb.append("    width: ").append(toIndentedString(width)).append("\n");
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
		out.writeValue(quality);
		out.writeValue(logoUrl);
		out.writeValue(height);
		out.writeValue(width);
	}

	public int describeContents() {
		return 0;
	}
}
