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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.UUID;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * CreateSignalProvider
 */


public class CreateSignalProvider implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("volumeFee")
  private Double volumeFee = null;

  @SerializedName("successFee")
  private Double successFee = null;

  public CreateSignalProvider() {
  }
  public CreateSignalProvider id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * AssetId
   * @return id
  **/
  @Schema(description = "AssetId")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public CreateSignalProvider volumeFee(Double volumeFee) {
    this.volumeFee = volumeFee;
    return this;
  }

   /**
   * Get volumeFee
   * @return volumeFee
  **/
  @Schema(description = "")
  public Double getVolumeFee() {
    return volumeFee;
  }

  public void setVolumeFee(Double volumeFee) {
    this.volumeFee = volumeFee;
  }

  public CreateSignalProvider successFee(Double successFee) {
    this.successFee = successFee;
    return this;
  }

   /**
   * Get successFee
   * @return successFee
  **/
  @Schema(description = "")
  public Double getSuccessFee() {
    return successFee;
  }

  public void setSuccessFee(Double successFee) {
    this.successFee = successFee;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateSignalProvider createSignalProvider = (CreateSignalProvider) o;
    return Objects.equals(this.id, createSignalProvider.id) &&
        Objects.equals(this.volumeFee, createSignalProvider.volumeFee) &&
        Objects.equals(this.successFee, createSignalProvider.successFee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, volumeFee, successFee);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateSignalProvider {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    volumeFee: ").append(toIndentedString(volumeFee)).append("\n");
    sb.append("    successFee: ").append(toIndentedString(successFee)).append("\n");
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
    out.writeValue(volumeFee);
    out.writeValue(successFee);
  }

  CreateSignalProvider(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    volumeFee = (Double)in.readValue(null);
    successFee = (Double)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<CreateSignalProvider> CREATOR = new Parcelable.Creator<CreateSignalProvider>() {
    public CreateSignalProvider createFromParcel(Parcel in) {
      return new CreateSignalProvider(in);
    }
    public CreateSignalProvider[] newArray(int size) {
      return new CreateSignalProvider[size];
    }
  };
}
