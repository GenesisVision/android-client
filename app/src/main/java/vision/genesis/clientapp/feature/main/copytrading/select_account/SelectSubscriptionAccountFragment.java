package vision.genesis.clientapp.feature.main.copytrading.select_account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public class SelectSubscriptionAccountFragment extends BaseFragment implements SelectSubscriptionAccountView
{
	private static final String EXTRA_FOLLOW_ID = "extra_follow_id";

	public static SelectSubscriptionAccountFragment with(UUID followId) {
		SelectSubscriptionAccountFragment fragment = new SelectSubscriptionAccountFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_FOLLOW_ID, followId);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_step)
	public ViewGroup stepGroup;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.group_select_account)
	public ViewGroup groupSelectAccount;

	@BindView(R.id.account)
	public TextView account;

	@BindView(R.id.group_no_account)
	public ViewGroup groupNoAccount;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public SelectSubscriptionAccountPresenter presenter;

	private Unbinder unbinder;

	private ArrayList<String> accountOptions;

	private Integer selectedAccountPosition;

	@OnClick(R.id.group_account)
	public void onAccountClicked() {
		if (getActivity() != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.select_account), accountOptions, selectedAccountPosition);
			fragment.setListener(presenter);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.button_create_trading_account)
	public void onCreateTradingAccountClicked() {
		if (getActivity() != null) {
			CreateAccountActivity.startFrom(getActivity());
		}
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		presenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_select_subscription_account, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		this.confirmButton.setText(String.format(Locale.getDefault(), "%s (1/2)", GenesisVisionApplication.INSTANCE.getString(R.string.next)));
		this.confirmButton.setEnabled(false);

		if (getArguments() != null) {
			UUID followId = (UUID) getArguments().getSerializable(EXTRA_FOLLOW_ID);
			if (followId != null) {
				presenter.setData(followId);
				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (presenter != null) {
			presenter.onResume();
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
	}

	@Override
	public void setAccountOptions(ArrayList<String> accountOptions) {
		this.accountOptions = accountOptions;
	}

	@Override
	public void setAccount(String account, Integer position) {
		this.account.setText(account);
		this.selectedAccountPosition = position;
	}

	@Override
	public void showNoAccounts(boolean show) {
		groupNoAccount.setVisibility(show ? View.VISIBLE : View.GONE);
		groupSelectAccount.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}
}