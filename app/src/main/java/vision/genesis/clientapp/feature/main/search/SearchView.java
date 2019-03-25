package vision.genesis.clientapp.feature.main.search;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import io.swagger.client.model.SearchViewModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/05/2018.
 */

interface SearchView extends MvpView
{
	void sendSearchResults(SearchViewModel results);

	@StateStrategyType(OneExecutionStateStrategy.class)
	void showSnackbarMessage(String message);

	void showNoInternet(boolean show);

	void showProgressBar(boolean show);

	void finishActivity();
}
