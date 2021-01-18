package vision.genesis.clientapp.feature.main.notifications;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.NotificationViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnNotificationClickedEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

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
		if (notifications.get(position) != null) {
			holder.setNotification(notifications.get(position));
		}
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
		private final View.OnClickListener clickListener;

		@BindView(R.id.icon)
		public SimpleDraweeView icon;

		@BindView(R.id.not_read)
		public SimpleDraweeView notRead;

		@BindView(R.id.text)
		public TextView text;

		@BindView(R.id.time)
		public TextView time;

		@BindView(R.id.arrow)
		public View arrow;

		private NotificationViewModel notification;

		private TypedValue selectableBackground = new TypedValue();

		NotificationViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			itemView.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, selectableBackground, true);

			clickListener = view -> {
				if (notification.getLocation() != null && notification.getLocation().getLocation() != null) {
					EventBus.getDefault().post(new OnNotificationClickedEvent(notification.getLocation()));
				}
			};
		}

		void setNotification(NotificationViewModel notification) {
			this.notification = notification;

			text.setText(notification.getText());
			time.setText(DateTimeUtil.formatShortTime(notification.getDate()));

			int iconResId = R.drawable.icon_notification_star;
			String logo = notification.getImageUrl();

			if (logo == null || logo.isEmpty()) {
				GenericDraweeHierarchy hierarchy = icon.getHierarchy();
				hierarchy.setBackgroundImage(AppCompatResources.getDrawable(itemView.getContext(), iconResId));
				icon.setHierarchy(hierarchy);
				icon.setImageURI("");
			}
			else {
				icon.setImageURI(ImageUtils.getImageUri(logo));
			}

			if (notification.getLocation() != null && notification.getLocation().getLocation() != null) {
				this.arrow.setVisibility(View.VISIBLE);
				itemView.setOnClickListener(clickListener);
				itemView.setBackgroundResource(selectableBackground.resourceId);
			}
			else {
				this.arrow.setVisibility(View.GONE);
				itemView.setOnClickListener(null);
				itemView.setBackground(null);
			}

		}
	}
}
