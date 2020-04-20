package vision.genesis.clientapp.managers;

import io.swagger.client.api.PartnershipApi;
import io.swagger.client.model.Currency;
import io.swagger.client.model.PartnershipDetails;
import io.swagger.client.model.ReferralFriendItemsViewModel;
import io.swagger.client.model.RewardDetailsItemsViewModel;
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
		return partnershipApi.getDetails(Currency.fromValue(currency));
	}

	public Observable<ReferralFriendItemsViewModel> getReferrals(Integer skip, Integer take) {
		return partnershipApi.getReferrals(null, null, skip, take);
	}

	public Observable<RewardDetailsItemsViewModel> getRewardsHistory(DateRange dateRange, Integer skip, Integer take) {
		return partnershipApi.getRewardsHistory(
				dateRange != null ? dateRange.getFrom() : null, dateRange != null ? dateRange.getTo() : null,
				skip, take);
	}
}