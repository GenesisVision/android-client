package vision.genesis.clientapp.feature.main.managers_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.PublicProfile;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ManagerDetailsModel;
import vision.genesis.clientapp.model.events.ShowManagerDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

public class ManagersListAdapter extends RecyclerView.Adapter<ManagersListAdapter.ManagerViewHolder>
{
	private List<PublicProfile> managers = new ArrayList<>();

	@NonNull
	@Override
	public ManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_manager, parent, false);
		return new ManagerViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ManagerViewHolder holder, int position) {
		holder.setManager(managers.get(position));
	}

	@Override
	public long getItemId(int position) {
		return managers.get(position).hashCode();
	}

	@Override
	public int getItemCount() {
		return managers.size();
	}

	public void setManagers(List<PublicProfile> funds) {
		this.managers.clear();
		this.managers.addAll(funds);
		notifyDataSetChanged();
	}

	public void addManagers(List<PublicProfile> funds) {
		this.managers.addAll(funds);
		notifyDataSetChanged();
	}

	static class ManagerViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.manager_avatar)
		public AvatarView managerAvatar;

		@BindView(R.id.manager_name)
		public TextView managerName;

		@BindView(R.id.manager_date)
		public TextView managerDate;

		private PublicProfile manager;

		ManagerViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (manager != null) {
					ManagerDetailsModel model = new ManagerDetailsModel(
							manager.getId(),
							manager.getAvatar(),
							manager.getUsername(),
							manager.getRegDate());
					EventBus.getDefault().post(new ShowManagerDetailsEvent(model));
				}
			});
		}

		private void setFonts() {
//			fundName.setTypeface(TypefaceUtil.semibold());
		}

		void setManager(PublicProfile manager) {
			this.manager = manager;
			updateData();
		}

		private void updateData() {
			managerAvatar.setImage(manager.getAvatar(), 100, 100);
			managerName.setText(manager.getUsername());
			managerDate.setText(DateTimeUtil.formatShortDate(manager.getRegDate()));
		}
	}
}
