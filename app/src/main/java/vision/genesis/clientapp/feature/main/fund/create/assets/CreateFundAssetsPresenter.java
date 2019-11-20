package vision.genesis.clientapp.feature.main.fund.create.assets;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.FundAssetPart;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.PlatformAsset;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.fund.create.assets.share.AssetShareDialogFragment;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.OnAddAssetAssetSelectedEvent;
import vision.genesis.clientapp.model.events.OnFinishAddAssetActivityEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.CreateFundAssetView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class CreateFundAssetsPresenter extends MvpPresenter<CreateFundAssetsView>
		implements CreateFundAssetView.Listener, AssetShareDialogFragment.Listener
{
	@Inject
	public FundsManager fundsManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private NewFundRequest request;

	private List<PlatformAsset> assets = new ArrayList<>();

	private PlatformAsset selectedAsset;

	private double remainingShare = 100;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setRequest(NewFundRequest request) {
		this.request = request;
	}

	private void getPlatformInfo() {
		platformInfoSubscription = settingsManager.getPlatformInfo()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetPlatformInfoSuccess,
						this::handleGetPlatformInfoError);
	}

	private void handleGetPlatformInfoSuccess(PlatformInfo platformInfo) {
		platformInfoSubscription.unsubscribe();
		assets = platformInfo.getEnums().getFund().getAssets();

		List<PlatformAsset> mandatoryAssets = new ArrayList<>();
		for (PlatformAsset asset : assets) {
			if (asset.getMandatoryFundPercent() > 0) {
				mandatoryAssets.add(asset);
				updateRequestWithAsset(asset, asset.getMandatoryFundPercent());
			}
		}
		getViewState().updateNotification(mandatoryAssets);
		updateRemainingShare();
	}

	private void updateRequestWithAsset(PlatformAsset asset, Double newShare) {
		FundAssetPart requestAsset = null;
		int index = 0;
		for (FundAssetPart ra : request.getAssets()) {
			if (asset.getId().equals(ra.getId())) {
				requestAsset = ra;
				break;
			}
			index++;
		}
		if (requestAsset == null) {
			FundAssetPart assetToAdd = new FundAssetPart();
			assetToAdd.setId(asset.getId());
			assetToAdd.setPercent(newShare);
			request.getAssets().add(assetToAdd);
			getViewState().addAsset(asset, newShare);
		}
		else if (newShare == 0) {
			onRemoveAssetClicked(asset);
		}
		else {
			requestAsset.setPercent(newShare);
			getViewState().updateAsset(index, newShare);
		}
		updateRemainingShare();
	}

	private void updateRemainingShare() {
		double addedShares = 0.0;
		if (request != null && request.getAssets() != null) {
			for (FundAssetPart asset : request.getAssets()) {
				addedShares += asset.getPercent();
			}
		}
		remainingShare = 100 - addedShares;
		getViewState().updateRemainingShare(remainingShare);
		getViewState().setAddAssetButtonEnabled(remainingShare > 0);
		getViewState().setNextButtonEnabled(addedShares == 100);
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onAssetClicked(PlatformAsset asset) {
		if (selectedAsset != null && selectedAsset.equals(asset)) {
			getViewState().deselectAssets();
			selectedAsset = null;
		}
		else {
			getViewState().selectAsset(asset);
			selectedAsset = asset;
		}
	}

	@Override
	public void onAssetLongClicked(PlatformAsset asset, double share) {
		getViewState().showSelectAssetShareDialog(asset, share, remainingShare);
	}

	@Override
	public void onRemoveAssetClicked(PlatformAsset asset) {
		if (request != null) {
			int index = 0;
			for (FundAssetPart requestAsset : request.getAssets()) {
				if (requestAsset.getId().equals(asset.getId())) {
					if (selectedAsset != null && selectedAsset.equals(asset)) {
						getViewState().deselectAssets();
						selectedAsset = null;
					}
					request.getAssets().remove(requestAsset);
					getViewState().removeAsset(index);
					updateRemainingShare();
					break;
				}
				index++;
			}
		}
	}

	@Subscribe
	public void onEventMainThread(OnAddAssetAssetSelectedEvent event) {
		double share = 0.0;
		for (FundAssetPart ra : request.getAssets()) {
			if (event.getAsset().getId().equals(ra.getId())) {
				share = ra.getPercent();
				break;
			}
		}
		getViewState().showSelectAssetShareDialog(event.getAsset(), share, remainingShare);
	}

	@Override
	public void onAddAsset(PlatformAsset asset, double share) {
		EventBus.getDefault().post(new OnFinishAddAssetActivityEvent());
		updateRequestWithAsset(asset, share);
	}
}
