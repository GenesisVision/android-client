package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.PlatformAsset;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.OnAddAssetAssetSelectedEvent;
import vision.genesis.clientapp.model.events.OnAddAssetClickedEvent;
import vision.genesis.clientapp.model.events.OnFinishAddAssetActivityEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */

@InjectViewState
public class AddAssetPresenter extends MvpPresenter<AddAssetView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	private Subscription platformInfoSubscription;

	private List<PlatformAsset> allAssets = new ArrayList<>();

	private boolean finishAfterAssetSelected = true;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
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

	void setNeedFinish(boolean finishAfterAssetSelected) {
		this.finishAfterAssetSelected = finishAfterAssetSelected;
	}

	void onMaskChanged(String mask) {
		if (mask != null && !mask.trim().isEmpty()) {
			filterAssets(mask.trim().toLowerCase());
		}
		else {
			getViewState().setAssets(allAssets);
		}
	}

	private void filterAssets(String mask) {
		List<PlatformAsset> assetsToShow = new ArrayList<>();
		for (PlatformAsset asset : allAssets) {
			if (asset.getAsset().toLowerCase().contains(mask)
					|| asset.getName().toLowerCase().contains(mask)) {
				assetsToShow.add(asset);
			}
		}
		getViewState().setAssets(assetsToShow);
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
		getViewState().showProgress(false);
		allAssets = platformInfo.getEnums().getFund().getAssets();
		getViewState().setAssets(allAssets);
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnAddAssetClickedEvent event) {
		EventBus.getDefault().post(new OnAddAssetAssetSelectedEvent(event.getAsset()));
//		if (finishAfterAssetSelected) {
		getViewState().finishActivity();
//		}
	}

	@Subscribe
	public void onEventMainThread(OnFinishAddAssetActivityEvent event) {
		getViewState().finishActivity();
	}
}