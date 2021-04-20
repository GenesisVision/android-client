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
 * BinanceRawFuturesCancelAllOrders
 */


public class BinanceRawFuturesCancelAllOrders implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawFuturesCancelAllOrders> CREATOR = new Parcelable.Creator<BinanceRawFuturesCancelAllOrders>()
	{
		public BinanceRawFuturesCancelAllOrders createFromParcel(Parcel in) {
			return new BinanceRawFuturesCancelAllOrders(in);
		}

		public BinanceRawFuturesCancelAllOrders[] newArray(int size) {
			return new BinanceRawFuturesCancelAllOrders[size];
		}
	};

	@SerializedName("code")
	private Integer code = null;

	@SerializedName("message")
	private String message = null;

	public BinanceRawFuturesCancelAllOrders() {
	}

	BinanceRawFuturesCancelAllOrders(Parcel in) {
		code = (Integer) in.readValue(null);
		message = (String) in.readValue(null);
	}

	public BinanceRawFuturesCancelAllOrders code(Integer code) {
		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 **/
	@Schema(description = "")
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public BinanceRawFuturesCancelAllOrders message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Get message
	 *
	 * @return message
	 **/
	@Schema(description = "")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesCancelAllOrders binanceRawFuturesCancelAllOrders = (BinanceRawFuturesCancelAllOrders) o;
		return Objects.equals(this.code, binanceRawFuturesCancelAllOrders.code) &&
				Objects.equals(this.message, binanceRawFuturesCancelAllOrders.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesCancelAllOrders {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
		out.writeValue(code);
		out.writeValue(message);
	}

	public int describeContents() {
		return 0;
	}
}
