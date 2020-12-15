package vision.genesis.clientapp.feature.main.program.reports;

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
import io.swagger.client.model.InvestorFinancialStatistic;
import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowReportDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramReportsAdapter extends RecyclerView.Adapter<ProgramReportsAdapter.PeriodHistoryViewHolder>
{
	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private String programCurrency;

	private int periodDurationDays;

	ProgramReportsAdapter(String programCurrency, int periodDurationDays) {
		this.programCurrency = programCurrency;
		this.periodDurationDays = periodDurationDays;
	}

	@NonNull
	@Override
	public PeriodHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_program_reports, parent, false);
		PeriodHistoryViewHolder viewHolder = new PeriodHistoryViewHolder(itemView);
		viewHolder.setData(programCurrency, periodDurationDays);
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

		@BindView(R.id.period_length)
		public TextView periodLength;

		@BindView(R.id.balance)
		public TextView balance;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.commissions)
		public TextView commissions;

		@BindView(R.id.deposit_withdraw)
		public TextView depositWithdraw;

		@BindView(R.id.balance_label)
		public TextView balanceLabel;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		@BindView(R.id.commissions_label)
		public TextView commissionsLabel;

		@BindView(R.id.deposit_withdraw_label)
		public TextView depositWithdrawLabel;

		private ProgramPeriodViewModel period;

		private String programCurrency;

		PeriodHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(view -> EventBus.getDefault().post(new ShowReportDetailsEvent(period)));
		}

		private void setFonts() {
			balanceLabel.setText(itemView.getContext().getString(R.string.balance).toLowerCase());
			profitLabel.setText(itemView.getContext().getString(R.string.profit).toLowerCase());
			commissionsLabel.setText(itemView.getContext().getString(R.string.commissions).toLowerCase());
			depositWithdrawLabel.setText(itemView.getContext().getString(R.string.deposit_withdraw).toLowerCase());
		}

		private void setData(String programCurrency, int periodDurationDays) {
			this.programCurrency = programCurrency;
		}

		private void setPeriod(ProgramPeriodViewModel period) {
			this.period = period;

			dateStarted.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));
			updateProgress();

			InvestorFinancialStatistic statistics = period.getInvestorStatistic();
			if (statistics != null) {
				if (statistics.getBalance() != null) {
					balance.setText(StringFormatUtil.getValueString(statistics.getBalance(), programCurrency));
				}

				if (statistics.getProfit() != null) {
					String profitValue = StringFormatUtil.getValueString(statistics.getProfit(), programCurrency);
					profit.setText(profitValue);
					profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
							statistics.getProfit() >= 0 ? (statistics.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
				}

				commissions.setText(StringFormatUtil.getValueString(
						statistics.getPlatformSuccessFee() + statistics.getManagerManagementFee() + statistics.getManagerSuccessFee(),
						programCurrency));

				depositWithdraw.setText(StringFormatUtil.getValueString(
						statistics.getDeposits() - statistics.getWithdrawals(),
						programCurrency));
			}
		}

		private void updateProgress() {
			long periodLength = period.getPeriodLength().longValue();
			this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
		}
	}
}
