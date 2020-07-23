package vision.genesis.clientapp.feature.main.social.post.details;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.Post;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface PostDetailsView extends MvpView
{
	void updateView(Post post);

	void setRefreshing(boolean refreshing);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);
}
