package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class PostImageModel implements AsymmetricItem
{
	public static final Parcelable.Creator<PostImageModel> CREATOR = new Parcelable.Creator<PostImageModel>()
	{
		@Override
		public PostImageModel createFromParcel(@NonNull Parcel in) {
			return new PostImageModel(in);
		}

		@Override
		@NonNull
		public PostImageModel[] newArray(int size) {
			return new PostImageModel[size];
		}
	};

	private String imageUrl;

	private int position;

	private int count;

	private int columnSpan;

	private int rowSpan;

	public PostImageModel(Parcel in) {
		readFromParcel(in);
	}

	public PostImageModel(String imageUrl, int position, int count, int columnSpan, int rowSpan) {
		this.imageUrl = imageUrl;
		this.position = position;
		this.count = count;
		this.columnSpan = columnSpan;
		this.rowSpan = rowSpan;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getCount() {
		return count;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public int getColumnSpan() {
		return columnSpan;
	}

	@Override
	public int getRowSpan() {
		return rowSpan;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	private void readFromParcel(Parcel in) {
		imageUrl = in.readString();
		position = in.readInt();
		count = in.readInt();
		columnSpan = in.readInt();
		rowSpan = in.readInt();
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(imageUrl);
		dest.writeInt(position);
		dest.writeInt(count);
		dest.writeInt(columnSpan);
		dest.writeInt(rowSpan);
	}
}
