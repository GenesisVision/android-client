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
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PeriodLeftDetailsView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SocialLinksView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.DateTimeUtil;
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

	public static ProgramInfoFragment with(ProgramDetailsFull details) {
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

	@BindView(R.id.switch_reinvest)
	public SwitchCompat reinvestSwitch;

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

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.label_entry_fee)
	public TextView entryFeeLabel;

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

	private ProgramDetailsFull programDetails;

	private Unbinder unbinder;

	@OnClick(R.id.group_manager)
	public void onManagerClicked() {
		if (getActivity() != null) {
			ProfilePublic manager = programDetails.getOwner();
			ManagerDetailsModel model = new ManagerDetailsModel(
					manager.getId(),
					manager.getAvatar(),
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

	@OnClick(R.id.group_entry_fee)
	public void onEntryFeeClicked() {
		if (programDetails != null && programDetails.getLevel() < Constants.MIN_PROGRAM_LEVEL_ENTRY_FEE && getActivity() != null) {
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
			programDetails = getArguments().getParcelable(EXTRA_DETAILS);
			if (programDetails != null) {
				presenter.setProgramDetails(programDetails);
				setProgramDetails(programDetails);

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
		entryFee.setTypeface(TypefaceUtil.semibold());
		successFee.setTypeface(TypefaceUtil.semibold());

		investedLabel.setText(investedLabel.getText().toString().toLowerCase());
		stopOutLabel.setText(stopOutLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		entryFeeLabel.setText(entryFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setProgramDetails(ProgramDetailsFull programDetails) {
		this.programDetails = programDetails;

		scrollView.setVisibility(View.VISIBLE);

		updateProgramInfo(programDetails);
		updateYourInvestment(programDetails);
		updateInvestNow(programDetails);
	}

	private void updateProgramInfo(ProgramDetailsFull programDetails) {
		managerAvatar.setImage(programDetails.getOwner().getAvatar(), 100, 100);
		managerName.setText(programDetails.getOwner().getUsername());
		managerDate.setText(DateTimeUtil.formatShortDate(programDetails.getOwner().getRegistrationDate()));

		socialLinks.setData(programDetails.getOwner().getSocialLinks());

		strategy.setText(programDetails.getDescription());
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null) {
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
			}
		}, 300);

		periodView.setData(programDetails.getPeriodDuration(), programDetails.getPeriodStarts(), programDetails.getPeriodEnds(), true, true);

	}

	private void updateInvestNow(ProgramDetailsFull programDetails) {
		PersonalProgramDetails personalDetails = programDetails.getPersonalDetails();

		availableToInvest.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.getShortenedAmount(programDetails.getAvailableInvestmentBase()).toString(),
				programDetails.getCurrency().getValue()));

		updateCurrentSelectedField(stopOut, programDetails.getStopOutLevelCurrent(), programDetails.getStopOutLevelSelected());
		updateCurrentSelectedField(entryFee, programDetails.getEntryFeeCurrent(), programDetails.getEntryFeeSelected());
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

	private void updateYourInvestment(ProgramDetailsFull programDetails) {
		PersonalProgramDetails personalDetails = programDetails.getPersonalDetails();

		if (personalDetails != null && personalDetails.isIsInvested() && !personalDetails.getStatus().equals(AssetInvestmentStatus.ENDED)) {
			yourInvestmentGroup.setVisibility(View.VISIBLE);
			status.setStatus(personalDetails.getStatus().getValue());
			invested.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(personalDetails.getInvested(), 0,
							StringFormatUtil.getCurrencyMaxFraction(this.programDetails.getCurrency().getValue())),
					this.programDetails.getCurrency().getValue()));
			value.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(personalDetails.getValue(), 0,
							StringFormatUtil.getCurrencyMaxFraction(this.programDetails.getCurrency().getValue())),
					this.programDetails.getCurrency().getValue()));
			profit.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(getProfitPercent(), 0, 4)));
			profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
					personalDetails.getValue() < personalDetails.getInvested()
							? R.attr.colorRed
							: R.attr.colorGreen));

			reinvestSwitch.setChecked(personalDetails.isIsReinvest());
		}
		else {
			yourInvestmentGroup.setVisibility(View.GONE);
		}
	}

	private Double getProfitPercent() {
		Double invested = programDetails.getPersonalDetails().getInvested();
		Double value = programDetails.getPersonalDetails().getValue();
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
	public void showRequestsBottomSheet() {
		if (getActivity() != null) {
			RequestsBottomSheetFragment bottomSheetDialog = new RequestsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setAssetId(programDetails.getId());
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