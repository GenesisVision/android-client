package vision.genesis.clientapp.managers;

import io.swagger.client.api.SearchApi;
import io.swagger.client.model.SearchViewModel;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/03/2019.
 */

public class SearchManager
{
	private SearchApi searchApi;

	public SearchManager(SearchApi searchApi) {
		this.searchApi = searchApi;
	}

	public Observable<SearchViewModel> search(String mask, Integer take) {
		return searchApi.v10SearchGet(AuthManager.token.getValue(), mask, take);
	}
}