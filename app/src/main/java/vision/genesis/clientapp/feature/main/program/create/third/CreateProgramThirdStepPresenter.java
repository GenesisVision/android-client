package vision.genesis.clientapp.feature.main.program.create.third;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CreateProgramData;
import vision.genesis.clientapp.model.events.OnCreateProgramThirdStepPassedEvent;
import vision.genesis.clientapp.model.events.SetCreateProgramDataEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/07/2018.
 */

@InjectViewState
public class CreateProgramThirdStepPresenter extends MvpPresenter<CreateProgramThirdStepView>
{
	@Inject
	public WalletManager walletManager;

	private CreateProgramData createProgramData;

	private Integer period;

	private Double deposit;

	private Double successFee;

	private Double managementFee;

	private String tokenSymbol;

	private String tokenName;

	private Subscription balanceSubscription;

	private Double balance;

	private DateTime startDate;

	@Override

	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getBalance();
	}

	@Override
	public void onDestroy() {
		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void getBalance() {
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetBalanceResponse,
						this::handleGetBalanceError);
	}

	private void handleGetBalanceResponse(Double balance) {
		balanceSubscription.unsubscribe();
		this.balance = balance;
		getViewState().setBalance(balance);
	}

	private void handleGetBalanceError(Throwable error) {
		balanceSubscription.unsubscribe();
	}

	private void checkCreateButtonAvailability() {
		if (createProgramData == null)
			return;
		boolean depositOk = deposit != null && deposit <= balance;
		boolean successFeeOk = successFee != null && successFee <= createProgramData.getMaxFeeSuccess();
		boolean managementFeeOk = managementFee != null && managementFee <= createProgramData.getMaxFeeManagement();
		boolean tokenSymbolOk = !tokenSymbol.isEmpty() && tokenSymbol.length() <= createProgramData.getMaxTokenSymbolLength();
		boolean tokenNameOk = !tokenName.isEmpty() && tokenName.length() <= createProgramData.getMaxTokenNameLength();
		getViewState().setCreateButtonAvailability(period != null && startDate != null
				&& depositOk && successFeeOk && managementFeeOk
				&& tokenSymbolOk && tokenNameOk);
	}

	public void onPeriodSelected(int position) {
		this.period = createProgramData.getAvailablePeriods().get(position);
		checkCreateButtonAvailability();
	}

	public void onStartDateButtonClicked() {
		getViewState().showDatePicker();
	}

	public void onStartDateChanged(DateTime startDay) {
		this.startDate = startDay;
		checkCreateButtonAvailability();
	}

	public void onDepositChanged(String depositString) {
		try {
			this.deposit = Double.parseDouble(depositString);
		} catch (NumberFormatException e) {
			deposit = null;
		}
		checkCreateButtonAvailability();
	}

	void onSuccessFeeChanged(String successFeeString) {
		try {
			this.successFee = Double.parseDouble(successFeeString);
		} catch (NumberFormatException e) {
			successFee = null;
		}
		checkCreateButtonAvailability();
	}

	void onManagementFeeChanged(String managementFeeString) {
		try {
			this.managementFee = Double.parseDouble(managementFeeString);
		} catch (NumberFormatException e) {
			managementFee = null;
		}
		checkCreateButtonAvailability();
	}

	public void onTokenNameChanged(String tokenName) {
		this.tokenName = tokenName;
		checkCreateButtonAvailability();
	}

	public void onTokenSymbolChanged(String tokenSymbol) {
		this.tokenSymbol = tokenSymbol;
		checkCreateButtonAvailability();
	}

	void onCreateButtonClicked() {
		EventBus.getDefault().post(new OnCreateProgramThirdStepPassedEvent(period, startDate, deposit, successFee, managementFee, tokenSymbol, tokenName));
	}

	@Subscribe
	public void onEventMainThread(SetCreateProgramDataEvent event) {
		this.createProgramData = event.getCreateProgramData();
		getViewState().setPeriods(createProgramData.getAvailablePeriods());
		getViewState().setLimits(createProgramData);
	}
}