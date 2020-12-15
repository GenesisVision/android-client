package vision.genesis.clientapp.feature.main.program.analytics;

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
import io.swagger.client.model.ProgramPeriodViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowPeriodDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramAnalyticsAdapter extends RecyclerView.Adapter<ProgramAnalyticsAdapter.PeriodHistoryViewHolder>
{
	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private String programCurrency;

	private int periodDurationDays;

	ProgramAnalyticsAdapter(String programCurrency, int periodDurationDays) {
		this.programCurrency = programCurrency;
		this.periodDurationDays = periodDurationDays;
	}

	@NonNull
	@Override
	public PeriodHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_program_analytics, parent, false);
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

		@BindView(R.id.investors)
		public TextView investors;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.balance_label)
		public TextView balanceLabel;

		@BindView(R.id.investors_label)
		public TextView investorsLabel;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		private ProgramPeriodViewModel period;

		private String programCurrency;

		private int periodDurationDays;

		PeriodHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(view -> EventBus.getDefault().post(new ShowPeriodDetailsEvent(period)));
		}

		private void setFonts() {
			balanceLabel.setText(itemView.getContext().getString(R.string.program_balance).toLowerCase());
			investorsLabel.setText(itemView.getContext().getString(R.string.investors_max).toLowerCase());
			profitLabel.setText(itemView.getContext().getString(R.string.profit).toLowerCase());
		}

		private void setData(String programCurrency, int periodDurationDays) {
			this.programCurrency = programCurrency;
			this.periodDurationDays = periodDurationDays;
		}

		private void setPeriod(ProgramPeriodViewModel period) {
			this.period = period;

			dateStarted.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));

			if (period.getBalance() != null) {
				balance.setText(StringFormatUtil.getValueString(period.getBalance(), programCurrency));
			}
			if (period.getInvestors() != null) {
				investors.setText(String.valueOf(period.getInvestors()));
			}
			if (period.getProfit() != null) {
				String profitValue = StringFormatUtil.getValueString(period.getProfit(), programCurrency);
				profit.setText(profitValue);
				profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						period.getProfit() >= 0 ? (period.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
			}

			updateProgress();
		}

		private void updateProgress() {
			long periodLength = period.getPeriodLength().longValue();
			this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
		}
	}
}
