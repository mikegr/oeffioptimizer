package com.mintplex.oeffioptimizer;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.routing.Routing;
import com.mintplex.oeffioptimizer.routing.Trips;

@EFragment(R.layout.fragment_fromtoform)
public class FromToFormFragment extends Fragment {

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
			from.setAdapter(new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_dropdown_item_1line, list));
	        to.setAdapter(new ArrayAdapter<Haltestellen>(getActivity(), android.R.layout.simple_dropdown_item_1line, list));
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
	

	public void showTrips(Routing result) {
		List<Trips> trips = result.getTrips();
		list.setAdapter(new ArrayAdapter<Trips>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, trips));
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
