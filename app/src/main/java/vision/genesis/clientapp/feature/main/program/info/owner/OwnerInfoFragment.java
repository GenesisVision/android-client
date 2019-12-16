package vision.genesis.clientapp.feature.main.program.info.owner;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
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
import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.PersonalFollowDetailsFull;
import io.swagger.client.model.PersonalProgramDetails;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.common.public_info.edit.EditPublicInfoActivity;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.follow.create.CreateFollowActivity;
import vision.genesis.clientapp.feature.main.follow.edit.EditFollowSettingsActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.feature.main.program.manage.ManageProgramActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.feature.main.trading_account.manage.ManageTradingAccountActivity;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.ui.AccountAgeView;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PeriodLeftDetailsView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/11/2019.
 */

public class OwnerInfoFragment extends BaseFragment implements OwnerInfoView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_DETAILS = "extra_details";

	public static OwnerInfoFragment with(ProgramFollowDetailsFull details) {
		OwnerInfoFragment ownerInfoFragment = new OwnerInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_DETAILS, details);
		ownerInfoFragment.setArguments(arguments);
		return ownerInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.label_public_info)
	public TextView labelPublicInfo;

	@BindView(R.id.button_edit_public_info)
	public TextView editPublicInfoButton;

	@BindView(R.id.strategy)
	public TextView strategy;

	@BindView(R.id.strategy_shadow)
	public View strategyShadow;


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

	@BindView(R.id.status)
	public InvestmentStatusView status;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.profit_label)
	public TextView profitLabel;

	@BindView(R.id.button_deposit)
	public PrimaryButton depositButton;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;


	@BindView(R.id.group_program_info)
	public ViewGroup programInfoGroup;

	@BindView(R.id.label_program)
	public TextView labelProgram;

	@BindView(R.id.button_manage_program)
	public TextView manageProgramButton;

	@BindView(R.id.available_to_invest)
	public TextView availableToInvest;

	@BindView(R.id.stop_out)
	public TextView stopOut;

	@BindView(R.id.label_stop_out)
	public TextView stopOutLabel;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.label_entry_fee)
	public TextView entryFeeLabel;

	@BindView(R.id.success_fee)
	public TextView successFee;

	@BindView(R.id.view_period)
	public PeriodLeftDetailsView periodView;

	@BindView(R.id.group_create_program)
	public ViewGroup createProgramGroup;


	@BindView(R.id.group_follow)
	public ViewGroup followGroup;

	@BindView(R.id.label_follow)
	public TextView labelFollow;

	@BindView(R.id.button_manage_follow)
	public TextView manageFollowButton;

	@BindView(R.id.group_follow_info)
	public ViewGroup followInfoGroup;

	@BindView(R.id.group_create_follow)
	public ViewGroup createFollowGroup;

	@BindView(R.id.subscription_success_fee)
	public TextView subscriptionSuccessFee;

	@BindView(R.id.label_subscription_success_fee)
	public TextView subscriptionSuccessFeeLabel;

	@BindView(R.id.subscription_volume_fee)
	public TextView subscriptionVolumeFee;

	@BindView(R.id.label_subscription_volume_fee)
	public TextView subscriptionVolumeFeeLabel;

	@InjectPresenter
	public OwnerInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private ProgramFollowDetailsFull details;

	private Unbinder unbinder;

	@OnClick(R.id.button_edit_public_info)
	public void onEditPublicInfoClicked() {
		presenter.onEditPublicInfoClicked();
	}

	@OnClick(R.id.button_manage_account)
	public void onManageAccountClicked() {
		presenter.onManageAccountClicked();
	}

	@OnClick(R.id.strategy)
	public void onStrategyClicked() {
		if (strategy.getHeight() == strategyMaxHeight) {
			expandStrategy();
		}
		else if (strategy.getHeight() > strategyMaxHeight) {
			collapseStrategy();
		}
	}

	@OnClick(R.id.group_entry_fee)
	public void onEntryFeeClicked() {
		if (details != null && details.getProgramDetails() != null && details.getProgramDetails().getLevel() < Constants.MIN_PROGRAM_LEVEL_ENTRY_FEE && getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.entry_fee_disabled_title), getString(R.string.entry_fee_disabled_message), false, null);
		}
	}

	private void expandStrategy() {
		ValueAnimator animator = ValueAnimator.ofInt(strategy.getMaxHeight(), 10000);
		animator.addUpdateListener(animation -> strategy.setMaxHeight((int) animator.getAnimatedValue()));
		animator.setDuration(400);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		strategyShadow.setVisibility(View.INVISIBLE);
	}

	private void collapseStrategy() {
		ValueAnimator animator = ValueAnimator.ofInt(strategy.getHeight(), strategyMaxHeight);
		animator.addUpdateListener(animation -> {
			strategy.setHeight((int) animator.getAnimatedValue());
			if (!animation.isRunning()) {
				strategy.setMaxHeight(strategyMaxHeight);
			}
		});
		animator.setDuration(200);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		strategyShadow.setVisibility(View.VISIBLE);
	}

	@OnClick(R.id.status)
	public void onStatusClicked() {
		presenter.onStatusClicked();
	}

	@OnClick(R.id.button_deposit)
	public void onDepositClicked() {
		presenter.onDepositClicked();
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		presenter.onWithdrawClicked();
	}

	@OnClick(R.id.button_manage_program)
	public void onManageProgramClicked() {
		presenter.onManageProgramClicked();
	}

	@OnClick(R.id.button_manage_follow)
	public void onManageFollowClicked() {
		presenter.onManageFollowClicked();
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
		return inflater.inflate(R.layout.fragment_program_owner_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			details = getArguments().getParcelable(EXTRA_DETAILS);
			if (details != null) {
				updateAll();

				setFonts();

				withdrawButton.setEmpty();

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateAll() {
		presenter.setDetails(details);
		setDetails(details);
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
		labelPublicInfo.setTypeface(TypefaceUtil.semibold());
		editPublicInfoButton.setTypeface(TypefaceUtil.semibold());

		labelAccount.setTypeface(TypefaceUtil.semibold());
		manageAccountButton.setTypeface(TypefaceUtil.semibold());
		currency.setTypeface(TypefaceUtil.semibold());
		leverage.setTypeface(TypefaceUtil.semibold());

		labelYourDeposit.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());
		profit.setTypeface(TypefaceUtil.semibold());

		labelProgram.setTypeface(TypefaceUtil.semibold());
		manageProgramButton.setTypeface(TypefaceUtil.semibold());
		availableToInvest.setTypeface(TypefaceUtil.semibold());
		stopOut.setTypeface(TypefaceUtil.semibold());
		entryFee.setTypeface(TypefaceUtil.semibold());
		successFee.setTypeface(TypefaceUtil.semibold());

		labelFollow.setTypeface(TypefaceUtil.semibold());
		manageFollowButton.setTypeface(TypefaceUtil.semibold());
		subscriptionSuccessFee.setTypeface(TypefaceUtil.semibold());
		subscriptionVolumeFee.setTypeface(TypefaceUtil.semibold());

		labelLeverage.setText(labelLeverage.getText().toString().toLowerCase());
		labelCurrency.setText(labelCurrency.getText().toString().toLowerCase());
		labelAge.setText(labelAge.getText().toString().toLowerCase());

		stopOutLabel.setText(stopOutLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		entryFeeLabel.setText(entryFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setDetails(ProgramFollowDetailsFull details) {
		this.details = details;

		if (details != null) {
			scrollView.setVisibility(View.VISIBLE);

			updatePublicInfo(details.getPublicInfo().getDescription());
			updateAccountInfo(details.getBrokerDetails().getLogo(),
					details.getTradingAccountInfo().getCurrency().getValue(), details.getTradingAccountInfo().getLeverageMax(),
					details.getPublicInfo().getCreationDate());

			ProgramDetailsFull programDetails = details.getProgramDetails();
			if (programDetails != null) {
				updateYourDeposit(programDetails.getPersonalDetails());
				updateProgramDetails(programDetails);
			}
			else {
				programInfoGroup.setVisibility(View.GONE);
				manageProgramButton.setVisibility(View.GONE);
				createProgramGroup.setVisibility(View.VISIBLE);
			}

			FollowDetailsFull followDetails = details.getFollowDetails();
			if (followDetails != null) {
				updateFollowDetails(followDetails);
			}
		}
	}

	private void updatePublicInfo(String description) {
		this.strategy.setText(description);
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null) {
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
			}
		}, 300);
	}

	private void updateAccountInfo(String brokerLogo, String currency, Integer leverage, DateTime creationDate) {
		this.brokerLogo.setImageURI(ImageUtils.getImageUri(brokerLogo));
		this.currency.setText(currency);
		this.leverage.setText(String.format(Locale.getDefault(), "1:%d", leverage));
		this.age.setCreationDate(creationDate);
	}

	private void updateProgramDetails(ProgramDetailsFull details) {
		programInfoGroup.setVisibility(View.VISIBLE);
		manageProgramButton.setVisibility(View.VISIBLE);
		createProgramGroup.setVisibility(View.GONE);

		PersonalProgramDetails personalDetails = details.getPersonalDetails();

		availableToInvest.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.getShortenedAmount(details.getAvailableInvestmentBase()).toString(),
				this.details.getTradingAccountInfo().getCurrency().getValue()));

		updateCurrentSelectedField(stopOut, details.getStopOutLevelCurrent(), details.getStopOutLevelSelected());
		updateCurrentSelectedField(entryFee, details.getEntryFeeCurrent(), details.getEntryFeeSelected());
		updateCurrentSelectedField(successFee, details.getSuccessFeeCurrent(), details.getSuccessFeeSelected());

		periodView.setData(details.getPeriodDuration(), details.getPeriodStarts(), details.getPeriodEnds(), true, true);

		depositButton.setEnabled(details.getAvailableInvestmentBase() > 0);

		if (personalDetails != null) {
			depositButton.setEnabled(details.getAvailableInvestmentBase() > 0 && personalDetails.isCanInvest());
			withdrawButton.setEnabled(personalDetails.isCanWithdraw());
		}
	}

	private void updateFollowDetails(FollowDetailsFull followDetails) {
		if (followDetails != null && followDetails.getSignalSettings() != null) {
			followInfoGroup.setVisibility(View.VISIBLE);
			manageFollowButton.setVisibility(View.VISIBLE);
			createFollowGroup.setVisibility(View.GONE);

			subscriptionSuccessFee.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(followDetails.getSignalSettings().getSignalSuccessFee(), 0, 4)));
			subscriptionVolumeFee.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(followDetails.getSignalSettings().getSignalVolumeFee(), 0, 4)));
		}
		else {
			followInfoGroup.setVisibility(View.GONE);
			manageFollowButton.setVisibility(View.GONE);
			createFollowGroup.setVisibility(View.VISIBLE);
		}
	}

	private void updateCurrentSelectedField(TextView textView, Double current, Double selected) {
		textView.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(current, 0, 4)));
		if (!selected.equals(current)) {
			textView.setText(textView.getText().toString().concat(
					String.format(Locale.getDefault(), " (%s%%)", StringFormatUtil.formatAmount(selected, 0, 4))));
		}
	}

	private void updateYourDeposit(PersonalProgramDetails personalDetails) {
		if (personalDetails != null && personalDetails.isIsInvested() && !personalDetails.getStatus().equals(AssetInvestmentStatus.ENDED)) {
			yourDepositGroup.setVisibility(View.VISIBLE);
			status.setVisibility(View.VISIBLE);
			status.setStatus(personalDetails.getStatus().getValue());
			value.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(personalDetails.getValue(), 0,
							StringFormatUtil.getCurrencyMaxFraction(this.details.getTradingAccountInfo().getCurrency().getValue())),
					this.details.getTradingAccountInfo().getCurrency().getValue()));
			profit.setVisibility(View.VISIBLE);
			profit.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(getProfitPercent(personalDetails.getInvested(), personalDetails.getValue()), 0, 4)));
			profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
					personalDetails.getValue() < personalDetails.getInvested()
							? R.attr.colorRed
							: R.attr.colorGreen));
		}
		else {
			yourDepositGroup.setVisibility(View.GONE);
		}
	}

	private void updateYourDeposit(PersonalFollowDetailsFull personalDetails) {
		if (personalDetails != null) {
			yourDepositGroup.setVisibility(View.VISIBLE);
			status.setVisibility(View.GONE);
//			value.setText(String.format(Locale.getDefault(), "%s %s",
//					StringFormatUtil.formatAmount(personalDetails.getValue(), 0,
//							StringFormatUtil.getCurrencyMaxFraction(this.followDetails.getCurrency().getValue())),
//					this.programDetails.getCurrency().getValue()));
			profit.setVisibility(View.GONE);
		}
		else {
			yourDepositGroup.setVisibility(View.GONE);
		}
	}

	private Double getProfitPercent(Double invested, Double value) {
		return Math.abs(invested != 0 ? 100 / invested * (invested - value) : 0);
	}

	@Override
	public void showInvestWithdrawButtons() {
//		buttonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
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
	public void showInvestProgramActivity(ProgramRequest request) {
		if (getActivity() != null) {
			InvestProgramActivity.startWith(getActivity(), request);
		}
	}

	@Override
	public void showWithdrawProgramActivity(ProgramRequest request) {
		if (getActivity() != null) {
			WithdrawProgramActivity.startWith(getActivity(), request);
		}
	}

	@Override
	public void showRequestsBottomSheet() {
		if (getActivity() != null) {
			RequestsBottomSheetFragment bottomSheetDialog = new RequestsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setAssetId(details.getId());
		}
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
	public void showEditPublicInfoActivity(UUID assetId, ProgramUpdate model) {
		if (getActivity() != null) {
			EditPublicInfoActivity.startFrom(getActivity(), assetId, model);
		}
	}

	@Override
	public void showManageAccountActivity(TradingAccountDetailsModel model) {
		if (getActivity() != null) {
			ManageTradingAccountActivity.startFrom(getActivity(), model);
		}
	}

	@Override
	public void showManageProgramActivity(ProgramFollowDetailsFull details) {
		if (getActivity() != null) {
			ManageProgramActivity.startFrom(getActivity(), details);
		}
	}

	@Override
	public void showEditFollowSettingsActivity(CreateSignalProvider model) {
		if (getActivity() != null) {
			EditFollowSettingsActivity.startFrom(getActivity(), model);
		}
	}

	public void updateInfo(ProgramFollowDetailsFull details) {
		this.details = details;
		updateAll();
	}
}