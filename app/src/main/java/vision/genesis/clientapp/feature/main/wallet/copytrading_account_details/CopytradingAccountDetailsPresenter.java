package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.events.SetCopytradingAccountOpenTradesCountEvent;
import vision.genesis.clientapp.model.events.SetCopytradingAccountTradesHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetCopytradingAccountTradingLogCountEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

@InjectViewState
public class CopytradingAccountDetailsPresenter extends MvpPresenter<CopytradingAccountDetailsView>
{
	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingAccountOpenTradesCountEvent event) {
		getViewState().setOpenTradesCount(event.getOpenTradesCount());
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingAccountTradesHistoryCountEvent event) {
		getViewState().setTradesHistoryCount(event.getTradesHistoryCount());
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingAccountTradingLogCountEvent event) {
		getViewState().setTradingLogCount(event.getEventsCount());
	}
}
