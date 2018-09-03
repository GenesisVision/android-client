package vision.genesis.clientapp.feature.main.dashboard.investor.programs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.DashboardProgramDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class DashboardProgramsAdapter extends RecyclerView.Adapter<DashboardProgramsAdapter.ProgramViewHolder>
{
	private List<DashboardProgramDetails> programs = new ArrayList<>();

	@Override
	public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_program, parent, false);
		return new ProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ProgramViewHolder holder, int position) {
		if (programs.get(position) != null)
			holder.setProgram(programs.get(position));
	}

	@Override
	public int getItemCount() {
		return programs.size();
	}

	@Override
	public long getItemId(int position) {
		return programs.get(position) != null
				? programs.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	void setPrograms(List<DashboardProgramDetails> programs) {
		this.programs.clear();
		this.programs.addAll(programs);
		notifyDataSetChanged();
	}

	static class ProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.profit_value)
		public TextView profitValue;

		@BindView(R.id.current_value_label)
		public TextView currentValueLabel;

		@BindView(R.id.current_value)
		public TextView currentValue;

		@BindView(R.id.share_label)
		public TextView shareLabel;

		@BindView(R.id.share)
		public TextView share;

		@BindView(R.id.time_left_label)
		public TextView timeLeftLabel;

		@BindView(R.id.time_left)
		public PeriodLeftView timeLeft;

		@BindView(R.id.status)
		public TextView status;

		@BindView(R.id.label_reinvest)
		public TextView reinvestLabel;

		private DashboardProgramDetails program;

		ProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
//				if (investmentProgram != null) {
//					InvestmentProgramDashboardInvestor data = investmentProgram.getData();
//
//					ProgramInfoModel programInfoModel = new ProgramInfoModel(data.getId(),
//							data.getLogo(),
//							data.getTitle(),
//							data.getManager().getUsername(),
//							data.isIsFavorite());
//					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programInfoModel));
//				}
			});
		}

		@OnClick(R.id.group_profit)
		public void onProfitClicked() {

		}

		private void setFonts() {
			profitPercent.setTypeface(TypefaceUtil.semibold());
			reinvestLabel.setTypeface(TypefaceUtil.semibold());
		}

		void setProgram(DashboardProgramDetails program) {
			this.program = program;
			updateData();
		}

		private void updateData() {
			programLogo.setImage(program.getLogo(), 100, 100);
			programLogo.setLevel(program.getLevel());

			programName.setText(program.getTitle());
			managerName.setText(program.getManager().getUsername());
//			chart.setEquityChart(program.getChart());


//			tokens.setText(investmentProgram.getTokens());
//			tokensFiat.setText(investmentProgram.getTokensFiat());
//
//			profitShort.setText(investmentProgram.getProfitShort());
//			profitFull.setText(investmentProgram.getProfitFull());
//
//			if (data.isIsEnabled())
//				periodLeftView.setDateTo(data.getStartOfPeriod(), data.getEndOfPeriod());
//			periodLeftView.setProgramClosed(!data.isIsEnabled());
		}
	}
}
