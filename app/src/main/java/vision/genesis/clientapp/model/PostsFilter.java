package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.UserFeedMode;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/07/2020.
 */
public class PostsFilter implements Parcelable
{
	public static final Creator<PostsFilter> CREATOR = new Creator<PostsFilter>()
	{
		@Override
		public PostsFilter createFromParcel(Parcel in) {
			return new PostsFilter(in);
		}

		@Override
		public PostsFilter[] newArray(int size) {
			return new PostsFilter[size];
		}
	};

	private UUID userId;

	private UUID tagContentId;

	private List<UUID> tagContentIds;

	private UserFeedMode userMode;

	private List<String> hashTags;

	private String mask;

	private boolean showTop;

	private boolean showLiked;

	private boolean showOnlyUserPosts;

	private boolean canAddNewPost;

	public PostsFilter() {
	}

	protected PostsFilter(Parcel in) {
		userId = (UUID) in.readValue(UUID.class.getClassLoader());
		tagContentId = (UUID) in.readValue(UUID.class.getClassLoader());
		in.readList(tagContentIds, UUID.class.getClassLoader());
		String userModeString = in.readString();
		userMode = userModeString != null ? UserFeedMode.fromValue(userModeString) : null;
		hashTags = in.createStringArrayList();
		mask = in.readString();
		showTop = in.readByte() != 0;
		showLiked = in.readByte() != 0;
		showOnlyUserPosts = in.readByte() != 0;
		canAddNewPost = in.readByte() != 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeValue(userId);
		parcel.writeValue(tagContentId);
		parcel.writeList(tagContentIds);
		parcel.writeString(userMode != null ? userMode.getValue() : null);
		parcel.writeStringList(hashTags);
		parcel.writeString(mask);
		parcel.writeByte((byte) (showTop ? 1 : 0));
		parcel.writeByte((byte) (showLiked ? 1 : 0));
		parcel.writeByte((byte) (showOnlyUserPosts ? 1 : 0));
		parcel.writeByte((byte) (canAddNewPost ? 1 : 0));
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getTagContentId() {
		return tagContentId;
	}

	public void setTagContentId(UUID tagContentId) {
		this.tagContentId = tagContentId;
	}

	public List<UUID> getTagContentIds() {
		return tagContentIds;
	}

	public void setTagContentIds(List<UUID> tagContentIds) {
		this.tagContentIds = tagContentIds;
	}

	public UserFeedMode getUserMode() {
		return userMode;
	}

	public void setUserMode(UserFeedMode userMode) {
		this.userMode = userMode;
	}

	public List<String> getHashTags() {
		return hashTags;
	}

	public void setHashTags(List<String> hashTags) {
		this.hashTags = hashTags;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public Boolean getShowTop() {
		return showTop;
	}

	public void setShowTop(Boolean showTop) {
		this.showTop = showTop;
	}

	public Boolean getShowLiked() {
		return showLiked;
	}

	public void setShowLiked(Boolean showLiked) {
		this.showLiked = showLiked;
	}

	public Boolean getShowOnlyUserPosts() {
		return showOnlyUserPosts;
	}

	public void setShowOnlyUserPosts(Boolean showOnlyUserPosts) {
		this.showOnlyUserPosts = showOnlyUserPosts;
	}

	public Boolean isCanAddNewPost() {
		return canAddNewPost;
	}

	public void setCanAddNewPost(Boolean canAddNewPost) {
		this.canAddNewPost = canAddNewPost;
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
