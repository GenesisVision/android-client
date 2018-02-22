package vision.genesis.clientapp.feature.main.traders.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

@InjectViewState
public class TraderDetailsPresenter extends MvpPresenter<TraderDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription userSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	void onInvestClicked() {
		getViewState().showInvestDialog();
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null)
			userLoggedOff();
		else
			userLoggedOn();
	}

	private void userLoggedOn() {
		getViewState().showInvestWithdrawButtons(true);
	}

	private void userLoggedOff() {
		getViewState().showInvestWithdrawButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().finishActivity();
	}
}
