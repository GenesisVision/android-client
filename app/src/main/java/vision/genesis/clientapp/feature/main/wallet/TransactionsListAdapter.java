package vision.genesis.clientapp.feature.main.wallet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.WalletTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ManagerAvatarView;

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
		@BindView(R.id.avatar)
		public ManagerAvatarView avatar;

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

		private WalletTransaction transaction;

		TransactionViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void setTransaction(WalletTransaction transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {

		}
	}
}
