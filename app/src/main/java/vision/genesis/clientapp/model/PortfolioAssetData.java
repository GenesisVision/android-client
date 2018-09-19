package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/09/2018.
 */
public class PortfolioAssetData
{
	private int colorIndicator;

	private String name;

	private String value;

	private String changePercent;

	private int changePercentColor;

	private String changeValue;

	public PortfolioAssetData(int colorIndicator, String name, String value, String changePercent, int changePercentColor, String changeValue) {
		this.colorIndicator = colorIndicator;
		this.name = name;
		this.value = value;
		this.changePercent = changePercent;
		this.changePercentColor = changePercentColor;
		this.changeValue = changeValue;
	}

	public int getColorIndicator() {
		return colorIndicator;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getChangePercent() {
		return changePercent;
	}

	public int getChangePercentColor() {
		return changePercentColor;
	}

	public String getChangeValue() {
		return changeValue;
	}
}
