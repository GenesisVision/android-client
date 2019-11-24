package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.FundInvestingDetailsList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

public class FundDashboardShortView extends RelativeLayout
{
	@BindView(R.id.fund_logo)
	public ProgramLogoView fundLogo;

	@BindView(R.id.fund_name)
	public TextView fundName;

	@BindView(R.id.owner_name)
	public TextView ownerName;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.delimiter)
	public View delimiter;

	private Unbinder unbinder;

	private FundInvestingDetailsList fund;

	private String baseCurrency;

	public FundDashboardShortView(Context context) {
		super(context);
		initView();
	}

	public FundDashboardShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FundDashboardShortView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_fund_dashboard_short, this);

		unbinder = ButterKnife.bind(this);

		fundName.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(v -> {
			if (fund != null) {
				FundDetailsModel fundDetailsModel = new FundDetailsModel(fund.getId(),
						fund.getLogo(),
						fund.getColor(),
						fund.getTitle(),
						fund.getOwner().getUsername(),
						fund.getPersonalDetails() != null ?
								fund.getPersonalDetails().isIsFavorite()
								: false,
//TODO:
//							fund.getPersonalDetails() != null ?
//									fund.getPersonalDetails().isHasNotifications()
//									: false);
						false);
				EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
			}
		});
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}

	public void setData(FundInvestingDetailsList fund, String baseCurrency) {
		this.fund = fund;
		this.baseCurrency = baseCurrency;

		this.fundLogo.setImage(fund.getLogo(), fund.getColor(), 50, 50);
		this.fundLogo.hideLevel();
		this.fundName.setText(fund.getTitle());
		this.ownerName.setText(fund.getOwner().getUsername());

//		double value = Math.random() * 100;
		double value = fund.getPersonalDetails().getValue();
//		double change = Math.random() * 10 - 5;

		this.value.setText(StringFormatUtil.getValueString(value, baseCurrency));
//		updateChangeText(value, change);
	}

	private void updateChangeText(double value, double profit) {
		double profitPercent = Math.abs(profit / (value - profit) * 100);
		String sign = profit > 0 ? "+" : "";
		this.change.setText(String.format(Locale.getDefault(), "%s%s (%s%%)",
				sign,
				StringFormatUtil.getValueString(profit, baseCurrency),
				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profit > 0
						? R.attr.colorGreen
						: profit < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}
}
