package com.mintplex.oeffioptimizer;

import java.util.List;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		autoComplete = (AutoCompleteTextView) view
				.findViewById(R.id.fragment_main_autocomplete);
		
		List<Haltestellen> list = Haltestellen.listAll(Haltestellen.class);
		
		autoComplete.setAdapter(new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_dropdown_item_1line, list));
		autoCompleteButton = (Button) view
				.findViewById(R.id.fragment_main_autocomplete_button);
		
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
		autoCompleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HaltestellenActivity.start(getActivity(), selected.id);
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
