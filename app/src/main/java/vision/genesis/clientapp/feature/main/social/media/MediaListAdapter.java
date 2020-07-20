package vision.genesis.clientapp.feature.main.social.media;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.MediaPost;
import io.swagger.client.model.PostImageResize;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnMediaPostClickedEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.MediaPostViewHolder>
{
	private List<MediaPost> mediaPosts = new ArrayList<>();

	@Override
	public MediaPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_media_post, parent, false);
		return new MediaPostViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(MediaPostViewHolder holder, int position) {
		if (mediaPosts.get(position) != null) {
			holder.setMediaPosts(mediaPosts.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return mediaPosts.size();
	}

	@Override
	public long getItemId(int position) {
		return mediaPosts.get(position) != null
				? mediaPosts.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	void setMediaPosts(List<MediaPost> mediaPosts) {
		this.mediaPosts.clear();
		this.mediaPosts.addAll(mediaPosts);
		notifyDataSetChanged();
	}

	void addMediaPosts(List<MediaPost> mediaPosts) {
		this.mediaPosts.addAll(mediaPosts);
		notifyDataSetChanged();
	}

	static class MediaPostViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.image)
		public SimpleDraweeView image;

		@BindView(R.id.type_image)
		public SimpleDraweeView typeImage;

		@BindView(R.id.title)
		public TextView title;

		@BindView(R.id.text)
		public TextView text;

		@BindView(R.id.author_logo)
		public SimpleDraweeView authorLogo;

		@BindView(R.id.author_name)
		public TextView authorName;

		@BindView(R.id.date)
		public TextView date;

		private MediaPost post;

		MediaPostViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(v -> {
				if (post != null) {
					EventBus.getDefault().post(new OnMediaPostClickedEvent(post));
				}
			});
		}

		void setMediaPosts(MediaPost post) {
			this.post = post;

			setImage(post);
			this.title.setText(post.getTitle());
			this.text.setText(post.getText());
			this.authorLogo.setImageURI(ImageUtils.getImageUri(post.getAuthorLogoUrl()));
			this.authorName.setText(post.getAuthor());
			this.date.setText(DateTimeUtil.formatEventDateTime(post.getDate()));

			this.typeImage.setImageURI(ImageUtils.getImageUri(post.getTypeLogoUrl()));
		}

		private void setImage(MediaPost post) {
			String logoUrl = post.getImage().getResizes().get(post.getImage().getResizes().size() - 1).getLogoUrl();
			for (PostImageResize resize : post.getImage().getResizes()) {
				if (resize.getQuality().equals(ImageQuality.HIGH)) {
					logoUrl = resize.getLogoUrl();
				}
			}
			this.image.setImageURI(ImageUtils.getImageUri(logoUrl));
			this.image.setVisibility(logoUrl == null || logoUrl.isEmpty() ? View.GONE : View.VISIBLE);
		}
	}
}
