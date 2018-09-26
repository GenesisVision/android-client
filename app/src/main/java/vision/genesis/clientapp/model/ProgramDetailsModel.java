package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.ProgramDetailsFull;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/04/2018.
 */
public class ProgramDetailsModel implements Parcelable
{
	public static final Creator<ProgramDetailsModel> CREATOR = new Creator<ProgramDetailsModel>()
	{
		@Override
		public ProgramDetailsModel createFromParcel(Parcel in) {
			return new ProgramDetailsModel(in);
		}

		@Override
		public ProgramDetailsModel[] newArray(int size) {
			return new ProgramDetailsModel[size];
		}
	};

	private UUID programId;

	private String avatar;

	private Integer level;

	private String programName;

	private String managerName;

	private boolean favorite;

	public ProgramDetailsModel(UUID programId, String avatar, Integer level, String programName, String managerName, boolean isFavorite) {
		this.programId = programId;
		this.avatar = avatar;
		this.level = level;
		this.programName = programName;
		this.managerName = managerName;
		this.favorite = isFavorite;
	}

	protected ProgramDetailsModel(Parcel in) {
		programId = (UUID) in.readSerializable();
		avatar = in.readString();
		level = in.readInt();
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

	public Integer getLevel() {
		return level;
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
		dest.writeInt(level);
		dest.writeString(programName);
		dest.writeString(managerName);
		dest.writeByte((byte) (favorite ? 1 : 0));
	}

	public void update(ProgramDetailsFull programDetails) {
		this.programId = programDetails.getId();
		this.avatar = programDetails.getLogo();
		this.level = programDetails.getLevel();
		this.programName = programDetails.getTitle();
		this.managerName = programDetails.getManager().getUsername();
//		this.favorite = programDetails.isIsFavorite();
	}
}
