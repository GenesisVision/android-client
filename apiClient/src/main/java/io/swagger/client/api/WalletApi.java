package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.MultiWalletExternalTransactionsViewModel;
import io.swagger.client.model.MultiWalletFilters;
import io.swagger.client.model.MultiWalletTransactionsViewModel;
import io.swagger.client.model.TransactionDetails;
import io.swagger.client.model.WalletInfo;
import io.swagger.client.model.WalletMultiSummary;
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
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletInfo&gt;
	 */
	@GET("v1.0/wallet/addresses/{currency}")
	Observable<WalletInfo> v10WalletAddressesByCurrencyGet(
			@retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

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
	 * Multi wallet summary
	 *
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletMultiSummary&gt;
	 */
	@GET("v1.0/wallet/multi/{currency}")
	Observable<WalletMultiSummary> v10WalletMultiByCurrencyGet(
			@retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get filters
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;MultiWalletFilters&gt;
	 */
	@GET("v1.0/wallet/multi/filters")
	Observable<MultiWalletFilters> v10WalletMultiFiltersGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Wallet pending transactions
	 *
	 * @param authorization JWT access token (required)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param currency      (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;MultiWalletExternalTransactionsViewModel&gt;
	 */
	@GET("v1.0/wallet/multi/transactions/external")
	Observable<MultiWalletExternalTransactionsViewModel> v10WalletMultiTransactionsExternalGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Multi wallet transactions
	 *
	 * @param authorization JWT access token (required)
	 * @param from          (optional)
	 * @param to            (optional)
	 * @param type          (optional)
	 * @param currency      (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;MultiWalletTransactionsViewModel&gt;
	 */
	@GET("v1.0/wallet/multi/transactions")
	Observable<MultiWalletTransactionsViewModel> v10WalletMultiTransactionsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("Type") String type, @retrofit2.http.Query("Currency") String currency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Disable paying platform fees with GVT
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/wallet/paygvtfee/off")
	Observable<Void> v10WalletPaygvtfeeOffPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Enable paying platform fees with GVT
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/wallet/paygvtfee/on")
	Observable<Void> v10WalletPaygvtfeeOnPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get transaction details
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;TransactionDetails&gt;
	 */
	@GET("v1.0/wallet/transaction/{id}")
	Observable<TransactionDetails> v10WalletTransactionByIdGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
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
	 * @param wallet        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;WalletTransactionsViewModel&gt;
	 */
	@GET("v1.0/wallet/transactions")
	Observable<WalletTransactionsViewModel> v10WalletTransactionsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("AssetId") UUID assetId, @retrofit2.http.Query("From") DateTime from, @retrofit2.http.Query("To") DateTime to, @retrofit2.http.Query("AssetType") String assetType, @retrofit2.http.Query("TxAction") String txAction, @retrofit2.http.Query("Wallet") String wallet, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Transfer money
	 *
	 * @param authorization   JWT access token (required)
	 * @param sourceId        (optional)
	 * @param sourceType      (optional)
	 * @param destinationId   (optional)
	 * @param destinationType (optional)
	 * @param amount          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/wallet/transfer")
	Observable<Void> v10WalletTransferPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("SourceId") UUID sourceId, @retrofit2.http.Query("SourceType") String sourceType, @retrofit2.http.Query("DestinationId") UUID destinationId, @retrofit2.http.Query("DestinationType") String destinationType, @retrofit2.http.Query("Amount") Double amount
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
