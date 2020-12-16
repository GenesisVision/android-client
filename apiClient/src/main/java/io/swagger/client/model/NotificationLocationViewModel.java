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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * NotificationLocationViewModel
 */


public class NotificationLocationViewModel implements Parcelable
{
	public static final Parcelable.Creator<NotificationLocationViewModel> CREATOR = new Parcelable.Creator<NotificationLocationViewModel>()
	{
		public NotificationLocationViewModel createFromParcel(Parcel in) {
			return new NotificationLocationViewModel(in);
		}

		public NotificationLocationViewModel[] newArray(int size) {
			return new NotificationLocationViewModel[size];
		}
	};

	@SerializedName("location")
	private String location = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("externalUrl")
	private String externalUrl = null;

	public NotificationLocationViewModel() {
	}

	NotificationLocationViewModel(Parcel in) {
		location = (String) in.readValue(null);
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		externalUrl = (String) in.readValue(null);
	}

	public NotificationLocationViewModel location(String location) {
		this.location = location;
		return this;
	}

	/**
	 * Platform location  &lt;br&gt;User&lt;br&gt;Program&lt;br&gt;Fund&lt;br&gt;Follow&lt;br&gt;TradingAccount&lt;br&gt;SocialPost&lt;br&gt;SocialMediaPost&lt;br&gt;Dashboard&lt;br&gt;ExternalUrl  Enum: GenesisVision.Core.DataModel.Enums.PlatformLocation
	 *
	 * @return location
	 **/
	@Schema(description = "Platform location  <br>User<br>Program<br>Fund<br>Follow<br>TradingAccount<br>SocialPost<br>SocialMediaPost<br>Dashboard<br>ExternalUrl  Enum: GenesisVision.Core.DataModel.Enums.PlatformLocation")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public NotificationLocationViewModel id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public NotificationLocationViewModel externalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
		return this;
	}

	/**
	 * Get externalUrl
	 *
	 * @return externalUrl
	 **/
	@Schema(description = "")
	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NotificationLocationViewModel notificationLocationViewModel = (NotificationLocationViewModel) o;
		return Objects.equals(this.location, notificationLocationViewModel.location) &&
				Objects.equals(this.id, notificationLocationViewModel.id) &&
				Objects.equals(this.externalUrl, notificationLocationViewModel.externalUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, id, externalUrl);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NotificationLocationViewModel {\n");

		sb.append("    location: ").append(toIndentedString(location)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    externalUrl: ").append(toIndentedString(externalUrl)).append("\n");
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
		out.writeValue(location);
		out.writeValue(id);
		out.writeValue(externalUrl);
	}

	public int describeContents() {
		return 0;
	}
}