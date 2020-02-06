package vision.genesis.clientapp.feature.main.trading_account.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PrivateTradingAccountFull;
import io.swagger.client.model.SignalSubscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.copytrading.details.CopytradingDetailsActivity;
import vision.genesis.clientapp.feature.main.follow.create.CreateFollowActivity;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.feature.main.settings.public_info.ProfilePublicInfoActivity;
import vision.genesis.clientapp.feature.main.trading_account.TradingAccountDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.trading_account.add_demo_funds.AddDemoFundsActivity;
import vision.genesis.clientapp.feature.main.wallet.transfer_funds.TransferFundsActivity;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.TransferFundsModel;
import vision.genesis.clientapp.ui.AccountAgeView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SignalProviderView;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class TradingAccountInfoFragment extends BaseFragment implements TradingAccountInfoView, TradingAccountDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_ACCOUNT_DETAILS = "extra_account_details";

	public static TradingAccountInfoFragment with(PrivateTradingAccountFull accountDetails) {
		TradingAccountInfoFragment tradingAccountInfoFragment = new TradingAccountInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_ACCOUNT_DETAILS, accountDetails);
		tradingAccountInfoFragment.setArguments(arguments);
		return tradingAccountInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;


	@BindView(R.id.label_account)
	public TextView labelAccount;

	@BindView(R.id.button_manage_account)
	public TextView manageAccountButton;

	@BindView(R.id.broker_logo)
	public SimpleDraweeView brokerLogo;

	@BindView(R.id.group_currency)
	public ViewGroup groupCurrency;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.label_currency)
	public TextView labelCurrency;

	@BindView(R.id.group_leverage)
	public ViewGroup groupLeverage;

	@BindView(R.id.leverage)
	public TextView leverage;

	@BindView(R.id.label_leverage)
	public TextView labelLeverage;

	@BindView(R.id.age_period)
	public AccountAgeView age;

	@BindView(R.id.label_age)
	public TextView labelAge;


	@BindView(R.id.group_your_deposit)
	public ViewGroup yourDepositGroup;

	@BindView(R.id.label_your_deposit)
	public TextView labelYourDeposit;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.group_deposit_buttons)
	public ViewGroup depositButtonsGroup;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;

	@BindView(R.id.button_add_funds)
	public PrimaryButton addFundsButton;

	@BindView(R.id.button_add_demo_funds)
	public PrimaryButton addDemoFundsButton;


	@BindView(R.id.group_program)
	public ViewGroup groupProgram;

	@BindView(R.id.label_program)
	public TextView labelProgram;

	@BindView(R.id.button_create_program)
	public PrimaryButton createProgramButton;


	@BindView(R.id.group_follow)
	public ViewGroup groupFollow;

	@BindView(R.id.label_follow)
	public TextView labelFollow;

	@BindView(R.id.button_create_follow)
	public PrimaryButton createFollowButton;


	@BindView(R.id.group_subscriptions)
	public ViewGroup subscriptionsGroup;

	@BindView(R.id.label_subscriptions)
	public TextView labelSubscriptions;

	@BindView(R.id.button_subscriptions_details)
	public TextView subscriptionsDetailsButton;

	@BindView(R.id.subscriptions_info_active)
	public TextView subscriptionsInfoActive;

	@BindView(R.id.subscriptions_info_inactive)
	public TextView subscriptionsInfoInactive;

	@BindView(R.id.group_my_subscriptions)
	public LinearLayout mySubscriptionsGroup;


	@InjectPresenter
	public TradingAccountInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private PrivateTradingAccountFull accountDetails;

	private Unbinder unbinder;

	@OnClick(R.id.button_manage_account)
	public void onManageAccountClicked() {
		presenter.onManageAccountClicked();
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		presenter.onWithdrawClicked();
	}

	@OnClick(R.id.button_add_funds)
	public void onAddFundsClicked() {
		presenter.onAddFundsClicked();
	}

	@OnClick(R.id.button_add_demo_funds)
	public void onAddDemoFundsClicked() {
		presenter.onAddDemoFundsClicked();
	}

	@OnClick(R.id.button_create_program)
	public void onCreateProgramClicked() {
		presenter.onCreateProgramClicked();
	}

	@OnClick(R.id.button_create_follow)
	public void onCreateFollowClicked() {
		presenter.onCreateFollowClicked();
	}

	@OnClick(R.id.button_subscriptions_details)
	public void onSubscriptionsDetailsClicked() {
		presenter.onSubscriptionsDetailsClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_trading_account_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		withdrawButton.setEmpty();

		if (getArguments() != null) {
			accountDetails = getArguments().getParcelable(EXTRA_ACCOUNT_DETAILS);
			if (accountDetails != null) {
				presenter.setDetails(accountDetails);
				setAccountDetails(accountDetails);

				setFonts();

				return;
			}
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

	private void setFonts() {
		labelAccount.setTypeface(TypefaceUtil.semibold());
		manageAccountButton.setTypeface(TypefaceUtil.semibold());
		currency.setTypeface(TypefaceUtil.semibold());
		leverage.setTypeface(TypefaceUtil.semibold());

		labelYourDeposit.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());

		labelProgram.setTypeface(TypefaceUtil.semibold());

		labelFollow.setTypeface(TypefaceUtil.semibold());

		labelSubscriptions.setTypeface(TypefaceUtil.semibold());
		subscriptionsDetailsButton.setTypeface(TypefaceUtil.semibold());

		labelLeverage.setText(labelLeverage.getText().toString().toLowerCase());
		labelCurrency.setText(labelCurrency.getText().toString().toLowerCase());
		labelAge.setText(labelAge.getText().toString().toLowerCase());
	}

	@Override
	public void setAccountDetails(PrivateTradingAccountFull accountDetails) {
		this.accountDetails = accountDetails;

		if (accountDetails != null) {

			scrollView.setVisibility(View.VISIBLE);

			updateAccountInfo(accountDetails);

			if (accountDetails.getTradingAccountInfo().isIsExternal()) {
				value.setText(StringFormatUtil.formatAmount(accountDetails.getTradingAccountInfo().getBalance()));
				depositButtonsGroup.setVisibility(View.GONE);
			}
			else {
				value.setText(StringFormatUtil.getValueString(accountDetails.getTradingAccountInfo().getBalance(),
						accountDetails.getTradingAccountInfo().getCurrency().getValue()));
				if (accountDetails.getOwnerActions().isCanMakeDemoDeposit()) {
					addDemoFundsButton.setVisibility(View.VISIBLE);
					depositButtonsGroup.setVisibility(View.GONE);

					groupProgram.setVisibility(View.GONE);
					groupFollow.setVisibility(View.GONE);
				}
				else {
					addDemoFundsButton.setVisibility(View.GONE);
					depositButtonsGroup.setVisibility(View.VISIBLE);

					groupProgram.setVisibility(accountDetails.getTradingAccountInfo().isIsExternal() ? View.GONE : View.VISIBLE);
					groupFollow.setVisibility(View.VISIBLE);
				}
			}

			if (accountDetails.getOwnerActions() != null) {
				withdrawButton.setEnabled(accountDetails.getOwnerActions().isCanTransferMoney());
				addFundsButton.setEnabled(accountDetails.getOwnerActions().isCanTransferMoney());
				createProgramButton.setEnabled(accountDetails.getOwnerActions().isCanMakeProgramFromPrivateTradingAccount());
				createFollowButton.setEnabled(accountDetails.getOwnerActions().isCanMakeSignalProviderFromPrivateTradingAccount());
			}
		}
	}

	private void updateAccountInfo(PrivateTradingAccountFull details) {
		this.brokerLogo.setImageURI(ImageUtils.getImageUri(details.getBrokerDetails().getLogo()));
		this.age.setCreationDate(details.getPublicInfo().getCreationDate());
		if (details.getTradingAccountInfo().isIsExternal()) {
			groupCurrency.setVisibility(View.GONE);
			groupLeverage.setVisibility(View.GONE);
		}
		else {
			groupCurrency.setVisibility(View.VISIBLE);
			groupLeverage.setVisibility(View.VISIBLE);

			this.currency.setText(details.getTradingAccountInfo().getCurrency().getValue());
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d", details.getTradingAccountInfo().getLeverage()));
		}
	}

	@Override
	public void showCopytrading(List<SignalSubscription> masters) {
		subscriptionsGroup.setVisibility(View.VISIBLE);
		mySubscriptionsGroup.removeAllViews();

		int index = 0;
		int active = 0;
		int inactive = 0;
		for (SignalSubscription master : masters) {
			if (master.getStatus().toLowerCase().equals("active")) {
				active++;
				SignalProviderView subscriptionView = new SignalProviderView(getContext());
				subscriptionView.setData(master);
				mySubscriptionsGroup.addView(subscriptionView);
				if (index == masters.size() - 1) {
					subscriptionView.removeDelimiter();
				}
				index++;
			}
			else {
				inactive++;
			}
		}
		mySubscriptionsGroup.setVisibility(active > 0 ? View.VISIBLE : View.GONE);

		subscriptionsInfoActive.setText(String.format(Locale.getDefault(), getString(R.string.template_subscriptions_info_active),
				active,
				getResources().getString(R.string.follow)));
		subscriptionsInfoActive.setVisibility(active > 0 ? View.VISIBLE : View.GONE);

		subscriptionsInfoInactive.setText(String.format(Locale.getDefault(), getString(R.string.template_subscriptions_info_inactive),
				inactive,
				getResources().getString(R.string.follow)));
		subscriptionsInfoInactive.setVisibility(inactive > 0 ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showCreateProgram(CreateProgramModel createProgramModel) {
		if (getActivity() != null) {
			CreateProgramActivity.startFrom(getActivity(), createProgramModel);
		}
	}

	@Override
	public void showCreateFollow(CreateProgramModel createProgramModel) {
		if (getActivity() != null) {
			CreateFollowActivity.startFrom(getActivity(), createProgramModel);
		}
	}

	@Override
	public void pagerShow() {
		if (presenter != null) {
			presenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, scrollView);
	}

	@Override
	public void showLoginActivity() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}

	@Override
	public void showTransferFundsActivity(TransferFundsModel model) {
		if (getActivity() != null) {
			TransferFundsActivity.startWith(getActivity(), model);
		}
	}

	@Override
	public void showCopytradingDetailsActivity(TradingAccountDetailsModel model) {
		if (getActivity() != null) {
			CopytradingDetailsActivity.startWith(getActivity(), model);
		}
	}

	@Override
	public void showProfilePublicInfoActivity() {
		if (getActivity() != null) {
			ProfilePublicInfoActivity.startFrom(getActivity(), true);
		}
	}

	@Override
	public void showAddDemoFundsActivity(ProgramRequest request) {
		if (getActivity() != null) {
			AddDemoFundsActivity.startWith(getActivity(), request);
		}
	}
}