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
import vision.genesis.clientapp.R;
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

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		totalProfitText.setTypeface(TypefaceUtil.light(getContext()));
		avgProfitText.setTypeface(TypefaceUtil.light(getContext()));
		balanceText.setTypeface(TypefaceUtil.light(getContext()));
		investorsText.setTypeface(TypefaceUtil.light(getContext()));

		totalProfitTextFull.setTypeface(TypefaceUtil.light(getContext()));
		avgProfitTextFull.setTypeface(TypefaceUtil.light(getContext()));
		balanceTextFull.setTypeface(TypefaceUtil.light(getContext()));

		totalProfitTextMod.setTypeface(TypefaceUtil.bold(getContext()));
		avgProfitTextPercent.setTypeface(TypefaceUtil.bold(getContext()));
		balanceTextMod.setTypeface(TypefaceUtil.bold(getContext()));

		totalProfitTitle.setTypeface(TypefaceUtil.bold(getContext()));
		avgProfitTitle.setTypeface(TypefaceUtil.bold(getContext()));
		balanceTitle.setTypeface(TypefaceUtil.bold(getContext()));
		investorsTitle.setTypeface(TypefaceUtil.bold(getContext()));

		totalProfitCurrency.setTypeface(TypefaceUtil.bold(getContext()));
		balanceCurrency.setTypeface(TypefaceUtil.bold(getContext()));
	}

	public void setData(Double profitTotal, Double profitAvg, Double balance, Integer investorsCount, String balanceCurrency) {
		totalProfitTextFull.setText(StringFormatUtil.formatAmount(profitTotal, 0, 18));

		ShortenedAmount totalProfitShortenedAmount = StringFormatUtil.getShortenedAmount(profitTotal);
		totalProfitText.setText(String.format("%s", totalProfitShortenedAmount.amount));
		totalProfitTextMod.setText(totalProfitShortenedAmount.modifier);

		avgProfitTextFull.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(profitAvg)));
		avgProfitText.setText(String.format(Locale.getDefault(), "%.0f", profitAvg));

		balanceTextFull.setText(StringFormatUtil.formatAmount(balance, 0, 18));

		ShortenedAmount balanceShortenedAmount = StringFormatUtil.getShortenedAmount(balance);
		balanceText.setText(String.format("%s", balanceShortenedAmount.amount));
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
