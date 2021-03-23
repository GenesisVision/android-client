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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ReallocationModel
 */


public class ReallocationModel implements Parcelable
{
	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("parts")
	private List<FundAssetPartWithIcon> parts = null;

	public ReallocationModel() {
	}

	public ReallocationModel date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@Schema(description = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public ReallocationModel parts(List<FundAssetPartWithIcon> parts) {
		this.parts = parts;
		return this;
	}

	public ReallocationModel addPartsItem(FundAssetPartWithIcon partsItem) {
		if (this.parts == null) {
			this.parts = new ArrayList<FundAssetPartWithIcon>();
		}
		this.parts.add(partsItem);
		return this;
	}

	/**
	 * Get parts
	 *
	 * @return parts
	 **/
	@Schema(description = "")
	public List<FundAssetPartWithIcon> getParts() {
		return parts;
	}

	public void setParts(List<FundAssetPartWithIcon> parts) {
		this.parts = parts;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReallocationModel reallocationModel = (ReallocationModel) o;
		return Objects.equals(this.date, reallocationModel.date) &&
				Objects.equals(this.parts, reallocationModel.parts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, parts);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ReallocationModel {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    parts: ").append(toIndentedString(parts)).append("\n");
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
		out.writeValue(parts);
	}

	public static final Parcelable.Creator<ReallocationModel> CREATOR = new Parcelable.Creator<ReallocationModel>()
	{
		public ReallocationModel createFromParcel(Parcel in) {
			return new ReallocationModel(in);
		}

		public ReallocationModel[] newArray(int size) {
			return new ReallocationModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	ReallocationModel(Parcel in) {
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		parts = (List<FundAssetPartWithIcon>) in.readValue(FundAssetPartWithIcon.class.getClassLoader());
	}
}
