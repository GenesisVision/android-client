package vision.genesis.clientapp.feature.main.program.financial_statistics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ManagerFinancialStatistic;
import io.swagger.client.model.ProgramPeriodViewModel;
import io.swagger.client.model.ProgramType;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowFinancialStatisticsDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramFinancialStatisticsAdapter extends RecyclerView.Adapter<ProgramFinancialStatisticsAdapter.PeriodHistoryViewHolder>
{
	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private String programCurrency;

	private ProgramType programType;

	ProgramFinancialStatisticsAdapter(String programCurrency, ProgramType programType) {
		this.programCurrency = programCurrency;
		this.programType = programType;
	}

	@NonNull
	@Override
	public PeriodHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_program_financial_statistics, parent, false);
		PeriodHistoryViewHolder viewHolder = new PeriodHistoryViewHolder(itemView);
		viewHolder.setData(programCurrency, programType);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull PeriodHistoryViewHolder holder, int position) {
		holder.setPeriod(periods.get(position));
	}

	@Override
	public int getItemCount() {
		return periods.size();
	}

	void setData(List<ProgramPeriodViewModel> periods) {
		this.periods.clear();
		this.periods.addAll(periods);
		notifyDataSetChanged();
	}

	void addData(List<ProgramPeriodViewModel> periods) {
		this.periods.addAll(periods);
		notifyDataSetChanged();
	}

	static class PeriodHistoryViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date_started)
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

		@BindView(R.id.deposit_withdraw)
		public TextView depositWithdraw;

		@BindView(R.id.balance_label)
		public TextView balanceLabel;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		@BindView(R.id.success_fee_label)
		public TextView successFeeLabel;

		@BindView(R.id.entry_fee_label)
		public TextView entryFeeLabel;

		@BindView(R.id.deposit_withdraw_label)
		public TextView depositWithdrawLabel;

		private ProgramPeriodViewModel period;

		private String programCurrency;

		private ProgramType programType;

		PeriodHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(view -> EventBus.getDefault().post(new ShowFinancialStatisticsDetailsEvent(period)));
		}

		private void setFonts() {
			balanceLabel.setText(itemView.getContext().getString(R.string.balance).toLowerCase());
			profitLabel.setText(itemView.getContext().getString(R.string.profit).toLowerCase());
			successFeeLabel.setText(itemView.getContext().getString(R.string.success_fee).toLowerCase());
			depositWithdrawLabel.setText(itemView.getContext().getString(R.string.deposit_withdraw).toLowerCase());
		}

		private void setData(String programCurrency, ProgramType programType) {
			this.programCurrency = programCurrency;
			this.programType = programType;
		}

		private void setPeriod(ProgramPeriodViewModel period) {
			this.period = period;

			if (programType.equals(ProgramType.FIXEDPERIOD)) {
				periodNumber.setVisibility(View.VISIBLE);
				periodLength.setVisibility(View.GONE);
				entryFeeLabel.setText(itemView.getContext().getString(R.string.entry_fee).toLowerCase());

				periodNumber.setText("#".concat(period.getNumber().toString()));
			}
			else {
				periodNumber.setVisibility(View.GONE);
				periodLength.setVisibility(View.VISIBLE);
				entryFeeLabel.setText(itemView.getContext().getString(R.string.management_fee).toLowerCase());

				updateProgress();

			}
			dateStarted.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));

			if (period.getBalance() != null) {
				balance.setText(StringFormatUtil.getValueString(period.getBalance(), programCurrency));
			}
			if (period.getProfit() != null) {
				String profitValue = StringFormatUtil.getValueString(period.getProfit(), programCurrency);
				profit.setText(profitValue);
				profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						period.getProfit() >= 0 ? (period.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
			}
			ManagerFinancialStatistic statistic = period.getManagerStatistic();
			if (statistic != null) {
				if (statistic.getSuccessFee() != null) {
					successFee.setText(StringFormatUtil.getValueString(statistic.getSuccessFee(), programCurrency));
				}
				if (statistic.getEntryFee() != null) {
					entryFee.setText(StringFormatUtil.getValueString(statistic.getEntryFee(), programCurrency));
				}
			}

			if (period.getManagerDeposit() != null && period.getManagerWithdraw() != null) {
				double depositWithdrawValue = period.getManagerDeposit() - period.getManagerWithdraw();
				String depositWithdrawStringValue = StringFormatUtil.getValueString(depositWithdrawValue, programCurrency);
				depositWithdraw.setText(depositWithdrawStringValue);
				depositWithdraw.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						depositWithdrawValue >= 0 ? (depositWithdrawValue == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
			}
		}

		private void updateProgress() {
			long periodLength = period.getPeriodLength().longValue();
			this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
		}
	}
}
