package vision.genesis.clientapp.feature.main.copytrading.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.events.SetCopytradingOpenTradesCountEvent;
import vision.genesis.clientapp.model.events.SetCopytradingTradesHistoryCountEvent;
import vision.genesis.clientapp.model.events.SetCopytradingTradingLogCountEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/12/2019.
 */

@InjectViewState
public class CopytradingDetailsPresenter extends MvpPresenter<CopytradingDetailsView>
{
	@Inject
	public Context context;

	private TradingAccountDetailsModel model;

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

	void setData(TradingAccountDetailsModel model) {
		this.model = model;
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingOpenTradesCountEvent event) {
		getViewState().setOpenTradesCount(event.getOpenTradesCount());
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingTradesHistoryCountEvent event) {
		getViewState().setTradesCount(event.getTradesHistoryCount());
	}

	@Subscribe
	public void onEventMainThread(SetCopytradingTradingLogCountEvent event) {
		getViewState().setTradingLogCount(event.getCount());
	}
}
