package vision.genesis.clientapp.managers;


import java.util.List;
import java.util.UUID;

import io.swagger.client.api.AssetsApi;
import io.swagger.client.model.ChangeBrokerProgramRequest;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.MakeExchangeAccountProgram;
import io.swagger.client.model.MakeExchangeProgram;
import io.swagger.client.model.MakeProgram;
import io.swagger.client.model.MakeSelfManagedFundPublicRequest;
import io.swagger.client.model.MakeSignalProviderProgram;
import io.swagger.client.model.MakeTradingAccountProgram;
import io.swagger.client.model.MakeTradingAccountSignalProvider;
import io.swagger.client.model.NewExchangeAccountRequest;
import io.swagger.client.model.NewExternalTradingAccountRequest;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewSelfManagedFundRequest;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TradingAccountCreateResult;
import io.swagger.client.model.TradingAccountDemoDeposit;
import io.swagger.client.model.TradingAccountPwdUpdate;
import io.swagger.client.model.TwoFactorAuthenticator;
import io.swagger.client.model.TwoFactorCodeModel;
import rx.Observable;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/10/2019.
 */

public class AssetsManager
{
	private AssetsApi assetsApi;

	public AssetsManager(AssetsApi assetsApi) {
		this.assetsApi = assetsApi;
	}

	public Observable<Void> createFund(NewFundRequest request) {
		return assetsApi.createFund(request);
	}

	public Observable<Void> createSelfManagedFund(NewSelfManagedFundRequest request) {
		return assetsApi.createSelfManagedFund(request);
	}

	public Observable<TradingAccountCreateResult> createTradingAccount(NewTradingAccountRequest request) {
		return assetsApi.createTradingAccount(request);
	}

	public Observable<TradingAccountCreateResult> createExchangeAccount(NewExchangeAccountRequest request) {
		return assetsApi.createExchangeAccount(request);
	}

	public Observable<TradingAccountCreateResult> createExternalTradingAccount(NewExternalTradingAccountRequest request) {
		return assetsApi.createExternalTradingAccount(request);
	}

	public Observable<TradingAccountCreateResult> createProgram(MakeProgram request) {
		return assetsApi.makeProgram(request);
	}

	public Observable<TradingAccountCreateResult> createExchangeProgram(MakeExchangeProgram request) {
		return assetsApi.makeExchangeProgram(request);
	}

	public Observable<Void> createProgramFromTradingAccount(MakeTradingAccountProgram request) {
		return assetsApi.makeAccountProgram(request);
	}

	public Observable<Void> createProgramFromExchangeAccount(MakeExchangeAccountProgram request) {
		return assetsApi.makeExchangeAccountProgram(request);
	}

	public Observable<Void> createProgramFromSignalProvider(MakeSignalProviderProgram followRequest) {
		return assetsApi.makeSignalProviderProgram(followRequest);
	}

	public Observable<Void> createFollowFromTradingAccount(MakeTradingAccountSignalProvider request) {
		return assetsApi.makeAccountSignalProvider(request);
	}

	public Observable<Void> createFollowFromExternalAccount(MakeTradingAccountSignalProvider request) {
		return assetsApi.makeExternalAccountSignalProvider(request);
	}

	public Observable<Void> updateSignalProviderSettings(CreateSignalProvider request) {
		return assetsApi.updateSignalProviderSettings(request);
	}

	public Observable<Void> updatePublicInfo(UUID assetId, ProgramUpdate request) {
		return assetsApi.updateAsset(assetId, request);
	}

	public Observable<Void> changeBroker(UUID assetId, ChangeBrokerProgramRequest request) {
		return assetsApi.changeBroker(assetId, request);
	}

	public Observable<Void> cancelBrokerChange(UUID accountId) {
		return assetsApi.cancelChangeBroker(accountId);
	}

	public Observable<Void> changeTradingAccountPassword(UUID accountId, TradingAccountPwdUpdate request) {
		return assetsApi.changeTradingAccountPassword(accountId, request);
	}

	public Observable<Void> updateProgramSettings(UUID assetId, ProgramUpdate request) {
		return assetsApi.updateAsset(assetId, request);
	}

	public Observable<Void> closePeriod(UUID programId) {
		return assetsApi.closeCurrentPeriod(programId);
	}

	public Observable<Void> closeProgram(UUID programId, TwoFactorCodeModel model) {
		return assetsApi.closeInvestmentProgram(programId, model);
	}

	public Observable<Void> updateFundSettings(UUID assetId, ProgramUpdate request) {
		return assetsApi.updateAsset(assetId, request);
	}

	public Observable<Void> updateFundAssets(UUID fundId, List<FundAssetPart> assets) {
		return assetsApi.updateFundAssets(fundId, assets);
	}

	public Observable<Void> closeFund(UUID fundId, TwoFactorCodeModel model) {
		return assetsApi.closeFund(fundId, model);
	}

	public Observable<Void> addDemoFunds(UUID accountId, Double amount) {
		TradingAccountDemoDeposit model = new TradingAccountDemoDeposit();
		model.setAmount(amount);
		return assetsApi.makeDemoTradingAccountDeposit(accountId, model);
	}

	public Observable<Void> closeTradingAccount(UUID accountId) {
		return assetsApi.closeTradingAccount(accountId);
	}

	public Observable<Void> closeExchangeTradingAccount(UUID accountId) {
		return assetsApi.closeExchangeAccount(accountId);
	}

	public Observable<Void> makePublicFund(MakeSelfManagedFundPublicRequest request) {
		return assetsApi.makeSelfManagedFundPublic(request);
	}

	public Observable<TwoFactorAuthenticator> getProgram2fa(UUID programId) {
		return assetsApi.getProgram2FA(programId);
	}

	public Observable<Void> confirmProgram2fa(UUID programId, TwoFactorCodeModel body) {
		return assetsApi.confirmProgram2FA(programId, body);
	}
}