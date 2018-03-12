package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 2/22/18.
 */

public class ProgramRequest implements Parcelable
{
	public static final Creator<ProgramRequest> CREATOR = new Creator<ProgramRequest>()
	{
		@Override
		public ProgramRequest createFromParcel(Parcel in) {
			return new ProgramRequest(in);
		}

		@Override
		public ProgramRequest[] newArray(int size) {
			return new ProgramRequest[size];
		}
	};

	@SerializedName("program_id")
	public UUID programId;

	@SerializedName("program_name")
	public String programName;

	@SerializedName("available")
	public double available;

	@SerializedName("amount")
	public double amount;

	public ProgramRequest() {

	}

	protected ProgramRequest(Parcel in) {
		programId = (UUID) in.readValue(UUID.class.getClassLoader());
		programName = in.readString();
		available = in.readDouble();
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
		dest.writeDouble(available);
		dest.writeDouble(amount);
	}
}
