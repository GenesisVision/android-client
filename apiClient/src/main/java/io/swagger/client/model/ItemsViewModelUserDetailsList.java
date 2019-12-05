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
 * ItemsViewModelUserDetailsList
 */


public class ItemsViewModelUserDetailsList implements Parcelable
{
	public static final Parcelable.Creator<ItemsViewModelUserDetailsList> CREATOR = new Parcelable.Creator<ItemsViewModelUserDetailsList>()
	{
		public ItemsViewModelUserDetailsList createFromParcel(Parcel in) {
			return new ItemsViewModelUserDetailsList(in);
		}

		public ItemsViewModelUserDetailsList[] newArray(int size) {
			return new ItemsViewModelUserDetailsList[size];
		}
	};

	@SerializedName("items")
	private List<UserDetailsList> items = null;

	@SerializedName("total")
	private Integer total = null;

	public ItemsViewModelUserDetailsList() {
	}

	ItemsViewModelUserDetailsList(Parcel in) {
		items = (List<UserDetailsList>) in.readValue(UserDetailsList.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<UserDetailsList> getItems() {
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
		ItemsViewModelUserDetailsList itemsViewModelUserDetailsList = (ItemsViewModelUserDetailsList) o;
		return Objects.equals(this.items, itemsViewModelUserDetailsList.items) &&
				Objects.equals(this.total, itemsViewModelUserDetailsList.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ItemsViewModelUserDetailsList {\n");

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
