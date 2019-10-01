package vision.genesis.clientapp.ui;

import android.content.Context;
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

public class FundAssetAllocationView extends RelativeLayout
{
	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.percent)
	public TextView percent;

	@BindView(R.id.background)
	public View background;

	private Unbinder unbinder;

	public FundAssetAllocationView(Context context) {
		super(context);
		initView();
	}

	public FundAssetAllocationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FundAssetAllocationView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_fund_asset_allocation, this);

		unbinder = ButterKnife.bind(this);

		percent.setTypeface(TypefaceUtil.semibold());
	}

	public void setAsset(FundAssetPartWithIcon asset) {
		this.icon.setImageURI(ImageUtils.getImageUri(asset.getIcon()));
		this.percent.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(asset.getPercent(), 0, 0)));

//		int assetColor = Color.parseColor(asset.getColor());
//		ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(assetColor));
	}
}
