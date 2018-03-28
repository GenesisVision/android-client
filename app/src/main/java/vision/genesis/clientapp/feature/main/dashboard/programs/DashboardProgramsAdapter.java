package vision.genesis.clientapp.feature.main.dashboard.programs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProfitChartView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardProgramsAdapter extends RecyclerView.Adapter<DashboardProgramsAdapter.InvestorProgramViewHolder>
{
	public List<InvestmentProgramDashboardInvestor> investorPrograms = new ArrayList<>();

	@Override
	public InvestorProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_program, parent, false);
		return new InvestorProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestorProgramViewHolder holder, int position) {
		holder.setInvestmentProgram(investorPrograms.get(position));
	}

	@Override
	public int getItemCount() {
		return investorPrograms.size();
	}

	void setInvestorPrograms(List<InvestmentProgramDashboardInvestor> investorPrograms) {
		this.investorPrograms.clear();
		this.investorPrograms.addAll(investorPrograms);
		notifyDataSetChanged();
	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{

		@BindView(R.id.program_logo)
		public AvatarView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.chart)
		public ProfitChartView chart;

		@BindView(R.id.tokens)
		public TextView tokens;

		@BindView(R.id.tokens_fiat)
		public TextView tokensFiat;

		@BindView(R.id.label_my_tokens)
		public TextView myTokensLabel;

		@BindView(R.id.profit)
		public TextView profit;

		@BindView(R.id.profit_currency)
		public TextView profitCurrency;

		@BindView(R.id.label_profit)
		public TextView profitLabel;

		@BindView(R.id.view_period_left)
		public PeriodLeftView periodLeftView;

		private Context context;

		private InvestmentProgramDashboardInvestor investmentProgram;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			context = itemView.getContext();

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram.getId())));
		}

		private void setFonts() {
			tokensFiat.setTypeface(TypefaceUtil.bold(context));
			myTokensLabel.setTypeface(TypefaceUtil.bold(context));
			profitCurrency.setTypeface(TypefaceUtil.bold(context));
			profitLabel.setTypeface(TypefaceUtil.bold(context));
		}

		void setInvestmentProgram(InvestmentProgramDashboardInvestor investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			programLogo.setImage(investmentProgram.getLogo());
			programLogo.setLevel(investmentProgram.getLevel());

			programName.setText(investmentProgram.getTitle());
			managerName.setText(String.format(Locale.getDefault(), "%s %s",
					context.getResources().getString(R.string.by),
					investmentProgram.getManager().getUsername()));

			chart.setChart(investmentProgram.getChart());

			tokens.setText(String.valueOf(investmentProgram.getInvestedTokens()));
			double tokensFiatValue = investmentProgram.getInvestedTokens() * investmentProgram.getToken().getInitialPrice();
			tokensFiat.setText(String.format(Locale.getDefault(), "($%.2f)", tokensFiatValue));

			profit.setText(String.format(Locale.getDefault(), "%.2f", investmentProgram.getProfitFromProgram()));
//			profitFiat.setText(String.format(Locale.getDefault(), "$%.2f", profitCurrency));
//			if (profitPercent > 0) {
//				profit.setTextColor(ContextCompat.getColor(context, R.color.transactionGreen));
//				profitFiat.setTextColor(ContextCompat.getColor(context, R.color.transactionGreen));
//				profit.setText(String.format(Locale.getDefault(), "+%.2f%%", profitPercent));
//				profitFiat.setText(String.format(Locale.getDefault(), "+$%.2f", profitCurrency));
//			}
//			else if (profitPercent < 0) {
//				profit.setTextColor(ContextCompat.getColor(context, R.color.transactionRed));
//				profitFiat.setTextColor(ContextCompat.getColor(context, R.color.transactionRed));
//				profit.setText(String.format(Locale.getDefault(), "%.2f%%", profitPercent));
//				profitFiat.setText(String.format(Locale.getDefault(), "$%.2f", profitCurrency));
//			}
//			else {
//				profit.setTextColor(ContextCompat.getColor(context, R.color.colorFontDark));
//				profitFiat.setTextColor(ContextCompat.getColor(context, R.color.colorFontDark));
//				profit.setText("0.00%");
//				profitFiat.setText("$0.00");
//			}

			if (investmentProgram.isIsEnabled())
				periodLeftView.setDateTo(investmentProgram.getStartOfPeriod(), investmentProgram.getEndOfPeriod());
			periodLeftView.setProgramClosed(!investmentProgram.isIsEnabled());
		}
	}
}
