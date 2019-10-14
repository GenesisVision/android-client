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

import org.joda.time.DateTime;

import java.util.Objects;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * ExternalKeyViewModel
 */


public class ExternalKeyViewModel implements Parcelable
{
	public static final Parcelable.Creator<ExternalKeyViewModel> CREATOR = new Parcelable.Creator<ExternalKeyViewModel>()
	{
		public ExternalKeyViewModel createFromParcel(Parcel in) {
			return new ExternalKeyViewModel(in);
		}

		public ExternalKeyViewModel[] newArray(int size) {
			return new ExternalKeyViewModel[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("dateAdd")
	private DateTime dateAdd = null;

	@SerializedName("exchangeName")
	private String exchangeName = null;

	public ExternalKeyViewModel() {
	}

	ExternalKeyViewModel(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		title = (String) in.readValue(null);
		dateAdd = (DateTime) in.readValue(DateTime.class.getClassLoader());
		exchangeName = (String) in.readValue(null);
	}

	public ExternalKeyViewModel id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ExternalKeyViewModel title(String title) {
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

	public ExternalKeyViewModel dateAdd(DateTime dateAdd) {
		this.dateAdd = dateAdd;
		return this;
	}

	/**
	 * Get dateAdd
	 *
	 * @return dateAdd
	 **/
	@Schema(description = "")
	public DateTime getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(DateTime dateAdd) {
		this.dateAdd = dateAdd;
	}

	public ExternalKeyViewModel exchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
		return this;
	}

	/**
	 * Get exchangeName
	 *
	 * @return exchangeName
	 **/
	@Schema(description = "")
	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExternalKeyViewModel externalKeyViewModel = (ExternalKeyViewModel) o;
		return Objects.equals(this.id, externalKeyViewModel.id) &&
				Objects.equals(this.title, externalKeyViewModel.title) &&
				Objects.equals(this.dateAdd, externalKeyViewModel.dateAdd) &&
				Objects.equals(this.exchangeName, externalKeyViewModel.exchangeName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, dateAdd, exchangeName);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExternalKeyViewModel {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    dateAdd: ").append(toIndentedString(dateAdd)).append("\n");
		sb.append("    exchangeName: ").append(toIndentedString(exchangeName)).append("\n");
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
		out.writeValue(id);
		out.writeValue(title);
		out.writeValue(dateAdd);
		out.writeValue(exchangeName);
	}

	public int describeContents() {
		return 0;
	}
}
