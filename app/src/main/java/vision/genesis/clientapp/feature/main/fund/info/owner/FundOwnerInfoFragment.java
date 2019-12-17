package vision.genesis.clientapp.feature.main.fund.info.owner;

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

import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Currency;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.PersonalFundDetails;
import io.swagger.client.model.ProgramUpdate;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.common.public_info.edit.EditPublicInfoActivity;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.fund.invest.InvestFundActivity;
import vision.genesis.clientapp.feature.main.fund.manage.ManageFundActivity;
import vision.genesis.clientapp.feature.main.fund.withdraw.WithdrawFundActivity;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/12/2019.
 */

public class FundOwnerInfoFragment extends BaseFragment implements FundOwnerInfoView, FundDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_FUND_DETAILS = "extra_fund_details";

	public static FundOwnerInfoFragment with(FundDetailsFull fundDetails) {
		FundOwnerInfoFragment ownerInfoFragment = new FundOwnerInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_FUND_DETAILS, fundDetails);
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


	@BindView(R.id.group_your_investment)
	public ViewGroup yourInvestmentGroup;

	@BindView(R.id.label_your_investment)
	public TextView labelYourInvestment;

	@BindView(R.id.status)
	public InvestmentStatusView status;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.profit_label)
	public TextView profitLabel;

	@BindView(R.id.button_invest)
	public PrimaryButton investButton;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;


	@BindView(R.id.group_fund_info)
	public ViewGroup fundInfoGroup;

	@BindView(R.id.label_fund)
	public TextView labelFund;

	@BindView(R.id.button_manage_fund)
	public TextView manageFundButton;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.label_entry_fee)
	public TextView entryFeeLabel;

	@BindView(R.id.exit_fee)
	public TextView exitFee;

	@BindView(R.id.label_exit_fee)
	public TextView exitFeeLabel;


	@InjectPresenter
	public FundOwnerInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private Unbinder unbinder;

	private FundDetailsFull fundDetails;

	@OnClick(R.id.button_edit_public_info)
	public void onEditPublicInfoClicked() {
		presenter.onEditPublicInfoClicked();
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

	@OnClick(R.id.button_manage_fund)
	public void onManageFundClicked() {
		presenter.onManageFundClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fund_owner_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			fundDetails = getArguments().getParcelable(EXTRA_FUND_DETAILS);
			if (fundDetails != null) {
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
		presenter.setDetails(fundDetails);
		setFundDetails(fundDetails);
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

		labelYourInvestment.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());
		profit.setTypeface(TypefaceUtil.semibold());

		labelFund.setTypeface(TypefaceUtil.semibold());
		manageFundButton.setTypeface(TypefaceUtil.semibold());
		entryFee.setTypeface(TypefaceUtil.semibold());
		exitFee.setTypeface(TypefaceUtil.semibold());

		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		entryFeeLabel.setText(entryFeeLabel.getText().toString().toLowerCase());
		exitFeeLabel.setText(exitFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setFundDetails(FundDetailsFull fundDetails) {
		this.fundDetails = fundDetails;

		if (fundDetails != null) {

			scrollView.setVisibility(View.VISIBLE);

			updatePublicInfo(fundDetails.getPublicInfo().getDescription());
			updateYourDeposit(fundDetails.getPersonalDetails());

			PersonalFundDetails personalDetails = fundDetails.getPersonalDetails();

			updateCurrentSelectedField(entryFee, fundDetails.getEntryFeeCurrent(), fundDetails.getEntryFeeSelected());
			updateCurrentSelectedField(exitFee, fundDetails.getExitFeeCurrent(), fundDetails.getExitFeeSelected());

			if (personalDetails != null) {
				investButton.setEnabled(personalDetails.isCanInvest());
				withdrawButton.setEnabled(personalDetails.isCanWithdraw());
			}
		}
	}

	private void updatePublicInfo(String description) {
		strategy.setText(description);
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null) {
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
			}
		}, 300);
	}

	private void updateCurrentSelectedField(TextView textView, Double current, Double selected) {
		textView.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(current, 0, 4)));
		if (!selected.equals(current)) {
			textView.setText(textView.getText().toString().concat(
					String.format(Locale.getDefault(), " (%s%%)", StringFormatUtil.formatAmount(selected, 0, 4))));
		}
	}

	private void updateYourDeposit(PersonalFundDetails personalDetails) {
		yourInvestmentGroup.setVisibility(View.VISIBLE);
		status.setVisibility(View.VISIBLE);
		status.setStatus(personalDetails.getStatus().getValue());
		value.setText(StringFormatUtil.getValueString(personalDetails.getValue(), Currency.GVT.getValue()));
		profit.setVisibility(View.GONE);
//			profit.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(getProfitPercent(), 0, 4)));
//			profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
//					personalDetails.getValue() < personalDetails.getInvested()
//							? R.attr.colorRed
//							: R.attr.colorGreen));
	}

//	private Double getProfitPercent() {
//		Double invested = fundDetails.getPersonalDetails().get();
//		Double value = fundDetails.getPersonalDetails().getValue();
//		return Math.abs(invested != 0 ? 100 / invested * (invested - value) : 0);
//	}

	@Override
	public void showInvestWithdrawButtons() {
//		buttonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showInvestFundActivity(FundRequest request) {
		if (getActivity() != null) {
			InvestFundActivity.startWith(getActivity(), request);
		}
	}

	@Override
	public void showWithdrawFundActivity(FundRequest request) {
		if (getActivity() != null) {
			WithdrawFundActivity.startWith(getActivity(), request);
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
	public void showRequestsBottomSheet() {
		if (getActivity() != null) {
			RequestsBottomSheetFragment bottomSheetDialog = new RequestsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setAssetId(fundDetails.getId());
		}
	}

	@Override
	public void showLoginActivity() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}


	@Override
	public void showEditPublicInfoActivity(UUID assetId, ProgramUpdate model) {
		if (getActivity() != null) {
			EditPublicInfoActivity.startFrom(getActivity(), assetId, model);
		}
	}

	@Override
	public void showManageFundActivity(FundDetailsFull fundDetails) {
		if (getActivity() != null) {
			ManageFundActivity.startFrom(getActivity(), fundDetails);
		}
	}

	public void updateInfo(FundDetailsFull fundDetails) {
		this.fundDetails = fundDetails;
		updateAll();
	}
}