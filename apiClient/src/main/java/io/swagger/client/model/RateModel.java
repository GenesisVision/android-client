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
 * RateModel
 */


public class RateModel implements Parcelable
{
	public static final Parcelable.Creator<RateModel> CREATOR = new Parcelable.Creator<RateModel>()
	{
		public RateModel createFromParcel(Parcel in) {
			return new RateModel(in);
		}

		public RateModel[] newArray(int size) {
			return new RateModel[size];
		}
	};

	@SerializedName("rate")
	private Double rate = null;

	public RateModel() {
	}

	RateModel(Parcel in) {
		rate = (Double) in.readValue(null);
	}

	public RateModel rate(Double rate) {
		this.rate = rate;
		return this;
	}

	/**
	 * Get rate
	 *
	 * @return rate
	 **/
	@Schema(description = "")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RateModel rateModel = (RateModel) o;
		return Objects.equals(this.rate, rateModel.rate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RateModel {\n");

		sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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
		out.writeValue(rate);
	}

	public int describeContents() {
		return 0;
	}
}
