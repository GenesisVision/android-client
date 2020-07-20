package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.listeners.OnImageChangeListener;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class ImageViewerOverlayView extends RelativeLayout implements OnImageChangeListener
{
	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.title)
	public TextView title;

	private Unbinder unbinder;

	private int imagesCount;

	private StfalconImageViewer imageViewer;

	public ImageViewerOverlayView(Context context) {
		super(context);
		initView();
	}

	public ImageViewerOverlayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ImageViewerOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_back)
	public void onBackCLicked() {
		if (imageViewer != null) {
			imageViewer.close();
		}
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_image_viewer_overlay, this);

		unbinder = ButterKnife.bind(this);

		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			int statusBarHeight = getResources().getDimensionPixelSize(resourceId);

			RelativeLayout.LayoutParams lp = (LayoutParams) toolbar.getLayoutParams();
			lp.setMargins(0, statusBarHeight, 0, 0);
		}
	}

	public void setImagesCount(int imagesCount) {
		this.imagesCount = imagesCount;
	}

	@Override
	public void onImageChange(int position) {
		updateTitle(position);
	}

	private void updateTitle(int position) {
		this.title.setText(String.format(Locale.getDefault(), "%d of %d", position + 1, imagesCount));
	}

	public void setImageViewer(StfalconImageViewer imageViewer) {
		this.imageViewer = imageViewer;
		updateTitle(imageViewer.currentPosition());
	}
}
