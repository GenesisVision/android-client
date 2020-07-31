package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.PostTag;
import io.swagger.client.model.SocialPostTagType;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostTagView extends RelativeLayout
{
	@BindView(R.id.platform_asset_logo)
	public SimpleDraweeView platformAssetLogo;

	@BindView(R.id.avatar_view)
	public AvatarView avatarView;

	@BindView(R.id.asset_logo)
	public ProgramLogoView assetLogo;

	@BindView(R.id.asset_name)
	public TextView assetName;

	@BindView(R.id.asset_type)
	public TextView assetType;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.change)
	public TextView change;

	private Unbinder unbinder;

	private PostTag tag;

	public PostTagView(Context context) {
		super(context);
		initView();
	}

	public PostTagView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PostTagView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_post_tag, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setPostTag(PostTag tag) {
		this.tag = tag;

		if (tag.getType().equals(SocialPostTagType.ASSET)) {
			this.assetType.setVisibility(View.GONE);
			this.assetLogo.setVisibility(View.GONE);
			this.avatarView.setVisibility(View.GONE);
			this.platformAssetLogo.setVisibility(View.VISIBLE);
			this.change.setVisibility(View.VISIBLE);

			this.platformAssetLogo.setImageURI(tag.getPlatformAssetDetails().getLogoUrl());
			this.assetName.setText(tag.getPlatformAssetDetails().getName());

			if (tag.getPlatformAssetDetails().getChange24Percent() != null) {
				this.change.setText(StringFormatUtil.getPercentString(Math.abs(tag.getPlatformAssetDetails().getChange24Percent())));
			}
			switch (tag.getPlatformAssetDetails().getChangeState()) {
				case NOTCHANGED:
					this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
					break;
				case INCREASED:
					this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));
					this.change.setText("↑".concat(this.change.getText().toString()));
					break;
				case DECREASED:
					this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
					this.change.setText("↓".concat(this.change.getText().toString()));
					break;
			}
			if (tag.getPlatformAssetDetails().getPrice() != null) {
				this.value.setText(StringFormatUtil.getValueString(tag.getPlatformAssetDetails().getPrice(), tag.getPlatformAssetDetails().getPriceCurrency().getValue()));
			}
		}
		else if (tag.getType().equals(SocialPostTagType.USER)) {
			this.assetType.setVisibility(View.GONE);
			this.assetLogo.setVisibility(View.GONE);
			this.platformAssetLogo.setVisibility(View.GONE);
			this.change.setVisibility(View.GONE);
			this.avatarView.setVisibility(View.VISIBLE);

			this.avatarView.setImage(tag.getUserDetails().getLogoUrl(), 50, 50);
			this.assetName.setText(tag.getUserDetails().getUsername());
		}
		else {
			this.assetType.setVisibility(View.VISIBLE);
			this.assetLogo.setVisibility(View.VISIBLE);
			this.platformAssetLogo.setVisibility(View.GONE);
			this.avatarView.setVisibility(View.GONE);
			this.change.setVisibility(View.GONE);

//			if (tag.getAssetDetails().getAssetType().equals(AssetType.PROGRAM)) {
//				this.assetLogo.setLevel(tag.getAssetDetails().getProgramDetails().getLevel(), tag.getAssetDetails().getProgramDetails().getLevelProgress());
//			}
//			else {
			this.assetLogo.hideLevel();
//			}
			this.assetLogo.setImage(tag.getAssetDetails().getLogoUrl(), tag.getAssetDetails().getColor(), 50, 50);

			this.assetName.setText(tag.getTitle());
			this.assetType.setText(tag.getAssetDetails().getAssetType().toString());

			if (tag.getAssetDetails().getPrice() != null && tag.getAssetDetails().getPriceCurrency() != null) {
				this.value.setText(StringFormatUtil.getValueString(tag.getAssetDetails().getPrice(), tag.getAssetDetails().getPriceCurrency().getValue()));
			}
		}
	}
}
