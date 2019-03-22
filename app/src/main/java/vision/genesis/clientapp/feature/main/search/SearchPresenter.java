package vision.genesis.clientapp.feature.main.search;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView>
{
	public void onSearchClicked(String text) {
//		getViewState().showProgressBar(true);
		getViewState().sendSearchAction(text);
	}
}
