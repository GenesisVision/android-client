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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.BrokerTradeServerType;
import io.swagger.client.model.ProgramFollowDetailsFull;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.external.attach.AttachExternalAccountActivity;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountActivity;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */

public class SelectSubscriptionAccountFragment extends BaseFragment implements SelectSubscriptionAccountView
{
	private static final String EXTRA_FOLLOW = "extra_follow";

	public static SelectSubscriptionAccountFragment with(ProgramFollowDetailsFull followDetails) {
		SelectSubscriptionAccountFragment fragment = new SelectSubscriptionAccountFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_FOLLOW, followDetails);
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

	@BindView(R.id.button_create_trading_account)
	public PrimaryButton createTradingAccountButton;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public SelectSubscriptionAccountPresenter presenter;

	private Unbinder unbinder;

	private ArrayList<String> accountOptions;

	private Integer selectedAccountPosition;

	private ProgramFollowDetailsFull followDetails;

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
			if (followDetails.getBrokerDetails().getType().equals(BrokerTradeServerType.BINANCEFOLLOW)) {
				AttachExternalAccountActivity.startWith(getActivity(), followDetails.getBrokerDetails());
			}
			else {
				CreateAccountActivity.startWith(getActivity(),
						new CreateAccountModel(followDetails.getBrokerDetails().getId(),
								followDetails.getBrokerDetails().getLogoUrl(),
								followDetails.getTradingAccountInfo().getCurrency(),
								followDetails.getTradingAccountInfo().getLeverageMax()));
			}
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
			followDetails = getArguments().getParcelable(EXTRA_FOLLOW);
			if (followDetails != null) {
				presenter.setData(followDetails);
				updateView(followDetails);
				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(ProgramFollowDetailsFull followDetails) {
		if (followDetails.getBrokerDetails().getType().equals(BrokerTradeServerType.BINANCEFOLLOW)) {
			this.createTradingAccountButton.setText(getString(R.string.attach_external_account));
		}
		else {
			this.createTradingAccountButton.setText(getString(R.string.create_trading_account));
		}
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