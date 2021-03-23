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
 * TimestampDate
 */


public class TimestampDate implements Parcelable
{
	@SerializedName("date")
	private Long date = null;

	public TimestampDate() {
	}

	public TimestampDate date(Long date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@Schema(description = "")
	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TimestampDate timestampDate = (TimestampDate) o;
		return Objects.equals(this.date, timestampDate.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TimestampDate {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
		out.writeValue(date);
	}

	public static final Parcelable.Creator<TimestampDate> CREATOR = new Parcelable.Creator<TimestampDate>()
	{
		public TimestampDate createFromParcel(Parcel in) {
			return new TimestampDate(in);
		}

		public TimestampDate[] newArray(int size) {
			return new TimestampDate[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	TimestampDate(Parcel in) {
		date = (Long) in.readValue(null);
	}
}
