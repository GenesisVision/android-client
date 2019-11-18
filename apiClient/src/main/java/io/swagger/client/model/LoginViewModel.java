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
 * LoginViewModel
 */


public class LoginViewModel implements Parcelable
{
	public static final Parcelable.Creator<LoginViewModel> CREATOR = new Parcelable.Creator<LoginViewModel>()
	{
		public LoginViewModel createFromParcel(Parcel in) {
			return new LoginViewModel(in);
		}

		public LoginViewModel[] newArray(int size) {
			return new LoginViewModel[size];
		}
	};

	@SerializedName("password")
	private String password = null;

	@SerializedName("rememberMe")
	private Boolean rememberMe = null;

	@SerializedName("twoFactorCode")
	private String twoFactorCode = null;

	@SerializedName("recoveryCode")
	private String recoveryCode = null;

	@SerializedName("client")
	private String client = null;

	@SerializedName("email")
	private String email = null;

	@SerializedName("captchaCheckResult")
	private CaptchaCheckResult captchaCheckResult = null;

	public LoginViewModel() {
	}

	LoginViewModel(Parcel in) {
		password = (String) in.readValue(null);
		rememberMe = (Boolean) in.readValue(null);
		twoFactorCode = (String) in.readValue(null);
		recoveryCode = (String) in.readValue(null);
		client = (String) in.readValue(null);
		email = (String) in.readValue(null);
		captchaCheckResult = (CaptchaCheckResult) in.readValue(CaptchaCheckResult.class.getClassLoader());
	}

	public LoginViewModel password(String password) {
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

	public LoginViewModel rememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
		return this;
	}

	/**
	 * Get rememberMe
	 *
	 * @return rememberMe
	 **/
	@Schema(description = "")
	public Boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public LoginViewModel twoFactorCode(String twoFactorCode) {
		this.twoFactorCode = twoFactorCode;
		return this;
	}

	/**
	 * Get twoFactorCode
	 *
	 * @return twoFactorCode
	 **/
	@Schema(description = "")
	public String getTwoFactorCode() {
		return twoFactorCode;
	}

	public void setTwoFactorCode(String twoFactorCode) {
		this.twoFactorCode = twoFactorCode;
	}

	public LoginViewModel recoveryCode(String recoveryCode) {
		this.recoveryCode = recoveryCode;
		return this;
	}

	/**
	 * Get recoveryCode
	 *
	 * @return recoveryCode
	 **/
	@Schema(description = "")
	public String getRecoveryCode() {
		return recoveryCode;
	}

	public void setRecoveryCode(String recoveryCode) {
		this.recoveryCode = recoveryCode;
	}

	public LoginViewModel client(String client) {
		this.client = client;
		return this;
	}

	/**
	 * Get client
	 *
	 * @return client
	 **/
	@Schema(description = "")
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public LoginViewModel email(String email) {
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

	public LoginViewModel captchaCheckResult(CaptchaCheckResult captchaCheckResult) {
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
		LoginViewModel loginViewModel = (LoginViewModel) o;
		return Objects.equals(this.password, loginViewModel.password) &&
				Objects.equals(this.rememberMe, loginViewModel.rememberMe) &&
				Objects.equals(this.twoFactorCode, loginViewModel.twoFactorCode) &&
				Objects.equals(this.recoveryCode, loginViewModel.recoveryCode) &&
				Objects.equals(this.client, loginViewModel.client) &&
				Objects.equals(this.email, loginViewModel.email) &&
				Objects.equals(this.captchaCheckResult, loginViewModel.captchaCheckResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, rememberMe, twoFactorCode, recoveryCode, client, email, captchaCheckResult);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoginViewModel {\n");

		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    rememberMe: ").append(toIndentedString(rememberMe)).append("\n");
		sb.append("    twoFactorCode: ").append(toIndentedString(twoFactorCode)).append("\n");
		sb.append("    recoveryCode: ").append(toIndentedString(recoveryCode)).append("\n");
		sb.append("    client: ").append(toIndentedString(client)).append("\n");
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
		out.writeValue(password);
		out.writeValue(rememberMe);
		out.writeValue(twoFactorCode);
		out.writeValue(recoveryCode);
		out.writeValue(client);
		out.writeValue(email);
		out.writeValue(captchaCheckResult);
	}

	public int describeContents() {
		return 0;
	}
}
