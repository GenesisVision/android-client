package vision.genesis.clientapp.feature.main.programs_list.filter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.appyvet.materialrangebar.RangeBar;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class ProgramsFiltersActivity extends BaseSwipeBackActivity implements ProgramsFiltersView
{
	public static void start(Context context) {
		context.startActivity(new Intent(context.getApplicationContext(), ProgramsFiltersActivity.class));
//		context.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.level_range_bar)
	public RangeBar levelRangeBar;

	@BindView(R.id.avg_profit_range_bar)
	public RangeBar avgProfitRangeBar;

	@BindView(R.id.active_programs_only)
	public CheckBox activeProgramsOnly;

	@BindView(R.id.favorite_programs_only)
	public CheckBox favoriteProgramsOnly;

	@BindView(R.id.button_apply)
	public View applyButton;

	@BindView(R.id.button_clear)
	public View clearButton;

	@InjectPresenter
	ProgramsFiltersPresenter programsFiltersPresenter;

	@OnCheckedChanged(R.id.active_programs_only)
	public void onActiveProgramsOnlyChanged(CompoundButton button, boolean checked) {
		programsFiltersPresenter.onActiveProgramsOnlyChanged(checked);
	}

	@OnCheckedChanged(R.id.favorite_programs_only)
	public void onFavoriteProgramsOnlyChanged(CompoundButton button, boolean checked) {
		programsFiltersPresenter.onFavoriteProgramsOnlyChanged(checked);
	}

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
	}

	private void initRangeBars() {
		levelRangeBar.setOnRangeBarChangeListener((rangeBar, leftPinIndex, rightPinIndex, leftPinValue, rightPinValue) -> {
			programsFiltersPresenter.setLevel(leftPinValue, rightPinValue);
		});

		avgProfitRangeBar.setOnRangeBarChangeListener((rangeBar, leftPinIndex, rightPinIndex, leftPinValue, rightPinValue) -> {
			programsFiltersPresenter.setAvgProfit(leftPinValue, rightPinValue);
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
		if (filter.getLevelMin() != null && filter.getLevelMax() != null) {
			levelRangeBar.setRangePinsByValue(filter.getLevelMin(), filter.getLevelMax());
		}
		if (filter.getProfitAvgPercentMin() != null && filter.getProfitAvgPercentMax() != null) {
			avgProfitRangeBar.setRangePinsByValue(filter.getProfitAvgPercentMin(), filter.getProfitAvgPercentMax());
		}

		if (filter.isShowActivePrograms() != null)
			activeProgramsOnly.setChecked(filter.isShowActivePrograms());

		if (filter.isShowMyFavorites() != null)
			favoriteProgramsOnly.setChecked(filter.isShowMyFavorites());
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}