package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

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

	@BindView(R.id.period_progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.text_period)
	public TextView periodText;

	@BindView(R.id.text_program_closed)
	public TextView programClosedText;

	protected DateTime dateFrom;

	protected DateTime dateTo;

	protected Subscription timeSubscription;

	protected boolean programClosed = false;

	protected boolean writeLeft = true;

	protected Unbinder unbinder;

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

	protected void initView() {
		inflate(getContext(), R.layout.view_period_left, this);

		unbinder = ButterKnife.bind(this);

		setFonts();
	}

	public void onDestroy() {
		if (timeSubscription != null)
			timeSubscription.unsubscribe();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	protected void setFonts() {
		periodText.setTypeface(TypefaceUtil.bold());
		programClosedText.setTypeface(TypefaceUtil.bold());
	}

	public void setDateTo(DateTime dateFrom, DateTime dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		initProgressBar();
		updatePeriodLeft();
//		startTimer();
	}

	protected void initProgressBar() {
		if (dateFrom != null && dateFrom.getYear() > 0 && dateFrom.isBefore(DateTime.now()))
			progressBar.setMax(Seconds.secondsBetween(dateFrom, dateTo).getSeconds());
	}

	protected void updateProgressBar() {
		if (dateFrom != null && dateFrom.getYear() > 0 && dateFrom.isBefore(DateTime.now()))
			progressBar.setProgress(Seconds.secondsBetween(dateFrom, DateTime.now()).getSeconds());
	}

	protected void startTimer() {
		timeSubscription = Observable.interval(1, TimeUnit.SECONDS)
				.onBackpressureDrop()
				.retry()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(time -> updatePeriodLeft(),
						Throwable::printStackTrace, System.out::println);
	}

	protected void updatePeriodLeft() {
		updateProgressBar();

		int daysLeft = DateTimeUtil.getDaysToDate(dateTo);
		if (daysLeft > 0) {
			numberText.setText(String.valueOf(daysLeft));
			setPeriodText(GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.days, daysLeft, daysLeft, daysLeft));
			return;
		}

		int hoursLeft = DateTimeUtil.getHoursToDate(dateTo);
		if (hoursLeft > 0) {
			numberText.setText(String.valueOf(hoursLeft));
			setPeriodText(GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.hours, hoursLeft, hoursLeft, hoursLeft));
			return;
		}

		int minutesLeft = DateTimeUtil.getMinutesToDate(dateTo);
		if (minutesLeft > 0) {
			numberText.setText(String.valueOf(minutesLeft));
			setPeriodText(GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.minutes, minutesLeft, minutesLeft, minutesLeft));
			return;
		}

		int secondsLeft = DateTimeUtil.getSecondsToDate(dateTo);
		if (secondsLeft > 0) {
			numberText.setText(String.valueOf(secondsLeft));
			setPeriodText(GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.seconds, secondsLeft, secondsLeft, secondsLeft));
		}
		else {
			numberText.setText("0");
			setPeriodText(GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.seconds, secondsLeft, secondsLeft, secondsLeft));

			if (timeSubscription != null)
				timeSubscription.unsubscribe();

//			if (!programClosed)
//				EventBus.getDefault().post(new OnPeriodLeftEvent());
		}
	}

	private void setPeriodText(String text) {
		if (writeLeft) {
			periodText.setText(String.format(Locale.getDefault(), "%s %s", text,
					GenesisVisionApplication.INSTANCE.getResources().getString(R.string.left)));
		}
		else {
			periodText.setText(text);
		}
	}

	public void setProgramClosed(boolean closed) {
		this.programClosed = closed;
		programClosedText.setVisibility(closed ? View.VISIBLE : View.GONE);
		periodGroup.setVisibility(!closed ? View.VISIBLE : View.GONE);
	}
}
