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
 * UsersSocialLinkInfo
 */


public class UsersSocialLinkInfo implements Parcelable
{
	public static final Parcelable.Creator<UsersSocialLinkInfo> CREATOR = new Parcelable.Creator<UsersSocialLinkInfo>()
	{
		public UsersSocialLinkInfo createFromParcel(Parcel in) {
			return new UsersSocialLinkInfo(in);
		}

		public UsersSocialLinkInfo[] newArray(int size) {
			return new UsersSocialLinkInfo[size];
		}
	};

	@SerializedName("type")
	private SocialLinkType type = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("name")
	private String name = null;

	public UsersSocialLinkInfo() {
	}

	UsersSocialLinkInfo(Parcel in) {
		type = (SocialLinkType) in.readValue(SocialLinkType.class.getClassLoader());
		url = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		name = (String) in.readValue(null);
	}

	public UsersSocialLinkInfo type(SocialLinkType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public SocialLinkType getType() {
		return type;
	}

	public void setType(SocialLinkType type) {
		this.type = type;
	}

	public UsersSocialLinkInfo url(String url) {
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

	public UsersSocialLinkInfo logoUrl(String logoUrl) {
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

	public UsersSocialLinkInfo name(String name) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UsersSocialLinkInfo usersSocialLinkInfo = (UsersSocialLinkInfo) o;
		return Objects.equals(this.type, usersSocialLinkInfo.type) &&
				Objects.equals(this.url, usersSocialLinkInfo.url) &&
				Objects.equals(this.logoUrl, usersSocialLinkInfo.logoUrl) &&
				Objects.equals(this.name, usersSocialLinkInfo.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, url, logoUrl, name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UsersSocialLinkInfo {\n");

		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
		out.writeValue(type);
		out.writeValue(url);
		out.writeValue(logoUrl);
		out.writeValue(name);
	}

	public int describeContents() {
		return 0;
	}
}
