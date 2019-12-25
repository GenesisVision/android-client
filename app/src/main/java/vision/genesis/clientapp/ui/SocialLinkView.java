package vision.genesis.clientapp.ui;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.SocialLinkViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/12/2019.
 */

public class SocialLinkView extends RelativeLayout
{
	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.text_input_layout)
	public TextInputLayout textInputLayout;

	@BindView(R.id.edit_text)
	public TextInputEditText editText;

	protected Unbinder unbinder;

	private SocialLinkViewModel model;

	private boolean focused = false;

	public SocialLinkView(Context context) {
		super(context);
		initView();
	}

	public SocialLinkView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialLinkView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	protected void initView() {
		inflate(getContext(), R.layout.view_social_link, this);

		unbinder = ButterKnife.bind(this);

		editText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (model == null) {
					return;
				}
				if (focused && !s.toString().startsWith(model.getUrl())) {
					editText.setText(model.getUrl());
					Selection.setSelection(editText.getText(), Objects.requireNonNull(editText.getText()).length());
				}
				if (s.toString().startsWith(model.getUrl())) {
					model.setValue(s.toString().substring(model.getUrl().length()));
				}
			}
		});

		editText.setOnFocusChangeListener((view, focused) -> {
			this.focused = focused;
			if (model == null) {
				return;
			}
			if (focused && Objects.requireNonNull(editText.getText()).length() == 0) {
				editText.setText(model.getUrl());
				new Handler().postDelayed(() -> Selection.setSelection(editText.getText(), editText.getText().length()), 100);
			}
			else if (!focused
					&& Objects.requireNonNull(editText.getText()).toString().startsWith(model.getUrl())
					&& editText.getText().length() == model.getUrl().length()) {
				editText.setText("");
			}
		});
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void setData(SocialLinkViewModel model) {
		this.model = model;
		this.logo.setImageURI(ImageUtils.getImageUri(model.getLogo()));
		this.textInputLayout.setHint(model.getName());
		if (model.getValue() != null && !model.getValue().isEmpty()) {
			this.editText.setText(model.getUrl().concat(model.getValue()));
		}
	}
}
