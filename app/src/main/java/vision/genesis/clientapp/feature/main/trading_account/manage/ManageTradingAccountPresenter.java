package vision.genesis.clientapp.feature.main.trading_account.manage;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.MigrationRequest;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.events.OnBrokerChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ManageTradingAccountPresenter extends MvpPresenter<ManageTradingAccountView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription cancelMigrationSubscription;

	private TradingAccountDetailsModel model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (cancelMigrationSubscription != null) {
			cancelMigrationSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	void setData(TradingAccountDetailsModel model) {
		this.model = model;
	}

	void onChangeClicked() {
		getViewState().showChangeBrokerActivity(model);
	}

	void onChangePasswordClicked() {
		getViewState().showChangePasswordActivity(model);
	}

	void onCancelMigrationClicked() {
		cancelMigration();
	}

	private void cancelMigration() {
		if (model != null && assetsManager != null) {
			getViewState().showCancelProgress(true);
			cancelMigrationSubscription = assetsManager.cancelBrokerChange(model.getAssetId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleCancelMigrationSuccess,
							this::handleCancelMigrationError);
		}
	}

	private void handleCancelMigrationSuccess(Void response) {
		cancelMigrationSubscription.unsubscribe();

		this.model.setMigration(null);
		getViewState().updateView(model);
		getViewState().showCancelProgress(false);
	}

	private void handleCancelMigrationError(Throwable throwable) {
		cancelMigrationSubscription.unsubscribe();
		getViewState().showCancelProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnBrokerChangedEvent event) {
		MigrationRequest migration = new MigrationRequest();
		migration.setNewBroker(event.getNewBroker());
		migration.setNewLeverage(event.getNewLeverage());
		migration.setDateCreate(event.getCreationDate());
		this.model.setMigration(migration);
		getViewState().updateView(model);
	}
}
