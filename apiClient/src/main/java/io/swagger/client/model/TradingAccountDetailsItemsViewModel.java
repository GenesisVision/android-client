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
 * TradingAccountDetailsItemsViewModel
 */


public class TradingAccountDetailsItemsViewModel implements Parcelable
{
	public static final Parcelable.Creator<TradingAccountDetailsItemsViewModel> CREATOR = new Parcelable.Creator<TradingAccountDetailsItemsViewModel>()
	{
		public TradingAccountDetailsItemsViewModel createFromParcel(Parcel in) {
			return new TradingAccountDetailsItemsViewModel(in);
		}

		public TradingAccountDetailsItemsViewModel[] newArray(int size) {
			return new TradingAccountDetailsItemsViewModel[size];
		}
	};

	@SerializedName("items")
	private List<TradingAccountDetails> items = null;

	@SerializedName("total")
	private Integer total = null;

	public TradingAccountDetailsItemsViewModel() {
	}

	TradingAccountDetailsItemsViewModel(Parcel in) {
		items = (List<TradingAccountDetails>) in.readValue(TradingAccountDetails.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	public TradingAccountDetailsItemsViewModel addItemsItem(TradingAccountDetails itemsItem) {
		if (this.items == null) {
			this.items = new ArrayList<TradingAccountDetails>();
		}
		this.items.add(itemsItem);
		return this;
	}

	public TradingAccountDetailsItemsViewModel total(Integer total) {
		this.total = total;
		return this;
	}

	public TradingAccountDetailsItemsViewModel items(List<TradingAccountDetails> items) {
		this.items = items;
		return this;
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<TradingAccountDetails> getItems() {
		return items;
	}

	public void setItems(List<TradingAccountDetails> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
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
		TradingAccountDetailsItemsViewModel tradingAccountDetailsItemsViewModel = (TradingAccountDetailsItemsViewModel) o;
		return Objects.equals(this.items, tradingAccountDetailsItemsViewModel.items) &&
				Objects.equals(this.total, tradingAccountDetailsItemsViewModel.total);
	}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TradingAccountDetailsItemsViewModel {\n");

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
