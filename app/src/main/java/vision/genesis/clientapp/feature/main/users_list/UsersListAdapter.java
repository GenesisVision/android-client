package vision.genesis.clientapp.feature.main.users_list;

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
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder>
{
	private List<PublicProfile> users = new ArrayList<>();

	@NonNull
	@Override
	public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
		return new UserViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
		holder.setUser(users.get(position));
	}

	@Override
	public long getItemId(int position) {
		return users.get(position).hashCode();
	}

	@Override
	public int getItemCount() {
		return users.size();
	}

	public void setUsers(List<PublicProfile> users) {
		this.users.clear();
		this.users.addAll(users);
		notifyDataSetChanged();
	}

	public void addUsers(List<PublicProfile> users) {
		this.users.addAll(users);
		notifyDataSetChanged();
	}

	static class UserViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.avatar)
		public AvatarView avatar;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.date)
		public TextView registrationDate;

		private PublicProfile user;

		UserViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (user != null) {
					UserDetailsModel model = new UserDetailsModel(
							user.getId(),
							user.getLogoUrl(),
							user.getUsername(),
							user.getRegDate());
					EventBus.getDefault().post(new ShowUserDetailsEvent(model));
				}
			});
		}

		private void setFonts() {
//			fundName.setTypeface(TypefaceUtil.semibold());
		}

		void setUser(PublicProfile user) {
			this.user = user;
			updateData();
		}

		private void updateData() {
			avatar.setImage(user.getLogoUrl(), 100, 100);
			name.setText(user.getUsername());
			registrationDate.setText(DateTimeUtil.formatShortDate(user.getRegDate()));
		}
	}
}
