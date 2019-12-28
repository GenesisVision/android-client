package vision.genesis.clientapp.feature.main.trading_account.create.broker;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokersInfo;
import io.swagger.client.model.BrokersProgramInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.BrokersManager;
import vision.genesis.clientapp.model.events.OnBrokerSelectedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

@InjectViewState
public class SelectBrokerPresenter extends MvpPresenter<SelectBrokerView>
{
	@Inject
	public Context context;

	@Inject
	public BrokersManager brokersManager;

	private Subscription getBrokersSubscription;

	private List<Broker> brokers;

	private Broker selectedBroker;

	private UUID assetId;

	private boolean isAssetIdSet = false;

	private boolean isExternal;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getBrokers();
	}

	@Override
	public void onDestroy() {
		if (getBrokersSubscription != null) {
			getBrokersSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(UUID assetId, boolean isExternal) {
		this.assetId = assetId;
		this.isExternal = isExternal;
		isAssetIdSet = true;
	}

	void onBrokerSelected(Broker selectedBroker) {
		this.selectedBroker = selectedBroker;
		int position = 0;
		for (Broker broker : brokers) {
			if (broker.equals(selectedBroker)) {
				break;
			}
			position++;
		}
		getViewState().selectBroker(selectedBroker, position);
		getViewState().showBrokerInfo(selectedBroker);

		getViewState().setNextButtonEnabled(true);
	}

	void onNextClicked() {
		if (selectedBroker != null) {
			EventBus.getDefault().post(new OnBrokerSelectedEvent(selectedBroker));
		}
	}

	private void getBrokers() {
		if (assetId != null) {
			getProgramBrokers();
		}
		else {
			if (isExternal) {
				getExternalBrokers();
			}
			else {
				getAllBrokers();
			}
		}
	}

	private void getExternalBrokers() {
		if (brokersManager != null && isAssetIdSet) {
			if (getBrokersSubscription != null) {
				getBrokersSubscription.unsubscribe();
			}
			getBrokersSubscription = brokersManager.getExternalBrokers()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetBrokersSuccess,
							this::handleGetBrokersError);
		}
	}

	private void getAllBrokers() {
		if (brokersManager != null && isAssetIdSet) {
			if (getBrokersSubscription != null) {
				getBrokersSubscription.unsubscribe();
			}
			getBrokersSubscription = brokersManager.getAllBrokers()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetBrokersSuccess,
							this::handleGetBrokersError);
		}
	}

	private void handleGetBrokersSuccess(BrokersInfo response) {
		getBrokersSubscription.unsubscribe();
		if (!response.getBrokers().isEmpty()) {
			getViewState().showProgress(false);

			this.brokers = response.getBrokers();
			getViewState().setBrokers(brokers);
			onBrokerSelected(response.getBrokers().get(0));
		}
	}

	private void getProgramBrokers() {
		if (brokersManager != null && isAssetIdSet) {
			if (getBrokersSubscription != null) {
				getBrokersSubscription.unsubscribe();
			}
			getBrokersSubscription = brokersManager.getBrokersForProgram(assetId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetProgramBrokersSuccess,
							this::handleGetBrokersError);
		}
	}

	private void handleGetProgramBrokersSuccess(BrokersProgramInfo response) {
		getBrokersSubscription.unsubscribe();
		if (!response.getBrokers().isEmpty()) {
			getViewState().showProgress(false);

			this.brokers = response.getBrokers();
			getViewState().setBrokers(brokers);
			onBrokerSelected(response.getBrokers().get(0));
		}
	}

	private void handleGetBrokersError(Throwable throwable) {
		getBrokersSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
