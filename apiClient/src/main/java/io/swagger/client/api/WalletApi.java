package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.WalletSummary;
import retrofit2.http.GET;
import rx.Observable;

public interface WalletApi
{
	/**
	 * Wallet summary
	 *
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletSummary&gt;
	 */
	@GET("v1.0/wallet/{currency}")
	Observable<WalletSummary> v10WalletByCurrencyGet(
			@retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Wallet transactions
	 *
	 * @param authorization JWT access token (required)
	 * @param assetId       (optional)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param assetType     (optional)
	 * @param txAction      (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;WalletSummary&gt;
	 */
	@GET("v1.0/wallet/transactions")
	Observable<WalletSummary> v10WalletTransactionsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("TxAction") String txAction, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
