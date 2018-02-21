package vision.genesis.clientapp.feature.main.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.swagger.client.model.InvestorProgram;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.InvestorProgramViewHolder>
{
	public List<InvestorProgram> investorPrograms = new ArrayList<>();

	@Override
	public InvestorProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_program, parent, false);
		return new InvestorProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestorProgramViewHolder holder, int position) {
		holder.setInvestorProgram(investorPrograms.get(position));
	}

	@Override
	public int getItemCount() {
		return investorPrograms.size();
	}

	void setInvestorPrograms(List<InvestorProgram> investorPrograms) {
		this.investorPrograms.clear();
		this.investorPrograms.addAll(investorPrograms);
		notifyDataSetChanged();
	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{
//		@BindView(R.id.avatar)
//		public ManagerAvatarView avatar;
//
//		@BindView(R.id.manager_name)
//		public TextView managerName;
//
//		@BindView(R.id.currency)
//		public TextView currency;
//
//		@BindView(R.id.text_deposit_text)
//		public TextView depositText;
//
//		@BindView(R.id.text_trades_text)
//		public TextView tradesText;
//
//		@BindView(R.id.text_period_text)
//		public TextView periodText;
//
//		@BindView(R.id.text_profit_text)
//		public TextView profitText;
//
//		@BindView(R.id.chart)
//		public ProfitChartView chart;

		private InvestorProgram investorProgram;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

//			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram)));
		}

		void setInvestorProgram(InvestorProgram investorProgram) {
			this.investorProgram = investorProgram;
			updateData();
		}

		private void updateData() {

		}
	}
}
