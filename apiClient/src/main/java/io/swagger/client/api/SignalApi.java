package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.AttachToExternalSignalProviderCommon;
import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.DetachFromExternalSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.ItemsViewModelSignalTradingEvent;
import io.swagger.client.model.ItemsViewModelTradingAccountDetails;
import io.swagger.client.model.NewExternalSignalAccountRequest;
import io.swagger.client.model.TradesSignalViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface SignalApi
{
	/**
	 * Subscribe to external signal using common account
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/external/attach/{id}/common")
	Observable<Void> attachSlaveCommonToMaster(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToExternalSignalProviderCommon body
	);

	/**
	 * Subscribe to signal provider
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
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
	 * @param assetId       Provider asset id (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/signal/trades/{id}/close")
	Observable<Void> closeTrade(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("assetId") UUID assetId
	);

	/**
	 * Create external signal provider account
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/external/create")
	Observable<Void> createExternalSignalProviderAccount(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewExternalSignalAccountRequest body
	);

	/**
	 * Unsubscribe from signal provider
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
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/external/detach/{id}")
	Observable<Void> detachSlaveFromMaster_0(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body DetachFromExternalSignalProvider body
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
	 * @return Call&lt;ItemsViewModelSignalTradingEvent&gt;
	 */
	@GET("v2.0/signal/trades/log")
	Observable<ItemsViewModelSignalTradingEvent> getSignalTradingLog(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("AccountCurrency") String accountCurrency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get subscriber accounts for subscribe to signal provider (common method for all signals)
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ItemsViewModelTradingAccountDetails&gt;
	 */
	@GET("v2.0/signal/attach/{id}/accounts")
	Observable<ItemsViewModelTradingAccountDetails> getSubscriberAccountsForAsset(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update signal subscription settings
	 *
	 * @param authorization JWT access token (required)
	 * @param id            Asset id (required)
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
