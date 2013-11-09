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

import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.model.Recent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainFragment extends Fragment {

	AutoCompleteTextView autoComplete;
	Button autoCompleteButton;
	Haltestellen selected;
	ListView recentList;

    Button abfahrtButton;
    AutoCompleteTextView abfahrtComplete;

    Haltestellen abfahrt;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		autoComplete = (AutoCompleteTextView) view
				.findViewById(R.id.fragment_main_autocomplete);
        autoCompleteButton = (Button) view
                .findViewById(R.id.fragment_main_autocomplete_button);
        abfahrtComplete = (AutoCompleteTextView) view.findViewById(R.id.fragment_main_abfahrt_autocomplete);
        abfahrtButton = (Button) view.findViewById(R.id.fragment_main_abfahrt_autocomplete_button);
        
        try {
            DatabaseHelper helper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
    		Dao<Haltestellen, Integer> haltestellenDao = helper.getHaltestellenDao();
    		List<Haltestellen> list = haltestellenDao.queryForAll();
    		autoComplete.setAdapter(new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_dropdown_item_1line, list));
            abfahrtComplete.setAdapter(new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_dropdown_item_1line, list));
			
		} catch (Exception e) {
			Log.w("Reading stops failed", e);
		}
        

		autoComplete.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				selected = (Haltestellen) arg0.getItemAtPosition(arg2);
				autoCompleteButton.setEnabled(true);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				selected = null;
				autoCompleteButton.setEnabled(false);
			}
		});

		autoComplete.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				selected = (Haltestellen) arg0.getItemAtPosition(arg2);
				autoCompleteButton.setEnabled(true);
				
			}
		});

        abfahrtComplete.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abfahrt = (Haltestellen) adapterView.getItemAtPosition(i);
                abfahrtButton.setEnabled(true);
            }
        });
		autoCompleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HaltestellenActivity.start(getActivity(), selected.id);
			}
		});
        abfahrtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbfahrtenActivity.start(getActivity(), abfahrt.id);
            }
        });

		autoCompleteButton.setEnabled(false);
		
		recentList = (ListView) view.findViewById(R.id.fragment_main_list_recent);
		
		setRecentList();
		
		return view;
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setRecentList();
	}

	private void setRecentList() {
		List<Recent> recent = Recent.find(Recent.class, null, null, null, "last_access DESC", null);
		
		recentList.setAdapter(new ArrayAdapter<Recent>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, recent));
		recentList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Recent r = (Recent) arg0.getItemAtPosition(arg2);
				if (r != null && r.fkHaltestellenId != null) {
					HaltestellenActivity.start(getActivity(), r.fkHaltestellenId.id);	
				}
			}
		});
	}

	public static MainFragment create() {
		return new MainFragment();
	}
}
