package vision.genesis.clientapp.feature.main.fund.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.FundDetailsFull;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.model.User;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

@InjectViewState
public class FundInfoPresenter extends MvpPresenter<FundInfoView>
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

		super.onDestroy();
	}

	void setFundId(UUID fundId) {
		this.fundId = fundId;
		getFundDetails();
	}

	void onShow() {
		getFundDetails();
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
		request.setFundLogo(fundDetails.getLogo());
		request.setFundColor(fundDetails.getColor());
		request.setFundName(fundDetails.getTitle());
		request.setManagerName(fundDetails.getManager().getUsername());

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
		request.setFundLogo(fundDetails.getLogo());
		request.setFundColor(fundDetails.getColor());
		request.setFundName(fundDetails.getTitle());
		request.setManagerName(fundDetails.getManager().getUsername());

		getViewState().showWithdrawFundActivity(request);
	}
}
