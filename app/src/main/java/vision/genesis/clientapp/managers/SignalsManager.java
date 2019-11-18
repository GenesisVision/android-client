package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.CopytradingApi;
import io.swagger.client.api.SignalApi;
import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.ItemsViewModelSignalTradingEvent;
import io.swagger.client.model.SignalDetachMode;
import io.swagger.client.model.TradesSignalViewModel;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/06/2019.
 */

public class SignalsManager
{
	private final SignalApi signalApi;

	private final CopytradingApi copytradingApi;

	public SignalsManager(SignalApi signalApi, CopytradingApi copytradingApi) {
		this.signalApi = signalApi;
		this.copytradingApi = copytradingApi;
	}

	public Observable<AttachToSignalProviderInfo> getSignalsInfo(UUID programId) {
//		return signalApi.getSlaveAttachInfo(programId, AuthManager.token.getValue());
		return null;
	}

	public Observable<Void> subscribeToProgram(SubscriptionSettingsModel model) {
		return signalApi.attachSlaveToMaster(AuthManager.token.getValue(), model.getProgramId(), model.getApiModel());
	}

	public Observable<Void> updateSubscription(SubscriptionSettingsModel model) {
		return signalApi.updateSubscriptionSettings(AuthManager.token.getValue(), model.getProgramId(), model.getApiModel());
	}

	public Observable<Void> unsubscribeFromProgram(UUID programId, SignalDetachMode unsubscriptionType) {
		DetachFromSignalProvider model = new DetachFromSignalProvider();
		model.setMode(unsubscriptionType);
		return signalApi.detachSlaveFromMaster(AuthManager.token.getValue(), programId, model);
	}

//	public Observable<CopyTradingAccountsList> getAccounts() {
//		return copytradingApi.getSignalAssets(AuthManager.token.getValue());
//	}

	public Observable<TradesSignalViewModel> getOpenTrades(String sorting, String symbol, UUID accountId, String accountCurrency, Integer skip, Integer take) {
		return signalApi.getOpenSignalTrades(AuthManager.token.getValue(), sorting, symbol, accountId, accountCurrency, skip, take);
	}

	public Observable<TradesSignalViewModel> getTradesHistory(DateRange dateRange, String sorting, String symbol, UUID accountId, String accountCurrency, Integer skip, Integer take) {
		return signalApi.getSignalTrades(AuthManager.token.getValue(), dateRange.getFrom(), dateRange.getTo(), symbol, sorting, accountId, accountCurrency, skip, take);
	}

	public Observable<Void> closeTrade(UUID tradeId, UUID programId) {
		return signalApi.closeTrade(tradeId, AuthManager.token.getValue(), programId);
	}

	public Observable<ItemsViewModelSignalTradingEvent> getTradingLog(String accountCurrency, Integer skip, Integer take) {
		return signalApi.getSignalTradingLog(AuthManager.token.getValue(), null, accountCurrency, skip, take);
	}
}