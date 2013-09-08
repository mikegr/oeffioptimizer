package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SteigInfoFragment extends AbstractFragment {

	
	public static SteigInfoFragment create(long id){
		SteigInfoFragment f = new SteigInfoFragment();
		Bundle b = new Bundle();
		b.putLong("id", id);
		return f;
	};
	
	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_steiginfo, container, false);
		list = (ListView) view.findViewById(R.id.fragment_steiginfo_list);
		
		SteigInfoAdapter adapter = new SteigInfoAdapter(getActivity());
		
		list.setAdapter(adapter);
		return view;
	}
}
