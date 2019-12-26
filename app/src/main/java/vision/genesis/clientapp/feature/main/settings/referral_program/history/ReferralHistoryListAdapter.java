package vision.genesis.clientapp.feature.main.settings.referral_program.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.RewardDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class ReferralHistoryListAdapter extends RecyclerView.Adapter<ReferralHistoryListAdapter.EventViewHolder>
{
	private List<RewardDetails> events = new ArrayList<>();

	@NonNull
	@Override
	public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_referral_history, parent, false);
		return new EventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
		if (events.get(position) != null) {
			holder.setEvent(events.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return events.size();
	}

	public void setEvents(List<RewardDetails> events) {
		this.events.clear();
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	public void addEvents(List<RewardDetails> events) {
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	static class EventViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.amount)
		public TextView amount;

		EventViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			amount.setTypeface(TypefaceUtil.semibold());
		}

		void setEvent(RewardDetails event) {
			this.date.setText(DateTimeUtil.formatShortDateTime(event.getDate()));
			this.amount.setText(StringFormatUtil.getValueString(event.getAmount(), event.getCurrency().getValue()));
		}
	}
}
