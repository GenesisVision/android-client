/*
 * Core API v1.0
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.0
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

import io.swagger.annotations.ApiModelProperty;

/**
 * UpdatePersonalDetailViewModel
 */

public class UpdatePersonalDetailViewModel
{
	@SerializedName("firstName")
	private String firstName = null;

	@SerializedName("middleName")
	private String middleName = null;

	@SerializedName("lastName")
	private String lastName = null;

	@SerializedName("birthday")
	private DateTime birthday = null;

	@SerializedName("citizenship")
	private String citizenship = null;

	@SerializedName("gender")
	private Boolean gender = null;

	@SerializedName("documentId")
	private String documentId = null;

	@SerializedName("country")
	private String country = null;

	@SerializedName("city")
	private String city = null;

	@SerializedName("address")
	private String address = null;

	@SerializedName("index")
	private String index = null;

	public UpdatePersonalDetailViewModel firstName(String firstName) {
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

	public UpdatePersonalDetailViewModel middleName(String middleName) {
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

	public UpdatePersonalDetailViewModel lastName(String lastName) {
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

	public UpdatePersonalDetailViewModel birthday(DateTime birthday) {
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

	public UpdatePersonalDetailViewModel citizenship(String citizenship) {
		this.citizenship = citizenship;
		return this;
	}

	/**
	 * Get citizenship
	 *
	 * @return citizenship
	 **/
	@ApiModelProperty(value = "")
	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public UpdatePersonalDetailViewModel gender(Boolean gender) {
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

	public UpdatePersonalDetailViewModel documentId(String documentId) {
		this.documentId = documentId;
		return this;
	}

	/**
	 * Get documentId
	 *
	 * @return documentId
	 **/
	@ApiModelProperty(value = "")
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public UpdatePersonalDetailViewModel country(String country) {
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

	public UpdatePersonalDetailViewModel city(String city) {
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

	public UpdatePersonalDetailViewModel address(String address) {
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

	public UpdatePersonalDetailViewModel index(String index) {
		this.index = index;
		return this;
	}

	/**
	 * Get index
	 *
	 * @return index
	 **/
	@ApiModelProperty(value = "")
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UpdatePersonalDetailViewModel updatePersonalDetailViewModel = (UpdatePersonalDetailViewModel) o;
		return Objects.equals(this.firstName, updatePersonalDetailViewModel.firstName) &&
				Objects.equals(this.middleName, updatePersonalDetailViewModel.middleName) &&
				Objects.equals(this.lastName, updatePersonalDetailViewModel.lastName) &&
				Objects.equals(this.birthday, updatePersonalDetailViewModel.birthday) &&
				Objects.equals(this.citizenship, updatePersonalDetailViewModel.citizenship) &&
				Objects.equals(this.gender, updatePersonalDetailViewModel.gender) &&
				Objects.equals(this.documentId, updatePersonalDetailViewModel.documentId) &&
				Objects.equals(this.country, updatePersonalDetailViewModel.country) &&
				Objects.equals(this.city, updatePersonalDetailViewModel.city) &&
				Objects.equals(this.address, updatePersonalDetailViewModel.address) &&
				Objects.equals(this.index, updatePersonalDetailViewModel.index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, middleName, lastName, birthday, citizenship, gender, documentId, country, city, address, index);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UpdatePersonalDetailViewModel {\n");

		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
		sb.append("    citizenship: ").append(toIndentedString(citizenship)).append("\n");
		sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
		sb.append("    documentId: ").append(toIndentedString(documentId)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    city: ").append(toIndentedString(city)).append("\n");
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
		sb.append("    index: ").append(toIndentedString(index)).append("\n");
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
