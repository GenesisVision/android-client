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
 * TwoFactorAuthenticatorConfirm
 */


public class TwoFactorAuthenticatorConfirm implements Parcelable {
  @SerializedName("password")
  private String password = null;

  @SerializedName("code")
  private String code = null;

  @SerializedName("sharedKey")
  private String sharedKey = null;

  public TwoFactorAuthenticatorConfirm() {
  }
  public TwoFactorAuthenticatorConfirm password(String password) {
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

  public TwoFactorAuthenticatorConfirm code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Get code
   * @return code
  **/
  @Schema(required = true, description = "")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public TwoFactorAuthenticatorConfirm sharedKey(String sharedKey) {
    this.sharedKey = sharedKey;
    return this;
  }

   /**
   * Get sharedKey
   * @return sharedKey
  **/
  @Schema(required = true, description = "")
  public String getSharedKey() {
    return sharedKey;
  }

  public void setSharedKey(String sharedKey) {
    this.sharedKey = sharedKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TwoFactorAuthenticatorConfirm twoFactorAuthenticatorConfirm = (TwoFactorAuthenticatorConfirm) o;
    return Objects.equals(this.password, twoFactorAuthenticatorConfirm.password) &&
        Objects.equals(this.code, twoFactorAuthenticatorConfirm.code) &&
        Objects.equals(this.sharedKey, twoFactorAuthenticatorConfirm.sharedKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, code, sharedKey);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TwoFactorAuthenticatorConfirm {\n");
    
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    sharedKey: ").append(toIndentedString(sharedKey)).append("\n");
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
    out.writeValue(code);
    out.writeValue(sharedKey);
  }

  TwoFactorAuthenticatorConfirm(Parcel in) {
    password = (String)in.readValue(null);
    code = (String)in.readValue(null);
    sharedKey = (String)in.readValue(null);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<TwoFactorAuthenticatorConfirm> CREATOR = new Parcelable.Creator<TwoFactorAuthenticatorConfirm>() {
    public TwoFactorAuthenticatorConfirm createFromParcel(Parcel in) {
      return new TwoFactorAuthenticatorConfirm(in);
    }
    public TwoFactorAuthenticatorConfirm[] newArray(int size) {
      return new TwoFactorAuthenticatorConfirm[size];
    }
  };
}
