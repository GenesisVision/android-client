package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AmountWithLogoCurrency;
import io.swagger.client.model.CoinsAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

public class BuySellCoinsModel implements Parcelable
{
	public static final Creator<BuySellCoinsModel> CREATOR = new Creator<BuySellCoinsModel>()
	{
		@Override
		public BuySellCoinsModel createFromParcel(Parcel in) {
			return new BuySellCoinsModel(in);
		}

		@Override
		public BuySellCoinsModel[] newArray(int size) {
			return new BuySellCoinsModel[size];
		}
	};

	public static BuySellCoinsModel createFrom(CoinsAsset coin) {
		return new BuySellCoinsModel(coin.getOefAssetId() != null ? coin.getOefAssetId() : coin.getId(), coin.getLogoUrl(), coin.getName(),
				coin.getAsset(), coin.getAmount());
	}

	private UUID id;

	private String logo;

	private String title;

	private String currency;

	private Double available;

	private Direction direction;

	private List<AmountWithLogoCurrency> balances = new ArrayList<>();

	private BuySellCoinsModel(Parcel in) {
		id = (UUID) in.readSerializable();
		logo = in.readString();
		title = in.readString();
		currency = in.readString();
		available = in.readDouble();
		direction = (Direction) in.readSerializable();
		in.readTypedList(balances, AmountWithLogoCurrency.CREATOR);
	}

	private BuySellCoinsModel(UUID id, String logo, String title, String currency, Double available) {
		this.id = id;
		this.logo = logo;
		this.title = title;
		this.currency = currency;
		this.available = available != null ? available : 0;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setTransferDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(id);
		parcel.writeString(logo);
		parcel.writeString(title);
		parcel.writeString(currency);
		parcel.writeDouble(available);
		parcel.writeSerializable(direction);
		parcel.writeTypedList(balances);
	}

	public List<AmountWithLogoCurrency> getBalances() {
		return balances;
	}

	public void setBalances(List<AmountWithLogoCurrency> balances) {
		this.balances = balances;
	}

	public enum Direction
	{
		BUY(),
		SELL()
	}
}
