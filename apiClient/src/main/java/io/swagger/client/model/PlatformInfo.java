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
 * PlatformInfo
 */


public class PlatformInfo implements Parcelable
{
	public static final Parcelable.Creator<PlatformInfo> CREATOR = new Parcelable.Creator<PlatformInfo>()
	{
		public PlatformInfo createFromParcel(Parcel in) {
			return new PlatformInfo(in);
		}

		public PlatformInfo[] newArray(int size) {
			return new PlatformInfo[size];
		}
	};

	@SerializedName("appVersionInfo")
	private AppVersion appVersionInfo = null;

	@SerializedName("filters")
	private FilterInfo filters = null;

	@SerializedName("assetInfo")
	private AssetPlatformInfo assetInfo = null;

	@SerializedName("commonInfo")
	private PlatformCommonInfo commonInfo = null;

	public PlatformInfo() {
	}

	PlatformInfo(Parcel in) {
		appVersionInfo = (AppVersion) in.readValue(AppVersion.class.getClassLoader());
		filters = (FilterInfo) in.readValue(FilterInfo.class.getClassLoader());
		assetInfo = (AssetPlatformInfo) in.readValue(AssetPlatformInfo.class.getClassLoader());
		commonInfo = (PlatformCommonInfo) in.readValue(PlatformCommonInfo.class.getClassLoader());
	}

	public PlatformInfo appVersionInfo(AppVersion appVersionInfo) {
		this.appVersionInfo = appVersionInfo;
		return this;
	}

	/**
	 * Get appVersionInfo
	 *
	 * @return appVersionInfo
	 **/
	@Schema(description = "")
	public AppVersion getAppVersionInfo() {
		return appVersionInfo;
	}

	public void setAppVersionInfo(AppVersion appVersionInfo) {
		this.appVersionInfo = appVersionInfo;
	}

	public PlatformInfo filters(FilterInfo filters) {
		this.filters = filters;
		return this;
	}

	/**
	 * Get filters
	 *
	 * @return filters
	 **/
	@Schema(description = "")
	public FilterInfo getFilters() {
		return filters;
	}

	public void setFilters(FilterInfo filters) {
		this.filters = filters;
	}

	public PlatformInfo assetInfo(AssetPlatformInfo assetInfo) {
		this.assetInfo = assetInfo;
		return this;
	}

	/**
	 * Get assetInfo
	 *
	 * @return assetInfo
	 **/
	@Schema(description = "")
	public AssetPlatformInfo getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(AssetPlatformInfo assetInfo) {
		this.assetInfo = assetInfo;
	}

	public PlatformInfo commonInfo(PlatformCommonInfo commonInfo) {
		this.commonInfo = commonInfo;
		return this;
	}

	/**
	 * Get commonInfo
	 *
	 * @return commonInfo
	 **/
	@Schema(description = "")
	public PlatformCommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(PlatformCommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlatformInfo platformInfo = (PlatformInfo) o;
		return Objects.equals(this.appVersionInfo, platformInfo.appVersionInfo) &&
				Objects.equals(this.filters, platformInfo.filters) &&
				Objects.equals(this.assetInfo, platformInfo.assetInfo) &&
				Objects.equals(this.commonInfo, platformInfo.commonInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(appVersionInfo, filters, assetInfo, commonInfo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlatformInfo {\n");

		sb.append("    appVersionInfo: ").append(toIndentedString(appVersionInfo)).append("\n");
		sb.append("    filters: ").append(toIndentedString(filters)).append("\n");
		sb.append("    assetInfo: ").append(toIndentedString(assetInfo)).append("\n");
		sb.append("    commonInfo: ").append(toIndentedString(commonInfo)).append("\n");
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
		out.writeValue(appVersionInfo);
		out.writeValue(filters);
		out.writeValue(assetInfo);
		out.writeValue(commonInfo);
	}

	public int describeContents() {
		return 0;
	}
}
