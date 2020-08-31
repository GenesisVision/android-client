package vision.genesis.clientapp.feature.main.social;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.Post;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.model.SocialPostType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface SocialMainView extends MvpView
{
	void updateMedia();

	void updateLive();

	void updateHot();

	void updateFeed();

	void updateUsers();

	void openMediaUrl(String url);

	void showAddNewPostButton(boolean show);

	void showSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener);

	void showEditPost(Post post);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}
