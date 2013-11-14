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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mintplex.oeffioptimizer.model.Connections;
import com.mintplex.oeffioptimizer.model.Exitinfo;
import com.mintplex.oeffioptimizer.model.Lift;

/**
 * Created by mike on 03.09.13.
 */
public class HaltestellenFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getArguments().getLong("ID");
    }

    long id;
    
    ExpandableListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station, container, false);
        
        listView = (ExpandableListView) view.findViewById(R.id.fragment_station_list);

        HaltestellenAdapter adapter = new HaltestellenAdapter(getLayoutInflater(null));
        
        /*
        List<Steige> steige = Steige.find(Steige.class, "fk_haltestellen_id = ? ORDER BY fk_linien_id", Long.toString(id));
        Map<Steige, SteigInfo> adapterData = new HashMap<Steige, SteigInfo>();
        
        List<Steige> using = new ArrayList<Steige>();
        for (Steige s: steige) {
        	SteigInfo info = SteigInfoAdapter.getSteigInfo(s);
        	if (! s.linienName.startsWith("U")) continue;
        	adapterData.put(s, info);      
        	using.add(s);
        }
        adapter.setData(using, adapterData);
        listView.setAdapter(adapter);
        */
        return view;
        
    }
    

    
    public static class SteigInfo {
    	public List<Connections> connections;
    	public List<Lift> lifts;
    	public List<Exitinfo> exits;
    	
		public int getCount() {
			return connections.size() + lifts.size() + exits.size();
		}

    }



    public static HaltestellenFragment create(long id) {
        HaltestellenFragment f = new HaltestellenFragment();
        Bundle b = new Bundle();
        b.putLong("ID", id);
        f.setArguments(b);
        return f;
    }



}
