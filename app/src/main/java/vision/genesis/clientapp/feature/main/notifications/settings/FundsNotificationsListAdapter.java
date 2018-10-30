package vision.genesis.clientapp.feature.main.notifications.settings;

import android.support.annotation.NonNull;
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
import io.swagger.client.model.FundNotificationSettingList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowFundNotificationsSettingsEvent;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/10/2018.
 */

public class FundsNotificationsListAdapter extends RecyclerView.Adapter<FundsNotificationsListAdapter.SettingsViewHolder>
{
	private List<FundNotificationSettingList> settings = new ArrayList<>();

	@NonNull
	@Override
	public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fund_notifications_settings, parent, false);
		return new SettingsViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
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

	public void setSettings(List<FundNotificationSettingList> settings) {
		this.settings.clear();
		this.settings.addAll(settings);
		notifyDataSetChanged();
	}

	static class SettingsViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.fund_logo)
		public ProgramLogoView fundLogo;

		@BindView(R.id.fund_name)
		public TextView fundName;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.count)
		public TextView count;

		private FundNotificationSettingList settings;

		SettingsViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			fundLogo.hideLevel();

			setFonts();
			itemView.setOnClickListener(v -> {
				if (settings != null) {
					EventBus.getDefault().post(new ShowFundNotificationsSettingsEvent(settings.getAssetId(), settings.getTitle()));
				}
			});
		}

		private void setFonts() {
			managerName.setTypeface(TypefaceUtil.semibold());
			count.setTypeface(TypefaceUtil.semibold());
		}

		void setSettings(FundNotificationSettingList settings) {
			this.settings = settings;

			fundLogo.setImage(settings.getLogo(), "#ffffff", 50, 50);
			fundName.setText(settings.getTitle());
//			managerName.setText(settings.getManagerName());
			count.setText(String.valueOf(settings.getSettingsGeneral().size()));
		}
	}
}
