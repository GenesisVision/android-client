package vision.genesis.clientapp.feature.main.follow.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.MakeTradingAccountSignalProvider;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.events.OnCreateProgramSuccessEvent;
import vision.genesis.clientapp.model.events.OnFollowSettingsConfirmEvent;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/12/2019.
 */

@InjectViewState
public class CreateFollowPresenter extends MvpPresenter<CreateFollowView>
{
	@Inject
	public Context context;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public AssetsManager assetsManager;

	private Subscription createFollowSubscription;

	private CreateProgramModel model;

	private String title;

	private String description;

	private String logo;

	private Double volumeFee;

	private Double successFee;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (createFollowSubscription != null) {
			createFollowSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(CreateProgramModel model) {
		this.model = model;

		getViewState().initViewPager(model.getAssetType().equals(AssetType.NONE));
	}

	private void sendCreateFollowRequest() {
		getViewState().showProgress(true);

		Observable<Void> apiRequest = null;

		if (model.getAssetType().equals(AssetType.NONE)) {
			MakeTradingAccountSignalProvider accountRequest = new MakeTradingAccountSignalProvider();

			accountRequest.setTradingAccountId(model.getAssetId());
			accountRequest.setTitle(this.title);
			accountRequest.setDescription(this.description);
			accountRequest.setLogo(this.logo);

			accountRequest.setVolumeFee(this.volumeFee);
			accountRequest.setSuccessFee(this.successFee);

			apiRequest = assetsManager.createFollowFromTradingAccount(accountRequest);
		}
		else if (model.getAssetType().equals(AssetType.FOLLOW) || model.getAssetType().equals(AssetType.PROGRAM)) {
			CreateSignalProvider programRequest = new CreateSignalProvider();

			programRequest.setAssetId(model.getAssetId());

			programRequest.setVolumeFee(this.volumeFee);
			programRequest.setSuccessFee(this.successFee);

			apiRequest = assetsManager.updateSignalProviderSettings(programRequest);
		}

		if (apiRequest != null) {
			createFollowSubscription = apiRequest
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleCreateFollowSuccess,
							this::handleCreateFollowError);
		}
	}

	private void handleCreateFollowSuccess(Void response) {
		createFollowSubscription.unsubscribe();
		EventBus.getDefault().post(new OnCreateProgramSuccessEvent(model.getAssetId()));
		getViewState().finishActivity();
	}

	private void handleCreateFollowError(Throwable throwable) {
		createFollowSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnPublicInfoConfirmButtonClickedEvent event) {
		this.title = event.getTitle();
		this.description = event.getDescription();
		this.logo = event.getLogo();

		getViewState().showSettings();
	}

	@Subscribe
	public void onEventMainThread(OnFollowSettingsConfirmEvent event) {
		this.volumeFee = event.getVolumeFee();
		this.successFee = event.getSuccessFee();

		sendCreateFollowRequest();
	}
}