package vision.genesis.clientapp.feature.main.fund.reallocate;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundAssetPart;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnFundAssetsChangedEvent;
import vision.genesis.clientapp.model.events.OnFundAssetsConfirmClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ReallocateFundPresenter extends MvpPresenter<ReallocateFundView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription updateSubscription;

	private UUID assetId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

	}

	@Override
	public void onDestroy() {
		if (updateSubscription != null) {
			updateSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID assetId) {
		this.assetId = assetId;
	}

	private void updateFundAssets(List<FundAssetPart> requestAssets) {
		getViewState().showProgress(true);

		if (assetsManager != null) {
			updateSubscription = assetsManager.updateFundAssets(assetId, requestAssets)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> handleUpdateSuccess(requestAssets),
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(List<FundAssetPart> assets) {
		updateSubscription.unsubscribe();

		EventBus.getDefault().post(new OnFundAssetsChangedEvent(assets));
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updateSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnFundAssetsConfirmClickedEvent event) {
		updateFundAssets(event.getRequestAssets());
	}
}