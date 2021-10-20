package io.swagger.client.api;//retrofit2

import java.util.UUID;

import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.Currency;
import io.swagger.client.model.DetachFromExternalSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.SignalTradingEventItemsViewModel;
import io.swagger.client.model.TradingAccountDetailsItemsViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface SignalApi {
  /**
   * Subscribe to external signal account
   * 
   * @param id  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v2.0/signal/external/attach/{id}/private")
  Observable<Void> attachSlaveToMasterExternalPrivateAccount(
            @retrofit2.http.Path("id") UUID id            ,                 @retrofit2.http.Body AttachToExternalSignalProviderExt body    
  );

  /**
   * Subscribe to signal provider
   * 
   * @param id  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v2.0/signal/attach/{id}")
  Observable<Void> attachSlaveToMasterInternal(
            @retrofit2.http.Path("id") UUID id            ,                 @retrofit2.http.Body AttachToSignalProvider body    
  );

  /**
   * 
   * 
   * @param id  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v2.0/signal/external/detach/{id}")
  Observable<Void> detachSlaveFromMasterExternal(
            @retrofit2.http.Path("id") UUID id            ,                 @retrofit2.http.Body DetachFromExternalSignalProvider body    
  );

  /**
   * Unsubscribe from signal provider
   * 
   * @param id  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v2.0/signal/detach/{id}")
  Observable<Void> detachSlaveFromMasterInternal(
            @retrofit2.http.Path("id") UUID id            ,                 @retrofit2.http.Body DetachFromSignalProvider body    
  );

  /**
   * Get investors signals trading log
   * 
   * @param accountId  (optional)
   * @param accountCurrency  (optional)
   * @param skip  (optional)
   * @param take  (optional)
   * @return Call&lt;SignalTradingEventItemsViewModel&gt;
   */
  @GET("v2.0/signal/trades/log")
  Observable<SignalTradingEventItemsViewModel> getSignalTradingLog(
        @retrofit2.http.Query("AccountId") UUID accountId                ,     @retrofit2.http.Query("AccountCurrency") Currency accountCurrency                ,     @retrofit2.http.Query("Skip") Integer skip                ,     @retrofit2.http.Query("Take") Integer take                
  );

  /**
   * Get subscriber accounts for subscribe to signal provider (common method for all signals)
   * 
   * @param id  (required)
   * @return Call&lt;TradingAccountDetailsItemsViewModel&gt;
   */
  @GET("v2.0/signal/attach/{id}/accounts")
  Observable<TradingAccountDetailsItemsViewModel> getSubscriberAccountsForAsset(
            @retrofit2.http.Path("id") UUID id            
  );

  /**
   * Update signal subscription settings
   * 
   * @param id  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v2.0/signal/external/{id}/update")
  Observable<Void> updateExternalSubscriptionSettings(
            @retrofit2.http.Path("id") UUID id            ,                 @retrofit2.http.Body AttachToExternalSignalProviderExt body    
  );

  /**
   * Update signal subscription settings
   * 
   * @param id  (required)
   * @param body  (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v2.0/signal/{id}/update")
  Observable<Void> updateSubscriptionSettings(
            @retrofit2.http.Path("id") UUID id            ,                 @retrofit2.http.Body AttachToSignalProvider body    
  );

}
