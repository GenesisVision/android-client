package vision.genesis.clientapp.feature.main.fund.info;

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
import io.swagger.client.model.AssetInvestmentStatus;
import io.swagger.client.model.FundChartStatistic;
import io.swagger.client.model.FundDetailsFull;
import io.swagger.client.model.PersonalFundDetails;
import io.swagger.client.model.ProfilePublic;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.common.requests.RequestsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.fund.invest.InvestFundActivity;
import vision.genesis.clientapp.feature.main.fund.withdraw.WithdrawFundActivity;
import vision.genesis.clientapp.feature.main.user.UserDetailsActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.InvestmentStatusView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/10/2018.
 */

public class FundInfoFragment extends BaseFragment implements FundInfoView, FundDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_FUND_ID = "extra_fund_id";

	public static FundInfoFragment with(UUID fundId) {
		FundInfoFragment fundInfoFragment = new FundInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_FUND_ID, fundId);
		fundInfoFragment.setArguments(arguments);
		return fundInfoFragment;
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

	@BindView(R.id.strategy)
	public TextView strategy;

	@BindView(R.id.strategy_shadow)
	public View strategyShadow;


	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.label_balance)
	public TextView balanceLabel;

	@BindView(R.id.investors)
	public TextView investors;

	@BindView(R.id.label_investors)
	public TextView investorsLabel;

	@BindView(R.id.start_day)
	public TextView startDay;

	@BindView(R.id.label_start_day)
	public TextView startDayLabel;


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

	@BindView(R.id.group_personal_exit_fee)
	public ViewGroup personalExitFeeGroup;

	@BindView(R.id.personal_exit_fee)
	public TextView personalExitFee;

	@BindView(R.id.label_personal_exit_fee)
	public TextView personalExitFeeLabel;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;


	@BindView(R.id.label_invest_now)
	public TextView labelInvestNow;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.label_entry_fee)
	public TextView entryFeeLabel;

	@BindView(R.id.exit_fee)
	public TextView exitFee;

	@BindView(R.id.label_exit_fee)
	public TextView exitFeeLabel;

	@BindView(R.id.button_invest)
	public PrimaryButton investButton;

	@BindView(R.id.invest_info)
	public TextView investInfo;

	@InjectPresenter
	public FundInfoPresenter fundInfoPresenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private FundDetailsFull fundDetails;

	private Unbinder unbinder;

	@OnClick(R.id.group_manager)
	public void onManagerClicked() {
		if (getActivity() != null) {
			ProfilePublic manager = fundDetails.getOwner();
			UserDetailsModel model = new UserDetailsModel(
					manager.getId(),
					manager.getLogoUrl(),
					manager.getUsername(),
					manager.getRegistrationDate());
			UserDetailsActivity.startWith(getActivity(), model);
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
		fundInfoPresenter.onStatusClicked();
	}

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		fundInfoPresenter.onInvestClicked();
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		fundInfoPresenter.onWithdrawClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fund_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		fundInfoPresenter.setFundId((UUID) getArguments().getSerializable(EXTRA_FUND_ID));

		setFonts();

		withdrawButton.setEmpty();
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
		entryFee.setTypeface(TypefaceUtil.semibold());
		exitFee.setTypeface(TypefaceUtil.semibold());

		balanceLabel.setText(balanceLabel.getText().toString().toLowerCase());
		investorsLabel.setText(investorsLabel.getText().toString().toLowerCase());
		startDayLabel.setText(startDayLabel.getText().toString().toLowerCase());

		investedLabel.setText(investedLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		entryFeeLabel.setText(entryFeeLabel.getText().toString().toLowerCase());
		exitFeeLabel.setText(exitFeeLabel.getText().toString().toLowerCase());
		personalExitFeeLabel.setText(personalExitFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setFundDetails(FundDetailsFull fundDetails) {
		this.fundDetails = fundDetails;

		scrollView.setVisibility(View.VISIBLE);

		managerAvatar.setImage(fundDetails.getOwner().getLogoUrl(), 100, 100);
		managerName.setText(fundDetails.getOwner().getUsername());
		managerDate.setText(DateTimeUtil.formatShortDate(fundDetails.getOwner().getRegistrationDate()));

		strategy.setText(fundDetails.getPublicInfo().getDescription());
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null) {
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
			}
		}, 300);

		PersonalFundDetails personalDetails = fundDetails.getPersonalDetails();
		if (personalDetails != null && !personalDetails.getStatus().equals(AssetInvestmentStatus.ENDED)) {
			yourInvestmentGroup.setVisibility(View.VISIBLE);
			status.setStatus(personalDetails.getStatus().getValue());
//		invested.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.getShortenedAmount(fundDetails.getPersonalProgramDetails().getInvested()).toString()));
//			invested.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(personalDetails.getInvested(), CurrencyEnum.GVT.toString())));
			value.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(personalDetails.getValue(), CurrencyEnum.GVT.toString())));
//			profit.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(personalDetails.getProfit(), 0, 4)));
			profit.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(getProfitPercent(personalDetails), 0, 4)));
//			profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(), personalDetails.getProfit() < 0 ? R.attr.colorRed : R.attr.colorGreen));
		}
		else {
			yourInvestmentGroup.setVisibility(View.GONE);
		}

		updateCurrentSelectedField(entryFee, fundDetails.getEntryFeeCurrent(), fundDetails.getEntryFeeSelected());
		updateCurrentSelectedField(exitFee, fundDetails.getExitFeeCurrent(), fundDetails.getExitFeeSelected());

		if (personalDetails != null) {
			investButton.setEnabled(personalDetails.isCanInvest());
			withdrawButton.setEnabled(personalDetails.isCanWithdraw());

			if (personalDetails.getExitFeePersonal() != null && (!personalDetails.getExitFeePersonal().equals(fundDetails.getExitFeeCurrent())
					|| !personalDetails.getExitFeePersonal().equals(fundDetails.getExitFeeSelected()))) {
				personalExitFeeGroup.setVisibility(View.VISIBLE);
				personalExitFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(personalDetails.getExitFeePersonal(), 0, 4)));
			}
			else {
				personalExitFeeGroup.setVisibility(View.GONE);
			}
		}

//		investInfo.setText(String.format(Locale.getDefault(), getString(R.string.request_info_template), DateTimeUtil.formatShortDateTime(fundDetails.getPeriodEnds())));
	}

	@Override
	public void updateStatistics(FundChartStatistic statistic, String baseCurrency) {
		this.balance.setText(StringFormatUtil.getValueString(statistic.getBalance(), baseCurrency));
		this.investors.setText(String.valueOf(statistic.getInvestors()));
		this.startDay.setText(DateTimeUtil.formatDate(statistic.getCreationDate()));
	}

	private void updateCurrentSelectedField(TextView textView, Double current, Double selected) {
		textView.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(current, 0, 4)));
		if (!selected.equals(current)) {
			textView.setText(textView.getText().toString().concat(
					String.format(Locale.getDefault(), " (%s%%)", StringFormatUtil.formatAmount(selected, 0, 4))));
		}
	}

	private Double getProfitPercent(PersonalFundDetails personalDetails) {
//		Double first = personalDetails.getInvested();
		Double first = personalDetails.getValue();
		Double last = personalDetails.getValue();

		return Math.abs(first != 0 ? 100 / first * (first - last) : 0);
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
		if (fundInfoPresenter != null) {
			fundInfoPresenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
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
	public void showLoginActivity() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}

	@Override
	public void showRequestsBottomSheet() {
		if (getActivity() != null) {
			RequestsBottomSheetFragment bottomSheetDialog = new RequestsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setAssetId(fundDetails.getId());
		}
	}
}