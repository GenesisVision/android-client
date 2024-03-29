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

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.AssetProvider;
import io.swagger.client.model.ChangeState;
import io.swagger.client.model.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.UUID;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * PostPlatformAssetDetailsWithPrices
 */


public class PostPlatformAssetDetailsWithPrices implements Parcelable {
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

  @SerializedName("priceCurrency")
  private Currency priceCurrency = null;

  @SerializedName("change24Percent")
  private Double change24Percent = null;

  @SerializedName("changeState")
  private ChangeState changeState = null;

  public PostPlatformAssetDetailsWithPrices() {
  }
  public PostPlatformAssetDetailsWithPrices id(UUID id) {
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

  public PostPlatformAssetDetailsWithPrices name(String name) {
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

  public PostPlatformAssetDetailsWithPrices asset(String asset) {
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

  public PostPlatformAssetDetailsWithPrices description(String description) {
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

  public PostPlatformAssetDetailsWithPrices logoUrl(String logoUrl) {
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

  public PostPlatformAssetDetailsWithPrices color(String color) {
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

  public PostPlatformAssetDetailsWithPrices url(String url) {
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

  public PostPlatformAssetDetailsWithPrices provider(AssetProvider provider) {
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

  public PostPlatformAssetDetailsWithPrices price(Double price) {
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

  public PostPlatformAssetDetailsWithPrices priceCurrency(Currency priceCurrency) {
    this.priceCurrency = priceCurrency;
    return this;
  }

   /**
   * Get priceCurrency
   * @return priceCurrency
  **/
  @Schema(description = "")
  public Currency getPriceCurrency() {
    return priceCurrency;
  }

  public void setPriceCurrency(Currency priceCurrency) {
    this.priceCurrency = priceCurrency;
  }

  public PostPlatformAssetDetailsWithPrices change24Percent(Double change24Percent) {
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

  public PostPlatformAssetDetailsWithPrices changeState(ChangeState changeState) {
    this.changeState = changeState;
    return this;
  }

   /**
   * Get changeState
   * @return changeState
  **/
  @Schema(description = "")
  public ChangeState getChangeState() {
    return changeState;
  }

  public void setChangeState(ChangeState changeState) {
    this.changeState = changeState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostPlatformAssetDetailsWithPrices postPlatformAssetDetailsWithPrices = (PostPlatformAssetDetailsWithPrices) o;
    return Objects.equals(this.id, postPlatformAssetDetailsWithPrices.id) &&
        Objects.equals(this.name, postPlatformAssetDetailsWithPrices.name) &&
        Objects.equals(this.asset, postPlatformAssetDetailsWithPrices.asset) &&
        Objects.equals(this.description, postPlatformAssetDetailsWithPrices.description) &&
        Objects.equals(this.logoUrl, postPlatformAssetDetailsWithPrices.logoUrl) &&
        Objects.equals(this.color, postPlatformAssetDetailsWithPrices.color) &&
        Objects.equals(this.url, postPlatformAssetDetailsWithPrices.url) &&
        Objects.equals(this.provider, postPlatformAssetDetailsWithPrices.provider) &&
        Objects.equals(this.price, postPlatformAssetDetailsWithPrices.price) &&
        Objects.equals(this.priceCurrency, postPlatformAssetDetailsWithPrices.priceCurrency) &&
        Objects.equals(this.change24Percent, postPlatformAssetDetailsWithPrices.change24Percent) &&
        Objects.equals(this.changeState, postPlatformAssetDetailsWithPrices.changeState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, asset, description, logoUrl, color, url, provider, price, priceCurrency, change24Percent, changeState);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostPlatformAssetDetailsWithPrices {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    priceCurrency: ").append(toIndentedString(priceCurrency)).append("\n");
    sb.append("    change24Percent: ").append(toIndentedString(change24Percent)).append("\n");
    sb.append("    changeState: ").append(toIndentedString(changeState)).append("\n");
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
    out.writeValue(name);
    out.writeValue(asset);
    out.writeValue(description);
    out.writeValue(logoUrl);
    out.writeValue(color);
    out.writeValue(url);
    out.writeValue(provider);
    out.writeValue(price);
    out.writeValue(priceCurrency);
    out.writeValue(change24Percent);
    out.writeValue(changeState);
  }

  PostPlatformAssetDetailsWithPrices(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    name = (String)in.readValue(null);
    asset = (String)in.readValue(null);
    description = (String)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    color = (String)in.readValue(null);
    url = (String)in.readValue(null);
    provider = (AssetProvider)in.readValue(AssetProvider.class.getClassLoader());
    price = (Double)in.readValue(null);
    priceCurrency = (Currency)in.readValue(Currency.class.getClassLoader());
    change24Percent = (Double)in.readValue(null);
    changeState = (ChangeState)in.readValue(ChangeState.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<PostPlatformAssetDetailsWithPrices> CREATOR = new Parcelable.Creator<PostPlatformAssetDetailsWithPrices>() {
    public PostPlatformAssetDetailsWithPrices createFromParcel(Parcel in) {
      return new PostPlatformAssetDetailsWithPrices(in);
    }
    public PostPlatformAssetDetailsWithPrices[] newArray(int size) {
      return new PostPlatformAssetDetailsWithPrices[size];
    }
  };
}
