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
 * PersonalFollowDetailsList
 */


public class PersonalFollowDetailsList implements Parcelable
{
	@SerializedName("isOwnAsset")
	private Boolean isOwnAsset = null;

	@SerializedName("isFavorite")
	private Boolean isFavorite = null;

	public PersonalFollowDetailsList() {
	}

	public PersonalFollowDetailsList isOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
		return this;
	}

	/**
	 * Get isOwnAsset
	 *
	 * @return isOwnAsset
	 **/
	@Schema(description = "")
	public Boolean isIsOwnAsset() {
		return isOwnAsset;
	}

	public void setIsOwnAsset(Boolean isOwnAsset) {
		this.isOwnAsset = isOwnAsset;
	}

	public PersonalFollowDetailsList isFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
		return this;
	}

	/**
	 * Get isFavorite
	 *
	 * @return isFavorite
	 **/
	@Schema(description = "")
	public Boolean isIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonalFollowDetailsList personalFollowDetailsList = (PersonalFollowDetailsList) o;
		return Objects.equals(this.isOwnAsset, personalFollowDetailsList.isOwnAsset) &&
				Objects.equals(this.isFavorite, personalFollowDetailsList.isFavorite);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isOwnAsset, isFavorite);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonalFollowDetailsList {\n");

		sb.append("    isOwnAsset: ").append(toIndentedString(isOwnAsset)).append("\n");
		sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
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
		out.writeValue(isOwnAsset);
		out.writeValue(isFavorite);
	}

	public static final Parcelable.Creator<PersonalFollowDetailsList> CREATOR = new Parcelable.Creator<PersonalFollowDetailsList>()
	{
		public PersonalFollowDetailsList createFromParcel(Parcel in) {
			return new PersonalFollowDetailsList(in);
		}

		public PersonalFollowDetailsList[] newArray(int size) {
			return new PersonalFollowDetailsList[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	PersonalFollowDetailsList(Parcel in) {
		isOwnAsset = (Boolean) in.readValue(null);
		isFavorite = (Boolean) in.readValue(null);
	}
}
