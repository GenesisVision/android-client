package vision.genesis.clientapp.feature.main.program.profit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ChartSimple;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/09/2018.
 */

public class ProgramProfitFragment extends BaseFragment implements ProgramProfitView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static ProgramProfitFragment with(UUID programId) {
		ProgramProfitFragment programProfitFragment = new ProgramProfitFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		programProfitFragment.setArguments(arguments);
		return programProfitFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.amount_value)
	public TextView amountValue;

	@BindView(R.id.amount_value_secondary)
	public TextView amountValueSecondary;

	@BindView(R.id.change_value)
	public TextView changeValue;

	@BindView(R.id.change_percent)
	public TextView changePercent;

	@BindView(R.id.change_value_secondary)
	public TextView changeValueSecondary;

	@BindView(R.id.label_statistics)
	public TextView statisticsLabel;

	@BindView(R.id.start_day)
	public TextView startDay;

	@BindView(R.id.start_balance)
	public TextView startBalance;

	@BindView(R.id.invested)
	public TextView invested;

	@BindView(R.id.success_trades)
	public TextView successTrades;

	@BindView(R.id.profit_factor)
	public TextView profitFactor;

	@BindView(R.id.sharpe_ratio)
	public TextView sharpeRatio;

	@BindView(R.id.sortino_ratio)
	public TextView sortinoRatio;

	@BindView(R.id.calmar_ratio)
	public TextView calmarRatio;

	@BindView(R.id.trades)
	public TextView trades;

	@BindView(R.id.investors)
	public TextView investors;

	@BindView(R.id.max_drawdown)
	public TextView maxDrawdown;

	@InjectPresenter
	public ProgramProfitPresenter programProfitPresenter;

	private UUID programId;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.DAY);

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(programProfitPresenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_profit, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		programId = (UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID);
		programProfitPresenter.setProgramId(programId);

		setFonts();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		amountValue.setTypeface(TypefaceUtil.semibold());
		changeValue.setTypeface(TypefaceUtil.semibold());
		changePercent.setTypeface(TypefaceUtil.semibold());
		amountValueSecondary.setTypeface(TypefaceUtil.medium());
		changeValueSecondary.setTypeface(TypefaceUtil.medium());

		statisticsLabel.setTypeface(TypefaceUtil.semibold());
		startDay.setTypeface(TypefaceUtil.semibold());
		startBalance.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());
		successTrades.setTypeface(TypefaceUtil.semibold());
		profitFactor.setTypeface(TypefaceUtil.semibold());
		sharpeRatio.setTypeface(TypefaceUtil.semibold());
		sortinoRatio.setTypeface(TypefaceUtil.semibold());
		calmarRatio.setTypeface(TypefaceUtil.semibold());
		trades.setTypeface(TypefaceUtil.semibold());
		investors.setTypeface(TypefaceUtil.semibold());
		maxDrawdown.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setChartData(List<ChartSimple> equityChart) {

	}

	@Override
	public void setAmount(String gvtAmount, String baseAmount) {
		amountValue.setText(gvtAmount);
		amountValueSecondary.setText(baseAmount);
	}

	@Override
	public void setChange(Boolean isChangeNegative, String changePercent, String changeValue, String baseChangeValue) {
		this.changePercent.setTextColor(isChangeNegative
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));

		this.changePercent.setText(changePercent);
		this.changeValue.setText(changeValue);
		this.changeValueSecondary.setText(baseChangeValue);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show)
			scrollView.setVisibility(View.VISIBLE);
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void setStatisticsData(Integer trades, Double successTradesPercent, Double profitFactor, Double sharpeRatio, Double sortinoRatio, Double calmarRatio, Double maxDrawdown) {
		this.trades.setText(String.valueOf(trades));
		this.successTrades.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(successTradesPercent, 0, 4)));
		this.profitFactor.setText(profitFactor == null ? "∞" : StringFormatUtil.formatAmount(profitFactor, 0, 4));
		this.sharpeRatio.setText(StringFormatUtil.formatAmount(sharpeRatio, 0, 4));
		this.sortinoRatio.setText(StringFormatUtil.formatAmount(sortinoRatio, 0, 4));
		this.calmarRatio.setText(StringFormatUtil.formatAmount(calmarRatio, 0, 4));
		this.maxDrawdown.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(maxDrawdown, 0, 4)));
	}

	@Override
	public void pagerShow() {
		if (programProfitPresenter != null)
			programProfitPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}