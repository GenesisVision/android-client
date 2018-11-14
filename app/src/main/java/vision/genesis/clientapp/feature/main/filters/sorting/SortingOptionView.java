package vision.genesis.clientapp.feature.main.filters.sorting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/05/2018.
 */
public class SortingOptionView extends RelativeLayout
{
	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.check)
	public ImageView check;

	public SortingOptionView(Context context) {
		super(context);
		initView();
	}

	public SortingOptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SortingOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_sorting_option, this);

		ButterKnife.bind(this);
	}

	public String getText() {
		return name.getText().toString();
	}

	public void setText(String text) {
		name.setText(text);
	}

	public void setSelected(boolean selected) {
		check.setVisibility(selected ? View.VISIBLE : View.GONE);
	}
}
