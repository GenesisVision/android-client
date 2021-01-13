package vision.genesis.clientapp.feature.main.dashboard.trading.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.DashboardAssetStatus;
import io.swagger.client.model.DashboardTradingAssetItemsViewModel;
import io.swagger.client.model.DashboardTradingDetails;
import io.swagger.client.model.ProfileFullViewModel;
import io.swagger.client.model.Timeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.timeframe_profit.TimeframeProfitView;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.events.OnProfilePublicInfoFilledEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

@InjectViewState
public class TradingDetailsPresenter extends MvpPresenter<TradingDetailsView> implements TimeframeProfitView.Listener
{
	private static final int TAKE = 100;

	private static final int EVENTS_TAKE = 5;

	@Inject
	public Context context;

	@Inject
	public DashboardManager dashboardManager;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public ProgramsManager programsManager;

	@Inject
	public FundsManager fundsManager;

	private Subscription baseCurrencySubscription;

	private Subscription profileSubscription;

	private Subscription getTradingSubscription;

	private Subscription privateAccountsSubscription;

	private Subscription privateFundsSubscription;

	private Subscription publicSubscription;

	private CurrencyEnum baseCurrency;

	private DashboardTradingDetails details;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);

	private Timeframe selectedTimeframe = Timeframe.DAY;

	private boolean isUsernameFilled = false;

	private boolean isWaitingFillProfileToCreateFund = false;

	private DashboardTradingAssetItemsViewModel privateAccountsModel;

	private DashboardTradingAssetItemsViewModel privateFundsModel;

	private DashboardAssetStatus privateStatus;

	private DashboardAssetStatus publicStatus;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);

		initCreateOptions();
		initStatusOptions();

		getViewState().showProgress(true);
		subscribeToBaseCurrency();
		getProfile();
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}
		if (getTradingSubscription != null) {
			getTradingSubscription.unsubscribe();
		}
		if (privateAccountsSubscription != null) {
			privateAccountsSubscription.unsubscribe();
		}
		if (privateFundsSubscription != null) {
			privateFundsSubscription.unsubscribe();
		}
		if (publicSubscription != null) {
			publicSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		updateAll();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		updateAll();
	}

	void onCreatePrivateOptionSelected(Integer position, String optionName) {
		switch (position) {
			case 0:
				getViewState().showCreateTradingAccountActivity();
				break;
			case 1:
				getViewState().showAttachAccountActivity();
				break;
			case 2:
				getViewState().showCreateSelfManagedFundActivity();
				break;
		}
	}

	void onCreatePublicOptionSelected(Integer position, String optionName) {
		switch (position) {
			case 0:
				checkCreateFundAvailability();
				break;
			case 1:
				checkCreateProgramAvailability();
				break;
		}
	}

	void onCreateFundClicked() {
		checkCreateFundAvailability();
	}

	private void checkCreateFundAvailability() {
		if (isUsernameFilled) {
			getViewState().showCreateFundActivity();
		}
		else {
			isWaitingFillProfileToCreateFund = true;
			getViewState().showProfilePublicInfoActivity();
		}
	}

	private void checkCreateProgramAvailability() {
		if (isUsernameFilled) {
			getViewState().showCreateProgramActivity();
		}
		else {
			isWaitingFillProfileToCreateFund = true;
			getViewState().showProfilePublicInfoActivity();
		}
	}

	private void initCreateOptions() {
		ArrayList<String> createPrivateOptions = new ArrayList<>();
		ArrayList<String> createPublicOptions = new ArrayList<>();

		createPrivateOptions.add(context.getString(R.string.create_trading_account));
		createPrivateOptions.add(context.getString(R.string.attach_external_account));
		createPrivateOptions.add(context.getString(R.string.create_self_managed_fund));

		createPublicOptions.add(context.getString(R.string.create_fund));
		createPublicOptions.add(context.getString(R.string.create_program));

		getViewState().setCreateOptions(createPrivateOptions, createPublicOptions);
	}

	private void initStatusOptions() {
		ArrayList<String> statusPrivateOptions = new ArrayList<>();
		ArrayList<String> statusPublicOptions = new ArrayList<>();

		statusPrivateOptions.add(context.getString(R.string.active));
		statusPrivateOptions.add(context.getString(R.string.all));

		statusPublicOptions.add(context.getString(R.string.active));
		statusPublicOptions.add(context.getString(R.string.all));

		getViewState().setStatusOptions(statusPrivateOptions, statusPublicOptions);

		int privatePosition = getStatusPosition(settingsManager.getSavedTradingPrivateStatus());
		onPrivateStatusOptionSelected(privatePosition, statusPrivateOptions.get(privatePosition));

		int publicPosition = getStatusPosition(settingsManager.getSavedTradingPublicStatus());
		onPublicStatusOptionSelected(publicPosition, statusPublicOptions.get(publicPosition));
	}

	private int getStatusPosition(DashboardAssetStatus status) {
		switch (status) {
			case ALL:
				return 1;
			default:
			case ACTIVE:
				return 0;
		}
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setBaseCurrency(baseCurrency);
		getViewState().showProgress(true);
		updateAll();
	}

	private void getProfile() {
		profileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess);

	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		this.isUsernameFilled = profile.getUserName() != null && !profile.getUserName().isEmpty();
	}

	private void updateAll() {
		getTrading();
		getPrivate();
		getPublic();
	}

	private void getTrading() {
		if (dashboardManager != null && baseCurrency != null) {
			getTradingSubscription = dashboardManager.getTradingDetails(baseCurrency.getValue(), EVENTS_TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleTradingSuccess, this::handleTradingError);
		}
	}

	private void handleTradingSuccess(DashboardTradingDetails details) {
		getTradingSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		this.details = details;

		getViewState().setTrading(details);
		getViewState().setTimeframe(selectedTimeframe);
		getViewState().setEvents(details.getEvents().getItems());
	}

	private void handleTradingError(Throwable throwable) {
		getTradingSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPrivate() {
		getPrivateAccounts();
	}

	private void getPrivateAccounts() {
		if (dashboardManager != null && baseCurrency != null && privateStatus != null) {
			if (privateAccountsSubscription != null) {
				privateAccountsSubscription.unsubscribe();
			}

			getViewState().showPrivateProgress();
			privateAccountsSubscription = dashboardManager.getPrivateAccounts(dateRange, baseCurrency.getValue(), privateStatus, 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPrivateAccountsResponse,
							this::handleGetPrivateAccountsError);
		}
	}

	private void handleGetPrivateAccountsResponse(DashboardTradingAssetItemsViewModel response) {
		privateAccountsSubscription.unsubscribe();

		this.privateAccountsModel = response;

		getViewState().setPrivateAccounts(response.getItems());

		getPrivateFunds();
	}

	private void handleGetPrivateAccountsError(Throwable throwable) {
		privateAccountsSubscription.unsubscribe();
		getViewState().hidePrivateProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	private void getPrivateFunds() {
		if (dashboardManager != null && baseCurrency != null && privateStatus != null) {
			if (privateFundsSubscription != null) {
				privateFundsSubscription.unsubscribe();
			}

			getViewState().showPrivateProgress();
			privateFundsSubscription = dashboardManager.getPrivateFunds(dateRange, baseCurrency.getValue(), privateStatus, 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPrivateFundsResponse,
							this::handleGetPrivateFundsError);
		}
	}

	private void handleGetPrivateFundsResponse(DashboardTradingAssetItemsViewModel response) {
		privateFundsSubscription.unsubscribe();
		getViewState().hidePrivateProgress();

		this.privateFundsModel = response;

		updatePrivateCount();
		getViewState().setPrivateFunds(response.getItems());
	}

	private void updatePrivateCount() {
		if (privateAccountsModel != null && privateFundsModel != null) {
			getViewState().setPrivateCount(privateAccountsModel.getTotal() + privateFundsModel.getTotal());
		}
	}

	private void handleGetPrivateFundsError(Throwable throwable) {
		privateFundsSubscription.unsubscribe();
		getViewState().hidePrivateProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}


	private void getPublic() {
		if (dashboardManager != null && baseCurrency != null && publicStatus != null) {
			if (publicSubscription != null) {
				publicSubscription.unsubscribe();
			}

			getViewState().showPublicProgress();
			publicSubscription = dashboardManager.getPublic(dateRange, baseCurrency.getValue(), publicStatus, 0, TAKE)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPublicResponse,
							this::handleGetPublicError);
		}
	}

	private void handleGetPublicResponse(DashboardTradingAssetItemsViewModel response) {
		publicSubscription.unsubscribe();
		getViewState().hidePublicProgress();

		getViewState().setPublicCount(response.getTotal());
		getViewState().setPublic(response.getItems());
	}

	private void handleGetPublicError(Throwable throwable) {
		publicSubscription.unsubscribe();
		getViewState().hidePublicProgress();

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onTimeframeSelected(Timeframe timeframe) {
		this.selectedTimeframe = timeframe;
		dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.fromValue(timeframe.getValue()));
		getViewState().setTimeframe(timeframe);
		getViewState().showProgress(true);
		updateAll();
	}

	@Subscribe
	public void onEventMainThread(OnProfilePublicInfoFilledEvent event) {
		if (isWaitingFillProfileToCreateFund) {
			isWaitingFillProfileToCreateFund = false;
			getViewState().showCreateFundActivity();
		}
	}

	void onPrivateStatusOptionSelected(Integer position, String text) {
		switch (position) {
			case 0:
				this.privateStatus = DashboardAssetStatus.ACTIVE;
				break;
			case 1:
				this.privateStatus = DashboardAssetStatus.ALL;
				break;
		}
		settingsManager.saveTradingPrivateStatus(privateStatus);
		getViewState().setPrivateStatus(text, position);
		getPrivate();
	}

	void onPublicStatusOptionSelected(Integer position, String text) {
		switch (position) {
			case 0:
				this.publicStatus = DashboardAssetStatus.ACTIVE;
				break;
			case 1:
				this.publicStatus = DashboardAssetStatus.ALL;
				break;
		}
		settingsManager.saveTradingPublicStatus(publicStatus);
		getViewState().setPublicStatus(text, position);
		getPublic();
	}
}
