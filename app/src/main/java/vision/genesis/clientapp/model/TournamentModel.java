package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */
public class TournamentModel implements Parcelable
{
	public static final Creator<TournamentModel> CREATOR = new Creator<TournamentModel>()
	{
		@Override
		public TournamentModel createFromParcel(Parcel in) {
			return new TournamentModel(in);
		}

		@Override
		public TournamentModel[] newArray(int size) {
			return new TournamentModel[size];
		}
	};

	private int maxRounds;

	private int currentRound;

	public TournamentModel(int maxRounds, int currentRound) {
		this.maxRounds = maxRounds;
		this.currentRound = currentRound;
	}

	protected TournamentModel(Parcel in) {
		maxRounds = in.readInt();
		currentRound = in.readInt();
	}

	public int getMaxRounds() {
		return maxRounds;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(maxRounds);
		dest.writeInt(currentRound);
	}
}
