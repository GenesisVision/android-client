package vision.genesis.clientapp.feature.common.currency;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/09/2018.
 */
public class CurrencyOptionView extends RelativeLayout
{
	@BindView(R.id.currency)
	public TextView currencyText;

	@BindView(R.id.rate)
	public TextView rate;

	@BindView(R.id.check)
	public ImageView check;

	private CurrencyEnum currency;

	public CurrencyOptionView(Context context) {
		super(context);
		initView();
	}

	public CurrencyOptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CurrencyOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_currency_option, this);

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		currencyText.setTypeface(TypefaceUtil.semibold());
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setData(CurrencyEnum currency, String rate) {
		this.currency = currency;
		this.currencyText.setText(currency.getValue());
		this.rate.setText(String.format(Locale.getDefault(), "1 GVT = %s %s", rate, currency.getValue()));
	}

	public void setSelected(boolean selected) {
		check.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
		currencyText.setTextColor(selected
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		rate.setTextColor(selected
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
	}
}
