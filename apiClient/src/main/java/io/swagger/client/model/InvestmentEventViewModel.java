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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * InvestmentEventViewModel
 */


public class InvestmentEventViewModel implements Parcelable
{
	public static final Parcelable.Creator<InvestmentEventViewModel> CREATOR = new Parcelable.Creator<InvestmentEventViewModel>()
	{
		public InvestmentEventViewModel createFromParcel(Parcel in) {
			return new InvestmentEventViewModel(in);
		}

		public InvestmentEventViewModel[] newArray(int size) {
			return new InvestmentEventViewModel[size];
		}
	};

	@SerializedName("title")
	private String title = null;

	@SerializedName("icon")
	private String icon = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("assetDetails")
	private AssetDetails assetDetails = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("currency")
	private CurrencyEnum currency = null;

	@SerializedName("changeState")
	private ChangeState changeState = null;

	@SerializedName("extendedInfo")
	private List<InvestmentEventItemViewModel> extendedInfo = null;

	@SerializedName("feesInfo")
	private List<FeeDetails> feesInfo = null;

	@SerializedName("totalFeesAmount")
	private Double totalFeesAmount = null;

	@SerializedName("totalFeesCurrency")
	private TotalFeesCurrencyEnum totalFeesCurrency = null;

	public InvestmentEventViewModel() {
	}

	InvestmentEventViewModel(Parcel in) {
		title = (String) in.readValue(null);
		icon = (String) in.readValue(null);
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		assetDetails = (AssetDetails) in.readValue(AssetDetails.class.getClassLoader());
		amount = (Double) in.readValue(null);
		currency = (CurrencyEnum) in.readValue(null);
		changeState = (ChangeState) in.readValue(ChangeState.class.getClassLoader());
		extendedInfo = (List<InvestmentEventItemViewModel>) in.readValue(InvestmentEventItemViewModel.class.getClassLoader());
		feesInfo = (List<FeeDetails>) in.readValue(FeeDetails.class.getClassLoader());
		totalFeesAmount = (Double) in.readValue(null);
		totalFeesCurrency = (TotalFeesCurrencyEnum) in.readValue(null);
	}

	public InvestmentEventViewModel title(String title) {
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

	public InvestmentEventViewModel icon(String icon) {
		this.icon = icon;
		return this;
	}

	/**
	 * Get icon
	 *
	 * @return icon
	 **/
	@Schema(description = "")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public InvestmentEventViewModel date(DateTime date) {
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

	public InvestmentEventViewModel assetDetails(AssetDetails assetDetails) {
		this.assetDetails = assetDetails;
		return this;
	}

	/**
	 * Get assetDetails
	 *
	 * @return assetDetails
	 **/
	@Schema(description = "")
	public AssetDetails getAssetDetails() {
		return assetDetails;
	}

	public void setAssetDetails(AssetDetails assetDetails) {
		this.assetDetails = assetDetails;
	}

	public InvestmentEventViewModel amount(Double amount) {
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

	public InvestmentEventViewModel currency(CurrencyEnum currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 **/
	@Schema(description = "")
	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public InvestmentEventViewModel changeState(ChangeState changeState) {
		this.changeState = changeState;
		return this;
	}

	/**
	 * Get changeState
	 *
	 * @return changeState
	 **/
	@Schema(description = "")
	public ChangeState getChangeState() {
		return changeState;
	}

	public void setChangeState(ChangeState changeState) {
		this.changeState = changeState;
	}

	public InvestmentEventViewModel extendedInfo(List<InvestmentEventItemViewModel> extendedInfo) {
		this.extendedInfo = extendedInfo;
		return this;
	}

	public InvestmentEventViewModel addExtendedInfoItem(InvestmentEventItemViewModel extendedInfoItem) {
		if (this.extendedInfo == null) {
			this.extendedInfo = new ArrayList<InvestmentEventItemViewModel>();
		}
		this.extendedInfo.add(extendedInfoItem);
		return this;
	}

	/**
	 * Get extendedInfo
	 *
	 * @return extendedInfo
	 **/
	@Schema(description = "")
	public List<InvestmentEventItemViewModel> getExtendedInfo() {
		return extendedInfo;
	}

	public void setExtendedInfo(List<InvestmentEventItemViewModel> extendedInfo) {
		this.extendedInfo = extendedInfo;
	}

	public InvestmentEventViewModel feesInfo(List<FeeDetails> feesInfo) {
		this.feesInfo = feesInfo;
		return this;
	}

	public InvestmentEventViewModel addFeesInfoItem(FeeDetails feesInfoItem) {
		if (this.feesInfo == null) {
			this.feesInfo = new ArrayList<FeeDetails>();
		}
		this.feesInfo.add(feesInfoItem);
		return this;
	}

	/**
	 * Get feesInfo
	 *
	 * @return feesInfo
	 **/
	@Schema(description = "")
	public List<FeeDetails> getFeesInfo() {
		return feesInfo;
	}

	public void setFeesInfo(List<FeeDetails> feesInfo) {
		this.feesInfo = feesInfo;
	}

	public InvestmentEventViewModel totalFeesAmount(Double totalFeesAmount) {
		this.totalFeesAmount = totalFeesAmount;
		return this;
	}

	/**
	 * Get totalFeesAmount
	 *
	 * @return totalFeesAmount
	 **/
	@Schema(description = "")
	public Double getTotalFeesAmount() {
		return totalFeesAmount;
	}

	public void setTotalFeesAmount(Double totalFeesAmount) {
		this.totalFeesAmount = totalFeesAmount;
	}

	public InvestmentEventViewModel totalFeesCurrency(TotalFeesCurrencyEnum totalFeesCurrency) {
		this.totalFeesCurrency = totalFeesCurrency;
		return this;
	}

	/**
	 * Get totalFeesCurrency
	 *
	 * @return totalFeesCurrency
	 **/
	@Schema(description = "")
	public TotalFeesCurrencyEnum getTotalFeesCurrency() {
		return totalFeesCurrency;
	}

	public void setTotalFeesCurrency(TotalFeesCurrencyEnum totalFeesCurrency) {
		this.totalFeesCurrency = totalFeesCurrency;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InvestmentEventViewModel investmentEventViewModel = (InvestmentEventViewModel) o;
		return Objects.equals(this.title, investmentEventViewModel.title) &&
				Objects.equals(this.icon, investmentEventViewModel.icon) &&
				Objects.equals(this.date, investmentEventViewModel.date) &&
				Objects.equals(this.assetDetails, investmentEventViewModel.assetDetails) &&
				Objects.equals(this.amount, investmentEventViewModel.amount) &&
				Objects.equals(this.currency, investmentEventViewModel.currency) &&
				Objects.equals(this.changeState, investmentEventViewModel.changeState) &&
				Objects.equals(this.extendedInfo, investmentEventViewModel.extendedInfo) &&
				Objects.equals(this.feesInfo, investmentEventViewModel.feesInfo) &&
				Objects.equals(this.totalFeesAmount, investmentEventViewModel.totalFeesAmount) &&
				Objects.equals(this.totalFeesCurrency, investmentEventViewModel.totalFeesCurrency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, icon, date, assetDetails, amount, currency, changeState, extendedInfo, feesInfo, totalFeesAmount, totalFeesCurrency);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InvestmentEventViewModel {\n");

		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    assetDetails: ").append(toIndentedString(assetDetails)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    changeState: ").append(toIndentedString(changeState)).append("\n");
		sb.append("    extendedInfo: ").append(toIndentedString(extendedInfo)).append("\n");
		sb.append("    feesInfo: ").append(toIndentedString(feesInfo)).append("\n");
		sb.append("    totalFeesAmount: ").append(toIndentedString(totalFeesAmount)).append("\n");
		sb.append("    totalFeesCurrency: ").append(toIndentedString(totalFeesCurrency)).append("\n");
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
		out.writeValue(icon);
		out.writeValue(date);
		out.writeValue(assetDetails);
		out.writeValue(amount);
		out.writeValue(currency);
		out.writeValue(changeState);
		out.writeValue(extendedInfo);
		out.writeValue(feesInfo);
		out.writeValue(totalFeesAmount);
		out.writeValue(totalFeesCurrency);
	}

	public int describeContents() {
		return 0;
	}

	/**
	 * Gets or Sets currency
	 */
	@JsonAdapter(CurrencyEnum.Adapter.class)
	public enum CurrencyEnum
	{
		USD("USD"),
		BTC("BTC"),
		ETH("ETH"),
		USDT("USDT"),
		GVT("GVT"),
		UNDEFINED("Undefined"),
		ADA("ADA"),
		XRP("XRP"),
		BCH("BCH"),
		LTC("LTC"),
		DOGE("DOGE"),
		BNB("BNB"),
		EUR("EUR");

		public static CurrencyEnum fromValue(String text) {
			for (CurrencyEnum b : CurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		CurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<CurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final CurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public CurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return CurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets totalFeesCurrency
	 */
	@JsonAdapter(TotalFeesCurrencyEnum.Adapter.class)
	public enum TotalFeesCurrencyEnum
	{
		USD("USD"),
		BTC("BTC"),
		ETH("ETH"),
		USDT("USDT"),
		GVT("GVT"),
		UNDEFINED("Undefined"),
		ADA("ADA"),
		XRP("XRP"),
		BCH("BCH"),
		LTC("LTC"),
		DOGE("DOGE"),
		BNB("BNB"),
		EUR("EUR");

		public static TotalFeesCurrencyEnum fromValue(String text) {
			for (TotalFeesCurrencyEnum b : TotalFeesCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		TotalFeesCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<TotalFeesCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final TotalFeesCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public TotalFeesCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return TotalFeesCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}
}
