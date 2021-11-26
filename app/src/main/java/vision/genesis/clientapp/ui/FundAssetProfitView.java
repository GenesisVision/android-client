package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.FundAssetPartWithIcon;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

public class FundAssetProfitView extends RelativeLayout
{
	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.percent)
	public TextView percent;

	@BindView(R.id.dot)
	public TextView dot;

	@BindView(R.id.background)
	public View background;

	private Unbinder unbinder;

	public FundAssetProfitView(Context context) {
		super(context);
		initView();
	}

	public FundAssetProfitView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FundAssetProfitView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_fund_asset_profit, this);

		unbinder = ButterKnife.bind(this);

		percent.setTypeface(TypefaceUtil.semibold());
	}

	public void setAsset(FundAssetPartWithIcon asset) {
		if (asset.getLogoUrl() != null) {
			this.icon.setImageURI(ImageUtils.getImageUri(asset.getLogoUrl()));
		}
		else {
			this.icon.setVisibility(View.GONE);
		}
		this.name.setText(asset.getName());
		this.percent.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(asset.getPercent(), 0, 0)));
		this.dot.setTextColor(Color.parseColor(asset.getColor()));
	}
}
