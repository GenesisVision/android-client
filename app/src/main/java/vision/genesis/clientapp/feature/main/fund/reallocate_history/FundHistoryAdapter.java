package vision.genesis.clientapp.feature.main.fund.reallocate_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.flexbox.FlexboxLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FundAssetPartWithIcon;
import io.swagger.client.model.FundHistoryEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowFundEventDetailsEvent;
import vision.genesis.clientapp.ui.AdditionalCountView;
import vision.genesis.clientapp.ui.FundAssetAllocationView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

public class FundHistoryAdapter extends RecyclerView.Adapter<FundHistoryAdapter.ReallocateHistoryViewHolder>
{
	private List<FundHistoryEventViewModel> history = new ArrayList<>();

	@NonNull
	@Override
	public ReallocateHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fund_history, parent, false);
		return new ReallocateHistoryViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ReallocateHistoryViewHolder holder, int position) {
		holder.setEvent(history.get(position));
	}

	@Override
	public int getItemCount() {
		return history.size();
	}

	void setHistory(List<FundHistoryEventViewModel> history) {
		this.history.clear();
		this.history.addAll(history);
		notifyDataSetChanged();
	}

	void addHistory(List<FundHistoryEventViewModel> history) {
		this.history.addAll(history);
		notifyDataSetChanged();
	}

	static class ReallocateHistoryViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.arrow)
		public View arrow;

		@BindView(R.id.action)
		public SimpleDraweeView action;

		@BindView(R.id.type)
		public TextView type;

		@BindView(R.id.description)
		public TextView description;

		@BindView(R.id.flex_box)
		public FlexboxLayout flexBox;

		@BindDimen(R.dimen.view_fund_asset_allocation_width)
		public int allocationViewWidth;

		@BindDimen(R.dimen.view_fund_asset_allocation_margin)
		public int allocationViewMargin;

		private FundHistoryEventViewModel event;

		ReallocateHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.setOnClickListener((view) -> {
				if (event != null && event.getTrades() != null && !event.getTrades().isEmpty()) {
					EventBus.getDefault().post(new ShowFundEventDetailsEvent(event));
				}
			});
		}

		private void setEvent(FundHistoryEventViewModel event) {
			this.event = event;

			this.action.setImageURI(ImageUtils.getImageUri(event.getLogoUrl()));
			setType(event);
			this.date.setText(DateTimeUtil.formatEventDateTime(event.getDate()));
			this.arrow.setVisibility(event.getTrades() != null && !event.getTrades().isEmpty() ? View.VISIBLE : View.INVISIBLE);

			if (event.getNewAssets() != null && !event.getNewAssets().isEmpty()) {
				this.flexBox.setVisibility(View.VISIBLE);
				this.description.setVisibility(View.GONE);

				int columns = itemView.getContext().getApplicationContext().getResources().getDisplayMetrics().widthPixels / (allocationViewWidth + allocationViewMargin);
				int rows = 1;

				int index = 0;
				flexBox.removeAllViews();
				for (FundAssetPartWithIcon part : event.getNewAssets()) {
					if (rows == 1 && index > columns - 1) {
						rows = 2;
					}
					if (rows == 2 && index > columns * rows - 2) {
						AdditionalCountView view = new AdditionalCountView(itemView.getContext());
						view.setAdditionalCount(event.getNewAssets().size() - index);
						flexBox.addView(view);
						break;
					}
					FundAssetAllocationView view = new FundAssetAllocationView(itemView.getContext());
					view.setAsset(part);
					flexBox.addView(view);
					FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
					lp.setMargins(0, 0, allocationViewMargin, allocationViewMargin);
					view.setLayoutParams(lp);
					index++;
				}
			}
			else {
				this.flexBox.setVisibility(View.GONE);
				this.description.setVisibility(View.VISIBLE);

				this.description.setText(event.getDescription());
			}
		}

		private void setType(FundHistoryEventViewModel event) {
			int stringResId = 0;
			if (event.getType() != null) {
				switch (event.getType()) {
					case CREATION:
						stringResId = R.string.creation;
						break;
					case INVESTMENT:
						stringResId = R.string.investment;
						break;
					case WITHDRAWAL:
						stringResId = R.string.withdrawal;
						break;
					case REBALANCE:
						stringResId = R.string.rebalance;
						break;
					case REALLOCATION:
						stringResId = R.string.reallocation;
						break;
					case CHALLENGEWINNER:
						stringResId = R.string.challenge_winner;
						break;
				}
			}
			if (stringResId != 0) {
				this.type.setText(itemView.getContext().getString(stringResId));
			}
		}
	}
}
