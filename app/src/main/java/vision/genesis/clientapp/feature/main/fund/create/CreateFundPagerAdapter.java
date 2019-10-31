package vision.genesis.clientapp.feature.main.fund.create;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import io.swagger.client.model.NewFundRequest;
import vision.genesis.clientapp.feature.main.fund.create.assets.CreateFundAssetsFragment;
import vision.genesis.clientapp.feature.main.fund.create.deposit.CreateFundDepositFragment;
import vision.genesis.clientapp.feature.main.fund.create.fees.CreateFundFeesFragment;
import vision.genesis.clientapp.feature.main.fund.create.main.CreateFundMainFragment;

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

	private CreateFundMainFragment mainFragment;

	private CreateFundAssetsFragment assetsFragment;

	private CreateFundFeesFragment feesFragment;

	private CreateFundDepositFragment depositFragment;

	CreateFundPagerAdapter(FragmentManager fm) {
		super(fm);

		createFragments();
	}

	private void createFragments() {
		mainFragment = new CreateFundMainFragment();
		assetsFragment = new CreateFundAssetsFragment();
		feesFragment = new CreateFundFeesFragment();
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
				return mainFragment;
		}
	}

	@Override
	public int getCount() {
		return 4;
	}

	void setRequest(NewFundRequest request) {
		mainFragment.setRequest(request);
		assetsFragment.setRequest(request);
		feesFragment.setRequest(request);
		depositFragment.setRequest(request);
	}

	public void setMinDepositAmount(Double minDepositAmount) {
		mainFragment.setMinDepositAmount(minDepositAmount);
		depositFragment.setMinDepositAmount(minDepositAmount);
	}
}
