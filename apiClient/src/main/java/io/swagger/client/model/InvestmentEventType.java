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
 * Gets or Sets InvestmentEventType
 */
@JsonAdapter(InvestmentEventType.Adapter.class)
public enum InvestmentEventType
{
	ALL("All"),
	ASSETFINISHED("AssetFinished"),
	ASSETPERIODSTARTED("AssetPeriodStarted"),
	ASSETPERIODENDED("AssetPeriodEnded"),
	ASSETPERIODENDEDDUETOSTOPOUT("AssetPeriodEndedDueToStopOut"),
	ASSETBROKERCHANGED("AssetBrokerChanged"),
	ASSETENTERINVESTMENT("AssetEnterInvestment"),
	ASSETTRADEOPEN("AssetTradeOpen"),
	ASSETTRADECLOSED("AssetTradeClosed"),
	ASSETSUBSCRIPTIONEDIT("AssetSubscriptionEdit"),
	ASSETINVESTBYINVESTOR("AssetInvestByInvestor"),
	ASSETWITHDRAWALBYINVESTOR("AssetWithdrawalByInvestor"),
	ASSETREALLOCATION("AssetReallocation"),
	ASSETSTARTED("AssetStarted"),
	ASSETPERIODPROCESSED("AssetPeriodProcessed"),
	ASSETINVESTBYMANAGER("AssetInvestByManager"),
	ASSETWITHDRAWALBYMANAGER("AssetWithdrawalByManager"),
	ASSETSUBSCRIBEBYINVESTOR("AssetSubscribeByInvestor"),
	ASSETUNSUBSCRIBEBYINVESTOR("AssetUnsubscribeByInvestor");

	public static InvestmentEventType fromValue(String text) {
		for (InvestmentEventType b : InvestmentEventType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	InvestmentEventType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<InvestmentEventType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final InvestmentEventType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public InvestmentEventType read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return InvestmentEventType.fromValue(String.valueOf(value));
		}
	}
}
