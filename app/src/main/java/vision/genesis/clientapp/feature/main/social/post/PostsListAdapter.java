package vision.genesis.clientapp.feature.main.social.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.Post;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.SocialPostView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private final SocialPostView.Listener postsListener;

	private List<Post> posts = new ArrayList<>();

	public PostsListAdapter(SocialPostView.Listener postsListener) {
		this.postsListener = postsListener;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false), postsListener);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		if (posts.get(position) != null) {
			((PostViewHolder) holder).setPost(posts.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return posts.size();
	}

	@Override
	public long getItemId(int position) {
		return position == 0 ? 0 : posts.get(position - 1).hashCode();
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
