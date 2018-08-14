package vision.genesis.clientapp.feature.main.portfolio_events.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.AvatarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class PortfolioEventsListAdapter extends RecyclerView.Adapter<PortfolioEventsListAdapter.PortfolioEventViewHolder>
{
	private List<DashboardPortfolioEvent> events = new ArrayList<>();

	@Override
	public PortfolioEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_portfolio_event, parent, false);
		return new PortfolioEventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(PortfolioEventViewHolder holder, int position) {
		if (events.get(position) != null)
			holder.setEvent(events.get(position));
	}

	@Override
	public int getItemCount() {
		return events.size();
	}

	@Override
	public long getItemId(int position) {
		return events.get(position) != null
				? events.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	void setEvents(List<DashboardPortfolioEvent> events) {
		this.events.clear();
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	static class PortfolioEventViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public AvatarView programLogo;

		private DashboardPortfolioEvent event;

		PortfolioEventViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				if (event != null) {
//					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent());
				}
			});
		}

		private void setFonts() {
//			programName.setTypeface(TypefaceUtil.bold());
		}

		void setEvent(DashboardPortfolioEvent investmentProgram) {
			this.event = event;
			updateData();
		}

		private void updateData() {

		}
	}
}
