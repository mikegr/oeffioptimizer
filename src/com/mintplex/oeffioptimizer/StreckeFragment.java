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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StreckeFragment extends AbstractFragment {

    public static final String TAG = "STRECKE";

	public static StreckeFragment create(long id) {
		StreckeFragment f = new StreckeFragment();
		Bundle b = new Bundle();
		b.putLong("id", id);
		f.setArguments(b);
		return f;
	}
	
	long id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getArguments().getLong("id");
	}

	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_strecke, container, false);
		list = (ListView) view.findViewById(R.id.fragment_strecke_list);
/*
		Steige steig = Steige.findById(Steige.class, id);
		long line = steig.fkLinienId;

		List<Steige> nextSteige = (List<Steige>) Select.
				from(Steige.class).
				where(
                        Condition.prop("fk_linien_id").eq(steig.fkLinienId),
                        Condition.prop("reihenfolge").gt(steig.reihenfolge),
                        Condition.prop("richtung").eq(steig.richtung))
                .list();
		
		list.setAdapter(new ArrayAdapter<Steige>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, nextSteige));
		list.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Steige s = (Steige) arg0.getItemAtPosition(arg2);
				SteigInfoActivity.start(getActivity(), s.id);
			}
		});
		*/
		return view;
	}
	
}
