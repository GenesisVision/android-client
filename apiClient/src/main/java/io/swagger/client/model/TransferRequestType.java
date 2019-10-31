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
 * Gets or Sets TransferRequestType
 */
@JsonAdapter(TransferRequestType.Adapter.class)
public enum TransferRequestType
{
	UNDEFINED("Undefined"),
	WALLET("Wallet"),
	TRADINGACCOUNT("TradingAccount"),
	GENESISVISIONPLATFORM("GenesisVisionPlatform"),
	SIGNALPROVIDERSETTINGS("SignalProviderSettings"),
	PROGRAM("Program"),
	FUND("Fund"),
	PAYMENTTRANSACTION("PaymentTransaction"),
	PROGRAMREQUEST("ProgramRequest"),
	FUNDREQUEST("FundRequest"),
	EXTERNALCOMMONACCOUNT("ExternalCommonAccount"),
	PENDINGTRADINGACCOUNT("PendingTradingAccount");

	public static TransferRequestType fromValue(String text) {
		for (TransferRequestType b : TransferRequestType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	TransferRequestType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<TransferRequestType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final TransferRequestType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public TransferRequestType read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return TransferRequestType.fromValue(String.valueOf(value));
		}
	}
}
