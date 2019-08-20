package vision.genesis.clientapp.feature.main.copytrading.trading_log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.SignalTradingEvent;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/08/2019.
 */

public class TradingLogListAdapter extends RecyclerView.Adapter<TradingLogListAdapter.EventViewHolder>
{
	private List<SignalTradingEvent> events = new ArrayList<>();

	@NonNull
	@Override
	public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trading_log_event, parent, false);
		return new EventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
		if (events.get(position) != null)
			holder.setEvent(events.get(position));
	}

	@Override
	public int getItemCount() {
		return events.size();
	}

	public void setEvents(List<SignalTradingEvent> events) {
		this.events.clear();
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	public void addEvents(List<SignalTradingEvent> events) {
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	static class EventViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.text)
		public TextView text;

		@BindView(R.id.time)
		public TextView time;

		private SignalTradingEvent event;

		EventViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			time.setTypeface(TypefaceUtil.semibold());
		}

		void setEvent(SignalTradingEvent event) {
			this.event = event;

			this.text.setText(event.getMessage());
			this.time.setText(DateTimeUtil.formatShortTime(event.getDate()));
		}
	}
}
