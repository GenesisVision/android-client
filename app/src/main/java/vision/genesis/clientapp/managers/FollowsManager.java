package vision.genesis.clientapp.managers;

import java.util.List;
import java.util.UUID;

import io.swagger.client.api.FollowApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.AccountBalanceChart;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.ItemsViewModelFollowDetailsListItem;
import io.swagger.client.model.ItemsViewModelSignalSubscription;
import io.swagger.client.model.ItemsViewModelSignalTradingEvent;
import io.swagger.client.model.ItemsViewModelTradingAccountDetails;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramProfitPercentCharts;
import io.swagger.client.model.TradesSignalViewModel;
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

	public Observable<ItemsViewModelFollowDetailsListItem> getFollows(ProgramsFilter filter) {
		return followApi.getFollowAssets(AuthManager.token.getValue(),
				null, null,
				filter.getTags(),
				filter.getDateRange().getFrom(), filter.getDateRange().getTo(),
				filter.getChartPointsCount(), filter.getFacetId() == null ? null : filter.getFacetId().toString(),
				filter.getMask(), filter.getManagerId(), false,
				filter.getSkip(), filter.getTake());
	}

	public Observable<ProgramFollowDetailsFull> getFollowDetails(String followId) {
		return followApi.getFollowAssetDetails(followId, AuthManager.token.getValue());
	}

	public Observable<ItemsViewModelSignalSubscription> getMySubscriptionsForFollow(UUID followId, Boolean onlyActive) {
		return followApi.getFollowSubscriptionsForAsset(followId, AuthManager.token.getValue(), onlyActive);
	}

	public Observable<ItemsViewModelSignalSubscription> getMastersForMyAccount(UUID accountId, Boolean onlyActive) {
		return followApi.getFollowSubscriptionsForOwnAccount(accountId, AuthManager.token.getValue(), onlyActive);
	}

	public Observable<TradesSignalViewModel> getTrades(UUID assetId, DateRange dateRange, Boolean isFollow, Integer skip, Integer take) {
		return followApi.getAssetTrades(assetId, dateRange.getFrom(), dateRange.getTo(), null, null, null, null, isFollow, skip, take);
	}

	public Observable<Void> subscribeToFollow(UUID followId, AttachToSignalProvider model) {
		return signalApi.attachSlaveToMasterInternal(AuthManager.token.getValue(), followId, model);
	}

	public Observable<Void> updateSubscription(UUID followId, SubscriptionSettingsModel model) {
		return signalApi.updateSubscriptionSettings(AuthManager.token.getValue(), followId, model.getApiModel());
	}

	public Observable<Void> unsubscribeFromFollow(UUID followId, DetachFromSignalProvider model) {
		return signalApi.detachSlaveFromMasterInternal(AuthManager.token.getValue(), followId, model);
	}

	public Observable<TradesSignalViewModel> getOpenTrades(String sorting, String symbol, UUID accountId, String accountCurrency, Integer skip, Integer take) {
		return signalApi.getOpenSignalTrades(AuthManager.token.getValue(), sorting, symbol, accountId, accountCurrency, skip, take);
	}

	public Observable<Void> closeTrade(UUID tradeId, UUID programId) {
		return signalApi.closeTradeInternal(tradeId, AuthManager.token.getValue(), programId);
	}

	public Observable<ItemsViewModelSignalTradingEvent> getTradingLog(UUID accountId, Integer skip, Integer take) {
		return signalApi.getSignalTradingLog(AuthManager.token.getValue(), accountId, null, skip, take);
	}

	public Observable<ItemsViewModelTradingAccountDetails> getSubscriberAccounts(UUID followId) {
		return signalApi.getSubscriberAccountsForAsset(followId, AuthManager.token.getValue());
	}

	public Observable<ProgramProfitPercentCharts> getProfitPercentChart(UUID programId, DateRange dateRange, Integer maxPointCount, String currency, List<Object> currencies) {
		return followApi.getProfitPercentCharts(programId, AuthManager.token.getValue(), dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency, currencies);
	}

	public Observable<AbsoluteProfitChart> getProfitAbsoluteChart(UUID programId, DateRange dateRange, Integer maxPointCount, String currency) {
		return followApi.getAbsoluteProfitChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}

	public Observable<AccountBalanceChart> getBalanceChart(UUID programId, DateRange dateRange, Integer maxPointCount, String currency) {
		return followApi.getBalanceChart(programId, dateRange.getFrom(), dateRange.getTo(), maxPointCount, currency);
	}
}