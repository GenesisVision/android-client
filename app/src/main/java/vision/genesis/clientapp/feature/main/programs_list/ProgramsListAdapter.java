package vision.genesis.clientapp.feature.main.programs_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgram;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ProgramInfoModel;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.ProgramDataView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class ProgramsListAdapter extends RecyclerView.Adapter<ProgramsListAdapter.InvestmentProgramViewHolder>
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
	}

	@Override
	public long getItemId(int position) {
		return investmentPrograms.get(position).hashCode();
	}

	@Override
	public int getItemCount() {
		return investmentPrograms.size();
	}

	public void setInvestmentPrograms(List<InvestmentProgram> investmentPrograms) {
		this.investmentPrograms.clear();
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	void addInvestmentPrograms(List<InvestmentProgram> investmentPrograms) {
		this.investmentPrograms.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		for (InvestmentProgram program : investmentPrograms) {
			if (program.getId().equals(programId)) {
				program.isFavorite(isFavorite);
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
		@BindView(R.id.avatar)
		public AvatarView avatar;

		@BindView(R.id.icon_favorite)
		public ImageView favoriteIcon;

		@BindView(R.id.title)
		public TextView title;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.view_program_data)
		public ProgramDataView programDataView;

		@BindView(R.id.text_no_available_tokens)
		public TextView noAvailableTokensText;

		private InvestmentProgram investmentProgram;

		InvestmentProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				if (investmentProgram != null) {
					ProgramInfoModel programInfoModel = new ProgramInfoModel(investmentProgram.getId(),
							investmentProgram.getLogo(),
							investmentProgram.getTitle(),
							investmentProgram.getManager().getUsername(),
							investmentProgram.isIsFavorite());
					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programInfoModel));
				}
			});
		}

		private void setFonts() {
			title.setTypeface(TypefaceUtil.bold());
			noAvailableTokensText.setTypeface(TypefaceUtil.bold());
		}

		void setInvestmentProgram(InvestmentProgram investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			avatar.setImage(investmentProgram.getLogo(), 100, 100);
			avatar.setLevel(investmentProgram.getLevel());

			favoriteIcon.setVisibility(investmentProgram.isIsFavorite() ? View.VISIBLE : View.GONE);

			title.setText(investmentProgram.getTitle());
			managerName.setText(String.format(Locale.getDefault(), "%s %s",
					GenesisVisionApplication.INSTANCE.getResources().getString(R.string.by),
					investmentProgram.getManager().getUsername()));

			programDataView.setData(investmentProgram.getProfitTotal(),
					investmentProgram.getProfitAvgPercent(),
					investmentProgram.getBalance(),
					investmentProgram.getInvestorsCount(),
					investmentProgram.getCurrency().toString());

			chart.setEquityChart(investmentProgram.getEquityChart());

			noAvailableTokensText.setVisibility(investmentProgram.getAvailableInvestment() > 0
					? View.GONE
					: View.VISIBLE);
		}
	}
}
