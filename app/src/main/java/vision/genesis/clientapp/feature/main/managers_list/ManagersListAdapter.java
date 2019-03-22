package vision.genesis.clientapp.feature.main.managers_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ManagerProfile;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2019.
 */

public class ManagersListAdapter extends RecyclerView.Adapter<ManagersListAdapter.ManagerViewHolder>
{
	private List<ManagerProfile> managers = new ArrayList<>();

	@NonNull
	@Override
	public ManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fund, parent, false);
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

	public void setManagers(List<ManagerProfile> funds) {
		this.managers.clear();
		this.managers.addAll(funds);
		notifyDataSetChanged();
	}

	public void addManagers(List<ManagerProfile> funds) {
		this.managers.addAll(funds);
		notifyDataSetChanged();
	}

	static class ManagerViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.manager_avatar)
		public SimpleDraweeView avatar;

		@BindView(R.id.manager_name)
		public TextView managerName;

		private ManagerProfile manager;

		ManagerViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (manager != null) {

				}
			});
		}

		private void setFonts() {
//			fundName.setTypeface(TypefaceUtil.semibold());
		}

		void setManager(ManagerProfile manager) {
			this.manager = manager;
			updateData();
		}

		private void updateData() {


		}
	}
}
