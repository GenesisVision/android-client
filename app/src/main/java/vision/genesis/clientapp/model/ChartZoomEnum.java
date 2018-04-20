package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/04/2018.
 */
public enum ChartZoomEnum
{
	ZOOM_5MIN("Min5"),
	ZOOM_1H("Hour1"),
	ZOOM_1D("Day1"),
	ZOOM_1W("Week1"),
	ZOOM_1M("Month1"),
	ZOOM_3M("Month3"),
	ZOOM_6M("Month6"),
	ZOOM_1Y("Year1"),
	ZOOM_ALL("All");

	public static ChartZoomEnum fromValue(String text) {
		for (ChartZoomEnum b : ChartZoomEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	ChartZoomEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
