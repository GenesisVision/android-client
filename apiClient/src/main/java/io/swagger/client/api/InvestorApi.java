package io.swagger.client.api;

import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfileShortViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransactionsViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * Confirm email after registration
	 *
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/investor/auth/confirmEmail")
	Observable<Void> apiInvestorAuthConfirmEmailGet(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
	 * Authorize
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/auth/signIn")
	Observable<String> apiInvestorAuthSignInPost(
			@retrofit2.http.Body LoginViewModel model
	);

	/**
	 * Register new investor
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/auth/signUp")
	Observable<Void> apiInvestorAuthSignUpPost(
			@retrofit2.http.Body RegisterInvestorViewModel model
	);

	/**
	 * Update auth token
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@GET("api/investor/auth/updateToken")
	Observable<String> apiInvestorAuthUpdateTokenGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investor dashboard
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;InvestorDashboard&gt;
	 */
	@GET("api/investor/dashboard")
	Observable<InvestorDashboard> apiInvestorDashboardGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;ProfileShortViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments/invest")
	Observable<ProfileShortViewModel> apiInvestorInvestmentsInvestPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body Invest model
	);

	/**
	 * Get investments by filter
	 *
	 * @param filter (optional)
	 * @return Call&lt;InvestmentProgramsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments")
	Observable<InvestmentProgramsViewModel> apiInvestorInvestmentsPost(
			@retrofit2.http.Body InvestmentsFilter filter
	);

	/**
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investments/withdraw")
	Observable<Void> apiInvestorInvestmentsWithdrawPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body Invest model
	);

	/**
	 * Get full profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileFullViewModel&gt;
	 */
	@GET("api/investor/profile/full")
	Observable<ProfileFullViewModel> apiInvestorProfileFullGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get short profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileShortViewModel&gt;
	 */
	@GET("api/investor/profile")
	Observable<ProfileShortViewModel> apiInvestorProfileGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Update profile
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/profile/update")
	Observable<Void> apiInvestorProfileUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ProfileFullViewModel model
	);

	/**
	 * Deposit
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/investor/wallet/deposit")
	Observable<Void> apiInvestorWalletDepositPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get user wallet transactions
	 *
	 * @param authorization JWT access token (required)
	 * @param filter        (optional)
	 * @return Call&lt;WalletTransactionsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/wallet/transactions")
	Observable<WalletTransactionsViewModel> apiInvestorWalletTransactionsPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TransactionsFilter filter
	);

	/**
	 * Withdraw
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/investor/wallet/withdraw")
	Observable<Void> apiInvestorWalletWithdrawPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
