package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/04/2018.
 */
public class AvailableTokensView extends RelativeLayout
{
	@BindView(R.id.unavailable)
	public FrameLayout unavailable;

	@BindView(R.id.invest)
	public FrameLayout invest;

	@BindDimen(R.dimen.available_tokens_view_width)
	int width;

	public AvailableTokensView(Context context) {
		super(context);
		initView();
	}

	public AvailableTokensView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AvailableTokensView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_available_tokens, this);

		ButterKnife.bind(this);
	}

	public void setData(double maxTokens, double unavailableTokens, double investTokens) {
		double oneTokenWidth = this.width / maxTokens;

		RelativeLayout.LayoutParams unavailableLP = (RelativeLayout.LayoutParams) unavailable.getLayoutParams();
		unavailableLP.width = (int) (unavailableTokens * oneTokenWidth);
		unavailable.setLayoutParams(unavailableLP);

		RelativeLayout.LayoutParams investLP = (RelativeLayout.LayoutParams) invest.getLayoutParams();
		investLP.width = (int) ((unavailableTokens + investTokens) * oneTokenWidth);
		invest.setLayoutParams(investLP);
	}
}
