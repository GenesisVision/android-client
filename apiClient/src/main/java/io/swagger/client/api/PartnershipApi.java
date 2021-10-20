package io.swagger.client.api;//retrofit2

import org.joda.time.DateTime;

import io.swagger.client.model.Currency;
import io.swagger.client.model.PartnershipDetails;
import io.swagger.client.model.ReferralFriendItemsViewModel;
import io.swagger.client.model.RewardDetailsItemsViewModel;
import retrofit2.http.GET;
import rx.Observable;

public interface PartnershipApi {
  /**
   * Export rewards history.
   * 
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;byte[]&gt;
   */
  @GET("v2.0/partnership/rewards/history/export")
  Observable<byte[]> exportHistory(
        @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

  /**
   * Get partnership details.
   * 
   * @param currency  (optional)
   * @return Call&lt;PartnershipDetails&gt;
   */
  @GET("v2.0/partnership/details")
  Observable<PartnershipDetails> getDetails(
        @retrofit2.http.Query("currency") Currency currency                
  );

  /**
   * Get agent friends (referrals and second level referrals).
   * 
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;ReferralFriendItemsViewModel&gt;
   */
  @GET("v2.0/partnership/referrals")
  Observable<ReferralFriendItemsViewModel> getReferrals(
        @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

  /**
   * Get history of agent rewards.
   * 
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;RewardDetailsItemsViewModel&gt;
   */
  @GET("v2.0/partnership/rewards/history")
  Observable<RewardDetailsItemsViewModel> getRewardsHistory(
        @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

}
