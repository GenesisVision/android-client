package vision.genesis.clientapp.feature.main.notifications.settings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ProgramNotificationSettingList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowProgramNotificationsSettingsEvent;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/10/2018.
 */

public class ProgramsNotificationsListAdapter extends RecyclerView.Adapter<ProgramsNotificationsListAdapter.SettingsViewHolder>
{
	private List<ProgramNotificationSettingList> settings = new ArrayList<>();

	@Override
	public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_program_notifications_settings, parent, false);
		return new SettingsViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SettingsViewHolder holder, int position) {
		if (settings.get(position) != null)
			holder.setSettings(settings.get(position));
	}

	@Override
	public int getItemCount() {
		return settings.size();
	}

	@Override
	public long getItemId(int position) {
		return settings.get(position) != null
				? settings.get(position).hashCode()
				: RecyclerView.NO_ID;
	}

	public void setSettings(List<ProgramNotificationSettingList> settings) {
		this.settings.clear();
		this.settings.addAll(settings);
		notifyDataSetChanged();
	}

	static class SettingsViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.program_logo)
		public ProgramLogoView programLogo;

		@BindView(R.id.program_name)
		public TextView programName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.count)
		public TextView count;

		private ProgramNotificationSettingList settings;

		SettingsViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
			itemView.setOnClickListener(v -> {
				if (settings != null) {
					EventBus.getDefault().post(new ShowProgramNotificationsSettingsEvent(settings.getProgramId(), settings.getTitle()));
				}
			});
		}

		private void setFonts() {
			managerName.setTypeface(TypefaceUtil.semibold());
			count.setTypeface(TypefaceUtil.semibold());
		}

		void setSettings(ProgramNotificationSettingList settings) {
			this.settings = settings;

			programLogo.setImage(settings.getLogo(), "#ffffff", 50, 50);
			programLogo.setLevel(settings.getLevel());
			programName.setText(settings.getTitle());
//			managerName.setText(settings.getManagerName());
			count.setText(String.valueOf(settings.getSettingsGeneral().size() + settings.getSettingsCustom().size()));
		}
	}
}
