/*
 * Core API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProfileFullViewModel
 */

public class ProfileFullViewModel
{
	@SerializedName("firstName")
	private String firstName = null;

	@SerializedName("middleName")
	private String middleName = null;

	@SerializedName("lastName")
	private String lastName = null;

	@SerializedName("documentType")
	private String documentType = null;

	@SerializedName("documentNumber")
	private String documentNumber = null;

	@SerializedName("country")
	private String country = null;

	@SerializedName("city")
	private String city = null;

	@SerializedName("address")
	private String address = null;

	@SerializedName("phone")
	private String phone = null;

	@SerializedName("birthday")
	private DateTime birthday = null;

	@SerializedName("gender")
	private Boolean gender = null;

	@SerializedName("avatar")
	private String avatar = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("email")
	private String email = null;

	@SerializedName("balance")
	private Double balance = null;

	public ProfileFullViewModel firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Get firstName
	 *
	 * @return firstName
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return middleName
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return lastName
	 **/
	@ApiModelProperty(value = "")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ProfileFullViewModel documentType(String documentType) {
		this.documentType = documentType;
		return this;
	}

	/**
	 * Get documentType
	 *
	 * @return documentType
	 **/
	@ApiModelProperty(value = "")
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public ProfileFullViewModel documentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
		return this;
	}

	/**
	 * Get documentNumber
	 *
	 * @return documentNumber
	 **/
	@ApiModelProperty(value = "")
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public ProfileFullViewModel country(String country) {
		this.country = country;
		return this;
	}

	/**
	 * Get country
	 *
	 * @return country
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return city
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return address
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return phone
	 **/
	@ApiModelProperty(value = "")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ProfileFullViewModel birthday(DateTime birthday) {
		this.birthday = birthday;
		return this;
	}

	/**
	 * Get birthday
	 *
	 * @return birthday
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return gender
	 **/
	@ApiModelProperty(value = "")
	public Boolean isGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public ProfileFullViewModel avatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	/**
	 * Get avatar
	 *
	 * @return avatar
	 **/
	@ApiModelProperty(value = "")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public ProfileFullViewModel id(UUID id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(value = "")
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
	 *
	 * @return email
	 **/
	@ApiModelProperty(value = "")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ProfileFullViewModel balance(Double balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * Get balance
	 *
	 * @return balance
	 **/
	@ApiModelProperty(value = "")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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
		return Objects.equals(this.firstName, profileFullViewModel.firstName) &&
				Objects.equals(this.middleName, profileFullViewModel.middleName) &&
				Objects.equals(this.lastName, profileFullViewModel.lastName) &&
				Objects.equals(this.documentType, profileFullViewModel.documentType) &&
				Objects.equals(this.documentNumber, profileFullViewModel.documentNumber) &&
				Objects.equals(this.country, profileFullViewModel.country) &&
				Objects.equals(this.city, profileFullViewModel.city) &&
				Objects.equals(this.address, profileFullViewModel.address) &&
				Objects.equals(this.phone, profileFullViewModel.phone) &&
				Objects.equals(this.birthday, profileFullViewModel.birthday) &&
				Objects.equals(this.gender, profileFullViewModel.gender) &&
				Objects.equals(this.avatar, profileFullViewModel.avatar) &&
				Objects.equals(this.id, profileFullViewModel.id) &&
				Objects.equals(this.email, profileFullViewModel.email) &&
				Objects.equals(this.balance, profileFullViewModel.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, middleName, lastName, documentType, documentNumber, country, city, address, phone, birthday, gender, avatar, id, email, balance);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProfileFullViewModel {\n");

		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
		sb.append("    documentNumber: ").append(toIndentedString(documentNumber)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    city: ").append(toIndentedString(city)).append("\n");
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
		sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
		sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
		sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
		sb.append("    avatar: ").append(toIndentedString(avatar)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

}

