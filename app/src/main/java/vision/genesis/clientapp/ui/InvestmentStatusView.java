package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.AssetInvestmentStatus;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 09/10/2018.
 */
public class InvestmentStatusView extends RelativeLayout
{
	@BindView(R.id.text_status)
	public TextView status;

	@BindView(R.id.background)
	public ImageView background;

	@BindView(R.id.arrow)
	public ImageView arrow;

	private Unbinder unbinder;

	public InvestmentStatusView(Context context) {
		super(context);
		initView();
	}

	public InvestmentStatusView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public InvestmentStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_investment_status, this);

		unbinder = ButterKnife.bind(this);

		status.setTypeface(TypefaceUtil.semibold());
	}

	public void setStatus(String status) {
		AssetInvestmentStatus statusEnum = AssetInvestmentStatus.fromValue(status);
		if (statusEnum != null) {
			this.status.setText(status);

			int colorAttrId = R.attr.colorTextPrimary;
			switch (statusEnum) {
				case ACTIVE:
					colorAttrId = R.attr.colorTextPrimary;
					arrow.setVisibility(View.GONE);
					break;
				case INVESTING:
					colorAttrId = R.attr.colorPending;
					arrow.setVisibility(View.VISIBLE);
					break;
				case WITHDRAWING:
					colorAttrId = R.attr.colorWithdrawing;
					arrow.setVisibility(View.VISIBLE);
					break;
				case ENDED:
					colorAttrId = R.attr.colorRed;
					arrow.setVisibility(View.GONE);
					break;
			}

			int color = ThemeUtil.getColorByAttrId(getContext(), colorAttrId);
			this.status.setTextColor(color);
			this.background.setColorFilter(color);
			this.arrow.setColorFilter(color);
		}
	}
}
