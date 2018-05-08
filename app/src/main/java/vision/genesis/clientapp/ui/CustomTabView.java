package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */
public class CustomTabView extends RelativeLayout
{
	@BindView(R.id.icon)
	public ImageView icon;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.count)
	public TextView count;

	private Unbinder unbinder;

	public CustomTabView(Context context) {
		super(context);
		initView();
	}

	public CustomTabView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CustomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_custom_tab, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setData(int iconResId, int textResId) {
		if (iconResId != 0) {
			icon.setImageDrawable(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, iconResId));
			icon.setVisibility(View.VISIBLE);
		}
		else {
			icon.setVisibility(View.GONE);
		}
		if (textResId != 0) {
			text.setText(getResources().getString(textResId));
			text.setVisibility(View.VISIBLE);
		}
		else {
			text.setVisibility(View.GONE);
		}
	}


	public void setSelectedState(boolean selected) {
		icon.setColorFilter(selected
				? ContextCompat.getColor(getContext(), R.color.colorPrimary)
				: ContextCompat.getColor(getContext(), R.color.colorFontMedium));
		text.setTextColor(selected
				? ContextCompat.getColor(getContext(), R.color.colorPrimary)
				: ContextCompat.getColor(getContext(), R.color.colorFontMedium));
	}

	public void setCount(int count) {
		this.count.setText(StringFormatUtil.formatAmount(count, 0, 0));
		this.count.setVisibility(View.VISIBLE);
	}
}
