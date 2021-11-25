package vision.genesis.clientapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PlatformCurrencyInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/11/2021.
 */
public class ChartAssetView extends RelativeLayout
{
	public interface Listener
	{
		void onAssetClicked(PlatformCurrencyInfo asset);

		void onRemoveAssetClicked(PlatformCurrencyInfo asset);
	}

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.background)
	public View background;

	@BindView(R.id.button_remove)
	public View removeButton;

	private Unbinder unbinder;

	private Listener listener;

	private PlatformCurrencyInfo asset;

	public ChartAssetView(Context context) {
		super(context);
		initView();
	}

	public ChartAssetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ChartAssetView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_remove)
	public void onRemoveClicked() {
		if (listener != null) {
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
		inflate(getContext(), R.layout.view_chart_asset, this);

		unbinder = ButterKnife.bind(this);

		name.setTypeface(TypefaceUtil.semibold());

		this.setOnClickListener(view -> {
			if (listener != null) {
				listener.onAssetClicked(asset);
			}
		});
	}

	public void setAsset(PlatformCurrencyInfo asset) {
		this.asset = asset;

		this.name.setText(asset.getName());
		this.name.setTextColor(Color.parseColor(asset.getColor()));
		ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(Color.parseColor(asset.getColor())));
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void setRemoveEnabled(boolean enabled) {
		this.removeButton.setVisibility(enabled ? View.VISIBLE : View.INVISIBLE);
	}
}
