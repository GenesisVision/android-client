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
 * UpdateProfileViewModel
 */


public class UpdateProfileViewModel implements Parcelable
{
	public static final Parcelable.Creator<UpdateProfileViewModel> CREATOR = new Parcelable.Creator<UpdateProfileViewModel>()
	{
		public UpdateProfileViewModel createFromParcel(Parcel in) {
			return new UpdateProfileViewModel(in);
		}

		public UpdateProfileViewModel[] newArray(int size) {
			return new UpdateProfileViewModel[size];
		}
	};

	@SerializedName("userName")
	private String userName = null;

	@SerializedName("about")
	private String about = null;

	public UpdateProfileViewModel() {
	}

	UpdateProfileViewModel(Parcel in) {
		userName = (String) in.readValue(null);
		about = (String) in.readValue(null);
	}

	public UpdateProfileViewModel about(String about) {
		this.about = about;
		return this;
	}

	public UpdateProfileViewModel userName(String userName) {
		this.userName = userName;
		return this;
	}

	/**
	 * Get userName
	 *
	 * @return userName
	 **/
	@Schema(description = "")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, about);
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UpdateProfileViewModel updateProfileViewModel = (UpdateProfileViewModel) o;
		return Objects.equals(this.userName, updateProfileViewModel.userName) &&
				Objects.equals(this.about, updateProfileViewModel.about);
	}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateProfileViewModel {\n");

    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    about: ").append(toIndentedString(about)).append("\n");
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
		out.writeValue(userName);
		out.writeValue(about);
	}

	public int describeContents() {
		return 0;
	}
}
