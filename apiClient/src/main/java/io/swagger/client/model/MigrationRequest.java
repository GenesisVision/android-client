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
 * MigrationRequest
 */


public class MigrationRequest implements Parcelable
{
	public static final Parcelable.Creator<MigrationRequest> CREATOR = new Parcelable.Creator<MigrationRequest>()
	{
		public MigrationRequest createFromParcel(Parcel in) {
			return new MigrationRequest(in);
		}

		public MigrationRequest[] newArray(int size) {
			return new MigrationRequest[size];
		}
	};

	@SerializedName("dateCreate")
	private DateTime dateCreate = null;

	@SerializedName("newLeverage")
	private Integer newLeverage = null;

	@SerializedName("newBroker")
	private Broker newBroker = null;

	public MigrationRequest() {
	}

	MigrationRequest(Parcel in) {
		dateCreate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		newLeverage = (Integer) in.readValue(null);
		newBroker = (Broker) in.readValue(Broker.class.getClassLoader());
	}

	public MigrationRequest dateCreate(DateTime dateCreate) {
		this.dateCreate = dateCreate;
		return this;
	}

	/**
	 * Get dateCreate
	 *
	 * @return dateCreate
	 **/
	@Schema(description = "")
	public DateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(DateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public MigrationRequest newLeverage(Integer newLeverage) {
		this.newLeverage = newLeverage;
		return this;
	}

	/**
	 * Get newLeverage
	 *
	 * @return newLeverage
	 **/
	@Schema(description = "")
	public Integer getNewLeverage() {
		return newLeverage;
	}

	public void setNewLeverage(Integer newLeverage) {
		this.newLeverage = newLeverage;
	}

	public MigrationRequest newBroker(Broker newBroker) {
		this.newBroker = newBroker;
		return this;
	}

	/**
	 * Get newBroker
	 *
	 * @return newBroker
	 **/
	@Schema(description = "")
	public Broker getNewBroker() {
		return newBroker;
	}

	public void setNewBroker(Broker newBroker) {
		this.newBroker = newBroker;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MigrationRequest migrationRequest = (MigrationRequest) o;
		return Objects.equals(this.dateCreate, migrationRequest.dateCreate) &&
				Objects.equals(this.newLeverage, migrationRequest.newLeverage) &&
				Objects.equals(this.newBroker, migrationRequest.newBroker);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCreate, newLeverage, newBroker);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MigrationRequest {\n");

		sb.append("    dateCreate: ").append(toIndentedString(dateCreate)).append("\n");
		sb.append("    newLeverage: ").append(toIndentedString(newLeverage)).append("\n");
		sb.append("    newBroker: ").append(toIndentedString(newBroker)).append("\n");
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
		out.writeValue(dateCreate);
		out.writeValue(newLeverage);
		out.writeValue(newBroker);
	}

	public int describeContents() {
		return 0;
	}
}
