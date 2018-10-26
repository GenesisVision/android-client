package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.WalletPendingTransactionsViewModel;
import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WalletTransactionsViewModel;
import io.swagger.client.model.WalletsInfo;
import io.swagger.client.model.WithdrawalSummary;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface WalletApi
{
	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletsInfo&gt;
	 */
	@GET("v1.0/wallet/addresses")
	Observable<WalletsInfo> v10WalletAddressesGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

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
	 * @return Call&lt;WalletTransactionsViewModel&gt;
	 */
	@GET("v1.0/wallet/transactions")
	Observable<WalletTransactionsViewModel> v10WalletTransactionsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("TxAction") String txAction, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Wallet pending transactions
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletPendingTransactionsViewModel&gt;
	 */
	@GET("v1.0/wallet/transactions/pending")
	Observable<WalletPendingTransactionsViewModel> v10WalletTransactionsPendingGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WithdrawalSummary&gt;
	 */
	@GET("v1.0/wallet/withdraw/info")
	Observable<WithdrawalSummary> v10WalletWithdrawInfoGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param txId          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/wallet/withdraw/request/cancel/{txId}")
	Observable<Void> v10WalletWithdrawRequestCancelByTxIdPost(
			@retrofit2.http.Path("txId") UUID txId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param requestId (optional)
	 * @param code      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/wallet/withdraw/request/confirm")
	Observable<Void> v10WalletWithdrawRequestConfirmPost(
			@retrofit2.http.Query("requestId") UUID requestId, @retrofit2.http.Query("code") String code
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v1.0/wallet/withdraw/request/new")
	Observable<Void> v10WalletWithdrawRequestNewPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body CreateWithdrawalRequestModel model
	);

	/**
	 * @param txId          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/wallet/withdraw/request/resend/{txId}")
	Observable<Void> v10WalletWithdrawRequestResendByTxIdPost(
			@retrofit2.http.Path("txId") UUID txId, @retrofit2.http.Header("Authorization") String authorization
	);

}
