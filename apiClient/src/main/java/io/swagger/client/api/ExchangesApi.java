package io.swagger.client.api;//retrofit2

import io.swagger.client.model.ExchangeInfoItemsViewModel;
import retrofit2.http.GET;
import rx.Observable;

public interface ExchangesApi {
  /**
   * Get exchanges for creating trading accounts
   * 
   * @return Call&lt;ExchangeInfoItemsViewModel&gt;
   */
  @GET("v2.0/exchanges")
  Observable<ExchangeInfoItemsViewModel> getExchanges();
    

}
