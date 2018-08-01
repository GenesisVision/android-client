package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.InvestmentProgramAccrual;
import io.swagger.client.model.ManagerAccountOnlineInfo;
import io.swagger.client.model.ManagerHistoryIpfsHash;
import io.swagger.client.model.NewManager;
import io.swagger.client.model.NewTradeEvent;
import io.swagger.client.model.StartValues;
import io.swagger.client.model.UsersRealAssets;

/**
 * API tests for BrokerApi
 */
public class BrokerApiTest
{

	private BrokerApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(BrokerApi.class);
	}

	/**
	 * Create manager
	 */
	@Test
	public void v10BrokerAccountCreatePostTest() {
		String authorization = null;
		NewManager request = null;
		// UUID response = api.v10BrokerAccountCreatePost(authorization, request);

		// TODO: test validations
	}

	/**
	 * Create OEF manager
	 */
	@Test
	public void v10BrokerAccountOefCreatePostTest() {
		String authorization = null;
		UUID requestId = null;
		// UUID response = api.v10BrokerAccountOefCreatePost(authorization, requestId);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10BrokerFreeaddressesGetTest() {
		String authorization = null;
		// List<String> response = api.v10BrokerFreeaddressesGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get broker initial data
	 */
	@Test
	public void v10BrokerInitDataGetTest() {
		String authorization = null;
		UUID brokerTradeServerId = null;
		// BrokerInitData response = api.v10BrokerInitDataGet(authorization, brokerTradeServerId);

		// TODO: test validations
	}

	/**
	 * Upload accounts online info
	 */
	@Test
	public void v10BrokerManagersAccountsOnlineInfoUpdatePostTest() {
		String authorization = null;
		List<ManagerAccountOnlineInfo> accounts = null;
		// Void response = api.v10BrokerManagersAccountsOnlineInfoUpdatePost(authorization, accounts);

		// TODO: test validations
	}

	/**
	 * Get all supported assets for OEFs
	 */
	@Test
	public void v10BrokerOefAssetAllGetTest() {
		String authorization = null;
		// PlatformAssets response = api.v10BrokerOefAssetAllGet(authorization);

		// TODO: test validations
	}

	/**
	 * Get real assets distribution for broker&#39;s clients
	 */
	@Test
	public void v10BrokerOefGetRealAssetsGetTest() {
		String authorization = null;
		UUID brokerTradeServerId = null;
		// UsersRealAssets response = api.v10BrokerOefGetRealAssetsGet(authorization, brokerTradeServerId);

		// TODO: test validations
	}

	/**
	 * Get broker OEF initial data
	 */
	@Test
	public void v10BrokerOefInitDataGetTest() {
		String authorization = null;
		UUID brokerTradeServerId = null;
		// BrokerOefInitData response = api.v10BrokerOefInitDataGet(authorization, brokerTradeServerId);

		// TODO: test validations
	}

	/**
	 * Update real assets distribution
	 */
	@Test
	public void v10BrokerOefSetRealAssetsPostTest() {
		String authorization = null;
		UUID brokerTradeServerId = null;
		UsersRealAssets assets = null;
		// Void response = api.v10BrokerOefSetRealAssetsPost(authorization, brokerTradeServerId, assets);

		// TODO: test validations
	}

	/**
	 * Accrue investors&#39; profits
	 */
	@Test
	public void v10BrokerPeriodAccrueProfitsPostTest() {
		String authorization = null;
		InvestmentProgramAccrual accrual = null;
		// Void response = api.v10BrokerPeriodAccrueProfitsPost(authorization, accrual);

		// TODO: test validations
	}

	/**
	 * Close investment period
	 */
	@Test
	public void v10BrokerPeriodClosePostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		Double currentBalance = null;
		// Void response = api.v10BrokerPeriodClosePost(authorization, investmentProgramId, currentBalance);

		// TODO: test validations
	}

	/**
	 * Apply new requested assets parts
	 */
	@Test
	public void v10BrokerPeriodOefApplyAssetPartsPostTest() {
		String authorization = null;
		UUID assetsRequestId = null;
		// Void response = api.v10BrokerPeriodOefApplyAssetPartsPost(authorization, assetsRequestId);

		// TODO: test validations
	}

	/**
	 * Close OEF investment program
	 */
	@Test
	public void v10BrokerPeriodOefProcessClosingProgramPostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		Double managerBalance = null;
		// Void response = api.v10BrokerPeriodOefProcessClosingProgramPost(authorization, investmentProgramId, managerBalance);

		// TODO: test validations
	}

	/**
	 * Force close OEF investment program
	 */
	@Test
	public void v10BrokerPeriodOefProcessForceClosingProgramPostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		Double managerBalance = null;
		// Void response = api.v10BrokerPeriodOefProcessForceClosingProgramPost(authorization, investmentProgramId, managerBalance);

		// TODO: test validations
	}

	/**
	 * Close investment program
	 */
	@Test
	public void v10BrokerPeriodProcessClosingProgramPostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		Double managerBalance = null;
		// Void response = api.v10BrokerPeriodProcessClosingProgramPost(authorization, investmentProgramId, managerBalance);

		// TODO: test validations
	}

	/**
	 * Process investment requests
	 */
	@Test
	public void v10BrokerPeriodProcessInvestmentRequestsPostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		// UUID response = api.v10BrokerPeriodProcessInvestmentRequestsPost(authorization, investmentProgramId);

		// TODO: test validations
	}

	/**
	 * Update manager token initial price/total supply after loss
	 */
	@Test
	public void v10BrokerPeriodReevaluateManagerTokenPostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		Double investorLossShare = null;
		// Void response = api.v10BrokerPeriodReevaluateManagerTokenPost(authorization, investmentProgramId, investorLossShare);

		// TODO: test validations
	}

	/**
	 * Set investment period start balance, manager share, manager balance
	 */
	@Test
	public void v10BrokerPeriodSetStartValuesPostTest() {
		String authorization = null;
		StartValues model = null;
		// Void response = api.v10BrokerPeriodSetStartValuesPost(authorization, model);

		// TODO: test validations
	}

	/**
	 * Terminate program
	 */
	@Test
	public void v10BrokerPeriodTerminatePostTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		// Void response = api.v10BrokerPeriodTerminatePost(authorization, investmentProgramId);

		// TODO: test validations
	}

	/**
	 * Get data for closing investment period
	 */
	@Test
	public void v10BrokerPeriodlosingDataGetTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		// ClosePeriodData response = api.v10BrokerPeriodlosingDataGet(authorization, investmentProgramId);

		// TODO: test validations
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void v10BrokerTradehistoryGetTest() {
		String authorization = null;
		UUID investmentProgramId = null;
		DateTime start = null;
		DateTime end = null;
		// List<String> response = api.v10BrokerTradehistoryGet(authorization, investmentProgramId, start, end);

		// TODO: test validations
	}

	/**
	 * Update manager history ipfs hash
	 */
	@Test
	public void v10BrokerTradesIpfsHashUpdatePostTest() {
		String authorization = null;
		ManagerHistoryIpfsHash data = null;
		// Void response = api.v10BrokerTradesIpfsHashUpdatePost(authorization, data);

		// TODO: test validations
	}

	/**
	 * New trade event
	 */
	@Test
	public void v10BrokerTradesNewPostTest() {
		String authorization = null;
		NewTradeEvent tradeEvent = null;
		// Void response = api.v10BrokerTradesNewPost(authorization, tradeEvent);

		// TODO: test validations
	}
}
