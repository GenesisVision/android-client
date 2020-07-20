package vision.genesis.clientapp.feature.main.social.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Locale;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.PostImageModel;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class PostImagesListAdapter extends ArrayAdapter<PostImageModel>
{
	private final LayoutInflater layoutInflater;

	public PostImagesListAdapter(Context context, List<PostImageModel> items) {
		super(context, 0, items);
		layoutInflater = LayoutInflater.from(context);
	}

	public PostImagesListAdapter(Context context) {
		super(context, 0);
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		View v;

		PostImageModel model = getItem(position);

		if (convertView == null) {
			v = layoutInflater.inflate(R.layout.view_post_image, parent, false);
		}
		else {
			v = convertView;
		}

		SimpleDraweeView image = v.findViewById(R.id.image);
		View screen = v.findViewById(R.id.screen);
		TextView text = v.findViewById(R.id.text);

		image.setImageURI(ImageUtils.getImageUri(model.getImageUrl()));
		if (model.getCount() > 0) {
			screen.setVisibility(View.VISIBLE);
			text.setVisibility(View.VISIBLE);
			text.setText(String.format(Locale.getDefault(), "+%d", model.getCount()));
		}

		v.setOnClickListener(view -> {
			if (model.getImageUrl() != null) {
//				EventBus.getDefault().post(new OnPostImageClickedEvent(model.getImageUrl(), model.getPosition(), postId));
			}
		});

		return v;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	public void appendItems(List<PostImageModel> newItems) {
		addAll(newItems);
		notifyDataSetChanged();
	}

	public void setItems(List<PostImageModel> moreItems) {
		clear();
		appendItems(moreItems);
	}
}
