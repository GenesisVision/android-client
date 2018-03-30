package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/03/2018.
 */

public class SpinnerView extends RelativeLayout
{
	@BindView(R.id.spinner)
	public Spinner spinner;

	@BindView(R.id.spinner_arrow)
	public View spinnerArrow;

	public SpinnerView(Context context) {
		super(context);
		initView();
	}

	public SpinnerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_spinner, this);

		ButterKnife.bind(this);
	}

	public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
		spinner.setOnItemSelectedListener(listener);
	}

	public void setData(List data) {
		ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner_dropdown, data);
		setAdapter(adapter);
	}

	public void setData(String[] data) {
		ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner_dropdown, data);
		setAdapter(adapter);
	}

	public void setData(String[] data, int itemLayoutId) {
		ArrayAdapter adapter = new ArrayAdapter<>(getContext(), itemLayoutId, data);
		setAdapter(adapter);
	}

	public void setSelection(int position) {
		spinner.setSelection(position);
	}

	public void setPrompt(String prompt) {
		spinner.setPrompt(prompt);
	}

	public void setEditMode(boolean editMode) {
		spinner.setEnabled(editMode);
		spinnerArrow.setVisibility(editMode ? View.VISIBLE : View.GONE);
	}

	private void setAdapter(ArrayAdapter adapter) {
		adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
		spinner.setAdapter(adapter);
	}
}
