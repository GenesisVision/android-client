package vision.genesis.clientapp.feature.main.manager.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.PublicProfile;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

@InjectViewState
public class ManagerInfoPresenter extends MvpPresenter<ManagerInfoView>
{
	@Inject
	public AuthManager authManager;

	@Inject
	public ProfileManager profileManager;

	private Subscription managersDetailsSubscription;

	private UUID managerId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getManagerDetails();
	}

	@Override
	public void onDestroy() {
		if (managersDetailsSubscription != null)
			managersDetailsSubscription.unsubscribe();

		super.onDestroy();
	}

	void setManagerId(UUID programId) {
		this.managerId = programId;
		getManagerDetails();
	}

	void onShow() {
		getManagerDetails();
	}

	private void getManagerDetails() {
		if (managerId != null && profileManager != null) {
			if (managersDetailsSubscription != null)
				managersDetailsSubscription.unsubscribe();
			managersDetailsSubscription = profileManager.getProfilePublic(managerId.toString())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleManagerDetailsSuccess,
							this::handleInvestmentProgramDetailsError);
		}
	}

	private void handleManagerDetailsSuccess(PublicProfile managerDetails) {
		managersDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().setManagerDetails(managerDetails);
	}

	private void handleInvestmentProgramDetailsError(Throwable throwable) {
		managersDetailsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}
}
