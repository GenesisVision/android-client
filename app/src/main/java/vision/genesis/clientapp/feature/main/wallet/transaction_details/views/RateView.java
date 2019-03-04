package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.TransactionDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class RateView extends RelativeLayout
{
	@BindView(R.id.value)
	public TextView value;

	public RateView(Context context) {
		super(context);
		initView();
	}

	public RateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public RateView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_rate, this);

		ButterKnife.bind(this);
	}

	public void setData(TransactionDetails details) {
		this.value.setText(String.format(Locale.getDefault(), "1 %s = %s",
				details.getCurrency().getValue(),
				StringFormatUtil.getValueString(details.getConvertingDetails().getRateValue(),
						details.getConvertingDetails().getCurrencyTo().getValue())));
	}
}