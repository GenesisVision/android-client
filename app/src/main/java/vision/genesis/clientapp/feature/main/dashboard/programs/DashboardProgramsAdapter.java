package vision.genesis.clientapp.feature.main.dashboard.programs;

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
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramDashboard;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowInvestProgramEvent;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowWithdrawProgramEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardProgramsAdapter extends RecyclerView.Adapter<DashboardProgramsAdapter.InvestorProgramViewHolder>
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

		@BindView(R.id.pending_requests)
		public TextView pendingRequestsText;

		@BindView(R.id.profit_percent)
		public TextView profitPercentText;

		@BindView(R.id.profit_currency)
		public TextView profitCurrencyText;

		@BindView(R.id.view_period_left)
		public PeriodLeftView periodLeftView;

		@BindView(R.id.button_invest)
		public View investButton;

		@BindView(R.id.button_withdraw)
		public View withdrawButton;

		private InvestmentProgramDashboard investmentProgram;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram.getId())));
			managerAvatar.hideLevel();
		}

		@OnClick(R.id.button_invest)
		public void onInvestClicked() {
			EventBus.getDefault().post(new ShowInvestProgramEvent(investmentProgram.getId(), investmentProgram.getTitle()));
		}

		@OnClick(R.id.button_withdraw)
		public void onWithdrawClicked() {
			EventBus.getDefault().post(new ShowWithdrawProgramEvent(investmentProgram.getId(), investmentProgram.getTitle(), investmentProgram.getInvestedTokens()));
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

			tokens.setText(String.format(Locale.getDefault(), "%s %s", StringFormatUtil.formatAmount(investmentProgram.getInvestedTokens()), itemView.getContext().getResources().getString(R.string.tokens)));
			accountCurrencyTokensWorth.setText("$0");

			pendingRequestsText.setVisibility(investmentProgram.isHasNewRequests() ? View.VISIBLE : View.GONE);

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
				profitPercentText.setText(String.format(Locale.getDefault(), "%.2f%%", profitPercent));
				profitCurrencyText.setText(String.format(Locale.getDefault(), "$%.2f", profitCurrency));
			}
			else {
				profitPercentText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorFontDark));
				profitCurrencyText.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorFontDark));
				profitPercentText.setText("0.00%");
				profitCurrencyText.setText("$0.00");
			}

			if (investmentProgram.isIsEnabled())
				periodLeftView.setDateTo(investmentProgram.getEndOfPeriod());
			periodLeftView.setProgramClosed(!investmentProgram.isIsEnabled());

			investButton.setVisibility(investmentProgram.isIsInvestEnable() ? View.VISIBLE : View.GONE);
			withdrawButton.setVisibility(investmentProgram.isIsWithdrawEnable() ? View.VISIBLE : View.GONE);
		}
	}
}
