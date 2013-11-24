package com.mintplex.oeffioptimizer;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.ViewById;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.routing.Legs;
import com.mintplex.oeffioptimizer.routing.Routing;
import com.mintplex.oeffioptimizer.routing.Trip;
import com.mintplex.oeffioptimizer.routing.Trips;

@EFragment(R.layout.fragment_fromtoform)
public class FromToFormFragment extends AbstractFragment {

	public static final String TAG = "FROMTOFORM";

	@ViewById(R.id.fragment_fromtoform_from_actv)
	AutoCompleteTextView from;

	@ViewById(R.id.fragment_fromtoform_to_actv)
	AutoCompleteTextView to;
	
	@ViewById(R.id.fragment_fromtoform_go_button)
	Button go;
	
	@ViewById(R.id.fragment_fromtoform_list)
	ListView list;
	
	@AfterViews
	public void afterViews()  {
		try {
			DatabaseHelper helper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
			Dao<Haltestellen, Integer> haltestellenDao = helper.getHaltestellenDao();
			List<Haltestellen> list = haltestellenDao.queryForAll();
			ArrayAdapter<Haltestellen> fromAdapter = new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
			//fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			from.setAdapter(fromAdapter);
			ArrayAdapter<Haltestellen> toAdapter = new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
			//toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        to.setAdapter(toAdapter);
	        
	        super.initAd();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

        
        from.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
        		fromData = (Haltestellen) arg0.getItemAtPosition(arg2);
				
			}
        	
		}); 
        to.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
        			long arg3) {
        		toData = (Haltestellen) arg0.getItemAtPosition(arg2);
        	}
        });
	}
	
	@Click(R.id.fragment_fromtoform_go_button)
	public void onGo() {
		hideKeyboard();
//		if (fromData == null || toData == null) {
//			Toast.makeText(getActivity(), "Wähle Stationen", Toast.LENGTH_LONG).show();
//			return;
//		}
		((FromToFormActivity)getActivity()).searchRoute(fromData, toData);
	}
	
	Haltestellen fromData;
	Haltestellen toData;
	
	@SystemService  
	LayoutInflater inflater;

	public void showTrips(Routing result) {
		List<Trips> trips = result.getTrips();
		list.setAdapter(new ArrayAdapter<Trips>( getActivity(),R.layout.from_to_list_item, android.R.id.text1, trips) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = inflater.inflate(R.layout.from_to_list_item, parent, false);
				StringBuilder lines = new StringBuilder();
				
				Trips trips = getItem(position);
				Trip trip = trips.getTrip();
				List<Legs> legs = trip.getLegs();
				for(Legs leg: legs) {
					if (lines.length() > 0) {
						lines.append(" \u00b7 ");
					}
					lines.append(leg.getMode().getNumber());
				}
				
				StringBuilder times = new StringBuilder();
				Legs leg0 = legs.get(0);
				Legs legZ = legs.get(legs.size() -1);
				
				times.append(leg0.getPoints().get(0).getDateTime().getTime());
				times.append(" - ");
				times.append(legZ.getPoints().get(1).getDateTime().getTime());
				
				
				Utils.t(view, R.id.from_to_list_item_lines, lines.toString());
				Utils.t(view, R.id.from_to_list_item_duration, trips.getTrip().getDuration());
				Utils.t(view, R.id.from_to_list_item_times, times.toString());
				return view;
			}
			
		});
	}
	
	@ItemClick(R.id.fragment_fromtoform_list)
	public void onTrip(Trips trips) {
		DisplayRouteActivity.start(getActivity(), trips.getTrip());
	}

	protected void hideKeyboard() {
		FragmentActivity act = getActivity();
		if (act == null || act.isFinishing()) return;
		InputMethodManager inputManager = (InputMethodManager)  act.getSystemService(Context.INPUT_METHOD_SERVICE);
    	if (inputManager != null) {
    		View currentFocus = act.getCurrentFocus();    		
    		inputManager.hideSoftInputFromWindow((currentFocus == null) ? null : currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
    	}
	}
	
}
