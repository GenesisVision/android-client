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
 * ChangePasswordViewModel
 */


public class ChangePasswordViewModel implements Parcelable
{
	@SerializedName("oldPassword")
	private String oldPassword = null;

	@SerializedName("password")
	private String password = null;

	@SerializedName("confirmPassword")
	private String confirmPassword = null;

	public ChangePasswordViewModel() {
	}

	public ChangePasswordViewModel oldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
		return this;
	}

	/**
	 * Get oldPassword
	 *
	 * @return oldPassword
	 **/
	@Schema(required = true, description = "")
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public ChangePasswordViewModel password(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Get password
	 *
	 * @return password
	 **/
	@Schema(required = true, description = "")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ChangePasswordViewModel confirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}

	/**
	 * Get confirmPassword
	 *
	 * @return confirmPassword
	 **/
	@Schema(description = "")
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ChangePasswordViewModel changePasswordViewModel = (ChangePasswordViewModel) o;
		return Objects.equals(this.oldPassword, changePasswordViewModel.oldPassword) &&
				Objects.equals(this.password, changePasswordViewModel.password) &&
				Objects.equals(this.confirmPassword, changePasswordViewModel.confirmPassword);
	}

	@Override
	public int hashCode() {
		return Objects.hash(oldPassword, password, confirmPassword);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ChangePasswordViewModel {\n");

		sb.append("    oldPassword: ").append(toIndentedString(oldPassword)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    confirmPassword: ").append(toIndentedString(confirmPassword)).append("\n");
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
		out.writeValue(oldPassword);
		out.writeValue(password);
		out.writeValue(confirmPassword);
	}

	public static final Parcelable.Creator<ChangePasswordViewModel> CREATOR = new Parcelable.Creator<ChangePasswordViewModel>()
	{
		public ChangePasswordViewModel createFromParcel(Parcel in) {
			return new ChangePasswordViewModel(in);
		}

		public ChangePasswordViewModel[] newArray(int size) {
			return new ChangePasswordViewModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ChangePasswordViewModel(Parcel in) {
		oldPassword = (String) in.readValue(null);
		password = (String) in.readValue(null);
		confirmPassword = (String) in.readValue(null);
	}
}
