package vision.genesis.clientapp.feature.main.social.post;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.Post;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.model.SocialPostType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface PostsListView extends MvpView
{
	void setPosts(List<Post> posts);

	void addPosts(List<Post> posts);

	void setPostDeleted(Post post, boolean isDeleted);

	void showSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener);

	void showEditPost(Post post);

	void setRefreshing(boolean refreshing);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void showEmptyList(boolean show);

	void showBottomProgress(boolean show);
}
