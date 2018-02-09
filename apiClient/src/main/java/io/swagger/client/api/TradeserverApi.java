package io.swagger.client.api;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.AccountCreated;
import io.swagger.client.model.NewTrade;
import io.swagger.client.model.TradeServerViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface TradeserverApi
{
	/**
	 * Init data for trade server wrapper
	 *
	 * @param tradeServerId (required)
	 * @return Call&lt;TradeServerViewModel&gt;
	 */
	@GET("api/tradeserver/initData")
	Observable<TradeServerViewModel> apiTradeserverInitDataGet(
			@retrofit2.http.Query("tradeServerId") UUID tradeServerId
	);

	/**
	 * New trade event
	 *
	 * @param trade (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/tradeserver/newTrade")
	Observable<Void> apiTradeserverNewTradePost(
			@retrofit2.http.Body NewTrade trade
	);

	/**
	 * Store trade account
	 *
	 * @param accounts (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/tradeserver/tradeAccountCreated")
	Observable<Void> apiTradeserverTradeAccountCreatedPost(
			@retrofit2.http.Body List<AccountCreated> accounts
	);

}
