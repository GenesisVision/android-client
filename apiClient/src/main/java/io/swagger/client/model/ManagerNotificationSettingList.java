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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ManagerNotificationSettingList
 */


public class ManagerNotificationSettingList implements Parcelable
{
	public static final Parcelable.Creator<ManagerNotificationSettingList> CREATOR = new Parcelable.Creator<ManagerNotificationSettingList>()
	{
		public ManagerNotificationSettingList createFromParcel(Parcel in) {
			return new ManagerNotificationSettingList(in);
		}

		public ManagerNotificationSettingList[] newArray(int size) {
			return new ManagerNotificationSettingList[size];
		}
	};

	@SerializedName("managerId")
	private UUID managerId = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("username")
	private String username = null;

	@SerializedName("avatar")
	private String avatar = null;

	@SerializedName("about")
	private String about = null;

	@SerializedName("settingsGeneral")
	private List<NotificationSettingViewModel> settingsGeneral = null;

	public ManagerNotificationSettingList() {
	}

	ManagerNotificationSettingList(Parcel in) {
		managerId = (UUID) in.readValue(UUID.class.getClassLoader());
		url = (String) in.readValue(null);
		username = (String) in.readValue(null);
		avatar = (String) in.readValue(null);
		about = (String) in.readValue(null);
		settingsGeneral = (List<NotificationSettingViewModel>) in.readValue(NotificationSettingViewModel.class.getClassLoader());
	}

	public ManagerNotificationSettingList managerId(UUID managerId) {
		this.managerId = managerId;
		return this;
	}

	/**
	 * Get managerId
	 *
	 * @return managerId
	 **/
	@Schema(description = "")
	public UUID getManagerId() {
		return managerId;
	}

	public void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}

	public ManagerNotificationSettingList url(String url) {
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

	public ManagerNotificationSettingList username(String username) {
		this.username = username;
		return this;
	}

	/**
	 * Get username
	 *
	 * @return username
	 **/
	@Schema(description = "")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ManagerNotificationSettingList avatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	/**
	 * Get avatar
	 *
	 * @return avatar
	 **/
	@Schema(description = "")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public ManagerNotificationSettingList about(String about) {
		this.about = about;
		return this;
	}

	/**
	 * Get about
	 *
	 * @return about
	 **/
	@Schema(description = "")
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public ManagerNotificationSettingList settingsGeneral(List<NotificationSettingViewModel> settingsGeneral) {
		this.settingsGeneral = settingsGeneral;
		return this;
	}

	public ManagerNotificationSettingList addSettingsGeneralItem(NotificationSettingViewModel settingsGeneralItem) {
		if (this.settingsGeneral == null) {
			this.settingsGeneral = new ArrayList<NotificationSettingViewModel>();
		}
		this.settingsGeneral.add(settingsGeneralItem);
		return this;
	}

	/**
	 * Get settingsGeneral
	 *
	 * @return settingsGeneral
	 **/
	@Schema(description = "")
	public List<NotificationSettingViewModel> getSettingsGeneral() {
		return settingsGeneral;
	}

	public void setSettingsGeneral(List<NotificationSettingViewModel> settingsGeneral) {
		this.settingsGeneral = settingsGeneral;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ManagerNotificationSettingList managerNotificationSettingList = (ManagerNotificationSettingList) o;
		return Objects.equals(this.managerId, managerNotificationSettingList.managerId) &&
				Objects.equals(this.url, managerNotificationSettingList.url) &&
				Objects.equals(this.username, managerNotificationSettingList.username) &&
				Objects.equals(this.avatar, managerNotificationSettingList.avatar) &&
				Objects.equals(this.about, managerNotificationSettingList.about) &&
				Objects.equals(this.settingsGeneral, managerNotificationSettingList.settingsGeneral);
	}

	@Override
	public int hashCode() {
		return Objects.hash(managerId, url, username, avatar, about, settingsGeneral);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ManagerNotificationSettingList {\n");

		sb.append("    managerId: ").append(toIndentedString(managerId)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    avatar: ").append(toIndentedString(avatar)).append("\n");
		sb.append("    about: ").append(toIndentedString(about)).append("\n");
		sb.append("    settingsGeneral: ").append(toIndentedString(settingsGeneral)).append("\n");
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
		out.writeValue(managerId);
		out.writeValue(url);
		out.writeValue(username);
		out.writeValue(avatar);
		out.writeValue(about);
		out.writeValue(settingsGeneral);
	}

	public int describeContents() {
		return 0;
	}
}
