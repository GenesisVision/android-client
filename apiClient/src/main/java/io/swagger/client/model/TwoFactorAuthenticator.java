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
 * TwoFactorAuthenticator
 */


public class TwoFactorAuthenticator implements Parcelable
{
	public static final Parcelable.Creator<TwoFactorAuthenticator> CREATOR = new Parcelable.Creator<TwoFactorAuthenticator>()
	{
		public TwoFactorAuthenticator createFromParcel(Parcel in) {
			return new TwoFactorAuthenticator(in);
		}

		public TwoFactorAuthenticator[] newArray(int size) {
			return new TwoFactorAuthenticator[size];
		}
	};

	@SerializedName("sharedKey")
	private String sharedKey = null;

	@SerializedName("authenticatorUri")
	private String authenticatorUri = null;

	public TwoFactorAuthenticator() {
	}

	TwoFactorAuthenticator(Parcel in) {
		sharedKey = (String) in.readValue(null);
		authenticatorUri = (String) in.readValue(null);
	}

	public TwoFactorAuthenticator authenticatorUri(String authenticatorUri) {
		this.authenticatorUri = authenticatorUri;
		return this;
	}

	public TwoFactorAuthenticator sharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
		return this;
	}

	/**
	 * Get sharedKey
	 *
	 * @return sharedKey
	 **/
	@Schema(description = "")
	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sharedKey, authenticatorUri);
	}

	/**
	 * Get authenticatorUri
	 *
	 * @return authenticatorUri
	 **/
	@Schema(description = "")
	public String getAuthenticatorUri() {
		return authenticatorUri;
	}

	public void setAuthenticatorUri(String authenticatorUri) {
		this.authenticatorUri = authenticatorUri;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TwoFactorAuthenticator twoFactorAuthenticator = (TwoFactorAuthenticator) o;
		return Objects.equals(this.sharedKey, twoFactorAuthenticator.sharedKey) &&
				Objects.equals(this.authenticatorUri, twoFactorAuthenticator.authenticatorUri);
	}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TwoFactorAuthenticator {\n");

    sb.append("    sharedKey: ").append(toIndentedString(sharedKey)).append("\n");
    sb.append("    authenticatorUri: ").append(toIndentedString(authenticatorUri)).append("\n");
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
		out.writeValue(sharedKey);
		out.writeValue(authenticatorUri);
	}

	public int describeContents() {
		return 0;
	}
}
