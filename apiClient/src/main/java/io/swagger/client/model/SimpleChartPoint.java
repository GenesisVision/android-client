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

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * SimpleChartPoint
 */


public class SimpleChartPoint implements Parcelable
{
	public static final Parcelable.Creator<SimpleChartPoint> CREATOR = new Parcelable.Creator<SimpleChartPoint>()
	{
		public SimpleChartPoint createFromParcel(Parcel in) {
			return new SimpleChartPoint(in);
		}

		public SimpleChartPoint[] newArray(int size) {
			return new SimpleChartPoint[size];
		}
	};

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("value")
	private Double value = null;

	public SimpleChartPoint() {
	}

	SimpleChartPoint(Parcel in) {
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		value = (Double) in.readValue(null);
	}

	public SimpleChartPoint date(DateTime date) {
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

	public SimpleChartPoint value(Double value) {
		this.value = value;
		return this;
	}

	/**
	 * Get value
	 *
	 * @return value
	 **/
	@Schema(description = "")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SimpleChartPoint simpleChartPoint = (SimpleChartPoint) o;
		return Objects.equals(this.date, simpleChartPoint.date) &&
				Objects.equals(this.value, simpleChartPoint.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SimpleChartPoint {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
		out.writeValue(value);
	}

	public int describeContents() {
		return 0;
	}
}
