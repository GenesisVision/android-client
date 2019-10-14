package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.CopyTradingAccountsList;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.ManagerProgramCreateResult;
import io.swagger.client.model.NewExternalSignalAccountRequest;
import io.swagger.client.model.SignalAccountsList;
import io.swagger.client.model.SignalTradingEvents;
import io.swagger.client.model.TradesSignalViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface SignalApi
{
	/**
	 * Subscribe to programs signals
	 *
	 * @param authorization JWT access token (required)
	 * @param id            Program Id (required)
	 * @param body          Subscription settings (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/attach/{id}")
	Observable<Void> attachSlaveToMaster(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToSignalProvider body
	);

	/**
	 * Subscribe to external signal account
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/external/attach/{id}/external")
	Observable<Void> attachSlaveToMaster_0(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToExternalSignalProviderExt body
	);

	/**
	 * Close signal trade
	 *
	 * @param id            Trade id (required)
	 * @param authorization JWT access token (required)
	 * @param programId     Provider program id (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/signal/trades/{id}/close")
	Observable<Void> closeTrade(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("programId") UUID programId
	);

	/**
	 * Create external signal account
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;ManagerProgramCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/external/create")
	Observable<ManagerProgramCreateResult> createExternalSignalAccount(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewExternalSignalAccountRequest body
	);

	/**
	 * Unsubscribe from program signals
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/detach/{id}")
	Observable<Void> detachSlaveFromMaster(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body DetachFromSignalProvider body
	);

	/**
	 * Get copytrading accounts
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;CopyTradingAccountsList&gt;
	 */
	@GET("v2.0/signal/accounts")
	Observable<CopyTradingAccountsList> getCopytradingAccounts(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investors signals trading log
	 *
	 * @param authorization   JWT access token (required)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;SignalTradingEvents&gt;
	 */
	@GET("v2.0/signal/external/trades/log")
	Observable<SignalTradingEvents> getExternalSignalTradingLog(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get investors signals open trades
	 *
	 * @param authorization   JWT access token (required)
	 * @param sorting         (optional)
	 * @param symbol          (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesSignalViewModel&gt;
	 */
	@GET("v2.0/signal/trades/open")
	Observable<TradesSignalViewModel> getOpenSignalTrades(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Accounts list
	 *
	 * @param authorization         (optional)
	 * @param tags                  (optional)
	 * @param sorting               (optional)
	 * @param statisticDateFrom     (optional)
	 * @param statisticDateTo       (optional)
	 * @param chartPointsCount      (optional)
	 * @param mask                  (optional)
	 * @param facetId               (optional)
	 * @param isFavorite            (optional)
	 * @param isEnabled             (optional)
	 * @param hasInvestorsForAll    (optional)
	 * @param hasInvestorsForClosed (optional)
	 * @param ids                   (optional)
	 * @param forceUseIdsList       (optional)
	 * @param managerId             (optional)
	 * @param programManagerId      (optional)
	 * @param status                (optional)
	 * @param skip                  (optional)
	 * @param take                  (optional)
	 * @return Call&lt;SignalAccountsList&gt;
	 */
	@GET("v2.0/signal/external")
	Observable<SignalAccountsList> getSignalAccounts(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("IsFavorite") Boolean isFavorite, @retrofit2.http.Query("IsEnabled") Boolean isEnabled, @retrofit2.http.Query("HasInvestorsForAll") Boolean hasInvestorsForAll, @retrofit2.http.Query("HasInvestorsForClosed") Boolean hasInvestorsForClosed, @retrofit2.http.Query("Ids") List<UUID> ids, @retrofit2.http.Query("ForceUseIdsList") Boolean forceUseIdsList, @retrofit2.http.Query("ManagerId") String managerId, @retrofit2.http.Query("ProgramManagerId") UUID programManagerId, @retrofit2.http.Query("Status") List<String> status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get investors signals trades history
	 *
	 * @param authorization   JWT access token (required)
	 * @param dateFrom        (optional)
	 * @param dateTo          (optional)
	 * @param symbol          (optional)
	 * @param sorting         (optional)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TradesSignalViewModel&gt;
	 */
	@GET("v2.0/signal/trades")
	Observable<TradesSignalViewModel> getSignalTrades(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get investors signals trading log
	 *
	 * @param authorization   JWT access token (required)
	 * @param accountId       (optional)
	 * @param accountCurrency (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;SignalTradingEvents&gt;
	 */
	@GET("v2.0/signal/trades/log")
	Observable<SignalTradingEvents> getSignalTradingLog(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get subscribe to programs signals info
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;AttachToSignalProviderInfo&gt;
	 */
	@GET("v2.0/signal/attach/{id}/info")
	Observable<AttachToSignalProviderInfo> getSlaveAttachInfo(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update signal subscription settings
	 *
	 * @param authorization JWT access token (required)
	 * @param id            Program id (required)
	 * @param body          Subscription settings (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/{id}/update")
	Observable<Void> updateSubscriptionSettings(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToSignalProvider body
	);

}
