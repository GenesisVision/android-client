package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnPostImageClickedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostImageView extends RelativeLayout
{
	@BindView(R.id.root)
	public View root;

	@BindView(R.id.image)
	public ImageView image;

	@BindView(R.id.screen)
	public View screen;

	@BindView(R.id.text)
	public TextView text;

	@BindDimen(R.dimen.social_post_image_height)
	public int imageHeight;

	private Unbinder unbinder;

	private String imageUrl;

	private int count;

	private int col;

	private int row;

	private UUID postId;

	private int position;

	public PostImageView(Context context) {
		super(context);
		initView();
	}

	public PostImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PostImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_post_image, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(v -> {
			if (imageUrl != null) {
				EventBus.getDefault().post(new OnPostImageClickedEvent(image, imageUrl, position, postId));
			}
		});
	}

	public void setData(String imageUrl, int position, int count, int col, int row, UUID postId) {
		this.imageUrl = imageUrl;
		this.position = position;
		this.count = count;
		this.col = col;
		this.row = row;
		this.postId = postId;

		Glide.with(getContext()).load(imageUrl).into(image);
		if (count > 0) {
			this.screen.setVisibility(View.VISIBLE);
			this.text.setVisibility(View.VISIBLE);
			this.text.setText(String.format(Locale.getDefault(), "+%d", count));
		}
	}

	public int getRow() {
		return row;
	}

	public void resize(int newWidth) {
		LayoutParams lp = (LayoutParams) this.image.getLayoutParams();

		lp.height = (int) (newWidth / 1.3f);
		if (lp.height > imageHeight) {
			lp.height = imageHeight;
		}
		lp.width = newWidth;
		this.image.setLayoutParams(lp);
	}
}
