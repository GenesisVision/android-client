package vision.genesis.clientapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Locale;

import io.swagger.client.model.InvestmentsFilter;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/6/18.
 */

public class FilterSortingOption
{
	public static ArrayList<FilterSortingOption> getOptions(Context context) {
		ArrayList<FilterSortingOption> options = new ArrayList<>();
		options.add(new FilterSortingOption(context, InvestmentsFilter.SortingEnum.BYPROFITDESC));
		options.add(new FilterSortingOption(context, InvestmentsFilter.SortingEnum.BYPROFITASC));
		options.add(new FilterSortingOption(context, InvestmentsFilter.SortingEnum.BYORDERSDESC));
		options.add(new FilterSortingOption(context, InvestmentsFilter.SortingEnum.BYORDERSASC));
		options.add(new FilterSortingOption(context, InvestmentsFilter.SortingEnum.BYRATINGDESC));
		options.add(new FilterSortingOption(context, InvestmentsFilter.SortingEnum.BYRATINGASC));
		return options;
	}

	public InvestmentsFilter.SortingEnum option;

	private Context context;

	private FilterSortingOption(Context context, InvestmentsFilter.SortingEnum option) {
		this.context = context;
		this.option = option;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof InvestmentsFilter.SortingEnum && obj.equals(this.option);
	}

	@Override
	public String toString() {
		switch (option) {
			case BYPROFITDESC:
				return String.format(Locale.getDefault(), "%s \u2193", context.getResources().getString(R.string.by_profit));
			case BYPROFITASC:
				return String.format(Locale.getDefault(), "%s \u2191", context.getResources().getString(R.string.by_profit));
			case BYRATINGDESC:
				return String.format(Locale.getDefault(), "%s \u2193", context.getResources().getString(R.string.by_rating));
			case BYRATINGASC:
				return String.format(Locale.getDefault(), "%s \u2191", context.getResources().getString(R.string.by_rating));
			case BYORDERSDESC:
				return String.format(Locale.getDefault(), "%s \u2193", context.getResources().getString(R.string.by_orders));
			case BYORDERSASC:
				return String.format(Locale.getDefault(), "%s \u2191", context.getResources().getString(R.string.by_orders));
			default:
				return "";
		}
	}
}
