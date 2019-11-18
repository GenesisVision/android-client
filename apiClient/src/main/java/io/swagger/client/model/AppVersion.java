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
 * AppVersion
 */


public class AppVersion implements Parcelable
{
	public static final Parcelable.Creator<AppVersion> CREATOR = new Parcelable.Creator<AppVersion>()
	{
		public AppVersion createFromParcel(Parcel in) {
			return new AppVersion(in);
		}

		public AppVersion[] newArray(int size) {
			return new AppVersion[size];
		}
	};

	@SerializedName("iOS")
	private IOsAppVersion iOS = null;

	@SerializedName("android")
	private AndroidAppVersion android = null;

	public AppVersion() {
	}

	AppVersion(Parcel in) {
		iOS = (IOsAppVersion) in.readValue(IOsAppVersion.class.getClassLoader());
		android = (AndroidAppVersion) in.readValue(AndroidAppVersion.class.getClassLoader());
	}

	public AppVersion iOS(IOsAppVersion iOS) {
		this.iOS = iOS;
		return this;
	}

	/**
	 * Get iOS
	 *
	 * @return iOS
	 **/
	@Schema(description = "")
	public IOsAppVersion getIOS() {
		return iOS;
	}

	public void setIOS(IOsAppVersion iOS) {
		this.iOS = iOS;
	}

	public AppVersion android(AndroidAppVersion android) {
		this.android = android;
		return this;
	}

	/**
	 * Get android
	 *
	 * @return android
	 **/
	@Schema(description = "")
	public AndroidAppVersion getAndroid() {
		return android;
	}

	public void setAndroid(AndroidAppVersion android) {
		this.android = android;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AppVersion appVersion = (AppVersion) o;
		return Objects.equals(this.iOS, appVersion.iOS) &&
				Objects.equals(this.android, appVersion.android);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iOS, android);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AppVersion {\n");

		sb.append("    iOS: ").append(toIndentedString(iOS)).append("\n");
		sb.append("    android: ").append(toIndentedString(android)).append("\n");
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
		out.writeValue(iOS);
		out.writeValue(android);
	}

	public int describeContents() {
		return 0;
	}
}
