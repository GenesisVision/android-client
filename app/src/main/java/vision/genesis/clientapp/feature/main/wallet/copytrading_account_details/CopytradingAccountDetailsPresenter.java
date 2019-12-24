package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.events.SetCopytradingOpenTradesCountEvent;
import vision.genesis.clientapp.model.events.SetCopytradingTradesHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetCopytradingTradingLogCountEvent;

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
	public void onEventMainThread(SetCopytradingOpenTradesCountEvent event) {
		getViewState().setOpenTradesCount(event.getOpenTradesCount());
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingTradesHistoryCountEvent event) {
		getViewState().setTradesHistoryCount(event.getTradesHistoryCount());
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingTradingLogCountEvent event) {
		getViewState().setTradingLogCount(event.getCount());
	}
}
