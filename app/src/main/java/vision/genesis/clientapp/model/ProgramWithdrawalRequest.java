package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class ProgramWithdrawalRequest implements Parcelable
{
	public static final Creator<ProgramWithdrawalRequest> CREATOR = new Creator<ProgramWithdrawalRequest>()
	{
		@Override
		public ProgramWithdrawalRequest createFromParcel(Parcel in) {
			return new ProgramWithdrawalRequest(in);
		}

		@Override
		public ProgramWithdrawalRequest[] newArray(int size) {
			return new ProgramWithdrawalRequest[size];
		}
	};

	@SerializedName("program_id")
	public UUID programId;

	@SerializedName("program_name")
	public String programName;

	@SerializedName("amount")
	public double amount;

	public ProgramWithdrawalRequest() {

	}

	protected ProgramWithdrawalRequest(Parcel in) {
		programId = (UUID) in.readValue(UUID.class.getClassLoader());
		programName = in.readString();
		amount = in.readDouble();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(programId);
		dest.writeString(programName);
		dest.writeDouble(amount);
	}
}
