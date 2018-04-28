package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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

	@BindView(R.id.edit_icon)
	public View editIcon;

	private Unbinder unbinder;

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

	@OnClick(R.id.edit_icon)
	public void onEditIconClicked() {
		editText.requestFocus();
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_profile_data, this);

		unbinder = ButterKnife.bind(this);

		setEditMode(false);
	}

	public void setEditMode(boolean editMode) {
		textInputLayout.setEnabled(editMode);
		if (editMode) {
			editText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.grey300), PorterDuff.Mode.SRC_IN);
			editIcon.setVisibility(View.VISIBLE);
		}
		else {
			editText.getBackground().setColorFilter(ContextCompat.getColor(getContext(), android.R.color.transparent), PorterDuff.Mode.SRC_IN);
			editIcon.setVisibility(View.GONE);
		}
	}

	public void setHint(String hint) {
		textInputLayout.setHint(hint.toUpperCase());
	}

	public String getText() {
		return editText.getText().toString();
	}

	public void setText(String text) {
		editText.setText(text);
	}
}
