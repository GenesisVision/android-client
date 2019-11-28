package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class AccountAgeView extends RelativeLayout
{
	private static final int MAX_DAYS = 365;

	@BindView(R.id.age_progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.age)
	public TextView age;

	public AccountAgeView(Context context) {
		super(context);
		initView();
	}

	public AccountAgeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AccountAgeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	protected void initView() {
		inflate(getContext(), R.layout.view_account_age, this);

		ButterKnife.bind(this);

		setFonts();

		progressBar.setMax(MAX_DAYS);
	}

	protected void setFonts() {
		age.setTypeface(TypefaceUtil.semibold());
	}

	public void setCreationDate(DateTime creationDate) {
		int days = Days.daysBetween(creationDate, DateTime.now()).getDays();
		String sign = days > MAX_DAYS ? "+" : "";
		this.progressBar.setProgress(days);
		this.age.setText(String.format(Locale.getDefault(), "%d%s %s", days, sign,
				GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.days, days, days, days)));
	}
}
