package vision.genesis.clientapp.feature.main.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.NotificationViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

public class NotificationsListAdapter extends RecyclerView.Adapter<NotificationsListAdapter.NotificationViewHolder>
{
	private List<NotificationViewModel> notifications = new ArrayList<>();

	@Override
	public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notification, parent, false);
		return new NotificationViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(NotificationViewHolder holder, int position) {
		if (notifications.get(position) != null)
			holder.setNotification(notifications.get(position));
	}

	@Override
	public int getItemCount() {
		return notifications.size();
	}

	@Override
	public long getItemId(int position) {
		return notifications.get(position) != null
				? notifications.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	public void setNotifications(List<NotificationViewModel> notifications) {
		this.notifications.clear();
		this.notifications.addAll(notifications);
		notifyDataSetChanged();
	}

	public void addNotifications(List<NotificationViewModel> notifications) {
		this.notifications.addAll(notifications);
		notifyDataSetChanged();
	}

	static class NotificationViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.icon)
		public SimpleDraweeView icon;

		@BindView(R.id.not_read)
		public SimpleDraweeView notRead;

		@BindView(R.id.text)
		public TextView text;

		@BindView(R.id.time)
		public TextView time;

		@BindView(R.id.action)
		public TextView action;

		private NotificationViewModel notification;

		NotificationViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				switch (notification.getType()) {
					case PROGRAMNEWSANDUPDATES:
					case PROGRAMENDOFPERIOD:
					case PROGRAMCONDITION:
					case MANAGERNEWPROGRAM:
						if (notification.getAssetType() != null) {
							if (notification.getAssetType().equals(NotificationViewModel.AssetTypeEnum.PROGRAM)) {
								ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(notification.getAssetId(),
										notification.getLogo(),
										"#131e26",
										0,
										0.0,
										"",
										"",
										"",
										false,
										false);
								EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
							}
							else if (notification.getAssetType().equals(NotificationViewModel.AssetTypeEnum.PROGRAM)) {
								FundDetailsModel fundDetailsModel = new FundDetailsModel(notification.getAssetId(),
										notification.getLogo(),
										"#131e26",
										"",
										"",
										false,
										false);
								EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
							}
						}
						break;
				}
			});
		}

		private void setFonts() {
			time.setTypeface(TypefaceUtil.semibold());
			action.setTypeface(TypefaceUtil.semibold());
		}

		void setNotification(NotificationViewModel notification) {
			this.notification = notification;

//			notRead.setVisibility(notification.);
			text.setText(notification.getText());
			time.setText(DateTimeUtil.formatShortTime(notification.getDate()));

			int iconResId = R.drawable.icon_notification_star;
			String logo = null;
			switch (notification.getType()) {
				case PLATFORMNEWSANDUPDATES:
					break;
				case PLATFORMEMERGENCY:
					break;
				case PLATFORMOTHER:
					break;
				case PROFILEUPDATED:
					break;
				case PROFILEPWDUPDATED:
					break;
				case PROFILEVERIFICATION:
					iconResId = R.drawable.icon_notification_person;
					action.setText(itemView.getContext().getString(R.string.get_verified));
					break;
				case PROFILE2FA:
					break;
				case PROFILESECURITY:
					break;
				case PROGRAMNEWSANDUPDATES:
					logo = notification.getLogo();
					break;
				case PROGRAMENDOFPERIOD:
					logo = notification.getLogo();
					break;
				case PROGRAMCONDITION:
					logo = notification.getLogo();
					break;
				case MANAGERNEWPROGRAM:
					logo = notification.getLogo();
					break;
			}

			if (logo == null || logo.isEmpty()) {
				GenericDraweeHierarchy hierarchy = icon.getHierarchy();
				hierarchy.setBackgroundImage(AppCompatResources.getDrawable(itemView.getContext(), iconResId));
				icon.setHierarchy(hierarchy);
				icon.setImageURI("");
			}
			else {
				icon.setImageURI(ImageUtils.getImageUri(logo));
			}
		}
	}
}
