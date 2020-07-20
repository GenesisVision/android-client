package vision.genesis.clientapp.feature.main.social.media;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.MediaPost;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

interface MediaView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void setRefreshing(boolean refreshing);

	void setMedia(List<MediaPost> mediaPosts);

	void addMedia(List<MediaPost> mediaPosts);

	void openMediaUrl(String url);

	void showSnackbarMessage(String message);
}
