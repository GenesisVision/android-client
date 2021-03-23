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
 * InvestmentEventItemViewModel
 */


public class InvestmentEventItemViewModel implements Parcelable
{
	@SerializedName("title")
	private String title = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("currency")
	private Currency currency = null;

	public InvestmentEventItemViewModel() {
	}

	public InvestmentEventItemViewModel title(String title) {
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

	public InvestmentEventItemViewModel amount(Double amount) {
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

	public InvestmentEventItemViewModel currency(Currency currency) {
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


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InvestmentEventItemViewModel investmentEventItemViewModel = (InvestmentEventItemViewModel) o;
		return Objects.equals(this.title, investmentEventItemViewModel.title) &&
				Objects.equals(this.amount, investmentEventItemViewModel.amount) &&
				Objects.equals(this.currency, investmentEventItemViewModel.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, amount, currency);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InvestmentEventItemViewModel {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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
		out.writeValue(title);
		out.writeValue(amount);
		out.writeValue(currency);
	}

	public static final Parcelable.Creator<InvestmentEventItemViewModel> CREATOR = new Parcelable.Creator<InvestmentEventItemViewModel>()
	{
		public InvestmentEventItemViewModel createFromParcel(Parcel in) {
			return new InvestmentEventItemViewModel(in);
		}

		public InvestmentEventItemViewModel[] newArray(int size) {
			return new InvestmentEventItemViewModel[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	InvestmentEventItemViewModel(Parcel in) {
		title = (String) in.readValue(null);
		amount = (Double) in.readValue(null);
		currency = (Currency) in.readValue(Currency.class.getClassLoader());
	}
}
