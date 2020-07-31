package vision.genesis.clientapp.utils;

import org.greenrobot.eventbus.EventBus;

import io.swagger.client.model.AssetType;
import io.swagger.client.model.PostAssetDetailsWithPrices;
import io.swagger.client.model.PostTag;
import io.swagger.client.model.ProfilePublic;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/07/2020.
 */
public class PostTagClickHandler
{
	public static void handlePostTagClick(PostTag tag) {
		switch (tag.getType()) {
			case UNDEFINED:
			case EVENT:
			case URL:
			case POST:
				break;
			case PROGRAM:
				PostAssetDetailsWithPrices program = tag.getAssetDetails();
				ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(program.getId(),
						program.getLogoUrl(),
						program.getColor(),
						program.getProgramDetails().getLevel(),
						program.getProgramDetails().getLevelProgress(),
						program.getTitle(),
						"",
						"",
						false,
						false,
						AssetType.PROGRAM);
				EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				break;
			case FUND:
				PostAssetDetailsWithPrices fund = tag.getAssetDetails();
				FundDetailsModel fundDetailsModel = new FundDetailsModel(fund.getId(),
						fund.getLogoUrl(),
						fund.getColor(),
						fund.getTitle(),
						"",
						false,
						false);
				EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
				break;
			case FOLLOW:
				PostAssetDetailsWithPrices follow = tag.getAssetDetails();
				ProgramDetailsModel followDetailsModel = new ProgramDetailsModel(follow.getId(),
						follow.getLogoUrl(),
						follow.getColor(),
						0,
						0.0,
						follow.getTitle(),
						"",
						"",
						false,
						false,
						AssetType.FOLLOW);
				EventBus.getDefault().post(new ShowProgramDetailsEvent(followDetailsModel));
				break;
			case USER:
				ProfilePublic userDetails = tag.getUserDetails();
				UserDetailsModel userModel = new UserDetailsModel(
						userDetails.getId(),
						userDetails.getLogoUrl(),
						userDetails.getUsername(),
						userDetails.getRegistrationDate());
				EventBus.getDefault().post(new ShowUserDetailsEvent(userModel));
				break;
			case ASSET:
				break;
		}
	}
}
