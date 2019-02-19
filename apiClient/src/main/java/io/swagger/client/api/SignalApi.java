package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.CopyTradingAccountsList;
import io.swagger.client.model.OpenSignalTradesList;
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
	 * Get investors signals open trades
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;OpenSignalTradesList&gt;
	 */
	@GET("v1.0/signal/opensignaltrades")
	Observable<OpenSignalTradesList> v10SignalOpensignaltradesGet(
			@retrofit2.http.Header("Authorization") String authorization
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
