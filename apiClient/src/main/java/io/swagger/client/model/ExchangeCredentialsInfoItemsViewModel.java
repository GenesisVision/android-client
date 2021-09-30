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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ExchangeCredentialsInfoItemsViewModel
 */


public class ExchangeCredentialsInfoItemsViewModel implements Parcelable
{
	public static final Parcelable.Creator<ExchangeCredentialsInfoItemsViewModel> CREATOR = new Parcelable.Creator<ExchangeCredentialsInfoItemsViewModel>()
	{
		public ExchangeCredentialsInfoItemsViewModel createFromParcel(Parcel in) {
			return new ExchangeCredentialsInfoItemsViewModel(in);
		}

		public ExchangeCredentialsInfoItemsViewModel[] newArray(int size) {
			return new ExchangeCredentialsInfoItemsViewModel[size];
		}
	};

	@SerializedName("items")
	private List<ExchangeCredentialsInfo> items = null;

	@SerializedName("total")
	private Integer total = null;

	public ExchangeCredentialsInfoItemsViewModel() {
	}

	ExchangeCredentialsInfoItemsViewModel(Parcel in) {
		items = (List<ExchangeCredentialsInfo>) in.readValue(ExchangeCredentialsInfo.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	public ExchangeCredentialsInfoItemsViewModel items(List<ExchangeCredentialsInfo> items) {
		this.items = items;
		return this;
	}

	public ExchangeCredentialsInfoItemsViewModel addItemsItem(ExchangeCredentialsInfo itemsItem) {
		if (this.items == null) {
			this.items = new ArrayList<ExchangeCredentialsInfo>();
		}
		this.items.add(itemsItem);
		return this;
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<ExchangeCredentialsInfo> getItems() {
		return items;
	}

	public void setItems(List<ExchangeCredentialsInfo> items) {
		this.items = items;
	}

	public ExchangeCredentialsInfoItemsViewModel total(Integer total) {
		this.total = total;
		return this;
	}

	/**
	 * Get total
	 *
	 * @return total
	 **/
	@Schema(description = "")
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExchangeCredentialsInfoItemsViewModel exchangeCredentialsInfoItemsViewModel = (ExchangeCredentialsInfoItemsViewModel) o;
		return Objects.equals(this.items, exchangeCredentialsInfoItemsViewModel.items) &&
				Objects.equals(this.total, exchangeCredentialsInfoItemsViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExchangeCredentialsInfoItemsViewModel {\n");

		sb.append("    items: ").append(toIndentedString(items)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
		out.writeValue(items);
		out.writeValue(total);
	}

	public int describeContents() {
		return 0;
	}
}
