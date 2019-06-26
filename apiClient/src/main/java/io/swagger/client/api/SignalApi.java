package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.CopyTradingAccountsList;
import io.swagger.client.model.DetachFromSignalProvider;
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
	 * @param authorization JWT access token (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param symbol        (optional)
	 * @param sorting       (optional)
	 * @param accountId     (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;TradesSignalViewModel&gt;
	 */
	@GET("v1.0/signal/trades")
	Observable<TradesSignalViewModel> v10SignalTradesGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get investors signals open trades
	 *
	 * @param authorization JWT access token (required)
	 * @param sorting       (optional)
	 * @param symbol        (optional)
	 * @param accountId     (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;TradesSignalViewModel&gt;
	 */
	@GET("v1.0/signal/trades/open")
	Observable<TradesSignalViewModel> v10SignalTradesOpenGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("AccountId") UUID accountId, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
