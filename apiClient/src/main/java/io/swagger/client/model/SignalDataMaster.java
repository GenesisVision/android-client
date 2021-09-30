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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SignalDataMaster
 */


public class SignalDataMaster implements Parcelable
{
  public static final Parcelable.Creator<SignalDataMaster> CREATOR = new Parcelable.Creator<SignalDataMaster>()
  {
    public SignalDataMaster createFromParcel(Parcel in) {
      return new SignalDataMaster(in);
    }

    public SignalDataMaster[] newArray(int size) {
      return new SignalDataMaster[size];
    }
  };

  @SerializedName("login")
  private String login = null;

  @SerializedName("share")
  private Double share = null;

  public SignalDataMaster() {
  }

  SignalDataMaster(Parcel in) {
    login = (String) in.readValue(null);
    share = (Double) in.readValue(null);
  }

  public SignalDataMaster login(String login) {
    this.login = login;
    return this;
  }

  /**
   * Get login
   *
   * @return login
   **/
  @Schema(description = "")
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public SignalDataMaster share(Double share) {
    this.share = share;
    return this;
  }

  /**
   * Get share
   *
   * @return share
   **/
  @Schema(description = "")
  public Double getShare() {
    return share;
  }

  public void setShare(Double share) {
    this.share = share;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignalDataMaster signalDataMaster = (SignalDataMaster) o;
    return Objects.equals(this.login, signalDataMaster.login) &&
            Objects.equals(this.share, signalDataMaster.share);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, share);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignalDataMaster {\n");

    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    share: ").append(toIndentedString(share)).append("\n");
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
    out.writeValue(login);
    out.writeValue(share);
  }

  public int describeContents() {
    return 0;
  }
}
