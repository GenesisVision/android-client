package vision.genesis.clientapp.feature.main.events.details;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/09/2019.
 */

public class EventAmountView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.currency)
	public TextView currency;

	public EventAmountView(Context context) {
		super(context);
		initView();
	}

	public EventAmountView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public EventAmountView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_event_amount, this);

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		this.value.setTypeface(TypefaceUtil.semibold());
		this.currency.setTypeface(TypefaceUtil.light());
	}

	public void setData(String title, Double amount, String currency) {
		this.label.setText(title);
		this.value.setText(StringFormatUtil.formatAmount(amount));
		this.currency.setText(currency);
	}
}
