package vision.genesis.clientapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/04/2019.
 */
public class SocialTagView extends RelativeLayout
{
	public interface Listener
	{
		void onRemoveTagClicked(String tag, UUID contentId);
	}

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.background)
	public View background;

	private Unbinder unbinder;

	private String tag;

	private UUID contentId;

	private Listener listener;

	public SocialTagView(Context context) {
		super(context);
		initView();
	}

	public SocialTagView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialTagView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_remove)
	public void onRemoveClicked() {
		if (listener != null) {
			listener.onRemoveTagClicked(tag, contentId);
		}
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_tag, this);

		unbinder = ButterKnife.bind(this);

		name.setTypeface(TypefaceUtil.semibold());
	}

	public void setTag(String tag, int tagColor, UUID contentId) {
		this.tag = tag;
		this.contentId = contentId;

		this.name.setText(tag);
		this.name.setTextColor(tagColor);
		ViewCompat.setBackgroundTintList(background, ColorStateList.valueOf(tagColor));
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public String getHashTag() {
		return tag;
	}
}
