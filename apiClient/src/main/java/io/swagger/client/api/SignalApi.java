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
	 * Get copytrading accounts
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;CopyTradingAccountsList&gt;
	 */
	@GET("v1.0/signal/accounts")
	Observable<CopyTradingAccountsList> v10SignalAccountsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get subscribe to programs signals info
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;AttachToSignalProviderInfo&gt;
	 */
	@GET("v1.0/signal/attach/{id}/info")
	Observable<AttachToSignalProviderInfo> v10SignalAttachByIdInfoGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Subscribe to programs signals
	 *
	 * @param id            Program Id (required)
	 * @param authorization JWT access token (required)
	 * @param model         Subscription settings (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/signal/attach/{id}")
	Observable<Void> v10SignalAttachByIdPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body AttachToSignalProvider model
	);

	/**
	 * Update signal subscription settings
	 *
	 * @param id            Program id (required)
	 * @param authorization JWT access token (required)
	 * @param model         Subscription settings (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/signal/{id}/update")
	Observable<Void> v10SignalByIdUpdatePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body AttachToSignalProvider model
	);

	/**
	 * Unsubscribe from program signals
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/signal/detach/{id}")
	Observable<Void> v10SignalDetachByIdPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body DetachFromSignalProvider model
	);

	/**
	 * Subscribe to external signal account
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/signal/external/attach/{id}/external")
	Observable<Void> v10SignalExternalAttachByIdExternalPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body AttachToExternalSignalProviderExt model
	);

	/**
	 * Create external signal account
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;ManagerProgramCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/signal/external/create")
	Observable<ManagerProgramCreateResult> v10SignalExternalCreatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewExternalSignalAccountRequest request
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
	@GET("v1.0/signal/external")
	Observable<SignalAccountsList> v10SignalExternalGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Tags") List<String> tags, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("StatisticDateFrom") DateTime statisticDateFrom, @retrofit2.http.Query("StatisticDateTo") DateTime statisticDateTo, @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount, @retrofit2.http.Query("Mask") String mask, @retrofit2.http.Query("FacetId") String facetId, @retrofit2.http.Query("IsFavorite") Boolean isFavorite, @retrofit2.http.Query("IsEnabled") Boolean isEnabled, @retrofit2.http.Query("HasInvestorsForAll") Boolean hasInvestorsForAll, @retrofit2.http.Query("HasInvestorsForClosed") Boolean hasInvestorsForClosed, @retrofit2.http.Query("Ids") List<UUID> ids, @retrofit2.http.Query("ForceUseIdsList") Boolean forceUseIdsList, @retrofit2.http.Query("ManagerId") String managerId, @retrofit2.http.Query("ProgramManagerId") UUID programManagerId, @retrofit2.http.Query("Status") List<String> status, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Close signal trade
	 *
	 * @param id            Trade id (required)
	 * @param authorization JWT access token (required)
	 * @param programId     Provider program id (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/signal/trades/{id}/close")
	Observable<Void> v10SignalTradesByIdClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("programId") UUID programId
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
	@GET("v1.0/signal/trades")
	Observable<TradesSignalViewModel> v10SignalTradesGet(
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
	@GET("v1.0/signal/trades/log")
	Observable<SignalTradingEvents> v10SignalTradesLogGet(
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
	@GET("v1.0/signal/trades/open")
	Observable<TradesSignalViewModel> v10SignalTradesOpenGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
