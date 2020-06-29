package vision.genesis.clientapp.feature.main.program.info.owner;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.AssetTypeExt;
import io.swagger.client.model.CreateSignalProvider;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.PersonalProgramDetails;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.ProgramUpdate;
import io.swagger.client.model.SignalSubscription;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.common.public_info.edit.EditPublicInfoActivity;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.copytrading.details.CopytradingDetailsActivity;
import vision.genesis.clientapp.feature.main.copytrading.edit_subscription.EditSubscriptionActivity;
import vision.genesis.clientapp.feature.main.copytrading.unfollow_trades.UnfollowTradesActivity;
import vision.genesis.clientapp.feature.main.follow.create.CreateFollowActivity;
import vision.genesis.clientapp.feature.main.follow.edit.EditFollowSettingsActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.feature.main.program.manage.ManageProgramActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.feature.main.trading_account.manage.ManageTradingAccountActivity;
import vision.genesis.clientapp.feature.main.wallet.transfer_funds.TransferFundsActivity;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.model.TransferFundsModel;
import vision.genesis.clientapp.ui.AccountAgeView;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PeriodLeftDetailsView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SignalProviderView;
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


	@BindView(R.id.group_scales)
	public ViewGroup groupScales;

	@BindView(R.id.genesis_ratio_progress_bar)
	public ProgressBar genesisRatioProgressBar;

	@BindView(R.id.genesis_ratio)
	public TextView genesisRatio;

	@BindView(R.id.invest_scale_progress_bar)
	public ProgressBar investScaleProgressBar;

	@BindView(R.id.invest_scale)
	public TextView investScale;

	@BindView(R.id.volume_scale_progress_bar)
	public ProgressBar volumeScaleProgressBar;

	@BindView(R.id.volume_scale)
	public TextView volumeScale;


	@BindView(R.id.group_your_deposit)
	public ViewGroup yourDepositGroup;

	@BindView(R.id.label_your_deposit)
	public TextView labelYourDeposit;

	@BindView(R.id.status)
	public InvestmentStatusView status;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.group_profit)
	public ViewGroup profitGroup;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.profit_label)
	public TextView profitLabel;

	@BindView(R.id.button_deposit_program)
	public PrimaryButton depositProgramButton;

	@BindView(R.id.button_withdraw_program)
	public PrimaryButton withdrawProgramButton;

	@BindView(R.id.button_add_funds)
	public PrimaryButton addFundsButton;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;

	@BindView(R.id.group_deposit_follow_buttons)
	public ViewGroup depositFollowButtonsGroup;

	@BindView(R.id.group_deposit_program_buttons)
	public ViewGroup depositProgramButtonsGroup;


	@BindView(R.id.group_program)
	public ViewGroup programGroup;

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

	@BindView(R.id.management_fee)
	public TextView managementFee;

	@BindView(R.id.label_management_fee)
	public TextView managementFeeLabel;

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

	@OnClick(R.id.group_genesis_ratio)
	public void onGenesisRatioClicked() {
		if (getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.genesis_ratio).toUpperCase(), getString(R.string.tooltip_genesis_ratio), false, null);
		}
	}

	@OnClick(R.id.group_invest_scale)
	public void onInvestScaleClicked() {
		if (getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.investment_scale).toUpperCase(), getString(R.string.tooltip_invest_scale), false, null);
		}
	}

	@OnClick(R.id.group_volume_scale)
	public void onVolumeScaleClicked() {
		if (getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.volume_scale).toUpperCase(), getString(R.string.tooltip_volume_scale), false, null);
		}
	}

	@OnClick(R.id.group_management_fee)
	public void onManagementFeeClicked() {
		if (details != null && details.getProgramDetails() != null && getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.management_fee), getString(R.string.management_fee_info), false, null);
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

	@OnClick(R.id.button_deposit_program)
	public void onDepositProgramClicked() {
		presenter.onDepositProgramClicked();
	}

	@OnClick(R.id.button_withdraw_program)
	public void onWithdrawProgramClicked() {
		presenter.onWithdrawProgramClicked();
	}

	@OnClick(R.id.button_add_funds)
	public void oAddFundsClicked() {
		presenter.onAddFundsClicked();
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

	@OnClick(R.id.button_subscriptions_details)
	public void onSubscriptionsDetailsClicked() {
		presenter.onSubscriptionsDetailsClicked();
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

		withdrawButton.setEmpty();

		if (getArguments() != null) {
			details = getArguments().getParcelable(EXTRA_DETAILS);
			if (details != null) {
				updateAll();

				setFonts();

				withdrawProgramButton.setEmpty();

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
	public void onResume() {
		super.onResume();
		if (presenter != null) {
			presenter.onResume();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (presenter != null) {
			presenter.onPause();
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
		managementFee.setTypeface(TypefaceUtil.semibold());
		successFee.setTypeface(TypefaceUtil.semibold());

		labelFollow.setTypeface(TypefaceUtil.semibold());
		manageFollowButton.setTypeface(TypefaceUtil.semibold());
		subscriptionSuccessFee.setTypeface(TypefaceUtil.semibold());
		subscriptionVolumeFee.setTypeface(TypefaceUtil.semibold());

		labelSubscriptions.setTypeface(TypefaceUtil.semibold());
		subscriptionsDetailsButton.setTypeface(TypefaceUtil.semibold());

		labelLeverage.setText(labelLeverage.getText().toString().toLowerCase());
		labelCurrency.setText(labelCurrency.getText().toString().toLowerCase());
		labelAge.setText(labelAge.getText().toString().toLowerCase());

		stopOutLabel.setText(stopOutLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		managementFeeLabel.setText(managementFeeLabel.getText().toString().toLowerCase());
		subscriptionVolumeFeeLabel.setText(subscriptionVolumeFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setDetails(ProgramFollowDetailsFull details) {
		this.details = details;

		if (details != null) {
			scrollView.setVisibility(View.VISIBLE);

			updatePublicInfo(details.getPublicInfo().getDescription());
			updateAccountInfo(details);

			updateYourDeposit();

			updateProgramDetails(details.getProgramDetails());
			updateFollowDetails(details.getFollowDetails());
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

	private void updateAccountInfo(ProgramFollowDetailsFull details) {
		this.brokerLogo.setImageURI(ImageUtils.getImageUri(details.getBrokerDetails().getLogoUrl()));
		this.age.setCreationDate(details.getPublicInfo().getCreationDate());
		if (details.getPublicInfo().getTypeExt().equals(AssetTypeExt.EXTERNALSIGNALTRADINGACCOUNT)) {
			groupCurrency.setVisibility(View.GONE);
			groupLeverage.setVisibility(View.GONE);
			groupScales.setVisibility(View.GONE);
		}
		else {
			groupCurrency.setVisibility(View.VISIBLE);
			groupLeverage.setVisibility(View.VISIBLE);

			this.currency.setText(details.getTradingAccountInfo().getCurrency().getValue());
			this.leverage.setText(String.format(Locale.getDefault(), "1:%d", details.getTradingAccountInfo().getLeverageMax()));

			if (details.getPublicInfo().getTypeExt().equals(AssetTypeExt.SIGNALTRADINGACCOUNT)) {
				groupScales.setVisibility(View.GONE);
			}
			else {
				groupScales.setVisibility(View.VISIBLE);
				this.genesisRatioProgressBar.setProgress(Double.valueOf(details.getProgramDetails().getGenesisRatio() * 100).intValue());
				this.investScaleProgressBar.setProgress(Double.valueOf(details.getProgramDetails().getInvestmentScale() * 100).intValue());
				this.volumeScaleProgressBar.setProgress(Double.valueOf(details.getProgramDetails().getVolumeScale() * 100).intValue());

				this.genesisRatio.setText(StringFormatUtil.formatAmount(details.getProgramDetails().getGenesisRatio(), 0, 2));
				this.investScale.setText(StringFormatUtil.formatAmount(details.getProgramDetails().getInvestmentScale(), 0, 2));
				this.volumeScale.setText(StringFormatUtil.formatAmount(details.getProgramDetails().getVolumeScale(), 0, 2));
			}
		}
	}

	private void updateProgramDetails(ProgramDetailsFull programDetails) {
		if (details.getPublicInfo().getTypeExt().equals(AssetTypeExt.EXTERNALSIGNALTRADINGACCOUNT)) {
			programGroup.setVisibility(View.GONE);
			return;
		}

		if (programDetails != null) {
			programInfoGroup.setVisibility(View.VISIBLE);
			manageProgramButton.setVisibility(View.VISIBLE);
			createProgramGroup.setVisibility(View.GONE);

			PersonalProgramDetails personalDetails = programDetails.getPersonalDetails();

			availableToInvest.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.getShortenedAmount(programDetails.getAvailableInvestmentBase()).toString(),
					this.details.getTradingAccountInfo().getCurrency().getValue()));

			updateCurrentSelectedField(stopOut, programDetails.getStopOutLevelCurrent(), programDetails.getStopOutLevelSelected());
			updateCurrentSelectedField(managementFee, programDetails.getManagementFeeCurrent(), programDetails.getManagementFeeSelected());
			updateCurrentSelectedField(successFee, programDetails.getSuccessFeeCurrent(), programDetails.getSuccessFeeSelected());

			periodView.setData(programDetails.getPeriodDuration(), programDetails.getPeriodStarts(), programDetails.getPeriodEnds(), true, true);

			depositProgramButton.setEnabled(programDetails.getAvailableInvestmentBase() > 0);

			if (personalDetails != null) {
				depositProgramButton.setEnabled(programDetails.getAvailableInvestmentBase() > 0 && personalDetails.isCanInvest());
				withdrawProgramButton.setEnabled(personalDetails.isCanWithdraw());
			}

		}
		else {
			programInfoGroup.setVisibility(View.GONE);
			manageProgramButton.setVisibility(View.GONE);
			createProgramGroup.setVisibility(View.VISIBLE);
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

	private void updateYourDeposit() {
		if (details.getProgramDetails() != null) {
			PersonalProgramDetails personalDetails = details.getProgramDetails().getPersonalDetails();
			if (personalDetails != null && personalDetails.isIsInvested() && !personalDetails.getStatus().equals(AssetInvestmentStatus.ENDED)) {
				status.setVisibility(View.VISIBLE);
				profitGroup.setVisibility(View.VISIBLE);
				depositProgramButtonsGroup.setVisibility(View.VISIBLE);
				depositFollowButtonsGroup.setVisibility(View.GONE);

				status.setStatus(personalDetails.getStatus().getValue());

				value.setText(StringFormatUtil.getValueString(personalDetails.getValue(),
						details.getTradingAccountInfo().getCurrency().getValue()));

				profit.setText(String.format(Locale.getDefault(), "%s%%",
						StringFormatUtil.formatAmount(getProfitPercent(personalDetails.getInvested(), personalDetails.getValue()), 0, 4)));
				profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
						personalDetails.getValue() < personalDetails.getInvested()
								? R.attr.colorRed
								: R.attr.colorGreen));
			}
		}
		else {
			status.setVisibility(View.GONE);
			profitGroup.setVisibility(View.GONE);
			depositProgramButtonsGroup.setVisibility(View.GONE);

			if (details.getPublicInfo().getTypeExt().equals(AssetTypeExt.EXTERNALSIGNALTRADINGACCOUNT)) {
				value.setText(StringFormatUtil.formatAmount(details.getTradingAccountInfo().getBalance()));
				depositFollowButtonsGroup.setVisibility(View.GONE);
			}
			else {
				value.setText(StringFormatUtil.getValueString(details.getTradingAccountInfo().getBalance(),
						details.getTradingAccountInfo().getCurrency().getValue()));
				depositFollowButtonsGroup.setVisibility(View.VISIBLE);
			}
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

	@Override
	public void showUnfollowTradesActivity(UUID followId, UUID tradingAccountId, String followName, Boolean isExternal) {
		if (getActivity() != null) {
			UnfollowTradesActivity.startWith(getActivity(), followId, tradingAccountId, followName, isExternal);
		}
	}

	@Override
	public void showEditSubscriptionActivity(SubscriptionSettingsModel model, UUID followId, UUID tradingAccountId, Boolean isExternal) {
		if (getActivity() != null) {
			EditSubscriptionActivity.startWith(getActivity(), model, followId, tradingAccountId, isExternal);
		}
	}

	@Override
	public void showCopytradingDetailsActivity(TradingAccountDetailsModel model) {
		if (getActivity() != null) {
			CopytradingDetailsActivity.startWith(getActivity(), model);
		}
	}

	@Override
	public void showTransferFundsActivity(TransferFundsModel model) {
		if (getActivity() != null) {
			TransferFundsActivity.startWith(getActivity(), model);
		}
	}

	public void updateInfo(ProgramFollowDetailsFull details) {
		this.details = details;
		updateAll();
	}
}