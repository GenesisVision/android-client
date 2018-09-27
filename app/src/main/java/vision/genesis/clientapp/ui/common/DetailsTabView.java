package vision.genesis.clientapp.ui.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
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

	public void setSelectedState(boolean selected) {
		text.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				selected
						? R.attr.colorTextPrimary
						: R.attr.colorTextSecondary));
	}
}
