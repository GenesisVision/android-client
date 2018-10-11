package vision.genesis.clientapp.feature.main.wallet;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>
{
	public List<WalletTransaction> transactions = new ArrayList<>();

	@NonNull
	@Override
	public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
		return new TransactionViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
		if (transactions.get(position) != null)
			holder.setTransaction(transactions.get(position));
	}

	@Override
	public int getItemCount() {
		return transactions.size();
	}

	//
	void setTransactions(List<WalletTransaction> transactions) {
		this.transactions.clear();
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	void addTransactions(List<WalletTransaction> transactions) {
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	static class TransactionViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.action)
		public SimpleDraweeView action;

		@BindView(R.id.text)
		public TextView text;

		@BindView(R.id.time)
		public TextView time;

		@BindView(R.id.value)
		public TextView value;

		private WalletTransaction transaction;


		TransactionViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			time.setTypeface(TypefaceUtil.semibold());
			value.setTypeface(TypefaceUtil.semibold());
		}

		void setTransaction(WalletTransaction transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {
			setType();
		}

		private void setType() {
			Integer actionResId = R.drawable.icon_arrow_green_down;
			String text = "";

			switch (transaction.getAction()) {

//				case DEPOSIT:
//					type.setText(itemView.getContext().getResources().getString(R.string.transaction_type_deposit));
//					break;
//				case WITHDRAW:
//					type.setText(itemView.getContext().getResources().getString(R.string.transaction_type_withdraw));
//					break;
//				case OPENPROGRAM:
//					type.setText(itemView.getContext().getResources().getString(R.string.transaction_type_open_program));
//					break;
//				case INVESTTOPROGRAM:
//					type.setText(itemView.getContext().getResources().getString(R.string.transaction_type_invest_to_program));
//					break;
//				case WITHDRAWFROMPROGRAM:
//					type.setText(itemView.getContext().getResources().getString(R.string.transaction_type_withdraw_from_program));
//					break;
//				case PROFITFROMPROGRAM:
//					type.setText(itemView.getContext().getResources().getString(R.string.profit_from_program));
//					break;
//				case CANCELINVESTMENTREQUEST:
//					type.setText(itemView.getContext().getResources().getString(R.string.transaction_type_cancel_investment_request));
//					break;
//				case PARTIALINVESTMENTEXECUTIONREFUND:
//					type.setText(itemView.getContext().getResources().getString(R.string.partial_investment_execution_refund));
//					break;
//				case CLOSINGPROGRAMREFUND:
//					type.setText(itemView.getContext().getResources().getString(R.string.closing_program_refund));
//					break;

				case TRANSFER:
					if (transaction.getSourceType().equals(WalletTransaction.SourceTypeEnum.WALLET)) {
						text = itemView.getContext().getResources().getString(R.string.transaction_type_withdraw);
						actionResId = R.drawable.icon_arrow_red_up;
					}
					else if (transaction.getDestinationType().equals(WalletTransaction.DestinationTypeEnum.WALLET)) {
						text = itemView.getContext().getResources().getString(R.string.transaction_type_deposit);
						actionResId = R.drawable.icon_arrow_green_down;
					}
					break;
				case PROGRAMOPEN:
					text = itemView.getContext().getResources().getString(R.string.transaction_type_open_program);
					actionResId = R.drawable.icon_arrow_red_up;
					break;
				case PROGRAMPROFIT:
					text = itemView.getContext().getResources().getString(R.string.profit_from_program);
					actionResId = R.drawable.icon_arrow_green_down;
					break;
				case PROGRAMINVEST:
					text = itemView.getContext().getResources().getString(R.string.transaction_type_invest_to_program);
					actionResId = R.drawable.icon_arrow_red_up;
					break;
				case PROGRAMWITHDRAWAL:
					text = itemView.getContext().getResources().getString(R.string.transaction_type_withdraw_from_program);
					actionResId = R.drawable.icon_arrow_green_down;
					break;
				case PROGRAMREFUNDPARTIALEXECUTION:
					text = itemView.getContext().getResources().getString(R.string.partial_investment_execution_refund);
					actionResId = R.drawable.icon_arrow_green_down;
					break;
				case PROGRAMREFUNDCLOSE:
					text = itemView.getContext().getResources().getString(R.string.closing_program_refund);
					actionResId = R.drawable.icon_arrow_green_down;
					break;
				case PROGRAMREQUESTINVEST:
					actionResId = R.drawable.icon_arrow_red_up;
					break;
				case PROGRAMREQUESTWITHDRAWAL:
					actionResId = R.drawable.icon_arrow_green_down;
					break;
				case PROGRAMREQUESTCANCEL:
					text = itemView.getContext().getResources().getString(R.string.transaction_type_cancel_investment_request);
					actionResId = R.drawable.icon_arrow_green_down;
					break;
			}

			this.text.setText(text);
			this.action.getHierarchy().setPlaceholderImage(ContextCompat.getDrawable(itemView.getContext(), actionResId));

			this.time.setText(DateTimeUtil.formatShortTime(transaction.getDate()));
			this.value.setText(StringFormatUtil.getGvtValueString(transaction.getAmount()));
			this.value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					transaction.getAmount() >= 0
							? R.attr.colorGreen
							: R.attr.colorRed));
		}
//
//		private void setAmount() {
//			double amountValue = transaction.getAmount();
//			String amountString = StringFormatUtil.formatAmount(amountValue, 2, StringFormatUtil.getCurrencyMaxFraction(CurrencyEnum.GVT.toString()));
//
//			switch (transaction.getType()) {
//				case DEPOSIT:
//					setPositiveAmount(amountString);
//					break;
//				case WITHDRAW:
//					setNegativeAmount(amountString);
//					break;
//				case OPENPROGRAM:
//					setNegativeAmount(amountString);
//					break;
//				case INVESTTOPROGRAM:
//					setNegativeAmount(amountString);
//					break;
//				case WITHDRAWFROMPROGRAM:
//					setPositiveAmount(amountString);
//					break;
//				case PROFITFROMPROGRAM:
//					if (amountValue >= 0)
//						setPositiveAmount(amountString);
//					else
//						setNegativeAmount(amountString);
//					break;
//				case CANCELINVESTMENTREQUEST:
//					setPositiveAmount(amountString);
//					break;
//				case PARTIALINVESTMENTEXECUTIONREFUND:
//					if (amountValue >= 0)
//						setPositiveAmount(amountString);
//					else
//						setNegativeAmount(amountString);
//					break;
//				case CLOSINGPROGRAMREFUND:
//					setPositiveAmount(amountString);
//					break;
//			}
//		}
//
//		private void setPositiveAmount(String amountString) {
//			amount.setText(String.format("+%s", amountString));
//			amount.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionGreen));
//		}
//
//		private void setNegativeAmount(String amountString) {
//			amount.setText(String.format("-%s", amountString));
//			amount.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.transactionRed));
//		}
	}
}
