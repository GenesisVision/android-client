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

import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ExchangeAssetItemsViewModel
 */


public class ExchangeAssetItemsViewModel implements Parcelable
{
	@SerializedName("items")
	private List<ExchangeAsset> items = null;

	@SerializedName("total")
	private Integer total = null;

	public ExchangeAssetItemsViewModel() {
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<ExchangeAsset> getItems() {
		return items;
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


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExchangeAssetItemsViewModel exchangeAssetItemsViewModel = (ExchangeAssetItemsViewModel) o;
		return Objects.equals(this.items, exchangeAssetItemsViewModel.items) &&
				Objects.equals(this.total, exchangeAssetItemsViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExchangeAssetItemsViewModel {\n");

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

	public static final Parcelable.Creator<ExchangeAssetItemsViewModel> CREATOR = new Parcelable.Creator<ExchangeAssetItemsViewModel>()
	{
		public ExchangeAssetItemsViewModel createFromParcel(Parcel in) {
			return new ExchangeAssetItemsViewModel(in);
		}

		public ExchangeAssetItemsViewModel[] newArray(int size) {
			return new ExchangeAssetItemsViewModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ExchangeAssetItemsViewModel(Parcel in) {
		items = (List<ExchangeAsset>) in.readValue(ExchangeAsset.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}
}
