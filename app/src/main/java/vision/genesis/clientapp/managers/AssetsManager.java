package vision.genesis.clientapp.managers;


import java.util.List;
import java.util.UUID;

import io.swagger.client.api.AssetsApi;
import io.swagger.client.model.ChangeBrokerProgramRequest;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.MakeSignalProviderProgram;
import io.swagger.client.model.MakeTradingAccountProgram;
import io.swagger.client.model.MakeTradingAccountSignalProvider;
import io.swagger.client.model.NewExternalTradingAccountRequest;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TradingAccountCreateResult;
import io.swagger.client.model.TradingAccountDemoDeposit;
import io.swagger.client.model.TradingAccountPwdUpdate;
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
		return assetsApi.createFund(AuthManager.token.getValue(), request);
	}

	public Observable<TradingAccountCreateResult> createTradingAccount(NewTradingAccountRequest request) {
		return assetsApi.createTradingAccount(AuthManager.token.getValue(), request);
	}

	public Observable<TradingAccountCreateResult> createExternalTradingAccount(NewExternalTradingAccountRequest request) {
		return assetsApi.createExternalTradingAccount(AuthManager.token.getValue(), request);
	}

	public Observable<Void> createProgramFromTradingAccount(MakeTradingAccountProgram request) {
		return assetsApi.makeAccountProgram(AuthManager.token.getValue(), request);
	}

	public Observable<Void> createProgramFromSignalProvider(MakeSignalProviderProgram followRequest) {
		return assetsApi.makeSignalProviderProgram(AuthManager.token.getValue(), followRequest);
	}

	public Observable<Void> createFollowFromTradingAccount(MakeTradingAccountSignalProvider request) {
		return assetsApi.makeAccountSignalProvider(AuthManager.token.getValue(), request);
	}

	public Observable<Void> createFollowFromExternalAccount(MakeTradingAccountSignalProvider request) {
		return assetsApi.makeExternalAccountSignalProvider(AuthManager.token.getValue(), request);
	}

	public Observable<Void> updateSignalProviderSettings(CreateSignalProvider request) {
		return assetsApi.updateSignalProviderSettings(AuthManager.token.getValue(), request);
	}

	public Observable<Void> updatePublicInfo(UUID assetId, ProgramUpdate request) {
		return assetsApi.updateAsset(AuthManager.token.getValue(), assetId, request);
	}

	public Observable<Void> changeBroker(UUID assetId, ChangeBrokerProgramRequest request) {
		return assetsApi.changeBroker(AuthManager.token.getValue(), assetId, request);
	}

	public Observable<Void> cancelBrokerChange(UUID accountId) {
		return assetsApi.cancelChangeBroker(accountId, AuthManager.token.getValue());
	}

	public Observable<Void> changeTradingAccountPassword(UUID accountId, TradingAccountPwdUpdate request) {
		return assetsApi.changeTradingAccountPassword(AuthManager.token.getValue(), accountId, request);
	}

	public Observable<Void> updateProgramSettings(UUID assetId, ProgramUpdate request) {
		return assetsApi.updateAsset(AuthManager.token.getValue(), assetId, request);
	}

	public Observable<Void> closePeriod(UUID programId) {
		return assetsApi.closeCurrentPeriod(programId, AuthManager.token.getValue());
	}

	public Observable<Void> closeProgram(UUID programId, TwoFactorCodeModel model) {
		return assetsApi.closeInvestmentProgram(AuthManager.token.getValue(), programId, model);
	}

	public Observable<Void> updateFundSettings(UUID assetId, ProgramUpdate request) {
		return assetsApi.updateAsset(AuthManager.token.getValue(), assetId, request);
	}

	public Observable<Void> updateFundAssets(UUID fundId, List<FundAssetPart> assets) {
		return assetsApi.updateFundAssets(AuthManager.token.getValue(), fundId, assets);
	}

	public Observable<Void> closeFund(UUID fundId, TwoFactorCodeModel model) {
		return assetsApi.closeFund(AuthManager.token.getValue(), fundId, model);
	}

	public Observable<Void> addDemoFunds(UUID accountId, Double amount) {
		TradingAccountDemoDeposit model = new TradingAccountDemoDeposit();
		model.setAmount(amount);
		return assetsApi.makeDemoTradingAccountDeposit(AuthManager.token.getValue(), accountId, model);
	}
}