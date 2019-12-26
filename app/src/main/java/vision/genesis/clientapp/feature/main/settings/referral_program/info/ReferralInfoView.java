package vision.genesis.clientapp.feature.main.settings.referral_program.info;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.PartnershipDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

interface ReferralInfoView extends MvpView
{
	void setRefUrl(String refUrl);

	void setDetails(PartnershipDetails details, String baseCurrency);

	void showRefProgress(boolean show);

	void showDetailsProgress(boolean show);

	void showSnackbarMessage(String message);
}
