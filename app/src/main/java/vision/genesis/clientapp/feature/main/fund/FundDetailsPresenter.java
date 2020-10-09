package vision.genesis.clientapp.feature.main.fund;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.InvestmentEventViewModels;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnFundFavoriteChangedEvent;
import vision.genesis.clientapp.model.events.SetFundDetailsEventsCountEvent;
import vision.genesis.clientapp.model.events.SetFundDetailsReallocatesCountEvent;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
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

	private Subscription eventsSubscription;

	private Subscription setFundFavoriteSubscription;

	private FundDetailsFull fundDetails;

	private boolean isActive = false;

	private FundDetailsModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}
		if (fundDetailsSubscription != null) {
			fundDetailsSubscription.unsubscribe();
		}
		if (eventsSubscription != null) {
			eventsSubscription.unsubscribe();
		}
		if (setFundFavoriteSubscription != null) {
			setFundFavoriteSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(FundDetailsModel model) {
		this.model = model;
	}

	void onFavoriteButtonClicked(boolean isFavorite) {
		setFundFavorite(isFavorite);
	}

	void onResume() {
		isActive = true;
		getFundDetails();
	}

	void onPause() {
		isActive = false;
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
		if (model != null && fundsManager != null) {
			fundDetailsSubscription = fundsManager.getFundDetails(model.getFundId(), CurrencyEnum.GVT)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleFundDetailsSuccess,
							this::handleFundDetailsError);
		}
	}

	private void handleFundDetailsSuccess(FundDetailsFull fundDetails) {
		fundDetailsSubscription.unsubscribe();
		getViewState().showNoInternet(false);
		getViewState().showNoInternetProgress(false);
		getViewState().setRefreshing(false);

		this.fundDetails = fundDetails;
		if (fundDetails.getPersonalDetails() != null && fundDetails.getPublicInfo().isIsOwnAsset()) {
			getViewState().showOwner(fundDetails);
		}
		else {
			getViewState().showGuest(fundDetails);
		}

		getEvents();
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

	private void getEvents() {
		if (model != null) {
			if (eventsSubscription != null) {
				eventsSubscription.unsubscribe();
			}
			eventsSubscription = fundsManager.getEvents(model.getFundId(), DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME), 0, 1)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetEventsResponse,
							this::handleGetEventsError);
		}
	}

	private void handleGetEventsResponse(InvestmentEventViewModels response) {
		eventsSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().finishInit(response.getTotal() > 0);
		getViewState().setEventsCount(response.getTotal());
	}

	private void handleGetEventsError(Throwable throwable) {
		eventsSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().finishInit(false);
	}

	private void setFundFavorite(boolean isFavorite) {
		if (fundDetails != null && fundsManager != null) {
			setFundFavoriteSubscription = fundsManager.setFundFavorite(fundDetails.getId(), isFavorite)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleSetFundFavoriteSuccess(response, fundDetails.getId(), isFavorite),
							this::handleSetFundFavoriteError);
		}
	}

	private void handleSetFundFavoriteSuccess(Void response, UUID fundId, boolean isFavorite) {
		setFundFavoriteSubscription.unsubscribe();

		EventBus.getDefault().post(new OnFundFavoriteChangedEvent(fundId, isFavorite));
	}

	private void handleSetFundFavoriteError(Throwable throwable) {
		setFundFavoriteSubscription.unsubscribe();

		getViewState().showToast(context.getString(R.string.error_occurred_performing_operation));
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		if (user == null) {
			userLoggedOff();
		}
		else {
			userLoggedOn();
		}
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
	public void onEventMainThread(SetFundDetailsReallocatesCountEvent event) {
		getViewState().setReallocatesCount(event.getCount());
	}

	@Subscribe
	public void onEventMainThread(SetFundDetailsEventsCountEvent event) {
		getViewState().setEventsCount(event.getEventsCount());
	}

	@Subscribe
	public void onEventMainThread(ShowEventDetailsEvent event) {
		if (isActive) {
			getViewState().showEventDetails(event.getEvent());
		}
	}
}
