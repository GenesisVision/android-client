package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.PublicProfile;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */
public class UserDetailsModel implements Parcelable
{
	public static final Creator<UserDetailsModel> CREATOR = new Creator<UserDetailsModel>()
	{
		@Override
		public UserDetailsModel createFromParcel(Parcel in) {
			return new UserDetailsModel(in);
		}

		@Override
		public UserDetailsModel[] newArray(int size) {
			return new UserDetailsModel[size];
		}
	};

	private UUID userId;

	private String avatar;

	private String name;

	private DateTime registrationDate;

	public UserDetailsModel(UUID userId, String avatar, String name, DateTime registrationDate) {
		this.userId = userId;
		this.avatar = avatar;
		this.name = name;
		this.registrationDate = registrationDate;
	}

	protected UserDetailsModel(Parcel in) {
		userId = (UUID) in.readSerializable();
		avatar = in.readString();
		name = in.readString();
		registrationDate = DateTime.parse(in.readString());
	}

	public UUID getUserId() {
		return userId;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getName() {
		return name;
	}

	public DateTime getRegistrationDate() {
		return registrationDate;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(userId);
		dest.writeString(avatar);
		dest.writeString(name);
		if (registrationDate == null) {
			dest.writeString("");
		}
		else {
			dest.writeString(registrationDate.toString());
		}
	}

	public void update(PublicProfile userDetails) {
		this.userId = userDetails.getId();
		this.avatar = userDetails.getLogoUrl();
		this.name = userDetails.getUsername();
		this.registrationDate = userDetails.getRegDate();
	}
}
