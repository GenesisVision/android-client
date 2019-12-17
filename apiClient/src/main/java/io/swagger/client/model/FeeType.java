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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets FeeType
 */
@JsonAdapter(FeeType.Adapter.class)
public enum FeeType
{
	GVPROGRAMENTRY("GvProgramEntry"),
	MANAGERPROGRAMENTRY("ManagerProgramEntry"),
	UNDEFINED("Undefined"),
	GVPROGRAMSUCCESS("GvProgramSuccess"),
	GVPROGRAMSUCCESSSUM("GvProgramSuccessSum"),
	GVFUNDENTRY("GvFundEntry"),
	GVGMGVTHOLDERFEE("GvGmGvtHolderFee"),
	GVGMREGULARFEE("GvGmRegularFee"),
	MANAGERPROGRAMSUCCESS("ManagerProgramSuccess"),
	MANAGERPROGRAMSUCCESSSUM("ManagerProgramSuccessSum"),
	MANAGERFUNDENTRY("ManagerFundEntry"),
	MANAGERFUNDEXIT("ManagerFundExit"),
	GVWITHDRAWAL("GvWithdrawal"),
	MANAGERSIGNALMASTERSUCCESSFEE("ManagerSignalMasterSuccessFee"),
	MANAGERSIGNALMASTERVOLUMEFEE("ManagerSignalMasterVolumeFee"),
	GVSIGNALSUCCESSFEE("GvSignalSuccessFee"),
	GVSIGNALVOLUMEFEE("GvSignalVolumeFee"),
	GVFUNDTRADE("GvFundTrade");

	public static FeeType fromValue(String text) {
		for (FeeType b : FeeType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	FeeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<FeeType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final FeeType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public FeeType read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return FeeType.fromValue(String.valueOf(value));
		}
	}
}
