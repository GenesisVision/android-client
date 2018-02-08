package vision.genesis.clientapp.feature.tournament.participants;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ParticipantsListAdapter extends RecyclerView.Adapter<ParticipantsListAdapter.ParticipantViewHolder>
{
	public List<ParticipantViewModel> participants = new ArrayList<>();

	@Override
	public ParticipantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_participant, parent, false);
		return new ParticipantViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ParticipantViewHolder holder, int position) {
		holder.setParticipant(participants.get(position));
	}

	@Override
	public int getItemCount() {
		return participants.size();
	}

	void setParticipants(List<ParticipantViewModel> participants) {
		this.participants.clear();
		this.participants.addAll(participants);
		notifyDataSetChanged();
	}

	void addParticipants(List<ParticipantViewModel> participants) {
		this.participants.addAll(participants);
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

		ParticipantViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

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
			profitPercentText.setText(String.format(Locale.getDefault(), "%.2f%%", participant.getTotalProfitInPercent()));

			chart.setDataDouble(participant.getChart());
		}
	}
}
