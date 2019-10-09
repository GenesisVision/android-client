package vision.genesis.clientapp.feature.main.program.period_history.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2019.
 */

public class PeriodDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.investors_invest_label)
	public TextView investorsInvestLabel;

	@BindView(R.id.investors_withdrawal_label)
	public TextView investorsWithdrawalLabel;

	@BindView(R.id.manager_investment_withdrawal_label)
	public TextView managerInvestmentWithdrawalLabel;

	@BindView(R.id.investors_withdrawal_profit_label)
	public TextView investorsWithdrawalProfitLabel;

	@BindView(R.id.platform_success_fee_label)
	public TextView platformSuccessFeeLabel;

	@BindView(R.id.gv_commission_rebate_label)
	public TextView gvCommissionRebateLabel;


	@BindView(R.id.period_number)
	public TextView periodNumber;

	@BindView(R.id.status)
	public TextView status;

	@BindView(R.id.date_start)
	public TextView dateStart;

	@BindView(R.id.period_length)
	public TextView periodLength;

	@BindView(R.id.investors)
	public TextView investors;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.investors_invest)
	public TextView investorsInvest;

	@BindView(R.id.investors_withdrawal)
	public TextView investorsWithdrawal;

	@BindView(R.id.manager_investment_withdrawal)
	public TextView managerInvestmentWithdrawal;

	@BindView(R.id.investors_withdrawal_profit)
	public TextView investorsWithdrawalProfit;

	@BindView(R.id.platform_success_fee)
	public TextView platformSuccessFee;

	@BindView(R.id.gv_commission_rebate)
	public TextView gvCommissionRebate;

	private ProgramPeriodViewModel period;

	private String programCurrency;

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		getDialog().setOnShowListener(dialog1 -> {
			BottomSheetDialog d = (BottomSheetDialog) dialog1;
			View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			if (bottomSheetInternal != null) {
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
			}
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_period_details, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	public void setData(ProgramPeriodViewModel period, String programCurrency) {
		this.period = period;
		this.programCurrency = programCurrency;

		updateView();
	}

	private void setFonts() {
		this.periodNumber.setTypeface(TypefaceUtil.semibold());
		this.status.setTypeface(TypefaceUtil.semibold());
		this.dateStart.setTypeface(TypefaceUtil.semibold());
		this.periodLength.setTypeface(TypefaceUtil.semibold());
		this.investors.setTypeface(TypefaceUtil.semibold());
		this.balance.setTypeface(TypefaceUtil.semibold());
		this.profit.setTypeface(TypefaceUtil.semibold());

		this.investorsInvest.setTypeface(TypefaceUtil.semibold());
		this.investorsWithdrawal.setTypeface(TypefaceUtil.semibold());
		this.managerInvestmentWithdrawal.setTypeface(TypefaceUtil.semibold());
		this.investorsWithdrawalProfit.setTypeface(TypefaceUtil.semibold());
		this.platformSuccessFee.setTypeface(TypefaceUtil.semibold());
		this.gvCommissionRebate.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (period != null && periodNumber != null && getContext() != null) {
			periodNumber.setText(String.format(Locale.getDefault(), "#%d", period.getNumber()));
			dateStart.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));

			if (period.getBalance() != null) {
				balance.setText(StringFormatUtil.getValueString(period.getBalance(), programCurrency));
			}
			if (period.getInvestors() != null) {
				investors.setText(String.valueOf(period.getInvestors()));
			}
			if (period.getProfit() != null) {
				String profitValue = StringFormatUtil.getValueString(period.getProfit(), programCurrency);
				String profitPercent = StringFormatUtil.formatAmount(100 / period.getBalance() * period.getProfit(), 0, 2);
				profit.setText(String.format(Locale.getDefault(), "%s (%s%%)", profitValue, profitPercent));
				profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
						period.getProfit() >= 0 ? (period.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
			}

			switch (period.getStatus()) {
				case PLANNED:
					this.status.setText(getContext().getString(R.string.planned));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
					break;
				case INPROCCESS:
					this.status.setText(getContext().getString(R.string.active));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));
					break;
				case CLOSED:
					this.status.setText(getContext().getString(R.string.closed));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
					break;
			}

			updateProgress();

			if (period.getInvestorsDeposit() != null && period.getInvestorsDeposit() != 0) {
				this.investorsInvest.setVisibility(View.VISIBLE);
				this.investorsInvestLabel.setVisibility(View.VISIBLE);
				this.investorsInvest.setText(StringFormatUtil.getValueString(period.getInvestorsDeposit(), programCurrency));
			}

			if (period.getInvestorsWithdraw() != null && period.getInvestorsWithdraw() != 0) {
				this.investorsWithdrawal.setVisibility(View.VISIBLE);
				this.investorsWithdrawalLabel.setVisibility(View.VISIBLE);
				this.investorsWithdrawal.setText(StringFormatUtil.getValueString(period.getInvestorsWithdraw(), programCurrency));
			}

			if (period.getManagerWithdraw() != null && period.getManagerWithdraw() != 0) {
				this.managerInvestmentWithdrawal.setVisibility(View.VISIBLE);
				this.managerInvestmentWithdrawalLabel.setVisibility(View.VISIBLE);
				this.managerInvestmentWithdrawal.setText(StringFormatUtil.getValueString(period.getManagerWithdraw(), programCurrency));
			}

			if (period.getInvestorsProfitWithdraw() != null && period.getInvestorsProfitWithdraw() != 0) {
				this.investorsWithdrawalProfit.setVisibility(View.VISIBLE);
				this.investorsWithdrawalProfitLabel.setVisibility(View.VISIBLE);
				this.investorsWithdrawalProfit.setText(StringFormatUtil.getValueString(period.getInvestorsProfitWithdraw(), programCurrency));
			}

			if (period.getPlatformSuccessFee() != null && period.getPlatformSuccessFee() != 0) {
				this.platformSuccessFee.setVisibility(View.VISIBLE);
				this.platformSuccessFeeLabel.setVisibility(View.VISIBLE);
				this.platformSuccessFee.setText(StringFormatUtil.getValueString(period.getPlatformSuccessFee(), programCurrency));
			}

			if (period.getManagerCommissionRebate() != null && period.getManagerCommissionRebate() != 0) {
				this.gvCommissionRebate.setVisibility(View.VISIBLE);
				this.gvCommissionRebateLabel.setVisibility(View.VISIBLE);
				this.gvCommissionRebate.setText(StringFormatUtil.getValueString(period.getManagerCommissionRebate(), programCurrency));
			}
		}
	}

	private void updateProgress() {
		long periodLength = period.getPeriodLength().longValue();
		switch (period.getStatus()) {
			case PLANNED:
				periodLength = 0;
				break;
			case INPROCCESS:
				periodLength = Seconds.secondsBetween(period.getDateFrom(), DateTime.now()).getSeconds() * 1000;
				break;
			case CLOSED:
				break;
		}
		this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
	}
}
