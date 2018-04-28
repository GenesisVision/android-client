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
 * GenesisVision
 * Created by Vitaly on 3/2/18.
 */

public class PeriodLeftDetailsView extends PeriodLeftView
{
	@BindView(R.id.group_period)
	public ViewGroup periodGroup;

	@BindView(R.id.text_number)
	public TextView numberText;

	@BindView(R.id.period_progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.text_period)
	public TextView periodText;

	@BindView(R.id.text_label)
	public TextView labelText;

	@BindView(R.id.text_program_closed)
	public TextView programClosedText;

	public PeriodLeftDetailsView(Context context) {
		super(context);
	}

	public PeriodLeftDetailsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PeriodLeftDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void initView() {
		inflate(getContext(), R.layout.view_period_left_details, this);

		unbinder = ButterKnife.bind(this);

		writeLeft = false;

		setFonts();
	}

	protected void setFonts() {
		numberText.setTypeface(TypefaceUtil.bold());
		periodText.setTypeface(TypefaceUtil.bold());
		labelText.setTypeface(TypefaceUtil.bold());

		programClosedText.setTypeface(TypefaceUtil.bold());
	}
}
