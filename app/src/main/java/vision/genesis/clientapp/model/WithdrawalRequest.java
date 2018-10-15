package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/10/2018.
 */
public class WithdrawalRequest implements Parcelable
{
	public static final Creator<WithdrawalRequest> CREATOR = new Creator<WithdrawalRequest>()
	{
		@Override
		public WithdrawalRequest createFromParcel(Parcel in) {
			return new WithdrawalRequest(in);
		}

		@Override
		public WithdrawalRequest[] newArray(int size) {
			return new WithdrawalRequest[size];
		}
	};

	private Double amount;

	private String currency;

	private String address;

	private String amountText;

	private String estimatedAmountText;

	private String feeAmountText;

	public WithdrawalRequest() {

	}

	protected WithdrawalRequest(Parcel in) {
		if (in.readByte() == 0) {
			amount = null;
		}
		else {
			amount = in.readDouble();
		}
		address = in.readString();
		currency = in.readString();
		amountText = in.readString();
		estimatedAmountText = in.readString();
		feeAmountText = in.readString();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAmountText() {
		return amountText;
	}

	public void setAmountText(String amountText) {
		this.amountText = amountText;
	}

	public String getEstimatedAmountText() {
		return estimatedAmountText;
	}

	public void setEstimatedAmountText(String estimatedAmountText) {
		this.estimatedAmountText = estimatedAmountText;
	}

	public String getFeeAmountText() {
		return feeAmountText;
	}

	public void setFeeAmountText(String feeAmountText) {
		this.feeAmountText = feeAmountText;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (amount == null) {
			dest.writeByte((byte) 0);
		}
		else {
			dest.writeByte((byte) 1);
			dest.writeDouble(amount);
		}
		dest.writeString(currency);
		dest.writeString(address);
		dest.writeString(amountText);
		dest.writeString(estimatedAmountText);
		dest.writeString(feeAmountText);
	}
}
