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
 * BinanceRawCancelOrderId
 */


public class BinanceRawCancelOrderId implements Parcelable
{
	@SerializedName("id")
	private String id = null;

	@SerializedName("clientOrderId")
	private String clientOrderId = null;

	@SerializedName("ocoOrder")
	private Boolean ocoOrder = null;

	public BinanceRawCancelOrderId() {
	}

	public BinanceRawCancelOrderId id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(description = "")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BinanceRawCancelOrderId clientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
		return this;
	}

	/**
	 * Get clientOrderId
	 *
	 * @return clientOrderId
	 **/
	@Schema(description = "")
	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public BinanceRawCancelOrderId ocoOrder(Boolean ocoOrder) {
		this.ocoOrder = ocoOrder;
		return this;
	}

	/**
	 * Get ocoOrder
	 *
	 * @return ocoOrder
	 **/
	@Schema(description = "")
	public Boolean isOcoOrder() {
		return ocoOrder;
	}

	public void setOcoOrder(Boolean ocoOrder) {
		this.ocoOrder = ocoOrder;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawCancelOrderId binanceRawCancelOrderId = (BinanceRawCancelOrderId) o;
		return Objects.equals(this.id, binanceRawCancelOrderId.id) &&
				Objects.equals(this.clientOrderId, binanceRawCancelOrderId.clientOrderId) &&
				Objects.equals(this.ocoOrder, binanceRawCancelOrderId.ocoOrder);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, clientOrderId, ocoOrder);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawCancelOrderId {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    clientOrderId: ").append(toIndentedString(clientOrderId)).append("\n");
		sb.append("    ocoOrder: ").append(toIndentedString(ocoOrder)).append("\n");
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
		out.writeValue(clientOrderId);
		out.writeValue(ocoOrder);
	}

	public static final Parcelable.Creator<BinanceRawCancelOrderId> CREATOR = new Parcelable.Creator<BinanceRawCancelOrderId>()
	{
		public BinanceRawCancelOrderId createFromParcel(Parcel in) {
			return new BinanceRawCancelOrderId(in);
		}

		public BinanceRawCancelOrderId[] newArray(int size) {
			return new BinanceRawCancelOrderId[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	BinanceRawCancelOrderId(Parcel in) {
		id = (String) in.readValue(null);
		clientOrderId = (String) in.readValue(null);
		ocoOrder = (Boolean) in.readValue(null);
	}
}
