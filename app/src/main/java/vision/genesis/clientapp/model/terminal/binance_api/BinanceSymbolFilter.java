package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */

public class BinanceSymbolFilter implements Parcelable
{
	public static final Parcelable.Creator<BinanceSymbolFilter> CREATOR = new Parcelable.Creator<BinanceSymbolFilter>()
	{
		public BinanceSymbolFilter createFromParcel(Parcel in) {
			return new BinanceSymbolFilter(in);
		}

		public BinanceSymbolFilter[] newArray(int size) {
			return new BinanceSymbolFilter[size];
		}
	};

	@SerializedName("filterType")
	private BinanceSymbolFilterType filterType = null;

	@SerializedName("minPrice")
	private Double minPrice = null;

	@SerializedName("maxPrice")
	private Double maxPrice = null;

	@SerializedName("tickSize")
	private Double tickSize = null;

	@SerializedName("minQty")
	private Double minQty = null;

	@SerializedName("maxQty")
	private Double maxQty = null;

	@SerializedName("stepSize")
	private Double stepSize = null;

	public BinanceSymbolFilter() {
	}

	BinanceSymbolFilter(Parcel in) {
		filterType = (BinanceSymbolFilterType) in.readValue(BinanceSymbolFilterType.class.getClassLoader());
		minPrice = (Double) in.readValue(null);
		maxPrice = (Double) in.readValue(null);
		tickSize = (Double) in.readValue(null);
		minQty = (Double) in.readValue(null);
		maxQty = (Double) in.readValue(null);
		stepSize = (Double) in.readValue(null);
	}

	public BinanceSymbolFilter filterType(BinanceSymbolFilterType filterType) {
		this.filterType = filterType;
		return this;
	}

	/**
	 * Get filterType
	 *
	 * @return filterType
	 **/
	public BinanceSymbolFilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(BinanceSymbolFilterType filterType) {
		this.filterType = filterType;
	}

	public BinanceSymbolFilter minPrice(Double minPrice) {
		this.minPrice = minPrice;
		return this;
	}

	/**
	 * Get minPrice
	 *
	 * @return minPrice
	 **/
	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public BinanceSymbolFilter maxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
		return this;
	}

	/**
	 * Get maxPrice
	 *
	 * @return maxPrice
	 **/
	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BinanceSymbolFilter tickSize(Double tickSize) {
		this.tickSize = tickSize;
		return this;
	}

	/**
	 * Get tickSize
	 *
	 * @return tickSize
	 **/
	public Double getTickSize() {
		return tickSize;
	}

	public void setTickSize(Double tickSize) {
		this.tickSize = tickSize;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceSymbolFilter binanceRawSymbolPriceFilter = (BinanceSymbolFilter) o;
		return Objects.equals(this.filterType, binanceRawSymbolPriceFilter.filterType) &&
				Objects.equals(this.minPrice, binanceRawSymbolPriceFilter.minPrice) &&
				Objects.equals(this.maxPrice, binanceRawSymbolPriceFilter.maxPrice) &&
				Objects.equals(this.tickSize, binanceRawSymbolPriceFilter.tickSize);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filterType, minPrice, maxPrice, tickSize);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawSymbolPriceFilter {\n");

		sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
		sb.append("    minPrice: ").append(toIndentedString(minPrice)).append("\n");
		sb.append("    maxPrice: ").append(toIndentedString(maxPrice)).append("\n");
		sb.append("    tickSize: ").append(toIndentedString(tickSize)).append("\n");
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
		out.writeValue(filterType);
		out.writeValue(minPrice);
		out.writeValue(maxPrice);
		out.writeValue(tickSize);
		out.writeValue(minQty);
		out.writeValue(maxQty);
		out.writeValue(stepSize);
	}

	public int describeContents() {
		return 0;
	}

	public Double getMinQty() {
		return minQty;
	}

	public Double getMaxQty() {
		return maxQty;
	}

	public Double getStepSize() {
		return stepSize;
	}
}

