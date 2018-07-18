package vision.genesis.clientapp.feature.main.program.create.third;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import vision.genesis.clientapp.model.CreateProgramData;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/07/2018.
 */

public interface CreateProgramThirdStepView extends MvpView
{
	void setPeriods(List<Integer> availablePeriods);

	void showDatePicker();

	void setCreateButtonAvailability(boolean available);

	void showSnackbarMessage(String message);

	void showCreateProgress(boolean show);

	void setLimits(CreateProgramData createProgramData);

	void setBalance(Double balance);
}
