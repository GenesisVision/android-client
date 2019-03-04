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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

/**
 * WalletTransaction
 */

public class WalletTransaction
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("amount")
	private Double amount = null;

	@SerializedName("amountConverted")
	private Double amountConverted = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("number")
	private Long number = null;

	@SerializedName("sourceId")
	private UUID sourceId = null;

	@SerializedName("sourceType")
	private SourceTypeEnum sourceType = null;

	@SerializedName("sourceCurrency")
	private SourceCurrencyEnum sourceCurrency = null;

	@SerializedName("sourceProgramInfo")
	private ProgramInfo sourceProgramInfo = null;

	@SerializedName("sourceBlockchainInfo")
	private BlockchainInfo sourceBlockchainInfo = null;

	@SerializedName("sourceWithdrawalInfo")
	private WithdrawalInfo sourceWithdrawalInfo = null;

	@SerializedName("action")
	private ActionEnum action = null;

	@SerializedName("information")
	private String information = null;

	@SerializedName("destinationId")
	private UUID destinationId = null;

	@SerializedName("destinationType")
	private DestinationTypeEnum destinationType = null;

	@SerializedName("destinationCurrency")
	private DestinationCurrencyEnum destinationCurrency = null;

	@SerializedName("destinationProgramInfo")
	private ProgramInfo destinationProgramInfo = null;

	@SerializedName("destinationBlockchainInfo")
	private BlockchainInfo destinationBlockchainInfo = null;

	@SerializedName("destinationWithdrawalInfo")
	private WithdrawalInfo destinationWithdrawalInfo = null;

	public WalletTransaction id(UUID id) {
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

	public WalletTransaction amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 **/
	@ApiModelProperty(value = "")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public WalletTransaction amountConverted(Double amountConverted) {
		this.amountConverted = amountConverted;
		return this;
	}

	/**
	 * Get amountConverted
	 *
	 * @return amountConverted
	 **/
	@ApiModelProperty(value = "")
	public Double getAmountConverted() {
		return amountConverted;
	}

	public void setAmountConverted(Double amountConverted) {
		this.amountConverted = amountConverted;
	}

	public WalletTransaction date(DateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 **/
	@ApiModelProperty(value = "")
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public WalletTransaction number(Long number) {
		this.number = number;
		return this;
	}

	/**
	 * Get number
	 *
	 * @return number
	 **/
	@ApiModelProperty(value = "")
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public WalletTransaction sourceId(UUID sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	/**
	 * Get sourceId
	 *
	 * @return sourceId
	 **/
	@ApiModelProperty(value = "")
	public UUID getSourceId() {
		return sourceId;
	}

	public void setSourceId(UUID sourceId) {
		this.sourceId = sourceId;
	}

	public WalletTransaction sourceType(SourceTypeEnum sourceType) {
		this.sourceType = sourceType;
		return this;
	}

	/**
	 * Get sourceType
	 *
	 * @return sourceType
	 **/
	@ApiModelProperty(value = "")
	public SourceTypeEnum getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceTypeEnum sourceType) {
		this.sourceType = sourceType;
	}

	public WalletTransaction sourceCurrency(SourceCurrencyEnum sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
		return this;
	}

	/**
	 * Get sourceCurrency
	 *
	 * @return sourceCurrency
	 **/
	@ApiModelProperty(value = "")
	public SourceCurrencyEnum getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(SourceCurrencyEnum sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public WalletTransaction sourceProgramInfo(ProgramInfo sourceProgramInfo) {
		this.sourceProgramInfo = sourceProgramInfo;
		return this;
	}

	/**
	 * Get sourceProgramInfo
	 *
	 * @return sourceProgramInfo
	 **/
	@ApiModelProperty(value = "")
	public ProgramInfo getSourceProgramInfo() {
		return sourceProgramInfo;
	}

	public void setSourceProgramInfo(ProgramInfo sourceProgramInfo) {
		this.sourceProgramInfo = sourceProgramInfo;
	}

	public WalletTransaction sourceBlockchainInfo(BlockchainInfo sourceBlockchainInfo) {
		this.sourceBlockchainInfo = sourceBlockchainInfo;
		return this;
	}

	/**
	 * Get sourceBlockchainInfo
	 *
	 * @return sourceBlockchainInfo
	 **/
	@ApiModelProperty(value = "")
	public BlockchainInfo getSourceBlockchainInfo() {
		return sourceBlockchainInfo;
	}

	public void setSourceBlockchainInfo(BlockchainInfo sourceBlockchainInfo) {
		this.sourceBlockchainInfo = sourceBlockchainInfo;
	}

	public WalletTransaction sourceWithdrawalInfo(WithdrawalInfo sourceWithdrawalInfo) {
		this.sourceWithdrawalInfo = sourceWithdrawalInfo;
		return this;
	}

	/**
	 * Get sourceWithdrawalInfo
	 *
	 * @return sourceWithdrawalInfo
	 **/
	@ApiModelProperty(value = "")
	public WithdrawalInfo getSourceWithdrawalInfo() {
		return sourceWithdrawalInfo;
	}

	public void setSourceWithdrawalInfo(WithdrawalInfo sourceWithdrawalInfo) {
		this.sourceWithdrawalInfo = sourceWithdrawalInfo;
	}

	public WalletTransaction action(ActionEnum action) {
		this.action = action;
		return this;
	}

	/**
	 * Get action
	 *
	 * @return action
	 **/
	@ApiModelProperty(value = "")
	public ActionEnum getAction() {
		return action;
	}

	public void setAction(ActionEnum action) {
		this.action = action;
	}

	public WalletTransaction information(String information) {
		this.information = information;
		return this;
	}

	/**
	 * Get information
	 *
	 * @return information
	 **/
	@ApiModelProperty(value = "")
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public WalletTransaction destinationId(UUID destinationId) {
		this.destinationId = destinationId;
		return this;
	}

	/**
	 * Get destinationId
	 *
	 * @return destinationId
	 **/
	@ApiModelProperty(value = "")
	public UUID getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(UUID destinationId) {
		this.destinationId = destinationId;
	}

	public WalletTransaction destinationType(DestinationTypeEnum destinationType) {
		this.destinationType = destinationType;
		return this;
	}

	/**
	 * Get destinationType
	 *
	 * @return destinationType
	 **/
	@ApiModelProperty(value = "")
	public DestinationTypeEnum getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(DestinationTypeEnum destinationType) {
		this.destinationType = destinationType;
	}

	public WalletTransaction destinationCurrency(DestinationCurrencyEnum destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
		return this;
	}

	/**
	 * Get destinationCurrency
	 *
	 * @return destinationCurrency
	 **/
	@ApiModelProperty(value = "")
	public DestinationCurrencyEnum getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(DestinationCurrencyEnum destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public WalletTransaction destinationProgramInfo(ProgramInfo destinationProgramInfo) {
		this.destinationProgramInfo = destinationProgramInfo;
		return this;
	}

	/**
	 * Get destinationProgramInfo
	 *
	 * @return destinationProgramInfo
	 **/
	@ApiModelProperty(value = "")
	public ProgramInfo getDestinationProgramInfo() {
		return destinationProgramInfo;
	}

	public void setDestinationProgramInfo(ProgramInfo destinationProgramInfo) {
		this.destinationProgramInfo = destinationProgramInfo;
	}

	public WalletTransaction destinationBlockchainInfo(BlockchainInfo destinationBlockchainInfo) {
		this.destinationBlockchainInfo = destinationBlockchainInfo;
		return this;
	}

	/**
	 * Get destinationBlockchainInfo
	 *
	 * @return destinationBlockchainInfo
	 **/
	@ApiModelProperty(value = "")
	public BlockchainInfo getDestinationBlockchainInfo() {
		return destinationBlockchainInfo;
	}

	public void setDestinationBlockchainInfo(BlockchainInfo destinationBlockchainInfo) {
		this.destinationBlockchainInfo = destinationBlockchainInfo;
	}

	public WalletTransaction destinationWithdrawalInfo(WithdrawalInfo destinationWithdrawalInfo) {
		this.destinationWithdrawalInfo = destinationWithdrawalInfo;
		return this;
	}

	/**
	 * Get destinationWithdrawalInfo
	 *
	 * @return destinationWithdrawalInfo
	 **/
	@ApiModelProperty(value = "")
	public WithdrawalInfo getDestinationWithdrawalInfo() {
		return destinationWithdrawalInfo;
	}

	public void setDestinationWithdrawalInfo(WithdrawalInfo destinationWithdrawalInfo) {
		this.destinationWithdrawalInfo = destinationWithdrawalInfo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WalletTransaction walletTransaction = (WalletTransaction) o;
		return Objects.equals(this.id, walletTransaction.id) &&
				Objects.equals(this.amount, walletTransaction.amount) &&
				Objects.equals(this.amountConverted, walletTransaction.amountConverted) &&
				Objects.equals(this.date, walletTransaction.date) &&
				Objects.equals(this.number, walletTransaction.number) &&
				Objects.equals(this.sourceId, walletTransaction.sourceId) &&
				Objects.equals(this.sourceType, walletTransaction.sourceType) &&
				Objects.equals(this.sourceCurrency, walletTransaction.sourceCurrency) &&
				Objects.equals(this.sourceProgramInfo, walletTransaction.sourceProgramInfo) &&
				Objects.equals(this.sourceBlockchainInfo, walletTransaction.sourceBlockchainInfo) &&
				Objects.equals(this.sourceWithdrawalInfo, walletTransaction.sourceWithdrawalInfo) &&
				Objects.equals(this.action, walletTransaction.action) &&
				Objects.equals(this.information, walletTransaction.information) &&
				Objects.equals(this.destinationId, walletTransaction.destinationId) &&
				Objects.equals(this.destinationType, walletTransaction.destinationType) &&
				Objects.equals(this.destinationCurrency, walletTransaction.destinationCurrency) &&
				Objects.equals(this.destinationProgramInfo, walletTransaction.destinationProgramInfo) &&
				Objects.equals(this.destinationBlockchainInfo, walletTransaction.destinationBlockchainInfo) &&
				Objects.equals(this.destinationWithdrawalInfo, walletTransaction.destinationWithdrawalInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount, amountConverted, date, number, sourceId, sourceType, sourceCurrency, sourceProgramInfo, sourceBlockchainInfo, sourceWithdrawalInfo, action, information, destinationId, destinationType, destinationCurrency, destinationProgramInfo, destinationBlockchainInfo, destinationWithdrawalInfo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WalletTransaction {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    amountConverted: ").append(toIndentedString(amountConverted)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    number: ").append(toIndentedString(number)).append("\n");
		sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
		sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
		sb.append("    sourceCurrency: ").append(toIndentedString(sourceCurrency)).append("\n");
		sb.append("    sourceProgramInfo: ").append(toIndentedString(sourceProgramInfo)).append("\n");
		sb.append("    sourceBlockchainInfo: ").append(toIndentedString(sourceBlockchainInfo)).append("\n");
		sb.append("    sourceWithdrawalInfo: ").append(toIndentedString(sourceWithdrawalInfo)).append("\n");
		sb.append("    action: ").append(toIndentedString(action)).append("\n");
		sb.append("    information: ").append(toIndentedString(information)).append("\n");
		sb.append("    destinationId: ").append(toIndentedString(destinationId)).append("\n");
		sb.append("    destinationType: ").append(toIndentedString(destinationType)).append("\n");
		sb.append("    destinationCurrency: ").append(toIndentedString(destinationCurrency)).append("\n");
		sb.append("    destinationProgramInfo: ").append(toIndentedString(destinationProgramInfo)).append("\n");
		sb.append("    destinationBlockchainInfo: ").append(toIndentedString(destinationBlockchainInfo)).append("\n");
		sb.append("    destinationWithdrawalInfo: ").append(toIndentedString(destinationWithdrawalInfo)).append("\n");
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

	/**
	 * Gets or Sets sourceType
	 */
	@JsonAdapter(SourceTypeEnum.Adapter.class)
	public enum SourceTypeEnum
	{
		WALLET("Wallet"),

		PROGRAM("Program"),

		FUND("Fund"),

		PROGRAMREQUEST("ProgramRequest"),

		FUNDREQUEST("FundRequest"),

		WITHDRAWALREQUEST("WithdrawalRequest"),

		PAYMENTTRANSACTION("PaymentTransaction"),

		BLOCKCHAIN("Blockchain"),

		GENESISVISIONPLATFORM("GenesisVisionPlatform");

		public static SourceTypeEnum fromValue(String text) {
			for (SourceTypeEnum b : SourceTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		SourceTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<SourceTypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final SourceTypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public SourceTypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return SourceTypeEnum.fromValue(String.valueOf(value));
			}
		}
	}


	/**
	 * Gets or Sets sourceCurrency
	 */
	@JsonAdapter(SourceCurrencyEnum.Adapter.class)
	public enum SourceCurrencyEnum
	{
		BTC("BTC"),

		ETH("ETH"),

		USDT("USDT"),

		GVT("GVT"),

		UNDEFINED("Undefined"),

		ADA("ADA"),

		XRP("XRP"),

		BCH("BCH"),

		LTC("LTC"),

		DOGE("DOGE"),

		BNB("BNB"),

		USD("USD"),

		EUR("EUR");

		public static SourceCurrencyEnum fromValue(String text) {
			for (SourceCurrencyEnum b : SourceCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		SourceCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<SourceCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final SourceCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public SourceCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return SourceCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets action
	 */
	@JsonAdapter(ActionEnum.Adapter.class)
	public enum ActionEnum
	{
		TRANSFER("Transfer"),

		PROGRAMOPEN("ProgramOpen"),

		PROGRAMPROFIT("ProgramProfit"),

		PROGRAMINVEST("ProgramInvest"),

		PROGRAMWITHDRAWAL("ProgramWithdrawal"),

		PROGRAMREFUNDPARTIALEXECUTION("ProgramRefundPartialExecution"),

		PROGRAMREFUNDCLOSE("ProgramRefundClose"),

		PROGRAMREQUESTINVEST("ProgramRequestInvest"),

		PROGRAMREQUESTWITHDRAWAL("ProgramRequestWithdrawal"),

		PROGRAMREQUESTCANCEL("ProgramRequestCancel"),

		PAYINGFEE("PayingFee");

		public static ActionEnum fromValue(String text) {
			for (ActionEnum b : ActionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		ActionEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<ActionEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final ActionEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public ActionEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return ActionEnum.fromValue(String.valueOf(value));
			}
		}
	}


	/**
	 * Gets or Sets destinationType
	 */
	@JsonAdapter(DestinationTypeEnum.Adapter.class)
	public enum DestinationTypeEnum
	{
		WALLET("Wallet"),

		PROGRAM("Program"),

		FUND("Fund"),

		PROGRAMREQUEST("ProgramRequest"),

		FUNDREQUEST("FundRequest"),

		WITHDRAWALREQUEST("WithdrawalRequest"),

		PAYMENTTRANSACTION("PaymentTransaction"),

		BLOCKCHAIN("Blockchain"),

		GENESISVISIONPLATFORM("GenesisVisionPlatform");

		public static DestinationTypeEnum fromValue(String text) {
			for (DestinationTypeEnum b : DestinationTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		DestinationTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<DestinationTypeEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final DestinationTypeEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public DestinationTypeEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return DestinationTypeEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets destinationCurrency
	 */
	@JsonAdapter(DestinationCurrencyEnum.Adapter.class)
	public enum DestinationCurrencyEnum
	{
		BTC("BTC"),

		ETH("ETH"),

		USDT("USDT"),

		GVT("GVT"),

		UNDEFINED("Undefined"),

		ADA("ADA"),

		XRP("XRP"),

		BCH("BCH"),

		LTC("LTC"),

		DOGE("DOGE"),

		BNB("BNB"),

		USD("USD"),

		EUR("EUR");

		public static DestinationCurrencyEnum fromValue(String text) {
			for (DestinationCurrencyEnum b : DestinationCurrencyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		DestinationCurrencyEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<DestinationCurrencyEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final DestinationCurrencyEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public DestinationCurrencyEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return DestinationCurrencyEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

