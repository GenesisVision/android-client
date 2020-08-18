package vision.genesis.clientapp.feature.main.social.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.Post;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.AddNewPostView;
import vision.genesis.clientapp.ui.SocialPostView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private static final int TYPE_BUTTON = 0;

	private static final int TYPE_POST = 1;

	private final SocialPostView.Listener postsListener;

	private final Boolean canAddNewPost;

	private final UUID userId;

	private List<Post> posts = new ArrayList<>();

	public PostsListAdapter(SocialPostView.Listener postsListener, Boolean canAddNewPost, UUID userId) {
		this.postsListener = postsListener;
		this.canAddNewPost = canAddNewPost;
		this.userId = userId;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		switch (viewType) {
			case TYPE_BUTTON:
				return new ButtonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_add_new_post_button, parent, false), userId);
			case TYPE_POST:
			default:
				return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false), postsListener);
		}
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		switch (holder.getItemViewType()) {
			case TYPE_BUTTON:
				break;
			case TYPE_POST:
				if (canAddNewPost) {
					((PostViewHolder) holder).setPost(posts.get(position - 1));
				}
				else {
					((PostViewHolder) holder).setPost(posts.get(position));
				}
				break;
		}
	}

	@Override
	public int getItemCount() {
		int count = posts.size();
		if (canAddNewPost) {
			count++;
		}
		return count;
	}


	@Override
	public int getItemViewType(int position) {
		if (canAddNewPost) {
			return position == 0 ? TYPE_BUTTON : TYPE_POST;
		}
		else {
			return TYPE_POST;
		}
	}

	@Override
	public long getItemId(int position) {
		if (canAddNewPost) {
			return position == 0 ? 0 : posts.get(position - 1).hashCode();
		}
		else {
			return posts.get(position) != null
					? posts.get(position).hashCode()
					: RecyclerView.NO_ID;
		}
	}

	public void setPosts(List<Post> posts) {
		this.posts.clear();
		this.posts.addAll(posts);
		notifyDataSetChanged();
	}

	public void addPosts(List<Post> posts) {
		this.posts.addAll(posts);
		notifyDataSetChanged();
	}

	public void setPostDeleted(Post deletedPost, boolean isDeleted) {
		int i = 0;
		for (Post post : posts) {
			if (post.getId().toString().equals(deletedPost.getId().toString())) {
				post.setIsDeleted(isDeleted);
				notifyItemChanged(i);
				break;
			}
			i++;
		}
	}

	static class ButtonViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.view_add_new_post)
		public AddNewPostView addNewPostView;

		ButtonViewHolder(View itemView, UUID userId) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			addNewPostView.setData(userId);
		}
	}

	static class PostViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.view_post)
		public SocialPostView postView;

		PostViewHolder(View itemView, SocialPostView.Listener listener) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			postView.setListener(listener);
		}

		void setPost(Post post) {
			postView.setPost(post);
		}
	}
}
