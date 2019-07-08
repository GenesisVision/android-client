package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class ProgramView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.program_logo)
	public ProgramLogoView programLogo;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	public ProgramView(Context context) {
		super(context);
		initView();
	}

	public ProgramView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProgramView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_program, this);

		ButterKnife.bind(this);

		managerName.setTypeface(TypefaceUtil.semibold());


	}

	public void setData(String label, String logo, String color, Integer level, Double levelProgress, String programName, String managerName) {
		this.label.setText(label);
		this.programLogo.setImage(logo, color, 50, 50);
		if (level > 0)
			this.programLogo.setLevel(level, levelProgress);
		else {
			this.programLogo.hideLevel();
			ViewGroup.LayoutParams lp = this.programLogo.getLayoutParams();
			lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
			this.programLogo.setLayoutParams(lp);
		}
		this.programName.setText(programName);
		this.managerName.setText(managerName);
	}
}
