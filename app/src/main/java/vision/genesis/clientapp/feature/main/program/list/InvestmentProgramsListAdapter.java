package vision.genesis.clientapp.feature.main.program.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgram;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.ProfitChartView;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class InvestmentProgramsListAdapter extends RecyclerView.Adapter<InvestmentProgramsListAdapter.InvestmentProgramViewHolder>
{
	public List<InvestmentProgram> investmentPrograms = new ArrayList<>();

	@Override
	public InvestmentProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_investment_program, parent, false);
		return new InvestmentProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestmentProgramViewHolder holder, int position) {
		holder.setInvestmentProgram(investmentPrograms.get(position));
		holder.setBackground(position);
	}

	@Override
	public int getItemCount() {
		return investmentPrograms.size();
	}

	void setInvestmentPrograms(List<InvestmentProgram> investmentPrograms) {
		this.investmentPrograms.clear();
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	void addInvestmentPrograms(List<InvestmentProgram> investmentPrograms) {
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	static class InvestmentProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.group_main)
		public ViewGroup mainGroup;

		@BindView(R.id.avatar)
		public AvatarView avatar;

		@BindView(R.id.title)
		public TextView title;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.currency)
		public TextView currency;

		@BindView(R.id.view_program_data)
		public ProgramDataView programDataView;

		@BindView(R.id.chart)
		public ProfitChartView chart;

		private InvestmentProgram investmentProgram;

		InvestmentProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(investmentProgram.getId())));
		}

		private void setFonts() {
			title.setTypeface(TypefaceUtil.bold(itemView.getContext()));

			currency.setTypeface(TypefaceUtil.bold(itemView.getContext()));
		}

		void setBackground(int position) {
			mainGroup.setBackground(position % 2 == 0
					? ContextCompat.getDrawable(itemView.getContext(), R.color.transparent)
					: ContextCompat.getDrawable(itemView.getContext(), R.color.listItemBackgroundGray));

		}

		void setInvestmentProgram(InvestmentProgram investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			avatar.setImage(investmentProgram.getLogo());
			avatar.setLevel(investmentProgram.getLevel());
			title.setText(investmentProgram.getTitle());
//			managerName.setText(investmentProgram.);
			currency.setText(investmentProgram.getCurrency().toString());

			programDataView.setData(investmentProgram.getProfitTotal(),
					investmentProgram.getProfitAvg(),
					investmentProgram.getBalance(),
					investmentProgram.getPeriodDuration());

			chart.setChart(investmentProgram.getChart());
		}
	}
}
