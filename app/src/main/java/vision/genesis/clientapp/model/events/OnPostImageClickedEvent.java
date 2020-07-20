package vision.genesis.clientapp.model.events;

import android.widget.ImageView;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class OnPostImageClickedEvent
{
	private final ImageView imageView;

	private final String imageUrl;

	private final int position;

	private final UUID postId;

	public OnPostImageClickedEvent(ImageView imageView, String imageUrl, int position, UUID postId) {
		this.imageView = imageView;
		this.imageUrl = imageUrl;
		this.position = position;
		this.postId = postId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getPosition() {
		return position;
	}

	public UUID getPostId() {
		return postId;
	}

	public ImageView getImageView() {
		return imageView;
	}
}
