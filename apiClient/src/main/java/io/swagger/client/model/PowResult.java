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
 * PowResult
 */


public class PowResult implements Parcelable
{
	@SerializedName("prefix")
	private String prefix = null;

	public PowResult() {
	}

	public PowResult prefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	/**
	 * Get prefix
	 *
	 * @return prefix
	 **/
	@Schema(description = "")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PowResult powResult = (PowResult) o;
		return Objects.equals(this.prefix, powResult.prefix);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prefix);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PowResult {\n");

		sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n");
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
		out.writeValue(prefix);
	}

	public static final Parcelable.Creator<PowResult> CREATOR = new Parcelable.Creator<PowResult>()
	{
		public PowResult createFromParcel(Parcel in) {
			return new PowResult(in);
		}

		public PowResult[] newArray(int size) {
			return new PowResult[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	PowResult(Parcel in) {
		prefix = (String) in.readValue(null);
	}
}
