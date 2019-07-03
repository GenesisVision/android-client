package vision.genesis.clientapp.managers;

import java.util.UUID;

import io.swagger.client.api.SignalApi;
import io.swagger.client.model.AttachToSignalProviderInfo;
import io.swagger.client.model.CopyTradingAccountsList;
import io.swagger.client.model.DetachFromSignalProvider;
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
	private SignalApi signalApi;

	public SignalsManager(SignalApi signalApi) {
		this.signalApi = signalApi;
	}

	public Observable<AttachToSignalProviderInfo> getSignalsInfo(UUID programId) {
		return signalApi.v10SignalAttachByIdInfoGet(programId, AuthManager.token.getValue());
	}

	public Observable<Void> subscribeToProgram(SubscriptionSettingsModel model) {
		return signalApi.v10SignalAttachByIdPost(model.getProgramId(), AuthManager.token.getValue(), model.getApiModel());
	}

	public Observable<Void> updateSubscription(SubscriptionSettingsModel model) {
		return signalApi.v10SignalByIdUpdatePost(model.getProgramId(), AuthManager.token.getValue(), model.getApiModel());
	}

	public Observable<Void> unsubscribeFromProgram(UUID programId, DetachFromSignalProvider.ModeEnum unsubscriptionType) {
		DetachFromSignalProvider model = new DetachFromSignalProvider();
		model.setMode(unsubscriptionType);
		return signalApi.v10SignalDetachByIdPost(programId, AuthManager.token.getValue(), model);
	}

	public Observable<CopyTradingAccountsList> getAccounts() {
		return signalApi.v10SignalAccountsGet(AuthManager.token.getValue());
	}

	public Observable<TradesSignalViewModel> getOpenTrades(String sorting, String symbol, UUID accountId, String accountCurrency, Integer skip, Integer take) {
		return signalApi.v10SignalTradesOpenGet(AuthManager.token.getValue(), sorting, symbol, accountId, accountCurrency, skip, take);
	}

	public Observable<TradesSignalViewModel> getTradesHistory(DateRange dateRange, String sorting, String symbol, UUID accountId, String accountCurrency, Integer skip, Integer take) {
		return signalApi.v10SignalTradesGet(AuthManager.token.getValue(), dateRange.getFrom(), dateRange.getTo(), symbol, sorting, accountId, accountCurrency, skip, take);
	}

	public Observable<Void> closeTrade(UUID tradeId, UUID programId) {
		return signalApi.v10SignalTradesByIdClosePost(tradeId, AuthManager.token.getValue(), programId);
	}
}