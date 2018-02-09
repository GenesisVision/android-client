/*
 * Tournament Core API
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * IFormFile
 */

public class IFormFile
{
	@SerializedName("contentType")
	private String contentType = null;

	@SerializedName("contentDisposition")
	private String contentDisposition = null;

	@SerializedName("headers")
	private Map<String, List<String>> headers = null;

	@SerializedName("length")
	private Long length = null;

	@SerializedName("name")
	private String name = null;

	@SerializedName("fileName")
	private String fileName = null;

	/**
	 * Get contentType
	 *
	 * @return contentType
	 **/
	@ApiModelProperty(value = "")
	public String getContentType() {
		return contentType;
	}

	/**
	 * Get contentDisposition
	 *
	 * @return contentDisposition
	 **/
	@ApiModelProperty(value = "")
	public String getContentDisposition() {
		return contentDisposition;
	}

	public IFormFile headers(Map<String, List<String>> headers) {
		this.headers = headers;
		return this;
	}

	public IFormFile putHeadersItem(String key, List<String> headersItem) {
		if (this.headers == null) {
			this.headers = new HashMap<String, List<String>>();
		}
		this.headers.put(key, headersItem);
		return this;
	}

	/**
	 * Get headers
	 *
	 * @return headers
	 **/
	@ApiModelProperty(value = "")
	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	/**
	 * Get length
	 *
	 * @return length
	 **/
	@ApiModelProperty(value = "")
	public Long getLength() {
		return length;
	}

	/**
	 * Get name
	 *
	 * @return name
	 **/
	@ApiModelProperty(value = "")
	public String getName() {
		return name;
	}

	/**
	 * Get fileName
	 *
	 * @return fileName
	 **/
	@ApiModelProperty(value = "")
	public String getFileName() {
		return fileName;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		IFormFile iformFile = (IFormFile) o;
		return Objects.equals(this.contentType, iformFile.contentType) &&
				Objects.equals(this.contentDisposition, iformFile.contentDisposition) &&
				Objects.equals(this.headers, iformFile.headers) &&
				Objects.equals(this.length, iformFile.length) &&
				Objects.equals(this.name, iformFile.name) &&
				Objects.equals(this.fileName, iformFile.fileName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentType, contentDisposition, headers, length, name, fileName);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class IFormFile {\n");

		sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
		sb.append("    contentDisposition: ").append(toIndentedString(contentDisposition)).append("\n");
		sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
		sb.append("    length: ").append(toIndentedString(length)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
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
