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
 * BinanceRawFuturesCompositeIndexInfoAsset
 */


public class BinanceRawFuturesCompositeIndexInfoAsset implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawFuturesCompositeIndexInfoAsset> CREATOR = new Parcelable.Creator<BinanceRawFuturesCompositeIndexInfoAsset>()
	{
		public BinanceRawFuturesCompositeIndexInfoAsset createFromParcel(Parcel in) {
			return new BinanceRawFuturesCompositeIndexInfoAsset(in);
		}

		public BinanceRawFuturesCompositeIndexInfoAsset[] newArray(int size) {
			return new BinanceRawFuturesCompositeIndexInfoAsset[size];
		}
	};

	@SerializedName("baseAsset")
	private String baseAsset = null;

	@SerializedName("weightInQuantity")
	private Double weightInQuantity = null;

	@SerializedName("weightInPercentage")
	private Double weightInPercentage = null;

	public BinanceRawFuturesCompositeIndexInfoAsset() {
	}

	BinanceRawFuturesCompositeIndexInfoAsset(Parcel in) {
		baseAsset = (String) in.readValue(null);
		weightInQuantity = (Double) in.readValue(null);
		weightInPercentage = (Double) in.readValue(null);
	}

	public BinanceRawFuturesCompositeIndexInfoAsset baseAsset(String baseAsset) {
		this.baseAsset = baseAsset;
		return this;
	}

	/**
	 * Get baseAsset
	 *
	 * @return baseAsset
	 **/
	@Schema(description = "")
	public String getBaseAsset() {
		return baseAsset;
	}

	public void setBaseAsset(String baseAsset) {
		this.baseAsset = baseAsset;
	}

	public BinanceRawFuturesCompositeIndexInfoAsset weightInQuantity(Double weightInQuantity) {
		this.weightInQuantity = weightInQuantity;
		return this;
	}

	/**
	 * Get weightInQuantity
	 *
	 * @return weightInQuantity
	 **/
	@Schema(description = "")
	public Double getWeightInQuantity() {
		return weightInQuantity;
	}

	public void setWeightInQuantity(Double weightInQuantity) {
		this.weightInQuantity = weightInQuantity;
	}

	public BinanceRawFuturesCompositeIndexInfoAsset weightInPercentage(Double weightInPercentage) {
		this.weightInPercentage = weightInPercentage;
		return this;
	}

	/**
	 * Get weightInPercentage
	 *
	 * @return weightInPercentage
	 **/
	@Schema(description = "")
	public Double getWeightInPercentage() {
		return weightInPercentage;
	}

	public void setWeightInPercentage(Double weightInPercentage) {
		this.weightInPercentage = weightInPercentage;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawFuturesCompositeIndexInfoAsset binanceRawFuturesCompositeIndexInfoAsset = (BinanceRawFuturesCompositeIndexInfoAsset) o;
		return Objects.equals(this.baseAsset, binanceRawFuturesCompositeIndexInfoAsset.baseAsset) &&
				Objects.equals(this.weightInQuantity, binanceRawFuturesCompositeIndexInfoAsset.weightInQuantity) &&
				Objects.equals(this.weightInPercentage, binanceRawFuturesCompositeIndexInfoAsset.weightInPercentage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(baseAsset, weightInQuantity, weightInPercentage);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawFuturesCompositeIndexInfoAsset {\n");

		sb.append("    baseAsset: ").append(toIndentedString(baseAsset)).append("\n");
		sb.append("    weightInQuantity: ").append(toIndentedString(weightInQuantity)).append("\n");
		sb.append("    weightInPercentage: ").append(toIndentedString(weightInPercentage)).append("\n");
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
		out.writeValue(baseAsset);
		out.writeValue(weightInQuantity);
		out.writeValue(weightInPercentage);
	}

	public int describeContents() {
		return 0;
	}
}
