package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
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
	@BindView(R.id.text_total_profit_text)
	public TextView totalProfitText;

	@BindView(R.id.text_total_profit_text_mod)
	public TextView totalProfitTextMod;

	@BindView(R.id.text_total_profit_title)
	public TextView totalProfitTitle;


	@BindView(R.id.text_avg_profit_text)
	public TextView avgProfitText;

	@BindView(R.id.text_avg_profit_text_percent)
	public TextView avgProfitTextPercent;

	@BindView(R.id.text_avg_profit_title)
	public TextView avgProfitTitle;


	@BindView(R.id.text_balance_text)
	public TextView balanceText;

	@BindView(R.id.text_balance_text_mod)
	public TextView balanceTextMod;

	@BindView(R.id.text_balance_title)
	public TextView balanceTitle;


	@BindView(R.id.text_period_text)
	public TextView periodText;

	@BindView(R.id.text_period_title)
	public TextView periodTitle;

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

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		totalProfitText.setTypeface(TypefaceUtil.light(getContext()));
		avgProfitText.setTypeface(TypefaceUtil.light(getContext()));
		balanceText.setTypeface(TypefaceUtil.light(getContext()));
		periodText.setTypeface(TypefaceUtil.light(getContext()));

		totalProfitTextMod.setTypeface(TypefaceUtil.bold(getContext()));
		avgProfitTextPercent.setTypeface(TypefaceUtil.bold(getContext()));
		balanceTextMod.setTypeface(TypefaceUtil.bold(getContext()));

		totalProfitTitle.setTypeface(TypefaceUtil.bold(getContext()));
		avgProfitTitle.setTypeface(TypefaceUtil.bold(getContext()));
		balanceTitle.setTypeface(TypefaceUtil.bold(getContext()));
		periodTitle.setTypeface(TypefaceUtil.bold(getContext()));
	}

	public void setData(Double profitTotal, Double profitAvg, Double balance, Integer periodDuration) {
		ShortenedAmount totalProfitShortenedAmount = StringFormatUtil.getShortenedAmount(profitTotal);
		totalProfitText.setText(String.format("$%s", totalProfitShortenedAmount.amount));
		totalProfitTextMod.setText(totalProfitShortenedAmount.modifier);

		avgProfitText.setText(String.format(Locale.getDefault(), "%.0f", profitAvg));

		ShortenedAmount balanceShortenedAmount = StringFormatUtil.getShortenedAmount(balance);
		balanceText.setText(String.format("$%s", balanceShortenedAmount.amount));
		balanceTextMod.setText(balanceShortenedAmount.modifier);

		periodText.setText(String.valueOf(periodDuration));
	}
}
