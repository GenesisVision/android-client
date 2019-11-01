package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.ItemsViewModelTransactionViewModel;
import io.swagger.client.model.UserCommissionData;
import io.swagger.client.model.WalletDepositSummary;
import io.swagger.client.model.WalletMultiAvailable;
import io.swagger.client.model.WalletMultiSummary;
import io.swagger.client.model.WithdrawalSummary;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface WalletApi
{
	/**
	 * @param txId          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/withdraw/request/cancel/{txId}")
	Observable<Void> cancelWithdrawalRequest(
			@retrofit2.http.Path("txId") UUID txId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param requestId (optional)
	 * @param code      (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/withdraw/request/confirm")
	Observable<Void> confirmWithdrawalRequestByCode(
			@retrofit2.http.Query("requestId") UUID requestId, @retrofit2.http.Query("code") String code
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/wallet/withdraw/request/new")
	Observable<Void> createWithdrawalRequest(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body CreateWithdrawalRequestModel body
	);

	/**
	 * GenesisMarkets commission data
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;UserCommissionData&gt;
	 */
	@GET("v2.0/wallet/fee/gvtholding")
	Observable<UserCommissionData> getGMCommissionData(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Multi wallet transactions
	 *
	 * @param authorization     JWT access token (required)
	 * @param dateFrom          (optional)
	 * @param dateTo            (optional)
	 * @param transactionFilter (optional)
	 * @param skip              (optional)
	 * @param take              (optional)
	 * @return Call&lt;ItemsViewModelTransactionViewModel&gt;
	 */
	@GET("v2.0/wallet/transactions")
	Observable<ItemsViewModelTransactionViewModel> getTransactions(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("TransactionFilter") String transactionFilter, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WithdrawalSummary&gt;
	 */
	@GET("v2.0/wallet/withdraw/info")
	Observable<WithdrawalSummary> getUserWithdrawalSummary(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Wallet available
	 *
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletMultiAvailable&gt;
	 */
	@GET("v2.0/wallet/{currency}/available")
	Observable<WalletMultiAvailable> getWalletAvailable(
			@retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Wallet summary
	 *
	 * @param currency      (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletMultiSummary&gt;
	 */
	@GET("v2.0/wallet/{currency}")
	Observable<WalletMultiSummary> getWalletSummary(
			@retrofit2.http.Path("currency") String currency, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param txId          (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/withdraw/request/resend/{txId}")
	Observable<Void> resendWithdrawalRequestEmail(
			@retrofit2.http.Path("txId") UUID txId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Disable paying platform fees with GVT
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/paygvtfee/off")
	Observable<Void> switchPayFeeInGvtOff(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Enable paying platform fees with GVT
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/paygvtfee/on")
	Observable<Void> switchPayFeeInGvtOn(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Transfer money
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/wallet/transfer")
	Observable<Void> transfer(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body InternalTransferRequest body
	);

	/**
	 * Update deposit wallets
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletDepositSummary&gt;
	 */
	@POST("v2.0/wallet/deposit/update")
	Observable<WalletDepositSummary> updateDepositWallets(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
