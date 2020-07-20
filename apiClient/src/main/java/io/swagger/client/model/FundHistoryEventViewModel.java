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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * FundHistoryEventViewModel
 */


public class FundHistoryEventViewModel implements Parcelable
{
	public static final Parcelable.Creator<FundHistoryEventViewModel> CREATOR = new Parcelable.Creator<FundHistoryEventViewModel>()
	{
		public FundHistoryEventViewModel createFromParcel(Parcel in) {
			return new FundHistoryEventViewModel(in);
		}

		public FundHistoryEventViewModel[] newArray(int size) {
			return new FundHistoryEventViewModel[size];
		}
	};

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("type")
	private FundHistoryEventType type = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("asset")
	private BasePlatformAsset asset = null;

	@SerializedName("trades")
	private List<FundTradingEventViewModel> trades = null;

	@SerializedName("reallocateAssets")
	private List<FundAssetPartWithIcon> reallocateAssets = null;

	public FundHistoryEventViewModel() {
	}

	FundHistoryEventViewModel(Parcel in) {
		date = (DateTime) in.readValue(DateTime.class.getClassLoader());
		type = (FundHistoryEventType) in.readValue(FundHistoryEventType.class.getClassLoader());
		amount = (Double) in.readValue(null);
		asset = (BasePlatformAsset) in.readValue(BasePlatformAsset.class.getClassLoader());
		trades = (List<FundTradingEventViewModel>) in.readValue(FundTradingEventViewModel.class.getClassLoader());
		reallocateAssets = (List<FundAssetPartWithIcon>) in.readValue(FundAssetPartWithIcon.class.getClassLoader());
	}

	public FundHistoryEventViewModel date(DateTime date) {
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

	public FundHistoryEventViewModel type(FundHistoryEventType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 **/
	@Schema(description = "")
	public FundHistoryEventType getType() {
		return type;
	}

	public void setType(FundHistoryEventType type) {
		this.type = type;
	}

	public FundHistoryEventViewModel amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Investment/withdrawal/fund creation
	 *
	 * @return amount
	 **/
	@Schema(description = "Investment/withdrawal/fund creation")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public FundHistoryEventViewModel asset(BasePlatformAsset asset) {
		this.asset = asset;
		return this;
	}

	/**
	 * Get asset
	 *
	 * @return asset
	 **/
	@Schema(description = "")
	public BasePlatformAsset getAsset() {
		return asset;
	}

	public void setAsset(BasePlatformAsset asset) {
		this.asset = asset;
	}

	public FundHistoryEventViewModel trades(List<FundTradingEventViewModel> trades) {
		this.trades = trades;
		return this;
	}

	public FundHistoryEventViewModel addTradesItem(FundTradingEventViewModel tradesItem) {
		if (this.trades == null) {
			this.trades = new ArrayList<FundTradingEventViewModel>();
		}
		this.trades.add(tradesItem);
		return this;
	}

	/**
	 * Get trades
	 *
	 * @return trades
	 **/
	@Schema(description = "")
	public List<FundTradingEventViewModel> getTrades() {
		return trades;
	}

	public void setTrades(List<FundTradingEventViewModel> trades) {
		this.trades = trades;
	}

	public FundHistoryEventViewModel reallocateAssets(List<FundAssetPartWithIcon> reallocateAssets) {
		this.reallocateAssets = reallocateAssets;
		return this;
	}

	public FundHistoryEventViewModel addReallocateAssetsItem(FundAssetPartWithIcon reallocateAssetsItem) {
		if (this.reallocateAssets == null) {
			this.reallocateAssets = new ArrayList<FundAssetPartWithIcon>();
		}
		this.reallocateAssets.add(reallocateAssetsItem);
		return this;
	}

	/**
	 * Get reallocateAssets
	 *
	 * @return reallocateAssets
	 **/
	@Schema(description = "")
	public List<FundAssetPartWithIcon> getReallocateAssets() {
		return reallocateAssets;
	}

	public void setReallocateAssets(List<FundAssetPartWithIcon> reallocateAssets) {
		this.reallocateAssets = reallocateAssets;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FundHistoryEventViewModel fundHistoryEventViewModel = (FundHistoryEventViewModel) o;
		return Objects.equals(this.date, fundHistoryEventViewModel.date) &&
				Objects.equals(this.type, fundHistoryEventViewModel.type) &&
				Objects.equals(this.amount, fundHistoryEventViewModel.amount) &&
				Objects.equals(this.asset, fundHistoryEventViewModel.asset) &&
				Objects.equals(this.trades, fundHistoryEventViewModel.trades) &&
				Objects.equals(this.reallocateAssets, fundHistoryEventViewModel.reallocateAssets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, type, amount, asset, trades, reallocateAssets);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FundHistoryEventViewModel {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
		sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
		sb.append("    reallocateAssets: ").append(toIndentedString(reallocateAssets)).append("\n");
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
		out.writeValue(type);
		out.writeValue(amount);
		out.writeValue(asset);
		out.writeValue(trades);
		out.writeValue(reallocateAssets);
	}

	public int describeContents() {
		return 0;
	}
}
