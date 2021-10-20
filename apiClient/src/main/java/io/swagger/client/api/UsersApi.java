package io.swagger.client.api;//retrofit2

import java.util.List;

import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.PublicProfile;
import io.swagger.client.model.PublicProfileFollow;
import io.swagger.client.model.UserDetailsListItemsViewModel;
import io.swagger.client.model.UsersFilterSorting;
import io.swagger.client.model.UsersFilterTimeframe;
import retrofit2.http.GET;
import rx.Observable;

public interface UsersApi {
  /**
   * Public profile
   * 
   * @param id  (required)
   * @param logoQuality  (optional)
   * @return Call&lt;PublicProfile&gt;
   */
  @GET("v2.0/users/{id}")
  Observable<PublicProfile> getUserProfile(
            @retrofit2.http.Path("id") String id            ,     @retrofit2.http.Query("logoQuality") ImageQuality logoQuality                
  );

  /**
   * Public profile follow details
   * 
   * @param id  (required)
   * @return Call&lt;PublicProfileFollow&gt;
   */
  @GET("v2.0/users/{id}/follow")
  Observable<PublicProfileFollow> getUserProfileFollowDetails(
            @retrofit2.http.Path("id") String id            
  );

  /**
   * Get users list
   * 
   * @param sorting  (optional)
   * @param timeframe  (optional)
   * @param tags  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;UserDetailsListItemsViewModel&gt;
   */
  @GET("v2.0/users")
  Observable<UserDetailsListItemsViewModel> getUsersList(
        @retrofit2.http.Query("Sorting") UsersFilterSorting sorting                ,     @retrofit2.http.Query("Timeframe") UsersFilterTimeframe timeframe                ,     @retrofit2.http.Query("Tags") List<String> tags                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

}
