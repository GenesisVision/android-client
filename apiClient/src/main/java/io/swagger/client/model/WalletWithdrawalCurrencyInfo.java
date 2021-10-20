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
import io.swagger.client.model.Blockchain;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * WalletWithdrawalCurrencyInfo
 */


public class WalletWithdrawalCurrencyInfo implements Parcelable {
  @SerializedName("value")
  private Double value = null;

  @SerializedName("blockchain")
  private Blockchain blockchain = null;

  @SerializedName("blockchainTitle")
  private String blockchainTitle = null;

  public WalletWithdrawalCurrencyInfo() {
  }
  public WalletWithdrawalCurrencyInfo value(Double value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public WalletWithdrawalCurrencyInfo blockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
    return this;
  }

   /**
   * Get blockchain
   * @return blockchain
  **/
  @Schema(description = "")
  public Blockchain getBlockchain() {
    return blockchain;
  }

  public void setBlockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
  }

  public WalletWithdrawalCurrencyInfo blockchainTitle(String blockchainTitle) {
    this.blockchainTitle = blockchainTitle;
    return this;
  }

   /**
   * Get blockchainTitle
   * @return blockchainTitle
  **/
  @Schema(description = "")
  public String getBlockchainTitle() {
    return blockchainTitle;
  }

  public void setBlockchainTitle(String blockchainTitle) {
    this.blockchainTitle = blockchainTitle;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WalletWithdrawalCurrencyInfo walletWithdrawalCurrencyInfo = (WalletWithdrawalCurrencyInfo) o;
    return Objects.equals(this.value, walletWithdrawalCurrencyInfo.value) &&
        Objects.equals(this.blockchain, walletWithdrawalCurrencyInfo.blockchain) &&
        Objects.equals(this.blockchainTitle, walletWithdrawalCurrencyInfo.blockchainTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, blockchain, blockchainTitle);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WalletWithdrawalCurrencyInfo {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    blockchain: ").append(toIndentedString(blockchain)).append("\n");
    sb.append("    blockchainTitle: ").append(toIndentedString(blockchainTitle)).append("\n");
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
    out.writeValue(value);
    out.writeValue(blockchain);
    out.writeValue(blockchainTitle);
  }

  WalletWithdrawalCurrencyInfo(Parcel in) {
    value = (Double)in.readValue(null);
    blockchain = (Blockchain)in.readValue(Blockchain.class.getClassLoader());
    blockchainTitle = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<WalletWithdrawalCurrencyInfo> CREATOR = new Parcelable.Creator<WalletWithdrawalCurrencyInfo>() {
    public WalletWithdrawalCurrencyInfo createFromParcel(Parcel in) {
      return new WalletWithdrawalCurrencyInfo(in);
    }
    public WalletWithdrawalCurrencyInfo[] newArray(int size) {
      return new WalletWithdrawalCurrencyInfo[size];
    }
  };
}
