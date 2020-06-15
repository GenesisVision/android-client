package io.swagger.client.api;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ChangeBrokerProgramRequest;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.MakeSignalProviderProgram;
import io.swagger.client.model.MakeTradingAccountProgram;
import io.swagger.client.model.MakeTradingAccountSignalProvider;
import io.swagger.client.model.NewExchangeAccountRequest;
import io.swagger.client.model.NewExternalTradingAccountRequest;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.ProgramLevelInfo;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.StringItemsViewModel;
import io.swagger.client.model.TradingAccountCreateResult;
import io.swagger.client.model.TradingAccountDemoDeposit;
import io.swagger.client.model.TradingAccountPwdUpdate;
import io.swagger.client.model.TwoFactorAuthenticator;
import io.swagger.client.model.TwoFactorCodeModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface AssetsApi
{
	/**
	 * Add trading account favorite symbol
	 *
	 * @param id     (required)
	 * @param symbol (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/tradingaccounts/{id}/symbol/favorite/{symbol}/add")
	Observable<Void> addFavoriteSymbol(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("symbol") String symbol
	);

	/**
	 * Cancel changing broker in existing program
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/programs/{id}/broker/change/cancel")
	Observable<Void> cancelChangeBroker(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Change broker in existing program
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/broker/change")
	Observable<Void> changeBroker(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ChangeBrokerProgramRequest body
	);

	/**
	 * Change trading account password
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/{id}/password/change")
	Observable<Void> changeTradingAccountPassword(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TradingAccountPwdUpdate body
	);

	/**
	 * Close current period
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/programs/{id}/period/close")
	Observable<Void> closeCurrentPeriod(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Close existing fund
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/{id}/close")
	Observable<Void> closeFund(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Close existing investment program
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/close")
	Observable<Void> closeInvestmentProgram(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Close trading account
	 *
	 * @param id (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/tradingaccounts/{id}/close")
	Observable<Void> closeTradingAccount(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Confirm 2FA for program if required (for brokers like Huobi)
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/2fa/confirm")
	Observable<Void> confirmProgram2FA(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Create exchange account
	 *
	 * @param body (optional)
	 * @return Call&lt;TradingAccountCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/exchange/create")
	Observable<TradingAccountCreateResult> createExchangeAccount(
			@retrofit2.http.Body NewExchangeAccountRequest body
	);

	/**
	 * Create external trading account
	 *
	 * @param body (optional)
	 * @return Call&lt;TradingAccountCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/external/create")
	Observable<TradingAccountCreateResult> createExternalTradingAccount(
			@retrofit2.http.Body NewExternalTradingAccountRequest body
	);

	/**
	 * Create fund
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/create")
	Observable<Void> createFund(
			@retrofit2.http.Body NewFundRequest body
	);

	/**
	 * Create trading account
	 *
	 * @param body (optional)
	 * @return Call&lt;TradingAccountCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/create")
	Observable<TradingAccountCreateResult> createTradingAccount(
			@retrofit2.http.Body NewTradingAccountRequest body
	);

	/**
	 * Get trading account favorite symbols
	 *
	 * @param id (required)
	 * @return Call&lt;StringItemsViewModel&gt;
	 */
	@GET("v2.0/assets/tradingaccounts/{id}/symbol/favorite")
	Observable<StringItemsViewModel> getFavoriteSymbols(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Get program data for levels calculator
	 *
	 * @param id (required)
	 * @return Call&lt;ProgramLevelInfo&gt;
	 */
	@GET("v2.0/assets/programs/{id}/levels/info")
	Observable<ProgramLevelInfo> getLevelsCalculator(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Get 2FA for program if needed
	 *
	 * @param id (required)
	 * @return Call&lt;TwoFactorAuthenticator&gt;
	 */
	@GET("v2.0/assets/programs/{id}/2fa/get")
	Observable<TwoFactorAuthenticator> getProgram2FA(
			@retrofit2.http.Path("id") UUID id
	);

	/**
	 * Create an investment program
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/fromaccount/create")
	Observable<Void> makeAccountProgram(
			@retrofit2.http.Body MakeTradingAccountProgram body
	);

	/**
	 * Make account signal provider
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/signal/create")
	Observable<Void> makeAccountSignalProvider(
			@retrofit2.http.Body MakeTradingAccountSignalProvider body
	);

	/**
	 * Make demo trading account deposit
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/{id}/demo/deposit")
	Observable<Void> makeDemoTradingAccountDeposit(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TradingAccountDemoDeposit body
	);

	/**
	 * Make external trading account signal provider
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/external/fromaccount/create")
	Observable<Void> makeExternalAccountSignalProvider(
			@retrofit2.http.Body MakeTradingAccountSignalProvider body
	);

	/**
	 * Create an investment program
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/fromsignalprovider/create")
	Observable<Void> makeSignalProviderProgram(
			@retrofit2.http.Body MakeSignalProviderProgram body
	);

	/**
	 * Remove trading account favorite symbol
	 *
	 * @param id     (required)
	 * @param symbol (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/tradingaccounts/{id}/symbol/favorite/{symbol}/remove")
	Observable<Void> removeFavoriteSymbol(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("symbol") String symbol
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/follow/{id}/update")
	Observable<Void> updateAsset(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ProgramUpdate body
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/{id}/update")
	Observable<Void> updateAsset_0(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ProgramUpdate body
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/update")
	Observable<Void> updateAsset_1(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ProgramUpdate body
	);

	/**
	 * Update fund assets parts
	 *
	 * @param id   (required)
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/{id}/assets/update")
	Observable<Void> updateFundAssets(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Body List<FundAssetPart> body
	);

	/**
	 * Edit account signal settings
	 *
	 * @param body (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/signal/edit")
	Observable<Void> updateSignalProviderSettings(
			@retrofit2.http.Body CreateSignalProvider body
	);

}
