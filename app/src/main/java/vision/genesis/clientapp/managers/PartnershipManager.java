package vision.genesis.clientapp.managers;

import io.swagger.client.api.PartnershipApi;
import io.swagger.client.model.ItemsViewModelReferralFriend;
import io.swagger.client.model.ItemsViewModelRewardDetails;
import io.swagger.client.model.PartnershipDetails;
import rx.Observable;
import vision.genesis.clientapp.model.DateRange;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class PartnershipManager
{
	private final PartnershipApi partnershipApi;

	public PartnershipManager(PartnershipApi partnershipApi) {
		this.partnershipApi = partnershipApi;
	}

	public Observable<PartnershipDetails> getDetails(String currency) {
		return partnershipApi.getDetails(AuthManager.token.getValue(), currency);
	}

	public Observable<ItemsViewModelReferralFriend> getReferrals(Integer skip, Integer take) {
		return partnershipApi.getReferrals(AuthManager.token.getValue(), null, null, skip, take);
	}

	public Observable<ItemsViewModelRewardDetails> getRewardsHistory(DateRange dateRange, Integer skip, Integer take) {
		return partnershipApi.getRewardsHistory(AuthManager.token.getValue(),
				dateRange != null ? dateRange.getFrom() : null, dateRange != null ? dateRange.getTo() : null,
				skip, take);
	}
}