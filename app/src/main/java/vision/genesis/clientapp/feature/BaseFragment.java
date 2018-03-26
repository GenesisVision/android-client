package vision.genesis.clientapp.feature;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import permissions.dispatcher.PermissionRequest;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.common.BackButtonListener;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class BaseFragment extends MvpAppCompatFragment implements BackButtonListener
{
	protected Dialog messageDialog;

	@Override
	public boolean onBackPressed() {
		return false;
	}

	public void showSnackbar(String message, View view) {
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
		AlertDialog rationaleDialog = new AlertDialog.Builder(getContext())
				.setPositiveButton(getString(R.string.allow), (dialog, which) -> request.proceed())
				.setNegativeButton(getString(R.string.deny), (dialog, which) -> request.cancel())
				.setCancelable(false)
				.setMessage(message)
				.show();

		rationaleDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
		rationaleDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
	}

	private Dialog showDialogMessage(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMessage(message);
		builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

		return dialog;
	}

	public void onShow() {

	}

	public void onHide() {

	}
}