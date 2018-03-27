package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/03/2018.
 */
public class ProfileDateView extends RelativeLayout
{
	@BindView(R.id.date)
	public ProfileDataView date;

	@BindView(R.id.calendar)
	public View calendar;

	public ProfileDateView(Context context) {
		super(context);
		initView();
	}

	public ProfileDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProfileDateView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_profile_date, this);

		ButterKnife.bind(this);

		setEditMode(false);
	}

	public void setEditMode(boolean editMode) {
		date.setEditMode(editMode);
		calendar.setVisibility(editMode ? View.VISIBLE : View.GONE);
	}

	public void setHint(String hint) {
		date.setHint(hint);
	}

	public void setText(String text) {
		date.setText(text);
	}
}