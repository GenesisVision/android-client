package vision.genesis.clientapp.model;

import java.util.Arrays;
import java.util.List;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 09/07/2018.
 */
public class CreateProgramData
{
//	private List<BrokerTradeServer> brokers;

	private int maxTitleLength = 20;

	private int maxDescriptionLength = 1000;

	private int maxTokenNameLength = 20;

	private int maxTokenSymbolLength = 5;

	private int maxFeeSuccess = 100;

	private int maxFeeManagement = 500;

	private List<Integer> availablePeriods = Arrays.asList(1, 2, 3, 5, 7, 10, 14);

//	public List<BrokerTradeServer> getBrokers() {
//		return brokers;
//	}

//	public void setBrokers(List<BrokerTradeServer> brokers) {
//		this.brokers = brokers;
//	}

	public int getMaxTitleLength() {
		return maxTitleLength;
	}

	public void setMaxTitleLength(int maxTitleLength) {
		this.maxTitleLength = maxTitleLength;
	}

	public int getMaxDescriptionLength() {
		return maxDescriptionLength;
	}

	public void setMaxDescriptionLength(int maxDescriptionLength) {
		this.maxDescriptionLength = maxDescriptionLength;
	}

	public int getMaxTokenNameLength() {
		return maxTokenNameLength;
	}

	public void setMaxTokenNameLength(int maxTokenNameLength) {
		this.maxTokenNameLength = maxTokenNameLength;
	}

	public int getMaxTokenSymbolLength() {
		return maxTokenSymbolLength;
	}

	public void setMaxTokenSymbolLength(int maxTokenSymbolLength) {
		this.maxTokenSymbolLength = maxTokenSymbolLength;
	}

	public int getMaxFeeSuccess() {
		return maxFeeSuccess;
	}

	public void setMaxFeeSuccess(int maxFeeSuccess) {
		this.maxFeeSuccess = maxFeeSuccess;
	}

	public int getMaxFeeManagement() {
		return maxFeeManagement;
	}

	public void setMaxFeeManagement(int maxFeeManagement) {
		this.maxFeeManagement = maxFeeManagement;
	}

	public List<Integer> getAvailablePeriods() {
		return availablePeriods;
	}

	public void setAvailablePeriods(List<Integer> availablePeriods) {
		this.availablePeriods = availablePeriods;
	}
}
