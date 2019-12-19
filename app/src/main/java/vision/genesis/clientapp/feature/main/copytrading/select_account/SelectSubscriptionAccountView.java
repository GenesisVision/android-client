package vision.genesis.clientapp.feature.main.copytrading.select_account;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/12/2019.
 */


interface SelectSubscriptionAccountView extends MvpView
{
	void setAccountOptions(ArrayList<String> accountOptions);

	void setAccount(String account, Integer position);

	void showNoAccounts(boolean show);

	void setConfirmButtonEnabled(boolean enabled);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);
}