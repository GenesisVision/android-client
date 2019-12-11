package vision.genesis.clientapp.feature.main.wallet.transactions;

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

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>
{
	public List<TransactionViewModel> transactions = new ArrayList<>();

	@NonNull
	@Override
	public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
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

	static class TransactionViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.logo_first)
		public SimpleDraweeView logoFirst;

		@BindView(R.id.logo_first_small)
		public SimpleDraweeView logoFirstSmall;

		@BindView(R.id.logo_second_small)
		public SimpleDraweeView logoSecondSmall;

		@BindView(R.id.group_logo_second)
		public ViewGroup groupLogoSecond;

		@BindView(R.id.description)
		public TextView description;

		@BindView(R.id.value_first)
		public TextView valueFirst;

		@BindView(R.id.value_second)
		public TextView valueSecond;

		@BindView(R.id.group_value_second)
		public ViewGroup groupValueSecond;

//		@BindView(R.id.status)
//		public TextView status;

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
			valueFirst.setTypeface(TypefaceUtil.semibold());
			valueSecond.setTypeface(TypefaceUtil.semibold());
//			status.setTypeface(TypefaceUtil.semibold());
		}

		void setTransaction(TransactionViewModel transaction) {
			this.transaction = transaction;
			updateData();
		}

		private void updateData() {
			AmountItem first = transaction.getAmount().getFirst();
			AmountItem second = transaction.getAmount().getSecond();

			description.setText(transaction.getDescription());

			logoFirst.setImageURI(ImageUtils.getImageUri(first.getLogo()));
			logoFirstSmall.setImageURI(ImageUtils.getImageUri(first.getLogo()));
			valueFirst.setText(StringFormatUtil.getValueString(first.getAmount(), first.getCurrency().getValue()));

			if (second == null) {
				logoFirst.setVisibility(View.VISIBLE);
				groupLogoSecond.setVisibility(View.GONE);
				groupValueSecond.setVisibility(View.GONE);

				valueFirst.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(),
						first.getAmount() >= 0
								? R.attr.colorGreen
								: R.attr.colorRed));
			}
			else {
				logoFirst.setVisibility(View.GONE);
				groupLogoSecond.setVisibility(View.VISIBLE);
				groupValueSecond.setVisibility(View.VISIBLE);

				valueFirst.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorTextPrimary));
				valueSecond.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorTextPrimary));

				logoSecondSmall.setImageURI(ImageUtils.getImageUri(second.getLogo()));
				valueSecond.setText(StringFormatUtil.getValueString(second.getAmount(), second.getCurrency().getValue()));
			}


			setStatus(transaction.getStatus());
		}

		private void setStatus(MultiWalletTransactionStatus status) {
			switch (status) {
				case DONE:
//					this.status.setText(itemView.getContext().getString(R.string.status_done));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_done));
					this.statusIcon.setVisibility(View.GONE);
					break;
				case PENDING:
//					this.status.setText(itemView.getContext().getString(R.string.status_pending));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_pending));
					this.statusIcon.setVisibility(View.VISIBLE);
					break;
				case CANCELED:
//					this.status.setText(itemView.getContext().getString(R.string.status_canceled));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_canceled));
					this.statusIcon.setVisibility(View.VISIBLE);
					break;
				case ERROR:
//					this.status.setText(itemView.getContext().getString(R.string.status_error));
					this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
							R.drawable.icon_status_canceled));
					this.statusIcon.setVisibility(View.VISIBLE);
					break;
			}
		}
	}
}
