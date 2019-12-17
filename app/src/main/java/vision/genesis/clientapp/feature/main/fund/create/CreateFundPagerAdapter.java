package vision.genesis.clientapp.feature.main.fund.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Locale;

import io.swagger.client.model.NewFundRequest;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.public_info.PublicInfoFragment;
import vision.genesis.clientapp.feature.main.fund.create.assets.FundAssetsFragment;
import vision.genesis.clientapp.feature.main.fund.create.deposit.CreateFundDepositFragment;
import vision.genesis.clientapp.feature.main.fund.create.fees.FundFeesFragment;
import vision.genesis.clientapp.model.FundAssetsModel;
import vision.genesis.clientapp.model.FundSettingsModel;
import vision.genesis.clientapp.model.PublicInfoModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

public class CreateFundPagerAdapter extends FragmentStatePagerAdapter
{
	public interface OnPageVisibilityChanged
	{
		void pagerShow();

		void pagerHide();
	}

	private PublicInfoFragment publicInfoFragment;

	private FundAssetsFragment assetsFragment;

	private FundFeesFragment feesFragment;

	private CreateFundDepositFragment depositFragment;

	CreateFundPagerAdapter(FragmentManager fm) {
		super(fm);

		publicInfoFragment = PublicInfoFragment.with(new PublicInfoModel(true, "01",
				GenesisVisionApplication.INSTANCE.getString(R.string.public_info), true,
				String.format(Locale.getDefault(), "%s (1/4)", GenesisVisionApplication.INSTANCE.getString(R.string.next)),
				null, null, null));
		assetsFragment = FundAssetsFragment.with(new FundAssetsModel(true, "02",
				GenesisVisionApplication.INSTANCE.getString(R.string.assets_selection), "",
				String.format(Locale.getDefault(), "%s (2/4)", GenesisVisionApplication.INSTANCE.getString(R.string.next)),
				null, true));
		feesFragment = FundFeesFragment.with(new FundSettingsModel(true, "03", GenesisVisionApplication.INSTANCE.getString(R.string.fees_settings),
				String.format(Locale.getDefault(), "%s (3/4)", GenesisVisionApplication.INSTANCE.getString(R.string.next)), null, null, false));
		depositFragment = new CreateFundDepositFragment();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 1:
				return assetsFragment;
			case 2:
				return feesFragment;
			case 3:
				return depositFragment;
			case 0:
			default:
				return publicInfoFragment;
		}
	}

	@Override
	public int getCount() {
		return 4;
	}

	void setRequest(NewFundRequest request) {
		depositFragment.setRequest(request);
	}

	public void setMinDepositAmount(Double minDepositAmount, String warningInfo) {
		publicInfoFragment.setWarningInfo(warningInfo);
		depositFragment.setMinDepositAmount(minDepositAmount);
	}
}
