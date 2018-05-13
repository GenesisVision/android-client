package vision.genesis.clientapp.feature.tournament;

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
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramDashboardInvestor;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.ProgramInfoModel;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PeriodLeftView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/05/2018.
 */

public class TournamentProgramsAdapter extends RecyclerView.Adapter<TournamentProgramsAdapter.InvestorProgramViewHolder>
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

	@Override
	public long getItemId(int position) {
		return investorPrograms.get(position).hashCode();
	}

	void setInvestorPrograms(List<InvestmentProgramDashboardInvestor> investorPrograms) {
		this.investorPrograms.clear();
		this.investorPrograms.addAll(investorPrograms);
		notifyDataSetChanged();
	}

	public void changeProgramIsFavorite(UUID programId, boolean isFavorite) {
		for (InvestmentProgramDashboardInvestor program : investorPrograms) {
			if (program.getId().equals(programId)) {
				program.isFavorite(isFavorite);
				notifyDataSetChanged();
				break;
			}
		}
	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public AvatarView programLogo;

		@BindView(R.id.icon_favorite)
		public ImageView favoriteIcon;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.tokens)
		public TextView tokens;

		@BindView(R.id.tokens_fiat)
		public TextView tokensFiat;

		@BindView(R.id.label_my_tokens)
		public TextView myTokensLabel;

		@BindView(R.id.group_profit_short)
		public ViewGroup profitShortGroup;

		@BindView(R.id.profit_short)
		public TextView profitShort;

		@BindView(R.id.profit_full)
		public TextView profitFull;

		@BindView(R.id.profit_currency)
		public TextView profitCurrency;

		@BindView(R.id.label_profit)
		public TextView profitLabel;

		@BindView(R.id.view_period_left)
		public PeriodLeftView periodLeftView;

		private boolean isProfitFull = false;

		private InvestmentProgramDashboardInvestor investmentProgram;

		InvestorProgramViewHolder(View itemView) {
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

		@OnClick(R.id.group_profit)
		public void onProfitClicked() {
			isProfitFull = !isProfitFull;
			setProfitVisibility();
		}

		private void setFonts() {
			programName.setTypeface(TypefaceUtil.bold());
			tokensFiat.setTypeface(TypefaceUtil.bold());
			myTokensLabel.setTypeface(TypefaceUtil.bold());
			profitCurrency.setTypeface(TypefaceUtil.bold());
			profitLabel.setTypeface(TypefaceUtil.bold());
		}

		void setInvestmentProgram(InvestmentProgramDashboardInvestor investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			programLogo.setImage(investmentProgram.getLogo(), 100, 100);
			programLogo.setLevel(investmentProgram.getLevel());

			favoriteIcon.setVisibility(investmentProgram.isIsFavorite() ? View.VISIBLE : View.GONE);

			programName.setText(investmentProgram.getTitle());
			managerName.setText(String.format(Locale.getDefault(), "%s %s",
					itemView.getContext().getResources().getString(R.string.by),
					investmentProgram.getManager().getUsername()));

			chart.setEquityChart(investmentProgram.getEquityChart());

			tokens.setText(StringFormatUtil.formatAmount(investmentProgram.getInvestedTokens(), 0,
					WalletManager.TOKENS_MAX_DECIMAL_POINT_DIGITS));
			double tokensFiatValue = investmentProgram.getInvestedTokens() * investmentProgram.getToken().getInitialPrice();
			tokensFiat.setText(String.format(Locale.getDefault(), "($%.2f)", tokensFiatValue));

			profitShort.setText(StringFormatUtil.formatAmount(investmentProgram.getProfitFromProgram(), 0, 2));
			profitFull.setText(StringFormatUtil.formatAmount(investmentProgram.getProfitFromProgram(), 2,
					StringFormatUtil.getCurrencyMaxFraction(WalletTransaction.CurrencyEnum.GVT.toString())));

			if (investmentProgram.isIsEnabled())
				periodLeftView.setDateTo(investmentProgram.getStartOfPeriod(), investmentProgram.getEndOfPeriod());
			periodLeftView.setProgramClosed(!investmentProgram.isIsEnabled());
		}

		private void setProfitVisibility() {
			profitShortGroup.setVisibility(!isProfitFull ? View.VISIBLE : View.GONE);
			profitFull.setVisibility(isProfitFull ? View.VISIBLE : View.GONE);
		}
	}
}
