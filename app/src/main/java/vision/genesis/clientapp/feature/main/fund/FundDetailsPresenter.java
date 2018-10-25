package vision.genesis.clientapp.feature.main.fund;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.FundIsFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

@InjectViewState
public class FundDetailsPresenter extends MvpPresenter<FundDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription userSubscription;

	private Subscription fundDetailsSubscription;

	private Subscription setFundFavoriteSubscription;

	private UUID fundId;

	private FundDetailsFull fundDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null)
			userSubscription.unsubscribe();

		if (fundDetailsSubscription != null)
			fundDetailsSubscription.unsubscribe();

		if (setFundFavoriteSubscription != null)
			setFundFavoriteSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	void setFundId(UUID fundId) {
		this.fundId = fundId;
	}

	void onFavoriteButtonClicked(boolean isFavorite) {
		setFundFavorite(isFavorite);
	}

	void onResume() {
		getFundDetails();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getFundDetails();
	}

	void onTryAgainClicked() {
		getViewState().showNoInternetProgress(true);
		getFundDetails();
	}

	private void getFundDetails() {
		if (fundId != null && fundsManager != null)
			fundDetailsSubscription = fundsManager.getFundDetails(fundId, CurrencyEnum.GVT)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleFundDetailsSuccess,
							this::handleFundDetailsError);
	}

	private void handleFundDetailsSuccess(FundDetailsFull fundDetails) {
		fundDetailsSubscription.unsubscribe();
		getViewState().showNoInternet(false);
		getViewState().showNoInternetProgress(false);
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		this.fundDetails = fundDetails;
		getViewState().setFund(fundDetails);
	}

	private void handleFundDetailsError(Throwable throwable) {
		fundDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (fundDetails == null) {
				getViewState().showNoInternet(true);
				getViewState().showNoInternetProgress(false);
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
		}
	}

	private void setFundFavorite(boolean isFavorite) {
		setFundFavoriteSubscription = fundsManager.setFundFavorite(fundId, isFavorite)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(response -> handleSetFundFavoriteSuccess(response, fundId, isFavorite),
						this::handleSetFundFavoriteError);
	}

	private void handleSetFundFavoriteSuccess(Void response, UUID fundId, boolean isFavorite) {
		setFundFavoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new FundIsFavoriteChangedEvent(fundId, isFavorite));
	}

	private void handleSetFundFavoriteError(Throwable throwable) {
		setFundFavoriteSubscription.unsubscribe();

		if (fundDetails == null)
			getViewState().setFund(fundDetails);
		getViewState().showToast(context.getString(R.string.error_occurred_performing_operation));
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
		getViewState().showToolbarButtons(true);
	}

	private void userLoggedOff() {
		getViewState().showToolbarButtons(false);
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(NewInvestmentSuccessEvent event) {
		getViewState().finishActivity();
	}
}
