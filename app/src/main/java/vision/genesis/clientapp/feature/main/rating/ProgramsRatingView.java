package vision.genesis.clientapp.feature.main.rating;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.LevelInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

interface ProgramsRatingView extends MvpView
{
	void setData(List<LevelInfo> levelData);

	void showSnackbarMessage(String message);
}
