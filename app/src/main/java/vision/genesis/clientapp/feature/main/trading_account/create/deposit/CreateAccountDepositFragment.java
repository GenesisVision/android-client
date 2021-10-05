package vision.genesis.clientapp.feature.main.trading_account.create.deposit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.NewTradingAccountRequest;
import io.swagger.client.model.WalletData;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class CreateAccountDepositFragment extends BaseFragment implements CreateAccountDepositView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static CreateAccountDepositFragment with(CreateAccountModel model) {
		CreateAccountDepositFragment fragment = new CreateAccountDepositFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_MODEL, model);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_step)
	public ViewGroup stepGroup;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;


	@BindView(R.id.group_account_info)
	public ViewGroup accountInfoGroup;

	@BindView(R.id.broker_logo)
	public SimpleDraweeView brokerLogo;

	@BindView(R.id.account_currency)
	public TextView accountCurrency;

	@BindView(R.id.leverage)
	public TextView leverage;


	@BindView(R.id.deposit_notification)
	public TextView depositNotification;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.label_amount_to_deposit)
	public TextView amountToDepositLabel;

	@BindView(R.id.icon_from)
	public SimpleDraweeView iconFrom;

	@BindView(R.id.wallet_from)
	public TextView walletFrom;

	@BindView(R.id.available_from)
	public TextView availableFrom;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.amount_currency)
	public TextView amountCurrency;

	@BindView(R.id.max)
	public TextView max;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.button_create_account)
	public PrimaryButton createAccountButton;

	@BindView(R.id.rate_progress_bar)
	public ProgressBar rateProgressBar;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public CreateAccountDepositPresenter presenter;

	private List<WalletData> wallets;

	private Unbinder unbinder;

	private NewTradingAccountRequest request;

	private Map<String, Double> minDepositAmountInfo;

	private String minDepositCurrency;

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectWalletBottomSheetFragment fragment = new SelectWalletBottomSheetFragment();
		fragment.setData(getString(R.string.select_wallet), wallets);
		fragment.setListener(presenter);
		fragment.show(getChildFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_edittext_amount)
	public void onAmountClicked() {
		showSoftKeyboard();
	}

	@OnClick(R.id.label_amount_to_deposit)
	public void onMinClicked() {
		presenter.onMinClicked();
	}

	@OnClick(R.id.max)
	public void onMaxClicked() {
		presenter.onMaxClicked();
	}

	@OnClick(R.id.button_create_account)
	public void onCreateAccountClicked() {
		presenter.onCreateAccountClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_account_deposit, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		createAccountButton.setEnabled(false);

		setTextListener();

		if (request != null) {
			presenter.setRequest(request);
		}

		if (minDepositAmountInfo != null) {
			presenter.setMinDeposit(minDepositAmountInfo, minDepositCurrency);
		}

		if (getArguments() != null) {
			CreateAccountModel model = getArguments().getParcelable(EXTRA_MODEL);
			updateView(model);
			return;
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void updateView(CreateAccountModel model) {
		if (model != null) {
			this.stepGroup.setVisibility(View.GONE);
			this.accountInfoGroup.setVisibility(View.VISIBLE);

			this.brokerLogo.setImageURI(ImageUtils.getImageUri(model.getLogoUrl()));
			this.accountCurrency.setText(model.getCurrency().getValue());
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d", model.getLeverage()));

		}
		else {
			this.stepGroup.setVisibility(View.VISIBLE);
			this.accountInfoGroup.setVisibility(View.GONE);
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> presenter.onAmountChanged(charSequence.toString()));
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			content.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setMinDepositWalletCurrencyAmount(Double minDepositAmount, String currency) {
		amountToDepositLabel.setText(String.format(Locale.getDefault(), "%s (min %s)",
				getString(R.string.amount_to_deposit),
				StringFormatUtil.getMinAmountValueString(minDepositAmount, currency)));
	}

	@Override
	public void setWallets(List<WalletData> wallets) {
		this.wallets = wallets;
	}

	@Override
	public void setWallet(WalletData wallet) {
		this.iconFrom.setImageURI(ImageUtils.getImageUri(wallet.getLogoUrl()));
		this.walletFrom.setText(wallet.getTitle());
		this.availableFrom.setText(String.format(Locale.getDefault(), "%s %s",
				getString(R.string.available_in_wallet),
				StringFormatUtil.getValueString(wallet.getAvailable(), wallet.getCurrency().getValue())));
		this.amountCurrency.setText(wallet.getCurrency().getValue());
		this.baseCurrencyAmount.setVisibility(
				wallet.getCurrency().getValue().equals(minDepositCurrency)
						? View.GONE
						: View.VISIBLE);
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setCreateButtonEnabled(boolean enabled) {
		this.createAccountButton.setEnabled(enabled);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	@Override
	public void showRateProgress(boolean show) {
		rateProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		amountGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	public void setRequest(NewTradingAccountRequest request) {
		this.request = request;
		if (presenter != null) {
			presenter.setRequest(request);
		}
	}

	public void setMinDepositAmount(Map<String, Double> minDepositAmountInfo, String minDepositCurrency) {
		this.minDepositAmountInfo = minDepositAmountInfo;
		this.minDepositCurrency = minDepositCurrency;
		if (presenter != null) {
			presenter.setMinDeposit(minDepositAmountInfo, minDepositCurrency);
		}
//		depositNotification.setText(String.format(Locale.getDefault(),
//				getString(R.string.template_create_trading_account_deposit_notification),
//				StringFormatUtil.getValueString(minDepositAmount, minDepositCurrency)));
	}

	public void setStepNumber(String stepNumber) {
		this.stepNumber.setText(stepNumber);
	}

	private void showSoftKeyboard() {
		if (getContext() != null) {
			InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			amount.requestFocus();
			if (imm != null) {
				imm.showSoftInput(amount, 0);
			}
		}
	}
}