package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/07/2019.
 */

public class FiltersView extends RelativeLayout
{
	@BindView(R.id.text_filters)
	public TextView filters;

	private Unbinder unbinder;

	public FiltersView(Context context) {
		super(context);
		initView();
	}

	public FiltersView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FiltersView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_filters, this);

		unbinder = ButterKnife.bind(this);

		filters.setTypeface(TypefaceUtil.semibold());
	}
}
