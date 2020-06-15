package vision.genesis.clientapp.feature.main.fund.info.owner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
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

	private Subscription userSubscription;

	private Subscription fundDetailsSubscription;

	private UUID fundId;

	private Boolean userLoggedOn;

	private FundDetailsFull fundDetails;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
		getViewState().showProgress(true);
		getFundDetails();
	}

	@Override
	public void onDestroy() {
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
	}

	void onShow() {
		getFundDetails();
	}

	void onEditPublicInfoClicked() {
		ProgramUpdate model = new ProgramUpdate();
		model.setTitle(fundDetails.getPublicInfo().getTitle());
		model.setDescription(fundDetails.getPublicInfo().getDescription());
		model.setLogo(fundDetails.getPublicInfo().getLogoUrl());
		model.setEntryFee(fundDetails.getEntryFeeCurrent());
		model.setExitFee(fundDetails.getExitFeeCurrent());
		getViewState().showEditPublicInfoActivity(fundId, model);
	}

	void onManageFundClicked() {
		getViewState().showManageFundActivity(fundDetails);
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

		getViewState().showWithdrawFundActivity(request);
	}

	private void getFundDetails() {
		if (fundId != null && fundsManager != null) {
			if (fundDetailsSubscription != null) {
				fundDetailsSubscription.unsubscribe();
			}
			fundDetailsSubscription = fundsManager.getFundDetails(fundId, CurrencyEnum.GVT)
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

		getViewState().setFundDetails(fundDetails);
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
