package vision.genesis.clientapp.feature.main.wallet;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransaction;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.RateManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.events.ShowDepositWalletActivityEvent;
import vision.genesis.clientapp.model.events.ShowWithdrawWalletActivityEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class WalletPresenter extends MvpPresenter<WalletView> implements ViewPager.OnPageChangeListener
{
	private static final String FIAT_CURRENCY = WalletTransaction.CurrencyEnum.USD.toString();

	@Inject
	public Context context;

	@Inject
	public WalletManager walletManager;

	@Inject
	public RateManager rateManager;

	private Subscription balanceSubscription;

	private Subscription rateSubscription;

	private double gvtBalance = 0;

	private double rate = 0;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);
	}

	@Override
	public void onDestroy() {
		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
		if (rateSubscription != null)
			rateSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
		updateBalance();
	}

	void onWithdrawButtonClicked() {
		EventBus.getDefault().post(new ShowWithdrawWalletActivityEvent());
	}

	void onDepositButtonClicked() {
		EventBus.getDefault().post(new ShowDepositWalletActivityEvent());
	}

	void onTransactionsFilterSelected(int position) {
		switch (position) {
			case 0:
				getViewState().setTransactionsFilterType(TransactionsFilter.TypeEnum.ALL);
				break;
			case 1:
				getViewState().setTransactionsFilterType(TransactionsFilter.TypeEnum.INTERNAL);
				break;
			case 2:
				getViewState().setTransactionsFilterType(TransactionsFilter.TypeEnum.EXTERNAL);
				break;
		}
	}

	private void updateBalance() {
		getViewState().showBalanceProgress();
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleBalanceUpdateResponse,
						this::handleBalanceUpdateError);

		updateRate();
	}

	private void handleBalanceUpdateResponse(Double balance) {
		gvtBalance = balance;
		getViewState().setBalance(balance);
		updateFiatBalance();
	}

	private void handleBalanceUpdateError(Throwable error) {
		getViewState().hideBalanceProgress();
	}

	private void updateRate() {
		rateSubscription = rateManager.getRate(WalletTransaction.CurrencyEnum.GVT.toString(), FIAT_CURRENCY)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::getRateSuccessHandler,
						this::getRateErrorHandler);
	}

	private void getRateSuccessHandler(Double rate) {
		rateSubscription.unsubscribe();
		getViewState().hideBalanceProgress();

		this.rate = rate;
		updateFiatBalance();
	}

	private void updateFiatBalance() {
		getViewState().setFiatBalance(rate * gvtBalance);
	}

	private void getRateErrorHandler(Throwable error) {
		rateSubscription.unsubscribe();
		getViewState().hideBalanceProgress();
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		getViewState().showPage(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}
