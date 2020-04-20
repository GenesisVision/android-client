package vision.genesis.clientapp.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/09/2018.
 */
public class DetailsTabView extends RelativeLayout
{
	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.count)
	public TextView count;

	@BindView(R.id.background_count)
	public ViewGroup countBackground;

	private Unbinder unbinder;

	public DetailsTabView(Context context) {
		super(context);
		initView();
	}

	public DetailsTabView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DetailsTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_details_tab, this);

		unbinder = ButterKnife.bind(this);

		text.setTypeface(TypefaceUtil.semibold());
		count.setTypeface(TypefaceUtil.semibold());
	}

	public void setData(int textResId) {
		if (textResId != 0) {
			text.setText(getResources().getString(textResId));
			text.setVisibility(View.VISIBLE);
		}
		else {
			text.setVisibility(View.GONE);
		}
	}

	public void setData(String text) {
		if (text != null && !text.isEmpty()) {
			this.text.setText(text);
			this.text.setVisibility(View.VISIBLE);
		}
		else {
			this.text.setVisibility(View.GONE);
		}
	}

	public void setSelectedState(boolean selected) {
		text.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				selected
						? R.attr.colorTextPrimary
						: R.attr.colorTextSecondary));

		count.setTextColor(ThemeUtil.getColorByAttrId(getContext(), selected ? R.attr.colorPrimary : R.attr.colorTextSecondary));
		countBackground.setBackground(ContextCompat.getDrawable(getContext(), selected ? R.drawable.background_count_active : R.drawable.background_count_inactive));
	}

	public void setCount(int count) {
		this.count.setText(StringFormatUtil.formatAmount(count, 0, 0));
		this.countBackground.setVisibility(View.VISIBLE);
	}
}
