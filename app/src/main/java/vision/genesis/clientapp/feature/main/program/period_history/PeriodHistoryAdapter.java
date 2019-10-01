package vision.genesis.clientapp.feature.main.program.period_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
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
 * Created by Vitaly on 21/08/2019.
 */

public class PeriodHistoryAdapter extends RecyclerView.Adapter<PeriodHistoryAdapter.PeriodHistoryViewHolder>
{
	private List<ProgramPeriodViewModel> periods = new ArrayList<>();

	private String programCurrency;

	PeriodHistoryAdapter(String programCurrency) {
		this.programCurrency = programCurrency;
	}

	@NonNull
	@Override
	public PeriodHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_period_history, parent, false);
		PeriodHistoryViewHolder viewHolder = new PeriodHistoryViewHolder(itemView);
		viewHolder.setCurrency(programCurrency);
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

		@BindView(R.id.period_length_label)
		public TextView periodLengthLabel;

		@BindView(R.id.balance_label)
		public TextView balanceLabel;

		@BindView(R.id.investors_label)
		public TextView investorsLabel;

		@BindView(R.id.profit_label)
		public TextView profitLabel;

		private ProgramPeriodViewModel period;

		private String programCurrency;

		PeriodHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			number.setTypeface(TypefaceUtil.semibold());
			dateStarted.setTypeface(TypefaceUtil.semibold());
			periodLength.setTypeface(TypefaceUtil.semibold());
			balance.setTypeface(TypefaceUtil.semibold());
			investors.setTypeface(TypefaceUtil.semibold());
			profit.setTypeface(TypefaceUtil.semibold());

			periodLengthLabel.setText(itemView.getContext().getString(R.string.period_length).toLowerCase());
			balanceLabel.setText(itemView.getContext().getString(R.string.balance).toLowerCase());
			investorsLabel.setText(itemView.getContext().getString(R.string.investors).toLowerCase());
			profitLabel.setText(itemView.getContext().getString(R.string.profit).toLowerCase());
		}

		private void setCurrency(String programCurrency) {

			this.programCurrency = programCurrency;
		}

		private void setPeriod(ProgramPeriodViewModel period) {
			this.period = period;

			number.setText(String.format(Locale.getDefault(), "#%d", period.getNumber()));
			dateStarted.setText(DateTimeUtil.formatEventDateTime(period.getDateFrom()));
			periodLength.setText(String.format(Locale.getDefault(), "(%s)", DateTimeUtil.getHumanReadablePeriod(period.getPeriodLength().longValue())));

			if (period.getBalance() != null) {
				balance.setText(StringFormatUtil.getValueString(period.getBalance(), programCurrency));
			}
			if (period.getInvestors() != null) {
				investors.setText(String.valueOf(period.getInvestors()));
			}
			if (period.getProfit() != null) {
				profit.setText(StringFormatUtil.getValueString(period.getProfit(), programCurrency));
				profit.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						period.getProfit() >= 0 ? R.attr.colorGreen : R.attr.colorRed));
			}
		}
	}
}
