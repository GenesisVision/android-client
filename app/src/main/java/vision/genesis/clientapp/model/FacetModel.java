package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/04/2019.
 */
public class FacetModel implements Parcelable
{
	public static final Creator<FacetModel> CREATOR = new Creator<FacetModel>()
	{
		@Override
		public FacetModel createFromParcel(Parcel in) {
			return new FacetModel(in);
		}

		@Override
		public FacetModel[] newArray(int size) {
			return new FacetModel[size];
		}
	};

	private UUID id;

	private String title;

	private String timeFrame;

	public FacetModel(UUID id, String title, String timeFrame) {
		this.id = id;
		this.title = title;
		this.timeFrame = timeFrame;
	}

	protected FacetModel(Parcel in) {
		id = (UUID) in.readSerializable();
		title = in.readString();
		timeFrame = in.readString();
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeSerializable(id);
		parcel.writeString(title);
		parcel.writeString(timeFrame);
	}
}
