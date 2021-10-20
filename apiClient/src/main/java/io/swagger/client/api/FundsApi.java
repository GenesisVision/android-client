package io.swagger.client.api;//retrofit2

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AbsoluteProfitChart;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FundBalanceChart;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.FundDetailsListItem;
import io.swagger.client.model.FundDetailsListItemItemsViewModel;
import io.swagger.client.model.FundHistoryEventFilterType;
import io.swagger.client.model.FundHistoryEventViewModelItemsViewModel;
import io.swagger.client.model.FundProfitPercentCharts;
import io.swagger.client.model.FundsFilterSorting;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.ReallocationModelItemsViewModel;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface FundsApi {
  /**
   * Add to favorites
   * 
   * @param id  (required)
   * @return Call&lt;Void&gt;
   */
  @POST("v2.0/funds/{id}/favorite/add")
  Observable<Void> addToFavorites(
            @retrofit2.http.Path("id") UUID id            
  );

  /**
   * Fund absolute profit chart
   * 
   * @param id  (required)
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param maxPointCount  (optional)
   * @param currency  (optional)
   * @return Call&lt;AbsoluteProfitChart&gt;
   */
  @GET("v2.0/funds/{id}/charts/profit/absolute")
  Observable<AbsoluteProfitChart> getFundAbsoluteProfitChart(
            @retrofit2.http.Path("id") UUID id            ,     @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("MaxPointCount") Integer maxPointCount                ,     @retrofit2.http.Query("Currency") Currency currency                
  );

  /**
   * Fund balance chart
   * 
   * @param id  (required)
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param maxPointCount  (optional)
   * @param currency  (optional)
   * @return Call&lt;FundBalanceChart&gt;
   */
  @GET("v2.0/funds/{id}/charts/balance")
  Observable<FundBalanceChart> getFundBalanceChart(
            @retrofit2.http.Path("id") UUID id            ,     @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("MaxPointCount") Integer maxPointCount                ,     @retrofit2.http.Query("Currency") Currency currency                
  );

  /**
   * Fund details
   * 
   * @param id  (required)
   * @param currency  (optional)
   * @param logoQuality  (optional)
   * @return Call&lt;FundDetailsFull&gt;
   */
  @GET("v2.0/funds/{id}")
  Observable<FundDetailsFull> getFundDetails(
            @retrofit2.http.Path("id") String id            ,     @retrofit2.http.Query("currency") Currency currency                ,     @retrofit2.http.Query("logoQuality") ImageQuality logoQuality                
  );

  /**
   * Fund profit percent charts
   * 
   * @param id  (required)
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param maxPointCount  (optional)
   * @param currency  (optional)
   * @param currencies  (optional)
   * @param chartAssetsCount  (optional, default to 3)
   * @return Call&lt;FundProfitPercentCharts&gt;
   */
  @GET("v2.0/funds/{id}/charts/profit/percent")
  Observable<FundProfitPercentCharts> getFundProfitPercentCharts(
            @retrofit2.http.Path("id") UUID id            ,     @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("MaxPointCount") Integer maxPointCount                ,     @retrofit2.http.Query("Currency") Currency currency                ,     @retrofit2.http.Query("currencies") List<Currency> currencies                ,     @retrofit2.http.Query("chartAssetsCount") Integer chartAssetsCount                
  );

  /**
   * Funds list
   * 
   * @param sorting  (optional)
   * @param showIn  (optional)
   * @param assets  (optional)
   * @param investorId  (optional)
   * @param includeWithInvestments  (optional)
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param chartPointsCount  (optional)
   * @param facetId  (optional)
   * @param mask  (optional)
   * @param ownerId  (optional)
   * @param showFavorites  (optional)
   * @param skipStatistic  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;FundDetailsListItemItemsViewModel&gt;
   */
  @GET("v2.0/funds")
  Observable<FundDetailsListItemItemsViewModel> getFunds(
        @retrofit2.http.Query("Sorting") FundsFilterSorting sorting                ,     @retrofit2.http.Query("ShowIn") Currency showIn                ,     @retrofit2.http.Query("Assets") List<String> assets                ,     @retrofit2.http.Query("InvestorId") UUID investorId                ,     @retrofit2.http.Query("IncludeWithInvestments") Boolean includeWithInvestments                ,     @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("ChartPointsCount") Integer chartPointsCount                ,     @retrofit2.http.Query("FacetId") String facetId                ,     @retrofit2.http.Query("Mask") String mask                ,     @retrofit2.http.Query("OwnerId") UUID ownerId                ,     @retrofit2.http.Query("ShowFavorites") Boolean showFavorites                ,     @retrofit2.http.Query("SkipStatistic") Boolean skipStatistic                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

  /**
   * Get funds trading events
   * 
   * @param id  (required)
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param eventsType  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;FundHistoryEventViewModelItemsViewModel&gt;
   */
  @GET("v2.0/funds/{id}/history")
  Observable<FundHistoryEventViewModelItemsViewModel> getFundsHistoryEvents(
            @retrofit2.http.Path("id") UUID id            ,     @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("EventsType") FundHistoryEventFilterType eventsType                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

  /**
   * Get last weekly funds challenge winner
   * 
   * @param chartPointsCount  (optional)
   * @return Call&lt;FundDetailsListItem&gt;
   */
  @GET("v2.0/funds/challenge/winner")
  Observable<FundDetailsListItem> getLastChallengeWinner(
        @retrofit2.http.Query("chartPointsCount") Integer chartPointsCount                
  );

  /**
   * Get history of asset part update requests
   * 
   * @param id  (required)
   * @param dateFrom  (optional)
   * @param dateTo  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;ReallocationModelItemsViewModel&gt;
   */
  @GET("v2.0/funds/{id}/reallocations")
  Observable<ReallocationModelItemsViewModel> getReallocatingHistory(
            @retrofit2.http.Path("id") UUID id            ,     @retrofit2.http.Query("DateFrom") DateTime dateFrom                ,     @retrofit2.http.Query("DateTo") DateTime dateTo                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

  /**
   * Remove from favorites
   * 
   * @param id  (required)
   * @return Call&lt;Void&gt;
   */
  @POST("v2.0/funds/{id}/favorite/remove")
  Observable<Void> removeFromFavorites(
            @retrofit2.http.Path("id") UUID id            
  );

}
