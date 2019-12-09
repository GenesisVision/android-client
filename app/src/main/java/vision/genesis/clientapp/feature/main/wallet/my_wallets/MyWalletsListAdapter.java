package vision.genesis.clientapp.feature.main.wallet.my_wallets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.model.events.ShowSpecificWalletEvent;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class MyWalletsListAdapter extends RecyclerView.Adapter<MyWalletsListAdapter.MyWalletViewHolder>
{
	private List<WalletData> wallets = new ArrayList<>();

	@NonNull
	@Override
	public MyWalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wallet, parent, false);
		return new MyWalletViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull MyWalletViewHolder holder, int position) {
		holder.setWallet(wallets.get(position));
	}

	@Override
	public int getItemCount() {
		return wallets.size();
	}

	void setWallets(List<WalletData> wallets) {
		this.wallets.clear();
		this.wallets.addAll(wallets);
		notifyDataSetChanged();
	}

	static class MyWalletViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.icon)
		public SimpleDraweeView icon;

		@BindView(R.id.currency)
		public TextView currency;

		@BindView(R.id.value)
		public TextView value;

		@BindView(R.id.value_ccy)
		public TextView valueCcy;

		private WalletData wallet;

		MyWalletViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (wallet != null)
					EventBus.getDefault().post(new ShowSpecificWalletEvent(WalletModel.createFrom(wallet)));
			});
		}

		private void setFonts() {
			value.setTypeface(TypefaceUtil.semibold());
		}

		void setWallet(WalletData wallet) {
			this.wallet = wallet;
			icon.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
			currency.setText(wallet.getTitle());
			value.setText(String.format(Locale.getDefault(), "%s",
					StringFormatUtil.getValueString(wallet.getTotal(), wallet.getCurrency().getValue())));
//			valueCcy.setText(String.format(Locale.getDefault(), "%s",
//					StringFormatUtil.getValueString(wallet.getTotalCcy(), wallet.getCurrencyCcy().getValue())));
		}
	}
}
