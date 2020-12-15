package vision.genesis.clientapp.feature.main.program.analytics.details;

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
import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramAnalyticsDetailsBottomSheetFragment extends BottomSheetDialogFragment
{
	@BindView(R.id.investors_invest_label)
	public TextView investorsInvestLabel;

	@BindView(R.id.investors_withdrawal_label)
	public TextView investorsWithdrawalLabel;

	@BindView(R.id.manager_investment_label)
	public TextView managerInvestmentLabel;

	@BindView(R.id.manager_withdrawal_label)
	public TextView managerWithdrawalLabel;


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

	@BindView(R.id.manager_investment)
	public TextView managerInvestment;

	@BindView(R.id.manager_withdrawal)
	public TextView managerWithdrawal;


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

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_program_analytics_details, null);

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
		this.dateStart.setTypeface(TypefaceUtil.semibold());
		this.periodLength.setTypeface(TypefaceUtil.semibold());
		this.investors.setTypeface(TypefaceUtil.semibold());
		this.balance.setTypeface(TypefaceUtil.semibold());
		this.profit.setTypeface(TypefaceUtil.semibold());

		this.investorsInvest.setTypeface(TypefaceUtil.semibold());
		this.investorsWithdrawal.setTypeface(TypefaceUtil.semibold());
		this.managerInvestment.setTypeface(TypefaceUtil.semibold());
		this.managerWithdrawal.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (period != null && dateStart != null && getContext() != null) {
			dateStart.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));

			if (period.getBalance() != null) {
				balance.setText(StringFormatUtil.getValueString(period.getBalance(), programCurrency));
			}
			if (period.getInvestors() != null) {
				investors.setText(String.valueOf(period.getInvestors()));
			}
			if (period.getProfit() != null) {
				String profitValue = StringFormatUtil.getValueString(period.getProfit(), programCurrency);
				profit.setText(profitValue);
				profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
						period.getProfit() >= 0 ? (period.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
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

			if (period.getManagerDeposit() != null && period.getManagerDeposit() != 0) {
				this.managerInvestment.setVisibility(View.VISIBLE);
				this.managerInvestmentLabel.setVisibility(View.VISIBLE);
				this.managerInvestment.setText(StringFormatUtil.getValueString(period.getManagerDeposit(), programCurrency));
			}

			if (period.getManagerWithdraw() != null && period.getManagerWithdraw() != 0) {
				this.managerWithdrawal.setVisibility(View.VISIBLE);
				this.managerWithdrawalLabel.setVisibility(View.VISIBLE);
				this.managerWithdrawal.setText(StringFormatUtil.getValueString(period.getManagerWithdraw(), programCurrency));
			}
		}
	}

	private void updateProgress() {
		long periodLength = period.getPeriodLength().longValue();
		this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
	}
}
