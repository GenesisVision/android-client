package vision.genesis.clientapp.feature.main.dashboard;

import android.support.v4.content.ContextCompat;
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
import io.swagger.client.model.InvestmentProgramDashboard;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.InvestorProgramViewHolder>
{
	public List<InvestmentProgramDashboard> investorPrograms = new ArrayList<>();

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

	void setInvestorPrograms(List<InvestmentProgramDashboard> investorPrograms) {
		this.investorPrograms.clear();
		this.investorPrograms.addAll(investorPrograms);
		notifyDataSetChanged();
	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.manager_avatar)
		public AvatarView managerAvatar;

		@BindView(R.id.program_logo)
		public AvatarView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.tokens)
		public TextView tokens;

		@BindView(R.id.account_currency_tokens_worth)
		public TextView accountCurrencyTokensWorth;

		@BindView(R.id.profit_percent)
		public TextView profitPercentText;

		@BindView(R.id.profit_currency)
		public TextView profitCurrencyText;

		@BindView(R.id.period)
		public TextView period;

		@BindView(R.id.period_days)
		public TextView periodDays;

		private InvestmentProgramDashboard investmentProgram;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram.getId())));
			managerAvatar.hideLevel();
		}

		void setInvestmentProgram(InvestmentProgramDashboard investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			managerAvatar.setImage(investmentProgram.getManager().getAvatar());

			programLogo.setImage(investmentProgram.getLogo());
			programLogo.setLevel(investmentProgram.getLevel());

			programName.setText(investmentProgram.getTitle());

			tokens.setText(itemView.getContext().getResources().getQuantityString(R.plurals.tokens, investmentProgram.getInvestedTokens(), investmentProgram.getInvestedTokens()));
			accountCurrencyTokensWorth.setText("$0");

			double profitPercent = investmentProgram.getProfitAvg();
			double profitCurrency = 0.00;
			profitPercentText.setText(String.format(Locale.getDefault(), "%.2f%%", profitPercent));
			profitCurrencyText.setText(String.format(Locale.getDefault(), "$%.2f", profitCurrency));
			if (profitPercent > 0) {
				profitPercentText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionGreen));
				profitCurrencyText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionGreen));
				profitPercentText.setText(String.format(Locale.getDefault(), "+%.2f%%", profitPercent));
				profitCurrencyText.setText(String.format(Locale.getDefault(), "+$%.2f", profitCurrency));
			}
			else if (profitPercent < 0) {
				profitPercentText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionRed));
				profitCurrencyText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionRed));
				profitPercentText.setText(String.format(Locale.getDefault(), "-%.2f%%", profitPercent));
				profitCurrencyText.setText(String.format(Locale.getDefault(), "-$%.2f", profitCurrency));
			}
			else {
				profitPercentText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorFontDark));
				profitCurrencyText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorFontDark));
				profitPercentText.setText("0.00%");
				profitCurrencyText.setText("$0.00");
			}

			int daysToPeriodEnd = DateTimeUtil.getDaysToDate(investmentProgram.getEndOfPeriod());
			period.setText(String.valueOf(daysToPeriodEnd));
			periodDays.setText(itemView.getContext().getResources().getQuantityString(R.plurals.days, daysToPeriodEnd, daysToPeriodEnd, daysToPeriodEnd));
		}
	}
}
