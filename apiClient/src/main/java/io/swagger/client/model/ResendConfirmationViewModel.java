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
 * ResendConfirmationViewModel
 */


public class ResendConfirmationViewModel implements Parcelable
{
	public static final Parcelable.Creator<ResendConfirmationViewModel> CREATOR = new Parcelable.Creator<ResendConfirmationViewModel>()
	{
		public ResendConfirmationViewModel createFromParcel(Parcel in) {
			return new ResendConfirmationViewModel(in);
		}

		public ResendConfirmationViewModel[] newArray(int size) {
			return new ResendConfirmationViewModel[size];
		}
	};

	@SerializedName("email")
	private String email = null;

	@SerializedName("captchaCheckResult")
	private CaptchaCheckResult captchaCheckResult = null;

	public ResendConfirmationViewModel() {
	}

	ResendConfirmationViewModel(Parcel in) {
		email = (String) in.readValue(null);
		captchaCheckResult = (CaptchaCheckResult) in.readValue(CaptchaCheckResult.class.getClassLoader());
	}

	public ResendConfirmationViewModel email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 *
	 * @return email
	 **/
	@Schema(required = true, description = "")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ResendConfirmationViewModel captchaCheckResult(CaptchaCheckResult captchaCheckResult) {
		this.captchaCheckResult = captchaCheckResult;
		return this;
	}

	/**
	 * Get captchaCheckResult
	 *
	 * @return captchaCheckResult
	 **/
	@Schema(description = "")
	public CaptchaCheckResult getCaptchaCheckResult() {
		return captchaCheckResult;
	}

	public void setCaptchaCheckResult(CaptchaCheckResult captchaCheckResult) {
		this.captchaCheckResult = captchaCheckResult;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResendConfirmationViewModel resendConfirmationViewModel = (ResendConfirmationViewModel) o;
		return Objects.equals(this.email, resendConfirmationViewModel.email) &&
				Objects.equals(this.captchaCheckResult, resendConfirmationViewModel.captchaCheckResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, captchaCheckResult);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResendConfirmationViewModel {\n");

		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    captchaCheckResult: ").append(toIndentedString(captchaCheckResult)).append("\n");
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
		out.writeValue(email);
		out.writeValue(captchaCheckResult);
	}

	public int describeContents() {
		return 0;
	}
}
