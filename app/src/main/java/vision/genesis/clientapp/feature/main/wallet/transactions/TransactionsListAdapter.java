package vision.genesis.clientapp.feature.main.wallet.transactions;

import android.support.annotation.NonNull;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.MultiWalletTransaction;
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

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>
{
	public List<MultiWalletTransaction> transactions = new ArrayList<>();

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

	void setTransactions(List<MultiWalletTransaction> transactions) {
		this.transactions.clear();
		this.transactions.addAll(transactions);
		notifyDataSetChanged();
	}

	void addTransactions(List<MultiWalletTransaction> transactions) {
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

		private MultiWalletTransaction transaction;

		TransactionViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (transaction != null)
					EventBus.getDefault().post(new ShowTransactionDetailsEvent(transaction.getId(), transaction.getType().getValue(), transaction.getDate()));
			});
		}

		private void setFonts() {
			value.setTypeface(TypefaceUtil.semibold());
			status.setTypeface(TypefaceUtil.semibold());
		}

		void setTransaction(MultiWalletTransaction transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {
			setType();
		}

		private void setType() {
			String logo = transaction.getLogoFrom();
			String currency = transaction.getCurrencyFrom().getValue();

			this.logo.setImageURI(ImageUtils.getImageUri(logo));
			description.setText(transaction.getDescription());

			if (transaction.getType().equals(MultiWalletTransaction.TypeEnum.CONVERTING)) {
				value.setText(String.format(Locale.getDefault(), "- %s %s",
						StringFormatUtil.formatCurrencyAmount(Math.abs(transaction.getAmount()), currency), currency));
				value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));
			}
			else {
				value.setText(String.format(Locale.getDefault(), "%s %s %s",
						transaction.getAmount() < 0 ? "-" : "+",
						StringFormatUtil.formatCurrencyAmount(Math.abs(transaction.getAmount()), currency), currency));
				value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						transaction.getAmount() >= 0
								? R.attr.colorGreen
								: R.attr.colorRed));
			}


			setStatus(transaction.getStatus());
		}

		private void setStatus(MultiWalletTransaction.StatusEnum status) {
			switch (status) {
				case DONE:
					this.status.setText(itemView.getContext().getString(R.string.status_done));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_done));
					break;
				case PENDING:
					this.status.setText(itemView.getContext().getString(R.string.status_pending));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_pending));
					break;
				case CANCELED:
					this.status.setText(itemView.getContext().getString(R.string.status_canceled));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_canceled));
					break;
				case ERROR:
					break;
			}
		}
	}
}
