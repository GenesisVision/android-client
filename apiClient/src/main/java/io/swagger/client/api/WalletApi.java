package io.swagger.client.api;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.model.CreateWithdrawalRequestModel;
import io.swagger.client.model.Currency;
import io.swagger.client.model.InternalTransferRequest;
import io.swagger.client.model.TransactionExternalType;
import io.swagger.client.model.TransactionInternalType;
import io.swagger.client.model.TransactionViewModelItemsViewModel;
import io.swagger.client.model.UserCommissionData;
import io.swagger.client.model.WalletDepositSummary;
import io.swagger.client.model.WalletMultiAvailable;
import io.swagger.client.model.WalletSummary;
import io.swagger.client.model.WithdrawalSummary;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface WalletApi
{
	/**
	 * @param txId (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/withdraw/request/cancel/{txId}")
	Observable<Void> cancelWithdrawalRequest(
			@retrofit2.http.Path("txId") UUID txId
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
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/wallet/withdraw/request/new")
	Observable<Void> createWithdrawalRequest(
			@retrofit2.http.Body CreateWithdrawalRequestModel body
	);

	/**
	 * GenesisMarkets commission data
	 *
	 * @return Call&lt;UserCommissionData&gt;
	 */
	@GET("v2.0/wallet/fee/gvtholding")
	Observable<UserCommissionData> getGMCommissionData();


	/**
	 * External transactions
	 *
	 * @param transactionType (optional)
	 * @param dateFrom        (optional)
	 * @param dateTo          (optional)
	 * @param currency        (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TransactionViewModelItemsViewModel&gt;
	 */
	@GET("v2.0/wallet/transactions/external")
	Observable<TransactionViewModelItemsViewModel> getTransactionsExternal(
			@retrofit2.http.Query("TransactionType") TransactionExternalType transactionType, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Currency") Currency currency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Internal transactions
	 *
	 * @param transactionType (optional)
	 * @param dateFrom        (optional)
	 * @param dateTo          (optional)
	 * @param currency        (optional)
	 * @param skip            (optional)
	 * @param take            (optional)
	 * @return Call&lt;TransactionViewModelItemsViewModel&gt;
	 */
	@GET("v2.0/wallet/transactions/internal")
	Observable<TransactionViewModelItemsViewModel> getTransactionsInternal(
			@retrofit2.http.Query("TransactionType") TransactionInternalType transactionType, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Currency") Currency currency, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * @return Call&lt;WithdrawalSummary&gt;
	 */
	@GET("v2.0/wallet/withdraw/info")
	Observable<WithdrawalSummary> getUserWithdrawalSummary();


	/**
	 * Wallet available
	 *
	 * @param currency (required)
	 * @return Call&lt;WalletMultiAvailable&gt;
	 */
	@GET("v2.0/wallet/{currency}/available")
	Observable<WalletMultiAvailable> getWalletAvailable(
			@retrofit2.http.Path("currency") Currency currency
	);

	/**
	 * Wallet summary
	 *
	 * @param currency (required)
	 * @return Call&lt;WalletSummary&gt;
	 */
	@GET("v2.0/wallet/{currency}")
	Observable<WalletSummary> getWalletSummary(
			@retrofit2.http.Path("currency") Currency currency
	);

	/**
	 * @param txId (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/wallet/withdraw/request/resend/{txId}")
	Observable<Void> resendWithdrawalRequestEmail(
			@retrofit2.http.Path("txId") UUID txId
	);

	/**
	 * Transfer money
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/wallet/transfer")
	Observable<Void> transfer(
			@retrofit2.http.Body InternalTransferRequest body
	);

	/**
	 * Update deposit wallets
	 *
	 * @return Call&lt;WalletDepositSummary&gt;
	 */
	@POST("v2.0/wallet/deposit/update")
	Observable<WalletDepositSummary> updateDepositWallets();


}
