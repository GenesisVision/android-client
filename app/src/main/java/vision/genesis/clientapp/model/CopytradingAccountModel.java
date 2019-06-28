package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.CopyTradingAccountInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */
public class CopytradingAccountModel implements Parcelable
{
	public static final Creator<CopytradingAccountModel> CREATOR = new Creator<CopytradingAccountModel>()
	{
		@Override
		public CopytradingAccountModel createFromParcel(Parcel in) {
			return new CopytradingAccountModel(in);
		}

		@Override
		public CopytradingAccountModel[] newArray(int size) {
			return new CopytradingAccountModel[size];
		}
	};

	public static CopytradingAccountModel createFrom(CopyTradingAccountInfo info) {
		return new CopytradingAccountModel(info.getId(), info.getLogo(), info.getTitle(),
				info.getCurrency().getValue(), info.getAvailable(),
				info.getBalance(), info.getEquity(), info.getFreeMargin(), info.getMarginLevel());
	}

	private UUID id;

	private String logo;

	private String title;

	private String currency;

	private Double available;

	private Double balance;

	private Double equity;

	private Double freeMargin;

	private Double marginLevel;

	private CopytradingAccountModel(Parcel in) {
		id = (UUID) in.readSerializable();
		logo = in.readString();
		title = in.readString();
		currency = in.readString();
		available = in.readDouble();
		balance = in.readDouble();
		equity = in.readDouble();
		freeMargin = in.readDouble();
		marginLevel = in.readDouble();
	}

	public CopytradingAccountModel(UUID id, String logo, String title, String currency, Double available, Double balance, Double equity, Double freeMargin, Double marginLevel) {
		this.id = id;
		this.logo = logo;
		this.title = title;
		this.currency = currency;
		this.available = available;
		this.balance = balance;
		this.equity = equity;
		this.freeMargin = freeMargin;
		this.marginLevel = marginLevel;
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getEquity() {
		return equity;
	}

	public void setEquity(Double equity) {
		this.equity = equity;
	}

	public Double getFreeMargin() {
		return freeMargin;
	}

	public void setFreeMargin(Double freeMargin) {
		this.freeMargin = freeMargin;
	}

	public Double getMarginLevel() {
		return marginLevel;
	}

	public void setMarginLevel(Double marginLevel) {
		this.marginLevel = marginLevel;
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
		parcel.writeDouble(balance);
		parcel.writeDouble(equity);
		parcel.writeDouble(freeMargin);
		parcel.writeDouble(marginLevel);
	}
}
