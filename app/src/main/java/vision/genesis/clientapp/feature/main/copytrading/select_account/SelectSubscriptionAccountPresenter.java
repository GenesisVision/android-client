package vision.genesis.clientapp.feature.main.copytrading.select_account;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.TradingAccountDetails;
import io.swagger.client.model.TradingAccountDetailsItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.FollowsManager;
import vision.genesis.clientapp.model.events.OnSelectSubscriptionTradingAccountConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */


@InjectViewState
public class SelectSubscriptionAccountPresenter extends MvpPresenter<SelectSubscriptionAccountView> implements SelectOptionBottomSheetFragment.OnOptionSelectedListener
{
	@Inject
	public FollowsManager followsManager;

	private Subscription getAccountsSubscription;

	private ProgramFollowDetailsFull followDetails;

	private List<TradingAccountDetails> accounts = new ArrayList<>();

	private TradingAccountDetails selectedAccount;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getAccounts();
	}

	@Override
	public void onDestroy() {
		if (getAccountsSubscription != null) {
			getAccountsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onResume() {
		getAccounts();
	}

	void setData(ProgramFollowDetailsFull followDetails) {
		this.followDetails = followDetails;

		getAccounts();
	}

	void onConfirmClicked() {
		EventBus.getDefault().post(new OnSelectSubscriptionTradingAccountConfirmEvent(selectedAccount.getId()));
	}

	private void updateConfirmButtonEnabled() {
		getViewState().setConfirmButtonEnabled(selectedAccount != null);
	}

	private void getAccounts() {
		if (followsManager != null && followDetails != null) {
			if (getAccountsSubscription != null) {
				getAccountsSubscription.unsubscribe();
			}
			getAccountsSubscription = followsManager.getSubscriberAccounts(followDetails.getId())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleGetAccountsSuccess,
							this::handleGetAccountsError);
		}
	}

	private void handleGetAccountsSuccess(TradingAccountDetailsItemsViewModel response) {
		getAccountsSubscription.unsubscribe();
		getViewState().showProgress(false);
		if (response.getTotal() == 0) {
			getViewState().showNoAccounts(true);
			return;
		}

		getViewState().showNoAccounts(false);
		this.accounts = response.getItems();
		ArrayList<String> accountOptions = new ArrayList<>();
		for (TradingAccountDetails account : accounts) {
			accountOptions.add(String.format(Locale.getDefault(), "%s | %s",
					account.getAsset() != null ? account.getAsset().getTitle() : account.getLogin(),
					account.getCurrency().getValue()));
		}
		getViewState().setAccountOptions(accountOptions);
		onOptionSelected(0, accountOptions.get(0));
	}

	private void handleGetAccountsError(Throwable throwable) {
		getAccountsSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onOptionSelected(Integer position, String text) {
		this.selectedAccount = accounts.get(position);
		getViewState().setAccount(text, position);
		updateConfirmButtonEnabled();
	}
}
