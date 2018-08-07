package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.ProgramDetailsFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/04/2018.
 */
public class ProgramInfoModel implements Parcelable
{
	public static final Creator<ProgramInfoModel> CREATOR = new Creator<ProgramInfoModel>()
	{
		@Override
		public ProgramInfoModel createFromParcel(Parcel in) {
			return new ProgramInfoModel(in);
		}

		@Override
		public ProgramInfoModel[] newArray(int size) {
			return new ProgramInfoModel[size];
		}
	};

	private UUID programId;

	private String avatar;

	private String programName;

	private String managerName;

	private boolean favorite;

	public ProgramInfoModel(UUID programId, String avatar, String programName, String managerName, boolean isFavorite) {
		this.programId = programId;
		this.avatar = avatar;
		this.programName = programName;
		this.managerName = managerName;
		this.favorite = isFavorite;
	}

	protected ProgramInfoModel(Parcel in) {
		programId = (UUID) in.readSerializable();
		avatar = in.readString();
		programName = in.readString();
		managerName = in.readString();
		favorite = in.readByte() != 0;
	}

	public UUID getProgramId() {
		return programId;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getProgramName() {
		return programName;
	}

	public String getManagerName() {
		return managerName;
	}

	public boolean isFavorite() {
		return favorite;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(programId);
		dest.writeString(avatar);
		dest.writeString(programName);
		dest.writeString(managerName);
		dest.writeByte((byte) (favorite ? 1 : 0));
	}

	public void update(ProgramDetailsFull programDetails) {
		this.programId = programDetails.getId();
		this.avatar = programDetails.getAvatar();
		this.programName = programDetails.getTitle();
		this.managerName = programDetails.getManager().getUsername();
//		this.favorite = programDetails.isIsFavorite();
	}
}
