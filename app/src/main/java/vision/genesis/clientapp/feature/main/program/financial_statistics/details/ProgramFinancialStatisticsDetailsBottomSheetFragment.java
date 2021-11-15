package vision.genesis.clientapp.feature.main.program.financial_statistics.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ManagerFinancialStatistic;
import io.swagger.client.model.ProgramPeriodViewModel;
import io.swagger.client.model.ProgramType;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramFinancialStatisticsDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.date_start)
	public TextView dateStarted;

	@BindView(R.id.period_number)
	public TextView periodNumber;

	@BindView(R.id.period_length)
	public TextView periodLength;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.success_fee)
	public TextView successFee;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.deposit)
	public TextView deposit;

	@BindView(R.id.withdraw)
	public TextView withdraw;

	@BindView(R.id.balance_label)
	public TextView balanceLabel;

	@BindView(R.id.period_number_label)
	public TextView periodNumberLabel;

	@BindView(R.id.period_length_label)
	public TextView periodLengthLabel;

	@BindView(R.id.profit_label)
	public TextView profitLabel;

	@BindView(R.id.success_fee_label)
	public TextView successFeeLabel;

	@BindView(R.id.entry_fee_label)
	public TextView entryFeeLabel;

	private ProgramPeriodViewModel period;

	private ProgramType programType;

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

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_program_financial_statistics_details, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		successFeeLabel.setText(StringFormatUtil.capitalize(dialog.getContext().getString(R.string.success_fee)));

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

	public void setData(ProgramPeriodViewModel period, ProgramType programType, String programCurrency) {
		this.period = period;
		this.programType = programType;
		this.programCurrency = programCurrency;

		updateView();
	}

	private void updateView() {
		if (period != null && dateStarted != null && getContext() != null) {
			if (programType.equals(ProgramType.FIXEDPERIOD)) {
				periodNumber.setVisibility(View.VISIBLE);
				periodNumberLabel.setVisibility(View.VISIBLE);
				periodLength.setVisibility(View.GONE);
				periodLengthLabel.setVisibility(View.GONE);
				entryFeeLabel.setText(getContext().getString(R.string.entry_fee));

				periodNumber.setText("#".concat(period.getNumber().toString()));
			}
			else {
				periodNumber.setVisibility(View.GONE);
				periodNumberLabel.setVisibility(View.GONE);
				periodLength.setVisibility(View.VISIBLE);
				periodLengthLabel.setVisibility(View.VISIBLE);
				entryFeeLabel.setText(getContext().getString(R.string.management_fee));

				updateProgress();

			}
			dateStarted.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));

			ManagerFinancialStatistic statistic = period.getManagerStatistic();
			if (statistic != null) {
				if (statistic.getSuccessFee() != null) {
					successFee.setText(StringFormatUtil.getValueString(statistic.getSuccessFee(), programCurrency));
				}
				if (statistic.getEntryFee() != null) {
					entryFee.setText(StringFormatUtil.getValueString(statistic.getEntryFee(), programCurrency));
				}
				if (statistic.getBalance() != null) {
					balance.setText(StringFormatUtil.getValueString(statistic.getBalance(), programCurrency));
				}
				if (statistic.getProfit() != null) {
					String profitValue = StringFormatUtil.getValueString(statistic.getProfit(), programCurrency);
					profit.setText(profitValue);
					profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
							statistic.getProfit() >= 0 ? (statistic.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
				}
			}

			if (period.getManagerDeposit() != null) {
				deposit.setText(StringFormatUtil.getValueString(period.getManagerDeposit(), programCurrency));
			}

			if (period.getManagerWithdraw() != null) {
				withdraw.setText(StringFormatUtil.getValueString(period.getManagerWithdraw(), programCurrency));
			}
		}
	}

	private void updateProgress() {
		long periodLength = period.getPeriodLength().longValue();
		this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
	}
}
