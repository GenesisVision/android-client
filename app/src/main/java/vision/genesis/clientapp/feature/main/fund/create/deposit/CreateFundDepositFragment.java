package vision.genesis.clientapp.feature.main.fund.create.deposit;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.NewFundRequest;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.select_wallet.SelectWalletBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

public class CreateFundDepositFragment extends BaseFragment implements CreateFundDepositView
{
	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

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

	@BindView(R.id.button_create_fund)
	public PrimaryButton createFundButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public CreateFundDepositPresenter presenter;

	private List<WalletData> wallets;

	private Unbinder unbinder;

	private NewFundRequest request;

	private Double minDepositAmount;

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

	@OnClick(R.id.button_create_fund)
	public void onCreateFundClicked() {
		presenter.onCreateFundClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_create_fund_deposit, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		createFundButton.setEnabled(false);

		setFonts();

		setTextListener();

		if (request != null) {
			presenter.setRequest(request);
		}

		if (minDepositAmount != null) {
			presenter.setMinDepositAmount(minDepositAmount);
		}
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
		max.setTypeface(TypefaceUtil.semibold());
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
	public void setMinDepositWalletCurrencyAmount(Double minDepositAmount) {
		amountToDepositLabel.setText(String.format(Locale.getDefault(), "%s (min %s)",
				getString(R.string.amount_to_deposit),
				StringFormatUtil.formatAmount(minDepositAmount, 0, 8)));
	}

	@Override
	public void setWallets(List<WalletData> wallets) {
		this.wallets = wallets;
	}

	@Override
	public void setWallet(WalletData wallet) {
		this.iconFrom.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.walletFrom.setText(wallet.getTitle());
		this.availableFrom.setText(String.format(Locale.getDefault(), "%s %s",
				getString(R.string.available_in_wallet),
				StringFormatUtil.getValueString(wallet.getAvailable(), wallet.getCurrency().getValue())));
		this.amountCurrency.setText(wallet.getCurrency().getValue());
		this.baseCurrencyAmount.setVisibility(
				wallet.getCurrency().getValue().equals(CurrencyEnum.GVT.getValue())
						? View.GONE
						: View.VISIBLE);
	}

	@Override
	public void setAmount(String amountText) {
		this.amount.setText(amountText);
		this.amount.setSelection(amountText.length(), amountText.length());
	}

	@Override
	public void setAmountBase(String amountBaseString) {
		this.baseCurrencyAmount.setText(amountBaseString);
	}

	@Override
	public void setCreateButtonEnabled(boolean enabled) {
		this.createFundButton.setEnabled(enabled);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	public void setRequest(NewFundRequest request) {
		this.request = request;
		if (presenter != null) {
			presenter.setRequest(request);
		}
	}

	public void setMinDepositAmount(Double minDepositAmount) {
		this.minDepositAmount = minDepositAmount;
		if (presenter != null) {
			presenter.setMinDepositAmount(minDepositAmount);
		}
		depositNotification.setText(String.format(Locale.getDefault(),
				getString(R.string.template_create_fund_deposit_second_notification),
				StringFormatUtil.formatAmount(minDepositAmount, 0, 8)));
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