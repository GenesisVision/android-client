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
import android.os.Parcelable;
import android.os.Parcel;
/**
 * TwoFactorCodeWithPassword
 */


public class TwoFactorCodeWithPassword implements Parcelable {
  @SerializedName("password")
  private String password = null;

  @SerializedName("twoFactorCode")
  private String twoFactorCode = null;

  @SerializedName("recoveryCode")
  private String recoveryCode = null;

  public TwoFactorCodeWithPassword() {
  }
  public TwoFactorCodeWithPassword password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  @Schema(required = true, description = "")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public TwoFactorCodeWithPassword twoFactorCode(String twoFactorCode) {
    this.twoFactorCode = twoFactorCode;
    return this;
  }

   /**
   * Get twoFactorCode
   * @return twoFactorCode
  **/
  @Schema(description = "")
  public String getTwoFactorCode() {
    return twoFactorCode;
  }

  public void setTwoFactorCode(String twoFactorCode) {
    this.twoFactorCode = twoFactorCode;
  }

  public TwoFactorCodeWithPassword recoveryCode(String recoveryCode) {
    this.recoveryCode = recoveryCode;
    return this;
  }

   /**
   * Get recoveryCode
   * @return recoveryCode
  **/
  @Schema(description = "")
  public String getRecoveryCode() {
    return recoveryCode;
  }

  public void setRecoveryCode(String recoveryCode) {
    this.recoveryCode = recoveryCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TwoFactorCodeWithPassword twoFactorCodeWithPassword = (TwoFactorCodeWithPassword) o;
    return Objects.equals(this.password, twoFactorCodeWithPassword.password) &&
        Objects.equals(this.twoFactorCode, twoFactorCodeWithPassword.twoFactorCode) &&
        Objects.equals(this.recoveryCode, twoFactorCodeWithPassword.recoveryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, twoFactorCode, recoveryCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TwoFactorCodeWithPassword {\n");
    
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    twoFactorCode: ").append(toIndentedString(twoFactorCode)).append("\n");
    sb.append("    recoveryCode: ").append(toIndentedString(recoveryCode)).append("\n");
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
    out.writeValue(password);
    out.writeValue(twoFactorCode);
    out.writeValue(recoveryCode);
  }

  TwoFactorCodeWithPassword(Parcel in) {
    password = (String)in.readValue(null);
    twoFactorCode = (String)in.readValue(null);
    recoveryCode = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<TwoFactorCodeWithPassword> CREATOR = new Parcelable.Creator<TwoFactorCodeWithPassword>() {
    public TwoFactorCodeWithPassword createFromParcel(Parcel in) {
      return new TwoFactorCodeWithPassword(in);
    }
    public TwoFactorCodeWithPassword[] newArray(int size) {
      return new TwoFactorCodeWithPassword[size];
    }
  };
}
