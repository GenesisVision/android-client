package vision.genesis.clientapp.feature.main.fund.reallocate_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.FundAssetPartWithIcon;
import io.swagger.client.model.ReallocationModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowReallocationDetailsEvent;
import vision.genesis.clientapp.ui.AdditionalCountView;
import vision.genesis.clientapp.ui.FundAssetAllocationView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

public class ReallocateHistoryAdapter extends RecyclerView.Adapter<ReallocateHistoryAdapter.ReallocateHistoryViewHolder>
{
	private List<ReallocationModel> reallocates = new ArrayList<>();

	@NonNull
	@Override
	public ReallocateHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reallocate_history, parent, false);
		return new ReallocateHistoryViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ReallocateHistoryViewHolder holder, int position) {
		holder.setReallocation(reallocates.get(position));
	}

	@Override
	public int getItemCount() {
		return reallocates.size();
	}

	void setReallocates(List<ReallocationModel> periods) {
		this.reallocates.clear();
		this.reallocates.addAll(periods);
		notifyDataSetChanged();
	}

	void addReallocates(List<ReallocationModel> periods) {
		this.reallocates.addAll(periods);
		notifyDataSetChanged();
	}

	static class ReallocateHistoryViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date)
		public TextView date;

//		@BindView(R.id.group_top)
//		public LinearLayout groupTop;
//
//		@BindView(R.id.group_bottom)
//		public LinearLayout groupBottom;

		@BindView(R.id.flex_box)
		public FlexboxLayout flexBox;

		@BindDimen(R.dimen.view_fund_asset_allocation_width)
		public int allocationViewWidth;

		@BindDimen(R.dimen.view_fund_asset_allocation_margin)
		public int allocationViewMargin;

		private ReallocationModel reallocation;

		ReallocateHistoryViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener((view) -> EventBus.getDefault().post(new ShowReallocationDetailsEvent(reallocation)));
		}

		private void setFonts() {
			date.setTypeface(TypefaceUtil.semibold());
		}

		private void setReallocation(ReallocationModel reallocation) {
			this.reallocation = reallocation;

			date.setText(DateTimeUtil.formatEventDateTime(reallocation.getDate()));

			int columns = itemView.getContext().getApplicationContext().getResources().getDisplayMetrics().widthPixels / (allocationViewWidth + allocationViewMargin);
			int rows = 1;

			int index = 0;
			flexBox.removeAllViews();
			for (FundAssetPartWithIcon part : reallocation.getParts()) {
				if (rows == 1 && index > columns - 1) {
					rows = 2;
				}
				if (rows == 2 && index > columns * rows - 2) {
					AdditionalCountView view = new AdditionalCountView(itemView.getContext());
					view.setAdditionalCount(reallocation.getParts().size() - index);
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
	}
}
