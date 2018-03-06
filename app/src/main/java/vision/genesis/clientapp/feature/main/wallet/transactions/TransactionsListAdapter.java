package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>
{
	public List<WalletTransaction> transactions = new ArrayList<>();

	@Override
	public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
		return new TransactionViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(TransactionViewHolder holder, int position) {
		holder.setTransaction(transactions.get(position));
	}

	@Override
	public int getItemCount() {
		return transactions.size();
	}

	void setTransactions(List<WalletTransaction> transactions) {
		this.transactions.clear();
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	void addTransactions(List<WalletTransaction> investmentPrograms) {
		this.transactions.addAll(investmentPrograms);
		notifyDataSetChanged();
	}

	static class TransactionViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.type)
		public TextView type;

		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.amount)
		public TextView amount;

		private WalletTransaction transaction;

		private Context context;

		TransactionViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			context = itemView.getContext();
		}

		void setTransaction(WalletTransaction transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {
			setType();
			date.setText(DateTimeUtil.formatDateTime(transaction.getDate()));
			setAmount();
		}

		private void setType() {
			switch (transaction.getType()) {
				case DEPOSIT:
					type.setText(context.getResources().getString(R.string.transaction_type_deposit));
					break;
				case WITHDRAW:
					type.setText(context.getResources().getString(R.string.transaction_type_withdraw));
					break;
				case OPENPROGRAM:
					type.setText(context.getResources().getString(R.string.transaction_type_open_program));
					break;
				case INVESTTOPROGRAM:
					type.setText(context.getResources().getString(R.string.transaction_type_invest_to_program));
					break;
				case WITHDRAWFROMPROGRAM:
					type.setText(context.getResources().getString(R.string.transaction_type_withdraw_from_program));
					break;
				case PROFITFROMPROGRAM:
					type.setText(context.getResources().getString(R.string.profit_from_program));
					break;
				case CANCELINVESTMENTREQUEST:
					type.setText(context.getResources().getString(R.string.transaction_type_cancel_investment_request));
					break;
				case PARTIALINVESTMENTEXECUTIONREFUND:
					type.setText(context.getResources().getString(R.string.partial_investment_execution_refund));
					break;

			}
		}

		private void setAmount() {
			double amountValue = transaction.getAmount();
			DecimalFormat df = new DecimalFormat("0.########");
			df.setMinimumFractionDigits(2);
			df.setRoundingMode(RoundingMode.DOWN);
			String amountString = df.format(Math.abs(amountValue));

			switch (transaction.getType()) {
				case DEPOSIT:
					setPositiveAmount(amountString);
					break;
				case WITHDRAW:
					setNegativeAmount(amountString);
					break;
				case OPENPROGRAM:
					setNegativeAmount(amountString);
					break;
				case INVESTTOPROGRAM:
					setNegativeAmount(amountString);
					break;
				case WITHDRAWFROMPROGRAM:
					setPositiveAmount(amountString);
					break;
				case PROFITFROMPROGRAM:
					if (amountValue >= 0)
						setPositiveAmount(amountString);
					else
						setNegativeAmount(amountString);
					break;
				case CANCELINVESTMENTREQUEST:
					setPositiveAmount(amountString);
					break;
				case PARTIALINVESTMENTEXECUTIONREFUND:
					if (amountValue >= 0)
						setPositiveAmount(amountString);
					else
						setNegativeAmount(amountString);
					break;
			}
		}

		private void setPositiveAmount(String amountString) {
			amount.setText(String.format("+%s", amountString));
			amount.setTextColor(ContextCompat.getColor(context, R.color.transactionGreen));
		}

		private void setNegativeAmount(String amountString) {
			amount.setText(String.format("-%s", amountString));
			amount.setTextColor(ContextCompat.getColor(context, R.color.transactionRed));
		}
	}
}
