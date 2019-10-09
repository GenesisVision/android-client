package vision.genesis.clientapp.feature.main.program.period_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

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
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/08/2019.
 */

public class PeriodHistoryAdapter extends RecyclerView.Adapter<PeriodHistoryAdapter.PeriodHistoryViewHolder>
{
	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private String programCurrency;

	private int periodDurationDays;

	PeriodHistoryAdapter(String programCurrency, int periodDurationDays) {
		this.programCurrency = programCurrency;
		this.periodDurationDays = periodDurationDays;
	}

	@NonNull
	@Override
	public PeriodHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_period_history, parent, false);
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

	void setPeriod(List<ProgramPeriodViewModel> periods) {
		this.periods.clear();
		this.periods.addAll(periods);
		notifyDataSetChanged();
	}

	void addPeriod(List<ProgramPeriodViewModel> periods) {
		this.periods.addAll(periods);
		notifyDataSetChanged();
	}

	static class PeriodHistoryViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.number)
		public TextView number;

		@BindView(R.id.status)
		public TextView status;

		@BindView(R.id.date_started)
		public TextView dateStarted;

		@BindView(R.id.period_length)
		public TextView periodLength;

		@BindView(R.id.period_progress)
		public ProgressBar periodProgress;

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
			number.setTypeface(TypefaceUtil.semibold());
			status.setTypeface(TypefaceUtil.semibold());
			dateStarted.setTypeface(TypefaceUtil.semibold());
//			periodLength.setTypeface(TypefaceUtil.semibold());
			balance.setTypeface(TypefaceUtil.semibold());
			investors.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());

			balanceLabel.setText(itemView.getContext().getString(R.string.balance).toLowerCase());
			investorsLabel.setText(itemView.getContext().getString(R.string.investors).toLowerCase());
			profitLabel.setText(itemView.getContext().getString(R.string.profit).toLowerCase());
		}

		private void setData(String programCurrency, int periodDurationDays) {
			this.programCurrency = programCurrency;
			this.periodDurationDays = periodDurationDays;
		}

		private void setPeriod(ProgramPeriodViewModel period) {
			this.period = period;

			number.setText(String.format(Locale.getDefault(), "#%d", period.getNumber()));
			dateStarted.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));

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
				profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						period.getProfit() >= 0 ? (period.getProfit() == 0 ? R.attr.colorTextPrimary : R.attr.colorGreen) : R.attr.colorRed));
			}

			switch (period.getStatus()) {
				case PLANNED:
					this.status.setText(itemView.getContext().getString(R.string.planned));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorPending));
					break;
				case INPROCCESS:
					this.status.setText(itemView.getContext().getString(R.string.active));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorGreen));
					break;
				case CLOSED:
					this.status.setText(itemView.getContext().getString(R.string.closed));
					this.status.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));
					break;
			}

			updateProgress();
		}

		private void updateProgress() {
			long periodLength = period.getPeriodLength().longValue();
//			periodProgress.setMax(Seconds.secondsBetween(period.getDateFrom(), period.getDateTo()).getSeconds());
			periodProgress.setMax(periodDurationDays * 24 * 60 * 60);
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
			periodProgress.setProgress((int) (periodLength / 1000));
			this.periodLength.setText(String.format(Locale.getDefault(), "%s", DateTimeUtil.getHumanReadablePeriod(periodLength)));
		}
	}
}
