package vision.genesis.clientapp.feature.main.terminal.order_book;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */
public class DepthItemView extends RelativeLayout
{
	public interface OnPriceSelectedListener
	{
		void OnPriceSelected(Double price);
	}

	public static final String TYPE_ASK = "type_ask";

	public static final String TYPE_BID = "type_bid";

	@BindView(R.id.fill)
	public View fillLine;

	@BindView(R.id.empty)
	public View emptyLine;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.amount)
	public TextView amount;

	private Unbinder unbinder;

	private OnPriceSelectedListener listener;

	private Pair<Double, Double> data;

	public DepthItemView(Context context) {
		super(context);
	}

	public DepthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DepthItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void initView(String type, boolean isMini) {
		int layoutId;
		switch (type) {
			case TYPE_ASK:
				layoutId = isMini ? R.layout.view_item_ask_mini : R.layout.view_item_ask;
				break;
			default:
			case TYPE_BID:
				layoutId = isMini ? R.layout.view_item_bid_mini : R.layout.view_item_bid;
				break;
		}
		inflate(getContext(), layoutId, this);

		unbinder = ButterKnife.bind(this);

		this.setOnClickListener(view -> {
			if (listener != null && data != null) {
				listener.OnPriceSelected(data.first);
			}
		});
	}

	public void setData(@Nullable Pair<Double, Double> data, Double fill, int priceDigits, int qtyDigits) {
		this.data = data;
		if (data != null) {
			this.price.setText(StringFormatUtil.formatAmount(data.first, priceDigits, priceDigits));
			this.amount.setText(StringFormatUtil.formatAmount(data.second, qtyDigits, qtyDigits));
		}
		else {
			this.price.setText("--");
			this.amount.setText("--");
		}
		LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) fillLine.getLayoutParams();
		LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) emptyLine.getLayoutParams();
		lp1.weight = fill.floatValue();
		lp2.weight = 1 - fill.floatValue();
	}

	public void setListener(OnPriceSelectedListener listener) {
		this.listener = listener;
	}
}
