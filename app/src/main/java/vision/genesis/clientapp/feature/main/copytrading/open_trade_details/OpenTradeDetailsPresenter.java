package vision.genesis.clientapp.feature.main.copytrading.open_trade_details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.OpenTradeModel;
import vision.genesis.clientapp.model.OpenTradeProviderModel;
import vision.genesis.clientapp.model.events.OnOpenTradeCloseClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

@InjectViewState
public class OpenTradeDetailsPresenter extends MvpPresenter<OpenTradeDetailsView>
{
	@Inject
	public FollowsManager followsManager;

	private Subscription closeTradeSubscription;

	private OpenTradeModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (closeTradeSubscription != null) {
			closeTradeSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setModel(OpenTradeModel model) {
		this.model = model;
		getViewState().setOpenTrades(model.getProviders());
	}

	void closeTrade(UUID programId) {
		if (followsManager != null && model != null && programId != null) {
			getViewState().showProgress(true);
//			closeTradeSubscription = followsManager.closeTrade(model.getId(), programId)
//					.subscribeOn(Schedulers.computation())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(response -> handleCloseTradeSuccess(programId),
//							this::handleCloseTradeError);
		}
	}

	private void handleCloseTradeSuccess(UUID programId) {
		closeTradeSubscription.unsubscribe();
		getViewState().showProgress(false);

		int position = 0;
		List<OpenTradeProviderModel> providers = model.getProviders();
		for (OpenTradeProviderModel provider : providers) {
			if (provider.getProgramId().equals(programId)) {
				providers.remove(provider);
				model.setProviders(providers);
				break;
			}
			position++;
		}

		getViewState().removeOpenTrade(position, providers.isEmpty());
	}

	private void handleCloseTradeError(Throwable throwable) {
		closeTradeSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnOpenTradeCloseClickedEvent event) {
		getViewState().askCloseTrade(event.getProgramId(), event.getSymbol(), event.getVolume());
	}
}
