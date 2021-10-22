package vision.genesis.clientapp.managers;

import java.util.Collections;
import java.util.UUID;

import io.swagger.client.api.CoinsApi;
import io.swagger.client.model.CoinsAssetItemsViewModel;
import io.swagger.client.model.CoinsFilterSorting;
import io.swagger.client.model.CoinsHistoryEventItemsViewModel;
import io.swagger.client.model.InternalTransferRequest;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/10/2021.
 */

public class CoinsManager
{
	private final CoinsApi coinsApi;

	public CoinsManager(CoinsApi coinsApi) {
		this.coinsApi = coinsApi;
	}

	public Observable<CoinsAssetItemsViewModel> getCoinsList(ProgramsFilter filter) {
		return coinsApi.getCoins(CoinsFilterSorting.fromValue(filter.getSorting().getValue()),
				filter.getAssets(), filter.getIsFavorite(),
				filter.getSkip(), filter.getTake());
	}

	public Observable<CoinsAssetItemsViewModel> getCoin(String symbol) {
		return coinsApi.getCoins(null, Collections.singletonList(symbol), null, 0, 1);
	}

	public Observable<Void> setCoinFavorite(UUID fundId, boolean isFavorite) {
		return isFavorite ? coinFavoritesAdd(fundId) : coinFavoritesRemove(fundId);
	}

	private Observable<Void> coinFavoritesAdd(UUID coinId) {
		return coinsApi.addToFavorites(coinId);
	}

	private Observable<Void> coinFavoritesRemove(UUID coinId) {
		return coinsApi.removeFromFavorites(coinId);
	}

	public Observable<Void> transfer(InternalTransferRequest request) {
		return coinsApi.transfer(request);
	}

	public Observable<CoinsAssetItemsViewModel> getPortfolio(int skip, int take) {
		return coinsApi.getUserCoins(CoinsFilterSorting.BYTOTALDESC,
				null, null,
				skip, take);
	}

	public Observable<CoinsHistoryEventItemsViewModel> getHistory(ProgramsFilter filter) {
		DateRange dateRange = filter.getDateRange();
		return coinsApi.getCoinsConvertingHistory(
				dateRange != null ? dateRange.getFrom() : null,
				dateRange != null ? dateRange.getTo() : null,
				filter.getAssets(), filter.getSkip(), filter.getTake());
	}
}