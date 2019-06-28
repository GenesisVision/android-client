package vision.genesis.clientapp.feature.main.wallet.copytrading_account_details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.model.events.SetSpecificWalletDepositsWithdrawalsCountEvent;
import vision.genesis.clientapp.model.events.SetSpecificWalletTransactionsCountEvent;

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
	public void onEventMainThread(SetSpecificWalletTransactionsCountEvent event) {
		getViewState().setOpenTradesCount(event.getTransactionsCount());
	}

	@Subscribe
	public void onEventMainThread(SetSpecificWalletDepositsWithdrawalsCountEvent event) {
		getViewState().setTradesHistoryCount(event.getDepositsWithdrawalsCount());
	}
}
