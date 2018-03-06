package vision.genesis.clientapp.feature;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import vision.genesis.clientapp.ui.common.BackButtonListener;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class BaseFragment extends MvpAppCompatFragment implements BackButtonListener
{
	@Override
	public boolean onBackPressed() {
		return false;
	}

	public void showSnackbar(String message, View view) {
		Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
		((TextView) snack.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
		snack.show();
	}

	public void onShow() {

	}

	public void onHide() {

	}
}