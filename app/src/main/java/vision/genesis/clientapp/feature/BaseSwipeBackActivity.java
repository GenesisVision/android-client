package vision.genesis.clientapp.feature;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import permissions.dispatcher.PermissionRequest;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 2/19/18.
 */

public class BaseSwipeBackActivity extends MvpAppCompatActivity implements SwipeBackActivityBase
{
	protected Dialog messageDialog;

	private SwipeBackActivityHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new SwipeBackActivityHelper(this);
		mHelper.onActivityCreate();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate();
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v == null && mHelper != null)
			return mHelper.findViewById(id);
		return v;
	}

	@Override
	public SwipeBackLayout getSwipeBackLayout() {
		return mHelper.getSwipeBackLayout();
	}

	@Override
	public void setSwipeBackEnable(boolean enable) {
		getSwipeBackLayout().setEnableGesture(enable);
	}

	@Override
	public void scrollToFinishActivity() {
		Utils.convertActivityToTranslucent(this);
		getSwipeBackLayout().scrollToFinishActivity();
	}

	public void setEdgeTrackingMode(int trackingMode) {
		getSwipeBackLayout().setEdgeTrackingEnabled(trackingMode);
	}

	protected void showSnackbar(String message, View view) {
		Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
		((TextView) snack.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
		snack.show();
	}

	protected void showMessageDialog(String message) {
		if (messageDialog != null)
			messageDialog.cancel();
		messageDialog = showDialogMessage(message);
	}

	protected void showRationaleDialog(String message, final PermissionRequest request) {
		AlertDialog rationaleDialog = new AlertDialog.Builder(this)
				.setPositiveButton(getString(R.string.allow), (dialog, which) -> request.proceed())
				.setNegativeButton(getString(R.string.deny), (dialog, which) -> request.cancel())
				.setCancelable(false)
				.setMessage(message)
				.show();

		rationaleDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
		rationaleDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));
	}

	private Dialog showDialogMessage(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message);
		builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent));

		return dialog;
	}
}
