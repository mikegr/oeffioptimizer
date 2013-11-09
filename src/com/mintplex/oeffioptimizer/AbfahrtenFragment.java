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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mintplex.oeffioptimizer.model.Steige;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class AbfahrtenFragment extends AbstractFragment {

	public static final String TAG = "ABFAHRT";

	public static Fragment create(long id) {
		AbfahrtenFragment f = new AbfahrtenFragment();
		Bundle b = new Bundle();
		b.putLong("id", id);
		f.setArguments(b);
		return f;
	}
	
	
	ExpandableListView list;
	
	long id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getArguments().getLong("id");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_abfahrten, container, false);
		list = (ExpandableListView) view.findViewById(R.id.fragment_abfahrten_list);
		
		List<Steige> steige = Steige.find(Steige.class, "fk_haltestellen_id = ?", Long.toString(id));
		Set<String> linien = new HashSet<String>();
		HashMap<String, List<Steige>> map = new HashMap<String, List<Steige>>();
		for(Steige s:steige) {
			linien.add(s.linienName);
			List<Steige> mapList = map.get(s.linienName);
			if (mapList == null) {
				mapList = new ArrayList<Steige>();
				map.put(s.linienName, mapList);	
			}
			mapList.add(s);
		}
		AbfahrtenAdapter adapter = new AbfahrtenAdapter(getActivity());
		ArrayList<String> sorted = new ArrayList<String>(linien);
		Collections.sort(sorted);
		adapter.setData(sorted, map);
		list.setAdapter(adapter);
		
		list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {
                Steige steig = (Steige) expandableListView.getExpandableListAdapter().getChild(i, i2);
                StreckeActivity.start(getActivity(), steig.id);
                return true;
            }
        });
		return view;
	}
	

	
}
