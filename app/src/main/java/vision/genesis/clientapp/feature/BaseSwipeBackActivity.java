package vision.genesis.clientapp.feature;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * GenesisVision
 * Created by Vitaly on 2/19/18.
 */

public class BaseSwipeBackActivity extends MvpAppCompatActivity implements SwipeBackActivityBase
{
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
}
