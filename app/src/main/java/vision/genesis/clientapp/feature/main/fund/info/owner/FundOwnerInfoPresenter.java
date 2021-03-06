package vision.genesis.clientapp.feature.main.fund.info.owner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.AssetTypeExt;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.MakeSelfManagedFundPublicRequest;
import io.swagger.client.model.ProgramUpdate;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnFundStatisticsUpdatedEvent;
import vision.genesis.clientapp.model.events.OnRequestCancelledEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/12/2019.
 */

@InjectViewState
public class FundOwnerInfoPresenter extends MvpPresenter<FundOwnerInfoView>
{
	@Inject
	public AuthManager authManager;

	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription userSubscription;

	private Subscription baseCurrencySubscription;

	private Subscription fundDetailsSubscription;

	private UUID fundId;

	private Boolean userLoggedOn;

	private FundDetailsFull fundDetails;

	private CurrencyEnum baseCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		subscribeToBaseCurrency();
		getViewState().showProgress(true);
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}

		if (fundDetailsSubscription != null) {
			fundDetailsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setDetails(FundDetailsFull fundDetails) {
		this.fundId = fundDetails.getId();
		this.fundDetails = fundDetails;

		getFundDetails();
	}

	void onShow() {
		getFundDetails();
	}

	void onEditPublicInfoClicked() {
		ProgramUpdate model = new ProgramUpdate();
		model.setTitle(fundDetails.getPublicInfo().getTitle());
		model.setDescription(fundDetails.getPublicInfo().getDescription());
		model.setLogo(fundDetails.getPublicInfo().getLogo());
		model.setEntryFee(fundDetails.getEntryFeeCurrent());
		model.setExitFee(fundDetails.getExitFeeCurrent());
		boolean showDescription = true;
		if (fundDetails.getPublicInfo().getTypeExt() != null) {
			showDescription = !fundDetails.getPublicInfo().getTypeExt().equals(AssetTypeExt.SELFMANAGEDFUND);
		}
		getViewState().showEditPublicInfoActivity(fundId, model, showDescription);
	}

	void onManageFundClicked() {
		getViewState().showManageFundActivity(fundDetails);
	}

	void onMakePublicFundClicked() {
		MakeSelfManagedFundPublicRequest request = new MakeSelfManagedFundPublicRequest();
		request.setId(fundDetails.getId());
		request.setTitle(fundDetails.getPublicInfo().getTitle());
		getViewState().showMakePublicFundActivity(request);
	}

	void onStatusClicked() {
		if (fundDetails != null && fundDetails.getPersonalDetails() != null) {
			if (fundDetails.getPersonalDetails().getStatus().equals(AssetInvestmentStatus.INVESTING) ||
					(fundDetails.getPersonalDetails().getStatus().equals(AssetInvestmentStatus.WITHDRAWING))) {
				getViewState().showRequestsBottomSheet();
			}
		}
	}

	void onInvestClicked() {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (fundDetails == null) {
			return;
		}

		FundRequest request = new FundRequest();

		request.setFundId(fundDetails.getId());
		request.setFundLogo(fundDetails.getPublicInfo().getLogoUrl());
		request.setFundColor(fundDetails.getPublicInfo().getColor());
		request.setFundName(fundDetails.getPublicInfo().getTitle());
		request.setManagerName(fundDetails.getOwner().getUsername());
		request.setEntryFee(fundDetails.getEntryFeeCurrent());
		request.setIsOwnFund(true);

		getViewState().showInvestFundActivity(request);
	}

	void onWithdrawClicked() {
		if (!userLoggedOn) {
			getViewState().showLoginActivity();
			return;
		}

		if (fundDetails == null) {
			return;
		}

		FundRequest request = new FundRequest();

		request.setFundId(fundDetails.getId());
		request.setFundLogo(fundDetails.getPublicInfo().getLogoUrl());
		request.setFundColor(fundDetails.getPublicInfo().getColor());
		request.setFundName(fundDetails.getPublicInfo().getTitle());
		request.setManagerName(fundDetails.getOwner().getUsername());
		request.setIsOwnFund(true);

		getViewState().showWithdrawFundActivity(request);
	}

	private void subscribeToBaseCurrency() {
		if (settingsManager != null) {
			baseCurrencySubscription = settingsManager.getBaseCurrency()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::baseCurrencyChangedHandler);
		}
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getFundDetails();
	}

	private void getFundDetails() {
		if (fundId != null && fundsManager != null && baseCurrency != null) {
			if (fundDetailsSubscription != null) {
				fundDetailsSubscription.unsubscribe();
			}
			fundDetailsSubscription = fundsManager.getFundDetails(fundId, baseCurrency)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleFundDetailsSuccess,
							this::handleFundDetailsError);
		}
	}

	private void handleFundDetailsSuccess(FundDetailsFull fundDetails) {
		fundDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		this.fundDetails = fundDetails;

		getViewState().setFundDetails(fundDetails, baseCurrency);
	}

	private void handleFundDetailsError(Throwable throwable) {
		fundDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
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
		userLoggedOn = true;
		getViewState().showInvestWithdrawButtons();
	}

	private void userLoggedOff() {
		userLoggedOn = false;
		getViewState().showInvestWithdrawButtons();
	}

	private void handleUserError(Throwable throwable) {
		userLoggedOff();
	}

	@Subscribe
	public void onEventMainThread(OnFundStatisticsUpdatedEvent event) {
		if (event.getFundId().equals(fundId)) {
			getViewState().updateStatistics(event.getStatistic(), event.getBaseCurrency());
		}
	}

	@Subscribe
	public void onEventMainThread(OnRequestCancelledEvent event) {
		getFundDetails();
	}
}
