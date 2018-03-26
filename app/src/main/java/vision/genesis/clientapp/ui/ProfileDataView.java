package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/03/2018.
 */

public class ProfileDataView extends RelativeLayout
{
	@BindView(R.id.text_input_layout)
	public TextInputLayout textInputLayout;

	@BindView(R.id.edit_text)
	public TextInputEditText editText;

	public ProfileDataView(Context context) {
		super(context);
		initView();
	}

	public ProfileDataView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProfileDataView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_profile_data, this);

		ButterKnife.bind(this);

		setEditMode(false);
	}

	public void setEditMode(boolean editMode) {
		textInputLayout.setEnabled(editMode);
		if (editMode)
			editText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.grey300), PorterDuff.Mode.SRC_IN);
		else
			editText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.SRC_IN);

	}

	public void setHint(String hint) {
		textInputLayout.setHint(hint.toUpperCase());
	}

	public void setText(String text) {
		editText.setText(text);
	}
}
