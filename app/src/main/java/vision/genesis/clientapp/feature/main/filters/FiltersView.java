package vision.genesis.clientapp.feature.main.filters;

import com.arellomobile.mvp.MvpView;

import vision.genesis.clientapp.model.filter.FilterOption;
import vision.genesis.clientapp.model.filter.UserFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/11/2018.
 */

interface FiltersView extends MvpView
{
	void filterUpdated(UserFilter filter);

	void setApplyButtonEnabled(boolean enabled);

	void showSingleValueChooser(FilterOption filterOption);

	void addAsset(String asset);

	void showAssetAlreadyAdded();

	void removeAsset(String asset);
}
