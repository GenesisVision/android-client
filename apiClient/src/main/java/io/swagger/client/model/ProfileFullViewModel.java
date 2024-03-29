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
import io.swagger.client.model.Currency;
import io.swagger.client.model.SocialViewMode;
import io.swagger.client.model.UserVerificationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.UUID;
import org.joda.time.DateTime;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * ProfileFullViewModel
 */


public class ProfileFullViewModel implements Parcelable {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("email")
  private String email = null;

  @SerializedName("firstName")
  private String firstName = null;

  @SerializedName("middleName")
  private String middleName = null;

  @SerializedName("lastName")
  private String lastName = null;

  @SerializedName("country")
  private String country = null;

  @SerializedName("city")
  private String city = null;

  @SerializedName("address")
  private String address = null;

  @SerializedName("phone")
  private String phone = null;

  @SerializedName("phoneNumberConfirmed")
  private Boolean phoneNumberConfirmed = null;

  @SerializedName("birthday")
  private DateTime birthday = null;

  @SerializedName("gender")
  private Boolean gender = null;

  @SerializedName("logoUrl")
  private String logoUrl = null;

  @SerializedName("about")
  private String about = null;

  @SerializedName("userName")
  private String userName = null;

  @SerializedName("index")
  private String index = null;

  @SerializedName("citizenship")
  private String citizenship = null;

  @SerializedName("refUrl")
  private String refUrl = null;

  @SerializedName("verificationStatus")
  private UserVerificationStatus verificationStatus = null;

  @SerializedName("isPublicInvestor")
  private Boolean isPublicInvestor = null;

  @SerializedName("platformCurrency")
  private Currency platformCurrency = null;

  @SerializedName("whoCanPostToMayWall")
  private SocialViewMode whoCanPostToMayWall = null;

  @SerializedName("whoCanCommentOnMyPosts")
  private SocialViewMode whoCanCommentOnMyPosts = null;

  @SerializedName("whoCanViewCommentsOnMyPosts")
  private SocialViewMode whoCanViewCommentsOnMyPosts = null;

  public ProfileFullViewModel() {
  }
  public ProfileFullViewModel id(UUID id) {
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

  public ProfileFullViewModel email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @Schema(description = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ProfileFullViewModel firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @Schema(description = "")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public ProfileFullViewModel middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

   /**
   * Get middleName
   * @return middleName
  **/
  @Schema(description = "")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public ProfileFullViewModel lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @Schema(description = "")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public ProfileFullViewModel country(String country) {
    this.country = country;
    return this;
  }

   /**
   * Get country
   * @return country
  **/
  @Schema(description = "")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public ProfileFullViewModel city(String city) {
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @Schema(description = "")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public ProfileFullViewModel address(String address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @Schema(description = "")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public ProfileFullViewModel phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @Schema(description = "")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public ProfileFullViewModel phoneNumberConfirmed(Boolean phoneNumberConfirmed) {
    this.phoneNumberConfirmed = phoneNumberConfirmed;
    return this;
  }

   /**
   * Get phoneNumberConfirmed
   * @return phoneNumberConfirmed
  **/
  @Schema(description = "")
  public Boolean isPhoneNumberConfirmed() {
    return phoneNumberConfirmed;
  }

  public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
    this.phoneNumberConfirmed = phoneNumberConfirmed;
  }

  public ProfileFullViewModel birthday(DateTime birthday) {
    this.birthday = birthday;
    return this;
  }

   /**
   * Get birthday
   * @return birthday
  **/
  @Schema(description = "")
  public DateTime getBirthday() {
    return birthday;
  }

  public void setBirthday(DateTime birthday) {
    this.birthday = birthday;
  }

  public ProfileFullViewModel gender(Boolean gender) {
    this.gender = gender;
    return this;
  }

   /**
   * Get gender
   * @return gender
  **/
  @Schema(description = "")
  public Boolean isGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public ProfileFullViewModel logoUrl(String logoUrl) {
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

  public ProfileFullViewModel about(String about) {
    this.about = about;
    return this;
  }

   /**
   * Get about
   * @return about
  **/
  @Schema(description = "")
  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public ProfileFullViewModel userName(String userName) {
    this.userName = userName;
    return this;
  }

   /**
   * Get userName
   * @return userName
  **/
  @Schema(description = "")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ProfileFullViewModel index(String index) {
    this.index = index;
    return this;
  }

   /**
   * Get index
   * @return index
  **/
  @Schema(description = "")
  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }

  public ProfileFullViewModel citizenship(String citizenship) {
    this.citizenship = citizenship;
    return this;
  }

   /**
   * Get citizenship
   * @return citizenship
  **/
  @Schema(description = "")
  public String getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(String citizenship) {
    this.citizenship = citizenship;
  }

  public ProfileFullViewModel refUrl(String refUrl) {
    this.refUrl = refUrl;
    return this;
  }

   /**
   * Get refUrl
   * @return refUrl
  **/
  @Schema(description = "")
  public String getRefUrl() {
    return refUrl;
  }

  public void setRefUrl(String refUrl) {
    this.refUrl = refUrl;
  }

  public ProfileFullViewModel verificationStatus(UserVerificationStatus verificationStatus) {
    this.verificationStatus = verificationStatus;
    return this;
  }

   /**
   * Get verificationStatus
   * @return verificationStatus
  **/
  @Schema(description = "")
  public UserVerificationStatus getVerificationStatus() {
    return verificationStatus;
  }

  public void setVerificationStatus(UserVerificationStatus verificationStatus) {
    this.verificationStatus = verificationStatus;
  }

  public ProfileFullViewModel isPublicInvestor(Boolean isPublicInvestor) {
    this.isPublicInvestor = isPublicInvestor;
    return this;
  }

   /**
   * Get isPublicInvestor
   * @return isPublicInvestor
  **/
  @Schema(description = "")
  public Boolean isIsPublicInvestor() {
    return isPublicInvestor;
  }

  public void setIsPublicInvestor(Boolean isPublicInvestor) {
    this.isPublicInvestor = isPublicInvestor;
  }

  public ProfileFullViewModel platformCurrency(Currency platformCurrency) {
    this.platformCurrency = platformCurrency;
    return this;
  }

   /**
   * Get platformCurrency
   * @return platformCurrency
  **/
  @Schema(description = "")
  public Currency getPlatformCurrency() {
    return platformCurrency;
  }

  public void setPlatformCurrency(Currency platformCurrency) {
    this.platformCurrency = platformCurrency;
  }

  public ProfileFullViewModel whoCanPostToMayWall(SocialViewMode whoCanPostToMayWall) {
    this.whoCanPostToMayWall = whoCanPostToMayWall;
    return this;
  }

   /**
   * Get whoCanPostToMayWall
   * @return whoCanPostToMayWall
  **/
  @Schema(description = "")
  public SocialViewMode getWhoCanPostToMayWall() {
    return whoCanPostToMayWall;
  }

  public void setWhoCanPostToMayWall(SocialViewMode whoCanPostToMayWall) {
    this.whoCanPostToMayWall = whoCanPostToMayWall;
  }

  public ProfileFullViewModel whoCanCommentOnMyPosts(SocialViewMode whoCanCommentOnMyPosts) {
    this.whoCanCommentOnMyPosts = whoCanCommentOnMyPosts;
    return this;
  }

   /**
   * Get whoCanCommentOnMyPosts
   * @return whoCanCommentOnMyPosts
  **/
  @Schema(description = "")
  public SocialViewMode getWhoCanCommentOnMyPosts() {
    return whoCanCommentOnMyPosts;
  }

  public void setWhoCanCommentOnMyPosts(SocialViewMode whoCanCommentOnMyPosts) {
    this.whoCanCommentOnMyPosts = whoCanCommentOnMyPosts;
  }

  public ProfileFullViewModel whoCanViewCommentsOnMyPosts(SocialViewMode whoCanViewCommentsOnMyPosts) {
    this.whoCanViewCommentsOnMyPosts = whoCanViewCommentsOnMyPosts;
    return this;
  }

   /**
   * Get whoCanViewCommentsOnMyPosts
   * @return whoCanViewCommentsOnMyPosts
  **/
  @Schema(description = "")
  public SocialViewMode getWhoCanViewCommentsOnMyPosts() {
    return whoCanViewCommentsOnMyPosts;
  }

  public void setWhoCanViewCommentsOnMyPosts(SocialViewMode whoCanViewCommentsOnMyPosts) {
    this.whoCanViewCommentsOnMyPosts = whoCanViewCommentsOnMyPosts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileFullViewModel profileFullViewModel = (ProfileFullViewModel) o;
    return Objects.equals(this.id, profileFullViewModel.id) &&
        Objects.equals(this.email, profileFullViewModel.email) &&
        Objects.equals(this.firstName, profileFullViewModel.firstName) &&
        Objects.equals(this.middleName, profileFullViewModel.middleName) &&
        Objects.equals(this.lastName, profileFullViewModel.lastName) &&
        Objects.equals(this.country, profileFullViewModel.country) &&
        Objects.equals(this.city, profileFullViewModel.city) &&
        Objects.equals(this.address, profileFullViewModel.address) &&
        Objects.equals(this.phone, profileFullViewModel.phone) &&
        Objects.equals(this.phoneNumberConfirmed, profileFullViewModel.phoneNumberConfirmed) &&
        Objects.equals(this.birthday, profileFullViewModel.birthday) &&
        Objects.equals(this.gender, profileFullViewModel.gender) &&
        Objects.equals(this.logoUrl, profileFullViewModel.logoUrl) &&
        Objects.equals(this.about, profileFullViewModel.about) &&
        Objects.equals(this.userName, profileFullViewModel.userName) &&
        Objects.equals(this.index, profileFullViewModel.index) &&
        Objects.equals(this.citizenship, profileFullViewModel.citizenship) &&
        Objects.equals(this.refUrl, profileFullViewModel.refUrl) &&
        Objects.equals(this.verificationStatus, profileFullViewModel.verificationStatus) &&
        Objects.equals(this.isPublicInvestor, profileFullViewModel.isPublicInvestor) &&
        Objects.equals(this.platformCurrency, profileFullViewModel.platformCurrency) &&
        Objects.equals(this.whoCanPostToMayWall, profileFullViewModel.whoCanPostToMayWall) &&
        Objects.equals(this.whoCanCommentOnMyPosts, profileFullViewModel.whoCanCommentOnMyPosts) &&
        Objects.equals(this.whoCanViewCommentsOnMyPosts, profileFullViewModel.whoCanViewCommentsOnMyPosts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, firstName, middleName, lastName, country, city, address, phone, phoneNumberConfirmed, birthday, gender, logoUrl, about, userName, index, citizenship, refUrl, verificationStatus, isPublicInvestor, platformCurrency, whoCanPostToMayWall, whoCanCommentOnMyPosts, whoCanViewCommentsOnMyPosts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfileFullViewModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    phoneNumberConfirmed: ").append(toIndentedString(phoneNumberConfirmed)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    about: ").append(toIndentedString(about)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    citizenship: ").append(toIndentedString(citizenship)).append("\n");
    sb.append("    refUrl: ").append(toIndentedString(refUrl)).append("\n");
    sb.append("    verificationStatus: ").append(toIndentedString(verificationStatus)).append("\n");
    sb.append("    isPublicInvestor: ").append(toIndentedString(isPublicInvestor)).append("\n");
    sb.append("    platformCurrency: ").append(toIndentedString(platformCurrency)).append("\n");
    sb.append("    whoCanPostToMayWall: ").append(toIndentedString(whoCanPostToMayWall)).append("\n");
    sb.append("    whoCanCommentOnMyPosts: ").append(toIndentedString(whoCanCommentOnMyPosts)).append("\n");
    sb.append("    whoCanViewCommentsOnMyPosts: ").append(toIndentedString(whoCanViewCommentsOnMyPosts)).append("\n");
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
    out.writeValue(email);
    out.writeValue(firstName);
    out.writeValue(middleName);
    out.writeValue(lastName);
    out.writeValue(country);
    out.writeValue(city);
    out.writeValue(address);
    out.writeValue(phone);
    out.writeValue(phoneNumberConfirmed);
    out.writeValue(birthday);
    out.writeValue(gender);
    out.writeValue(logoUrl);
    out.writeValue(about);
    out.writeValue(userName);
    out.writeValue(index);
    out.writeValue(citizenship);
    out.writeValue(refUrl);
    out.writeValue(verificationStatus);
    out.writeValue(isPublicInvestor);
    out.writeValue(platformCurrency);
    out.writeValue(whoCanPostToMayWall);
    out.writeValue(whoCanCommentOnMyPosts);
    out.writeValue(whoCanViewCommentsOnMyPosts);
  }

  ProfileFullViewModel(Parcel in) {
    id = (UUID)in.readValue(UUID.class.getClassLoader());
    email = (String)in.readValue(null);
    firstName = (String)in.readValue(null);
    middleName = (String)in.readValue(null);
    lastName = (String)in.readValue(null);
    country = (String)in.readValue(null);
    city = (String)in.readValue(null);
    address = (String)in.readValue(null);
    phone = (String)in.readValue(null);
    phoneNumberConfirmed = (Boolean)in.readValue(null);
    birthday = (DateTime)in.readValue(DateTime.class.getClassLoader());
    gender = (Boolean)in.readValue(null);
    logoUrl = (String)in.readValue(null);
    about = (String)in.readValue(null);
    userName = (String)in.readValue(null);
    index = (String)in.readValue(null);
    citizenship = (String)in.readValue(null);
    refUrl = (String)in.readValue(null);
    verificationStatus = (UserVerificationStatus)in.readValue(UserVerificationStatus.class.getClassLoader());
    isPublicInvestor = (Boolean)in.readValue(null);
    platformCurrency = (Currency)in.readValue(Currency.class.getClassLoader());
    whoCanPostToMayWall = (SocialViewMode)in.readValue(SocialViewMode.class.getClassLoader());
    whoCanCommentOnMyPosts = (SocialViewMode)in.readValue(SocialViewMode.class.getClassLoader());
    whoCanViewCommentsOnMyPosts = (SocialViewMode)in.readValue(SocialViewMode.class.getClassLoader());
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<ProfileFullViewModel> CREATOR = new Parcelable.Creator<ProfileFullViewModel>() {
    public ProfileFullViewModel createFromParcel(Parcel in) {
      return new ProfileFullViewModel(in);
    }
    public ProfileFullViewModel[] newArray(int size) {
      return new ProfileFullViewModel[size];
    }
  };
}
