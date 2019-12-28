package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.AttachToExternalSignalProviderCommon;
import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.DetachFromExternalSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.ItemsViewModelSignalTradingEvent;
import io.swagger.client.model.ItemsViewModelTradingAccountDetails;
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
	Observable<Void> attachSlaveToMasterExternalCommonAccount(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToExternalSignalProviderCommon body
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
	@POST("v2.0/signal/external/attach/{id}/private")
	Observable<Void> attachSlaveToMasterExternalPrivateAccount(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToExternalSignalProviderExt body
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
	Observable<Void> attachSlaveToMasterInternal(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToSignalProvider body
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
	Observable<Void> closeTradeInternal(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("assetId") UUID assetId
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
	Observable<Void> detachSlaveFromMasterExternal(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body DetachFromExternalSignalProvider body
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
	Observable<Void> detachSlaveFromMasterInternal(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body DetachFromSignalProvider body
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
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/signal/external/{id}/update")
	Observable<Void> updateExternalSubscriptionSettings(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body AttachToExternalSignalProviderExt body
	);

	/**
	 * Update signal subscription settings
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
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
