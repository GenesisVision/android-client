package io.swagger.client.api;

import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransactionsViewModel;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface WalletApi
{
	/**
	 * @param filter (optional)
	 * @return Call&lt;WalletTransactionsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/wallet/transactions")
	Observable<WalletTransactionsViewModel> apiInvestorWalletTransactionsPost(
			@retrofit2.http.Body TransactionsFilter filter
	);

	/**
	 * @param filter (optional)
	 * @return Call&lt;WalletTransactionsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/wallet/transactions")
	Observable<WalletTransactionsViewModel> apiManagerWalletTransactionsPost(
			@retrofit2.http.Body TransactionsFilter filter
	);

}
