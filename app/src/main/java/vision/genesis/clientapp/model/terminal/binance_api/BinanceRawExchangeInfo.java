package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.client.model.BinanceRawRateLimit;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */
public class BinanceRawExchangeInfo implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawExchangeInfo> CREATOR = new Parcelable.Creator<BinanceRawExchangeInfo>()
	{
		public BinanceRawExchangeInfo createFromParcel(Parcel in) {
			return new BinanceRawExchangeInfo(in);
		}

		public BinanceRawExchangeInfo[] newArray(int size) {
			return new BinanceRawExchangeInfo[size];
		}
	};

	@SerializedName("timeZone")
	private String timeZone = null;

	@SerializedName("serverTime")
	private Long serverTime = null;

	@SerializedName("rateLimits")
	private List<BinanceRawRateLimit> rateLimits = null;

	@SerializedName("symbols")
	private List<BinanceRawSymbol> symbols = null;

	public BinanceRawExchangeInfo() {
	}

	BinanceRawExchangeInfo(Parcel in) {
		timeZone = (String) in.readValue(null);
		serverTime = in.readLong();
		rateLimits = (List<BinanceRawRateLimit>) in.readValue(BinanceRawRateLimit.class.getClassLoader());
		symbols = (List<BinanceRawSymbol>) in.readValue(BinanceRawSymbol.class.getClassLoader());
	}

	public BinanceRawExchangeInfo timeZone(String timeZone) {
		this.timeZone = timeZone;
		return this;
	}

	/**
	 * Get timeZone
	 *
	 * @return timeZone
	 **/
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public BinanceRawExchangeInfo serverTime(Long serverTime) {
		this.serverTime = serverTime;
		return this;
	}

	/**
	 * Get serverTime
	 *
	 * @return serverTime
	 **/
	public DateTime getServerTime() {
		return new DateTime(serverTime);
	}

	public void setServerTime(DateTime serverTime) {
		this.serverTime = serverTime.getMillis();
	}

	public BinanceRawExchangeInfo rateLimits(List<BinanceRawRateLimit> rateLimits) {
		this.rateLimits = rateLimits;
		return this;
	}

	public BinanceRawExchangeInfo addRateLimitsItem(BinanceRawRateLimit rateLimitsItem) {
		if (this.rateLimits == null) {
			this.rateLimits = new ArrayList<BinanceRawRateLimit>();
		}
		this.rateLimits.add(rateLimitsItem);
		return this;
	}

	/**
	 * Get rateLimits
	 *
	 * @return rateLimits
	 **/

	public List<BinanceRawRateLimit> getRateLimits() {
		return rateLimits;
	}

	public void setRateLimits(List<BinanceRawRateLimit> rateLimits) {
		this.rateLimits = rateLimits;
	}

	public BinanceRawExchangeInfo symbols(List<BinanceRawSymbol> symbols) {
		this.symbols = symbols;
		return this;
	}

	public BinanceRawExchangeInfo addSymbolsItem(BinanceRawSymbol symbolsItem) {
		if (this.symbols == null) {
			this.symbols = new ArrayList<BinanceRawSymbol>();
		}
		this.symbols.add(symbolsItem);
		return this;
	}

	/**
	 * Get symbols
	 *
	 * @return symbols
	 **/
	public List<BinanceRawSymbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<BinanceRawSymbol> symbols) {
		this.symbols = symbols;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawExchangeInfo binanceRawExchangeInfo = (BinanceRawExchangeInfo) o;
		return Objects.equals(this.timeZone, binanceRawExchangeInfo.timeZone) &&
				Objects.equals(this.serverTime, binanceRawExchangeInfo.serverTime) &&
				Objects.equals(this.rateLimits, binanceRawExchangeInfo.rateLimits) &&
				Objects.equals(this.symbols, binanceRawExchangeInfo.symbols);
	}

	@Override
	public int hashCode() {
		return Objects.hash(timeZone, serverTime, rateLimits, symbols);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawExchangeInfo {\n");

		sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
		sb.append("    serverTime: ").append(toIndentedString(serverTime)).append("\n");
		sb.append("    rateLimits: ").append(toIndentedString(rateLimits)).append("\n");
		sb.append("    symbols: ").append(toIndentedString(symbols)).append("\n");
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
		out.writeValue(timeZone);
		out.writeValue(serverTime);
		out.writeValue(rateLimits);
		out.writeValue(symbols);
	}

	public int describeContents() {
		return 0;
	}
}
