package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2019.
 */

public class AdditionalCountView extends RelativeLayout
{
	@BindView(R.id.count)
	public TextView count;

	private Unbinder unbinder;

	public AdditionalCountView(Context context) {
		super(context);
		initView();
	}

	public AdditionalCountView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AdditionalCountView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_additional_count, this);

		unbinder = ButterKnife.bind(this);

		count.setTypeface(TypefaceUtil.semibold());
	}

	public void setAdditionalCount(int count) {
		this.count.setText(String.format(Locale.getDefault(), "+%s", count));
	}
}
