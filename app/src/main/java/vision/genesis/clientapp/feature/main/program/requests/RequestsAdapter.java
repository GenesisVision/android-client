package vision.genesis.clientapp.feature.main.program.requests;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 3/1/18.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.InvestorProgramViewHolder>
{
//	public List<InvestmentProgramRequest> requests = new ArrayList<>();

	@Override
	public InvestorProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_request, parent, false);
		return new InvestorProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestorProgramViewHolder holder, int position) {
//		holder.setRequest(requests.get(position));
	}

	@Override
	public int getItemCount() {
//		return requests.size();
		return 0;
	}

//	void setRequests(List<InvestmentProgramRequest> requests) {
//		this.requests.clear();
//		this.requests.addAll(requests);
//		notifyDataSetChanged();
//	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.type)
		public TextView type;

		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.amount)
		public TextView amount;

		@BindView(R.id.currency)
		public TextView currency;

//		private InvestmentProgramRequest request;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		@OnClick(R.id.button_cancel)
		public void onCancelClicked() {
//			EventBus.getDefault().post(new OnCancelRequestClickedEvent(request.getId()));
		}

//		void setRequest(InvestmentProgramRequest request) {
//			this.request = request;
//			updateData();
//		}
//
//		private void updateData() {
//			type.setText(request.getType().toString());
//			date.setText(DateTimeUtil.formatDateTime(request.getDate()));
//			currency.setText(request.getType().getValue().equals(InvestmentProgramRequest.TypeEnum.INVEST.getValue())
//					? itemView.getContext().getString(R.string.gvt)
//					: itemView.getContext().getString(R.string.tokens));
//
//			setAmount();
//		}
//
//		private void setAmount() {
//			DecimalFormat df = new DecimalFormat("0.########");
//			df.setMinimumFractionDigits(2);
//			df.setRoundingMode(RoundingMode.DOWN);
//			String amountString = df.format(request.getAmount());
//
//			switch (request.getType()) {
//				case INVEST:
//					amount.setText(amountString);
//					amount.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionGreen));
//					break;
//				case WITHDRAWAL:
//					amount.setText(amountString);
//					amount.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionRed));
//					break;
//			}
//		}
	}
}
