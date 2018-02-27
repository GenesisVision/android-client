package vision.genesis.clientapp.feature.main.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgramDashboard;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ManagerAvatarView;

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
		@BindView(R.id.program_logo)
		public ManagerAvatarView programLogo;

		@BindView(R.id.manager_avatar)
		public SimpleDraweeView managerAvatar;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.tokens)
		public TextView tokens;

		@BindView(R.id.account_currency_tokens_worth)
		public TextView accountCurrencyTokensWorth;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.profit_currency)
		public TextView profitCurrency;

		@BindView(R.id.period)
		public TextView period;

		private InvestmentProgramDashboard investmentProgram;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

//			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram)));
		}

		void setInvestmentProgram(InvestmentProgramDashboard investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {

		}
	}
}
