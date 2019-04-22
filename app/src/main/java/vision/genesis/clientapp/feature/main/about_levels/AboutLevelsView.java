package vision.genesis.clientapp.feature.main.about_levels;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.LevelInfo;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

interface AboutLevelsView extends MvpView
{
	void setSelectedCurrency(String value);

	void setLevelsInfo(List<LevelInfo> levelsInfo);

	void showProgressBar(boolean show);

	void showSnackbarMessage(String message);
}
