package io.swagger.client.api;

import io.swagger.client.model.LoginViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.ProfileShortViewModel;
import io.swagger.client.model.RegisterInvestorViewModel;
import io.swagger.client.model.RegisterManagerViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface AccountApi
{
	/**
	 * @param model (optional)
	 * @return Call&lt;String&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/broker/auth/signIn")
	Observable<String> apiBrokerAuthSignInPost(
			@retrofit2.http.Body LoginViewModel model
	);

	/**
	 * @return Call&lt;String&gt;
	 */
	@GET("api/broker/auth/updateToken")
	Observable<String> apiBrokerAuthUpdateTokenGet();


	/**
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/investor/auth/confirmEmail")
	Observable<Void> apiInvestorAuthConfirmEmailGet(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
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
	 * @return Call&lt;String&gt;
	 */
	@GET("api/investor/auth/updateToken")
	Observable<String> apiInvestorAuthUpdateTokenGet();


	/**
	 * @return Call&lt;ProfileFullViewModel&gt;
	 */
	@GET("api/investor/profile/full")
	Observable<ProfileFullViewModel> apiInvestorProfileFullGet();


	/**
	 * @return Call&lt;ProfileShortViewModel&gt;
	 */
	@GET("api/investor/profile")
	Observable<ProfileShortViewModel> apiInvestorProfileGet();


	/**
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/investor/profile/update")
	Observable<Void> apiInvestorProfileUpdatePost(
			@retrofit2.http.Body ProfileFullViewModel model
	);

	/**
	 * @param userId (optional)
	 * @param code   (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("api/manager/auth/confirmEmail")
	Observable<Void> apiManagerAuthConfirmEmailGet(
			@retrofit2.http.Query("userId") String userId, @retrofit2.http.Query("code") String code
	);

	/**
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
	 * @return Call&lt;String&gt;
	 */
	@GET("api/manager/auth/updateToken")
	Observable<String> apiManagerAuthUpdateTokenGet();


	/**
	 * @return Call&lt;ProfileFullViewModel&gt;
	 */
	@GET("api/manager/profile/full")
	Observable<ProfileFullViewModel> apiManagerProfileFullGet();


	/**
	 * @return Call&lt;ProfileShortViewModel&gt;
	 */
	@GET("api/manager/profile")
	Observable<ProfileShortViewModel> apiManagerProfileGet();


	/**
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/manager/profile/update")
	Observable<Void> apiManagerProfileUpdatePost(
			@retrofit2.http.Body ProfileFullViewModel model
	);

}
