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
 * ExchangeAsset
 */


public class ExchangeAsset implements Parcelable
{
	public static final Parcelable.Creator<ExchangeAsset> CREATOR = new Parcelable.Creator<ExchangeAsset>()
	{
		public ExchangeAsset createFromParcel(Parcel in) {
			return new ExchangeAsset(in);
		}

		public ExchangeAsset[] newArray(int size) {
			return new ExchangeAsset[size];
		}
	};

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("creationDate")
	private DateTime creationDate = null;

	@SerializedName("balance")
	private Double balance = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("asset")
	private AssetDetails asset = null;

	public ExchangeAsset() {
	}

	ExchangeAsset(Parcel in) {
		id = (UUID) in.readValue(UUID.class.getClassLoader());
		title = (String) in.readValue(null);
		creationDate = (DateTime) in.readValue(DateTime.class.getClassLoader());
		balance = (Double) in.readValue(null);
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		asset = (AssetDetails) in.readValue(AssetDetails.class.getClassLoader());
	}

	public ExchangeAsset id(UUID id) {
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

	public ExchangeAsset title(String title) {
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

	public ExchangeAsset creationDate(DateTime creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * Get creationDate
	 *
	 * @return creationDate
	 **/
	@Schema(description = "")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public ExchangeAsset balance(Double balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * Get balance
	 *
	 * @return balance
	 **/
	@Schema(description = "")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ExchangeAsset currency(Currency currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public ExchangeAsset asset(AssetDetails asset) {
		this.asset = asset;
		return this;
	}

	/**
	 * Get asset
	 *
	 * @return asset
	 **/
	@Schema(description = "")
	public AssetDetails getAsset() {
		return asset;
	}

	public void setAsset(AssetDetails asset) {
		this.asset = asset;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExchangeAsset exchangeAsset = (ExchangeAsset) o;
		return Objects.equals(this.id, exchangeAsset.id) &&
				Objects.equals(this.title, exchangeAsset.title) &&
				Objects.equals(this.creationDate, exchangeAsset.creationDate) &&
				Objects.equals(this.balance, exchangeAsset.balance) &&
				Objects.equals(this.currency, exchangeAsset.currency) &&
				Objects.equals(this.asset, exchangeAsset.asset);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, creationDate, balance, currency, asset);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ExchangeAsset {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
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
		out.writeValue(creationDate);
		out.writeValue(balance);
		out.writeValue(currency);
		out.writeValue(asset);
	}

	public int describeContents() {
		return 0;
	}
}
