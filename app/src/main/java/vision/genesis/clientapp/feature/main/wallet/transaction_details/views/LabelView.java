package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */
public class LabelView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	public LabelView(Context context) {
		super(context);
		initView();
	}

	public LabelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_label, this);

		ButterKnife.bind(this);
	}

	public void setData(String text) {
		this.label.setText(text);
	}
}