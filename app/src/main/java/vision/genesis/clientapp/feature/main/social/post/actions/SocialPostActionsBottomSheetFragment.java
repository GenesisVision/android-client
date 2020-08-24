package vision.genesis.clientapp.feature.main.social.post.actions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.Post;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.SocialPostType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/10/2018.
 */

public class SocialPostActionsBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface Listener
	{
		void onEditClicked(Post post, SocialPostType type);

		void onPinClicked(Post post);

		void onUnpinClicked(Post post);

		void onDeleteClicked(Post post);

		void onCopyLinkClicked(Post post);

		void onReportClicked(Post post);

		void onShareClicked(Post post);
	}

	@BindView(R.id.edit)
	public TextView edit;

	@BindView(R.id.pin)
	public TextView pin;

	@BindView(R.id.unpin)
	public TextView unpin;

	@BindView(R.id.delete)
	public TextView delete;

	@BindView(R.id.copy_link)
	public TextView copyLink;

	@BindView(R.id.report)
	public TextView report;

	@BindView(R.id.share)
	public TextView share;


	@BindView(R.id.group_progress_bar)
	public ViewGroup progressBarGroup;

	private Listener listener;

	private Post post;

	private SocialPostType type;

	private boolean isOwnPost;

	@OnClick(R.id.edit)
	public void onEditClicked() {
		if (listener != null && post != null) {
			listener.onEditClicked(post, type);
		}
		this.dismiss();
	}

	@OnClick(R.id.pin)
	public void onPinClicked() {
		if (listener != null && post != null) {
			listener.onPinClicked(post);
		}
		this.dismiss();
	}

	@OnClick(R.id.unpin)
	public void onUnpinClicked() {
		if (listener != null && post != null) {
			listener.onUnpinClicked(post);
		}
		this.dismiss();
	}

	@OnClick(R.id.delete)
	public void onDeleteClicked() {
		if (listener != null && post != null) {
			listener.onDeleteClicked(post);
		}
		this.dismiss();
	}

	@OnClick(R.id.copy_link)
	public void onCopyLinkClicked() {
		if (listener != null && post != null) {
			listener.onCopyLinkClicked(post);
		}
		this.dismiss();
	}

	@OnClick(R.id.report)
	public void onReportClicked() {
		if (listener != null && post != null) {
			listener.onReportClicked(post);
		}
		this.dismiss();
	}

	@OnClick(R.id.share)
	public void onShareClicked() {
		if (listener != null && post != null) {
			listener.onShareClicked(post);
		}
		this.dismiss();
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_social_post_actions, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void setData(Post post, SocialPostType socialPostType, boolean isOwnPost) {
		this.post = post;
		this.type = socialPostType;
		this.isOwnPost = isOwnPost;

		if (edit != null) {
			updateView();
		}
	}

	private void updateView() {
		if (post != null) {
			if (post.getPersonalDetails() != null) {
				this.edit.setVisibility(post.getPersonalDetails().isCanEdit() ? View.VISIBLE : View.GONE);
				this.pin.setVisibility(post.getPersonalDetails().isCanPin() && !post.isIsPinned() ? View.VISIBLE : View.GONE);
				this.unpin.setVisibility(post.getPersonalDetails().isCanPin() && post.isIsPinned() ? View.VISIBLE : View.GONE);
				this.delete.setVisibility(post.getPersonalDetails().isCanDelete() ? View.VISIBLE : View.GONE);
			}
			else {
				this.edit.setVisibility(View.GONE);
				this.pin.setVisibility(View.GONE);
				this.unpin.setVisibility(View.GONE);
				this.delete.setVisibility(View.GONE);
			}

			this.report.setVisibility(!isOwnPost ? View.VISIBLE : View.GONE);
		}
	}
}
