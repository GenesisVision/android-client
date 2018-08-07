package vision.genesis.clientapp.feature.main.program.create.second;

import com.arellomobile.mvp.MvpView;

import java.util.List;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/07/2018.
 */

public interface CreateProgramSecondStepView extends MvpView
{
//	void setBrokers(List<BrokerTradeServer> brokers);

	void setLeverages(List<Integer> leverages);

	void setNextButtonAvailability(boolean available);

	void showSnackbarMessage(String message);

	void showNextProgress(boolean show);

	void setConfirmError(String message);
}
