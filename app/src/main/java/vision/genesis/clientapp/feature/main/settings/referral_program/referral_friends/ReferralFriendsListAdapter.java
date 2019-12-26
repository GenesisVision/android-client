package vision.genesis.clientapp.feature.main.settings.referral_program.referral_friends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ReferralFriend;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class ReferralFriendsListAdapter extends RecyclerView.Adapter<ReferralFriendsListAdapter.EventViewHolder>
{
	private List<ReferralFriend> referrals = new ArrayList<>();

	@NonNull
	@Override
	public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_referral_friend, parent, false);
		return new EventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
		if (referrals.get(position) != null) {
			holder.setEvent(referrals.get(position));
		}
	}

	@Override
	public int getItemCount() {
		return referrals.size();
	}

	public void setReferrals(List<ReferralFriend> referrals) {
		this.referrals.clear();
		this.referrals.addAll(referrals);
		notifyDataSetChanged();
	}

	public void addReferrals(List<ReferralFriend> referrals) {
		this.referrals.addAll(referrals);
		notifyDataSetChanged();
	}

	static class EventViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.date)
		public TextView date;

		@BindView(R.id.email)
		public TextView email;

		EventViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			setFonts();
		}

		private void setFonts() {
			email.setTypeface(TypefaceUtil.semibold());
		}

		void setEvent(ReferralFriend referral) {
			this.date.setText(DateTimeUtil.formatShortDateTime(referral.getDate()));
			this.email.setText(referral.getEmailMask());
		}
	}
}
