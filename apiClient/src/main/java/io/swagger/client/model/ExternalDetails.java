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
 * For external transactions
 */
@Schema(description = "For external transactions")

public class ExternalDetails implements Parcelable
{
	public static final Parcelable.Creator<ExternalDetails> CREATOR = new Parcelable.Creator<ExternalDetails>()
	{
		public ExternalDetails createFromParcel(Parcel in) {
			return new ExternalDetails(in);
		}

		public ExternalDetails[] newArray(int size) {
			return new ExternalDetails[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("logo")
	private String logo = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("address")
	private String address = null;

	@SerializedName("url")
	private String url = null;

	public ExternalDetails() {
	}

	ExternalDetails(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		logo = (String) in.readValue(null);
		title = (String) in.readValue(null);
		address = (String) in.readValue(null);
		url = (String) in.readValue(null);
	}

	public ExternalDetails id(UUID id) {
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

	public ExternalDetails logo(String logo) {
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

	public ExternalDetails title(String title) {
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

	public ExternalDetails address(String address) {
		this.address = address;
		return this;
	}

	/**
	 * Get address
	 *
	 * @return address
	 **/
	@Schema(description = "")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ExternalDetails url(String url) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExternalDetails externalDetails = (ExternalDetails) o;
		return Objects.equals(this.id, externalDetails.id) &&
				Objects.equals(this.logo, externalDetails.logo) &&
				Objects.equals(this.title, externalDetails.title) &&
				Objects.equals(this.address, externalDetails.address) &&
				Objects.equals(this.url, externalDetails.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, logo, title, address, url);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExternalDetails {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
		out.writeValue(logo);
		out.writeValue(title);
		out.writeValue(address);
		out.writeValue(url);
	}

	public int describeContents() {
		return 0;
	}
}
