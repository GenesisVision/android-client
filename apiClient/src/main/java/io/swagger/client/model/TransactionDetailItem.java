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
 * TransactionDetailItem
 */


public class TransactionDetailItem implements Parcelable
{
	public static final Parcelable.Creator<TransactionDetailItem> CREATOR = new Parcelable.Creator<TransactionDetailItem>()
	{
		public TransactionDetailItem createFromParcel(Parcel in) {
			return new TransactionDetailItem(in);
		}

		public TransactionDetailItem[] newArray(int size) {
			return new TransactionDetailItem[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("details")
	private String details = null;

	@SerializedName("url")
	private String url = null;

	@SerializedName("canCopy")
	private Boolean canCopy = null;

	public TransactionDetailItem() {
	}

	TransactionDetailItem(Parcel in) {
		title = (String) in.readValue(null);
		details = (String) in.readValue(null);
		url = (String) in.readValue(null);
		canCopy = (Boolean) in.readValue(null);
	}

	public TransactionDetailItem title(String title) {
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

	public TransactionDetailItem details(String details) {
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

	public TransactionDetailItem url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * Get url
	 *
	 * @return url
	 **/
	@Schema(description = "")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TransactionDetailItem canCopy(Boolean canCopy) {
		this.canCopy = canCopy;
		return this;
	}

	/**
	 * Get canCopy
	 *
	 * @return canCopy
	 **/
	@Schema(description = "")
	public Boolean isCanCopy() {
		return canCopy;
	}

	public void setCanCopy(Boolean canCopy) {
		this.canCopy = canCopy;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TransactionDetailItem transactionDetailItem = (TransactionDetailItem) o;
		return Objects.equals(this.title, transactionDetailItem.title) &&
				Objects.equals(this.details, transactionDetailItem.details) &&
				Objects.equals(this.url, transactionDetailItem.url) &&
				Objects.equals(this.canCopy, transactionDetailItem.canCopy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, details, url, canCopy);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TransactionDetailItem {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    details: ").append(toIndentedString(details)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    canCopy: ").append(toIndentedString(canCopy)).append("\n");
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
		out.writeValue(url);
		out.writeValue(canCopy);
	}

	public int describeContents() {
		return 0;
	}
}
