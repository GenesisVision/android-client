package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class PeriodLeftShortView extends PeriodLeftView
{
	@BindView(R.id.group_period)
	public ViewGroup periodGroup;

	@BindView(R.id.period_progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.period_length)
	public TextView periodLength;

	@BindView(R.id.period_left)
	public TextView periodLeft;

	@BindView(R.id.text_program_closed)
	public TextView programClosedText;

	@BindView(R.id.text_no_period)
	public TextView noPeriodText;

	public PeriodLeftShortView(Context context) {
		super(context);
	}

	public PeriodLeftShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PeriodLeftShortView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void initView() {
		inflate(getContext(), R.layout.view_period_left_short, this);

		unbinder = ButterKnife.bind(this);

		writeLeft = true;

//		setFonts();
	}

	protected void setFonts() {
		periodLength.setTypeface(TypefaceUtil.semibold());
		periodLeft.setTypeface(TypefaceUtil.semibold());

		programClosedText.setTypeface(TypefaceUtil.bold());
	}
}
