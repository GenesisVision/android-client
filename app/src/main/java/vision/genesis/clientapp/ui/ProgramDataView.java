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
import vision.genesis.clientapp.model.InvestmentProgramExtended;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/03/2018.
 */

public class ProgramDataView extends RelativeLayout
{
	@BindView(R.id.text_total_profit)
	public TextView totalProfitText;

	@BindView(R.id.text_total_profit_title)
	public TextView totalProfitTitle;

	@BindView(R.id.total_profit_currency)
	public CurrencyView totalProfitCurrency;

	@BindView(R.id.text_avg_profit)
	public TextView avgProfitText;

	@BindView(R.id.text_avg_profit_title)
	public TextView avgProfitTitle;

	@BindView(R.id.text_balance)
	public TextView balanceText;

	@BindView(R.id.text_balance_title)
	public TextView balanceTitle;

	@BindView(R.id.balance_currency)
	public CurrencyView balanceCurrency;

	@BindView(R.id.text_investors)
	public TextView investorsText;

	@BindView(R.id.text_investors_title)
	public TextView investorsTitle;

	private Unbinder unbinder;

	public ProgramDataView(Context context) {
		super(context);
		initView();
	}

	public ProgramDataView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProgramDataView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_program_data, this);

		unbinder = ButterKnife.bind(this);

		setFonts();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void setFonts() {
//		totalProfitText.setTypeface(TypefaceUtil.bold());
//		avgProfitText.setTypeface(TypefaceUtil.bold());
//		balanceText.setTypeface(TypefaceUtil.bold());
//		investorsText.setTypeface(TypefaceUtil.bold());

//		totalProfitTitle.setTypeface(TypefaceUtil.light());
//		avgProfitTitle.setTypeface(TypefaceUtil.light());
//		balanceTitle.setTypeface(TypefaceUtil.light());
//		investorsTitle.setTypeface(TypefaceUtil.light());
	}

	public void setData(InvestmentProgramExtended program) {
		totalProfitText.setText(program.getTotalProfitTextFull());
		avgProfitText.setText(program.getAvgProfitTextFull());
		balanceText.setText(program.getBalanceTextFull());
		this.balanceCurrency.setCurrency(program.getBalanceCurrency());
		investorsText.setText(program.getInvestorsText());
	}

	public void setData(Double profitTotal, Double profitAvg, Double balance, Integer investorsCount, String balanceCurrency) {
		totalProfitText.setText(StringFormatUtil.formatAmount(profitTotal, 0, 18));
		avgProfitText.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(profitAvg)));
		balanceText.setText(StringFormatUtil.formatAmount(balance, 0, 18));
		this.balanceCurrency.setCurrency(balanceCurrency);
		investorsText.setText(String.valueOf(investorsCount));
	}
}
