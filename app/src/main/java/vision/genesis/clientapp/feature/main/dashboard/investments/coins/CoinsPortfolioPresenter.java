package vision.genesis.clientapp.feature.main.dashboard.investments.coins;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.events.SetCoinsPortfolioAssetsCountEvent;
import vision.genesis.clientapp.model.events.SetCoinsPortfolioHistoryCountEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

@InjectViewState
public class CoinsPortfolioPresenter extends MvpPresenter<CoinsPortfolioView>
{
	@Inject
	public Context context;

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
	public void onEventMainThread(SetCoinsPortfolioAssetsCountEvent event) {
		getViewState().setAssetsCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetCoinsPortfolioHistoryCountEvent event) {
		getViewState().setHistoryCount(event.getCount());
	}

}
