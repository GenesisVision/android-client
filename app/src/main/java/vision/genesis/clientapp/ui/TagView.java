package vision.genesis.clientapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.Tag;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/04/2019.
 */
public class TagView extends RelativeLayout
{
	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.background)
	public View background;

	private Unbinder unbinder;

	public TagView(Context context) {
		super(context);
		initView();
	}

	public TagView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_tag, this);

		unbinder = ButterKnife.bind(this);

		name.setTypeface(TypefaceUtil.semibold());
	}

	public void setTag(Tag tag) {
		this.name.setText(tag.getName());

		int tagColor = Color.parseColor(tag.getColor());
		this.name.setTextColor(tagColor);
		ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(tagColor));
	}

	public void setDemo() {
		this.name.setText(getContext().getString(R.string.demo).toUpperCase());
		this.name.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
		ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending)));
	}
}
