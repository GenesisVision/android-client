package vision.genesis.clientapp.feature.main.fund.create.assets;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.PlatformAsset;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

interface CreateFundAssetsView extends MvpView
{
	void updateNotification(List<PlatformAsset> mandatoryAssets);

	void updateRemainingShare(double remainingShare);

	void addAsset(PlatformAsset asset, double share);

	void updateAsset(int index, double newShare);

	void selectAsset(PlatformAsset asset);

	void deselectAssets();

	void removeAsset(int index);

	@StateStrategyType(SkipStrategy.class)
	void showSelectAssetShareDialog(PlatformAsset asset, double share, double remainingShare);

	void showSnackbarMessage(String message);

	void setAddAssetButtonEnabled(boolean enabled);

	void setNextButtonEnabled(boolean enabled);
}