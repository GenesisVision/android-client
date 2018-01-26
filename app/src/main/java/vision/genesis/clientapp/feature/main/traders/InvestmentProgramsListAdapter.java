package vision.genesis.clientapp.feature.main.traders;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgram;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnTraderItemListClicked;

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
//		notifyDataSetChanged();
	}

	static class InvestmentProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.avatar)
		public SimpleDraweeView avatar;

		@BindView(R.id.level)
		public TextView level;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.currency)
		public TextView currency;

		@BindView(R.id.text_deposit_text)
		public TextView depositText;

		@BindView(R.id.text_trades_text)
		public TextView tradesText;

		@BindView(R.id.text_period_text)
		public TextView periodText;

		@BindView(R.id.text_profit_text)
		public TextView profitText;

		private InvestmentProgram investmentProgram;

		InvestmentProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener(v -> new Handler().postDelayed(() -> EventBus.getDefault().post(new OnTraderItemListClicked(investmentProgram)), 300));
		}

		void setInvestmentProgram(InvestmentProgram investmentProgram) {
			this.investmentProgram = investmentProgram;
			updateData();
		}

		private void updateData() {
			avatar.setImageURI(investmentProgram.getInvestment().getLogo());
			level.setText(String.format(Locale.getDefault(), "%.0f", investmentProgram.getInvestment().getRating()));
			managerName.setText(investmentProgram.getAccount().getLogin());
			currency.setText(investmentProgram.getAccount().getCurrency());

//			depositText.setText(investmentProgram.getInvestment().);
			tradesText.setText(String.valueOf(investmentProgram.getInvestment().getOrdersCount()));
			periodText.setText(String.valueOf(investmentProgram.getInvestment().getPeriod()));
			profitText.setText(String.format(Locale.getDefault(), "%.2f%%", investmentProgram.getInvestment().getTotalProfit()));
		}
	}
}
