package vision.genesis.clientapp.feature.main.notifications.program;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.NotificationSettingViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnCustomNotificationSettingSetEnabledEvent;
import vision.genesis.clientapp.model.events.OnDeleteCustomNotificationSettingClickedEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/09/2018.
 */

public class CustomNotificationsListAdapter extends RecyclerView.Adapter<CustomNotificationsListAdapter.CustomNotificationSettingViewHolder>
{
	public List<NotificationSettingViewModel> settings = new ArrayList<>();

	@NonNull
	@Override
	public CustomNotificationSettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_custom_notification, parent, false);
		return new CustomNotificationSettingViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull CustomNotificationSettingViewHolder holder, int position) {
		holder.setSetting(settings.get(position));
	}

	@Override
	public int getItemCount() {
		return settings.size();
	}

	public void setSettings(List<NotificationSettingViewModel> requests) {
		this.settings.clear();
		this.settings.addAll(requests);
		notifyDataSetChanged();
	}

	public void deleteSetting(UUID settingId) {
		int position = 0;
		for (NotificationSettingViewModel setting : settings) {
			if (setting.getId().equals(settingId)) {
				settings.remove(position);
				notifyItemRemoved(position);
				break;
			}
			position++;
		}
	}

	public void setEnabled(UUID settingId, Boolean enabled) {
		int position = 0;
		for (NotificationSettingViewModel setting : settings) {
			if (setting.getId().equals(settingId)) {
				setting.setIsEnabled(enabled);
				notifyItemChanged(position);
				break;
			}
			position++;
		}
	}

	static class CustomNotificationSettingViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.swipe_layout)
		public SwipeLayout swipeLayout;

		@BindView(R.id.type)
		public TextView type;

		@BindView(R.id.value)
		public TextView value;

		@BindView(R.id.checkbox)
		public CheckBox checkBox;

		@BindView(R.id.button_delete)
		public TextView deleteText;

		private NotificationSettingViewModel setting;

		CustomNotificationSettingViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			deleteText.setTypeface(TypefaceUtil.semibold());

			swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
		}

		@OnClick(R.id.checkbox)
		public void onCheckBoxClicked() {
			EventBus.getDefault().post(new OnCustomNotificationSettingSetEnabledEvent(setting.getId(), !setting.isIsEnabled()));
		}

		@OnClick(R.id.button_delete)
		public void onDeleteClicked() {
			EventBus.getDefault().post(new OnDeleteCustomNotificationSettingClickedEvent(setting.getId()));
		}

		void setSetting(NotificationSettingViewModel setting) {
			this.setting = setting;
			updateData();
		}

		private void updateData() {
			if (setting != null) {
				switch (setting.getConditionType()) {
					case PROFIT:
						this.type.setText(itemView.getContext().getString(R.string.profit));
						this.value.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(setting.getConditionAmount(), 0, 4)));
						break;
					case LEVEL:
						this.type.setText(itemView.getContext().getString(R.string.level));
						this.value.setText(StringFormatUtil.formatAmount(setting.getConditionAmount(), 0, 0));
						break;
				}
				this.checkBox.setChecked(setting.isIsEnabled());
			}
		}
	}
}
