package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/05/2018.
 */
public class MinMaxFilterModel
{
	private final String filterName;

	private final String minValueLabel;

	private final String maxValueLabel;

	private final Integer currentMinValue;

	private final Integer currentMaxValue;

	private final Integer minValue;

	private final Integer maxValue;

	public MinMaxFilterModel(String filterName,
	                         String minValueLabel,
	                         String maxValueLabel,
	                         Integer currentMinValue,
	                         Integer currentMaxValue,
	                         Integer minValue,
	                         Integer maxValue) {
		this.filterName = filterName;
		this.minValueLabel = minValueLabel;
		this.maxValueLabel = maxValueLabel;
		this.currentMinValue = currentMinValue;
		this.currentMaxValue = currentMaxValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public String getFilterName() {
		return filterName;
	}

	public String getMinValueLabel() {
		return minValueLabel;
	}

	public String getMaxValueLabel() {
		return maxValueLabel;
	}

	public Integer getCurrentMinValue() {
		return currentMinValue;
	}

	public Integer getCurrentMaxValue() {
		return currentMaxValue;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public Integer getMaxValue() {
		return maxValue;
	}
}
