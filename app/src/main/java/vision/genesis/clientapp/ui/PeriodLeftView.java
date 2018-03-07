package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnPeriodLeftEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 3/2/18.
 */

public class PeriodLeftView extends RelativeLayout
{
	@BindView(R.id.group_period)
	public ViewGroup periodGroup;

	@BindView(R.id.text_number)
	public TextView numberText;

	@BindView(R.id.text_period)
	public TextView periodText;

	@BindView(R.id.text_left)
	public TextView leftText;

	@BindView(R.id.text_program_closed)
	public TextView programClosedText;

	private DateTime dateTo;

	private Subscription timeSubscription;

	private boolean programClosed = false;

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
		updatePeriodLeft();
		startTimer();
	}

	private void startTimer() {
		timeSubscription = Observable.interval(1, TimeUnit.SECONDS)
				.onBackpressureDrop()
				.retry()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(time -> updatePeriodLeft(),
						Throwable::printStackTrace, System.out::println);
	}

	private void updatePeriodLeft() {
		int daysLeft = DateTimeUtil.getDaysToDate(dateTo);
		if (daysLeft > 0) {
			numberText.setText(String.valueOf(daysLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.days, daysLeft, daysLeft, daysLeft));
			return;
		}

		int hoursLeft = DateTimeUtil.getHoursToDate(dateTo);
		if (hoursLeft > 0) {
			numberText.setText(String.valueOf(hoursLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.hours, hoursLeft, hoursLeft, hoursLeft));
			return;
		}

		int minutesLeft = DateTimeUtil.getMinutesToDate(dateTo);
		if (minutesLeft > 0) {
			numberText.setText(String.valueOf(minutesLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.minutes, minutesLeft, minutesLeft, minutesLeft));
			return;
		}

		int secondsLeft = DateTimeUtil.getSecondsToDate(dateTo);
		if (secondsLeft > 0) {
			numberText.setText(String.valueOf(secondsLeft));
			periodText.setText(getResources().getQuantityString(R.plurals.seconds, secondsLeft, secondsLeft, secondsLeft));
		}
		else {
			numberText.setText("0");
			periodText.setText(getResources().getQuantityString(R.plurals.seconds, secondsLeft, secondsLeft, secondsLeft));

			if (timeSubscription != null)
				timeSubscription.unsubscribe();

			if (!programClosed)
				EventBus.getDefault().post(new OnPeriodLeftEvent());
		}
	}

	public void showLeft(boolean show) {
		leftText.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void setProgramClosed(boolean closed) {
		this.programClosed = closed;
		programClosedText.setVisibility(closed ? View.VISIBLE : View.GONE);
		periodGroup.setVisibility(!closed ? View.VISIBLE : View.GONE);
	}
}
