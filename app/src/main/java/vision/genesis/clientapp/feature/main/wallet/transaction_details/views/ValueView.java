package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class ValueView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.value)
	public TextView value;

	public ValueView(Context context) {
		super(context);
		initView();
	}

	public ValueView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ValueView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_value, this);

		ButterKnife.bind(this);
	}

	public void setData(String label, String value) {
		this.label.setText(label);
		this.value.setText(value);
	}

	public void setValueParams(Boolean semibold, Integer textSizeSp) {
		if (semibold)
			this.value.setTypeface(TypefaceUtil.semibold());
		this.value.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp);
	}
}