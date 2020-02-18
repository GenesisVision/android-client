package vision.genesis.clientapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/02/2020.
 */

public class LevelView extends RelativeLayout
{
	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.background)
	public View background;

	private Unbinder unbinder;

	private int levelValue;

	private int colorResId;

	public LevelView(Context context) {
		super(context);
		initView();
	}

	public LevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public LevelView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_level, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setLevel(int level, int colorResId) {
		this.levelValue = level;
		this.colorResId = colorResId;
		this.level.setText(String.valueOf(level));

		setSelected(false);
	}

	public void setSelected(boolean selected) {
		if (selected) {
			this.level.setTextColor(ContextCompat.getColor(getContext(), colorResId));
			ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary)));
		}
		else {
			this.level.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
			ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(ContextCompat.getColor(getContext(), colorResId)));
		}
	}

	public int getLevel() {
		return levelValue;
	}
}
