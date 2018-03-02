package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 3/2/18.
 */

public class PeriodLeftView extends RelativeLayout
{
	@BindView(R.id.text_number)
	public TextView numberText;

	@BindView(R.id.text_period)
	public TextView periodText;

	@BindView(R.id.text_left)
	public TextView leftText;

	private DateTime dateTo;

	public PeriodLeftView(Context context) {
		super(context);
		initView();
	}

	public PeriodLeftView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PeriodLeftView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_period_left, this);

		ButterKnife.bind(this);
	}

	public void setDateTo(DateTime date) {
		dateTo = date;

		int daysLeft = DateTimeUtil.getDaysToDate(dateTo);
		int hoursLeft = DateTimeUtil.getHoursToDate(dateTo);
		int minutesLeft = DateTimeUtil.getMinutesToDate(dateTo);
		int secondsLeft = DateTimeUtil.getSecondsToDate(dateTo);

		if (daysLeft > 0) {
			numberText.setText(String.valueOf(daysLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.days, daysLeft, daysLeft, daysLeft));
		}
		else if (hoursLeft > 0) {
			numberText.setText(String.valueOf(hoursLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.hours, hoursLeft, hoursLeft, hoursLeft));
		}
		else if (minutesLeft > 0) {
			numberText.setText(String.valueOf(minutesLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.minutes, minutesLeft, minutesLeft, minutesLeft));
		}
		else if (secondsLeft > 0) {
			numberText.setText(String.valueOf(secondsLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.seconds, secondsLeft, secondsLeft, secondsLeft));
		}
		else {
			numberText.setText("0");
			periodText.setText(getResources().getQuantityString(R.plurals.seconds, secondsLeft, secondsLeft, secondsLeft));
		}
	}

	public void showLeft(boolean show) {
		leftText.setVisibility(show ? View.VISIBLE : View.GONE);
	}
}
