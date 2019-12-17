package vision.genesis.clientapp.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.FundAssetInfo;
import io.swagger.client.model.PlatformAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;
import vision.genesis.clientapp.utils.VibrationUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

public class CreateFundAssetView extends RelativeLayout
{
	public interface Listener
	{
		void onAssetClicked(PlatformAsset asset);

		void onAssetLongClicked(PlatformAsset asset, double share);

		void onRemoveAssetClicked(PlatformAsset asset);
	}

	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.percent)
	public TextView percent;

	@BindView(R.id.background)
	public View background;

	@BindView(R.id.button_remove)
	public ViewGroup removeButton;

	private PlatformAsset asset;

	private Listener listener;

	private Unbinder unbinder;

	private double share;

	public CreateFundAssetView(Context context) {
		super(context);
		initView();
	}

	public CreateFundAssetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CreateFundAssetView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_remove)
	public void onRemoveClicked() {
		if (listener != null && asset != null) {
			listener.onRemoveAssetClicked(asset);
		}
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_create_fund_asset, this);

		unbinder = ButterKnife.bind(this);

		percent.setTypeface(TypefaceUtil.semibold());

		background.setOnClickListener((view) -> {
			if (listener != null && asset != null) {
				listener.onAssetClicked(asset);
			}
		});

		background.setOnLongClickListener(view -> {
			if (listener != null && asset != null) {
				VibrationUtil.vibrateShort(getContext());
				listener.onAssetLongClicked(asset, share);
				return true;
			}
			else {
				return false;
			}
		});
	}

	public PlatformAsset getAsset() {
		return asset;
	}

	public void setAsset(FundAssetInfo asset) {
		this.icon.setImageURI(ImageUtils.getImageUri(asset.getIcon()));
		this.name.setText(asset.getSymbol());
		this.removeButton.setVisibility(View.GONE);

		updateShare(asset.getTarget());
	}

	public void setAsset(PlatformAsset asset, double share) {
		this.asset = asset;

		this.icon.setImageURI(ImageUtils.getImageUri(asset.getIcon()));
		this.name.setText(asset.getAsset());
		this.removeButton.setVisibility(share > asset.getMandatoryFundPercent() ? View.VISIBLE : View.GONE);

		updateShare(share);
	}

	public double getShare() {
		return share;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private void updateShare(double share) {
		this.share = share;
		this.percent.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(share, 0, 0)));
		if (asset != null) {
			this.removeButton.setVisibility(share > asset.getMandatoryFundPercent() ? View.VISIBLE : View.GONE);
		}
	}

	public void select(boolean selected) {
		int assetColor = Color.parseColor(asset.getColor());
		int unselectedColor = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary);
		ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(selected ? assetColor : unselectedColor));
		background.setAlpha(selected ? 0.5f : 0.02f);
	}

	public void updateShareWithAnim(double newShare) {
		ValueAnimator animator = ValueAnimator.ofFloat((float) this.share, (float) newShare);
		animator.addUpdateListener(animation -> updateShare((float) animator.getAnimatedValue()));
		animator.setDuration(300);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
	}
}
