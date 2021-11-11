package vision.genesis.clientapp.feature.main.fund.manage;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.AssetTypeExt;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.TwoFactorCodeModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnCheckTfaConfirmClickedEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaErrorEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaSuccessEvent;
import vision.genesis.clientapp.model.events.OnFundAssetsChangedEvent;
import vision.genesis.clientapp.model.events.OnFundSettingsChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ManageFundPresenter extends MvpPresenter<ManageFundView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	@Inject
	public AuthManager authManager;

	private Subscription userSubscription;

	private Subscription closeFundSubscription;

	private FundDetailsFull details;

	private Boolean twoFactorEnabled = false;

	private boolean tfaEnabled = false;

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
		if (closeFundSubscription != null) {
			closeFundSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(FundDetailsFull details) {
		this.details = details;
	}

	void onChangeSettingsClicked() {
		ProgramUpdate model = new ProgramUpdate();
		model.setTitle(details.getPublicInfo().getTitle());
		model.setDescription(details.getPublicInfo().getDescription());
		model.setLogo(details.getPublicInfo().getLogo());
		model.setEntryFee(details.getEntryFeeSelected());
		model.setExitFee(details.getExitFeeSelected());

		getViewState().showChangeSettingsActivity(details.getId(), model);
	}

	void onChangeAssetsClicked() {
		String reallocationInfo = String.format(Locale.getDefault(), context.getString(R.string.template_reallocation_available), details.getPersonalDetails().getAvailableReallocationPercents());
		if (details.getPublicInfo().getTypeExt() != null && !details.getPublicInfo().getTypeExt().equals(AssetTypeExt.SELFMANAGEDFUND)) {
			reallocationInfo = reallocationInfo.concat("\n").concat(context.getString(R.string.reallocation_info));
		}

		getViewState().showReallocateFundActivity(details.getId(), reallocationInfo, details.getAssetsStructure());
	}

	void onCloseFundClicked() {
		if (twoFactorEnabled) {
			getViewState().showCheckTfaActivity();
		}
		else {
			closeFund(null);
		}
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated);
	}

	private void userUpdated(User user) {
		this.twoFactorEnabled = user.getTwoFactorStatus();
	}

	private void closeFund(String code) {
		if (details != null && assetsManager != null) {
			getViewState().showProgress(true);
			TwoFactorCodeModel model = new TwoFactorCodeModel();
			model.setTwoFactorCode(code);
			closeFundSubscription = assetsManager.closeFund(details.getId(), model)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCloseFundSuccess,
							this::handleCloseFundError);
		}
	}

	private void handleCloseFundSuccess(Void response) {
		closeFundSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (tfaEnabled) {
			EventBus.getDefault().post(new OnCheckTfaSuccessEvent());
		}

		getViewState().finishActivity();
	}

	private void handleCloseFundError(Throwable throwable) {
		closeFundSubscription.unsubscribe();
		getViewState().showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (tfaEnabled) {
				EventBus.getDefault().post(new OnCheckTfaErrorEvent(context.getResources().getString(R.string.network_error)));
			}
			else {
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			}
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.property == null || error.property.isEmpty()) {
						if (tfaEnabled) {
							EventBus.getDefault().post(new OnCheckTfaErrorEvent(error.message));
						}
						else {
							getViewState().showSnackbarMessage(error.message);
						}
					}
				}
			}
			tfaEnabled = false;
		}
	}

	@Subscribe
	public void onEventMainThread(OnFundSettingsChangedEvent event) {
		details.setEntryFeeSelected(event.getModel().getEntryFee());
		details.setExitFeeSelected(event.getModel().getExitFee());
		getViewState().updateView(details);
	}

	@Subscribe
	public void onEventMainThread(OnFundAssetsChangedEvent event) {
//		details.setAssetsStructure(event.getNewAssets());
		details.getPersonalDetails().getOwnerActions().setCanReallocate(false);
		getViewState().updateView(details);
	}

	@Subscribe
	public void onEventMainThread(OnCheckTfaConfirmClickedEvent event) {
		tfaEnabled = true;
		closeFund(event.getCode());
	}
}
