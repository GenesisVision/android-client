package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.ProgramDetailsList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

public class ProgramDashboardShortView extends RelativeLayout
{
	@BindView(R.id.program_logo)
	public ProgramLogoView programLogo;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.time_left)
	public PeriodLeftShortView timeLeft;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.change)
	public TextView change;

	private Unbinder unbinder;

	private ProgramDetailsList program;

	private String baseCurrency;

	public ProgramDashboardShortView(Context context) {
		super(context);
		initView();
	}

	public ProgramDashboardShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProgramDashboardShortView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_program_dashboard_short, this);

		unbinder = ButterKnife.bind(this);

		programName.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(v -> {
			if (program != null) {
//				EventBus.getDefault().post(new ShowEventDetailsEvent(event));
			}
		});
	}

	public void setData(ProgramDetailsList program, String baseCurrency) {
		this.program = program;
		this.baseCurrency = baseCurrency;

		this.programLogo.setImage(program.getLogo(), program.getColor(), 50, 50);
		this.programLogo.setLevel(program.getLevel(), program.getLevelProgress());
		this.programName.setText(program.getTitle());
		this.timeLeft.setData(program.getPeriodDuration(), program.getPeriodStarts(), program.getPeriodEnds(), false, true);

		double value = Math.random() * 1000000;
		double change = Math.random() * 100000 - 50000;

		this.value.setText(StringFormatUtil.getValueString(value, baseCurrency));
		updateChangeText(value, change);
	}

	private void updateChangeText(double value, double profit) {
		double profitPercent = Math.abs(profit / (value - profit) * 100);
		String sign = profit > 0 ? "+" : "";
		this.change.setText(String.format(Locale.getDefault(), "%s%s (%s%%)",
				sign,
				StringFormatUtil.getValueString(profit, baseCurrency),
				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profit > 0
						? R.attr.colorGreen
						: profit < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}
}
