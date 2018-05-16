package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.InvestmentProgramExtended;
import vision.genesis.clientapp.model.ShortenedAmount;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/03/2018.
 */

public class ProgramDataView extends RelativeLayout
{
	@BindView(R.id.group_total_profit_short)
	public ViewGroup totalProfitShortGroup;

	@BindView(R.id.text_total_profit_full)
	public TextView totalProfitTextFull;

	@BindView(R.id.text_total_profit_text)
	public TextView totalProfitText;

	@BindView(R.id.text_total_profit_text_mod)
	public TextView totalProfitTextMod;

	@BindView(R.id.text_total_profit_title)
	public TextView totalProfitTitle;

	@BindView(R.id.total_profit_currency)
	public TextView totalProfitCurrency;

	@BindView(R.id.group_avg_profit_short)
	public ViewGroup avgProfitShortGroup;

	@BindView(R.id.text_avg_profit_full)
	public TextView avgProfitTextFull;

	@BindView(R.id.text_avg_profit_text)
	public TextView avgProfitText;

	@BindView(R.id.text_avg_profit_text_percent)
	public TextView avgProfitTextPercent;

	@BindView(R.id.text_avg_profit_title)
	public TextView avgProfitTitle;

	@BindView(R.id.group_balance_short)
	public ViewGroup balanceShortGroup;

	@BindView(R.id.text_balance_full)
	public TextView balanceTextFull;

	@BindView(R.id.text_balance_text)
	public TextView balanceText;

	@BindView(R.id.text_balance_text_mod)
	public TextView balanceTextMod;

	@BindView(R.id.text_balance_title)
	public TextView balanceTitle;

	@BindView(R.id.balance_currency)
	public TextView balanceCurrency;

	@BindView(R.id.text_investors_text)
	public TextView investorsText;

	@BindView(R.id.text_investors_title)
	public TextView investorsTitle;

	private boolean isTotalProfitFull = false;

	private boolean isAvgProfitFull = false;

	private boolean isBalanceFull = false;

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

	@OnClick(R.id.group_total_profit)
	public void onTotalProfitClicked() {
		isTotalProfitFull = !isTotalProfitFull;
		setTotalProfitVisibility();
	}

	@OnClick(R.id.group_avg_profit)
	public void onAvgProfitClicked() {
		isAvgProfitFull = !isAvgProfitFull;
		setAvgProfitVisibility();
	}

	@OnClick(R.id.group_balance)
	public void onBalanceClicked() {
		isBalanceFull = !isBalanceFull;
		setBalanceVisibility();
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
		totalProfitText.setTypeface(TypefaceUtil.light());
		avgProfitText.setTypeface(TypefaceUtil.light());
		balanceText.setTypeface(TypefaceUtil.light());
		investorsText.setTypeface(TypefaceUtil.light());

		totalProfitTextFull.setTypeface(TypefaceUtil.light());
		avgProfitTextFull.setTypeface(TypefaceUtil.light());
		balanceTextFull.setTypeface(TypefaceUtil.light());

		totalProfitTextMod.setTypeface(TypefaceUtil.bold());
		avgProfitTextPercent.setTypeface(TypefaceUtil.bold());
		balanceTextMod.setTypeface(TypefaceUtil.bold());

		totalProfitTitle.setTypeface(TypefaceUtil.bold());
		avgProfitTitle.setTypeface(TypefaceUtil.bold());
		balanceTitle.setTypeface(TypefaceUtil.bold());
		investorsTitle.setTypeface(TypefaceUtil.bold());

		totalProfitCurrency.setTypeface(TypefaceUtil.bold());
		balanceCurrency.setTypeface(TypefaceUtil.bold());
	}

	public void setData(InvestmentProgramExtended program) {
		totalProfitTextFull.setText(program.getTotalProfitTextFull());
		totalProfitText.setText(program.getTotalProfitText());
		totalProfitTextMod.setText(program.getTotalProfitTextMod());

		avgProfitTextFull.setText(program.getAvgProfitTextFull());
		avgProfitText.setText(program.getAvgProfitText());

		balanceTextFull.setText(program.getBalanceTextFull());
		balanceText.setText(program.getBalanceText());
		balanceTextMod.setText(program.getBalanceTextMod());

		this.balanceCurrency.setText(program.getData().getCurrency().toString());

		investorsText.setText(program.getInvestorsText());
	}

	public void setData(Double profitTotal, Double profitAvg, Double balance, Integer investorsCount, String balanceCurrency) {
		totalProfitTextFull.setText(StringFormatUtil.formatAmount(profitTotal, 0, 18));

		ShortenedAmount totalProfitShortenedAmount = StringFormatUtil.getShortenedAmount(profitTotal);
		totalProfitText.setText(String.format("%s", totalProfitShortenedAmount.amount));
//		totalProfitText.setText(String.format("%s", profitTotal));
		totalProfitTextMod.setText(totalProfitShortenedAmount.modifier);

		avgProfitTextFull.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(profitAvg)));
		avgProfitText.setText(String.format(Locale.getDefault(), "%.0f", profitAvg));

		balanceTextFull.setText(StringFormatUtil.formatAmount(balance, 0, 18));

		ShortenedAmount balanceShortenedAmount = StringFormatUtil.getShortenedAmount(balance);
		balanceText.setText(String.format("%s", balanceShortenedAmount.amount));
//		balanceText.setText(String.format("%s", balance));
		balanceTextMod.setText(balanceShortenedAmount.modifier);

		this.balanceCurrency.setText(balanceCurrency);

		investorsText.setText(String.valueOf(investorsCount));
	}

	private void setTotalProfitVisibility() {
		totalProfitShortGroup.setVisibility(!isTotalProfitFull ? View.VISIBLE : View.GONE);
		totalProfitTextFull.setVisibility(isTotalProfitFull ? View.VISIBLE : View.GONE);
	}

	private void setAvgProfitVisibility() {
		avgProfitShortGroup.setVisibility(!isAvgProfitFull ? View.VISIBLE : View.GONE);
		avgProfitTextFull.setVisibility(isAvgProfitFull ? View.VISIBLE : View.GONE);
	}

	private void setBalanceVisibility() {
		balanceShortGroup.setVisibility(!isBalanceFull ? View.VISIBLE : View.GONE);
		balanceTextFull.setVisibility(isBalanceFull ? View.VISIBLE : View.GONE);
	}
}
