package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.FollowDetailsFull;
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

	private String programColor;

	private Integer level;

	private Double levelProgress;

	private String programName;

	private String managerName;

	private String currency;

	private boolean favorite;

	private boolean hasNotifications;

	private AssetType assetType;

	public ProgramDetailsModel(UUID programId, String avatar, String programColor, Integer level, Double levelProgress,
	                           String programName, String managerName, String currency, boolean isFavorite, boolean hasNotifications,
	                           AssetType assetType) {
		this.programId = programId;
		this.avatar = avatar;
		this.programColor = programColor;
		this.level = level;
		this.levelProgress = levelProgress;
		this.programName = programName;
		this.managerName = managerName;
		this.currency = currency;
		this.favorite = isFavorite;
		this.hasNotifications = hasNotifications;
		this.assetType = assetType;
	}

	protected ProgramDetailsModel(Parcel in) {
		programId = (UUID) in.readSerializable();
		avatar = in.readString();
		programColor = in.readString();
		level = in.readInt();
		levelProgress = in.readDouble();
		programName = in.readString();
		managerName = in.readString();
		currency = in.readString();
		favorite = in.readByte() != 0;
		hasNotifications = in.readByte() != 0;
		assetType = AssetType.fromValue(in.readString());
	}

	public UUID getProgramId() {
		return programId;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getProgramColor() {
		return programColor;
	}

	public Integer getLevel() {
		return level;
	}

	public Double getLevelProgress() {
		return levelProgress;
	}

	public String getProgramName() {
		return programName;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getCurrency() {
		return currency;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public boolean isHasNotifications() {
		return hasNotifications;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(programId);
		dest.writeString(avatar);
		dest.writeString(programColor);
		dest.writeInt(level);
		dest.writeDouble(levelProgress);
		dest.writeString(programName);
		dest.writeString(managerName);
		dest.writeString(currency);
		dest.writeByte((byte) (favorite ? 1 : 0));
		dest.writeByte((byte) (hasNotifications ? 1 : 0));
		dest.writeString(assetType.getValue());
	}

	public void update(ProgramDetailsFull programDetails) {
		this.programId = programDetails.getId();
		this.avatar = programDetails.getLogo();
		this.programColor = programDetails.getColor();
		this.level = programDetails.getLevel();
		this.levelProgress = programDetails.getLevelProgress();
		this.programName = programDetails.getTitle();
		this.managerName = programDetails.getOwner().getUsername();
		this.currency = programDetails.getCurrency().getValue();
		this.favorite = programDetails.getPersonalDetails() != null
				? programDetails.getPersonalDetails().isIsFavorite()
				: false;
		this.hasNotifications = programDetails.getPersonalDetails() != null
				? programDetails.getPersonalDetails().isHasNotifications()
				: false;
		this.assetType = AssetType.PROGRAM;
	}

	public void update(FollowDetailsFull followDetails) {
		this.programId = followDetails.getId();
		this.avatar = followDetails.getLogo();
		this.programColor = followDetails.getColor();
		this.level = 0;
		this.levelProgress = 0.0;
		this.programName = followDetails.getTitle();
		this.managerName = followDetails.getOwner().getUsername();
		this.currency = followDetails.getCurrency().getValue();
		this.favorite = followDetails.getPersonalDetails() != null
				? followDetails.getPersonalDetails().isIsFavorite()
				: false;
		this.hasNotifications = followDetails.getPersonalDetails() != null
				? followDetails.getPersonalDetails().isHasNotifications()
				: false;
		this.assetType = AssetType.FOLLOW;
	}
}
