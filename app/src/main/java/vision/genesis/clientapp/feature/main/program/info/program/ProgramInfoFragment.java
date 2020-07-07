package vision.genesis.clientapp.feature.main.program.info.program;

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
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.PersonalProgramDetails;
import io.swagger.client.model.ProfilePublic;
import io.swagger.client.model.ProgramDetailsFull;
import io.swagger.client.model.ProgramFollowDetailsFull;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.manager.ManagerDetailsActivity;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.feature.main.program.withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.model.ManagerDetailsModel;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.AccountAgeView;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PeriodLeftDetailsView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SocialLinksView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramInfoFragment extends BaseFragment implements ProgramInfoView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_DETAILS = "extra_details";

	public static ProgramInfoFragment with(ProgramFollowDetailsFull details) {
		ProgramInfoFragment programInfoFragment = new ProgramInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_DETAILS, details);
		programInfoFragment.setArguments(arguments);
		return programInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.manager_avatar)
	public AvatarView managerAvatar;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.manager_date)
	public TextView managerDate;

	@BindView(R.id.social_links)
	public SocialLinksView socialLinks;

	@BindView(R.id.strategy)
	public TextView strategy;

	@BindView(R.id.strategy_shadow)
	public View strategyShadow;

	@BindView(R.id.view_period)
	public PeriodLeftDetailsView periodView;


	@BindView(R.id.broker_logo)
	public SimpleDraweeView brokerLogo;

	@BindView(R.id.currency)
	public TextView accountCurrency;

	@BindView(R.id.leverage)
	public TextView leverage;

	@BindView(R.id.age_period)
	public AccountAgeView age;

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


	@BindView(R.id.group_your_investment)
	public ViewGroup yourInvestmentGroup;

	@BindView(R.id.label_your_investment)
	public TextView labelYourInvestment;

	@BindView(R.id.status)
	public InvestmentStatusView status;

	@BindView(R.id.invested)
	public TextView invested;

	@BindView(R.id.invested_label)
	public TextView investedLabel;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.profit_label)
	public TextView profitLabel;


	@BindView(R.id.group_personal_commissions)
	public ViewGroup personalCommissionsGroup;

	@BindView(R.id.group_personal_success_fee)
	public ViewGroup personalSuccessFeeGroup;

	@BindView(R.id.personal_success_fee)
	public TextView personalSuccessFee;

	@BindView(R.id.label_personal_success_fee)
	public TextView personalSuccessFeeLabel;

	@BindView(R.id.group_personal_management_fee)
	public ViewGroup personalManagementFeeGroup;

	@BindView(R.id.personal_management_fee)
	public TextView personalManagementFee;

	@BindView(R.id.label_personal_management_fee)
	public TextView personalManagementFeeLabel;


	@BindView(R.id.switch_reinvest)
	public SwitchCompat reinvestSwitch;

	@BindView(R.id.switch_ignore_so)
	public SwitchCompat ignoreSoSwitch;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;

	@BindView(R.id.label_invest_now)
	public TextView labelInvestNow;

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

	@BindView(R.id.button_invest)
	public PrimaryButton investButton;

	@BindView(R.id.invest_info)
	public TextView investInfo;

	@InjectPresenter
	public ProgramInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private ProgramFollowDetailsFull details;

	private Unbinder unbinder;

	private String currency;

	@OnClick(R.id.group_manager)
	public void onManagerClicked() {
		if (getActivity() != null) {
			ProfilePublic manager = details.getOwner();
			ManagerDetailsModel model = new ManagerDetailsModel(
					manager.getId(),
					manager.getLogoUrl(),
					manager.getUsername(),
					manager.getRegistrationDate());
			ManagerDetailsActivity.startWith(getActivity(), model);
		}
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

	@OnClick(R.id.tooltip_reinvest)
	public void onReinvestProfitTooltipClicked() {
		if (getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.reinvest_profit), getString(R.string.tooltip_reinvest_profit_info), false, null);
		}
	}

	@OnClick(R.id.tooltip_ignore_so)
	public void onIgnoreSoTooltipClicked() {
		if (getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.tooltip_ignore_stopout_title), getString(R.string.tooltip_ignore_stopout_info), false, null);
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

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		presenter.onInvestClicked();
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		presenter.onWithdrawClicked();
	}

	@OnClick(R.id.switch_reinvest)
	public void onReinvestClicked() {
		presenter.onReinvestClicked();
	}


	@OnClick(R.id.switch_ignore_so)
	public void onIgnoreSoClicked() {
		presenter.onIgnoreSoClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			details = getArguments().getParcelable(EXTRA_DETAILS);
			if (details != null) {
				currency = details.getTradingAccountInfo().getCurrency().getValue();
				presenter.setDetails(details);
				setDetails(details);

				setFonts();

				withdrawButton.setEmpty();

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
		labelYourInvestment.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());
		profit.setTypeface(TypefaceUtil.semibold());

		labelInvestNow.setTypeface(TypefaceUtil.semibold());
		availableToInvest.setTypeface(TypefaceUtil.semibold());
		stopOut.setTypeface(TypefaceUtil.semibold());
		managementFee.setTypeface(TypefaceUtil.semibold());
		successFee.setTypeface(TypefaceUtil.semibold());

		investedLabel.setText(investedLabel.getText().toString().toLowerCase());
		stopOutLabel.setText(stopOutLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		managementFeeLabel.setText(managementFeeLabel.getText().toString().toLowerCase());
		personalManagementFeeLabel.setText(personalManagementFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setDetails(ProgramFollowDetailsFull details) {
		this.details = details;
		this.currency = details.getTradingAccountInfo().getCurrency().getValue();

		scrollView.setVisibility(View.VISIBLE);

		updateProgramInfo(details);
		updateAccountInfo(details);
		updateYourInvestment(details.getProgramDetails().getPersonalDetails());
		updateInvestNow(details.getProgramDetails());
	}

	private void updateProgramInfo(ProgramFollowDetailsFull details) {
		managerAvatar.setImage(details.getOwner().getLogoUrl(), 100, 100);
		managerName.setText(details.getOwner().getUsername());
		managerDate.setText(DateTimeUtil.formatShortDate(details.getOwner().getRegistrationDate()));

		socialLinks.setData(details.getOwner().getSocialLinks());

		strategy.setText(details.getPublicInfo().getDescription());
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null) {
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
			}
		}, 300);

		ProgramDetailsFull programDetails = details.getProgramDetails();
		periodView.setData(programDetails.getPeriodDuration(), programDetails.getPeriodStarts(), programDetails.getPeriodEnds(), true, true);
	}

	private void updateAccountInfo(ProgramFollowDetailsFull details) {
		this.brokerLogo.setImageURI(ImageUtils.getImageUri(details.getBrokerDetails().getLogoUrl()));
		this.accountCurrency.setText(details.getTradingAccountInfo().getCurrency().getValue());
		this.leverage.setText(String.format(Locale.getDefault(), "1:%d", details.getTradingAccountInfo().getLeverageMax()));
		this.age.setCreationDate(details.getPublicInfo().getCreationDate());

		this.genesisRatioProgressBar.setProgress(Double.valueOf(details.getProgramDetails().getGenesisRatio() * 100).intValue());
		this.investScaleProgressBar.setProgress(Double.valueOf(details.getProgramDetails().getInvestmentScale() * 100).intValue());
		this.volumeScaleProgressBar.setProgress(Double.valueOf(details.getProgramDetails().getVolumeScale() * 100).intValue());

		this.genesisRatio.setText(StringFormatUtil.formatAmount(details.getProgramDetails().getGenesisRatio(), 0, 2));
		this.investScale.setText(StringFormatUtil.formatAmount(details.getProgramDetails().getInvestmentScale(), 0, 2));
		this.volumeScale.setText(StringFormatUtil.formatAmount(details.getProgramDetails().getVolumeScale(), 0, 2));
	}

	private void updateInvestNow(ProgramDetailsFull programDetails) {
		PersonalProgramDetails personalDetails = programDetails.getPersonalDetails();

		availableToInvest.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.getShortenedAmount(programDetails.getAvailableInvestmentBase()).toString(),
				currency));

		updateCurrentSelectedField(stopOut, programDetails.getStopOutLevelCurrent(), programDetails.getStopOutLevelSelected());
		updateCurrentSelectedField(managementFee, programDetails.getManagementFeeCurrent(), programDetails.getManagementFeeSelected());
		updateCurrentSelectedField(successFee, programDetails.getSuccessFeeCurrent(), programDetails.getSuccessFeeSelected());

		investButton.setEnabled(programDetails.getAvailableInvestmentBase() > 0);

		if (personalDetails != null) {
			investButton.setEnabled(programDetails.getAvailableInvestmentBase() > 0 && personalDetails.isCanInvest());
			withdrawButton.setEnabled(personalDetails.isCanWithdraw());
		}

		investInfo.setText(String.format(Locale.getDefault(), getString(R.string.request_info_template), DateTimeUtil.formatShortDateTime(programDetails.getPeriodEnds())));
	}

	private void updateCurrentSelectedField(TextView textView, Double current, Double selected) {
		textView.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(current, 0, 4)));
		if (!selected.equals(current)) {
			textView.setText(textView.getText().toString().concat(
					String.format(Locale.getDefault(), " (%s%%)", StringFormatUtil.formatAmount(selected, 0, 4))));
		}
	}

	private void updateYourInvestment(PersonalProgramDetails personalDetails) {

		if (personalDetails != null && personalDetails.isIsInvested() && !personalDetails.getStatus().equals(AssetInvestmentStatus.ENDED)) {
			yourInvestmentGroup.setVisibility(View.VISIBLE);
			status.setStatus(personalDetails.getStatus().getValue());
			invested.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(personalDetails.getInvested(), 0,
							StringFormatUtil.getCurrencyMaxFraction(currency)),
					currency));
			value.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(personalDetails.getValue(), 0,
							StringFormatUtil.getCurrencyMaxFraction(currency)),
					currency));
			profit.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(getProfitPercent(personalDetails), 0, 4)));
			profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
					personalDetails.getValue() < personalDetails.getInvested()
							? R.attr.colorRed
							: R.attr.colorGreen));

			int personalFeesCounter = 0;
			if (!personalDetails.getSuccessFeePersonal().equals(details.getProgramDetails().getSuccessFeeCurrent())
					|| !personalDetails.getSuccessFeePersonal().equals(details.getProgramDetails().getSuccessFeeSelected())) {
				personalSuccessFeeGroup.setVisibility(View.VISIBLE);
				personalSuccessFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(personalDetails.getSuccessFeePersonal(), 0, 4)));
				personalFeesCounter++;
			}
			else {
				personalSuccessFeeGroup.setVisibility(View.GONE);
			}
			if (!personalDetails.getManagementFeePersonal().equals(details.getProgramDetails().getManagementFeeCurrent())
					|| !personalDetails.getManagementFeePersonal().equals(details.getProgramDetails().getManagementFeeSelected())) {
				personalManagementFeeGroup.setVisibility(View.VISIBLE);
				personalManagementFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(personalDetails.getManagementFeePersonal(), 0, 4)));
				personalFeesCounter++;
			}
			else {
				personalManagementFeeGroup.setVisibility(View.GONE);
			}
			personalCommissionsGroup.setVisibility(personalFeesCounter > 0 ? View.VISIBLE : View.GONE);

			reinvestSwitch.setChecked(personalDetails.isIsReinvest());
			ignoreSoSwitch.setChecked(personalDetails.isIsAutoJoin());
		}
		else {
			yourInvestmentGroup.setVisibility(View.GONE);
		}
	}

	private Double getProfitPercent(PersonalProgramDetails personalDetails) {
		Double invested = personalDetails.getInvested();
		Double value = personalDetails.getValue();
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
	public void setReinvest(Boolean isReinvest) {
		reinvestSwitch.setChecked(isReinvest);
	}


	@Override
	public void setIgnoreSo(Boolean isIgnoreSo) {
		ignoreSoSwitch.setChecked(isIgnoreSo);
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
}