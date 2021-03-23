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
 * ProgramDetailsListItemItemsViewModel
 */


public class ProgramDetailsListItemItemsViewModel implements Parcelable
{
	@SerializedName("items")
	private List<ProgramDetailsListItem> items = null;

	@SerializedName("total")
	private Integer total = null;

	public ProgramDetailsListItemItemsViewModel() {
	}

	/**
	 * Get items
	 *
	 * @return items
	 **/
	@Schema(description = "")
	public List<ProgramDetailsListItem> getItems() {
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
		ProgramDetailsListItemItemsViewModel programDetailsListItemItemsViewModel = (ProgramDetailsListItemItemsViewModel) o;
		return Objects.equals(this.items, programDetailsListItemItemsViewModel.items) &&
				Objects.equals(this.total, programDetailsListItemItemsViewModel.total);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, total);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProgramDetailsListItemItemsViewModel {\n");

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

	public static final Parcelable.Creator<ProgramDetailsListItemItemsViewModel> CREATOR = new Parcelable.Creator<ProgramDetailsListItemItemsViewModel>()
	{
		public ProgramDetailsListItemItemsViewModel createFromParcel(Parcel in) {
			return new ProgramDetailsListItemItemsViewModel(in);
		}

		public ProgramDetailsListItemItemsViewModel[] newArray(int size) {
			return new ProgramDetailsListItemItemsViewModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ProgramDetailsListItemItemsViewModel(Parcel in) {
		items = (List<ProgramDetailsListItem>) in.readValue(ProgramDetailsListItem.class.getClassLoader());
		total = (Integer) in.readValue(null);
	}
}
