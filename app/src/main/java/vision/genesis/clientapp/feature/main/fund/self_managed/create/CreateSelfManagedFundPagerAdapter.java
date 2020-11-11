package vision.genesis.clientapp.feature.main.fund.self_managed.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Locale;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoFragment;
import vision.genesis.clientapp.feature.main.fund.create.assets.FundAssetsFragment;
import vision.genesis.clientapp.feature.main.fund.create.deposit.CreateFundDepositFragment;
import vision.genesis.clientapp.model.FundAssetsModel;
import vision.genesis.clientapp.model.FundDepositModel;
import vision.genesis.clientapp.model.PublicInfoModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

public class CreateSelfManagedFundPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private PublicInfoFragment publicInfoFragment;

	private FundAssetsFragment assetsFragment;

	private CreateFundDepositFragment depositFragment;

	CreateSelfManagedFundPagerAdapter(FragmentManager fm) {
		super(fm);

		publicInfoFragment = PublicInfoFragment.with(new PublicInfoModel(true, "01",
				GenesisVisionApplication.INSTANCE.getString(R.string.public_info), true, false,
				String.format(Locale.getDefault(), "%s (1/3)", GenesisVisionApplication.INSTANCE.getString(R.string.next)),
				null, null, null));
		assetsFragment = FundAssetsFragment.with(new FundAssetsModel(true, "02",
				GenesisVisionApplication.INSTANCE.getString(R.string.assets_selection), "",
				String.format(Locale.getDefault(), "%s (2/3)", GenesisVisionApplication.INSTANCE.getString(R.string.next)),
				null, true));
		depositFragment = CreateFundDepositFragment.with(new FundDepositModel(true, "03",
				GenesisVisionApplication.INSTANCE.getString(R.string.deposit_details),
				GenesisVisionApplication.INSTANCE.getString(R.string.create_self_managed_fund)));
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 1:
				return assetsFragment;
			case 2:
				return depositFragment;
			case 0:
			default:
				return publicInfoFragment;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	public void setMinDepositAmount(Double minDepositAmount, String warningInfo) {
		publicInfoFragment.setWarningInfo(warningInfo);
		depositFragment.setMinDepositAmount(minDepositAmount);
	}
}
