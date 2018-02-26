package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.Invest;
import io.swagger.client.model.InvestmentProgramsViewModel;
import io.swagger.client.model.InvestmentsFilter;
import io.swagger.client.model.InvestorDashboard;
import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfilePublicViewModel;
import io.swagger.client.model.ProfileShortViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.UpdateProfileViewModel;
import io.swagger.client.model.WalletAddressViewModel;
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
	 * Cancel investment request
	 *
	 * @param requestId     (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("api/investor/investments/cancelInvestmentRequest")
	Observable<Void> apiInvestorInvestmentsCancelInvestmentRequestPost(
			@retrofit2.http.Query("requestId") UUID requestId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Invest in manager
	 *
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
	 * Withdraw from investment program
	 *
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

}
