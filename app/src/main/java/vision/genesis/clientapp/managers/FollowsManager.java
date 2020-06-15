package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.FollowApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.Currency;
import io.swagger.client.model.DetachFromExternalSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.FollowDetailsListItemItemsViewModel;
import io.swagger.client.model.FollowFilterSorting;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.SignalSubscriptionItemsViewModel;
import io.swagger.client.model.SignalTradingEventItemsViewModel;
import io.swagger.client.model.TradesSignalViewModel;
import io.swagger.client.model.TradingAccountDetailsItemsViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.filter.ProgramsFilter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/11/2019.
 */

public class FollowsManager
{
	private final SignalApi signalApi;

	private final FollowApi followApi;

	public FollowsManager(SignalApi signalApi, FollowApi followApi) {
		this.signalApi = signalApi;
		this.followApi = followApi;
	}

	public Observable<FollowDetailsListItemItemsViewModel> getFollows(ProgramsFilter filter) {
		return followApi.getFollowAssets(
				filter.getSorting() != null ? FollowFilterSorting.fromValue(filter.getSorting().getValue()) : null,
				filter.getShowIn() != null ? Currency.fromValue(filter.getShowIn().getValue()) : null,
				filter.getTags(), null,
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(),
				false, false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<ProgramFollowDetailsFull> getFollowDetails(String followId) {
		return followApi.getFollowAssetDetails(followId, ImageQuality.HIGH);
	}

	public Observable<SignalSubscriptionItemsViewModel> getMySubscriptionsForFollow(UUID followId, Boolean onlyActive) {
		return followApi.getFollowSubscriptionsForAsset(followId, onlyActive);
	}

	public Observable<SignalSubscriptionItemsViewModel> getMastersForMyAccount(UUID accountId, Boolean onlyActive) {
		return followApi.getFollowSubscriptionsForOwnAccount(accountId, onlyActive);
	}

	public Observable<TradesSignalViewModel> getTrades(UUID assetId, DateRange dateRange, Boolean isFollow, Integer skip, Integer take) {
		return followApi.getAssetTrades(assetId, dateRange.getFrom(), dateRange.getTo(), null, null, null, null, isFollow, skip, take);
	}

	public Observable<Void> subscribeToFollow(UUID followId, AttachToSignalProvider model) {
		return signalApi.attachSlaveToMasterInternal(followId, model);
	}

	public Observable<Void> subscribeToExternalFollowWithPrivate(UUID followId, AttachToExternalSignalProviderExt model) {
		return signalApi.attachSlaveToMasterExternalPrivateAccount(followId, model);
	}

	public Observable<Void> updateSubscription(UUID followId, SubscriptionSettingsModel model) {
		return signalApi.updateSubscriptionSettings(followId, model.getApiModel());
	}

	public Observable<Void> unsubscribeFromFollow(UUID followId, DetachFromSignalProvider model) {
		return signalApi.detachSlaveFromMasterInternal(followId, model);
	}

	public Observable<Void> unsubscribeFromExternalFollow(UUID followId, DetachFromExternalSignalProvider model) {
		return signalApi.detachSlaveFromMasterExternal(followId, model);
	}

//	public Observable<TradesSignalViewModel> getOpenTrades(String sorting, String symbol, UUID accountId, String accountCurrency, Integer skip, Integer take) {
//		return signalApi.getOpenSignalTrades( sorting, symbol, accountId, accountCurrency, skip, take);
//	}
//
//	public Observable<Void> closeTrade(UUID tradeId, UUID programId) {
//		return signalApi.closeTradeInternal(tradeId programId);
//	}

	public Observable<SignalTradingEventItemsViewModel> getTradingLog(UUID accountId, Integer skip, Integer take) {
		return signalApi.getSignalTradingLog(accountId, null, skip, take);
	}

	public Observable<TradingAccountDetailsItemsViewModel> getSubscriberAccounts(UUID followId) {
		return signalApi.getSubscriberAccountsForAsset(followId);
	}

	public Observable<ProgramProfitPercentCharts> getProfitPercentChart(UUID programId, DateRange dateRange, Integer maxPointCount, Currency currency, List<Currency> currencies) {
		return followApi.getProfitPercentCharts(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency, currencies);
	}

	public Observable<AbsoluteProfitChart> getProfitAbsoluteChart(UUID programId, DateRange dateRange, Integer maxPointCount, Currency currency) {
		return followApi.getAbsoluteProfitChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<AccountBalanceChart> getBalanceChart(UUID programId, DateRange dateRange, Integer maxPointCount, Currency currency) {
		return followApi.getBalanceChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<Void> setFollowFavorite(UUID programId, boolean isFavorite) {
		return isFavorite ? followFavoritesAdd(programId) : followFavoritesRemove(programId);
	}

	private Observable<Void> followFavoritesAdd(UUID programId) {
		return followApi.addToFavorites(programId);
	}

	private Observable<Void> followFavoritesRemove(UUID programId) {
		return followApi.removeFromFavorites(programId);
	}
}