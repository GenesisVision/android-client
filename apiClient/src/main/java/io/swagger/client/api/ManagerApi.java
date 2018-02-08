package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.BrokersFilter;
import io.swagger.client.model.BrokersViewModel;
import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.NewInvestmentRequest;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfilePublicViewModel;
import io.swagger.client.model.ProfileShortViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.WalletTransactionsViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ManagerApi
{
	/**
	 * Create new investment request
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;UUID&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/account/newInvestmentRequest")
	Observable<UUID> apiManagerAccountNewInvestmentRequestPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewInvestmentRequest request
	);

	/**
	 * Confirm email after registration
	 *
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/manager/auth/confirmEmail")
	Observable<Void> apiManagerAuthConfirmEmailGet(
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
	@POST("api/manager/auth/signIn")
	Observable<String> apiManagerAuthSignInPost(
			@retrofit2.http.Body LoginViewModel model
	);

	/**
	 * Register new manager
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/auth/signUp")
	Observable<Void> apiManagerAuthSignUpPost(
			@retrofit2.http.Body RegisterManagerViewModel model
	);

	/**
	 * Update auth token
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;String&gt;
	 */
	@GET("api/manager/auth/updateToken")
	Observable<String> apiManagerAuthUpdateTokenGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get all enabled trade servers
	 *
	 * @param filter (optional)
	 * @return Call&lt;BrokersViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/brokers")
	Observable<BrokersViewModel> apiManagerBrokersPost(
			@retrofit2.http.Body BrokersFilter filter
	);

	/**
	 * Close existing investment program
	 *
	 * @param investmentProgramId (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/manager/investment/close")
	Observable<Void> apiManagerInvestmentCloseGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investment program with statistic by id
	 *
	 * @param investmentProgramId (required)
	 * @return Call&lt;InvestmentProgramViewModel&gt;
	 */
	@GET("api/manager/investment")
	Observable<InvestmentProgramViewModel> apiManagerInvestmentGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId
	);

	/**
	 * Get full profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileFullViewModel&gt;
	 */
	@GET("api/manager/profile/full")
	Observable<ProfileFullViewModel> apiManagerProfileFullGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get short profile
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProfileShortViewModel&gt;
	 */
	@GET("api/manager/profile")
	Observable<ProfileShortViewModel> apiManagerProfileGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get public profile
	 *
	 * @param userId (required)
	 * @return Call&lt;ProfilePublicViewModel&gt;
	 */
	@GET("api/manager/profile/public")
	Observable<ProfilePublicViewModel> apiManagerProfilePublicGet(
			@retrofit2.http.Query("userId") UUID userId
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
	@POST("api/manager/profile/update")
	Observable<Void> apiManagerProfileUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateProfileViewModel model
	);

	/**
	 * Deposit
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/manager/wallet/deposit")
	Observable<Void> apiManagerWalletDepositPost(
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
	@POST("api/manager/wallet/transactions")
	Observable<WalletTransactionsViewModel> apiManagerWalletTransactionsPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TransactionsFilter filter
	);

	/**
	 * Withdraw
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/manager/wallet/withdraw")
	Observable<Void> apiManagerWalletWithdrawPost(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
