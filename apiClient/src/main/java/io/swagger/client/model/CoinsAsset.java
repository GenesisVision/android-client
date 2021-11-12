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
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * CoinsAsset
 */


public class CoinsAsset implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("asset")
  private String asset = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("provider")
  private AssetProvider provider = null;

  @SerializedName("price")
  private Double price = null;

  @SerializedName("change24Percent")
  private Double change24Percent = null;

  @SerializedName("totalVolume")
  private Double totalVolume = null;

  @SerializedName("marketCap")
  private Double marketCap = null;

  @SerializedName("details")
  private AssetInfo details = null;

  @SerializedName("chart")
  private TickerChart chart = null;

  @SerializedName("isFavorite")
  private Boolean isFavorite = null;

  @SerializedName("isTransferred")
  private Boolean isTransferred = null;

  @SerializedName("oefAssetId")
  private UUID oefAssetId = null;

  @SerializedName("amount")
  private Double amount = null;

  @SerializedName("averagePrice")
  private Double averagePrice = null;

  @SerializedName("profitCurrent")
  private Double profitCurrent = null;

  @SerializedName("total")
  private Double total = null;

  public CoinsAsset() {
  }

  public CoinsAsset id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public CoinsAsset name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CoinsAsset asset(String asset) {
    this.asset = asset;
    return this;
  }

   /**
   * Get asset
   * @return asset
  **/
  @Schema(description = "")
  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public CoinsAsset description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CoinsAsset logoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
    return this;
  }

   /**
   * Get logoUrl
   * @return logoUrl
  **/
  @Schema(description = "")
  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public CoinsAsset color(String color) {
    this.color = color;
    return this;
  }

   /**
   * Get color
   * @return color
  **/
  @Schema(description = "")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public CoinsAsset url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @Schema(description = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public CoinsAsset provider(AssetProvider provider) {
    this.provider = provider;
    return this;
  }

   /**
   * Get provider
   * @return provider
  **/
  @Schema(description = "")
  public AssetProvider getProvider() {
    return provider;
  }

  public void setProvider(AssetProvider provider) {
    this.provider = provider;
  }

  public CoinsAsset price(Double price) {
    this.price = price;
    return this;
  }

   /**
   * Get price
   * @return price
  **/
  @Schema(description = "")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public CoinsAsset change24Percent(Double change24Percent) {
    this.change24Percent = change24Percent;
    return this;
  }

   /**
   * Get change24Percent
   * @return change24Percent
  **/
  @Schema(description = "")
  public Double getChange24Percent() {
    return change24Percent;
  }

  public void setChange24Percent(Double change24Percent) {
    this.change24Percent = change24Percent;
  }

  public CoinsAsset totalVolume(Double totalVolume) {
    this.totalVolume = totalVolume;
    return this;
  }

   /**
   * Get totalVolume
   * @return totalVolume
  **/
  @Schema(description = "")
  public Double getTotalVolume() {
    return totalVolume;
  }

  public void setTotalVolume(Double totalVolume) {
    this.totalVolume = totalVolume;
  }

  public CoinsAsset marketCap(Double marketCap) {
    this.marketCap = marketCap;
    return this;
  }

   /**
   * Get marketCap
   * @return marketCap
  **/
  @Schema(description = "")
  public Double getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(Double marketCap) {
    this.marketCap = marketCap;
  }

  public CoinsAsset details(AssetInfo details) {
    this.details = details;
    return this;
  }

   /**
   * Get details
   * @return details
  **/
  @Schema(description = "")
  public AssetInfo getDetails() {
    return details;
  }

  public void setDetails(AssetInfo details) {
    this.details = details;
  }

  public CoinsAsset chart(TickerChart chart) {
    this.chart = chart;
    return this;
  }

   /**
   * Get chart
   * @return chart
  **/
  @Schema(description = "")
  public TickerChart getChart() {
    return chart;
  }

  public void setChart(TickerChart chart) {
    this.chart = chart;
  }

  public CoinsAsset isFavorite(Boolean isFavorite) {
    this.isFavorite = isFavorite;
    return this;
  }

  CoinsAsset(Parcel in) {
    id = (UUID) in.readValue(UUID.class.getClassLoader());
    name = (String) in.readValue(null);
    asset = (String) in.readValue(null);
    description = (String) in.readValue(null);
    logoUrl = (String) in.readValue(null);
    color = (String) in.readValue(null);
    url = (String) in.readValue(null);
    provider = (AssetProvider) in.readValue(AssetProvider.class.getClassLoader());
    price = (Double) in.readValue(null);
    change24Percent = (Double) in.readValue(null);
    totalVolume = (Double) in.readValue(null);
    marketCap = (Double) in.readValue(null);
    details = (AssetInfo) in.readValue(AssetInfo.class.getClassLoader());
    chart = (TickerChart) in.readValue(TickerChart.class.getClassLoader());
    isFavorite = (Boolean) in.readValue(null);
    isTransferred = (Boolean) in.readValue(null);
    oefAssetId = (UUID) in.readValue(UUID.class.getClassLoader());
    amount = (Double) in.readValue(null);
    averagePrice = (Double) in.readValue(null);
    profitCurrent = (Double) in.readValue(null);
    total = (Double) in.readValue(null);
  }

  /**
   * Get isFavorite
   *
   * @return isFavorite
   **/
  @Schema(description = "")
  public Boolean isIsFavorite() {
    return isFavorite;
  }

  public void setIsFavorite(Boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public CoinsAsset isTransferred(Boolean isTransferred) {
    this.isTransferred = isTransferred;
    return this;
  }

  /**
   * Get isTransferred
   *
   * @return isTransferred
   **/
  @Schema(description = "")
  public Boolean isIsTransferred() {
    return isTransferred;
  }

  public void setIsTransferred(Boolean isTransferred) {
    this.isTransferred = isTransferred;
  }

  public CoinsAsset oefAssetId(UUID oefAssetId) {
    this.oefAssetId = oefAssetId;
    return this;
  }

  public void setOefAssetId(UUID oefAssetId) {
    this.oefAssetId = oefAssetId;
  }

  public CoinsAsset amount(Double amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @Schema(description = "")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public CoinsAsset averagePrice(Double averagePrice) {
    this.averagePrice = averagePrice;
    return this;
  }

   /**
   * Get averagePrice
   * @return averagePrice
  **/
  @Schema(description = "")
  public Double getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(Double averagePrice) {
    this.averagePrice = averagePrice;
  }

  public CoinsAsset profitCurrent(Double profitCurrent) {
    this.profitCurrent = profitCurrent;
    return this;
  }

   /**
   * Get profitCurrent
   * @return profitCurrent
  **/
  @Schema(description = "")
  public Double getProfitCurrent() {
    return profitCurrent;
  }

  public void setProfitCurrent(Double profitCurrent) {
    this.profitCurrent = profitCurrent;
  }

  public CoinsAsset total(Double total) {
    this.total = total;
    return this;
  }

   /**
   * Get total
    * @return total
    **/
   @Schema(description = "")
   public Double getTotal() {
     return total;
   }

  public void setTotal(Double total) {
    this.total = total;
  }

  /**
   * Get oefAssetId
   *
   * @return oefAssetId
   **/
  @Schema(description = "")
  public UUID getOefAssetId() {
    return oefAssetId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CoinsAsset coinsAsset = (CoinsAsset) o;
    return Objects.equals(this.id, coinsAsset.id) &&
            Objects.equals(this.name, coinsAsset.name) &&
            Objects.equals(this.asset, coinsAsset.asset) &&
            Objects.equals(this.description, coinsAsset.description) &&
            Objects.equals(this.logoUrl, coinsAsset.logoUrl) &&
            Objects.equals(this.color, coinsAsset.color) &&
            Objects.equals(this.url, coinsAsset.url) &&
            Objects.equals(this.provider, coinsAsset.provider) &&
            Objects.equals(this.price, coinsAsset.price) &&
            Objects.equals(this.change24Percent, coinsAsset.change24Percent) &&
            Objects.equals(this.totalVolume, coinsAsset.totalVolume) &&
            Objects.equals(this.marketCap, coinsAsset.marketCap) &&
            Objects.equals(this.details, coinsAsset.details) &&
            Objects.equals(this.chart, coinsAsset.chart) &&
            Objects.equals(this.isFavorite, coinsAsset.isFavorite) &&
            Objects.equals(this.isTransferred, coinsAsset.isTransferred) &&
            Objects.equals(this.oefAssetId, coinsAsset.oefAssetId) &&
            Objects.equals(this.amount, coinsAsset.amount) &&
            Objects.equals(this.averagePrice, coinsAsset.averagePrice) &&
            Objects.equals(this.profitCurrent, coinsAsset.profitCurrent) &&
            Objects.equals(this.total, coinsAsset.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, asset, description, logoUrl, color, url, provider, price, change24Percent, totalVolume, marketCap, details, chart, isFavorite, isTransferred, oefAssetId, amount, averagePrice, profitCurrent, total);
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CoinsAsset {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    change24Percent: ").append(toIndentedString(change24Percent)).append("\n");
    sb.append("    totalVolume: ").append(toIndentedString(totalVolume)).append("\n");
    sb.append("    marketCap: ").append(toIndentedString(marketCap)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    chart: ").append(toIndentedString(chart)).append("\n");
    sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
    sb.append("    isTransferred: ").append(toIndentedString(isTransferred)).append("\n");
    sb.append("    oefAssetId: ").append(toIndentedString(oefAssetId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    averagePrice: ").append(toIndentedString(averagePrice)).append("\n");
    sb.append("    profitCurrent: ").append(toIndentedString(profitCurrent)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public void writeToParcel(Parcel out, int flags) {
    out.writeValue(id);
    out.writeValue(name);
    out.writeValue(asset);
    out.writeValue(description);
    out.writeValue(logoUrl);
    out.writeValue(color);
    out.writeValue(url);
    out.writeValue(provider);
    out.writeValue(price);
    out.writeValue(change24Percent);
    out.writeValue(totalVolume);
    out.writeValue(marketCap);
    out.writeValue(details);
    out.writeValue(chart);
    out.writeValue(isFavorite);
    out.writeValue(isTransferred);
    out.writeValue(oefAssetId);
    out.writeValue(amount);
    out.writeValue(averagePrice);
    out.writeValue(profitCurrent);
    out.writeValue(total);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<CoinsAsset> CREATOR = new Parcelable.Creator<CoinsAsset>() {
    public CoinsAsset createFromParcel(Parcel in) {
      return new CoinsAsset(in);
    }
    public CoinsAsset[] newArray(int size) {
      return new CoinsAsset[size];
    }
  };
}
