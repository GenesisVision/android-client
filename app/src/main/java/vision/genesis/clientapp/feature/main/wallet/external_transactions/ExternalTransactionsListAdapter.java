package vision.genesis.clientapp.feature.main.wallet.external_transactions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.AmountItem;
import io.swagger.client.model.MultiWalletTransactionStatus;
import io.swagger.client.model.TransactionViewModel;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowTransactionDetailsEvent;
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
	public List<TransactionViewModel> transactions = new ArrayList<>();

	@NonNull
	@Override
	public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_external_transaction, parent, false);
		return new TransactionViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
		if (transactions.get(position) != null) {
			holder.setTransaction(transactions.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return transactions.size();
	}

	void setTransactions(List<TransactionViewModel> transactions) {
		this.transactions.clear();
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	void addTransactions(List<TransactionViewModel> transactions) {
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	public void setStatusCanceled(UUID transactionId) {
		for (TransactionViewModel transaction : transactions) {
			if (transaction.getId().equals(transactionId)) {
				transaction.setStatus(MultiWalletTransactionStatus.CANCELED);
				notifyItemChanged(transactions.indexOf(transaction));
				break;
			}
		}
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

		private TransactionViewModel transaction;

		TransactionViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (transaction != null) {
					EventBus.getDefault().post(new ShowTransactionDetailsEvent(transaction));
				}
			});
		}

		private void setFonts() {
			value.setTypeface(TypefaceUtil.semibold());
			status.setTypeface(TypefaceUtil.semibold());
		}

		void setTransaction(TransactionViewModel transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {
			setType();
		}

		private void setType() {
			AmountItem first = transaction.getAmount().getFirst();

			description.setText(transaction.getDescription());

			logo.setImageURI(ImageUtils.getImageUri(first.getLogo()));
			value.setText(StringFormatUtil.getValueString(first.getAmount(), first.getCurrency().getValue()));
			value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
					first.getAmount() >= 0
							? R.attr.colorGreen
							: R.attr.colorRed));

			setStatus(transaction.getStatus());
		}

		private void setStatus(MultiWalletTransactionStatus status) {
			switch (status) {
				case DONE:
					this.status.setText(itemView.getContext().getString(R.string.status_done));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_done));
					this.statusIcon.setVisibility(View.GONE);
					break;
				case PENDING:
					this.status.setText(itemView.getContext().getString(R.string.status_pending));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_pending));
					this.statusIcon.setVisibility(View.VISIBLE);
					break;
				case CANCELED:
					this.status.setText(itemView.getContext().getString(R.string.status_canceled));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_canceled));
					this.statusIcon.setVisibility(View.VISIBLE);
					break;
				case ERROR:
					this.status.setText(itemView.getContext().getString(R.string.status_error));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_canceled));
					this.statusIcon.setVisibility(View.VISIBLE);
					break;
			}
		}
	}
}
