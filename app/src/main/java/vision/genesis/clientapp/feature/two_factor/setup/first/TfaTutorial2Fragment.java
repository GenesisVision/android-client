package vision.genesis.clientapp.feature.two_factor.setup.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/06/2018.
 */
public class TfaTutorial2Fragment extends MvpAppCompatFragment
{
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tfa_tutorial_2, container, false);
	}
}
