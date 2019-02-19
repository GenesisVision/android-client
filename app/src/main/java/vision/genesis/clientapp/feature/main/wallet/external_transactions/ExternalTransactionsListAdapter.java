package vision.genesis.clientapp.feature.main.wallet.external_transactions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.MultiWalletExternalTransaction;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class ExternalTransactionsListAdapter extends RecyclerView.Adapter<ExternalTransactionsListAdapter.TransactionViewHolder>
{
	public List<MultiWalletExternalTransaction> transactions = new ArrayList<>();

	@NonNull
	@Override
	public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_external_transaction, parent, false);
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

	void setTransactions(List<MultiWalletExternalTransaction> transactions) {
		this.transactions.clear();
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	void addTransactions(List<MultiWalletExternalTransaction> transactions) {
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	static class TransactionViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.logo)
		public SimpleDraweeView logo;

		@BindView(R.id.description)
		public TextView description;

		@BindView(R.id.value)
		public TextView value;

		@BindView(R.id.status)
		public TextView status;

		@BindView(R.id.icon_status)
		public ImageView statusIcon;

		private MultiWalletExternalTransaction transaction;

		TransactionViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			value.setTypeface(TypefaceUtil.semibold());
			status.setTypeface(TypefaceUtil.semibold());
		}

		void setTransaction(MultiWalletExternalTransaction transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {
			setType();
		}

		private void setType() {
			logo.setImageURI(ImageUtils.getImageUri(transaction.getLogo()));
			description.setText(transaction.getType().getValue());
			value.setText(String.format(Locale.getDefault(), "%s %s %s",
					transaction.getAmount() < 0 ? "-" : "+",
					StringFormatUtil.formatCurrencyAmount(Math.abs(transaction.getAmount()), transaction.getCurrency().getValue()), transaction.getCurrency().getValue()));
			value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					transaction.getAmount() >= 0
							? R.attr.colorGreen
							: R.attr.colorRed));

//			status.setText(String.format(Locale.getDefault(), "%s / %s", transaction.));
		}
	}
}
