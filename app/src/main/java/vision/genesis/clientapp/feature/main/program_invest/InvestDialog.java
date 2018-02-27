package vision.genesis.clientapp.feature.main.program_invest;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramDetails;
import io.swagger.client.model.WalletsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.InvestManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.NewInvestmentSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.ui.AmountEditText;

/**
 * GenesisVision
 * Created by Vitaly on 2/20/18.
 */

public class InvestDialog extends AppCompatDialog
{
	@Inject
	public WalletManager walletManager;

	@Inject
	public InvestManager investManager;

	@BindView(R.id.text_balance)
	public TextView balanceText;

	@BindView(R.id.edittext_amount)
	public AmountEditText amountEditText;

	@BindView(R.id.button_invest)
	public Button investButton;

	private Subscription balanceSubscription;

	private Subscription investSubscription;

	private double balance = 0;

	private double amount = 0;

	private InvestmentProgramDetails program;

	public InvestDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);

		createDialog();
	}

	public void setProgram(InvestmentProgramDetails program) {
		this.program = program;
	}

	@OnClick(R.id.button_invest)
	public void OnInvestClicked() {
		sendInvestRequest();
	}

	@OnClick(R.id.button_cancel)
	public void OnCancelClicked() {
		closeDialog();
	}

	private void createDialog() {
		setContentView(R.layout.dialog_invest);
		ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		balanceText.setText(String.format(Locale.getDefault(), "%s:", getContext().getResources().getString(R.string.balance)));
		getBalance();
		setInvestButtonEnabled();

		setAmountTextListener();
	}

	private void setAmountTextListener() {
		amountEditText.setListener(newAmount -> {
			amount = newAmount;
			setInvestButtonEnabled();
		});
	}

	private void setInvestButtonEnabled() {
		boolean enabled = amount > 0 && amount <= balance;
		investButton.setEnabled(enabled);
		investButton.setTextColor(ContextCompat.getColor(getContext(), enabled ? R.color.colorPrimary : R.color.colorFontMedium));
	}

	private void sendInvestRequest() {
		investSubscription = investManager.invest(program.getId(), amount)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleInvestSuccess,
						this::handleInvestError);
	}

	private void handleInvestSuccess(WalletsViewModel model) {
		investSubscription.unsubscribe();
		walletManager.getBalance();

		closeDialog();
		EventBus.getDefault().post(new NewInvestmentSuccessEvent());

	}

	private void handleInvestError(Throwable throwable) {
		investSubscription.unsubscribe();

		if (ApiErrorResolver.isNetworkError(throwable)) {
			showToastMessage(getContext().getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.message != null) {
						showToastMessage(error.message);
						break;
					}
				}
			}
		}
	}

	private void getBalance() {
		showBalanceProgress(true);
		balanceSubscription = walletManager.getBalance()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleBalanceUpdateResponse,
						this::handleBalanceUpdateError);
	}

	private void handleBalanceUpdateResponse(Double balance) {
		showBalanceProgress(false);
		setBalance(balance);
	}

	private void handleBalanceUpdateError(Throwable error) {
		showBalanceProgress(false);
	}

	private void setBalance(Double balance) {
		this.balance = balance;
		DecimalFormat df = new DecimalFormat("0.####");
		df.setRoundingMode(RoundingMode.DOWN);
		balanceText.setText(String.format(Locale.getDefault(), "%s: %s GVT", getContext().getResources().getString(R.string.balance), df.format(balance)));
	}

	private void showBalanceProgress(boolean show) {

	}

	private void showToastMessage(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}

	private void closeDialog() {
		if (balanceSubscription != null)
			balanceSubscription.unsubscribe();
		if (investSubscription != null)
			investSubscription.unsubscribe();
		this.cancel();
	}
}
