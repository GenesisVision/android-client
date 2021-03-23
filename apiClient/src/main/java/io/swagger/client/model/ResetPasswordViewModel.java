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
 * ResetPasswordViewModel
 */


public class ResetPasswordViewModel implements Parcelable
{
	@SerializedName("userId")
	private String userId = null;

	@SerializedName("code")
	private String code = null;

	@SerializedName("password")
	private String password = null;

	@SerializedName("confirmPassword")
	private String confirmPassword = null;

	public ResetPasswordViewModel() {
	}

	public ResetPasswordViewModel userId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * Get userId
	 *
	 * @return userId
	 **/
	@Schema(required = true, description = "")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ResetPasswordViewModel code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 **/
	@Schema(required = true, description = "")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResetPasswordViewModel password(String password) {
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

	public ResetPasswordViewModel confirmPassword(String confirmPassword) {
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
		ResetPasswordViewModel resetPasswordViewModel = (ResetPasswordViewModel) o;
		return Objects.equals(this.userId, resetPasswordViewModel.userId) &&
				Objects.equals(this.code, resetPasswordViewModel.code) &&
				Objects.equals(this.password, resetPasswordViewModel.password) &&
				Objects.equals(this.confirmPassword, resetPasswordViewModel.confirmPassword);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, code, password, confirmPassword);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResetPasswordViewModel {\n");

		sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
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
		out.writeValue(userId);
		out.writeValue(code);
		out.writeValue(password);
		out.writeValue(confirmPassword);
	}

	public static final Parcelable.Creator<ResetPasswordViewModel> CREATOR = new Parcelable.Creator<ResetPasswordViewModel>()
	{
		public ResetPasswordViewModel createFromParcel(Parcel in) {
			return new ResetPasswordViewModel(in);
		}

		public ResetPasswordViewModel[] newArray(int size) {
			return new ResetPasswordViewModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ResetPasswordViewModel(Parcel in) {
		userId = (String) in.readValue(null);
		code = (String) in.readValue(null);
		password = (String) in.readValue(null);
		confirmPassword = (String) in.readValue(null);
	}
}
