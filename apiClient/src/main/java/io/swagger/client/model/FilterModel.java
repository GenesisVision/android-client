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
 * FilterModel
 */


public class FilterModel implements Parcelable
{
	public static final Parcelable.Creator<FilterModel> CREATOR = new Parcelable.Creator<FilterModel>()
	{
		public FilterModel createFromParcel(Parcel in) {
			return new FilterModel(in);
		}

		public FilterModel[] newArray(int size) {
			return new FilterModel[size];
		}
	};

	@SerializedName("key")
	private String key = null;

	@SerializedName("title")
	private String title = null;

	public FilterModel() {
	}

	FilterModel(Parcel in) {
		key = (String) in.readValue(null);
		title = (String) in.readValue(null);
	}

	public FilterModel key(String key) {
		this.key = key;
		return this;
	}

	/**
	 * Get key
	 *
	 * @return key
	 **/
	@Schema(description = "")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public FilterModel title(String title) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FilterModel filterModel = (FilterModel) o;
		return Objects.equals(this.key, filterModel.key) &&
				Objects.equals(this.title, filterModel.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, title);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FilterModel {\n");

		sb.append("    key: ").append(toIndentedString(key)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
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
		out.writeValue(key);
		out.writeValue(title);
	}

	public int describeContents() {
		return 0;
	}
}
