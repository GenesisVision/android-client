package vision.genesis.clientapp.feature.main.program.reports.details;

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
import io.swagger.client.model.InvestorFinancialStatistic;
import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramReportsDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.success_fee_label)
	public TextView successFeeLabel;


	@BindView(R.id.date_start)
	public TextView dateStart;

	@BindView(R.id.period_length)
	public TextView periodLength;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.success_fee)
	public TextView successFee;

	@BindView(R.id.platform_success_fee)
	public TextView platformSuccessFee;

	@BindView(R.id.management_fee)
	public TextView managementFee;

	@BindView(R.id.deposits)
	public TextView deposits;

	@BindView(R.id.withdrawals)
	public TextView withdrawals;


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

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_program_reports_details, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		this.successFeeLabel.setText(StringFormatUtil.capitalize(getString(R.string.success_fee)));

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

	private void updateView() {
		if (period != null && dateStart != null && getContext() != null) {
			dateStart.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));
			updateProgress();

			InvestorFinancialStatistic statistics = period.getInvestorStatistic();
			if (statistics != null) {
				if (statistics.getBalance() != null) {
					balance.setText(StringFormatUtil.getValueString(statistics.getBalance(), programCurrency));
				}

				if (statistics.getProfit() != null) {
					String profitValue = StringFormatUtil.getValueString(statistics.getProfit(), programCurrency);
					profit.setText(profitValue);
					profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
							statistics.getProfit() >= 0 ? (statistics.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
				}

				successFee.setText(StringFormatUtil.getValueString(statistics.getManagerSuccessFee(), programCurrency));
				platformSuccessFee.setText(StringFormatUtil.getValueString(statistics.getPlatformSuccessFee(), programCurrency));
				managementFee.setText(StringFormatUtil.getValueString(statistics.getManagerManagementFee(), programCurrency));

				deposits.setText(StringFormatUtil.getValueString(statistics.getDeposits(), programCurrency));
				withdrawals.setText(StringFormatUtil.getValueString(statistics.getWithdrawals(), programCurrency));
			}
		}
	}

	private void updateProgress() {
		long periodLength = period.getPeriodLength().longValue();
		this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
	}
}
