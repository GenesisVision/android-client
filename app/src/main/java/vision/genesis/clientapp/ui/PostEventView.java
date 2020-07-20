package vision.genesis.clientapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.PostAssetDetailsWithPrices;
import io.swagger.client.model.PostTag;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostEventView extends RelativeLayout
{
	@BindView(R.id.action)
	public SimpleDraweeView action;

	@BindView(R.id.subject)
	public SimpleDraweeView subject;

	@BindView(R.id.event_text)
	public TextView text;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.group_change)
	public ViewGroup changeGroup;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.change_background)
	public View changeBackground;

	private Unbinder unbinder;

	private PostTag tag;

	public PostEventView(Context context) {
		super(context);
		initView();
	}

	public PostEventView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PostEventView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_post_event, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(v -> {
			if (tag != null) {
			}
		});
	}

	public void setPostTag(PostTag tag) {
		this.tag = tag;

		this.action.setImageURI(tag.getEvent().getLogoUrl());
		PostAssetDetailsWithPrices details = tag.getAssetDetails();
		if (details != null) {
			if (details.getLogoUrl() == null || details.getLogoUrl().isEmpty()) {
				GenericDraweeHierarchy hierarchy = subject.getHierarchy();
				hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(details.getColor())));
				subject.setHierarchy(hierarchy);
				subject.setImageURI("");
			}
			else {
				subject.setImageURI(ImageUtils.getImageUri(details.getLogoUrl()));
			}
		}

		this.text.setText(tag.getEvent().getTitle());

		if (tag.getEvent().getAmount() != null && tag.getEvent().getCurrency() != null) {
			this.value.setText(StringFormatUtil.getValueString(tag.getEvent().getAmount(), tag.getEvent().getCurrency().getValue()));
			this.value.setVisibility(View.VISIBLE);
		}
		else {
			this.value.setVisibility(View.GONE);
		}

		if (tag.getEvent().getPercent() != null) {
			this.changeGroup.setVisibility(View.VISIBLE);
			this.change.setText(StringFormatUtil.getPercentString(tag.getEvent().getPercent()));

			int colorAttr;
			switch (tag.getEvent().getChangeState()) {
				case INCREASED:
					colorAttr = R.attr.colorGreen;
					break;
				case DECREASED:
					colorAttr = R.attr.colorRed;
					break;
				case NOTCHANGED:
				default:
					colorAttr = R.attr.colorTextPrimary;
					break;
			}

			this.value.setTextColor(ThemeUtil.getColorByAttrId(getContext(), colorAttr));
			this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(), colorAttr));
			ViewCompat.setBackgroundTintList(this.changeBackground, ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), colorAttr)));
		}
		else {
			this.changeGroup.setVisibility(View.GONE);
		}
	}
}
