package vision.genesis.clientapp.model.terminal.binance_api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.client.model.BinanceAccountType;
import io.swagger.client.model.BinanceOrderType;
import io.swagger.client.model.BinanceRawSymbolIcebergPartsFilter;
import io.swagger.client.model.BinanceRawSymbolLotSizeFilter;
import io.swagger.client.model.BinanceRawSymbolMarketLotSizeFilter;
import io.swagger.client.model.BinanceRawSymbolMaxAlgorithmicOrdersFilter;
import io.swagger.client.model.BinanceRawSymbolMaxOrdersFilter;
import io.swagger.client.model.BinanceRawSymbolMaxPositionFilter;
import io.swagger.client.model.BinanceRawSymbolMinNotionalFilter;
import io.swagger.client.model.BinanceRawSymbolPercentPriceFilter;
import io.swagger.client.model.BinanceRawSymbolPriceFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */

public class BinanceRawSymbol implements Parcelable
{
	public static final Parcelable.Creator<BinanceRawSymbol> CREATOR = new Parcelable.Creator<BinanceRawSymbol>()
	{
		public BinanceRawSymbol createFromParcel(Parcel in) {
			return new BinanceRawSymbol(in);
		}

		public BinanceRawSymbol[] newArray(int size) {
			return new BinanceRawSymbol[size];
		}
	};

	@SerializedName("symbol")
	private String name = null;

	@SerializedName("status")
	private BinanceSymbolStatus status = null;

	@SerializedName("baseAsset")
	private String baseAsset = null;

	@SerializedName("baseAssetPrecision")
	private Integer baseAssetPrecision = null;

	@SerializedName("quoteAsset")
	private String quoteAsset = null;

	@SerializedName("quoteAssetPrecision")
	private Integer quoteAssetPrecision = null;

	@SerializedName("orderTypes")
	private List<BinanceOrderType> orderTypes = null;

	@SerializedName("iceBergAllowed")
	private Boolean iceBergAllowed = null;

	@SerializedName("isSpotTradingAllowed")
	private Boolean isSpotTradingAllowed = null;

	@SerializedName("isMarginTradingAllowed")
	private Boolean isMarginTradingAllowed = null;

	@SerializedName("ocoAllowed")
	private Boolean ocoAllowed = null;

	@SerializedName("quoteOrderQuantityMarketAllowed")
	private Boolean quoteOrderQuantityMarketAllowed = null;

	@SerializedName("baseCommissionPrecision")
	private Integer baseCommissionPrecision = null;

	@SerializedName("quoteCommissionPrecision")
	private Integer quoteCommissionPrecision = null;

	@SerializedName("permissions")
	private List<BinanceAccountType> permissions = null;

	@SerializedName("iceBergPartsFilter")
	private BinanceRawSymbolIcebergPartsFilter iceBergPartsFilter = null;

	@SerializedName("lotSizeFilter")
	private BinanceRawSymbolLotSizeFilter lotSizeFilter = null;

	@SerializedName("marketLotSizeFilter")
	private BinanceRawSymbolMarketLotSizeFilter marketLotSizeFilter = null;

	@SerializedName("maxOrdersFilter")
	private BinanceRawSymbolMaxOrdersFilter maxOrdersFilter = null;

	@SerializedName("maxAlgorithmicOrdersFilter")
	private BinanceRawSymbolMaxAlgorithmicOrdersFilter maxAlgorithmicOrdersFilter = null;

	@SerializedName("minNotionalFilter")
	private BinanceRawSymbolMinNotionalFilter minNotionalFilter = null;

	@SerializedName("priceFilter")
	private BinanceRawSymbolPriceFilter priceFilter = null;

	@SerializedName("pricePercentFilter")
	private BinanceRawSymbolPercentPriceFilter pricePercentFilter = null;

	@SerializedName("maxPositionFilter")
	private BinanceRawSymbolMaxPositionFilter maxPositionFilter = null;

	@SerializedName("filters")
	private ArrayList<BinanceSymbolFilter> filters = new ArrayList<>();

	public BinanceRawSymbol() {
	}

	BinanceRawSymbol(Parcel in) {
		name = (String) in.readValue(null);
		status = (BinanceSymbolStatus) in.readValue(BinanceSymbolStatus.class.getClassLoader());
		baseAsset = (String) in.readValue(null);
		baseAssetPrecision = (Integer) in.readValue(null);
		quoteAsset = (String) in.readValue(null);
		quoteAssetPrecision = (Integer) in.readValue(null);
		orderTypes = (List<BinanceOrderType>) in.readValue(BinanceOrderType.class.getClassLoader());
		iceBergAllowed = (Boolean) in.readValue(null);
		isSpotTradingAllowed = (Boolean) in.readValue(null);
		isMarginTradingAllowed = (Boolean) in.readValue(null);
		ocoAllowed = (Boolean) in.readValue(null);
		quoteOrderQuantityMarketAllowed = (Boolean) in.readValue(null);
		baseCommissionPrecision = (Integer) in.readValue(null);
		quoteCommissionPrecision = (Integer) in.readValue(null);
		permissions = (List<BinanceAccountType>) in.readValue(BinanceAccountType.class.getClassLoader());
		iceBergPartsFilter = (BinanceRawSymbolIcebergPartsFilter) in.readValue(BinanceRawSymbolIcebergPartsFilter.class.getClassLoader());
		lotSizeFilter = (BinanceRawSymbolLotSizeFilter) in.readValue(BinanceRawSymbolLotSizeFilter.class.getClassLoader());
		marketLotSizeFilter = (BinanceRawSymbolMarketLotSizeFilter) in.readValue(BinanceRawSymbolMarketLotSizeFilter.class.getClassLoader());
		maxOrdersFilter = (BinanceRawSymbolMaxOrdersFilter) in.readValue(BinanceRawSymbolMaxOrdersFilter.class.getClassLoader());
		maxAlgorithmicOrdersFilter = (BinanceRawSymbolMaxAlgorithmicOrdersFilter) in.readValue(BinanceRawSymbolMaxAlgorithmicOrdersFilter.class.getClassLoader());
		minNotionalFilter = (BinanceRawSymbolMinNotionalFilter) in.readValue(BinanceRawSymbolMinNotionalFilter.class.getClassLoader());
		priceFilter = (BinanceRawSymbolPriceFilter) in.readValue(BinanceRawSymbolPriceFilter.class.getClassLoader());
		pricePercentFilter = (BinanceRawSymbolPercentPriceFilter) in.readValue(BinanceRawSymbolPercentPriceFilter.class.getClassLoader());
		maxPositionFilter = (BinanceRawSymbolMaxPositionFilter) in.readValue(BinanceRawSymbolMaxPositionFilter.class.getClassLoader());
		in.readList(filters, BinanceSymbolFilter.class.getClassLoader());
	}

	public BinanceRawSymbol name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BinanceRawSymbol status(BinanceSymbolStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 **/

	public BinanceSymbolStatus getStatus() {
		return status;
	}

	public void setStatus(BinanceSymbolStatus status) {
		this.status = status;
	}

	public BinanceRawSymbol baseAsset(String baseAsset) {
		this.baseAsset = baseAsset;
		return this;
	}

	/**
	 * Get baseAsset
	 *
	 * @return baseAsset
	 **/

	public String getBaseAsset() {
		return baseAsset;
	}

	public void setBaseAsset(String baseAsset) {
		this.baseAsset = baseAsset;
	}

	public BinanceRawSymbol baseAssetPrecision(Integer baseAssetPrecision) {
		this.baseAssetPrecision = baseAssetPrecision;
		return this;
	}

	/**
	 * Get baseAssetPrecision
	 *
	 * @return baseAssetPrecision
	 **/

	public Integer getBaseAssetPrecision() {
		return baseAssetPrecision;
	}

	public void setBaseAssetPrecision(Integer baseAssetPrecision) {
		this.baseAssetPrecision = baseAssetPrecision;
	}

	public BinanceRawSymbol quoteAsset(String quoteAsset) {
		this.quoteAsset = quoteAsset;
		return this;
	}

	/**
	 * Get quoteAsset
	 *
	 * @return quoteAsset
	 **/

	public String getQuoteAsset() {
		return quoteAsset;
	}

	public void setQuoteAsset(String quoteAsset) {
		this.quoteAsset = quoteAsset;
	}

	public BinanceRawSymbol quoteAssetPrecision(Integer quoteAssetPrecision) {
		this.quoteAssetPrecision = quoteAssetPrecision;
		return this;
	}

	/**
	 * Get quoteAssetPrecision
	 *
	 * @return quoteAssetPrecision
	 **/

	public Integer getQuoteAssetPrecision() {
		return quoteAssetPrecision;
	}

	public void setQuoteAssetPrecision(Integer quoteAssetPrecision) {
		this.quoteAssetPrecision = quoteAssetPrecision;
	}

	public BinanceRawSymbol orderTypes(List<BinanceOrderType> orderTypes) {
		this.orderTypes = orderTypes;
		return this;
	}

	public BinanceRawSymbol addOrderTypesItem(BinanceOrderType orderTypesItem) {
		if (this.orderTypes == null) {
			this.orderTypes = new ArrayList<BinanceOrderType>();
		}
		this.orderTypes.add(orderTypesItem);
		return this;
	}

	/**
	 * Get orderTypes
	 *
	 * @return orderTypes
	 **/

	public List<BinanceOrderType> getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(List<BinanceOrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}

	public BinanceRawSymbol iceBergAllowed(Boolean iceBergAllowed) {
		this.iceBergAllowed = iceBergAllowed;
		return this;
	}

	/**
	 * Get iceBergAllowed
	 *
	 * @return iceBergAllowed
	 **/

	public Boolean isIceBergAllowed() {
		return iceBergAllowed;
	}

	public void setIceBergAllowed(Boolean iceBergAllowed) {
		this.iceBergAllowed = iceBergAllowed;
	}

	public BinanceRawSymbol isSpotTradingAllowed(Boolean isSpotTradingAllowed) {
		this.isSpotTradingAllowed = isSpotTradingAllowed;
		return this;
	}

	/**
	 * Get isSpotTradingAllowed
	 *
	 * @return isSpotTradingAllowed
	 **/

	public Boolean isIsSpotTradingAllowed() {
		return isSpotTradingAllowed;
	}

	public void setIsSpotTradingAllowed(Boolean isSpotTradingAllowed) {
		this.isSpotTradingAllowed = isSpotTradingAllowed;
	}

	public BinanceRawSymbol isMarginTradingAllowed(Boolean isMarginTradingAllowed) {
		this.isMarginTradingAllowed = isMarginTradingAllowed;
		return this;
	}

	/**
	 * Get isMarginTradingAllowed
	 *
	 * @return isMarginTradingAllowed
	 **/

	public Boolean isIsMarginTradingAllowed() {
		return isMarginTradingAllowed;
	}

	public void setIsMarginTradingAllowed(Boolean isMarginTradingAllowed) {
		this.isMarginTradingAllowed = isMarginTradingAllowed;
	}

	public BinanceRawSymbol ocoAllowed(Boolean ocoAllowed) {
		this.ocoAllowed = ocoAllowed;
		return this;
	}

	/**
	 * Get ocoAllowed
	 *
	 * @return ocoAllowed
	 **/

	public Boolean isOcoAllowed() {
		return ocoAllowed;
	}

	public void setOcoAllowed(Boolean ocoAllowed) {
		this.ocoAllowed = ocoAllowed;
	}

	public BinanceRawSymbol quoteOrderQuantityMarketAllowed(Boolean quoteOrderQuantityMarketAllowed) {
		this.quoteOrderQuantityMarketAllowed = quoteOrderQuantityMarketAllowed;
		return this;
	}

	/**
	 * Get quoteOrderQuantityMarketAllowed
	 *
	 * @return quoteOrderQuantityMarketAllowed
	 **/

	public Boolean isQuoteOrderQuantityMarketAllowed() {
		return quoteOrderQuantityMarketAllowed;
	}

	public void setQuoteOrderQuantityMarketAllowed(Boolean quoteOrderQuantityMarketAllowed) {
		this.quoteOrderQuantityMarketAllowed = quoteOrderQuantityMarketAllowed;
	}

	public BinanceRawSymbol baseCommissionPrecision(Integer baseCommissionPrecision) {
		this.baseCommissionPrecision = baseCommissionPrecision;
		return this;
	}

	/**
	 * Get baseCommissionPrecision
	 *
	 * @return baseCommissionPrecision
	 **/

	public Integer getBaseCommissionPrecision() {
		return baseCommissionPrecision;
	}

	public void setBaseCommissionPrecision(Integer baseCommissionPrecision) {
		this.baseCommissionPrecision = baseCommissionPrecision;
	}

	public BinanceRawSymbol quoteCommissionPrecision(Integer quoteCommissionPrecision) {
		this.quoteCommissionPrecision = quoteCommissionPrecision;
		return this;
	}

	/**
	 * Get quoteCommissionPrecision
	 *
	 * @return quoteCommissionPrecision
	 **/

	public Integer getQuoteCommissionPrecision() {
		return quoteCommissionPrecision;
	}

	public void setQuoteCommissionPrecision(Integer quoteCommissionPrecision) {
		this.quoteCommissionPrecision = quoteCommissionPrecision;
	}

	public BinanceRawSymbol permissions(List<BinanceAccountType> permissions) {
		this.permissions = permissions;
		return this;
	}

	public BinanceRawSymbol addPermissionsItem(BinanceAccountType permissionsItem) {
		if (this.permissions == null) {
			this.permissions = new ArrayList<BinanceAccountType>();
		}
		this.permissions.add(permissionsItem);
		return this;
	}

	/**
	 * Get permissions
	 *
	 * @return permissions
	 **/

	public List<BinanceAccountType> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<BinanceAccountType> permissions) {
		this.permissions = permissions;
	}

	public BinanceRawSymbol iceBergPartsFilter(BinanceRawSymbolIcebergPartsFilter iceBergPartsFilter) {
		this.iceBergPartsFilter = iceBergPartsFilter;
		return this;
	}

	/**
	 * Get iceBergPartsFilter
	 *
	 * @return iceBergPartsFilter
	 **/

	public BinanceRawSymbolIcebergPartsFilter getIceBergPartsFilter() {
		return iceBergPartsFilter;
	}

	public void setIceBergPartsFilter(BinanceRawSymbolIcebergPartsFilter iceBergPartsFilter) {
		this.iceBergPartsFilter = iceBergPartsFilter;
	}

	public BinanceRawSymbol lotSizeFilter(BinanceRawSymbolLotSizeFilter lotSizeFilter) {
		this.lotSizeFilter = lotSizeFilter;
		return this;
	}

	/**
	 * Get lotSizeFilter
	 *
	 * @return lotSizeFilter
	 **/

	public BinanceSymbolFilter getLotSizeFilter() {
		return getFilter(BinanceSymbolFilterType.LOTSIZE);
	}

	public void setLotSizeFilter(BinanceRawSymbolLotSizeFilter lotSizeFilter) {
		this.lotSizeFilter = lotSizeFilter;
	}

	public BinanceSymbolFilter getFilter(BinanceSymbolFilterType filterType) {
		if (filters != null) {
			for (BinanceSymbolFilter filter : filters) {
				if (filter.getFilterType() != null && filter.getFilterType().equals(filterType)) {
					return filter;
				}
			}
		}
		return null;
	}

	public BinanceRawSymbol marketLotSizeFilter(BinanceRawSymbolMarketLotSizeFilter marketLotSizeFilter) {
		this.marketLotSizeFilter = marketLotSizeFilter;
		return this;
	}

	/**
	 * Get marketLotSizeFilter
	 *
	 * @return marketLotSizeFilter
	 **/

	public BinanceRawSymbolMarketLotSizeFilter getMarketLotSizeFilter() {
		return marketLotSizeFilter;
	}

	public void setMarketLotSizeFilter(BinanceRawSymbolMarketLotSizeFilter marketLotSizeFilter) {
		this.marketLotSizeFilter = marketLotSizeFilter;
	}

	public BinanceRawSymbol maxOrdersFilter(BinanceRawSymbolMaxOrdersFilter maxOrdersFilter) {
		this.maxOrdersFilter = maxOrdersFilter;
		return this;
	}

	/**
	 * Get maxOrdersFilter
	 *
	 * @return maxOrdersFilter
	 **/

	public BinanceRawSymbolMaxOrdersFilter getMaxOrdersFilter() {
		return maxOrdersFilter;
	}

	public void setMaxOrdersFilter(BinanceRawSymbolMaxOrdersFilter maxOrdersFilter) {
		this.maxOrdersFilter = maxOrdersFilter;
	}

	public BinanceRawSymbol maxAlgorithmicOrdersFilter(BinanceRawSymbolMaxAlgorithmicOrdersFilter maxAlgorithmicOrdersFilter) {
		this.maxAlgorithmicOrdersFilter = maxAlgorithmicOrdersFilter;
		return this;
	}

	/**
	 * Get maxAlgorithmicOrdersFilter
	 *
	 * @return maxAlgorithmicOrdersFilter
	 **/

	public BinanceRawSymbolMaxAlgorithmicOrdersFilter getMaxAlgorithmicOrdersFilter() {
		return maxAlgorithmicOrdersFilter;
	}

	public void setMaxAlgorithmicOrdersFilter(BinanceRawSymbolMaxAlgorithmicOrdersFilter maxAlgorithmicOrdersFilter) {
		this.maxAlgorithmicOrdersFilter = maxAlgorithmicOrdersFilter;
	}

	public BinanceRawSymbol minNotionalFilter(BinanceRawSymbolMinNotionalFilter minNotionalFilter) {
		this.minNotionalFilter = minNotionalFilter;
		return this;
	}

	/**
	 * Get minNotionalFilter
	 *
	 * @return minNotionalFilter
	 **/

	public BinanceRawSymbolMinNotionalFilter getMinNotionalFilter() {
		return minNotionalFilter;
	}

	public void setMinNotionalFilter(BinanceRawSymbolMinNotionalFilter minNotionalFilter) {
		this.minNotionalFilter = minNotionalFilter;
	}

	public BinanceRawSymbol priceFilter(BinanceRawSymbolPriceFilter priceFilter) {
		this.priceFilter = priceFilter;
		return this;
	}

	/**
	 * Get priceFilter
	 *
	 * @return priceFilter
	 **/

	public BinanceSymbolFilter getPriceFilter() {
		return getFilter(BinanceSymbolFilterType.PRICE);
	}

	public void setPriceFilter(BinanceRawSymbolPriceFilter priceFilter) {
		this.priceFilter = priceFilter;
	}

	public BinanceRawSymbol pricePercentFilter(BinanceRawSymbolPercentPriceFilter pricePercentFilter) {
		this.pricePercentFilter = pricePercentFilter;
		return this;
	}

	/**
	 * Get pricePercentFilter
	 *
	 * @return pricePercentFilter
	 **/

	public BinanceRawSymbolPercentPriceFilter getPricePercentFilter() {
		return pricePercentFilter;
	}

	public void setPricePercentFilter(BinanceRawSymbolPercentPriceFilter pricePercentFilter) {
		this.pricePercentFilter = pricePercentFilter;
	}

	public BinanceRawSymbol maxPositionFilter(BinanceRawSymbolMaxPositionFilter maxPositionFilter) {
		this.maxPositionFilter = maxPositionFilter;
		return this;
	}

	/**
	 * Get maxPositionFilter
	 *
	 * @return maxPositionFilter
	 **/

	public BinanceRawSymbolMaxPositionFilter getMaxPositionFilter() {
		return maxPositionFilter;
	}

	public void setMaxPositionFilter(BinanceRawSymbolMaxPositionFilter maxPositionFilter) {
		this.maxPositionFilter = maxPositionFilter;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BinanceRawSymbol binanceRawSymbol = (BinanceRawSymbol) o;
		return Objects.equals(this.name, binanceRawSymbol.name) &&
				Objects.equals(this.status, binanceRawSymbol.status) &&
				Objects.equals(this.baseAsset, binanceRawSymbol.baseAsset) &&
				Objects.equals(this.baseAssetPrecision, binanceRawSymbol.baseAssetPrecision) &&
				Objects.equals(this.quoteAsset, binanceRawSymbol.quoteAsset) &&
				Objects.equals(this.quoteAssetPrecision, binanceRawSymbol.quoteAssetPrecision) &&
				Objects.equals(this.orderTypes, binanceRawSymbol.orderTypes) &&
				Objects.equals(this.iceBergAllowed, binanceRawSymbol.iceBergAllowed) &&
				Objects.equals(this.isSpotTradingAllowed, binanceRawSymbol.isSpotTradingAllowed) &&
				Objects.equals(this.isMarginTradingAllowed, binanceRawSymbol.isMarginTradingAllowed) &&
				Objects.equals(this.ocoAllowed, binanceRawSymbol.ocoAllowed) &&
				Objects.equals(this.quoteOrderQuantityMarketAllowed, binanceRawSymbol.quoteOrderQuantityMarketAllowed) &&
				Objects.equals(this.baseCommissionPrecision, binanceRawSymbol.baseCommissionPrecision) &&
				Objects.equals(this.quoteCommissionPrecision, binanceRawSymbol.quoteCommissionPrecision) &&
				Objects.equals(this.permissions, binanceRawSymbol.permissions) &&
				Objects.equals(this.iceBergPartsFilter, binanceRawSymbol.iceBergPartsFilter) &&
				Objects.equals(this.lotSizeFilter, binanceRawSymbol.lotSizeFilter) &&
				Objects.equals(this.marketLotSizeFilter, binanceRawSymbol.marketLotSizeFilter) &&
				Objects.equals(this.maxOrdersFilter, binanceRawSymbol.maxOrdersFilter) &&
				Objects.equals(this.maxAlgorithmicOrdersFilter, binanceRawSymbol.maxAlgorithmicOrdersFilter) &&
				Objects.equals(this.minNotionalFilter, binanceRawSymbol.minNotionalFilter) &&
				Objects.equals(this.priceFilter, binanceRawSymbol.priceFilter) &&
				Objects.equals(this.pricePercentFilter, binanceRawSymbol.pricePercentFilter) &&
				Objects.equals(this.maxPositionFilter, binanceRawSymbol.maxPositionFilter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, status, baseAsset, baseAssetPrecision, quoteAsset, quoteAssetPrecision, orderTypes, iceBergAllowed, isSpotTradingAllowed, isMarginTradingAllowed, ocoAllowed, quoteOrderQuantityMarketAllowed, baseCommissionPrecision, quoteCommissionPrecision, permissions, iceBergPartsFilter, lotSizeFilter, marketLotSizeFilter, maxOrdersFilter, maxAlgorithmicOrdersFilter, minNotionalFilter, priceFilter, pricePercentFilter, maxPositionFilter);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BinanceRawSymbol {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    baseAsset: ").append(toIndentedString(baseAsset)).append("\n");
		sb.append("    baseAssetPrecision: ").append(toIndentedString(baseAssetPrecision)).append("\n");
		sb.append("    quoteAsset: ").append(toIndentedString(quoteAsset)).append("\n");
		sb.append("    quoteAssetPrecision: ").append(toIndentedString(quoteAssetPrecision)).append("\n");
		sb.append("    orderTypes: ").append(toIndentedString(orderTypes)).append("\n");
		sb.append("    iceBergAllowed: ").append(toIndentedString(iceBergAllowed)).append("\n");
		sb.append("    isSpotTradingAllowed: ").append(toIndentedString(isSpotTradingAllowed)).append("\n");
		sb.append("    isMarginTradingAllowed: ").append(toIndentedString(isMarginTradingAllowed)).append("\n");
		sb.append("    ocoAllowed: ").append(toIndentedString(ocoAllowed)).append("\n");
		sb.append("    quoteOrderQuantityMarketAllowed: ").append(toIndentedString(quoteOrderQuantityMarketAllowed)).append("\n");
		sb.append("    baseCommissionPrecision: ").append(toIndentedString(baseCommissionPrecision)).append("\n");
		sb.append("    quoteCommissionPrecision: ").append(toIndentedString(quoteCommissionPrecision)).append("\n");
		sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
		sb.append("    iceBergPartsFilter: ").append(toIndentedString(iceBergPartsFilter)).append("\n");
		sb.append("    lotSizeFilter: ").append(toIndentedString(lotSizeFilter)).append("\n");
		sb.append("    marketLotSizeFilter: ").append(toIndentedString(marketLotSizeFilter)).append("\n");
		sb.append("    maxOrdersFilter: ").append(toIndentedString(maxOrdersFilter)).append("\n");
		sb.append("    maxAlgorithmicOrdersFilter: ").append(toIndentedString(maxAlgorithmicOrdersFilter)).append("\n");
		sb.append("    minNotionalFilter: ").append(toIndentedString(minNotionalFilter)).append("\n");
		sb.append("    priceFilter: ").append(toIndentedString(priceFilter)).append("\n");
		sb.append("    pricePercentFilter: ").append(toIndentedString(pricePercentFilter)).append("\n");
		sb.append("    maxPositionFilter: ").append(toIndentedString(maxPositionFilter)).append("\n");
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
		out.writeValue(name);
		out.writeValue(status);
		out.writeValue(baseAsset);
		out.writeValue(baseAssetPrecision);
		out.writeValue(quoteAsset);
		out.writeValue(quoteAssetPrecision);
		out.writeValue(orderTypes);
		out.writeValue(iceBergAllowed);
		out.writeValue(isSpotTradingAllowed);
		out.writeValue(isMarginTradingAllowed);
		out.writeValue(ocoAllowed);
		out.writeValue(quoteOrderQuantityMarketAllowed);
		out.writeValue(baseCommissionPrecision);
		out.writeValue(quoteCommissionPrecision);
		out.writeValue(permissions);
		out.writeValue(iceBergPartsFilter);
		out.writeValue(lotSizeFilter);
		out.writeValue(marketLotSizeFilter);
		out.writeValue(maxOrdersFilter);
		out.writeValue(maxAlgorithmicOrdersFilter);
		out.writeValue(minNotionalFilter);
		out.writeValue(priceFilter);
		out.writeValue(pricePercentFilter);
		out.writeValue(maxPositionFilter);
		out.writeList(filters);
	}

	public int describeContents() {
		return 0;
	}

	public ArrayList<BinanceSymbolFilter> getFilters() {
		return filters;
	}

	public void setFilters(ArrayList<BinanceSymbolFilter> filters) {
		this.filters = filters;
	}
}

