package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.CopyTradingAccountsList;
import io.swagger.client.model.TradesHistorySignalSlaveViewModel;
import io.swagger.client.model.TradesOpenSignalSlaveViewModel;
import retrofit2.http.GET;
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
	 * Subscribe to programs signals
	 *
	 * @param id                     Program Id (required)
	 * @param authorization          JWT access token (required)
	 * @param mode                   (optional)
	 * @param percent                (optional)
	 * @param openTolerancePercent   (optional)
	 * @param fixedVolume            (optional)
	 * @param fixedCurrency          (optional)
	 * @param initialDepositCurrency (optional)
	 * @param initialDepositAmount   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/signal/attach/{id}")
	Observable<Void> v10SignalAttachByIdPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Mode") String mode, @retrofit2.http.Query("Percent") Double percent, @retrofit2.http.Query("OpenTolerancePercent") Double openTolerancePercent, @retrofit2.http.Query("FixedVolume") Double fixedVolume, @retrofit2.http.Query("FixedCurrency") String fixedCurrency, @retrofit2.http.Query("InitialDepositCurrency") String initialDepositCurrency, @retrofit2.http.Query("InitialDepositAmount") Double initialDepositAmount
	);

	/**
	 * Unsubscribe from program signals
	 *
	 * @param id            Program id (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/signal/detach/{id}")
	Observable<Void> v10SignalDetachByIdPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close signal trade
	 *
	 * @param id            Trade id (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/signal/trades/{id}/close")
	Observable<Void> v10SignalTradesByIdClosePost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investors signals trades history
	 *
	 * @param authorization JWT access token (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param symbol        (optional)
	 * @param sorting       (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;TradesHistorySignalSlaveViewModel&gt;
	 */
	@GET("v1.0/signal/trades")
	Observable<TradesHistorySignalSlaveViewModel> v10SignalTradesGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Symbol") String symbol, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get investors signals open trades
	 *
	 * @param authorization JWT access token (required)
	 * @param sorting       (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;TradesOpenSignalSlaveViewModel&gt;
	 */
	@GET("v1.0/signal/trades/open")
	Observable<TradesOpenSignalSlaveViewModel> v10SignalTradesOpenGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Update signal subscription settings
	 *
	 * @param authorization          JWT access token (required)
	 * @param id                     Program id (optional)
	 * @param mode                   (optional)
	 * @param percent                (optional)
	 * @param openTolerancePercent   (optional)
	 * @param fixedVolume            (optional)
	 * @param fixedCurrency          (optional)
	 * @param initialDepositCurrency (optional)
	 * @param initialDepositAmount   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/signal/update")
	Observable<Void> v10SignalUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("id") UUID id, @retrofit2.http.Query("Mode") String mode, @retrofit2.http.Query("Percent") Double percent, @retrofit2.http.Query("OpenTolerancePercent") Double openTolerancePercent, @retrofit2.http.Query("FixedVolume") Double fixedVolume, @retrofit2.http.Query("FixedCurrency") String fixedCurrency, @retrofit2.http.Query("InitialDepositCurrency") String initialDepositCurrency, @retrofit2.http.Query("InitialDepositAmount") Double initialDepositAmount
	);

}
