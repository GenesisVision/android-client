package vision.genesis.clientapp.feature.main.external.attach;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.NewExternalTradingAccountRequest;
import io.swagger.client.model.TradingAccountCreateResult;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.model.events.OnExternalApiKeyConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */

@InjectViewState
public class AttachExternalAccountPresenter extends MvpPresenter<AttachExternalAccountView>
{
	@Inject
	public AssetsManager assetsManager;

	private Subscription createAccountSubscription;

	private Broker selectedBroker;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (createAccountSubscription != null) {
			createAccountSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void sendAttachAccountRequest(String apiKey, String apiSecret) {
		getViewState().showProgress(true);

		NewExternalTradingAccountRequest request = new NewExternalTradingAccountRequest();
		request.setBrokerAccountTypeId(selectedBroker.getAccountTypes().get(0).getId());
		request.setKey(apiKey);
		request.setSecret(apiSecret);

		createAccountSubscription = assetsManager.createExternalTradingAccount(request)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleCreateAccountSuccess,
						this::handleCreateAccountError);
	}

	private void handleCreateAccountSuccess(TradingAccountCreateResult response) {
		createAccountSubscription.unsubscribe();
		//TODO:
//		if (response.isTwoFactorRequired())
		getViewState().finishActivity();
	}

	private void handleCreateAccountError(Throwable throwable) {
		createAccountSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnBrokerSelectedEvent event) {
		this.selectedBroker = event.getSelectedBroker();
		getViewState().showApiKey(event.getSelectedBroker());
	}

	@Subscribe
	public void onEventMainThread(OnExternalApiKeyConfirmEvent event) {
		sendAttachAccountRequest(event.getApiKey(), event.getApiSecret());
	}
}