package vision.genesis.clientapp.feature.main.program.filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.appyvet.materialrangebar.RangeBar;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.FilterSortingOption;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ProgramsFiltersActivity extends BaseSwipeBackActivity implements ProgramsFiltersView
{
	public static void startFrom(Activity activity) {
		activity.startActivity(new Intent(activity, ProgramsFiltersActivity.class));
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.level_range_bar)
	public RangeBar levelRangeBar;

	@BindView(R.id.avg_profit_range_bar)
	public RangeBar avgProfitRangeBar;

	@BindView(R.id.spinner_sorting)
	public Spinner sortingSpinner;

	@BindView(R.id.button_apply)
	public View applyButton;

	@BindView(R.id.button_clear)
	public View clearButton;

	@InjectPresenter
	ProgramsFiltersPresenter programsFiltersPresenter;

	private ArrayList<FilterSortingOption> sortingOptions;

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		programsFiltersPresenter.onApplyClicked();
	}

	@OnClick(R.id.button_clear)
	public void onClearClicked() {
		programsFiltersPresenter.onClearClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_programs_filters);

		ButterKnife.bind(this);

		initToolbar();
		initRangeBars();
		initSpinner();
	}

	private void initRangeBars() {
		levelRangeBar.setOnRangeBarChangeListener((rangeBar, leftPinIndex, rightPinIndex, leftPinValue, rightPinValue) -> {
			programsFiltersPresenter.setLevel(leftPinValue, rightPinValue);
		});

		avgProfitRangeBar.setOnRangeBarChangeListener((rangeBar, leftPinIndex, rightPinIndex, leftPinValue, rightPinValue) -> {
			programsFiltersPresenter.setAvgProfit(leftPinValue, rightPinValue);
		});
	}

	private void initSpinner() {
		sortingOptions = FilterSortingOption.getOptions(this);
		ArrayAdapter<FilterSortingOption> sortingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortingOptions);
		sortingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sortingSpinner.setAdapter(sortingAdapter);

		sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				programsFiltersPresenter.onSortingSelected(sortingOptions.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.filters));
		toolbar.addLeftButton(R.drawable.back_arrow, () -> programsFiltersPresenter.onBackClicked());
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setFilterData(InvestmentProgramsFilter filter) {
		int index = 0;
		for (FilterSortingOption sortingOption : sortingOptions) {
			if (sortingOption.option.equals(filter.getSorting())) {
				sortingSpinner.setSelection(index);
			}
			index++;
		}
		if (filter.getLevelMin() != null && filter.getLevelMax() != null) {
			levelRangeBar.setRangePinsByValue(filter.getLevelMin(), filter.getLevelMax());
		}
		if (filter.getProfitAvgMin() != null && filter.getProfitAvgMax() != null) {
			avgProfitRangeBar.setRangePinsByValue(filter.getProfitAvgMin(), filter.getProfitAvgMax());
		}
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
