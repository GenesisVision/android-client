package io.swagger.client.api;

import org.joda.time.DateTime;

import io.swagger.client.model.ItemsViewModelReferralFriend;
import io.swagger.client.model.ItemsViewModelRewardDetails;
import io.swagger.client.model.PartnershipDetails;
import retrofit2.http.GET;
import rx.Observable;

public interface PartnershipApi
{
	/**
	 * Export rewards history.
	 *
	 * @param authorization JWT access token (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;String&gt;
	 */
	@GET("v2.0/partnership/rewards/history/export")
	Observable<String> exportHistory(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get partnership details.
	 *
	 * @param authorization JWT access token (required)
	 * @param currency      (optional)
	 * @return Call&lt;PartnershipDetails&gt;
	 */
	@GET("v2.0/partnership/details")
	Observable<PartnershipDetails> getDetails(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("currency") String currency
	);

	/**
	 * Get agent friends (referrals and second level referrals).
	 *
	 * @param authorization JWT access token (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ItemsViewModelReferralFriend&gt;
	 */
	@GET("v2.0/partnership/referrals")
	Observable<ItemsViewModelReferralFriend> getReferrals(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

	/**
	 * Get history of agent rewards.
	 *
	 * @param authorization JWT access token (required)
	 * @param dateFrom      (optional)
	 * @param dateTo        (optional)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;ItemsViewModelRewardDetails&gt;
	 */
	@GET("v2.0/partnership/rewards/history")
	Observable<ItemsViewModelRewardDetails> getRewardsHistory(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("DateFrom") DateTime dateFrom, @retrofit2.http.Query("DateTo") DateTime dateTo, @retrofit2.http.Query("Skip") Integer skip, @retrofit2.http.Query("Take") Integer take
	);

}
