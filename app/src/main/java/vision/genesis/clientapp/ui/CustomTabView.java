package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

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

	@BindView(R.id.background_count)
	public ViewGroup countBackground;

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
		View customView = inflate(getContext(), R.layout.view_custom_tab, this);
//		View targetViewToApplyMargin = (View) customView.getParent();
//		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) targetViewToApplyMargin.getLayoutParams();
//
//		layoutParams.rightMargin = 20;
//		targetViewToApplyMargin.setLayoutParams(layoutParams);

		unbinder = ButterKnife.bind(this);

		text.setTypeface(TypefaceUtil.semibold());
		count.setTypeface(TypefaceUtil.semibold());
	}

	public void setData(int iconResId, int textResId) {
		if (iconResId != 0) {
			icon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE, iconResId));
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
		icon.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), selected ? R.attr.colorTextPrimary : R.attr.colorTextSecondary));
		text.setTextColor(ThemeUtil.getColorByAttrId(getContext(), selected ? R.attr.colorTextPrimary : R.attr.colorTextSecondary));
		count.setTextColor(ThemeUtil.getColorByAttrId(getContext(), selected ? R.attr.colorPrimary : R.attr.colorTextSecondary));
		countBackground.setBackground(ContextCompat.getDrawable(getContext(), selected ? R.drawable.background_count_active : R.drawable.background_count_inactive));
	}

	public void setCount(int count) {
		this.count.setText(StringFormatUtil.formatAmount(count, 0, 0));
		this.countBackground.setVisibility(View.VISIBLE);
	}
}
