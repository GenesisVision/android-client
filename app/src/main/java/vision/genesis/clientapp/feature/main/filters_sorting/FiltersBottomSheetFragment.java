package vision.genesis.clientapp.feature.main.filters_sorting;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.MinMaxFilterModel;
import vision.genesis.clientapp.model.ProgramsFilter;
import vision.genesis.clientapp.utils.NumberFormatUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class FiltersBottomSheetFragment extends BottomSheetDialogFragment
{
	interface OnFiltersChangedListener
	{
		void onFiltersChanged(ProgramsFilter filter);
	}

	@BindView(R.id.text_filters)
	public TextView filtersText;

	@BindView(R.id.level)
	public FilterOptionView level;

	@BindView(R.id.avg_profit)
	public FilterOptionView avgProfit;

	@BindView(R.id.total_profit)
	public FilterOptionView totalProfit;

	@BindView(R.id.balance)
	public FilterOptionView balance;

	@BindView(R.id.active_programs_only)
	public FilterSwitchView activeProgramsOnly;

	@BindView(R.id.available_to_invest_only)
	public FilterSwitchView availableToInvestProgramsOnly;

	@BindView(R.id.button_apply)
	public ViewGroup applyButton;

	@BindView(R.id.button_close)
	public ViewGroup closeButton;

	@BindInt(R.integer.level_min)
	public int defaultLevelMinLevel;

	@BindInt(R.integer.level_max)
	public int defaultLevelMaxLevel;

	private FragmentActivity activity;

	private OnFiltersChangedListener listener;

	private ProgramsFilter filter;

	private Integer minLevel;

	private Integer maxLevel;

	private Integer minAvgProfit;

	private Integer maxAvgProfit;

	private Integer minTotalProfit;

	private Integer maxTotalProfit;

	private Integer minBalance;

	private Integer maxBalance;

	private Boolean activeOnly;

	private Boolean availableToInvestOnly;

//	@OnCheckedChanged(R.id.active_programs_only)
//	public void onActiveProgramsOnlyChanged(CompoundButton button, boolean checked) {
//		onActiveProgramsOnlyChanged(checked);
//	}

	@OnClick(R.id.button_reset)
	public void onResetClicked() {
		resetControlsValues();
		updateLevel(null, null);
		updateAvgProfit(null, null);
		updateTotalProfit(null, null);
		updateBalance(null, null);
		onActiveProgramsOnlyChanged(null);
		onAvailableToInvestOnlyChanged(null);
	}

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		if (listener != null) {
			saveChanges();
			listener.onFiltersChanged(filter);
			this.dismiss();
		}
	}

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		this.dismiss();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null)
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_filters, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		initFilterOptions();

		if (filter != null)
			setFilterData(filter);
	}

	public void setCurrentFilter(ProgramsFilter filter) {
		this.filter = filter;

		minLevel = filter.getLevelMin();
		maxLevel = filter.getLevelMax();
		minAvgProfit = NumberFormatUtil.doubleToInteger(filter.getProfitAvgMin());
		maxAvgProfit = NumberFormatUtil.doubleToInteger(filter.getProfitAvgMax());
//		minTotalProfit = NumberFormatUtil.doubleToInteger(filter.getProfitTotalMin());
//		maxTotalProfit = NumberFormatUtil.doubleToInteger(filter.getProfitTotalMax());
//		minBalance = NumberFormatUtil.doubleToInteger(filter.getBalanceUsdMin());
//		maxBalance = NumberFormatUtil.doubleToInteger(filter.getBalanceUsdMax());
//		activeOnly = filter.isShowActivePrograms();

		if (activeProgramsOnly != null)
			setFilterData(filter);
	}

	public void setActivity(FragmentActivity activity) {
		this.activity = activity;
	}

	public void setListener(OnFiltersChangedListener listener) {
		this.listener = listener;
	}

	private void setFonts() {
		filtersText.setTypeface(TypefaceUtil.bold());
	}

	private void initFilterOptions() {
		level.setName(getString(R.string.level));
		avgProfit.setName(getString(R.string.average_profit));
		totalProfit.setName(getString(R.string.total_profit));
		balance.setName(getString(R.string.balance));

		level.setOnClickListener(v -> {
			if (activity != null) {
				FilterMinMaxBottomSheetFragment bottomSheetDialog = new FilterMinMaxBottomSheetFragment();
				bottomSheetDialog.show(activity.getSupportFragmentManager(), bottomSheetDialog.getTag());
				MinMaxFilterModel model = new MinMaxFilterModel(getString(R.string.level),
						getString(R.string.min_level),
						getString(R.string.max_level),
						minLevel,
						maxLevel,
						defaultLevelMinLevel,
						defaultLevelMaxLevel
				);
				bottomSheetDialog.setData(model);
				bottomSheetDialog.setListener(this::updateLevel);
			}
		});

		avgProfit.setOnClickListener(v -> {
			if (activity != null) {
				FilterMinMaxBottomSheetFragment bottomSheetDialog = new FilterMinMaxBottomSheetFragment();
				bottomSheetDialog.show(activity.getSupportFragmentManager(), bottomSheetDialog.getTag());
				MinMaxFilterModel model = new MinMaxFilterModel(getString(R.string.average_profit),
						getString(R.string.min_avg_profit),
						getString(R.string.max_avg_profit),
						minAvgProfit,
						maxAvgProfit,
						null,
						null
				);
				bottomSheetDialog.setData(model);
				bottomSheetDialog.setListener(this::updateAvgProfit);
			}
		});

		totalProfit.setOnClickListener(v -> {
			if (activity != null) {
				FilterMinMaxBottomSheetFragment bottomSheetDialog = new FilterMinMaxBottomSheetFragment();
				bottomSheetDialog.show(activity.getSupportFragmentManager(), bottomSheetDialog.getTag());
				MinMaxFilterModel model = new MinMaxFilterModel(getString(R.string.total_profit),
						getString(R.string.min_total_profit),
						getString(R.string.max_total_profit),
						minTotalProfit,
						maxTotalProfit,
						null,
						null
				);
				bottomSheetDialog.setData(model);
				bottomSheetDialog.setListener(this::updateTotalProfit);
			}
		});

		balance.setOnClickListener(v -> {
			if (activity != null) {
				FilterMinMaxBottomSheetFragment bottomSheetDialog = new FilterMinMaxBottomSheetFragment();
				bottomSheetDialog.show(activity.getSupportFragmentManager(), bottomSheetDialog.getTag());
				MinMaxFilterModel model = new MinMaxFilterModel(getString(R.string.balance),
						getString(R.string.min_balance),
						getString(R.string.max_balance),
						minBalance,
						maxBalance,
						null,
						null
				);
				bottomSheetDialog.setData(model);
				bottomSheetDialog.setListener(this::updateBalance);
			}
		});

		activeProgramsOnly.setName(getString(R.string.active_programs_only));
		activeProgramsOnly.setListener(this::onActiveProgramsOnlyChanged);

		availableToInvestProgramsOnly.setName(getString(R.string.available_to_invest_only));
		availableToInvestProgramsOnly.setListener(this::onAvailableToInvestOnlyChanged);
	}

	private void updateLevel(Integer minLevel, Integer maxLevel) {
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;

		setRangeFilterValue(level, minLevel, maxLevel);
		updateButtons();
	}

	private void updateAvgProfit(Integer minAvgProfit, Integer maxAvgProfit) {
		this.minAvgProfit = minAvgProfit;
		this.maxAvgProfit = maxAvgProfit;

		setRangeFilterValue(avgProfit, minAvgProfit, maxAvgProfit);
		updateButtons();
	}

	private void updateTotalProfit(Integer minTotalProfit, Integer maxTotalProfit) {
		this.minTotalProfit = minTotalProfit;
		this.maxTotalProfit = maxTotalProfit;

		setRangeFilterValue(totalProfit, minTotalProfit, maxTotalProfit);
		updateButtons();
	}

	private void updateBalance(Integer minBalance, Integer maxBalance) {
		this.minBalance = minBalance;
		this.maxBalance = maxBalance;

		setRangeFilterValue(balance, minBalance, maxBalance);
		updateButtons();
	}

	private void setRangeFilterValue(FilterOptionView filterOptionView, Integer minValue, Integer maxValue) {
		String from = "";
		String to = "";

		if (minValue != null)
			from = String.format(Locale.getDefault(), "%s %s",
					getString(R.string.from),
					StringFormatUtil.formatAmount(minValue, 0, 4));

		if (maxValue != null)
			to = String.format(Locale.getDefault(), " %s %s",
					getString(R.string.to),
					StringFormatUtil.formatAmount(maxValue, 0, 4));

		filterOptionView.setValue(!from.isEmpty() || !to.isEmpty()
				? String.format(Locale.getDefault(), "%s %s", from, to)
				: getString(R.string.any));
	}

	private void resetRangeFilterValue(FilterOptionView filterOptionView) {
		filterOptionView.setValue(getString(R.string.any));
	}

	private void setFilterData(ProgramsFilter filter) {
		setRangeFilterValue(level, filter.getLevelMin(), filter.getLevelMax());
		setRangeFilterValue(avgProfit,
				NumberFormatUtil.doubleToInteger(filter.getProfitAvgMin()),
				NumberFormatUtil.doubleToInteger(filter.getProfitAvgMax()));
//		setRangeFilterValue(totalProfit,
//				NumberFormatUtil.doubleToInteger(filter.getProfitTotalMin()),
//				NumberFormatUtil.doubleToInteger(filter.getProfitTotalMax()));
//		setRangeFilterValue(balance,
//				NumberFormatUtil.doubleToInteger(filter.getBalanceUsdMin()),
//				NumberFormatUtil.doubleToInteger(filter.getBalanceUsdMax()));

//		if (filter.isShowActivePrograms() != null)
//			activeProgramsOnly.setChecked(filter.isShowActivePrograms());

//		if (filter.isShowAvailable() != null)
//		availableToInvestProgramsOnly.setChecked(filter.isShowAvailable());
	}

	private void resetControlsValues() {
		resetRangeFilterValue(level);
		resetRangeFilterValue(avgProfit);
		resetRangeFilterValue(totalProfit);
		resetRangeFilterValue(balance);
		activeProgramsOnly.setChecked(false);
		availableToInvestProgramsOnly.setChecked(false);
	}

	private void onActiveProgramsOnlyChanged(Boolean checked) {
		activeOnly = checked;
		updateButtons();
	}

	private void onAvailableToInvestOnlyChanged(Boolean checked) {
		availableToInvestOnly = checked;
		updateButtons();
	}

	private void saveChanges() {
		filter.setLevelMin(minLevel);
		filter.setLevelMax(maxLevel);
		filter.setProfitAvgMin(NumberFormatUtil.integerToDouble(minAvgProfit));
		filter.setProfitAvgMax(NumberFormatUtil.integerToDouble(maxAvgProfit));
//		filter.setProfitTotalMin(NumberFormatUtil.integerToDouble(minTotalProfit));
//		filter.setProfitTotalMax(NumberFormatUtil.integerToDouble(maxTotalProfit));
//		filter.setBalanceUsdMin(NumberFormatUtil.integerToDouble(minBalance));
//		filter.setBalanceUsdMax(NumberFormatUtil.integerToDouble(maxBalance));
//		filter.setShowActivePrograms(activeOnly);
//		filter.setShowAvailableToInvestPrograms(availableToInvestOnly);
	}

	private void updateButtons() {
		boolean isFilterChanged = !valuesAreEqual(filter.getLevelMin(), minLevel, 1)
				|| !valuesAreEqual(filter.getLevelMax(), maxLevel, 7)
				|| !valuesAreEqual(NumberFormatUtil.doubleToInteger(filter.getProfitAvgMin()), minAvgProfit, null)
				|| !valuesAreEqual(NumberFormatUtil.doubleToInteger(filter.getProfitAvgMax()), maxAvgProfit, null);
//				|| !valuesAreEqual(NumberFormatUtil.doubleToInteger(filter.getProfitTotalMin()), minTotalProfit, null)
//				|| !valuesAreEqual(NumberFormatUtil.doubleToInteger(filter.getProfitTotalMax()), maxTotalProfit, null)
//				|| !valuesAreEqual(NumberFormatUtil.doubleToInteger(filter.getBalanceUsdMin()), minBalance, null)
//				|| !valuesAreEqual(NumberFormatUtil.doubleToInteger(filter.getBalanceUsdMax()), maxBalance, null)
//				|| !valuesAreEqual(filter.isShowActivePrograms(), activeOnly, false);
//				|| !valuesAreEqual(filter.isShowAvailableToInvestPrograms(), availableToInvestOnly, false);
		applyButton.setVisibility(isFilterChanged ? View.VISIBLE : View.GONE);
		closeButton.setVisibility(!isFilterChanged ? View.VISIBLE : View.GONE);
	}

	private boolean valuesAreEqual(Object v1, Object v2, Object defaultValue) {
		return (v1 == null && (v2 == null || v2.equals(defaultValue))
				|| (v2 == null && v1.equals(defaultValue)
				|| (v2 != null && v2.equals(v1))));
	}
}
