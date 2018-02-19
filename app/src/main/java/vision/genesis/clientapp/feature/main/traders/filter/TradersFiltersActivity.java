package vision.genesis.clientapp.feature.main.traders.filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentsFilter;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.FilterSortingOption;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class TradersFiltersActivity extends BaseSwipeBackActivity implements TradersFiltersView
{
	public static void startFrom(Activity activity) {
		activity.startActivity(new Intent(activity, TradersFiltersActivity.class));
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.spinner_sorting)
	public Spinner sortingSpinner;

	@BindView(R.id.edittext_max_from)
	public EditText maxFrom;

	@BindView(R.id.edittext_max_to)
	public EditText maxTo;

	@BindView(R.id.button_apply)
	public View applyButton;

	@BindView(R.id.button_clear)
	public View clearButton;

	@InjectPresenter
	TradersFiltersPresenter tradersFiltersPresenter;

	private ArrayList<FilterSortingOption> sortingOptions;

	@OnClick(R.id.button_apply)
	public void onApplyClicked() {
		tradersFiltersPresenter.onApplyClicked();
	}

	@OnClick(R.id.button_clear)
	public void onClearClicked() {
		tradersFiltersPresenter.onClearClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_traders_filters);

		ButterKnife.bind(this);

		initToolbar();

		initSpinner();
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
				tradersFiltersPresenter.onSortingSelected(sortingOptions.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.filters));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> tradersFiltersPresenter.onBackClicked());
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setFilterData(InvestmentsFilter filter) {
		int index = 0;
		for (FilterSortingOption sortingOption : sortingOptions) {
			if (sortingOption.option.equals(filter.getSorting())) {
				sortingSpinner.setSelection(index);
			}
			index++;
		}
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
