package vision.genesis.clientapp.feature.main.copytrading.commissions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderSignalFee;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/07/2019.
 */

public class CommissionView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.currency)
	public TextView currency;

	public CommissionView(Context context) {
		super(context);
		initView();
	}

	public CommissionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CommissionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_commission, this);

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		this.value.setTypeface(TypefaceUtil.semibold());
		this.currency.setTypeface(TypefaceUtil.light());
	}

	public void setData(OrderSignalFee fee) {
		this.label.setText(getFeeName(fee));
		this.value.setText(getAmountString(fee.getAmount(), fee.getCurrency().toString()));
		this.currency.setText(fee.getCurrency().toString());
	}

	public void setData(String name, Double amount, String currency) {
		this.label.setText(name);
		this.value.setText(StringFormatUtil.formatCurrencyAmount(amount, currency));
		this.currency.setText(currency);
	}

	private String getAmountString(Double amount, String currency) {
		if (amount > 0) {
			amount *= -1;
		}
		return StringFormatUtil.formatCurrencyAmount(amount, currency);
	}

	private String getFeeName(OrderSignalFee fee) {
		int nameResId = 0;
		switch (fee.getType()) {
			case MANAGERSIGNALMASTERVOLUMEFEE:
				nameResId = R.string.volume_fee;
				break;
			case MANAGERSIGNALMASTERSUCCESSFEE:
				nameResId = R.string.manager_success_fee;
				break;
			case GVSIGNALSUCCESSFEE:
				nameResId = R.string.platform_success_fee;
				break;
			case GVPROGRAMENTRY:
				break;
			case GVPROGRAMSUCCESS:
				break;
			case GVFUNDENTRY:
				break;
			case GVGMGVTHOLDERFEE:
				break;
			case MANAGERPROGRAMENTRY:
				break;
			case MANAGERPROGRAMSUCCESS:
				break;
			case MANAGERFUNDENTRY:
				break;
			case MANAGERFUNDEXIT:
				break;
			case GVWITHDRAWAL:
				break;
			case UNDEFINED:
				break;
		}
		return nameResId == 0 ? "" : getContext().getString(nameResId);
	}
}
