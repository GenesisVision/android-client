package vision.genesis.clientapp.feature.common.public_info.edit;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramUpdate;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnPublicInfoConfirmButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/11/2019.
 */

@InjectViewState
public class EditPublicInfoPresenter extends MvpPresenter<EditPublicInfoView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription updatePublicInfoSubscription;

	private ProgramUpdate model;

	private UUID assetId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

	}

	@Override
	public void onDestroy() {
		if (updatePublicInfoSubscription != null) {
			updatePublicInfoSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID assetId, ProgramUpdate model) {
		this.assetId = assetId;
		this.model = model;
	}

	private void updatePublicInfo() {
		getViewState().showProgress(true);

		if (assetsManager != null) {
			updatePublicInfoSubscription = assetsManager.updatePublicInfo(assetId, model)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleUpdateSuccess,
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(Void response) {
		updatePublicInfoSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updatePublicInfoSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnPublicInfoConfirmButtonClickedEvent event) {
		model.setTitle(event.getTitle());
		model.setDescription(event.getDescription());
		model.setLogo(event.getLogo());

		updatePublicInfo();
	}
}