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
 * ErrorMessage
 */


public class ErrorMessage implements Parcelable
{
	public static final Parcelable.Creator<ErrorMessage> CREATOR = new Parcelable.Creator<ErrorMessage>()
	{
		public ErrorMessage createFromParcel(Parcel in) {
			return new ErrorMessage(in);
		}

		public ErrorMessage[] newArray(int size) {
			return new ErrorMessage[size];
		}
	};

	@SerializedName("message")
	private String message = null;

	@SerializedName("property")
	private String property = null;

	public ErrorMessage() {
	}

	ErrorMessage(Parcel in) {
		message = (String) in.readValue(null);
		property = (String) in.readValue(null);
	}

	public ErrorMessage message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Get message
	 *
	 * @return message
	 **/
	@Schema(description = "")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessage property(String property) {
		this.property = property;
		return this;
	}

	/**
	 * Get property
	 *
	 * @return property
	 **/
	@Schema(description = "")
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorMessage errorMessage = (ErrorMessage) o;
		return Objects.equals(this.message, errorMessage.message) &&
				Objects.equals(this.property, errorMessage.property);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, property);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ErrorMessage {\n");

		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    property: ").append(toIndentedString(property)).append("\n");
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
		out.writeValue(message);
		out.writeValue(property);
	}

	public int describeContents() {
		return 0;
	}
}
