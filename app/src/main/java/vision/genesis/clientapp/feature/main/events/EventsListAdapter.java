package vision.genesis.clientapp.feature.main.events;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.AssetDetails;
import io.swagger.client.model.InvestmentEventItemViewModel;
import io.swagger.client.model.InvestmentEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowEventDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/25/18.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.PortfolioEventViewHolder>
{
	private List<InvestmentEventViewModel> events = new ArrayList<>();

	@Override
	public PortfolioEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_portfolio_event, parent, false);
		return new PortfolioEventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(PortfolioEventViewHolder holder, int position) {
		if (events.get(position) != null) {
			holder.setEvent(events.get(position));
		}
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

	public void setEvents(List<InvestmentEventViewModel> events) {
		this.events.clear();
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	public void addEvents(List<InvestmentEventViewModel> events) {
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

		private InvestmentEventViewModel event;

		PortfolioEventViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				if (event != null) {
					EventBus.getDefault().post(new ShowEventDetailsEvent(event));
				}
			});
		}

		private void setFonts() {
			time.setTypeface(TypefaceUtil.semibold());
			value.setTypeface(TypefaceUtil.semibold());
		}

		void setEvent(InvestmentEventViewModel event) {
			this.event = event;

			AssetDetails details = event.getAssetDetails();
			if (details != null) {
				if (details.getLogo() == null || details.getLogo().isEmpty()) {
					GenericDraweeHierarchy hierarchy = subject.getHierarchy();
					hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(details.getColor())));
					subject.setHierarchy(hierarchy);
					subject.setImageURI("");
				}
				else {
					subject.setImageURI(ImageUtils.getImageUri(details.getLogo()));
				}
			}

			action.setImageURI(ImageUtils.getImageUri(event.getIcon()));
			text.setText(event.getTitle());
			time.setText(DateTimeUtil.formatShortTime(event.getDate()));

			value.setVisibility(View.VISIBLE);
			if (event.getExtendedInfo() != null && event.getExtendedInfo().size() > 0) {
				InvestmentEventItemViewModel info = event.getExtendedInfo().get(0);
				value.setText(StringFormatUtil.getValueString(info.getAmount(), info.getCurrency().getValue()));
			}
			else if (event.getAmount() != null) {
				value.setText(StringFormatUtil.getValueString(event.getAmount(), event.getCurrency().getValue()));
			}
			else {
				value.setVisibility(View.GONE);
			}

			int valueColorAttrId = R.attr.colorTextPrimary;
			switch (event.getChangeState()) {
				case INCREASED:
					valueColorAttrId = R.attr.colorGreen;
					break;
				case DECREASED:
					valueColorAttrId = R.attr.colorRed;
					break;
				case NOTCHANGED:
					break;
			}
			this.value.setTextColor(ThemeUtil.getColorByAttrId(itemView.getContext(), valueColorAttrId));
		}
	}
}
