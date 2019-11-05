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
 * OrderModelSignalData
 */


public class OrderModelSignalData implements Parcelable
{
	public static final Parcelable.Creator<OrderModelSignalData> CREATOR = new Parcelable.Creator<OrderModelSignalData>()
	{
		public OrderModelSignalData createFromParcel(Parcel in) {
			return new OrderModelSignalData(in);
		}

		public OrderModelSignalData[] newArray(int size) {
			return new OrderModelSignalData[size];
		}
	};

	@SerializedName("masters")
	private List<SignalDataMaster> masters = null;

	public OrderModelSignalData() {
	}

	OrderModelSignalData(Parcel in) {
		masters = (List<SignalDataMaster>) in.readValue(SignalDataMaster.class.getClassLoader());
	}

	public OrderModelSignalData masters(List<SignalDataMaster> masters) {
		this.masters = masters;
		return this;
	}

	public OrderModelSignalData addMastersItem(SignalDataMaster mastersItem) {
		if (this.masters == null) {
			this.masters = new ArrayList<SignalDataMaster>();
		}
		this.masters.add(mastersItem);
		return this;
	}

	/**
	 * Get masters
	 *
	 * @return masters
	 **/
	@Schema(description = "")
	public List<SignalDataMaster> getMasters() {
		return masters;
	}

	public void setMasters(List<SignalDataMaster> masters) {
		this.masters = masters;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderModelSignalData orderModelSignalData = (OrderModelSignalData) o;
		return Objects.equals(this.masters, orderModelSignalData.masters);
	}

	@Override
	public int hashCode() {
		return Objects.hash(masters);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OrderModelSignalData {\n");

		sb.append("    masters: ").append(toIndentedString(masters)).append("\n");
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
		out.writeValue(masters);
	}

	public int describeContents() {
		return 0;
	}
}
