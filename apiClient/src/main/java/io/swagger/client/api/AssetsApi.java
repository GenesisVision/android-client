package io.swagger.client.api;

import java.util.List;
import java.util.UUID;

import io.swagger.client.model.ChangeBrokerProgramRequest;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.MakeSignalProviderProgram;
import io.swagger.client.model.MakeTradingAccountProgram;
import io.swagger.client.model.MakeTradingAccountSignalProvider;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.ProgramLevelInfo;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TradingAccountCreateResult;
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
	 * Cancel changing broker in existing program
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/programs/{id}/broker/change/cancel")
	Observable<Void> cancelChangeBroker(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Change broker in existing program
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/broker/change")
	Observable<Void> changeBroker(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ChangeBrokerProgramRequest body
	);

	/**
	 * Change trading account password
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/{id}/password/change")
	Observable<Void> changeProgramPassword(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TradingAccountPwdUpdate body
	);

	/**
	 * Close current period
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v2.0/assets/programs/{id}/period/close")
	Observable<Void> closeCurrentPeriod(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Close existing fund
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/{id}/close")
	Observable<Void> closeFund(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Close existing investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/close")
	Observable<Void> closeInvestmentProgram(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Confirm 2FA for program if required (for brokers like Huobi)
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/2fa/confirm")
	Observable<Void> confirmProgram2FA(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body TwoFactorCodeModel body
	);

	/**
	 * Create fund
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/create")
	Observable<Void> createFund(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewFundRequest body
	);

	/**
	 * Create trading account
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;TradingAccountCreateResult&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/tradingaccounts/create")
	Observable<TradingAccountCreateResult> createTradingAccount(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body NewTradingAccountRequest body
	);

	/**
	 * Get program data for levels calculator
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;ProgramLevelInfo&gt;
	 */
	@GET("v2.0/assets/programs/{id}/levels/info")
	Observable<ProgramLevelInfo> getLevelsCalculator(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Get 2FA for program if needed
	 *
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;TwoFactorAuthenticator&gt;
	 */
	@GET("v2.0/assets/programs/{id}/2fa/get")
	Observable<TwoFactorAuthenticator> getProgram2FA(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * Create an investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/fromaccount/create")
	Observable<Void> makeAccountProgram(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body MakeTradingAccountProgram body
	);

	/**
	 * Make account signal provider
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/signal/create")
	Observable<Void> makeAccountSignalProvider(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body MakeTradingAccountSignalProvider body
	);

	/**
	 * Create an investment program
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/fromsignalprovider/create")
	Observable<Void> makeSignalProviderProgram(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body MakeSignalProviderProgram body
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/{id}/update")
	Observable<Void> updateAsset(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ProgramUpdate body
	);

	/**
	 * Update investment program/fund details
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/programs/{id}/update")
	Observable<Void> updateAsset_0(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body ProgramUpdate body
	);

	/**
	 * Update fund assets parts
	 *
	 * @param authorization JWT access token (required)
	 * @param id            (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/funds/{id}/assets/update")
	Observable<Void> updateFundAssets(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Path("id") UUID id, @retrofit2.http.Body List<FundAssetPart> body
	);

	/**
	 * Edit account signal settings
	 *
	 * @param authorization JWT access token (required)
	 * @param body          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("v2.0/assets/signal/edit")
	Observable<Void> updateSignalProviderSettings(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Body CreateSignalProvider body
	);

}
