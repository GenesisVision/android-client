package vision.genesis.clientapp.feature;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import permissions.dispatcher.PermissionRequest;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.common.BackButtonListener;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class BaseFragment extends MvpAppCompatFragment implements BackButtonListener
{
	private Dialog messageDialog;

	@Override
	public boolean onBackPressed() {
		return false;
	}

	public void showSnackbar(String message, View view) {
		Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
		((TextView) snack.getView().findViewById(com.google.android.material.R.id.snackbar_text))
				.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
		snack.getView().setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorCard));
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

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

		return dialog;
	}

	public void onShow() {

	}

	public void onHide() {

	}
}