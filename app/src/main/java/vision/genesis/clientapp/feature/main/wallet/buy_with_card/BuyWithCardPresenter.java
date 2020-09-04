package vision.genesis.clientapp.feature.main.wallet.buy_with_card;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.model.events.OnCardDepositFinishedEvent;
import vision.genesis.clientapp.net.CustomWebViewClient;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/09/2020.
 */

@InjectViewState
public class BuyWithCardPresenter extends MvpPresenter<BuyWithCardView> implements CustomWebViewClient.Listener
{

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

//		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	@Override
	public void onIntercepted(String url) {
		EventBus.getDefault().post(new OnCardDepositFinishedEvent());
		getViewState().finishActivity();
	}
}
