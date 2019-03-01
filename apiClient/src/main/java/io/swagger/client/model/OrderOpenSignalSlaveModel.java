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
 * OrderOpenSignalSlaveModel
 */

public class OrderOpenSignalSlaveModel
{
	@SerializedName("manager")
	private ProfilePublic manager = null;

	@SerializedName("program")
	private OrderProgramData program = null;

	@SerializedName("programId")
	private UUID programId = null;

	@SerializedName("id")
	private UUID id = null;

	@SerializedName("login")
	private String login = null;

	@SerializedName("ticket")
	private String ticket = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("volume")
	private Double volume = null;

	@SerializedName("profit")
	private Double profit = null;

	@SerializedName("direction")
	private DirectionEnum direction = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("priceCurrent")
	private Double priceCurrent = null;

	@SerializedName("entry")
	private EntryEnum entry = null;

	public OrderOpenSignalSlaveModel manager(ProfilePublic manager) {
		this.manager = manager;
		return this;
	}

	/**
	 * Get manager
	 *
	 * @return manager
	 **/
	@ApiModelProperty(value = "")
	public ProfilePublic getManager() {
		return manager;
	}

	public void setManager(ProfilePublic manager) {
		this.manager = manager;
	}

	public OrderOpenSignalSlaveModel program(OrderProgramData program) {
		this.program = program;
		return this;
	}

	/**
	 * Get program
	 *
	 * @return program
	 **/
	@ApiModelProperty(value = "")
	public OrderProgramData getProgram() {
		return program;
	}

	public void setProgram(OrderProgramData program) {
		this.program = program;
	}

	public OrderOpenSignalSlaveModel programId(UUID programId) {
		this.programId = programId;
		return this;
	}

	/**
	 * Get programId
	 *
	 * @return programId
	 **/
	@ApiModelProperty(value = "")
	public UUID getProgramId() {
		return programId;
	}

	public void setProgramId(UUID programId) {
		this.programId = programId;
	}

	public OrderOpenSignalSlaveModel id(UUID id) {
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

	public OrderOpenSignalSlaveModel login(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Get login
	 *
	 * @return login
	 **/
	@ApiModelProperty(value = "")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public OrderOpenSignalSlaveModel ticket(String ticket) {
		this.ticket = ticket;
		return this;
	}

	/**
	 * Get ticket
	 *
	 * @return ticket
	 **/
	@ApiModelProperty(value = "")
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public OrderOpenSignalSlaveModel symbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	/**
	 * Get symbol
	 *
	 * @return symbol
	 **/
	@ApiModelProperty(value = "")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public OrderOpenSignalSlaveModel volume(Double volume) {
		this.volume = volume;
		return this;
	}

	/**
	 * Get volume
	 *
	 * @return volume
	 **/
	@ApiModelProperty(value = "")
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public OrderOpenSignalSlaveModel profit(Double profit) {
		this.profit = profit;
		return this;
	}

	/**
	 * Get profit
	 *
	 * @return profit
	 **/
	@ApiModelProperty(value = "")
	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public OrderOpenSignalSlaveModel direction(DirectionEnum direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * Get direction
	 *
	 * @return direction
	 **/
	@ApiModelProperty(value = "")
	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	public OrderOpenSignalSlaveModel date(DateTime date) {
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

	public OrderOpenSignalSlaveModel price(Double price) {
		this.price = price;
		return this;
	}

	/**
	 * Get price
	 *
	 * @return price
	 **/
	@ApiModelProperty(value = "")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OrderOpenSignalSlaveModel priceCurrent(Double priceCurrent) {
		this.priceCurrent = priceCurrent;
		return this;
	}

	/**
	 * Get priceCurrent
	 *
	 * @return priceCurrent
	 **/
	@ApiModelProperty(value = "")
	public Double getPriceCurrent() {
		return priceCurrent;
	}

	public void setPriceCurrent(Double priceCurrent) {
		this.priceCurrent = priceCurrent;
	}

	public OrderOpenSignalSlaveModel entry(EntryEnum entry) {
		this.entry = entry;
		return this;
	}

	/**
	 * Get entry
	 *
	 * @return entry
	 **/
	@ApiModelProperty(value = "")
	public EntryEnum getEntry() {
		return entry;
	}

	public void setEntry(EntryEnum entry) {
		this.entry = entry;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderOpenSignalSlaveModel orderOpenSignalSlaveModel = (OrderOpenSignalSlaveModel) o;
		return Objects.equals(this.manager, orderOpenSignalSlaveModel.manager) &&
				Objects.equals(this.program, orderOpenSignalSlaveModel.program) &&
				Objects.equals(this.programId, orderOpenSignalSlaveModel.programId) &&
				Objects.equals(this.id, orderOpenSignalSlaveModel.id) &&
				Objects.equals(this.login, orderOpenSignalSlaveModel.login) &&
				Objects.equals(this.ticket, orderOpenSignalSlaveModel.ticket) &&
				Objects.equals(this.symbol, orderOpenSignalSlaveModel.symbol) &&
				Objects.equals(this.volume, orderOpenSignalSlaveModel.volume) &&
				Objects.equals(this.profit, orderOpenSignalSlaveModel.profit) &&
				Objects.equals(this.direction, orderOpenSignalSlaveModel.direction) &&
				Objects.equals(this.date, orderOpenSignalSlaveModel.date) &&
				Objects.equals(this.price, orderOpenSignalSlaveModel.price) &&
				Objects.equals(this.priceCurrent, orderOpenSignalSlaveModel.priceCurrent) &&
				Objects.equals(this.entry, orderOpenSignalSlaveModel.entry);
	}

	@Override
	public int hashCode() {
		return Objects.hash(manager, program, programId, id, login, ticket, symbol, volume, profit, direction, date, price, priceCurrent, entry);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OrderOpenSignalSlaveModel {\n");

		sb.append("    manager: ").append(toIndentedString(manager)).append("\n");
		sb.append("    program: ").append(toIndentedString(program)).append("\n");
		sb.append("    programId: ").append(toIndentedString(programId)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    ticket: ").append(toIndentedString(ticket)).append("\n");
		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
		sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
		sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    priceCurrent: ").append(toIndentedString(priceCurrent)).append("\n");
		sb.append("    entry: ").append(toIndentedString(entry)).append("\n");
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
	 * Gets or Sets direction
	 */
	@JsonAdapter(DirectionEnum.Adapter.class)
	public enum DirectionEnum
	{
		BUY("Buy"),

		SELL("Sell"),

		BALANCE("Balance"),

		CREDIT("Credit"),

		UNDEFINED("Undefined");

		public static DirectionEnum fromValue(String text) {
			for (DirectionEnum b : DirectionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		DirectionEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<DirectionEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final DirectionEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public DirectionEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return DirectionEnum.fromValue(String.valueOf(value));
			}
		}
	}

	/**
	 * Gets or Sets entry
	 */
	@JsonAdapter(EntryEnum.Adapter.class)
	public enum EntryEnum
	{
		IN("In"),

		OUT("Out"),

		INOUT("InOut"),

		OUTBY("OutBy");

		public static EntryEnum fromValue(String text) {
			for (EntryEnum b : EntryEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		private String value;

		EntryEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static class Adapter extends TypeAdapter<EntryEnum>
		{
			@Override
			public void write(final JsonWriter jsonWriter, final EntryEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public EntryEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return EntryEnum.fromValue(String.valueOf(value));
			}
		}
	}

}

