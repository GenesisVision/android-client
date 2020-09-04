package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.content.Context;
import android.util.Pair;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.AssetProvider;
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
import vision.genesis.clientapp.utils.PlatformAssetsComparator;
import vision.genesis.clientapp.utils.PlatformAssetsPairsComparator;

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

	private ArrayList<Pair<String, List<PlatformAsset>>> sortedAssets = new ArrayList<>();

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

	void onMaskChanged(String mask) {
		if (mask != null && !mask.trim().isEmpty()) {
			filterAssets(mask.trim().toLowerCase());
		}
		else {
			getViewState().setAssets(sortedAssets);
		}
	}

	private void filterAssets(String mask) {
		ArrayList<Pair<String, List<PlatformAsset>>> assetsToShow = new ArrayList<>();

		for (Pair<String, List<PlatformAsset>> pair : sortedAssets) {
			List<PlatformAsset> newList = new ArrayList<>();
			for (PlatformAsset asset : pair.second) {
				if (asset.getAsset().toLowerCase().contains(mask)
						|| asset.getName().toLowerCase().contains(mask)) {
					newList.add(asset);
				}
			}
			Pair<String, List<PlatformAsset>> newPair = new Pair<>(pair.first, newList);
			assetsToShow.add(newPair);
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
		List<PlatformAsset> allAssets = platformInfo.getAssetInfo().getFundInfo().getAssets();

		int i;
		for (PlatformAsset asset : allAssets) {
			if (asset.getProvider() != null && !asset.getProvider().equals(AssetProvider.UNDEFINED)) {
				i = 0;
				for (Pair<String, List<PlatformAsset>> pair : sortedAssets) {
					if (pair.first.equals(asset.getProvider().getValue())) {
						pair.second.add(asset);
						break;
					}
					i++;
				}
				if (i == sortedAssets.size()) {
					ArrayList<PlatformAsset> providerAssets = new ArrayList<>();
					providerAssets.add(asset);
					sortedAssets.add(new Pair<>(asset.getProvider().getValue(), providerAssets));
				}
			}
		}

		for (Pair<String, List<PlatformAsset>> pair : sortedAssets) {
			Collections.sort(pair.second, new PlatformAssetsComparator());
		}
		Collections.sort(sortedAssets, new PlatformAssetsPairsComparator());

		getViewState().setAssets(sortedAssets);
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnAddAssetClickedEvent event) {
		EventBus.getDefault().post(new OnAddAssetAssetSelectedEvent(event.getAsset()));
		getViewState().finishActivity();
	}

	@Subscribe
	public void onEventMainThread(OnFinishAddAssetActivityEvent event) {
		getViewState().finishActivity();
	}
}