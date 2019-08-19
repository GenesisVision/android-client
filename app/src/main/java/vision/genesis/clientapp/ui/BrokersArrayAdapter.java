package vision.genesis.clientapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/07/2018.
 */

public class BrokersArrayAdapter extends ArrayAdapter
{
	public BrokersArrayAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
		super(context, resource, objects);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
		}
		else {
			view = convertView;
		}

//		if (getItem(position) instanceof BrokerTradeServer) {
//			BrokerTradeServer broker = (BrokerTradeServer) getItem(position);
//			if (broker != null) {
//				TextView title = view.findViewById(android.R.id.text1);
//				title.setText(String.format(Locale.getDefault(), "%s (%s)",
//						broker.getName(), broker.getHost()));
//			}
//
//		}
		return view;
	}

	public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_two_lines_item, parent, false);
		}
		else {
			view = convertView;
		}

		TextView title = view.findViewById(R.id.title);
		TextView subtitle = view.findViewById(R.id.subtitle);

//		if (getItem(position) instanceof BrokerTradeServer) {
//			BrokerTradeServer broker = (BrokerTradeServer) getItem(position);
//			if (broker != null) {
//				title.setText(String.format(Locale.getDefault(), "%s (%s)",
//						broker.getName(), broker.getHost()));
//
//				StringBuilder leveragesString = new StringBuilder();
//				int index = 0;
//				for (Integer leverage : broker.getLeverages()) {
//					leveragesString.append(leverage.toString());
//					if (index < broker.getLeverages().size() - 1)
//						leveragesString.append(", ");
//					index++;
//				}
//
//				subtitle.setText(String.format(Locale.getDefault(), "%s: %s",
//						GenesisVisionApplication.INSTANCE.getString(R.string.leverages),
//						leveragesString.toString()));
//			}
//		}
		return view;
	}
}