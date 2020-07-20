package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.MediaPost;
import io.swagger.client.model.PostImageResize;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnMediaPostClickedEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class MediaPostShortView extends RelativeLayout
{
	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.author_logo)
	public SimpleDraweeView authorLogo;

	@BindView(R.id.author_name)
	public TextView authorName;

	@BindView(R.id.date)
	public TextView date;

	private Unbinder unbinder;

	private MediaPost post;

	public MediaPostShortView(Context context) {
		super(context);
		initView();
	}

	public MediaPostShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MediaPostShortView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_media_post_short, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(v -> {
			if (post != null) {
				EventBus.getDefault().post(new OnMediaPostClickedEvent(post));
			}
		});
	}

	public void setPost(MediaPost post) {
		this.post = post;

		setImage(post);
		this.title.setText(post.getTitle());
		this.authorLogo.setImageURI(ImageUtils.getImageUri(post.getAuthorLogoUrl()));
		this.authorName.setText(post.getAuthor());
		this.date.setText(DateTimeUtil.formatEventDateTime(post.getDate()));
	}

	private void setImage(MediaPost post) {
		String logoUrl = post.getImage().getResizes().get(post.getImage().getResizes().size() - 1).getLogoUrl();
		for (PostImageResize resize : post.getImage().getResizes()) {
			if (resize.getQuality().equals(ImageQuality.HIGH)) {
				logoUrl = resize.getLogoUrl();
			}
		}
		this.image.setImageURI(ImageUtils.getImageUri(logoUrl));
	}
}
