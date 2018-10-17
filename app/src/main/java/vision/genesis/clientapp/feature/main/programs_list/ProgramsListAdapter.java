package vision.genesis.clientapp.feature.main.programs_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ProgramDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.CurrencyView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class ProgramsListAdapter extends RecyclerView.Adapter<ProgramsListAdapter.InvestmentProgramViewHolder>
{
	public List<ProgramDetails> investmentPrograms = new ArrayList<>();

	@Override
	public InvestmentProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_investment_program, parent, false);
		return new InvestmentProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestmentProgramViewHolder holder, int position) {
		holder.setProgram(investmentPrograms.get(position));
	}

	@Override
	public long getItemId(int position) {
		return investmentPrograms.get(position).hashCode();
	}

	@Override
	public int getItemCount() {
		return investmentPrograms.size();
	}

	public void setInvestmentPrograms(List<ProgramDetails> investmentPrograms) {
		this.investmentPrograms.clear();
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	public void addInvestmentPrograms(List<ProgramDetails> investmentPrograms) {
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		for (ProgramDetails program : investmentPrograms) {
			if (program.getId().equals(programId)) {
//				program.isFavorite(isFavorite);
				notifyDataSetChanged();
				break;
			}
		}
	}

	public void removeProgram(UUID programId) {
		for (int i = 0; i < investmentPrograms.size(); i++) {
			if (investmentPrograms.get(i).getId().equals(programId)) {
				investmentPrograms.remove(i);
				notifyItemRemoved(i);
				break;
			}
		}
	}

	static class InvestmentProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

//		@BindView(R.id.icon_favorite)
//		public ImageView favoriteIcon;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.program_currency)
		public CurrencyView programCurrency;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.profit_value)
		public TextView profitValue;

		@BindView(R.id.balance)
		public TextView balance;

		@BindView(R.id.balance_label)
		public TextView balanceLabel;

		@BindView(R.id.available_to_invest)
		public TextView availableToInvest;

		@BindView(R.id.available_to_invest_label)
		public TextView availableToInvestLabel;

		@BindView(R.id.period)
		public PeriodLeftView period;

		@BindView(R.id.period_label)
		public TextView periodLabel;

		private ProgramDetails program;

		InvestmentProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			programLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (program != null) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(program.getId(),
							program.getLogo(),
							program.getColor(),
							program.getLevel(),
							program.getTitle(),
							program.getManager().getUsername(),
							program.getPersonalDetails() != null ?
									program.getPersonalDetails().isIsFavorite()
									: false);
					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.semibold());
			managerName.setTypeface(TypefaceUtil.medium());
			profitPercent.setTypeface(TypefaceUtil.semibold());
			balance.setTypeface(TypefaceUtil.semibold());
			availableToInvest.setTypeface(TypefaceUtil.semibold());

			balanceLabel.setText(balanceLabel.getText().toString().toLowerCase());
			availableToInvestLabel.setText(availableToInvestLabel.getText().toString().toLowerCase());
			periodLabel.setText(periodLabel.getText().toString().toLowerCase());
		}

		void setProgram(ProgramDetails program) {
			this.program = program;
			updateData();
		}

		private void updateData() {
			programLogo.setImage(program.getLogo(), program.getColor(), 100, 100);
			programLogo.setLevel(program.getLevel());

//			favoriteIcon.setVisibility(data.isIsFavorite() ? View.VISIBLE : View.GONE);

			this.programName.setText(program.getTitle());
			this.managerName.setText(program.getManager().getUsername());

			this.chart.setChart(program.getChart());

			Double profitPercent = getProfitPercent();
			Double profitValue = getProfitValue();

			this.programCurrency.setCurrency(program.getCurrency().getValue());

			this.profitPercent.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(profitPercent, 0, 2)));
			this.profitPercent.setTextColor(profitValue >= 0
					? ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorGreen)
					: ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));

			this.profitValue.setText(String.format(Locale.getDefault(), "%s%s GVT",
					profitValue > 0 ? "+" : "",
					StringFormatUtil.formatAmount(profitValue, 0, 4)));

			this.period.setData(program.getPeriodDuration(), program.getPeriodStarts(), program.getPeriodEnds(), true, false);

			this.balance.setText(String.format(Locale.getDefault(), "%s GVT",
					StringFormatUtil.getShortenedAmount(program.getStatistic().getBalanceGVT().getAmount())));

			this.availableToInvest.setText(String.format(Locale.getDefault(), "%s GVT",
					StringFormatUtil.getShortenedAmount(program.getAvailableInvestment())));

		}

		private Double getProfitPercent() {
			Double first = program.getChart().get(0).getValue();
			Double last = program.getChart().get(program.getChart().size() - 1).getValue();

			return Math.abs(first != 0 ? 100 / first * (first - last) : 0);
		}

		private Double getProfitValue() {
			Double first = program.getChart().get(0).getValue();
			Double last = program.getChart().get(program.getChart().size() - 1).getValue();

			return last - first;
		}
	}
}
