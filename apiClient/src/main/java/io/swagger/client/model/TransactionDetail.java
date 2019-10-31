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
 * TransactionDetail
 */


public class TransactionDetail implements Parcelable
{
	public static final Parcelable.Creator<TransactionDetail> CREATOR = new Parcelable.Creator<TransactionDetail>()
	{
		public TransactionDetail createFromParcel(Parcel in) {
			return new TransactionDetail(in);
		}

		public TransactionDetail[] newArray(int size) {
			return new TransactionDetail[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("details")
	private String details = null;

	public TransactionDetail() {
	}

	TransactionDetail(Parcel in) {
		title = (String) in.readValue(null);
		details = (String) in.readValue(null);
	}

	public TransactionDetail title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 *
	 * @return title
	 **/
	@Schema(description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TransactionDetail details(String details) {
		this.details = details;
		return this;
	}

	/**
	 * Get details
	 *
	 * @return details
	 **/
	@Schema(description = "")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TransactionDetail transactionDetail = (TransactionDetail) o;
		return Objects.equals(this.title, transactionDetail.title) &&
				Objects.equals(this.details, transactionDetail.details);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, details);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TransactionDetail {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
		out.writeValue(title);
		out.writeValue(details);
	}

	public int describeContents() {
		return 0;
	}
}
