package vision.genesis.clientapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.mikephil.charting.data.Entry;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * GenesisVision
 * Created by Vitaly on 1/30/18.
 */

public class InvestmentProgram implements Parcelable
{
	public static final Creator<InvestmentProgram> CREATOR = new Creator<InvestmentProgram>()
	{
		@Override
		public InvestmentProgram createFromParcel(Parcel in) {
			return new InvestmentProgram(in);
		}

		@Override
		public InvestmentProgram[] newArray(int size) {
			return new InvestmentProgram[size];
		}
	};

	@SerializedName("id")
	public UUID id = null;

	@SerializedName("logo")
	public String logo = null;

	@SerializedName("title")
	public String title = null;

	@SerializedName("description")
	public String description = null;

	@SerializedName("managerAccountId")
	public UUID managerAccountId = null;

	@SerializedName("managerName")
	public String managerName = null;

	@SerializedName("currency")
	public String currency = null;

	@SerializedName("tokenName")
	public String tokenName = null;

	@SerializedName("rating")
	public Double rating = null;

	@SerializedName("period")
	public Integer period = null;

	@SerializedName("ordersCount")
	public Integer ordersCount = null;

	@SerializedName("totalProfit")
	public Double totalProfit = null;

	@SerializedName("dateFrom")
	public DateTime dateFrom = null;

	@SerializedName("dateTo")
	public DateTime dateTo = null;

//	@SerializedName("lastPeriod")
//	public Period lastPeriod = null;

	@SerializedName("investMinAmount")
	public Double investMinAmount = null;

	@SerializedName("investMaxAmount")
	public Double investMaxAmount = null;

	@SerializedName("isEnabled")
	public Boolean isEnabled = null;

	@SerializedName("chartData")
	public List<Entry> chartData = new ArrayList<>();

	public InvestmentProgram(io.swagger.client.model.InvestmentProgram program) {
		this.id = program.getInvestment().getId();
		this.logo = program.getInvestment().getLogo();
		this.title = program.getInvestment().getTitle();
		this.description = program.getInvestment().getDescription();
		this.managerAccountId = program.getInvestment().getManagerAccountId();
		this.managerName = program.getAccount().getLogin();
		this.currency = program.getAccount().getCurrency();
		this.tokenName = program.getToken().getTokenName();
		this.rating = program.getInvestment().getRating();
		this.period = program.getInvestment().getPeriod();
		this.ordersCount = program.getInvestment().getOrdersCount();
		this.totalProfit = program.getInvestment().getTotalProfit();
		this.dateFrom = program.getInvestment().getDateFrom();
		this.dateTo = program.getInvestment().getDateTo();
//		this.lastPeriod = program.getInvestment().getLastPeriod();
		this.investMinAmount = program.getInvestment().getInvestMinAmount();
		this.investMaxAmount = program.getInvestment().getInvestMaxAmount();
		this.isEnabled = program.getInvestment().isIsEnabled();

		//TODO: parse from api model
		this.chartData = new ArrayList<>();
	}

	protected InvestmentProgram(Parcel in) {
		this.id = (UUID) in.readValue(UUID.class.getClassLoader());
		this.logo = (String) in.readValue(String.class.getClassLoader());
		this.title = (String) in.readValue(String.class.getClassLoader());
		this.description = (String) in.readValue(String.class.getClassLoader());
		this.managerAccountId = (UUID) in.readValue(UUID.class.getClassLoader());
		this.managerName = (String) in.readValue(String.class.getClassLoader());
		this.currency = (String) in.readValue(String.class.getClassLoader());
		this.tokenName = (String) in.readValue(String.class.getClassLoader());
		this.rating = (Double) in.readValue(Double.class.getClassLoader());
		this.period = (Integer) in.readValue(Integer.class.getClassLoader());
		this.ordersCount = (Integer) in.readValue(Integer.class.getClassLoader());
		this.totalProfit = (Double) in.readValue(Double.class.getClassLoader());
		this.dateFrom = (DateTime) in.readValue(DateTime.class.getClassLoader());
		this.dateTo = (DateTime) in.readValue(DateTime.class.getClassLoader());
//		this.lastPeriod = (Period) in.readValue(Period.class.getClassLoader());
		this.investMinAmount = (Double) in.readValue(Double.class.getClassLoader());
		this.investMaxAmount = (Double) in.readValue(Double.class.getClassLoader());
		this.isEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
		in.readTypedList(this.chartData, Entry.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(id);
		dest.writeValue(logo);
		dest.writeValue(title);
		dest.writeValue(description);
		dest.writeValue(managerAccountId);
		dest.writeValue(managerName);
		dest.writeValue(currency);
		dest.writeValue(tokenName);
		dest.writeValue(rating);
		dest.writeValue(period);
		dest.writeValue(ordersCount);
		dest.writeValue(totalProfit);
		dest.writeValue(dateFrom);
		dest.writeValue(dateTo);
//		dest.writeValue(lastPeriod);
		dest.writeValue(investMinAmount);
		dest.writeValue(investMaxAmount);
		dest.writeValue(isEnabled);
		dest.writeTypedList(chartData);
	}

	public String getRating() {
		return String.format(Locale.getDefault(), "%.0f", this.rating);
	}
}
