package vision.genesis.clientapp.model.events;

import org.joda.time.DateTime;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/07/2018.
 */
public class OnCreateProgramThirdStepPassedEvent
{
	private final Integer period;

	private final DateTime startDate;

	private final Double deposit;

	private final Double successFee;

	private final Double managementFee;

	private final String tokenSymbol;

	private final String tokenName;

	public OnCreateProgramThirdStepPassedEvent(Integer period, DateTime startDate, Double deposit, Double successFee, Double managementFee, String tokenSymbol, String tokenName) {
		this.period = period;
		this.startDate = startDate;
		this.deposit = deposit;
		this.successFee = successFee;
		this.managementFee = managementFee;
		this.tokenSymbol = tokenSymbol;
		this.tokenName = tokenName;
	}

	public Integer getPeriod() {
		return period;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public Double getDeposit() {
		return deposit;
	}

	public Double getSuccessFee() {
		return successFee;
	}

	public Double getManagementFee() {
		return managementFee;
	}

	public String getTokenSymbol() {
		return tokenSymbol;
	}

	public String getTokenName() {
		return tokenName;
	}
}
