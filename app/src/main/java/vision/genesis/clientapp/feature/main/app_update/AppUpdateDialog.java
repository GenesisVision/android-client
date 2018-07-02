package vision.genesis.clientapp.feature.main.app_update;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.AppUpdateModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/05/2018.
 */
public class AppUpdateDialog extends AppCompatDialog
{
	private final AppUpdateModel model;

	@BindView(R.id.version)
	public TextView version;

	@BindView(R.id.button_update)
	public TextView updateButton;

	@Inject
	public AuthManager authManager;

	private boolean dontRemind;

	public AppUpdateDialog(Context context, AppUpdateModel model) {
		super(context);
		this.model = model;
		initLayout();
	}

	@OnClick(R.id.button_update)
	public void onUpdateClicked() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(String.format(Locale.getDefault(), "market://details?id=%s", BuildConfig.APPLICATION_ID)));
		getContext().startActivity(intent);

		this.dismiss();
	}

	@OnClick(R.id.button_cancel)
	public void onCancelClicked() {
		if (dontRemind)
			authManager.setIgnoredVersionUpdate(model.getVersionCode());
		this.dismiss();
	}

	@OnCheckedChanged(R.id.checkbox_dont_remind)
	public void onDontRemindCheckedChanged(CompoundButton button, boolean checked) {
		dontRemind = checked;
		updateButton.setEnabled(!dontRemind);
		updateButton.setTextColor(!dontRemind
				? ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent)
				: ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.grey400));
	}

	private void initLayout() {
		setContentView(R.layout.dialog_app_update);

		GenesisVisionApplication.getComponent().inject(this);

		ButterKnife.bind(this);

		version.setText(model.getVersionName());
	}
}
