package vision.genesis.clientapp.model;

import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.swagger.client.model.InvestmentProgram;
import vision.genesis.clientapp.ui.chart.ProfitSmallChartView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/05/2018.
 */
public class InvestmentProgramExtended
{
	public static List<InvestmentProgramExtended> extendInvestmentPrograms(List<InvestmentProgram> programs) {
		List<InvestmentProgramExtended> extendedPrograms = new ArrayList<>();

		for (InvestmentProgram program : programs) {
			extendedPrograms.add(new InvestmentProgramExtended(program));
		}
		return extendedPrograms;
	}

	private final InvestmentProgram data;

	private LineData equityChart;

	private String totalProfitTextFull;

	private String totalProfitText;

	private String totalProfitTextMod;

	private String avgProfitTextFull;

	private String avgProfitText;

	private String balanceTextFull;

	private String balanceText;

	private String balanceTextMod;

	private String investorsText;

	public InvestmentProgramExtended(InvestmentProgram program) {
		this.data = program;

		this.setEquityChart(ProfitSmallChartView.getPreparedEquityChart(program.getEquityChart()));

		this.setTotalProfitTextFull(StringFormatUtil.formatAmount(program.getProfitTotal(), 0, 18));

		ShortenedAmount totalProfitShortenedAmount = StringFormatUtil.getShortenedAmount(program.getProfitTotal());
		this.setTotalProfitText(String.format("%s", totalProfitShortenedAmount.amount));
		this.setTotalProfitTextMod(totalProfitShortenedAmount.modifier);

		this.setAvgProfitTextFull(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(program.getProfitAvgPercent())));
		this.setAvgProfitText(String.format(Locale.getDefault(), "%.0f", program.getProfitAvgPercent()));

		this.setBalanceTextFull(StringFormatUtil.formatAmount(program.getBalance(), 0, 18));

		ShortenedAmount balanceShortenedAmount = StringFormatUtil.getShortenedAmount(program.getBalance());
		this.setBalanceText(String.format("%s", balanceShortenedAmount.amount));
		this.setBalanceTextMod(balanceShortenedAmount.modifier);

		this.setInvestorsText(String.valueOf(program.getInvestorsCount()));
	}

	public LineData getEquityChart() {
		return equityChart;
	}

	public void setEquityChart(LineData equityChart) {
		this.equityChart = equityChart;
	}

	public InvestmentProgram getData() {
		return data;
	}

	public String getTotalProfitTextFull() {
		return totalProfitTextFull;
	}

	public void setTotalProfitTextFull(String totalProfitTextFull) {
		this.totalProfitTextFull = totalProfitTextFull;
	}

	public String getTotalProfitText() {
		return totalProfitText;
	}

	public void setTotalProfitText(String totalProfitText) {
		this.totalProfitText = totalProfitText;
	}

	public String getTotalProfitTextMod() {
		return totalProfitTextMod;
	}

	public void setTotalProfitTextMod(String totalProfitTextMod) {
		this.totalProfitTextMod = totalProfitTextMod;
	}

	public String getAvgProfitTextFull() {
		return avgProfitTextFull;
	}

	public void setAvgProfitTextFull(String avgProfitTextFull) {
		this.avgProfitTextFull = avgProfitTextFull;
	}

	public String getAvgProfitText() {
		return avgProfitText;
	}

	public void setAvgProfitText(String avgProfitText) {
		this.avgProfitText = avgProfitText;
	}

	public String getBalanceTextFull() {
		return balanceTextFull;
	}

	public void setBalanceTextFull(String balanceTextFull) {
		this.balanceTextFull = balanceTextFull;
	}

	public String getBalanceText() {
		return balanceText;
	}

	public void setBalanceText(String balanceText) {
		this.balanceText = balanceText;
	}

	public String getBalanceTextMod() {
		return balanceTextMod;
	}

	public void setBalanceTextMod(String balanceTextMod) {
		this.balanceTextMod = balanceTextMod;
	}

	public String getInvestorsText() {
		return investorsText;
	}

	public void setInvestorsText(String investorsText) {
		this.investorsText = investorsText;
	}
}
