package vision.genesis.clientapp.feature.main.follow.balance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PlatformCurrencyInfo;
import io.swagger.client.model.ProgramFollowDetailsFull;
import io.swagger.client.model.SimpleChartPoint;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.ChartAssetView;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.chart.BalanceChartView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/12/2018.
 */

public class FollowBalanceFragment extends BaseFragment implements FollowBalanceView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_DETAILS = "extra_details";

	public static FollowBalanceFragment with(ProgramFollowDetailsFull details) {
		FollowBalanceFragment fragment = new FollowBalanceFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_DETAILS, details);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.view_chart_asset)
	public ChartAssetView chartAssetView;

	@BindView(R.id.balance_chart)
	public BalanceChartView balanceChart;

	@BindView(R.id.amount_value)
	public TextView amountValue;

	@BindView(R.id.change_value)
	public TextView changeValue;

	@BindView(R.id.change_percent)
	public TextView changePercent;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public FollowBalancePresenter presenter;

	private ProgramFollowDetailsFull details;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.WEEK);

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(presenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_follow_balance, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		details = getArguments().getParcelable(EXTRA_DETAILS);
		presenter.setData(details);

		setFonts();

		balanceChart.setTouchListener(presenter);

		chartAssetView.setRemoveEnabled(false);
		chartAssetView.setListener(new ChartAssetView.Listener()
		{
			@Override
			public void onAssetClicked(PlatformCurrencyInfo asset) {
				presenter.onAssetClicked(asset);
			}

			@Override
			public void onRemoveAssetClicked(PlatformCurrencyInfo asset) {
			}
		});
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
	}

	@Override
	public void setChartData(List<SimpleChartPoint> balanceChart) {
		this.balanceChart.setSimpleChart(balanceChart, dateRange);
	}

	@Override
	public void setAmount(String amount) {
		amountValue.setText(amount);
	}

	@Override
	public void setChange(Boolean isChangeNegative, String changePercent, String changeValue) {
		this.changePercent.setTextColor(isChangeNegative
				? ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed)
				: ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));

		this.changePercent.setText(changePercent);
		this.changeValue.setText(changeValue);
	}

	@Override
	public void setCurrency(PlatformCurrencyInfo selectedCurrency) {
		this.chartAssetView.setAsset(selectedCurrency);
	}

	@Override
	public void showChangeBaseCurrencyList(ArrayList<String> optionsList) {
		if (getActivity() != null) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.currency), optionsList, -1);
			fragment.setListener((position, text) -> presenter.onChangeBaseCurrencySelected(text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
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
		if (presenter != null) {
			presenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null) {
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
		}
	}
}