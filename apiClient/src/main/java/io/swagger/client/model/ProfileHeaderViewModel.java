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
 * ProfileHeaderViewModel
 */


public class ProfileHeaderViewModel implements Parcelable
{
	public static final Parcelable.Creator<ProfileHeaderViewModel> CREATOR = new Parcelable.Creator<ProfileHeaderViewModel>()
	{
		public ProfileHeaderViewModel createFromParcel(Parcel in) {
			return new ProfileHeaderViewModel(in);
		}

		public ProfileHeaderViewModel[] newArray(int size) {
			return new ProfileHeaderViewModel[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("email")
	private String email = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("avatar")
	private String avatar = null;

	@SerializedName("countryCode")
	private String countryCode = null;

	@SerializedName("notificationsCount")
	private Integer notificationsCount = null;

	@SerializedName("isKycConfirmed")
	private Boolean isKycConfirmed = null;

	@SerializedName("isForexAllowed")
	private Boolean isForexAllowed = null;

	@SerializedName("isTwoFactorEnabled")
	private Boolean isTwoFactorEnabled = null;

	@SerializedName("isNewUser")
	private Boolean isNewUser = null;

	@SerializedName("isPublicInvestor")
	private Boolean isPublicInvestor = null;

	@SerializedName("isUserNameFilled")
	private Boolean isUserNameFilled = null;

	public ProfileHeaderViewModel() {
	}

	ProfileHeaderViewModel(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		name = (String) in.readValue(null);
		email = (String) in.readValue(null);
		url = (String) in.readValue(null);
		avatar = (String) in.readValue(null);
		countryCode = (String) in.readValue(null);
		notificationsCount = (Integer) in.readValue(null);
		isKycConfirmed = (Boolean) in.readValue(null);
		isForexAllowed = (Boolean) in.readValue(null);
		isTwoFactorEnabled = (Boolean) in.readValue(null);
		isNewUser = (Boolean) in.readValue(null);
		isPublicInvestor = (Boolean) in.readValue(null);
		isUserNameFilled = (Boolean) in.readValue(null);
	}

	public ProfileHeaderViewModel id(UUID id) {
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

	public ProfileHeaderViewModel name(String name) {
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

	public ProfileHeaderViewModel email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 *
	 * @return email
	 **/
	@Schema(description = "")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ProfileHeaderViewModel url(String url) {
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

	public ProfileHeaderViewModel avatar(String avatar) {
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

	public ProfileHeaderViewModel countryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * Get countryCode
	 *
	 * @return countryCode
	 **/
	@Schema(description = "")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public ProfileHeaderViewModel notificationsCount(Integer notificationsCount) {
		this.notificationsCount = notificationsCount;
		return this;
	}

	/**
	 * Get notificationsCount
	 *
	 * @return notificationsCount
	 **/
	@Schema(description = "")
	public Integer getNotificationsCount() {
		return notificationsCount;
	}

	public void setNotificationsCount(Integer notificationsCount) {
		this.notificationsCount = notificationsCount;
	}

	public ProfileHeaderViewModel isKycConfirmed(Boolean isKycConfirmed) {
		this.isKycConfirmed = isKycConfirmed;
		return this;
	}

	/**
	 * Get isKycConfirmed
	 *
	 * @return isKycConfirmed
	 **/
	@Schema(description = "")
	public Boolean isIsKycConfirmed() {
		return isKycConfirmed;
	}

	public void setIsKycConfirmed(Boolean isKycConfirmed) {
		this.isKycConfirmed = isKycConfirmed;
	}

	public ProfileHeaderViewModel isForexAllowed(Boolean isForexAllowed) {
		this.isForexAllowed = isForexAllowed;
		return this;
	}

	/**
	 * Get isForexAllowed
	 *
	 * @return isForexAllowed
	 **/
	@Schema(description = "")
	public Boolean isIsForexAllowed() {
		return isForexAllowed;
	}

	public void setIsForexAllowed(Boolean isForexAllowed) {
		this.isForexAllowed = isForexAllowed;
	}

	public ProfileHeaderViewModel isTwoFactorEnabled(Boolean isTwoFactorEnabled) {
		this.isTwoFactorEnabled = isTwoFactorEnabled;
		return this;
	}

	/**
	 * Get isTwoFactorEnabled
	 *
	 * @return isTwoFactorEnabled
	 **/
	@Schema(description = "")
	public Boolean isIsTwoFactorEnabled() {
		return isTwoFactorEnabled;
	}

	public void setIsTwoFactorEnabled(Boolean isTwoFactorEnabled) {
		this.isTwoFactorEnabled = isTwoFactorEnabled;
	}

	public ProfileHeaderViewModel isNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
		return this;
	}

	/**
	 * Get isNewUser
	 *
	 * @return isNewUser
	 **/
	@Schema(description = "")
	public Boolean isIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
	}

	public ProfileHeaderViewModel isPublicInvestor(Boolean isPublicInvestor) {
		this.isPublicInvestor = isPublicInvestor;
		return this;
	}

	/**
	 * Get isPublicInvestor
	 *
	 * @return isPublicInvestor
	 **/
	@Schema(description = "")
	public Boolean isIsPublicInvestor() {
		return isPublicInvestor;
	}

	public void setIsPublicInvestor(Boolean isPublicInvestor) {
		this.isPublicInvestor = isPublicInvestor;
	}

	public ProfileHeaderViewModel isUserNameFilled(Boolean isUserNameFilled) {
		this.isUserNameFilled = isUserNameFilled;
		return this;
	}

	/**
	 * Get isUserNameFilled
	 *
	 * @return isUserNameFilled
	 **/
	@Schema(description = "")
	public Boolean isIsUserNameFilled() {
		return isUserNameFilled;
	}

	public void setIsUserNameFilled(Boolean isUserNameFilled) {
		this.isUserNameFilled = isUserNameFilled;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProfileHeaderViewModel profileHeaderViewModel = (ProfileHeaderViewModel) o;
		return Objects.equals(this.id, profileHeaderViewModel.id) &&
				Objects.equals(this.name, profileHeaderViewModel.name) &&
				Objects.equals(this.email, profileHeaderViewModel.email) &&
				Objects.equals(this.url, profileHeaderViewModel.url) &&
				Objects.equals(this.avatar, profileHeaderViewModel.avatar) &&
				Objects.equals(this.countryCode, profileHeaderViewModel.countryCode) &&
				Objects.equals(this.notificationsCount, profileHeaderViewModel.notificationsCount) &&
				Objects.equals(this.isKycConfirmed, profileHeaderViewModel.isKycConfirmed) &&
				Objects.equals(this.isForexAllowed, profileHeaderViewModel.isForexAllowed) &&
				Objects.equals(this.isTwoFactorEnabled, profileHeaderViewModel.isTwoFactorEnabled) &&
				Objects.equals(this.isNewUser, profileHeaderViewModel.isNewUser) &&
				Objects.equals(this.isPublicInvestor, profileHeaderViewModel.isPublicInvestor) &&
				Objects.equals(this.isUserNameFilled, profileHeaderViewModel.isUserNameFilled);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email, url, avatar, countryCode, notificationsCount, isKycConfirmed, isForexAllowed, isTwoFactorEnabled, isNewUser, isPublicInvestor, isUserNameFilled);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProfileHeaderViewModel {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    avatar: ").append(toIndentedString(avatar)).append("\n");
		sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
		sb.append("    notificationsCount: ").append(toIndentedString(notificationsCount)).append("\n");
		sb.append("    isKycConfirmed: ").append(toIndentedString(isKycConfirmed)).append("\n");
		sb.append("    isForexAllowed: ").append(toIndentedString(isForexAllowed)).append("\n");
		sb.append("    isTwoFactorEnabled: ").append(toIndentedString(isTwoFactorEnabled)).append("\n");
		sb.append("    isNewUser: ").append(toIndentedString(isNewUser)).append("\n");
		sb.append("    isPublicInvestor: ").append(toIndentedString(isPublicInvestor)).append("\n");
		sb.append("    isUserNameFilled: ").append(toIndentedString(isUserNameFilled)).append("\n");
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
		out.writeValue(id);
		out.writeValue(name);
		out.writeValue(email);
		out.writeValue(url);
		out.writeValue(avatar);
		out.writeValue(countryCode);
		out.writeValue(notificationsCount);
		out.writeValue(isKycConfirmed);
		out.writeValue(isForexAllowed);
		out.writeValue(isTwoFactorEnabled);
		out.writeValue(isNewUser);
		out.writeValue(isPublicInvestor);
		out.writeValue(isUserNameFilled);
	}

	public int describeContents() {
		return 0;
	}
}
