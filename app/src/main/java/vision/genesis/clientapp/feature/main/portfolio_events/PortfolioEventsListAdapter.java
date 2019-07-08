package vision.genesis.clientapp.feature.main.portfolio_events;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.DashboardPortfolioEvent;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.PortfolioEvent;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class PortfolioEventsListAdapter extends RecyclerView.Adapter<PortfolioEventsListAdapter.PortfolioEventViewHolder>
{
	private List<PortfolioEvent> events = new ArrayList<>();

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

	public void setEvents(List<PortfolioEvent> events) {
		this.events.clear();
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	public void addEvents(List<PortfolioEvent> events) {
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	static class PortfolioEventViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.subject)
		public SimpleDraweeView subject;

		@BindView(R.id.action)
		public SimpleDraweeView action;

		@BindView(R.id.text)
		public TextView text;

		@BindView(R.id.time)
		public TextView time;

		@BindView(R.id.value)
		public TextView value;

		private PortfolioEvent event;

		PortfolioEventViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				if (event != null) {
					if (event.getAssetType().equals(DashboardPortfolioEvent.AssetTypeEnum.PROGRAM)) {
						ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(event.getAssetId(),
								event.getLogoUrl(),
								event.getAssetColor(),
								0,
								0.0,
								event.getAssetName(),
								"",
								event.getProgramCurrency(),
								false,
								false);
						EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
					}
					else if (event.getAssetType().equals(DashboardPortfolioEvent.AssetTypeEnum.FUND)) {
						FundDetailsModel fundDetailsModel = new FundDetailsModel(event.getAssetId(),
								event.getLogoUrl(),
								event.getAssetColor(),
								event.getAssetName(),
								"",
								false,
								false);
						EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
					}
				}
			});
		}

		private void setFonts() {
			time.setTypeface(TypefaceUtil.semibold());
			value.setTypeface(TypefaceUtil.semibold());
		}

		void setEvent(PortfolioEvent event) {
			this.event = event;

			if (event.getLogoUrl() == null || event.getLogoUrl().isEmpty()) {
				GenericDraweeHierarchy hierarchy = subject.getHierarchy();
				hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(event.getAssetColor())));
				subject.setHierarchy(hierarchy);
				subject.setImageURI("");
			}
			else {
				subject.setImageURI(ImageUtils.getImageUri(event.getLogoUrl()));
			}

			action.getHierarchy().setPlaceholderImage(AppCompatResources.getDrawable(itemView.getContext(), event.getActionResId()));
			text.setText(event.getText());
			time.setText(event.getTime());
			value.setText(event.getValue());

			this.value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), event.getValueColorResId()));
		}
	}
}
