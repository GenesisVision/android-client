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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ProfilePublic
 */


public class ProfilePublic implements Parcelable
{
	public static final Parcelable.Creator<ProfilePublic> CREATOR = new Parcelable.Creator<ProfilePublic>()
	{
		public ProfilePublic createFromParcel(Parcel in) {
			return new ProfilePublic(in);
		}

		public ProfilePublic[] newArray(int size) {
			return new ProfilePublic[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("username")
	private String username = null;

	@SerializedName("logoUrl")
	private String logoUrl = null;

	@SerializedName("registrationDate")
	private DateTime registrationDate = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("socialLinks")
	private List<SocialLinkViewModel> socialLinks = null;

	public ProfilePublic() {
	}

	ProfilePublic(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		username = (String) in.readValue(null);
		logoUrl = (String) in.readValue(null);
		registrationDate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		url = (String) in.readValue(null);
		socialLinks = (List<SocialLinkViewModel>) in.readValue(SocialLinkViewModel.class.getClassLoader());
	}

	public ProfilePublic id(UUID id) {
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

	public ProfilePublic username(String username) {
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

	public ProfilePublic logoUrl(String logoUrl) {
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

	public ProfilePublic registrationDate(DateTime registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}

	/**
	 * Get registrationDate
	 *
	 * @return registrationDate
	 **/
	@Schema(description = "")
	public DateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(DateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public ProfilePublic url(String url) {
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

	public ProfilePublic socialLinks(List<SocialLinkViewModel> socialLinks) {
		this.socialLinks = socialLinks;
		return this;
	}

	public ProfilePublic addSocialLinksItem(SocialLinkViewModel socialLinksItem) {
		if (this.socialLinks == null) {
			this.socialLinks = new ArrayList<SocialLinkViewModel>();
		}
		this.socialLinks.add(socialLinksItem);
		return this;
	}

	/**
	 * Get socialLinks
	 *
	 * @return socialLinks
	 **/
	@Schema(description = "")
	public List<SocialLinkViewModel> getSocialLinks() {
		return socialLinks;
	}

	public void setSocialLinks(List<SocialLinkViewModel> socialLinks) {
		this.socialLinks = socialLinks;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProfilePublic profilePublic = (ProfilePublic) o;
		return Objects.equals(this.id, profilePublic.id) &&
				Objects.equals(this.username, profilePublic.username) &&
				Objects.equals(this.logoUrl, profilePublic.logoUrl) &&
				Objects.equals(this.registrationDate, profilePublic.registrationDate) &&
				Objects.equals(this.url, profilePublic.url) &&
				Objects.equals(this.socialLinks, profilePublic.socialLinks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, logoUrl, registrationDate, url, socialLinks);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProfilePublic {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
		sb.append("    registrationDate: ").append(toIndentedString(registrationDate)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    socialLinks: ").append(toIndentedString(socialLinks)).append("\n");
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
		out.writeValue(username);
		out.writeValue(logoUrl);
		out.writeValue(registrationDate);
		out.writeValue(url);
		out.writeValue(socialLinks);
	}

	public int describeContents() {
		return 0;
	}
}
