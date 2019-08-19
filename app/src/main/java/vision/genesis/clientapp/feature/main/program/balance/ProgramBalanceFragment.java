package vision.genesis.clientapp.feature.main.program.balance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProgramBalanceChartElement;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.chart.BalanceChartView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/10/2018.
 */

public class ProgramBalanceFragment extends BaseFragment implements ProgramBalanceView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static ProgramBalanceFragment with(UUID programId) {
		ProgramBalanceFragment programProfitFragment = new ProgramBalanceFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		programProfitFragment.setArguments(arguments);
		return programProfitFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.balance_chart)
	public BalanceChartView balanceChart;

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

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public ProgramBalancePresenter programBalancePresenter;

	private UUID programId;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(programBalancePresenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_balance, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		programId = (UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID);
		programBalancePresenter.setProgramId(programId);

		setFonts();

		balanceChart.setTouchListener(programBalancePresenter);
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
	}

	@Override
	public void setChartData(List<ProgramBalanceChartElement> balanceChart) {
		this.balanceChart.setProgramChartData(balanceChart, dateRange);
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
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
			dateRangeView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void pagerShow() {
		if (programBalancePresenter != null)
			programBalancePresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null)
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
	}
}