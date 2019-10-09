package vision.genesis.clientapp.feature.common.requests;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
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
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.OnCancelRequestClickedEvent;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/09/2018.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder>
{
	public List<ProgramRequest> requests = new ArrayList<>();

	@Override
	public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_request, parent, false);
		return new RequestViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(RequestViewHolder holder, int position) {
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

	static class RequestViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.swipe_layout)
		public SwipeLayout swipeLayout;

		@BindView(R.id.surface_view)
		public ViewGroup surfaceView;

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

		RequestViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			cancelText.setTypeface(TypefaceUtil.semibold());
			value.setTypeface(TypefaceUtil.semibold());

			swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

			surfaceView.setOnClickListener(v -> {
				if (request != null) {
					if (request.getProgramType().equals(ProgramRequest.ProgramTypeEnum.PROGRAM)) {
						ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(request.getProgramId(),
								request.getLogo(),
								request.getColor(),
								0,
								0.0,
								request.getTitle(),
								"",
								request.getCurrency().getValue(),
								false,
								false);
						EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
					}
					else if (request.getProgramType().equals(ProgramRequest.ProgramTypeEnum.FUND)) {
						FundDetailsModel fundDetailsModel = new FundDetailsModel(request.getProgramId(),
								request.getLogo(),
								request.getColor(),
								request.getTitle(),
								"",
								false,
								false);
						EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
					}
				}
			});
		}

		@OnClick(R.id.button_cancel)
		public void onCancelClicked() {
			if (request.isCanCancelRequest()) {
				EventBus.getDefault().post(new OnCancelRequestClickedEvent(request.getId()));
			}
		}

		void setRequest(ProgramRequest request) {
			this.request = request;
			updateData();
		}

		private void updateData() {
			this.name.setText(request.getTitle());
			this.type.setText(request.getType().getValue());
			if (request.getFundWithdrawPercent() != null) {
				this.value.setText(String.format(Locale.getDefault(), "%s%% (≈ %s GVT)", StringFormatUtil.formatAmount(request.getFundWithdrawPercent(), 0, 2),
						StringFormatUtil.formatCurrencyAmount(request.getValue(), CurrencyEnum.GVT.getValue())));
			}
			else if (request.getType().equals(ProgramRequest.TypeEnum.WITHDRAWAL) && request.isWithdrawAll()) {
				this.value.setText(itemView.getContext().getString(R.string.withdraw_all));
			}
			else {
				this.value.setText(String.format(Locale.getDefault(), "%s %s %s", request.getType().equals(ProgramRequest.TypeEnum.INVEST) ? "+" : "-",
						StringFormatUtil.formatCurrencyAmount(request.getValue(), request.getCurrency().getValue()), request.getCurrency().getValue()));
			}
			this.date.setText(DateTimeUtil.formatRequestDate(request.getDate()));

			if (request.getLogo() == null || request.getLogo().isEmpty()) {
				GenericDraweeHierarchy hierarchy = subject.getHierarchy();
				hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(request.getColor())));
				subject.setHierarchy(hierarchy);
				subject.setImageURI("");
			}
			else {
				subject.setImageURI(ImageUtils.getImageUri(request.getLogo()));
			}

			int actionResId = 0;
			switch (request.getType()) {
				case INVEST:
					actionResId = R.drawable.icon_arrow_white_left;
					break;
				case WITHDRAWAL:
					actionResId = R.drawable.icon_arrow_white_right;
					break;
			}
			this.action.getHierarchy().setPlaceholderImage(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, actionResId));

			swipeLayout.setSwipeEnabled(request.isCanCancelRequest());
		}
	}
}
