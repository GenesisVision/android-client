package vision.genesis.clientapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Locale;

import io.swagger.client.model.InvestmentProgramsFilter;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/6/18.
 */

public class FilterSortingOption
{
	public static ArrayList<FilterSortingOption> getOptions(Context appContext) {
		ArrayList<FilterSortingOption> options = new ArrayList<>();
		options.add(new FilterSortingOption(appContext, InvestmentProgramsFilter.SortingEnum.BYPROFITDESC));
		options.add(new FilterSortingOption(appContext, InvestmentProgramsFilter.SortingEnum.BYPROFITASC));
		options.add(new FilterSortingOption(appContext, InvestmentProgramsFilter.SortingEnum.BYORDERSDESC));
		options.add(new FilterSortingOption(appContext, InvestmentProgramsFilter.SortingEnum.BYORDERSASC));
		options.add(new FilterSortingOption(appContext, InvestmentProgramsFilter.SortingEnum.BYLEVELDESC));
		options.add(new FilterSortingOption(appContext, InvestmentProgramsFilter.SortingEnum.BYLEVELASC));
		return options;
	}

	public InvestmentProgramsFilter.SortingEnum option;

	private Context appContext;

	private FilterSortingOption(Context appContext, InvestmentProgramsFilter.SortingEnum option) {
		this.appContext = appContext;
		this.option = option;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof InvestmentProgramsFilter.SortingEnum && obj.equals(this.option);
	}

	@Override
	public String toString() {
		switch (option) {
			case BYPROFITDESC:
				return String.format(Locale.getDefault(), "%s \u2193", appContext.getResources().getString(R.string.sort_by_profit));
			case BYPROFITASC:
				return String.format(Locale.getDefault(), "%s \u2191", appContext.getResources().getString(R.string.sort_by_profit));
			case BYLEVELDESC:
				return String.format(Locale.getDefault(), "%s \u2193", appContext.getResources().getString(R.string.sort_by_level));
			case BYLEVELASC:
				return String.format(Locale.getDefault(), "%s \u2191", appContext.getResources().getString(R.string.sort_by_level));
			case BYORDERSDESC:
				return String.format(Locale.getDefault(), "%s \u2193", appContext.getResources().getString(R.string.sort_by_orders));
			case BYORDERSASC:
				return String.format(Locale.getDefault(), "%s \u2191", appContext.getResources().getString(R.string.sort_by_orders));
			default:
				return "";
		}
	}
}
