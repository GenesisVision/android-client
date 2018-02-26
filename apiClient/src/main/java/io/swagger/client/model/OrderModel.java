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
 * OrderModel
 */

public class OrderModel
{
	@SerializedName("id")
	private UUID id = null;

	@SerializedName("login")
	private Long login = null;

	@SerializedName("ticket")
	private Long ticket = null;

	@SerializedName("symbol")
	private String symbol = null;

	@SerializedName("volume")
	private Double volume = null;

	@SerializedName("profit")
	private Double profit = null;

	@SerializedName("direction")
	private DirectionEnum direction = null;

	@SerializedName("dateOpen")
	private DateTime dateOpen = null;

	@SerializedName("dateClose")
	private DateTime dateClose = null;

	@SerializedName("priceOpen")
	private Double priceOpen = null;

	@SerializedName("priceClose")
	private Double priceClose = null;

	@SerializedName("date")
	private DateTime date = null;

	@SerializedName("price")
	private Double price = null;

	@SerializedName("entry")
	private EntryEnum entry = null;

	public OrderModel id(UUID id) {
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

	public OrderModel login(Long login) {
		this.login = login;
		return this;
	}

	/**
	 * Get login
	 *
	 * @return login
	 **/
	@ApiModelProperty(value = "")
	public Long getLogin() {
		return login;
	}

	public void setLogin(Long login) {
		this.login = login;
	}

	public OrderModel ticket(Long ticket) {
		this.ticket = ticket;
		return this;
	}

	/**
	 * Get ticket
	 *
	 * @return ticket
	 **/
	@ApiModelProperty(value = "")
	public Long getTicket() {
		return ticket;
	}

	public void setTicket(Long ticket) {
		this.ticket = ticket;
	}

	public OrderModel symbol(String symbol) {
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

	public OrderModel volume(Double volume) {
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

	public OrderModel profit(Double profit) {
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

	public OrderModel direction(DirectionEnum direction) {
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

	public OrderModel dateOpen(DateTime dateOpen) {
		this.dateOpen = dateOpen;
		return this;
	}

	/**
	 * Get dateOpen
	 *
	 * @return dateOpen
	 **/
	@ApiModelProperty(value = "")
	public DateTime getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(DateTime dateOpen) {
		this.dateOpen = dateOpen;
	}

	public OrderModel dateClose(DateTime dateClose) {
		this.dateClose = dateClose;
		return this;
	}

	/**
	 * Get dateClose
	 *
	 * @return dateClose
	 **/
	@ApiModelProperty(value = "")
	public DateTime getDateClose() {
		return dateClose;
	}

	public void setDateClose(DateTime dateClose) {
		this.dateClose = dateClose;
	}

	public OrderModel priceOpen(Double priceOpen) {
		this.priceOpen = priceOpen;
		return this;
	}

	/**
	 * Get priceOpen
	 *
	 * @return priceOpen
	 **/
	@ApiModelProperty(value = "")
	public Double getPriceOpen() {
		return priceOpen;
	}

	public void setPriceOpen(Double priceOpen) {
		this.priceOpen = priceOpen;
	}

	public OrderModel priceClose(Double priceClose) {
		this.priceClose = priceClose;
		return this;
	}

	/**
	 * Get priceClose
	 *
	 * @return priceClose
	 **/
	@ApiModelProperty(value = "")
	public Double getPriceClose() {
		return priceClose;
	}

	public void setPriceClose(Double priceClose) {
		this.priceClose = priceClose;
	}

	public OrderModel date(DateTime date) {
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

	public OrderModel price(Double price) {
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

	public OrderModel entry(EntryEnum entry) {
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
		OrderModel orderModel = (OrderModel) o;
		return Objects.equals(this.id, orderModel.id) &&
				Objects.equals(this.login, orderModel.login) &&
				Objects.equals(this.ticket, orderModel.ticket) &&
				Objects.equals(this.symbol, orderModel.symbol) &&
				Objects.equals(this.volume, orderModel.volume) &&
				Objects.equals(this.profit, orderModel.profit) &&
				Objects.equals(this.direction, orderModel.direction) &&
				Objects.equals(this.dateOpen, orderModel.dateOpen) &&
				Objects.equals(this.dateClose, orderModel.dateClose) &&
				Objects.equals(this.priceOpen, orderModel.priceOpen) &&
				Objects.equals(this.priceClose, orderModel.priceClose) &&
				Objects.equals(this.date, orderModel.date) &&
				Objects.equals(this.price, orderModel.price) &&
				Objects.equals(this.entry, orderModel.entry);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, ticket, symbol, volume, profit, direction, dateOpen, dateClose, priceOpen, priceClose, date, price, entry);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class OrderModel {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    login: ").append(toIndentedString(login)).append("\n");
		sb.append("    ticket: ").append(toIndentedString(ticket)).append("\n");
		sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
		sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
		sb.append("    profit: ").append(toIndentedString(profit)).append("\n");
		sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
		sb.append("    dateOpen: ").append(toIndentedString(dateOpen)).append("\n");
		sb.append("    dateClose: ").append(toIndentedString(dateClose)).append("\n");
		sb.append("    priceOpen: ").append(toIndentedString(priceOpen)).append("\n");
		sb.append("    priceClose: ").append(toIndentedString(priceClose)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

