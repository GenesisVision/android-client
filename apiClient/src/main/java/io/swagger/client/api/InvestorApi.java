package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.ChangePasswordViewModel;
import io.swagger.client.model.ForgotPasswordViewModel;
import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramBuyToken;
import io.swagger.client.model.InvestmentProgramRequests;
import io.swagger.client.model.InvestmentProgramRequestsFilter;
import io.swagger.client.model.InvestmentProgramViewModel;
import io.swagger.client.model.InvestmentProgramsFilter;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.OpenTradesViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfilePublicViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.ResetPasswordViewModel;
import io.swagger.client.model.TradesChartViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TradesViewModel;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.WalletAddressViewModel;
import io.swagger.client.model.WalletInvestmentPrograms;
import io.swagger.client.model.WalletStatistic;
import io.swagger.client.model.WalletStatisticFilter;
import io.swagger.client.model.WalletTransactionsViewModel;
import io.swagger.client.model.WalletWithdrawRequestModel;
import io.swagger.client.model.WalletsViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface InvestorApi
{
	/**
	 * Change password
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/auth/changePassword")
	Observable<Void> apiInvestorAuthChangePasswordPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body ChangePasswordViewModel model
	);

	/**
	 * Confirm email after registration
	 *
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;String&gt;
	 */
	@POST("api/investor/auth/confirmEmail")
	Observable<String> apiInvestorAuthConfirmEmailPost(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
	 * Forgot password investor
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/auth/forgotPassword")
	Observable<Void> apiInvestorAuthForgotPasswordPost(
			@retrofit2.http.Body ForgotPasswordViewModel model
	);

	/**
	 * Reset password
	 *
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/auth/resetPassword")
	Observable<String> apiInvestorAuthResetPasswordPost(
			@retrofit2.http.Body ResetPasswordViewModel model
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
	 * @param sorting       (optional)
	 * @return Call&lt;InvestorDashboard&gt;
	 */
	@GET("api/investor/dashboard")
	Observable<InvestorDashboard> apiInvestorDashboardGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("Sorting") String sorting
	);

	/**
	 * Get investment program buy token model
	 *
	 * @param investmentProgramId (required)
	 * @param authorization       JWT access token (required)
	 * @return Call&lt;InvestmentProgramBuyToken&gt;
	 */
	@GET("api/investor/investmentProgram/buyTokens")
	Observable<InvestmentProgramBuyToken> apiInvestorInvestmentProgramBuyTokensGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get investment program details by id
	 *
	 * @param investmentProgramId (required)
	 * @param authorization       (optional)
	 * @return Call&lt;InvestmentProgramViewModel&gt;
	 */
	@GET("api/investor/investmentProgram")
	Observable<InvestmentProgramViewModel> apiInvestorInvestmentProgramGet(
			@retrofit2.http.Query("investmentProgramId") UUID investmentProgramId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get manager open trades
	 *
	 * @param authorization JWT access token (required)
	 * @param filter        (optional)
	 * @return Call&lt;OpenTradesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investmentProgram/openTrades")
	Observable<OpenTradesViewModel> apiInvestorInvestmentProgramOpenTradesPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body TradesFilter filter
	);

	/**
	 * Get investment program&#39;s requests
	 *
	 * @param authorization JWT access token (required)
	 * @param filter        (optional)
	 * @return Call&lt;InvestmentProgramRequests&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investmentProgram/requests")
	Observable<InvestmentProgramRequests> apiInvestorInvestmentProgramRequestsPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body InvestmentProgramRequestsFilter filter
	);

	/**
	 * Get manager trades chart
	 *
	 * @param investmentProgramId (required)
	 * @return Call&lt;TradesChartViewModel&gt;
	 */
	@GET("api/investor/investmentProgram/trades/chart")
	Observable<TradesChartViewModel> apiInvestorInvestmentProgramTradesChartGet(
			@retrofit2.http.Query("InvestmentProgramId") UUID investmentProgramId
	);

	/**
	 * Get manager trade history
	 *
	 * @param filter (optional)
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investmentProgram/trades")
	Observable<TradesViewModel> apiInvestorInvestmentProgramTradesPost(
			@retrofit2.http.Body TradesFilter filter
	);

	/**
	 * Cancel investment request
	 *
	 * @param requestId     (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/investor/investmentPrograms/cancelInvestmentRequest")
	Observable<Void> apiInvestorInvestmentProgramsCancelInvestmentRequestPost(
			@retrofit2.http.Query("requestId") UUID requestId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Invest in manager
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;WalletsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investmentPrograms/invest")
	Observable<WalletsViewModel> apiInvestorInvestmentProgramsInvestPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body Invest model
	);

	/**
	 * Get public investment program&#39;s list
	 *
	 * @param authorization (optional)
	 * @param filter        (optional)
	 * @return Call&lt;InvestmentProgramsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investmentPrograms")
	Observable<InvestmentProgramsViewModel> apiInvestorInvestmentProgramsPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body InvestmentProgramsFilter filter
	);

	/**
	 * Withdraw from investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/investmentPrograms/withdraw")
	Observable<Void> apiInvestorInvestmentProgramsWithdrawPost(
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
	 * Get public profile
	 *
	 * @param userId (required)
	 * @return Call&lt;ProfilePublicViewModel&gt;
	 */
	@GET("api/investor/profile/public")
	Observable<ProfilePublicViewModel> apiInvestorProfilePublicGet(
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
	@POST("api/investor/profile/update")
	Observable<Void> apiInvestorProfileUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body UpdateProfileViewModel model
	);

	/**
	 * Get eth address for GVT depositing
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletAddressViewModel&gt;
	 */
	@GET("api/investor/wallet/address")
	Observable<WalletAddressViewModel> apiInvestorWalletAddressGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get user wallets
	 *
	 * @param authorization JWT access token (required)
	 * @return Call&lt;WalletsViewModel&gt;
	 */
	@GET("api/investor/wallet")
	Observable<WalletsViewModel> apiInvestorWalletGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get user wallet statistic
	 *
	 * @param authorization JWT access token (required)
	 * @param filter        (optional)
	 * @return Call&lt;WalletStatistic&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/wallet/statistic")
	Observable<WalletStatistic> apiInvestorWalletStatisticPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body WalletStatisticFilter filter
	);

	/**
	 * Get user investment programs with tx
	 *
	 * @param authorization JWT access token (required)
	 * @param mask          (optional)
	 * @return Call&lt;WalletInvestmentPrograms&gt;
	 */
	@GET("api/investor/wallet/transactions/investmentProgramsList")
	Observable<WalletInvestmentPrograms> apiInvestorWalletTransactionsInvestmentProgramsListGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("mask") String mask
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
	 * Withdraw request
	 *
	 * @param authorization JWT access token (required)
	 * @param request       (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/wallet/withdrawRequest")
	Observable<Void> apiInvestorWalletWithdrawRequestPost(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body WalletWithdrawRequestModel request
	);

}
