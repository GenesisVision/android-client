package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/05/2018.
 */
public class InvestmentProgramExtended
{
//	public static List<InvestmentProgramExtended> extendInvestmentPrograms(List<InvestmentProgram> programs) {
//		List<InvestmentProgramExtended> extendedPrograms = new ArrayList<>();
//
//		for (InvestmentProgram program : programs) {
//			extendedPrograms.add(new InvestmentProgramExtended(program));
//		}
//		return extendedPrograms;
//	}
//
//	private final boolean hasFreeTokens;
//
//	private InvestmentProgram data;
//
//	private LineData equityChart;
//
//	private SpannableString totalProfitTextFull;
//
//	private String totalProfitText;
//
//	private String totalProfitTextMod;
//
//	private SpannableString avgProfitTextFull;
//
//	private String avgProfitText;
//
//	private SpannableString balanceTextFull;
//
//	private String balanceText;
//
//	private String balanceTextMod;
//
//	private String investorsText;
//
//	private SpannableString availableTokensText;
//
//	private String balanceCurrency;
//
//	public InvestmentProgramExtended(InvestmentProgram program) {
//		this.data = program;
//
//		this.setEquityChart(ProfitSmallChartView.getPreparedEquityChart(program.getEquityChart()));
//
//		this.setTotalProfitTextFull(
//				StringFormatUtil.getDecimalSpannable(StringFormatUtil.formatAmount(program.getProfitTotal(), 0, 8)));
//
//		this.setAvgProfitTextFull(StringFormatUtil.getDecimalSpannable(
//				String.format(Locale.US, "%s%%", StringFormatUtil.formatAmount(program.getProfitAvgPercent()))));
//
//		this.setBalanceTextFull(
//				StringFormatUtil.getDecimalSpannable(StringFormatUtil.formatAmount(program.getBalance(), 0, 8)));
//
//		this.setBalanceCurrency(program.getCurrency().toString());
//
//		this.setInvestorsText(String.valueOf(program.getInvestorsCount()));
//
//		this.setAvailableTokensText(StringFormatUtil.getDecimalSpannable(StringFormatUtil.formatAmount(program.getAvailableInvestment(), 0, 4)));
//		this.hasFreeTokens = program.getAvailableInvestment() > 0;
//	}
//
//	public InvestmentProgramExtended(InvestmentProgramDetails program) {
//
//		this.setTotalProfitTextFull(
//				StringFormatUtil.getDecimalSpannable(StringFormatUtil.formatAmount(program.getProfitTotal(), 0, 8)));
//
//		this.setAvgProfitTextFull(StringFormatUtil.getDecimalSpannable(
//				String.format(Locale.US, "%s%%", StringFormatUtil.formatAmount(program.getProfitAvgPercent()))));
//
//		this.setBalanceTextFull(
//				StringFormatUtil.getDecimalSpannable(StringFormatUtil.formatAmount(program.getBalance(), 0, 8)));
//
//		this.setBalanceCurrency(program.getCurrency().toString());
//
//		this.setInvestorsText(String.valueOf(program.getInvestorsCount()));
//
//		this.setAvailableTokensText(StringFormatUtil.getDecimalSpannable(StringFormatUtil.formatAmount(program.getAvailableInvestment(), 0, 4)));
//		this.hasFreeTokens = program.getAvailableInvestment() > 0;
//	}
//
//	public LineData getEquityChart() {
//		return equityChart;
//	}
//
//	public void setEquityChart(LineData equityChart) {
//		this.equityChart = equityChart;
//	}
//
//	public InvestmentProgram getData() {
//		return data;
//	}
//
//	public SpannableString getTotalProfitTextFull() {
//		return totalProfitTextFull;
//	}
//
//	public void setTotalProfitTextFull(SpannableString totalProfitTextFull) {
//		this.totalProfitTextFull = totalProfitTextFull;
//	}
//
//	public String getTotalProfitText() {
//		return totalProfitText;
//	}
//
//	public void setTotalProfitText(String totalProfitText) {
//		this.totalProfitText = totalProfitText;
//	}
//
//	public String getTotalProfitTextMod() {
//		return totalProfitTextMod;
//	}
//
//	public void setTotalProfitTextMod(String totalProfitTextMod) {
//		this.totalProfitTextMod = totalProfitTextMod;
//	}
//
//	public SpannableString getAvgProfitTextFull() {
//		return avgProfitTextFull;
//	}
//
//	public void setAvgProfitTextFull(SpannableString avgProfitTextFull) {
//		this.avgProfitTextFull = avgProfitTextFull;
//	}
//
//	public String getAvgProfitText() {
//		return avgProfitText;
//	}
//
//	public void setAvgProfitText(String avgProfitText) {
//		this.avgProfitText = avgProfitText;
//	}
//
//	public SpannableString getBalanceTextFull() {
//		return balanceTextFull;
//	}
//
//	public void setBalanceTextFull(SpannableString balanceTextFull) {
//		this.balanceTextFull = balanceTextFull;
//	}
//
//	public String getBalanceText() {
//		return balanceText;
//	}
//
//	public void setBalanceText(String balanceText) {
//		this.balanceText = balanceText;
//	}
//
//	public String getBalanceTextMod() {
//		return balanceTextMod;
//	}
//
//	public void setBalanceTextMod(String balanceTextMod) {
//		this.balanceTextMod = balanceTextMod;
//	}
//
//	public String getInvestorsText() {
//		return investorsText;
//	}
//
//	public void setInvestorsText(String investorsText) {
//		this.investorsText = investorsText;
//	}
//
//	public SpannableString getAvailableTokensText() {
//		return availableTokensText;
//	}
//
//	public void setAvailableTokensText(SpannableString availableTokensText) {
//		this.availableTokensText = availableTokensText;
//	}
//
//	public boolean isHasFreeTokens() {
//		return hasFreeTokens;
//	}
//
//	public String getBalanceCurrency() {
//		return balanceCurrency;
//	}
//
//	public void setBalanceCurrency(String balanceCurrency) {
//		this.balanceCurrency = balanceCurrency;
//	}
}
