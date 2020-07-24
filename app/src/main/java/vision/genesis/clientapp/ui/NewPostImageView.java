package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class NewPostImageView extends RelativeLayout
{
	public interface PostImageClickListener
	{
		void onPostImageClicked(ImageView image, String imageId);

		void onDeleteImageClicked(NewPostImageView image, String imageId);
	}

	@BindView(R.id.root)
	public View root;

	@BindView(R.id.image)
	public ImageView image;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.social_post_image_height)
	public int imageHeight;

	private Unbinder unbinder;

	private String imageId;

	private PostImageClickListener listener;

	public NewPostImageView(Context context) {
		super(context);
		initView();
	}

	public NewPostImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public NewPostImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_delete)
	public void onDeleteClicked() {
		if (listener != null) {
			listener.onDeleteImageClicked(this, imageId);
		}
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_new_post_image, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(v -> {
			if (imageId != null && listener != null) {
				listener.onPostImageClicked(image, imageId);
			}
		});
	}

	public void setData(String imageId) {
		this.imageId = imageId;

		Glide.with(getContext()).load(ImageUtils.getImageUriById(imageId)).listener(new RequestListener<Drawable>()
		{
			@Override
			public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
				return false;
			}

			@Override
			public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
				progressBar.setVisibility(View.GONE);
				return false;
			}
		}).into(image);
	}

	public void setListener(PostImageClickListener listener) {
		this.listener = listener;
	}
}
