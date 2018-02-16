package vision.genesis.clientapp.feature.tournament.participants;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ParticipantViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnParticipantItemListClicked;
import vision.genesis.clientapp.ui.ManagerAvatarView;
import vision.genesis.clientapp.ui.ProfitChartView;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

public class ParticipantsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private final int VIEW_ITEM = 1;

	private final int VIEW_PROG = 0;

	public List<ParticipantViewModel> participants = new ArrayList<>();

	@Override
	public int getItemViewType(int position) {
		return participants.get(position) != null ? VIEW_ITEM : VIEW_PROG;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecyclerView.ViewHolder vh;
		if (viewType == VIEW_ITEM) {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_participant, parent, false);
			vh = new ParticipantViewHolder(itemView);
		}
		else {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progress, parent, false);
			vh = new ProgressViewHolder(itemView);
		}
		return vh;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ParticipantViewHolder)
			((ParticipantViewHolder) holder).setParticipant(participants.get(position));
	}

	@Override
	public int getItemCount() {
		return participants.size();
	}

	void setParticipants(List<ParticipantViewModel> participants) {
		this.participants.clear();
		this.participants.addAll(participants);
		this.participants.add(null);
		notifyDataSetChanged();
	}

	void addParticipants(List<ParticipantViewModel> participants) {
		if (this.participants.size() > 0 && this.participants.get(this.participants.size() - 1) == null)
			this.participants.remove(this.participants.size() - 1);

		if (participants.size() > 0) {
			this.participants.addAll(participants);
			this.participants.add(null);
		}
		notifyDataSetChanged();
	}

	static class ParticipantViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.avatar)
		public ManagerAvatarView avatar;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.text_place_text)
		public TextView placeText;

		@BindView(R.id.text_trades_text)
		public TextView tradesText;

		@BindView(R.id.text_profit_text)
		public TextView profitText;

		@BindView(R.id.text_profit_percent_text)
		public TextView profitPercentText;

		@BindView(R.id.chart)
		public ProfitChartView chart;

		private ParticipantViewModel participant;

		private Context context;

		ParticipantViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			context = itemView.getContext();

			itemView.setOnClickListener(v -> EventBus.getDefault().post(new OnParticipantItemListClicked(participant)));
		}

		void setParticipant(ParticipantViewModel participant) {
			this.participant = participant;
			updateData();
		}

		private void updateData() {
			avatar.setImageUrl(participant.getAvatar());
			avatar.hideLevel();
			name.setText(participant.getName());

			placeText.setText(String.valueOf(participant.getPlace()));
			tradesText.setText(String.valueOf(participant.getOrdersCount()));
			profitText.setText(String.valueOf(participant.getTotalProfit()));

			double profitPercent = participant.getTotalProfitInPercent();
			profitPercentText.setText(String.format(Locale.getDefault(), "%.2f%%", profitPercent));
			if (profitPercent > 0)
				profitPercentText.setTextColor(ContextCompat.getColor(context, R.color.transactionGreen));
			else if (profitPercent < 0)
				profitPercentText.setTextColor(ContextCompat.getColor(context, R.color.transactionRed));
			else
				profitPercentText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

			chart.setDataDouble(participant.getChart());
		}
	}

	static class ProgressViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.progress_bar)
		public ProgressBar progressBar;

		ProgressViewHolder(View v) {
			super(v);

			ButterKnife.bind(this, itemView);
		}
	}
}
