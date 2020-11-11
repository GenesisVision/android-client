package vision.genesis.clientapp.feature.main.fund.self_managed.make_public;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.FundCreateAssetPlatformInfo;
import io.swagger.client.model.MakeSelfManagedFundPublicRequest;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ValidatorUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2020.
 */

@InjectViewState
public class MakePublicFundPresenter extends MvpPresenter<MakePublicFundView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription makePublicFundSubscription;

	private Subscription platformInfoSubscription;

	private String title = "";

	private String description = "";

	private double entryFee = 0;

	private double exitFee = 0;

	private double maxEntryFee = 0;

	private double maxExitFee = 0;

	private MakeSelfManagedFundPublicRequest request;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (makePublicFundSubscription != null) {
			makePublicFundSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(MakeSelfManagedFundPublicRequest request) {
		this.request = request;
	}

	void onTitleChanged(String title) {
		this.title = title.trim();
		checkTitleError();
		checkButtonAvailability();
	}

	void onDescriptionChanged(String description) {
		this.description = description.trim();
		checkDescriptionError();
		checkButtonAvailability();
	}

	void onTitleFocusLost() {
		if (this.title.length() < Constants.MIN_ASSET_NAME_LENGTH && context != null) {
			getViewState().showTitleError(String.format(Locale.getDefault(), context.getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_NAME_LENGTH));
		}
	}

	void onDescriptionFocusLost() {
		if (this.description.length() < Constants.MIN_ASSET_DESCRIPTION_LENGTH && context != null) {
			getViewState().showDescriptionError(String.format(Locale.getDefault(), context.getString(R.string.template_minimum_symbols), Constants.MIN_ASSET_DESCRIPTION_LENGTH));
		}
	}

	private void checkTitleError() {
		if (this.title.isEmpty()) {
			getViewState().cleanTitleError();
			return;
		}
		if (!ValidatorUtil.isTitleValid(this.title) && context != null) {
			getViewState().showTitleError(context.getString(R.string.error_title_not_valid));
		}
		else if (this.title.length() >= Constants.MIN_ASSET_NAME_LENGTH) {
			getViewState().cleanTitleError();
		}
	}

	private void checkDescriptionError() {
		if (this.description.isEmpty()) {
			getViewState().cleanDescriptionError();
			return;
		}
		if (this.description.length() >= Constants.MIN_ASSET_DESCRIPTION_LENGTH) {
			getViewState().cleanDescriptionError();
		}
	}

	void onEntryFeeChanged(String entryFeeString) {
		try {
			entryFee = Double.parseDouble(entryFeeString);
		} catch (NumberFormatException e) {
			entryFee = 0;
		}
		if (entryFee > maxEntryFee) {
			getViewState().setEntryFee(maxEntryFee);
			return;
		}

		checkButtonAvailability();
	}

	void onExitFeeChanged(String exitFeeString) {
		try {
			exitFee = Double.parseDouble(exitFeeString);
		} catch (NumberFormatException e) {
			exitFee = 0;
		}
		if (exitFee > maxExitFee) {
			getViewState().setExitFee(maxExitFee);
			return;
		}

		checkButtonAvailability();
	}

	private void checkButtonAvailability() {
		boolean titleOk = this.title.length() >= Constants.MIN_ASSET_NAME_LENGTH
				&& this.title.length() <= Constants.MAX_ASSET_NAME_LENGTH
				&& ValidatorUtil.isTitleValid(this.title);
		boolean descriptionOk = this.description.length() >= Constants.MIN_ASSET_DESCRIPTION_LENGTH
				&& this.description.length() <= Constants.MAX_ASSET_DESCRIPTION_LENGTH;
		boolean entryFeeOk = entryFee <= maxEntryFee;
		boolean exitFeeOk = exitFee <= maxExitFee;

		getViewState().setButtonEnabled(titleOk && descriptionOk && entryFeeOk && exitFeeOk);
	}

	void onMakePublicFundClicked() {
		request.setTitle(this.title);
		request.setDescription(this.description);
		request.setEntryFee(entryFee);
		request.setExitFee(exitFee);
		makePublicFund();
	}

	private void getPlatformInfo() {
		if (settingsManager != null) {
			platformInfoSubscription = settingsManager.getPlatformInfo()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetPlatformInfoSuccess,
							this::handleGetPlatformInfoError);
		}
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();
		FundCreateAssetPlatformInfo info = platformInfo.getAssetInfo().getFundInfo().getCreateFundInfo();
		maxEntryFee = info.getMaxEntryFee();
		maxExitFee = info.getMaxExitFee();
		getViewState().updateEntryFeeDescription(maxEntryFee);
		getViewState().updateExitFeeDescription(maxExitFee);
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void makePublicFund() {
		if (assetsManager != null && request != null) {
			getViewState().showProgress(true);

			makePublicFundSubscription = assetsManager.makePublicFund(request)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleMakePublicFundSuccess, this::handleMakePublicFundError);
		}
	}

	private void handleMakePublicFundSuccess(Void response) {
		makePublicFundSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleMakePublicFundError(Throwable throwable) {
		makePublicFundSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}