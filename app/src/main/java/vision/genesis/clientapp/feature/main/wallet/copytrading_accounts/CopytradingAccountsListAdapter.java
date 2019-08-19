package vision.genesis.clientapp.feature.main.wallet.copytrading_accounts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.CopyTradingAccountInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CopytradingAccountModel;
import vision.genesis.clientapp.model.events.ShowCopytradingAccountDetailsEvent;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

public class CopytradingAccountsListAdapter extends RecyclerView.Adapter<CopytradingAccountsListAdapter.CopytradingAccountViewHolder>
{
	private List<CopyTradingAccountInfo> accounts = new ArrayList<>();

	@NonNull
	@Override
	public CopytradingAccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_copytrading_account, parent, false);
		return new CopytradingAccountViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull CopytradingAccountViewHolder holder, int position) {
		holder.setAccount(accounts.get(position));
	}

	@Override
	public int getItemCount() {
		return accounts.size();
	}

	void setAccounts(List<CopyTradingAccountInfo> accounts) {
		this.accounts.clear();
		this.accounts.addAll(accounts);
		notifyDataSetChanged();
	}

	static class CopytradingAccountViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.icon)
		public SimpleDraweeView icon;

		@BindView(R.id.currency)
		public TextView currency;

		@BindView(R.id.value)
		public TextView value;

		private CopyTradingAccountInfo account;

		CopytradingAccountViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (account != null)
					EventBus.getDefault().post(new ShowCopytradingAccountDetailsEvent(CopytradingAccountModel.createFrom(account)));
			});
		}

		private void setFonts() {
			value.setTypeface(TypefaceUtil.semibold());
		}

		void setAccount(CopyTradingAccountInfo account) {
			this.account = account;
			icon.setImageURI(ImageUtils.getImageUri(account.getLogo()));
			currency.setText(account.getTitle());
			value.setText(String.format(Locale.getDefault(), "%s",
					StringFormatUtil.getValueString(account.getBalance(), account.getCurrency().getValue())));
		}
	}
}
