package io.swagger.client.api;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.AttachToExternalSignalProviderCommon;
import io.swagger.client.model.AttachToExternalSignalProviderExt;
import io.swagger.client.model.AttachToSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.NewExternalSignalAccountRequest;

/**
 * API tests for SignalApi
 */
public class SignalApiTest
{

	private SignalApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(SignalApi.class);
	}


	/**
	 * Subscribe to external signal using common account
	 */
	@Test
	public void attachSlaveCommonToMasterTest() {
		String authorization = null;
		UUID id = null;
		AttachToExternalSignalProviderCommon body = null;
		// Void response = api.attachSlaveCommonToMaster(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Subscribe to programs signals
	 */
	@Test
	public void attachSlaveToMasterTest() {
		String authorization = null;
		UUID id = null;
		AttachToSignalProvider body = null;
		// Void response = api.attachSlaveToMaster(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Subscribe to external signal account
	 */
	@Test
	public void attachSlaveToMaster_0Test() {
		String authorization = null;
		UUID id = null;
		AttachToExternalSignalProviderExt body = null;
		// Void response = api.attachSlaveToMaster_0(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Close signal trade
	 */
	@Test
	public void closeTradeTest() {
		UUID id = null;
		String authorization = null;
		UUID programId = null;
		// Void response = api.closeTrade(id, authorization, programId);

		// TODO: test validations
	}

	/**
	 * Create external signal account
	 */
	@Test
	public void createExternalSignalAccountTest() {
		String authorization = null;
		NewExternalSignalAccountRequest body = null;
		// ProgramCreateResult response = api.createExternalSignalAccount(authorization, body);

		// TODO: test validations
	}

	/**
	 * Unsubscribe from program signals
	 */
	@Test
	public void detachSlaveFromMasterTest() {
		String authorization = null;
		UUID id = null;
		DetachFromSignalProvider body = null;
		// Void response = api.detachSlaveFromMaster(authorization, id, body);

		// TODO: test validations
	}

	/**
	 * Get copytrading slaves accounts
	 */
	@Test
	public void getCopytradingAccountsTest() {
		String authorization = null;
		// CopyTradingAccountsList response = api.getCopytradingAccounts(authorization);

		// TODO: test validations
	}

	/**
	 * Get investors signals trading log
	 */
	@Test
	public void getExternalSignalTradingLogTest() {
		String authorization = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// SignalTradingEvents response = api.getExternalSignalTradingLog(authorization, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Get investors signals open trades
	 */
	@Test
	public void getOpenSignalTradesTest() {
		String authorization = null;
		String sorting = null;
		String symbol = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// TradesSignalViewModel response = api.getOpenSignalTrades(authorization, sorting, symbol, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Get investors signals trades history
	 */
	@Test
	public void getSignalTradesTest() {
		String authorization = null;
		DateTime dateFrom = null;
		DateTime dateTo = null;
		String symbol = null;
		String sorting = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// TradesSignalViewModel response = api.getSignalTrades(authorization, dateFrom, dateTo, symbol, sorting, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Get investors signals trading log
	 */
	@Test
	public void getSignalTradingLogTest() {
		String authorization = null;
		UUID accountId = null;
		String accountCurrency = null;
		Integer skip = null;
		Integer take = null;
		// SignalTradingEvents response = api.getSignalTradingLog(authorization, accountId, accountCurrency, skip, take);

		// TODO: test validations
	}

	/**
	 * Get copytrading masters list
	 */
	@Test
	public void getSignalsTest() {
		String authorization = null;
		List<String> tags = null;
		DateTime statisticDateFrom = null;
		DateTime statisticDateTo = null;
		Integer chartPointsCount = null;
		String chartCurrency = null;
		String facetId = null;
		String mask = null;
		Integer skip = null;
		Integer take = null;
		// ItemsViewModelCopyTradingAccountInfo response = api.getSignals(authorization, tags, statisticDateFrom, statisticDateTo, chartPointsCount, chartCurrency, facetId, mask, skip, take);

		// TODO: test validations
	}

	/**
	 * Get subscribe to programs signals info
	 */
	@Test
	public void getSlaveAttachInfoTest() {
		UUID id = null;
		String authorization = null;
		// AttachToSignalProviderInfo response = api.getSlaveAttachInfo(id, authorization);

		// TODO: test validations
	}

	/**
	 * Update signal subscription settings
	 */
	@Test
	public void updateSubscriptionSettingsTest() {
		String authorization = null;
		UUID id = null;
		AttachToSignalProvider body = null;
		// Void response = api.updateSubscriptionSettings(authorization, id, body);

		// TODO: test validations
	}
}
