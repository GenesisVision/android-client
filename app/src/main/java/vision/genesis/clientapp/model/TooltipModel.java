package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/04/2018.
 */
public class TooltipModel implements Parcelable
{
	public static final Creator<TooltipModel> CREATOR = new Creator<TooltipModel>()
	{
		@Override
		public TooltipModel createFromParcel(Parcel in) {
			return new TooltipModel(in);
		}

		@Override
		public TooltipModel[] newArray(int size) {
			return new TooltipModel[size];
		}
	};

	public final float centerX;

	public final float topY;

	public final float bottomY;

	public final String tooltipText;

	public TooltipModel(float centerX, float topY, float bottomY, String tooltipText) {
		this.centerX = centerX;
		this.topY = topY;
		this.bottomY = bottomY;
		this.tooltipText = tooltipText;
	}

	protected TooltipModel(Parcel in) {
		centerX = in.readFloat();
		topY = in.readFloat();
		bottomY = in.readFloat();
		tooltipText = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeFloat(centerX);
		dest.writeFloat(topY);
		dest.writeFloat(bottomY);
		dest.writeString(tooltipText);
	}
}