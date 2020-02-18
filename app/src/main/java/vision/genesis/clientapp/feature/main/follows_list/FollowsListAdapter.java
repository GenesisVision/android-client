package vision.genesis.clientapp.feature.main.follows_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.FollowDetailsListItem;
import io.swagger.client.model.Tag;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.facet.FollowFacetView;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.OnListFollowFavoriteClickedEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.ui.TagView;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/11/2019.
 */

public class FollowsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private static final int TYPE_FACET = 0;

	private static final int TYPE_CARD = 1;

	private List<FollowDetailsListItem> follows = new ArrayList<>();

	private List<AssetFacet> facets = new ArrayList<>();

	@Override
	public int getItemViewType(int position) {
		return position == 0 ? TYPE_FACET : TYPE_CARD;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		switch (viewType) {
			case TYPE_FACET:
				return new FacetsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_facets, parent, false));
			case TYPE_CARD:
			default:
				return new FollowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_follow, parent, false));
		}
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		switch (holder.getItemViewType()) {
			case TYPE_FACET:
				((FacetsViewHolder) holder).setFacets(facets);
				break;
			case TYPE_CARD:
				((FollowViewHolder) holder).setFollow(follows.get(position - 1));
				break;
		}
	}

	@Override
	public long getItemId(int position) {
		return position == 0 ? 0 : follows.get(position - 1).hashCode();
	}

	@Override
	public int getItemCount() {
		return follows.size() + 1;
	}

	void setFacets(List<AssetFacet> facets) {
		this.facets = facets;
		notifyDataSetChanged();
	}

	void setFollows(List<FollowDetailsListItem> follows) {
		this.follows.clear();
		this.follows.addAll(follows);
		notifyDataSetChanged();
	}

	void addFollows(List<FollowDetailsListItem> follows) {
		this.follows.addAll(follows);
		notifyDataSetChanged();
	}

	void changeFollowIsFavorite(UUID programId, boolean isFavorite) {
		for (FollowDetailsListItem follow : follows) {
			if (follow.getId().equals(programId)) {
				if (follow.getPersonalDetails() != null && !follow.getPersonalDetails().isIsFavorite().equals(isFavorite)) {
					follow.getPersonalDetails().setIsFavorite(isFavorite);
					notifyItemChanged(follows.indexOf(follow));
				}
				break;
			}
		}
	}

	public void removeFolllow(UUID followId) {
		for (int i = 0; i < follows.size(); i++) {
			if (follows.get(i).getId().equals(followId)) {
				follows.remove(i);
				notifyItemRemoved(i);
				break;
			}
		}
	}

	static class FacetsViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.group_facets)
		public ViewGroup facetsGroup;

		private List<AssetFacet> facets;

		FacetsViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		void setFacets(List<AssetFacet> facets) {
			this.facets = facets;
			updateView();
		}

		private void updateView() {
			facetsGroup.removeAllViews();
			for (AssetFacet facet : facets) {
				FollowFacetView view = new FollowFacetView(itemView.getContext());
				view.setData(facet);
				facetsGroup.addView(view);
			}
		}
	}

	static class FollowViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.follow_logo)
		public ProgramLogoView followLogo;

		@BindView(R.id.follow_name)
		public TextView followName;

		@BindView(R.id.owner_name)
		public TextView ownerName;

		@BindView(R.id.favorite)
		public ImageView favorite;

		@BindView(R.id.chart)
		public ProfitSmallChartView chart;

		@BindView(R.id.profit_percent)
		public TextView profitPercent;

		@BindView(R.id.trades)
		public TextView trades;

		@BindView(R.id.trades_label)
		public TextView tradesLabel;

		@BindView(R.id.subscribers)
		public TextView subscribers;

		@BindView(R.id.subscribers_label)
		public TextView subscribersLabel;

		@BindView(R.id.drawdown)
		public TextView drawdown;

		@BindView(R.id.drawdown_label)
		public TextView drawdownLabel;

		@BindView(R.id.tag_1)
		public TagView tag1;

		@BindView(R.id.tag_2)
		public TagView tag2;

		@BindView(R.id.tags_left)
		public TagView tagsLeft;

		private FollowDetailsListItem follow;

		FollowViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			followLogo.setLevelBackground(R.attr.colorCard);

			setFonts();

			itemView.setOnClickListener(v -> {
				if (follow != null) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(follow.getId(),
							follow.getLogo(),
							follow.getColor(),
							0,
							0.0,
							follow.getTitle(),
							follow.getOwner().getUsername(),
							follow.getCurrency().getValue(),
							follow.getPersonalDetails() != null ?
									follow.getPersonalDetails().isIsFavorite()
									: false,
//TODO:
//							program.getPersonalDetails() != null ?
//									program.getPersonalDetails().isHasNotifications()
//									: false);
							false,
							AssetType.FOLLOW);
					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				}
			});
		}

		@OnClick(R.id.favorite)
		public void onFavoriteClicked() {
			if (follow != null && follow.getPersonalDetails() != null) {
				follow.getPersonalDetails().setIsFavorite(!follow.getPersonalDetails().isIsFavorite());
				updateData();
				EventBus.getDefault().post(new OnListFollowFavoriteClickedEvent(follow.getId(), follow.getPersonalDetails().isIsFavorite()));
			}
		}

		private void setFonts() {
			followName.setTypeface(TypefaceUtil.semibold());
			ownerName.setTypeface(TypefaceUtil.medium());
			profitPercent.setTypeface(TypefaceUtil.semibold());
			subscribers.setTypeface(TypefaceUtil.semibold());
			trades.setTypeface(TypefaceUtil.semibold());
			drawdown.setTypeface(TypefaceUtil.semibold());

			subscribersLabel.setText(subscribersLabel.getText().toString().toLowerCase());
			tradesLabel.setText(tradesLabel.getText().toString().toLowerCase());
			drawdownLabel.setText(drawdownLabel.getText().toString().toLowerCase());
		}

		void setFollow(FollowDetailsListItem follow) {
			this.follow = follow;
			updateData();
		}

		private void updateData() {
			followLogo.setImage(follow.getLogo(), follow.getColor(), 100, 100);
			followLogo.hideLevel();

			if (follow.getPersonalDetails() != null) {
				favorite.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, follow.getPersonalDetails().isIsFavorite()
						? R.drawable.icon_favorite_fill
						: R.drawable.icon_favorite));
				favorite.setAlpha(follow.getPersonalDetails().isIsFavorite() ? 1f : 0.3f);
			}
			favorite.setVisibility(follow.getPersonalDetails() != null ? View.VISIBLE : View.INVISIBLE);

			this.followName.setText(follow.getTitle());
			this.ownerName.setText(follow.getOwner().getUsername());

			if (follow.getStatistic() != null) {
				this.chart.setChart(follow.getStatistic().getChart());
			}

			Double profitPercent = follow.getStatistic().getProfit();

			this.profitPercent.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(profitPercent, 0, 2)));
			this.profitPercent.setTextColor(profitPercent >= 0
					? ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorGreen)
					: ThemeUtil.getColorByAttrId(itemView.getContext(), R.attr.colorRed));

			this.trades.setText(String.valueOf(follow.getTradesCount()));
			this.subscribers.setText(String.valueOf(follow.getSubscribersCount()));
			this.drawdown.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(follow.getStatistic().getDrawdown(), 0, 2)));

			updateTags(follow.getTags());
		}

		private void updateTags(List<Tag> tags) {
			if (tags != null) {
				showTagMaybe(tag1, tags, 0);
				showTagMaybe(tag2, tags, 1);
				if (tags.size() > 2) {
					Tag tagLeft = new Tag();
					tagLeft.setName(String.format(Locale.getDefault(), "+%d", tags.size() - 2));
					tagLeft.setColor("#787d82");
					tagsLeft.setTag(tagLeft);
					tagsLeft.setVisibility(View.VISIBLE);
				}
				else {
					tagsLeft.setVisibility(View.GONE);
				}
			}
			else {
				tag1.setVisibility(View.GONE);
				tag2.setVisibility(View.GONE);
				tagsLeft.setVisibility(View.GONE);
			}
		}

		private void showTagMaybe(TagView tagView, List<Tag> tags, Integer position) {
			if (tags != null && tags.size() > position) {
				tagView.setTag(tags.get(position));
				tagView.setVisibility(View.VISIBLE);
			}
			else {
				tagView.setVisibility(View.GONE);
			}
		}
	}
}
