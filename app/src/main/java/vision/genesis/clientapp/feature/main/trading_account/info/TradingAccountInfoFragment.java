package vision.genesis.clientapp.feature.main.trading_account.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.joda.time.DateTime;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PrivateTradingAccountFull;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.follow.create.CreateFollowActivity;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.feature.main.trading_account.TradingAccountPagerAdapter;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.ui.AccountAgeView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class TradingAccountInfoFragment extends BaseFragment implements TradingAccountInfoView, TradingAccountPagerAdapter.OnPageVisibilityChanged
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

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.label_currency)
	public TextView labelCurrency;

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

	@BindView(R.id.button_transfer)
	public PrimaryButton transferButton;


	@BindView(R.id.label_program)
	public TextView labelProgram;

	@BindView(R.id.button_create_program)
	public PrimaryButton createProgramButton;


	@BindView(R.id.label_follow)
	public TextView labelFollow;

	@BindView(R.id.button_create_follow)
	public PrimaryButton createFollowButton;


	@InjectPresenter
	public TradingAccountInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private UUID programId;

	private PrivateTradingAccountFull accountDetails;

	private Unbinder unbinder;

	@OnClick(R.id.button_manage_account)
	public void onManageAccountClicked() {
		presenter.onManageAccountClicked();
	}

	@OnClick(R.id.button_transfer)
	public void onTransferClicked() {
		presenter.onTransferClicked();
	}

	@OnClick(R.id.button_create_program)
	public void onCreateProgramClicked() {
		presenter.onCreateProgramClicked();
	}

	@OnClick(R.id.button_create_follow)
	public void onCreateFollowClicked() {
		presenter.onCreateFollowClicked();
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

		labelLeverage.setText(labelLeverage.getText().toString().toLowerCase());
		labelCurrency.setText(labelCurrency.getText().toString().toLowerCase());
		labelAge.setText(labelAge.getText().toString().toLowerCase());
	}

	@Override
	public void setAccountDetails(PrivateTradingAccountFull accountDetails) {
		this.accountDetails = accountDetails;

		if (accountDetails != null) {

			scrollView.setVisibility(View.VISIBLE);

			updateAccountInfo(accountDetails.getBrokerDetails().getLogo(),
					accountDetails.getCurrency().getValue(), accountDetails.getLeverage(),
					accountDetails.getCreationDate());

			value.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(accountDetails.getBalance(), 0,
							StringFormatUtil.getCurrencyMaxFraction(this.accountDetails.getCurrency().getValue())),
					this.accountDetails.getCurrency().getValue()));

			if (accountDetails.getOwnerActions() != null) {
				transferButton.setEnabled(accountDetails.getOwnerActions().isCanTransferMoney());
				createProgramButton.setEnabled(accountDetails.getOwnerActions().isCanMakeProgramFromPrivateTradingAccount());
				createFollowButton.setEnabled(accountDetails.getOwnerActions().isCanMakeSignalProviderFromPrivateTradingAccount());
			}
		}
	}

	private void updateAccountInfo(String brokerLogo, String currency, Integer leverage, DateTime creationDate) {
		this.brokerLogo.setImageURI(ImageUtils.getImageUri(brokerLogo));
		this.currency.setText(currency);
		this.leverage.setText(String.format(Locale.getDefault(), "1:%d", leverage));
		this.age.setCreationDate(creationDate);
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
}