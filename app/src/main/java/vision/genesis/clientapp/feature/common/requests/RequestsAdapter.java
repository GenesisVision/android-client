package vision.genesis.clientapp.feature.common.requests;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProgramRequest;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.OnCancelRequestClickedEvent;
import vision.genesis.clientapp.model.events.ShowInvestmentProgramDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/09/2018.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.InvestorProgramViewHolder>
{
	public List<ProgramRequest> requests = new ArrayList<>();

	@Override
	public InvestorProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_request, parent, false);
		return new InvestorProgramViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(InvestorProgramViewHolder holder, int position) {
		holder.setRequest(requests.get(position));
	}

	@Override
	public int getItemCount() {
		return requests.size();
	}

	public void setRequests(List<ProgramRequest> requests) {
		this.requests.clear();
		this.requests.addAll(requests);
		notifyDataSetChanged();
	}

	public void deleteRequest(int position) {
		requests.remove(position);
		notifyItemRemoved(position);
	}

	static class InvestorProgramViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.swipe_layout)
		public SwipeLayout swipeLayout;

		@BindView(R.id.subject)
		public SimpleDraweeView subject;

		@BindView(R.id.action)
		public SimpleDraweeView action;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.type)
		public TextView type;

		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.value)
		public TextView value;

		@BindView(R.id.button_cancel)
		public TextView cancelText;

		private ProgramRequest request;

		InvestorProgramViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			cancelText.setTypeface(TypefaceUtil.semibold());

			swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

			itemView.setOnClickListener(v -> {
				if (request != null) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(request.getProgramId(),
							request.getLogo(),
//							request.getColor(),
							"#00FF00",
							0,
							request.getTitle(),
							"",
							false);
					EventBus.getDefault().post(new ShowInvestmentProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		@OnClick(R.id.button_cancel)
		public void onCancelClicked() {
			EventBus.getDefault().post(new OnCancelRequestClickedEvent(request.getId()));
		}

		void setRequest(ProgramRequest request) {
			this.request = request;
			updateData();
		}

		private void updateData() {
			this.name.setText(request.getTitle());
			this.type.setText(request.getType().getValue());
			this.value.setText(String.format(Locale.getDefault(), "%s %s GVT", request.getValue() > 0 ? "+" : "",
					StringFormatUtil.formatCurrencyAmount(request.getValue(), CurrencyEnum.GVT.getValue())));
			this.date.setText(DateTimeUtil.formatRequestDate(request.getDate()));

			this.subject.setImageURI(ImageUtils.getImageUri(request.getLogo()));

			int actionResId = 0;
			switch (request.getType()) {
				case INVEST:
					actionResId = R.drawable.icon_arrow_green_down;
					break;
				case WITHDRAWAL:
					actionResId = R.drawable.icon_arrow_red_up;
					break;
			}
			this.action.getHierarchy().setPlaceholderImage(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, actionResId));
		}
	}
}
