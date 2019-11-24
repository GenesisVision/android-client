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
 * AmountItem
 */


public class AmountItem implements Parcelable
{
	public static final Parcelable.Creator<AmountItem> CREATOR = new Parcelable.Creator<AmountItem>()
	{
		public AmountItem createFromParcel(Parcel in) {
			return new AmountItem(in);
		}

		public AmountItem[] newArray(int size) {
			return new AmountItem[size];
		}
	};

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("currency")
	private Currency currency = null;

	@SerializedName("color")
	private String color = null;

	@SerializedName("title")
	private String title = null;

	public AmountItem() {
	}

	AmountItem(Parcel in) {
		amount = (Double) in.readValue(null);
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
		color = (String) in.readValue(null);
		title = (String) in.readValue(null);
	}

	public AmountItem amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@Schema(description = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public AmountItem currency(Currency currency) {
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

	public AmountItem color(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Get color
	 *
	 * @return color
	 **/
	@Schema(description = "")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public AmountItem title(String title) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AmountItem amountItem = (AmountItem) o;
		return Objects.equals(this.amount, amountItem.amount) &&
				Objects.equals(this.currency, amountItem.currency) &&
				Objects.equals(this.color, amountItem.color) &&
				Objects.equals(this.title, amountItem.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency, color, title);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AmountItem {\n");

		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    color: ").append(toIndentedString(color)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
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
		out.writeValue(amount);
		out.writeValue(currency);
		out.writeValue(color);
		out.writeValue(title);
	}

	public int describeContents() {
		return 0;
	}
}
