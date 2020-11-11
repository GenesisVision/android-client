package vision.genesis.clientapp.feature.main.fund.self_managed.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.NewSelfManagedFundRequest;
import io.swagger.client.model.PlatformInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.OnCreateFundCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnFundAssetsConfirmClickedEvent;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/11/2020.
 */

@InjectViewState
public class CreateSelfManagedFundPresenter extends MvpPresenter<CreateSelfManagedFundView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public AssetsManager assetsManager;

	private NewSelfManagedFundRequest request = new NewSelfManagedFundRequest();

	private Subscription platformInfoSubscription;

	private Subscription createFundSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		request.setAssets(new ArrayList<>());

		getPlatformInfo();
	}

	@Override
	public void onDestroy() {
		if (platformInfoSubscription != null) {
			platformInfoSubscription.unsubscribe();
		}
		if (createFundSubscription != null) {
			createFundSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
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
		getViewState().setMinDepositAmount(platformInfo.getAssetInfo().getFundInfo().getCreateFundInfo().getMinDeposit());
	}

	private void handleGetPlatformInfoError(Throwable throwable) {
		platformInfoSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void sendCreateFundRequest() {
		getViewState().showProgress(true);

		createFundSubscription = assetsManager.createSelfManagedFund(request)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleCreateFundSuccess, this::handleCreateFundError);
	}

	private void handleCreateFundSuccess(Void response) {
		createFundSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleCreateFundError(Throwable throwable) {
		createFundSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnPublicInfoConfirmButtonClickedEvent event) {
		if (request != null) {
			request.setTitle(event.getTitle());
			request.setLogo(event.getLogo());

			getViewState().showNextStep();
		}
	}

	@Subscribe
	public void onEventMainThread(OnFundAssetsConfirmClickedEvent event) {
		request.setAssets(event.getRequestAssets());
		getViewState().showNextStep();
	}

	@Subscribe
	public void onEventMainThread(OnCreateFundCreateButtonClickedEvent event) {
		request.setDepositAmount(event.getAmount());
		request.setDepositWalletId(event.getWalletId());
		sendCreateFundRequest();
	}
}