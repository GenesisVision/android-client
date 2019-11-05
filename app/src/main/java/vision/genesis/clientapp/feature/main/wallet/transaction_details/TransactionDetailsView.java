package vision.genesis.clientapp.feature.main.wallet.transaction_details;

import com.arellomobile.mvp.MvpView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */

interface TransactionDetailsView extends MvpView
{
	//	void setDetails(TransactionViewModel transactionDetails);
//
//	void setEmailResent();
//
	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
