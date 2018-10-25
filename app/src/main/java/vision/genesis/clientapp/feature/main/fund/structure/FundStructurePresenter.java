package vision.genesis.clientapp.feature.main.fund.structure;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.FundAssetsListInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.FundsManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

@InjectViewState
public class FundStructurePresenter extends MvpPresenter<FundStructureView>
{
	@Inject
	public FundsManager fundsManager;

	private Subscription assetsSubscription;

	private UUID fundId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showProgress(true);
		getAssets();
	}

	@Override
	public void onDestroy() {
		if (assetsSubscription != null)
			assetsSubscription.unsubscribe();

		super.onDestroy();
	}

	void setFundId(UUID programId) {
		this.fundId = programId;
		getAssets();
	}

	void onShow() {
		getAssets();
	}

	void onSwipeRefresh() {
		getViewState().showProgress(true);
		getAssets();
	}

	private void getAssets() {
		if (fundId != null && fundsManager != null) {
			if (assetsSubscription != null)
				assetsSubscription.unsubscribe();
			assetsSubscription = fundsManager.getFundAssets(fundId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetAssetsSuccess,
							this::handleGetAssetsError);
		}
	}

	private void handleGetAssetsSuccess(FundAssetsListInfo response) {
		assetsSubscription.unsubscribe();
		getViewState().showProgress(false);

		getViewState().setAssets(response.getAssets());
	}

	private void handleGetAssetsError(Throwable throwable) {
		assetsSubscription.unsubscribe();
		getViewState().showProgress(false);
	}

}
