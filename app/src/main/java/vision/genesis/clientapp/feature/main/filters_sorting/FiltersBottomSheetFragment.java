package vision.genesis.clientapp.feature.main.filters_sorting;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.appyvet.materialrangebar.RangeBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class FiltersBottomSheetFragment extends BottomSheetDialogFragment
{
	interface OnFiltersChangedListener
	{
		void onFiltersChanged(InvestmentProgramsFilter filter);
	}

	@BindView(R.id.text_filters)
	public TextView filtersText;

	@BindView(R.id.level_range_bar)
	public RangeBar levelRangeBar;

	@BindView(R.id.avg_profit_range_bar)
	public RangeBar avgProfitRangeBar;

	@BindView(R.id.active_programs_only)
	public CheckBox activeProgramsOnly;

	@BindView(R.id.button_apply)
	public ViewGroup applyButton;

	@BindView(R.id.button_close)
	public ViewGroup closeButton;

	private OnFiltersChangedListener listener;

	private InvestmentProgramsFilter filter;

	private Integer minLevel;

	private Integer maxLevel;

	private Integer minAvgProfit;

	private Integer maxAvgProfit;

	private Boolean activeOnly;

	@OnCheckedChanged(R.id.active_programs_only)
	public void onActiveProgramsOnlyChanged(CompoundButton button, boolean checked) {
		onActiveProgramsOnlyChanged(checked);
	}

	@OnClick(R.id.button_reset)
	public void onResetClicked() {
		resetControlsValues();
		setLevel(null, null);
		setAvgProfit(null, null);
		onActiveProgramsOnlyChanged(null);
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
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_filters, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		initRangeBars();

		if (filter != null)
			setFilterData(filter);
	}

	public void setCurrentFilter(InvestmentProgramsFilter filter) {
		this.filter = filter;

		minLevel = filter.getLevelMin();
		maxLevel = filter.getLevelMax();
		minAvgProfit = filter.getProfitAvgPercentMin();
		maxAvgProfit = filter.getProfitAvgPercentMax();
		activeOnly = filter.isShowActivePrograms();

		if (activeProgramsOnly != null)
			setFilterData(filter);
	}

	public void setListener(OnFiltersChangedListener listener) {
		this.listener = listener;
	}

	private void setFonts() {
		filtersText.setTypeface(TypefaceUtil.bold());
	}

	private void initRangeBars() {
		levelRangeBar.setOnRangeBarChangeListener((rangeBar, leftPinIndex, rightPinIndex, leftPinValue, rightPinValue) -> {
			setLevel(leftPinValue, rightPinValue);
		});

		avgProfitRangeBar.setOnRangeBarChangeListener((rangeBar, leftPinIndex, rightPinIndex, leftPinValue, rightPinValue) -> {
			setAvgProfit(leftPinValue, rightPinValue);
		});
	}

	private void setFilterData(InvestmentProgramsFilter filter) {
		if (filter.getLevelMin() != null && filter.getLevelMax() != null) {
			levelRangeBar.setRangePinsByValue(filter.getLevelMin(), filter.getLevelMax());
		}
		if (filter.getProfitAvgPercentMin() != null && filter.getProfitAvgPercentMax() != null) {
			avgProfitRangeBar.setRangePinsByValue(filter.getProfitAvgPercentMin(), filter.getProfitAvgPercentMax());
		}

		if (filter.isShowActivePrograms() != null)
			activeProgramsOnly.setChecked(filter.isShowActivePrograms());
	}

	private void resetControlsValues() {
		levelRangeBar.setRangePinsByValue(getResources().getInteger(R.integer.level_min), getResources().getInteger(R.integer.level_max));
		avgProfitRangeBar.setRangePinsByValue(getResources().getInteger(R.integer.avg_profit_min), getResources().getInteger(R.integer.avg_profit_max));
		activeProgramsOnly.setChecked(false);
	}

	private void setLevel(String minLevel, String maxLevel) {
		this.minLevel = minLevel != null ? Integer.parseInt(minLevel) : null;
		this.maxLevel = maxLevel != null ? Integer.parseInt(maxLevel) : null;
		updateButtons();
	}

	private void setAvgProfit(String leftValue, String rightValue) {
		this.minAvgProfit = leftValue != null ? Integer.parseInt(leftValue) : null;
		this.maxAvgProfit = rightValue != null ? Integer.parseInt(rightValue) : null;
		updateButtons();
	}

	private void onActiveProgramsOnlyChanged(Boolean checked) {
		activeOnly = checked;
		updateButtons();
	}

	private void saveChanges() {
		filter.setLevelMin(minLevel);
		filter.setLevelMax(maxLevel);
		filter.setProfitAvgPercentMin(minAvgProfit);
		filter.setProfitAvgPercentMax(maxAvgProfit);
		filter.setShowActivePrograms(activeOnly);
	}

	private void updateButtons() {
		boolean isFilterChanged = !valuesAreEqual(filter.getLevelMin(), minLevel, 1)
				|| !valuesAreEqual(filter.getLevelMax(), maxLevel, 7)
				|| !valuesAreEqual(filter.getProfitAvgPercentMin(), minAvgProfit, -100)
				|| !valuesAreEqual(filter.getProfitAvgPercentMax(), maxAvgProfit, 200)
				|| !valuesAreEqual(filter.isShowActivePrograms(), activeOnly, false);
		applyButton.setVisibility(isFilterChanged ? View.VISIBLE : View.GONE);
		closeButton.setVisibility(!isFilterChanged ? View.VISIBLE : View.GONE);
	}

	private boolean valuesAreEqual(Object v1, Object v2, Object defaultValue) {
		return (v1 == null && (v2 == null || v2.equals(defaultValue))
				|| (v2 == null && v1.equals(defaultValue)
				|| (v2 != null && v2.equals(v1))));
	}
}
