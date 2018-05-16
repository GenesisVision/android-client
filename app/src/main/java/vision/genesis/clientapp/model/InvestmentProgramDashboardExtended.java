package vision.genesis.clientapp.model;

import com.github.mikephil.charting.data.LineData;

import io.swagger.client.model.InvestmentProgramDashboardInvestor;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/05/2018.
 */
public class InvestmentProgramDashboardExtended
{
	private final InvestmentProgramDashboardInvestor data;

	private LineData equityChart;

	private String tokens;

	private String tokensFiat;

	private String profitShort;

	private String profitFull;

	public InvestmentProgramDashboardExtended(InvestmentProgramDashboardInvestor program) {
		this.data = program;
	}

	public InvestmentProgramDashboardInvestor getData() {
		return data;
	}

	public LineData getEquityChart() {
		return equityChart;
	}

	public void setEquityChart(LineData equityChart) {
		this.equityChart = equityChart;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public String getTokensFiat() {
		return tokensFiat;
	}

	public void setTokensFiat(String tokensFiat) {
		this.tokensFiat = tokensFiat;
	}

	public String getProfitShort() {
		return profitShort;
	}

	public void setProfitShort(String profitShort) {
		this.profitShort = profitShort;
	}

	public String getProfitFull() {
		return profitFull;
	}

	public void setProfitFull(String profitFull) {
		this.profitFull = profitFull;
	}
}
