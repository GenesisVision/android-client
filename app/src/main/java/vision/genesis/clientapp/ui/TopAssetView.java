package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.SocialPostPlatformAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/08/2020.
 */

public class TopAssetView extends RelativeLayout
{
	@BindView(R.id.platform_asset_logo)
	public SimpleDraweeView platformAssetLogo;

	@BindView(R.id.asset_name)
	public TextView assetName;

	@BindView(R.id.asset_ticker)
	public TextView assetTicker;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.change)
	public TextView change;

	private Unbinder unbinder;

	private SocialPostPlatformAsset asset;

	public TopAssetView(Context context) {
		super(context);
		initView();
	}

	public TopAssetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TopAssetView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_top_asset, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setTopAsset(SocialPostPlatformAsset asset) {
		this.asset = asset;

		this.platformAssetLogo.setImageURI(asset.getLogoUrl());
		this.assetName.setText(asset.getName());
		this.assetTicker.setText(asset.getAsset());

		if (asset.getChange24Percent() != null) {
			this.change.setText(StringFormatUtil.getPercentString(Math.abs(asset.getChange24Percent())));
		}
		switch (asset.getChangeState()) {
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
		if (asset.getPrice() != null) {
			this.value.setText(StringFormatUtil.getValueString(asset.getPrice(), asset.getPriceCurrency().getValue()));
		}
	}
}
