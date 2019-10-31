package vision.genesis.clientapp.feature.main.fund.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import io.swagger.client.model.NewFundRequest;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.events.OnCreateFundCreateButtonClickedEvent;
import vision.genesis.clientapp.model.events.OnCreateFundNextButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

@InjectViewState
public class CreateFundPresenter extends MvpPresenter<CreateFundView>
{
	@Inject
	public Context context;

	@Inject
	public FundsManager fundsManager;

	private NewFundRequest request = new NewFundRequest();

	private Subscription minDepositAmountSubscription;

	private Subscription createFundSubscription;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		request.setAssets(new ArrayList<>());
		getViewState().setRequestObjectToFragments(request);

		getMinDepositAmount();
	}

	@Override
	public void onDestroy() {
		if (minDepositAmountSubscription != null) {
			minDepositAmountSubscription.unsubscribe();
		}
		if (createFundSubscription != null) {
			createFundSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void getMinDepositAmount() {
		minDepositAmountSubscription = fundsManager.getMinDepositAmountToCreate()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetMinDepositAmountSuccess,
						this::handleGetMinDepositAmountError);
	}

	private void handleGetMinDepositAmountSuccess(Double minDepositAmount) {
		minDepositAmountSubscription.unsubscribe();
		getViewState().setMinDepositAmount(minDepositAmount);
	}

	private void handleGetMinDepositAmountError(Throwable throwable) {
		minDepositAmountSubscription.unsubscribe();
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void sendCreateFundRequest() {
		getViewState().showProgress(true);

		createFundSubscription = fundsManager.sendCreateFundRequest(request)
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
	public void onEventMainThread(OnCreateFundNextButtonClickedEvent event) {
		getViewState().showNextStep();
	}

	@Subscribe
	public void onEventMainThread(OnCreateFundCreateButtonClickedEvent event) {
		sendCreateFundRequest();
	}
}