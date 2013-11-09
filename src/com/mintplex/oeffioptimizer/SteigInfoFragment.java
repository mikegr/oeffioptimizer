/**
 * ÖffiOptimizer
 * Copyright (C) 2013 Michael Greifeneder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mintplex.oeffioptimizer.model.Steige;

public class SteigInfoFragment extends AbstractFragment {


    public static final String TAG = "STEIG_INFO";

	public static SteigInfoFragment create(long id){
		SteigInfoFragment f = new SteigInfoFragment();
		Bundle b = new Bundle();
		b.putLong("id", id);
        f.setArguments(b);
		return f;
	};
	
	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_steiginfo, container, false);
		list = (ListView) view.findViewById(R.id.fragment_steiginfo_list);
/*
        Steige steig = Steige.findById(Steige.class, id);

        HaltestellenFragment.SteigInfo steifInfo = SteigInfoAdapter.getSteigInfo(steig);

		SteigInfoAdapter adapter = new SteigInfoAdapter(getActivity(), steifInfo);

		list.setAdapter(adapter);
		*/
		return view;
	}
}
